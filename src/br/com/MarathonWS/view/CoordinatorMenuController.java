/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class CoordinatorMenuController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.INICIO;

    }

    @FXML
    private void btRunnersActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.RUNNERMANAGEMENT);
    }

    @FXML
    private void btSponsorshipActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.SPONSORSHIPOVERVIEW);
    }

}
