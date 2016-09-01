/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.dao.HowLongDAO;
import br.com.MarathonWS.model.entity.HowLong;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class HowLongMarathonController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbTitle;
    @FXML
    private ImageView ivFoto;
    @FXML
    private Label lbDescricao;
    @FXML
    private VBox vbSpeed;
    @FXML
    private VBox vbDistance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (HowLong howLong : new HowLongDAO().pegarTodos()) {
            Parent parent = FxMananger.carregarComponente("HowLongItem", howLong);
            parent.setOnMouseReleased((MouseEvent) -> {
                lbTitle.setText(howLong.getName());
                ivFoto.setImage(new Image("file:source/howLongMarathon/" + howLong.getImage()));
                if (howLong.getTipo() == 1) {
                    long segundos = (long) ((42 / howLong.getForce()) * 3600);
                    long hora = segundos / 3600;
                    segundos %= 3600;
                    long minuto = segundos / 60;
                    segundos %= 60;
                    String tempo = (hora == 0 ? "" : hora + "h ") + (minuto == 0 ? "" : minuto + "m ") + (segundos == 0 ? "" : segundos + "s");
                    lbDescricao.setText("The top speed of a " + howLong.getName() + " is " + howLong.getForce() + "Km/h. It would take " + tempo + " \n"
                            + "to complete a 42km marathon.");
                } else {
                    double quantidade = 42000 / howLong.getForce();
                    lbDescricao.setText("The length of a " + howLong.getName() + " is " + howLong.getForce() + "m. It would take\n" + new DecimalFormat("0").format(quantidade)
                            + " of them to cover the track of a 42km marathon.");
                }

            });
            switch (howLong.getTipo()) {
                case 1:
                    vbSpeed.getChildren().add(parent);
                    break;
                case 2:
                    vbDistance.getChildren().add(parent);
                    break;
            }
        }
    }

}
