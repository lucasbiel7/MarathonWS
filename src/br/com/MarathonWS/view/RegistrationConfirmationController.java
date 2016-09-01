/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.model.util.Sessao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class RegistrationConfirmationController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbDesc.setText("Thank you for registering as a runner in " + Sessao.marathon.getMarathonName() + "!\n"
                + "You will be contacted soon about payment.");
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENURUNNER;
    }

    @FXML
    private void btOkActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MENURUNNER);
    }
}
