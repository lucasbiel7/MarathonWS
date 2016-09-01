/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.UserDAO;
import br.com.MarathonWS.model.entity.User;
import br.com.MarathonWS.model.util.Message;
import br.com.MarathonWS.model.util.Sessao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pfSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Runner user
//        tfEmail.setText("zulma.laurie@saucedout.com");
//        pfSenha.setText("E6V7enaE");
        //Administrator
//        tfEmail.setText("leila.azedeva@mskills.com");
//        pfSenha.setText("MvTQ3itX");
        //Coordinator
//        tfEmail.setText("dean.pinilla@gmail.com");
//        pfSenha.setText("WQnbEyjo");
    }

    @FXML
    private void btLoginActionEvent(ActionEvent actionEvent) {
        if (!tfEmail.getText().isEmpty() && !pfSenha.getText().isEmpty()) {
            User user = new UserDAO().pegarPorEmail(tfEmail.getText());
            if (user != null) {
                if (user.getPassword().equals(pfSenha.getText())) {
                    Sessao.user = user;
                    switch (user.getRoleId().getRoleName()) {
                        case "Administrator":
                            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MENUADMINISTRATOR);
                            break;
                        case "Coordinator":
                            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MENUCOORDINATOR);
                            break;
                        case "Runner":
                            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MENURUNNER);
                            break;
                    }
                } else {
                    Message.show("Login is unsucessful", "Password are incorrect!", Message.Tipo.ERROR);
                }
            } else {
                Message.show("Login is unsucessful", "Can not find that email address on database!", Message.Tipo.ERROR);
            }
        } else {
            Message.show("Error", "All fields are required", Message.Tipo.ERROR);
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        GerenciarJanela.janelaAtual = GerenciarJanela.Janela.INICIO;
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.INICIO);
    }

}
