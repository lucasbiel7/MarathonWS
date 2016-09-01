/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.model.entity.HowLong;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class HowLongItemController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ImageView ivFoto;
    @FXML
    private Label lbNomeItem;

    private HowLong howLong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            howLong = (HowLong) apPrincipal.getUserData();
            ivFoto.setImage(new Image("file:source/howLongMarathon/" + howLong.getImage()));
            lbNomeItem.setText(howLong.getName());
        });
    }

}
