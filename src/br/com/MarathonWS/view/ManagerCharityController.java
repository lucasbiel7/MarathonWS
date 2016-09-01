/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.CharityDAO;
import br.com.MarathonWS.model.entity.Charity;
import br.com.MarathonWS.model.util.Message;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ManagerCharityController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfName;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfLogoFile;
    @FXML
    private Circle ciLogoImage;

    private Charity charity;
    private FileChooser fileChooser;
    private File imagem;

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            if (apPrincipal.getUserData() instanceof Charity) {
                charity = (Charity) apPrincipal.getUserData();
                tfName.setText(charity.getCharityName());
                taDescription.setText(charity.getCharityDescription());
                tfLogoFile.setText(charity.getCharityLogo());
                ciLogoImage.setFill(new ImagePattern(new Image("file:source/charity/" + charity.getCharityLogo())));
            }
        });
        charity = new Charity();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagem", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp"));
    }

    @FXML
    private void btSaveActionEvent(ActionEvent actionEvent) {
        if (tfName.getText().isEmpty() || taDescription.getText().isEmpty()) {
            Message.show("Fields required", "Name and description can not be a null!", Message.Tipo.ERROR);
        } else {
            charity.setCharityName(tfName.getText());
            charity.setCharityDescription(taDescription.getText());
            if (imagem != null) {
                charity.setCharityLogo(imagem.getName());
                try {
                    Files.write(Paths.get("source/charity/" + charity.getCharityLogo()), Files.readAllBytes(Paths.get(imagem.getAbsolutePath())));
                } catch (IOException ex) {
                    Logger.getLogger(ManagerCharityController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
            if (charity.getCharityId() != null) {
                new CharityDAO().editar(charity);
                Message.show("Edit charity", "Charity was edited successfully!", Message.Tipo.INFORMATION);
            } else {
                new CharityDAO().cadastrar(charity);
                Message.show("Save charity", "Charity was successfully saved!!", Message.Tipo.INFORMATION);
            }
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
    }

    @FXML
    private void btBrowseActionEvent(ActionEvent actionEvent) {
        imagem = fileChooser.showOpenDialog(apPrincipal.getScene().getWindow());
        if (imagem != null) {
            tfLogoFile.setText(imagem.getName());
            ciLogoImage.setFill(new ImagePattern(new Image("file:" + imagem.getAbsolutePath())));
        } else {
            tfLogoFile.setText("");
            ciLogoImage.setFill(null);
        }
    }
}
