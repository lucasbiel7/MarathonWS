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
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ScrollPane spContainer;
    @FXML
    private Label lbTimeLeft;
    @FXML
    private Button btVoltar;
    @FXML
    private Button btLogout;
    private Timeline timeline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GerenciarJanela.janelaAtual = GerenciarJanela.Janela.INICIO;
        FxMananger.inserirPainel(spContainer, FxMananger.carregarComponente(GerenciarJanela.janelaAtual.getArquivo()));
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), (ActionEvent ae) -> {
            if (Sessao.event != null) {
                Duration duration = Duration.between(new Date().toInstant(), Sessao.event.getStartDateTime().toInstant());
                long seconds = duration.getSeconds();
                long restTime;
                long days = duration.getSeconds() / (60 * 60 * 24);
                restTime = duration.getSeconds() % (60 * 60 * 24);
                long hours = restTime / (60 * 60);
                restTime = restTime % (60 * 60);
                long minuts = restTime / 60;
                lbTimeLeft.setText(" " + days + " days " + hours + " hours and " + minuts + " minutes until race starts!");
            }
        }), new KeyFrame(javafx.util.Duration.ZERO, (ActionEvent ae) -> {
            btLogout.setVisible(Sessao.user != null);
            btVoltar.setVisible(GerenciarJanela.janelaAtual != GerenciarJanela.Janela.INICIO);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void btLogoutActionEvent(ActionEvent actionEvent) {
        Sessao.user = null;
        GerenciarJanela.janelaAtual = GerenciarJanela.Janela.INICIO;
        FxMananger.inserirPainel(spContainer, FxMananger.carregarComponente(GerenciarJanela.janelaAtual.getArquivo()));
    }

    @FXML
    private void btVoltarActionEvent(ActionEvent actionEvent) {
        System.out.println("Antiga: " + GerenciarJanela.janelaAntiga);
        System.out.println("Atual: " + GerenciarJanela.janelaAtual);
        if (GerenciarJanela.janelaAtual == GerenciarJanela.Janela.MENUADMINISTRATOR || GerenciarJanela.janelaAtual == GerenciarJanela.Janela.MENUCOORDINATOR || GerenciarJanela.janelaAtual == GerenciarJanela.Janela.MENURUNNER) {
            Sessao.user = null;
        }
        FxMananger.inserirPainel(spContainer, GerenciarJanela.janelaAntiga);

    }
}
