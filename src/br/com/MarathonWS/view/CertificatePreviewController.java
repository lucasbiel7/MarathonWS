/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.EventDAO;
import br.com.MarathonWS.control.dao.MarathonDAO;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.control.dao.SponsorshipDAO;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.Marathon;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.util.Formatter;
import br.com.MarathonWS.model.util.Message;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class CertificatePreviewController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<Event> cbEvent;
    @FXML
    private ScrollPane spRelatorio;
    private SwingNode snRelatorio;

    private ObservableList<Event> events = FXCollections.observableArrayList();

    private Registration registration;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            if (apPrincipal.getUserData() instanceof Registration) {
                registration = (Registration) apPrincipal.getUserData();
            }
        });
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.RUNNERMANAGEMENT;
        snRelatorio = new SwingNode();
        spRelatorio.setContent(snRelatorio);
        cbEvent.setItems(events);
        Marathon marathon = new MarathonDAO().pegarPorAno((short) 2014);
        events.setAll(new EventDAO().pegarPorMaratona(marathon));
    }

    @FXML
    private void cbRaceEventActionEvent(ActionEvent actionEvent) {
        if (cbEvent.getSelectionModel().getSelectedItem() != null) {
            if (registration != null) {
                Event event = cbEvent.getSelectionModel().getSelectedItem();
                RegistrationEvent registrationEvent = new RegistrationEventDAO().pegarPorEventRegistration(event, registration);
                if (registrationEvent != null) {
                    if (registrationEvent.getRaceTime() != null) {
                        List<RegistrationEvent> registrationEvents = new RegistrationEventDAO().pegarPorEventOrderRaceTime(event);
                        registrationEvents.sort((RegistrationEvent o1, RegistrationEvent o2) -> {
                            if (o1.getRaceTime() == null) {
                                return 1;
                            }
                            if (o2.getRaceTime() == null) {
                                return -1;
                            }
                            return Integer.compare(o1.getRaceTime(), o2.getRaceTime());
                        });

                        try {
                            Map<String, Object> parametros = new HashMap<>();
                            parametros.put("logo", getClass().getResourceAsStream("/br/com/MarathonWS/view/image/marathon-skills-2014-logo.png"));
                            parametros.put("seal", getClass().getResourceAsStream("/br/com/MarathonWS/view/image/marathon-skills-2014-certificate-seal.png"));
                            int sec = registrationEvent.getRaceTime();
                            int hora = sec / (60 * 60);
                            sec %= (60 * 60);
                            int min = sec / 60;
                            sec %= 60;
                            parametros.put("congrats", "<html>Congratulations " + registration.getRunnerId().getEmail().getFirstName() + " " + registration.getRunnerId().getEmail().getLastName() + " for running in the " + event.getEventName() + ".<br>"
                                    + "You ran a time of " + hora + "h " + min + "m " + sec + "s and got a rank of " + registrationEvents.indexOf(registrationEvent) + "<sup>rd</sup>!");
                            parametros.put("event", event.getMarathonId().getMarathonName() + "\n"
                                    + event.getMarathonId().getCityName() + "," + event.getMarathonId().getCountryCode().getCountryName());

                            parametros.put("targetRaise", "You also raised $" + Formatter.toMoney(new SponsorshipDAO().pegarPorRegistration(registration).stream().mapToDouble(registration -> registration.getAmount().doubleValue()).sum()) + " for " + registration.getCharityId().getCharityName());
                            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/br/com/MarathonWS/view/report/certificate.jasper"), parametros, new JREmptyDataSource());
                            JRViewer jRViewer = new JRViewer(jasperPrint);
                            snRelatorio.setContent(jRViewer);
                        } catch (JRException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {

                    }
                } else {
                    Message.show("Not registered", "You have not registered for this event", Message.Tipo.ERROR);
                }
            }
        }
    }

}
