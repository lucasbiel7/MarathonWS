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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class InicioController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbTexto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GerenciarJanela.janelaAntiga = null;
        lbTexto.setText(Sessao.event.getMarathonId().getCityName() + ", " + Sessao.event.getMarathonId().getCountryCode().getCountryName() + "\n"
                + new SimpleDateFormat("EEEE").format(new Date()) + " " + new SimpleDateFormat("MMMM").format(new Date()) + " " + new SimpleDateFormat("yyyy").format(new Date()));
    }

    @FXML
    private void btLoginActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.LOGIN);
    }

    @FXML
    private void btWantRunnerActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.WANTBEARUNNER);
    }

    @FXML
    private void btWantSponsorActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.SPONSORARUNNER);
    }

    @FXML
    private void btWantFindActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.WANTFINDOUTMORE);
    }
}
