/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.CharityDAO;
import br.com.MarathonWS.control.dao.EventDAO;
import br.com.MarathonWS.control.dao.RaceKitOptionDAO;
import br.com.MarathonWS.control.dao.RegistrationDAO;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.control.dao.RegistrationStatusDAO;
import br.com.MarathonWS.control.dao.RunnerDAO;
import br.com.MarathonWS.model.entity.Charity;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.RaceKitOption;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.util.Formatter;
import br.com.MarathonWS.model.util.Message;
import br.com.MarathonWS.model.util.Sessao;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jidefx.scene.control.field.FormattedTextField;
import jidefx.scene.control.field.verifier.IntegerRangePatternVerifier;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class RegisterEventController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbDescription;
    @FXML
    private VBox vbEvents;
    @FXML
    private VBox vbRacesKit;
    @FXML
    private Label lbCost;
    @FXML
    private ComboBox<Charity> cbCharity;
    @FXML
    private FormattedTextField ftfTargetToRaise;
    @FXML
    private ImageView ivInforCharity;

    private ObservableList<Charity> charities = FXCollections.observableArrayList();

    private PopOver poCharity;
    private Runner runner;
    private Registration registration;
    private double cost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbDescription.setText("Please fill out all of the following information to register for events in the Skills\n"
                + Sessao.marathon.getMarathonName() + " beings held in " + Sessao.marathon.getCityName() + ", " + Sessao.marathon.getCountryCode().getCountryName() + ". Your will be contacted after you have\n"
                + "registered to organise payment and other details.");
        charities.setAll(new CharityDAO().pegarTodos());
        cbCharity.setItems(charities);
        ftfTargetToRaise.getPatternVerifiers().put("d", new IntegerRangePatternVerifier(0, Integer.MAX_VALUE));
        ftfTargetToRaise.setPattern("d");
        ivInforCharity.setVisible(false);
        poCharity = new PopOver();
        poCharity.setDetached(true);
        poCharity.setTitle("");
        runner = new RunnerDAO().pegarPorUsuario(Sessao.user);
        registration = new RegistrationDAO().pegarPorRunner(runner);
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENURUNNER;
        for (Event event : new EventDAO().pegarPorMaratona(Sessao.marathon)) {
            CheckBox checkBox = new CheckBox(event.getEventName() + "($" + Formatter.toMoney(event.getCost().doubleValue()) + ")");
            checkBox.setUserData(event);
            RegistrationEvent registrationEvent = new RegistrationEventDAO().pegarPorEventRegistration(event, registration);
            checkBox.setSelected(registrationEvent != null);
            checkBox.setOnAction(this::calcular);
            vbEvents.getChildren().add(checkBox);
        }
        ToggleGroup tgKits = new ToggleGroup();
        for (RaceKitOption raceKitOption : new RaceKitOptionDAO().pegarTodos()) {
            RadioButton radioButton = new RadioButton("Option " + raceKitOption.getRaceKitOptionId() + "($" + Formatter.toMoney(raceKitOption.getCost().doubleValue()) + "):\n"
                    + raceKitOption.getRaceKitOption());
            radioButton.setToggleGroup(tgKits);
            radioButton.setSelected(raceKitOption.getRaceKitOptionId().equalsIgnoreCase("a"));
            if (registration != null) {
                radioButton.setSelected(registration.getRaceKitOptionId().equals(raceKitOption));
            }
            radioButton.setUserData(raceKitOption);
            radioButton.setOnAction(this::calcular);
            vbRacesKit.getChildren().add(radioButton);
        }
        calcular(null);
        if (registration != null) {
            cbCharity.getSelectionModel().select(registration.getCharityId());
            ftfTargetToRaise.setText(String.valueOf(registration.getSponsorshipTarget().intValue()));
        }

    }

    @FXML
    private void btRegisterActionEvent(ActionEvent actionEvent) {
        if (registration == null) {
            registration = new Registration();
        }
        registration.setCost(new BigDecimal(cost));
        for (Node node : vbRacesKit.getChildren()) {
            if (node instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) node;
                if (radioButton.isSelected()) {
                    registration.setRaceKitOptionId((RaceKitOption) radioButton.getUserData());
                    break;
                }
            }
        }
        registration.setCharityId(cbCharity.getSelectionModel().getSelectedItem());
        registration.setRegistrationDateTime(new Date());
        registration.setRegistrationStatusId(new RegistrationStatusDAO().pegarPorId((short) 1));
        registration.setRunnerId(runner);
        if (registration.getCharityId() == null) {
            Message.show("Chosen charity", "Charity can not be a null.", Message.Tipo.ERROR);
        } else if (getEvents().size() < 1) {
            Message.show("Chosen event", "At least 1 event must be chosen.", Message.Tipo.ERROR);
        } else if (ftfTargetToRaise.getText().isEmpty() || Double.parseDouble(ftfTargetToRaise.getText()) < 0) {
            Message.show("Target raise invalid", "Target to raise must be a valid positive integer", Message.Tipo.ERROR);
        } else {
            registration.setSponsorshipTarget(BigDecimal.valueOf(Double.parseDouble(ftfTargetToRaise.getText())));
            if (registration.getRegistrationId() != null) {
                new RegistrationDAO().editar(registration);
            } else {
                new RegistrationDAO().cadastrar(registration);
            }
            for (Event event : getEvents()) {
                RegistrationEvent registrationEvent = new RegistrationEventDAO().pegarPorEventRegistration(event, registration);
                if (registrationEvent == null) {
                    registrationEvent = new RegistrationEvent();
                    registrationEvent.setRegistrationId(registration);
                    registrationEvent.setEventId(event);
                    List<RegistrationEvent> registrationEvents = new RegistrationEventDAO().pegarPorEvent(event);
                    if (registrationEvents.isEmpty()) {
                        registrationEvent.setBibNumber((short) 1);
                    } else {
                        registrationEvent.setBibNumber((short) (registrationEvents.get(0).getBibNumber() + Short.parseShort("1")));
                    }
                    new RegistrationEventDAO().cadastrar(registrationEvent);
                }
            }
            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.RUNNERREGISTRATIONCONFIRMATION);
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MENURUNNER);
    }

    @FXML
    private void cbCharityActionEvent(ActionEvent actionEvent) {
        ivInforCharity.setVisible(cbCharity.getSelectionModel().getSelectedItem() != null);
        poCharity.setContentNode(FxMananger.carregarComponente("DescCharity", cbCharity.getSelectionModel().getSelectedItem()));
    }

    @FXML
    private void ivCharityMouseReleased(MouseEvent mouseEvent) {
        poCharity.show(apPrincipal.getScene().getWindow());
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for (Node node : vbEvents.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    events.add((Event) checkBox.getUserData());
                }
            }
        }
        return events;

    }

    public List<RaceKitOption> getRaceKitOptions() {
        List<RaceKitOption> raceKitOptions = new ArrayList<>();
        for (Node node : vbRacesKit.getChildren()) {
            if (node instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) node;
                if (radioButton.isSelected()) {
                    raceKitOptions.add((RaceKitOption) radioButton.getUserData());
                }
            }
        }
        return raceKitOptions;
    }

    private void calcular(ActionEvent actionEvent) {
        cost = 0;
        for (Event event : getEvents()) {
            cost += event.getCost().doubleValue();
        }
        for (RaceKitOption raceKitOption : getRaceKitOptions()) {
            cost += raceKitOption.getCost().doubleValue();
        }
        lbCost.setText("$" + Formatter.toMoney(cost));
    }
}
