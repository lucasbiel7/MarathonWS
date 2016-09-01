/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.EventDAO;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.control.dao.RegistrationStatusDAO;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.entity.RegistrationStatus;
import br.com.MarathonWS.model.util.Message;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class RunnerManagementController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<RegistrationStatus> cbStatus;
    @FXML
    private ComboBox<Event> cbEvent;
    @FXML
    private ComboBox<String> cbSortBy;
    @FXML
    private TableView<RegistrationEvent> tvRegistration;
    @FXML
    private TableColumn<RegistrationEvent, String> tcFirstName;
    @FXML
    private TableColumn<RegistrationEvent, String> tcLastName;
    @FXML
    private TableColumn<RegistrationEvent, String> tcEmail;
    @FXML
    private TableColumn<RegistrationEvent, RegistrationStatus> tcStatus;
    @FXML
    private TableColumn<RegistrationEvent, RegistrationEvent> tcEditar;
    @FXML
    private Label lbTotalRunners;
    @FXML
    private Button btSearch;
    private ObservableList<RegistrationStatus> registrationStatus = FXCollections.observableArrayList();
    private ObservableList<Event> events = FXCollections.observableArrayList();
    private ObservableList<String> sortBy = FXCollections.observableArrayList();
    private ObservableList<RegistrationEvent> registrationEvents = FXCollections.observableArrayList();

    private PopOver poEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            apPrincipal.getScene().addMnemonic(new Mnemonic(btSearch, new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_ANY)));
        });
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENUCOORDINATOR;
        cbEvent.setItems(events);
        cbSortBy.setItems(sortBy);
        cbStatus.setItems(registrationStatus);
        tvRegistration.setItems(registrationEvents);
        events.setAll(new EventDAO().pegarTodos());
        sortBy.setAll("First Name", "Last Name", "E-mail", "Status");
        registrationStatus.setAll(new RegistrationStatusDAO().pegarTodos());
        tcFirstName.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, String> param) -> new SimpleStringProperty(param.getValue().getRegistrationId().getRunnerId().getEmail().getFirstName()));
        tcLastName.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, String> param) -> new SimpleStringProperty(param.getValue().getRegistrationId().getRunnerId().getEmail().getLastName()));
        tcEmail.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, String> param) -> new SimpleStringProperty(param.getValue().getRegistrationId().getRunnerId().getEmail().getEmail()));
        tcStatus.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, RegistrationStatus> param) -> new SimpleObjectProperty<>(param.getValue().getRegistrationId().getRegistrationStatusId()));
        tcEditar.setCellValueFactory((TableColumn.CellDataFeatures<RegistrationEvent, RegistrationEvent> param) -> new SimpleObjectProperty<>(param.getValue()));
        tcEditar.setCellFactory((TableColumn<RegistrationEvent, RegistrationEvent> param) -> new TableCell<RegistrationEvent, RegistrationEvent>() {
            @Override
            protected void updateItem(RegistrationEvent item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText("");
                } else {
                    Button button = new Button("Edit");
                    button.setOnAction((ActionEvent ae) -> {
                        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MANAGEARUNNER, item.getRegistrationId());
                    });
                    setGraphic(button);
                }
            }
        });
        poEmail = new PopOver();
        poEmail.setTitle("Emails");
    }

    @FXML
    private void btRefreshActionEvent(ActionEvent actionEvent) {
        if (cbStatus.getSelectionModel().getSelectedItem() != null && cbEvent.getSelectionModel().getSelectedItem() != null && cbSortBy.getSelectionModel().getSelectedItem() != null) {
            registrationEvents.setAll(new RegistrationEventDAO().pegarPorEventStatus(cbEvent.getSelectionModel().getSelectedItem(), cbStatus.getSelectionModel().getSelectedItem()));
            switch (cbSortBy.getSelectionModel().getSelectedItem()) {
                case "First Name":
                    registrationEvents.sort((RegistrationEvent o1, RegistrationEvent o2) -> o1.getRegistrationId().getRunnerId().getEmail().getFirstName().compareTo(o2.getRegistrationId().getRunnerId().getEmail().getFirstName()));
                    break;
                case "Last Name":
                    registrationEvents.sort((RegistrationEvent o1, RegistrationEvent o2) -> o1.getRegistrationId().getRunnerId().getEmail().getLastName().compareTo(o2.getRegistrationId().getRunnerId().getEmail().getLastName()));
                    break;
                case "E-mail":
                    registrationEvents.sort((RegistrationEvent o1, RegistrationEvent o2) -> o1.getRegistrationId().getRunnerId().getEmail().getEmail().compareTo(o2.getRegistrationId().getRunnerId().getEmail().getEmail()));
                    break;
                case "Status":

                    break;
            }
            lbTotalRunners.setText(String.valueOf(registrationEvents.size()));
        } else {
            Message.show("Empty field", "All fields are required", Message.Tipo.ERROR);
        }
    }

    @FXML
    private void btRunnerDetailsActionEvent(ActionEvent actionEvent) {
        if (registrationEvents.isEmpty()) {
            Message.show("Empty list", "Choise filters and refresh table to export to CSV.", Message.Tipo.ERROR);
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
            File file = fileChooser.showSaveDialog(apPrincipal.getScene().getWindow());
            if (file != null) {
                List<String> linha = new ArrayList<>();
                linha.add("First Name;Last Name;Email;Gender;Country;Date of Birth;Registration Status;Race events");
                for (RegistrationEvent registrationEvent : registrationEvents) {
                    Registration registration = registrationEvent.getRegistrationId();
                    List<RegistrationEvent> registrationEvents = new RegistrationEventDAO().pegarPorRegistration(registration);
                    String events = "\"";
                    for (RegistrationEvent registrationEvent1 : registrationEvents) {
                        events += registrationEvent1.getEventId().getEventName() + ", ";
                    }
                    events = events.substring(0, events.length() - 2);
                    events += "\"";
                    linha.add(""
                            + registration.getRunnerId().getEmail().getFirstName() + ";"
                            + registration.getRunnerId().getEmail().getLastName() + ";"
                            + registration.getRunnerId().getEmail().getEmail() + ";"
                            + registration.getRunnerId().getGender() + ";"
                            + registration.getRunnerId().getCountryCode() + ";"
                            + new SimpleDateFormat("dd/MM/yyyy").format(registration.getRunnerId().getDateOfBirth()) + ";"
                            + registration.getRegistrationStatusId() + ";"
                            + events
                    );
                }
                try {
                    Files.write(Paths.get(file.getAbsolutePath()), linha, StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    Logger.getLogger(RunnerManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void btEmailAddressActionEvent(ActionEvent actionEvent) {
        if (registrationEvents.isEmpty()) {
            Message.show("Empty list", "Choise filters and refresh table to export email list.", Message.Tipo.ERROR);
        } else {
            String emails = "";
            for (RegistrationEvent registrationEvent : registrationEvents) {
                emails += registrationEvent.getRegistrationId().getRunnerId().getEmail().getFirstName() + " " + registrationEvent.getRegistrationId().getRunnerId().getEmail().getLastName() + " <" + registrationEvent.getRegistrationId().getRunnerId().getEmail().getEmail() + ">;";
                emails += "\n";
            }
            emails = emails.substring(0, emails.length() - 2);
            poEmail.setContentNode(FxMananger.carregarComponente("ViewEmail", emails));
            poEmail.show(btSearch);
        }
    }
}
