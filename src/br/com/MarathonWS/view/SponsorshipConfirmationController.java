/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.model.entity.Sponsorship;
import br.com.MarathonWS.model.util.Formatter;
import br.com.MarathonWS.model.util.Sessao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
public class SponsorshipConfirmationController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbInformation;
    @FXML
    private Label lbCorredor;
    @FXML
    private Label lbCarity;

    private Sponsorship sponsorship;
    private int numero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Object[] objetos = (Object[]) apPrincipal.getUserData();
            for (Object objeto : objetos) {
                if (objeto instanceof Sponsorship) {
                    sponsorship = (Sponsorship) objeto;
                } else if (objeto instanceof Integer) {
                    numero = (Integer) objeto;
                }
            }
            lbInformation.setText("Thank you for sponsoring a runner in " + Sessao.marathon + "!\n"
                    + "Your donation will help out their chosen charity.");
            lbCorredor.setText(sponsorship.getRegistrationId().getRunnerId().getEmail().getFirstName() + " " + sponsorship.getRegistrationId().getRunnerId().getEmail().getLastName() + " (" + numero + ") from " + sponsorship.getRegistrationId().getRunnerId().getCountryCode().getCountryCode());
            lbCarity.setText(sponsorship.getRegistrationId().getCharityId().getCharityName() + "\n"
                    + "$" + Formatter.toMoney(sponsorship.getAmount().doubleValue()));
        });
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.INICIO;
    }

    @FXML
    private void btVoltarActionEvent(ActionEvent actionEvent) {
        GerenciarJanela.janelaAtual = GerenciarJanela.Janela.INICIO;
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), FxMananger.carregarComponente(GerenciarJanela.janelaAtual.getArquivo()));
    }

}
