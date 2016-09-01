/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.RegistrationEventDAO;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.util.Formatter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ManageARunnerController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbFirstName;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbGender;
    @FXML
    private Label lbDateOfBirth;
    @FXML
    private Label lbCountry;
    @FXML
    private Label lbCharity;
    @FXML
    private Label lbTargetToRaise;
    @FXML
    private Label lbRaceKitOption;
    @FXML
    private Label lbRaceEvent;
    @FXML
    private ImageView ivRegistered;
    @FXML
    private ImageView ivPaymentConfirmed;
    @FXML
    private ImageView ivRaceKitSent;
    @FXML
    private ImageView ivRaceAttended;

    private Registration registration;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            registration = (Registration) apPrincipal.getUserData();
            lbEmail.setText(registration.getRunnerId().getEmail().getEmail());
            lbFirstName.setText(registration.getRunnerId().getEmail().getFirstName());
            lbLastName.setText(registration.getRunnerId().getEmail().getLastName());
            lbGender.setText(registration.getRunnerId().getGender().getGender());
            lbDateOfBirth.setText(new SimpleDateFormat("dd MMMM yyyy").format(registration.getRunnerId().getDateOfBirth()));
            lbCountry.setText(registration.getRunnerId().getCountryCode().getCountryName());
            lbCharity.setText(registration.getCharityId().getCharityName());
            lbTargetToRaise.setText("$" + Formatter.toMoney(registration.getSponsorshipTarget().doubleValue()));
            lbRaceKitOption.setText("Option " + registration.getRaceKitOptionId().getRaceKitOptionId());
            List<RegistrationEvent> registrationEvents = new RegistrationEventDAO().pegarPorRegistration(registration);
            for (RegistrationEvent registrationEvent : registrationEvents) {
                lbRaceEvent.setText(lbRaceEvent.getText() + registrationEvent.getEventId().getEventName() + "\n");
            }
            Image image = new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/check.png"));
            switch (registration.getRegistrationStatusId().getRegistrationStatusId()) {
                case 1:
                    ivRegistered.setImage(image);
                    break;
                case 2:
                    ivRegistered.setImage(image);
                    ivPaymentConfirmed.setImage(image);
                    break;
                case 3:
                    ivRegistered.setImage(image);
                    ivPaymentConfirmed.setImage(image);
                    ivRaceKitSent.setImage(image);
                    break;
                case 4:
                    ivRegistered.setImage(image);
                    ivPaymentConfirmed.setImage(image);
                    ivRaceKitSent.setImage(image);
                    ivRaceAttended.setImage(image);
                    break;
            }
        });
    }

    @FXML
    private void btPreviewCertificateActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.PREVIEWCERTIFICATE, registration);
    }

    @FXML
    private void btEditProfileActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.EDITPROFILE, registration.getRunnerId().getEmail());
    }
}
