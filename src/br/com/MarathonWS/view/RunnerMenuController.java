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
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class RunnerMenuController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;

    private PopOver poContactInformation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        poContactInformation = new PopOver();
        poContactInformation.setTitle("");
        poContactInformation.setContentNode(FxMananger.carregarComponente("ContactInformation"));
        poContactInformation.setDetached(true);
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.INICIO;
    }

    @FXML
    private void btConctactInformationActionEvent(ActionEvent actionEvent) {
        poContactInformation.show(apPrincipal.getScene().getWindow());
    }

    @FXML
    private void btRegisterForAnEventActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.REGISTEREVENT);
    }

    @FXML
    private void btEditYourProfileActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.EDITPROFILE);
    }

    @FXML
    private void btMyRaceResultsActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MYRACERESULT);
    }

    @FXML
    private void btSponsorshipActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MYSPONSORSHIP);
    }
}
