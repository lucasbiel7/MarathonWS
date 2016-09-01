/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.control.dao.SponsorshipDAO;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.entity.Sponsorship;
import br.com.MarathonWS.model.util.Message;
import br.com.MarathonWS.model.util.Sessao;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jidefx.scene.control.field.FormattedTextField;
import jidefx.scene.control.field.verifier.IntegerDateFieldPatternVerifier;
import jidefx.scene.control.field.verifier.IntegerRangePatternVerifier;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class SponsorARunnerController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfName;
    @FXML
    private ComboBox<RegistrationEvent> cbRegistrationEvent;
    @FXML
    private TextField tfNameOfCard;
    @FXML
    private FormattedTextField ftfNumberOfCard;
    @FXML
    private FormattedTextField ftfMonth;
    @FXML
    private FormattedTextField ftfYear;
    @FXML
    private FormattedTextField ftfCVC;

    @FXML
    private Label lbCharity;
    @FXML
    private FormattedTextField ftfAmount;
    @FXML
    private Label lbAmount;
    private ObservableList<RegistrationEvent> registrationEvents = FXCollections.observableArrayList();

    private PopOver popOver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ftfAmount.getPatternVerifiers().put("d", new IntegerRangePatternVerifier(0, Integer.MAX_VALUE));
        ftfAmount.setPattern("d");
        ftfAmount.setText("0");
        popOver = new PopOver();
        ftfNumberOfCard.getPatternVerifiers().put("n", new IntegerRangePatternVerifier(0, 9999));
        ftfNumberOfCard.setPattern("n n n n");
        ftfYear.getPatternVerifiers().put("d", new IntegerDateFieldPatternVerifier(Calendar.YEAR, true));
        ftfYear.setPattern("d");
        ftfMonth.getPatternVerifiers().put("d", new IntegerRangePatternVerifier(1, 12, true));
        ftfMonth.setPattern("d");
        ftfCVC.getPatternVerifiers().put("d", new IntegerRangePatternVerifier(0, 999));
        ftfCVC.setPattern("d");
        lbCharity.setVisible(false);
        cbRegistrationEvent.setItems(registrationEvents);
        registrationEvents.setAll(new RegistrationEventDAO().pegarPorMarathona(Sessao.marathon));
    }

    @FXML
    private void cbRunnerActionEvent(ActionEvent actionEvent) {
        System.out.println("seleciono");
        if (cbRegistrationEvent.getSelectionModel().getSelectedItem() != null) {
            lbCharity.setText(cbRegistrationEvent.getSelectionModel().getSelectedItem().getRegistrationId().getCharityId().getCharityName());
        }
        lbCharity.setVisible(cbRegistrationEvent.getSelectionModel().getSelectedItem() != null);

    }

    @FXML
    private void ivInfoMouseReleased(MouseEvent mouseEvent) {
        System.out.println("Info clicked");
        popOver.setContentNode(FxMananger.carregarComponente("DescCharity", cbRegistrationEvent.getSelectionModel().getSelectedItem().getRegistrationId().getCharityId()));
        popOver.setDetachable(true);
        popOver.show(lbCharity, mouseEvent.getSceneX(), mouseEvent.getSceneY());
    }

    @FXML
    private void btAdicionarActionEvent(ActionEvent actionEvent) {
        Integer integer = Integer.parseInt(ftfAmount.getText());
        integer += 10;
        ftfAmount.setText(String.valueOf(integer));
        lbAmount.setText("$" + ftfAmount.getText());
    }

    @FXML
    private void btRemoverActionEvent(ActionEvent actionEvent) {
        Integer integer = Integer.parseInt(ftfAmount.getText());
        if (integer - 10 >= 0) {
            integer -= 10;
        }
        ftfAmount.setText(String.valueOf(integer));
        lbAmount.setText("$" + ftfAmount.getText());
    }

    @FXML
    private void btPayNowActionEvent(ActionEvent actionEvent) {
        Sponsorship sponsorship = new Sponsorship();
        sponsorship.setAmount(BigDecimal.valueOf(Double.parseDouble(ftfAmount.getText())));
        sponsorship.setSponsorName(tfName.getText());
        if (tfNameOfCard.getText().isEmpty() || sponsorship.getAmount().doubleValue() <= 0 || cbRegistrationEvent.getSelectionModel().getSelectedItem() == null || tfNameOfCard.getText().isEmpty() || ftfNumberOfCard.getText().length() < 19 || ftfMonth.getText().isEmpty() || ftfYear.getText().isEmpty() || ftfCVC.getText().length() < 3) {
            Message.show("Empty form", "All fields are required.", Message.Tipo.ERROR);

        } else {
            sponsorship.setRegistrationId(cbRegistrationEvent.getSelectionModel().getSelectedItem().getRegistrationId());
            new SponsorshipDAO().cadastrar(sponsorship);
            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.SPONSORSHIPCONFIRMATION, new Object[]{sponsorship,cbRegistrationEvent.getSelectionModel().getSelectedItem().getBibNumber().intValue()});
        }
    }

    @FXML
    private void tfAmountKeyRelease(KeyEvent keyEvent) {
        lbAmount.setText("$" + ftfAmount.getText());
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        GerenciarJanela.janelaAtual = GerenciarJanela.Janela.INICIO;
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), FxMananger.carregarComponente(GerenciarJanela.janelaAtual.getArquivo()));
    }

}
