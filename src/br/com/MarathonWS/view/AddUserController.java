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
import br.com.MarathonWS.model.util.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class AddUserController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private ComboBox<Role> cbRole;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirmPass;

    private ObservableList<Role> roles = FXCollections.observableArrayList();
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbRole.setItems(roles);
        roles.setAll(new RoleDAO().pegarTodos());
        user = new User();
    }

    @FXML
    private void btSaveActionEvent(ActionEvent actionEvent) {
        user.setEmail(tfEmail.getText());
        user.setFirstName(tfFirstName.getText());
        user.setLastName(tfLastName.getText());
        user.setPassword(pfPassword.getText());
        user.setRoleId(cbRole.getSelectionModel().getSelectedItem());
        if (user.getEmail().matches(".{1,}@\\w{1,}.\\w{1,}")) {
            if (user.getEmail().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getPassword().isEmpty() || cbRole.getSelectionModel().getSelectedItem() == null) {
                Message.show("Need all fields", "All fields are required on form!", Message.Tipo.ERROR);
            } else if (user.getPassword().length() >= 6 && user.getPassword().matches(".*([A-Z]{1,}).*") && user.getPassword().matches(".*([a-z]{1,}).*") && user.getPassword().matches(".*([0-9]{1,}).*") && user.getPassword().matches(".*([!#@\\$%\\^]{1,}).*")) {
                if (user.getPassword().equals(pfConfirmPass.getText())) {
                    new UserDAO().cadastrar(user);
                    FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
                    Message.show("Save user", "Users was successfully saved!", Message.Tipo.INFORMATION);
                } else {
                    Message.show("Confirm password invalid", "The value of “Password Again” must match the value of “Password”.", Message.Tipo.ERROR);
                }
            } else {
                Message.show("Password invalid", "The password must meet the following requirements:\n"
                        + "At least 6 characters\n"
                        + "At least 1 uppercase letter\n"
                        + "At least 1 number/digit\n"
                        + "At least 1 of the following symbols: !@#$%^", Message.Tipo.ERROR);
            }
        } else {
            Message.show("Email invalid", "Email address must be in a valid format", Message.Tipo.ERROR);
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
    }

}
