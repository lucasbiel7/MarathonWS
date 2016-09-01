/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.VolunteerDAO;
import br.com.MarathonWS.model.entity.Country;
import br.com.MarathonWS.model.entity.Gender;
import br.com.MarathonWS.model.entity.Volunteer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class VolunteerManagementController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<String> cbSortBy;
    @FXML
    private TableView<Volunteer> tvVolunteer;
    @FXML
    private Label lbTotalVolunteers;
    @FXML
    private TableColumn<Volunteer, String> tcFirstName;
    @FXML
    private TableColumn<Volunteer, String> tcLastName;
    @FXML
    private TableColumn<Volunteer, Country> tcCountry;
    @FXML
    private TableColumn<Volunteer, Gender> tcGender;

    private ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();
    private ObservableList<String> sortBy = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENUADMINISTRATOR;
        volunteers.setAll(new VolunteerDAO().pegarTodos());
        sortBy.setAll("First Name", "Last Name", "Country", "Gender");
        cbSortBy.setItems(sortBy);
        tvVolunteer.setItems(volunteers);
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        lbTotalVolunteers.setText(String.valueOf(volunteers.size()));
    }

    @FXML
    private void btRefreshActionEvent(ActionEvent actionEvent) {
        if (cbSortBy.getSelectionModel().getSelectedItem() != null) {
            String order = "firstName";
            switch (cbSortBy.getSelectionModel().getSelectedItem()) {
                case "First Name":
                    order = "firstName";
                    break;
                case "Last Name":
                    order = "lastName";
                    break;
                case "Country":
                    order = "countryCode";
                    break;
                case "Gender":
                    order = "gender";
                    break;
            }
            volunteers.setAll(new VolunteerDAO().pegarTodosOrder(order));
            lbTotalVolunteers.setText(String.valueOf(volunteers.size()));
        }
    }

    @FXML
    private void btImportVolunteerActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.IMPORTVOLUNTEERS);
    }

}
