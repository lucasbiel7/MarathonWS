/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.RegistrationDAO;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.control.dao.RunnerDAO;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.Marathon;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.util.CategoriaIdade;
import br.com.MarathonWS.model.util.Sessao;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class MyRaceResultsController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbGender;
    @FXML
    private Label lbAgeCategory;
    @FXML
    private TableView<RegistrationEvent> tvRank;
    @FXML
    private TableColumn<RegistrationEvent, Marathon> tcMarathon;
    @FXML
    private TableColumn<RegistrationEvent, Event> tcEvent;
    @FXML
    private TableColumn<RegistrationEvent, Integer> tcTime;
    @FXML
    private TableColumn<RegistrationEvent, Integer> tcOverAllRank;
    @FXML
    private TableColumn<RegistrationEvent, Integer> tcCategoryRank;

    private Runner runner;
    private ObservableList<RegistrationEvent> registrationEvents = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        runner = new RunnerDAO().pegarPorUsuario(Sessao.user);
        Registration registration = new RegistrationDAO().pegarPorRunner(runner);
        long anos = Duration.between(runner.getDateOfBirth().toInstant(), new Date().toInstant()).getSeconds() / (60 * 60 * 24 * 365);
        CategoriaIdade categoriaIdade = CategoriaIdade.categoria(Integer.parseInt(String.valueOf(anos)));
        lbAgeCategory.setText(categoriaIdade.toString());
        lbGender.setText(runner.getGender().toString());
        tvRank.setItems(registrationEvents);
        registrationEvents.setAll(new RegistrationEventDAO().pegarPorRegistration(registration));
        tcMarathon.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, Marathon> param) -> new SimpleObjectProperty<>(param.getValue().getEventId().getMarathonId()));
        tcEvent.setCellValueFactory(new PropertyValueFactory("eventId"));
        tcTime.setCellValueFactory(new PropertyValueFactory<>("raceTime"));
        tcTime.setCellFactory((TableColumn<RegistrationEvent, Integer> param) -> new TableCell<RegistrationEvent, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                if (empty) {
                    setText("");
                } else if (item == null) {
                    setText("Nã foi registrado o tempo");
                } else {
                    int hora = item / (60 * 60);
                    item %= (60 * 60);
                    int min = item / 60;
                    item %= 60;
                    int sec = item;
                    setText(hora + "h " + min + "m " + sec + "s");
                }
            }
        });
        tcOverAllRank.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, Integer> param) -> new SimpleObjectProperty<>(new RegistrationEventDAO().pegarPorEventOrderRaceTime(param.getValue().getEventId()).indexOf(param.getValue()) + 1));
        tcCategoryRank.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, Integer> param) -> new SimpleObjectProperty<>(new RegistrationEventDAO().pegarPorEventCategory(param.getValue().getEventId(), categoriaIdade, runner.getGender()).indexOf(param.getValue()) + 1));
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENURUNNER;
    }

    @FXML
    private void btViewAllRaceResultActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.VIEWALLRACERESULT);
    }
}
