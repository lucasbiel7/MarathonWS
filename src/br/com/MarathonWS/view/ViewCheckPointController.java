/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.model.entity.Checkpoint;
import br.com.MarathonWS.model.entity.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ViewCheckPointController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbTitle;
    @FXML
    private Label lbLandmark;
    @FXML
    private Label lbDescricao;
    @FXML
    private Label lbTitleService;
    @FXML
    private VBox vbService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            if (apPrincipal.getUserData() instanceof Checkpoint) {
                Checkpoint checkpoint = (Checkpoint) apPrincipal.getUserData();
                lbTitle.setText(checkpoint.getCheckpoint());
                lbLandmark.setText("Landmark:");
                lbDescricao.setText(checkpoint.getLandmark());
                lbTitleService.setText("Services Provided:");
                if (checkpoint.getDrink()) {
                    Label label = new Label("Drink");
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/services/" + "map-icon-drinks.png")));
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    label.setGraphic(imageView);
                    vbService.getChildren().add(label);
                }
                if (checkpoint.getEnergybar()) {
                    Label label = new Label("Energy bar");
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/services/" + "map-icon-energy-bars.png")));
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    label.setGraphic(imageView);
                    vbService.getChildren().add(label);
                }
                if (checkpoint.getInformation()) {
                    Label label = new Label("Information");
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/services/" + "map-icon-information.png")));
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    label.setGraphic(imageView);
                    vbService.getChildren().add(label);

                }
                if (checkpoint.getMedical()) {
                    Label label = new Label("Medical");
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/services/" + "map-icon-medical.png")));
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    label.setGraphic(imageView);
                    vbService.getChildren().add(label);

                }
                if (checkpoint.getToilet()) {
                    Label label = new Label("Toilets");
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/services/" + "map-icon-toilets.png")));
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    label.setGraphic(imageView);
                    vbService.getChildren().add(label);
                }
            } else if (apPrincipal.getUserData() instanceof Event) {
                Event event = (Event) apPrincipal.getUserData();
                lbTitle.setText("Race start");
                lbDescricao.setText(event.getEventName());
                Label label = new Label("Race start");
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/services/" + "map-icon-start.png")));
                imageView.setFitHeight(30);
                imageView.setPreserveRatio(true);
                label.setGraphic(imageView);
                vbService.getChildren().add(label);
            }
        });
    }
}
