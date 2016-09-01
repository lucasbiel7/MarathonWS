/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.model.entity.Charity;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ViewCharityController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Circle cFoto;
    @FXML
    private Label lbTitulo;
    @FXML
    private Text tDescricao;

    private Charity charity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            charity = (Charity) apPrincipal.getUserData();
            lbTitulo.setText(charity.getCharityName());
            tDescricao.setText(charity.getCharityDescription());
            try {
                cFoto.setFill(new ImagePattern(new Image(new ByteArrayInputStream(Files.readAllBytes(Paths.get("source/charity/" + charity.getCharityLogo()))))));
            } catch (IOException ex) {
                Logger.getLogger(ViewCharityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
