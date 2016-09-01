/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class BMICalculatorController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private ToggleButton tbMale;
    @FXML
    private ToggleButton tbFemale;
    @FXML
    private TextField tfAltura;
    @FXML
    private TextField tfPeso;
    @FXML
    private Slider slResultado;
    @FXML
    private Label lbResultado;
    @FXML
    private ImageView ivResultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btCalculateActionEvent(ActionEvent actionEvent) {
        double weight = Double.parseDouble(tfPeso.getText().replace(",", "."));
        double altura = Double.parseDouble(tfAltura.getText().replace(",", ".")) / 100;
        double bmi = weight / Math.pow(altura, 2);
        slResultado.setValue(bmi);
        lbResultado.setText(new DecimalFormat("0.00").format(bmi));
        if (bmi < 18.5) {
            ivResultado.setImage(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/" + "bmi-underweight-icon" + ".png")));
        } else if (bmi < 25d) {
            ivResultado.setImage(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/" + "bmi-healthy-icon" + ".png")));
        } else if (bmi < 30) {
            ivResultado.setImage(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/" + "bmi-overweight-icon" + ".png")));
        } else {
            ivResultado.setImage(new Image(getClass().getResourceAsStream("/br/com/MarathonWS/view/image/" + "bmi-obese-icon" + ".png")));
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
    }

}
