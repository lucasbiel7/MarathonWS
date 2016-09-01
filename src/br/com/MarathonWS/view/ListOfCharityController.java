/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.dao.CharityDAO;
import br.com.MarathonWS.model.entity.Charity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ListOfCharityController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private VBox vbCharities;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (Charity charity : new CharityDAO().pegarTodos()) {
            vbCharities.getChildren().add(FxMananger.carregarComponente("ViewCharity", charity));
        }
    }

}
