/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.dao.EventDAO;
import br.com.MarathonWS.control.dao.GenderDAO;
import br.com.MarathonWS.control.dao.MarathonDAO;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.model.entity.Country;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.Gender;
import br.com.MarathonWS.model.entity.Marathon;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.util.CategoriaIdade;
import br.com.MarathonWS.model.util.Message;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class PreviousRaceResultController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<Marathon> cbMarathon;
    @FXML
    private ComboBox<Event> cbRaceEvent;
    @FXML
    private ComboBox<Gender> cbGender;
    @FXML
    private ComboBox<CategoriaIdade> cbCategoriaIdade;
    @FXML
    private Label lbTotalRunners;
    @FXML
    private Label lbTotalRunnersFinish;
    @FXML
    private Label lbAverageRaceTime;
    @FXML
    private TableView<RegistrationEvent> tvRegistrationEvent;
    @FXML
    private TableColumn<RegistrationEvent, Integer> tcRank;
    @FXML
    private TableColumn<RegistrationEvent, Integer> tcRaceTime;
    @FXML
    private TableColumn<RegistrationEvent, Runner> tcRunnerName;
    @FXML
    private TableColumn<RegistrationEvent, Country> tcCountry;

    private ObservableList<Gender> gender = FXCollections.observableArrayList();
    private ObservableList<CategoriaIdade> categoriaIdades = FXCollections.observableArrayList();
    private ObservableList<Event> events = FXCollections.observableArrayList();
    private ObservableList<Marathon> marathons = FXCollections.observableArrayList();
    private ObservableList<RegistrationEvent> registrationEvents = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbMarathon.setItems(marathons);
        cbCategoriaIdade.setItems(categoriaIdades);
        cbRaceEvent.setItems(events);
        cbGender.setItems(gender);
        gender.setAll(new GenderDAO().pegarTodos());
        categoriaIdades.setAll(CategoriaIdade.values());
        marathons.setAll(new MarathonDAO().pegarTodos());
        tvRegistrationEvent.setItems(registrationEvents);
        tcRaceTime.setCellValueFactory(new PropertyValueFactory<>("raceTime"));
        tcRaceTime.setCellFactory((TableColumn<RegistrationEvent, Integer> param) -> new TableCell<RegistrationEvent, Integer>() {
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
        tcRunnerName.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, Runner> param) -> new SimpleObjectProperty<>(param.getValue().getRegistrationId().getRunnerId()));
        tcCountry.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, Country> param) -> new SimpleObjectProperty<>(param.getValue().getRegistrationId().getRunnerId().getCountryCode()));
        tcRank.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, Integer> param) -> new SimpleObjectProperty<>(registrationEvents.indexOf(param.getValue()) + 1));
        tcRank.setCellFactory((TableColumn<RegistrationEvent, Integer> param) -> new TableCell<RegistrationEvent, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                if (empty) {
                    setText("");
                } else if (item != null) {
                    setText("#" + item);
                }
            }
        });
        tcCountry.setCellFactory((TableColumn<RegistrationEvent, Country> param) -> new TableCell<RegistrationEvent, Country>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                if (empty) {
                    setText("");
                } else {
                    setText(item.getCountryCode());
                }
            }
        });
    }

    @FXML
    private void btSearchActionEvent(ActionEvent actionEvent) {
        if (cbRaceEvent.getSelectionModel().getSelectedItem() != null && cbGender.getSelectionModel().getSelectedItem() != null && cbCategoriaIdade.getSelectionModel().getSelectedItem() != null) {
            List<RegistrationEvent> registrationEvent = new RegistrationEventDAO().pegarPorEventCategory(cbRaceEvent.getSelectionModel().getSelectedItem(), cbCategoriaIdade.getSelectionModel().getSelectedItem(), cbGender.getSelectionModel().getSelectedItem());
            registrationEvent.sort((RegistrationEvent o1, RegistrationEvent o2) -> {
                if (o1.getRaceTime() == null) {
                    return 1;
                }
                if (o2.getRaceTime() == null) {
                    return -1;
                }
                return Integer.compare(o1.getRaceTime(), o2.getRaceTime());
            });
            lbTotalRunners.setText(String.valueOf(registrationEvent.size()));
            lbTotalRunnersFinish.setText(String.valueOf(registrationEvent.stream().filter((RegistrationEvent t) -> t.getRaceTime() != null).count()));
            double mediaTime = registrationEvent.stream().filter((RegistrationEvent t) -> t.getRaceTime() != null).mapToInt(RegistrationEvent::getRaceTime).average().orElse(0d);
            long item = (long) mediaTime;
            long hora = item / (60 * 60);
            item %= (60 * 60);
            long min = item / 60;
            item %= 60;
            long sec = item;
            lbAverageRaceTime.setText(hora + "h " + min + "m " + sec + "s");
            registrationEvents.setAll(registrationEvent);
        } else {
            Message.show("Choice filter", "All fields are required.", Message.Tipo.ERROR);
        }
    }

    @FXML
    private void cbMarathonActionEvent(ActionEvent actionEvent) {
        cbRaceEvent.getSelectionModel().clearSelection();
        if (cbMarathon.getSelectionModel().getSelectedItem() != null) {
            events.setAll(new EventDAO().pegarPorMaratona(cbMarathon.getSelectionModel().getSelectedItem()));
        }
    }

}
