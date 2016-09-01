/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.RoleDAO;
import br.com.MarathonWS.control.dao.UserDAO;
import br.com.MarathonWS.model.entity.Role;
import br.com.MarathonWS.model.entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class UserManagementController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ComboBox<Role> cbRole;
    @FXML
    private ComboBox<String> cbSortBy;
    @FXML
    private TextField tfSearch;
    @FXML
    private Label lbTotalUsers;

    @FXML
    private TableView<User> tvUser;
    @FXML
    private TableColumn<User, String> tcFirstName;
    @FXML
    private TableColumn<User, String> tcLastName;
    @FXML
    private TableColumn<User, String> tcEmail;
    @FXML
    private TableColumn<User, Role> tcRole;
    @FXML
    private TableColumn<User, User> tcEditar;

    private ObservableList<Role> roles = FXCollections.observableArrayList();
    private ObservableList<String> sortBy = FXCollections.observableArrayList();
    private ObservableList<User> users = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENUADMINISTRATOR;
        cbRole.setItems(roles);
        cbSortBy.setItems(sortBy);
        tvUser.setItems(users);
        roles.setAll(new RoleDAO().pegarTodos());
        sortBy.setAll("First Name", "Last Name", "Email", "Role");
        users.setAll(new UserDAO().pegarTodos());
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("roleId"));
        tcEditar.setCellValueFactory((TableColumn.CellDataFeatures<User, User> param) -> new SimpleObjectProperty<>(param.getValue()));
        tcEditar.setCellFactory((TableColumn<User, User> param) -> new TableCell<User, User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                if (empty) {
                    setText("");
                    setGraphic(null);
                } else {
                    Button button = new Button("Edit");
                    button.setOnAction((ActionEvent ae) -> {
                        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.EDITUSER, item);
                    });
                    setGraphic(button);
                }
            }
        });
        lbTotalUsers.setText(String.valueOf(users.size()));
    }

    @FXML
    private void btRefreshActionEvent(ActionEvent actionEvent) {
        String order = "firstName";
        if (cbSortBy.getSelectionModel().getSelectedItem() != null) {
            switch (cbSortBy.getSelectionModel().getSelectedItem()) {
                case "First Name":
                    order = "firstName";
                    break;
                case "Last Name":
                    order = "lastName";
                    break;
                case "Email":
                    order = "email";
                    break;
                case "Role":
                    order = "roleId";
                    break;
            }
        }
        users.setAll(new UserDAO().pegarPorRoleSearchOrder(cbRole.getSelectionModel().getSelectedItem(),
                tfSearch.getText(),
                order));
        lbTotalUsers.setText(String.valueOf(users.size()));
    }

    @FXML
    private void btAddNewUserActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.ADDUSER);
        
    }
}
