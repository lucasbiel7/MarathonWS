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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ManageCharitiesController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TableView<Charity> tvCharity;
    @FXML
    private TableColumn<Charity, String> tcLogo;
    @FXML
    private TableColumn<Charity, String> tcName;
    @FXML
    private TableColumn<Charity, String> tcDescription;
    @FXML
    private TableColumn<Charity, Charity> tcEdit;

    private ObservableList<Charity> charitys = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GerenciarJanela.janelaAntiga = GerenciarJanela.Janela.MENUADMINISTRATOR;
        tvCharity.setItems(charitys);
        charitys.setAll(new CharityDAO().pegarTodos());
        tcLogo.setCellValueFactory(new PropertyValueFactory<>("charityLogo"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("charityName"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("charityDescription"));
        tcEdit.setCellValueFactory((TableColumn.CellDataFeatures<Charity, Charity> param) -> new SimpleObjectProperty<>(param.getValue()));
        tcLogo.setCellFactory((TableColumn<Charity, String> param) -> new TableCell<Charity, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                } else {
                    Circle circle = new Circle(30);
                    circle.setFill(new ImagePattern(new Image("file:source/charity/" + item)));

                    setGraphic(circle);
                }
            }
        });
        tcEdit.setCellFactory((TableColumn<Charity, Charity> param) -> new TableCell<Charity, Charity>() {
            @Override
            protected void updateItem(Charity item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                } else {
                    Button button = new Button("Edit");
                    button.setOnAction((ActionEvent ae) -> {
                        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MANAGERCHARITY, item);
                    });
                    setGraphic(button);
                }
            }
        });
    }

    @FXML
    private void btAddCharityActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.MANAGERCHARITY);
    }
}
