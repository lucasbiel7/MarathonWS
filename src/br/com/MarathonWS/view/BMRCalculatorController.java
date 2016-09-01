/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.model.util.Message;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class BMRCalculatorController implements Initializable {

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
    private TextField tfAge;
    @FXML
    private Label lbSedentary;
    @FXML
    private Label lbLightActive;
    @FXML
    private Label lbModeratelyActive;
    @FXML
    private Label lbVeryActive;
    @FXML
    private Label lbExtremelyActive;
    @FXML
    private Label lbYourBMR;

    private PopOver poDescriptionActivity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        poDescriptionActivity = new PopOver(FxMananger.carregarComponente("DescCaloriesBurned"));
        poDescriptionActivity.setDetached(true);
    }

    @FXML
    private void btCalculateActionEvent(ActionEvent actionEvent) {
        if (tfAge.getText().isEmpty() || tfAltura.getText().isEmpty() || tfPeso.getText().isEmpty()) {
            Message.show("All field are required", "Please fill all fields' to calculate your BMR.", Message.Tipo.ERROR);
        } else {
            double bmr;
            if (tbFemale.isSelected()) {
                bmr = 655 + (9.6 * Double.parseDouble(tfPeso.getText())) + (1.8 * Double.parseDouble(tfAltura.getText())) - (4.7 * Integer.parseInt(tfAge.getText()));
            } else {
                bmr = 66 + (13.7 * Double.parseDouble(tfPeso.getText())) + (5 * Double.parseDouble(tfAltura.getText())) - (6.8 * Integer.parseInt(tfAge.getText()));
            }
            lbYourBMR.setText(String.valueOf(bmr));
            lbSedentary.setText("Sendentary: " + new DecimalFormat("0.000").format(bmr * 1.2));
            lbLightActive.setText("Lightly Active: " + new DecimalFormat("0.000").format(bmr * 1.375));
            lbModeratelyActive.setText("Moderately Active: " + new DecimalFormat("0.000").format(bmr * 1.55));
            lbVeryActive.setText("Very Active: " + new DecimalFormat("0.000").format(bmr * 1.725));
            lbExtremelyActive.setText("Extremely Active: " + new DecimalFormat("0.000").format(bmr * 1.9));
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
    }

    @FXML
    private void ivInfoMouseReleased(MouseEvent mouseEvent) {
        poDescriptionActivity.show(apPrincipal.getScene().getWindow());
    }
}
