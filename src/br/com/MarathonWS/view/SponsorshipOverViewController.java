/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.dao.CharityDAO;
import br.com.MarathonWS.control.dao.SponsorshipDAO;
import br.com.MarathonWS.model.entity.Charity;
import br.com.MarathonWS.model.util.Formatter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class SponsorshipOverViewController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;

    @FXML
    private ComboBox<String> cbSortBy;
    @FXML
    private Label lbQuantidade;
    @FXML
    private Label lbTotal;
    @FXML
    private TableView<Charity> tvCharity;
    @FXML
    private TableColumn<Charity, String> tcLogo;
    @FXML
    private TableColumn<Charity, String> tcName;
    @FXML
    private TableColumn<Charity, Double> tcAmount;

    private ObservableList<String> sortBy = FXCollections.observableArrayList();
    private ObservableList<Charity> charitys = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbSortBy.setItems(sortBy);
        sortBy.setAll("Logo", "Charity name", "Total amount");
        tvCharity.setItems(charitys);
        charitys.setAll(new CharityDAO().pegarTodos());
        tcLogo.setCellValueFactory(new PropertyValueFactory<>("charityLogo"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("charityName"));
        tcAmount.setCellValueFactory((TableColumn.CellDataFeatures<Charity, Double> param) -> new SimpleObjectProperty<>(new SponsorshipDAO().pegarPorCharity(param.getValue()).stream().mapToDouble(sponsorship -> sponsorship.getAmount().doubleValue()).sum()));
        tcLogo.setCellFactory((TableColumn<Charity, String> param) -> new TableCell<Charity, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (empty) {
                    setText("");
                    setGraphic(null);
                } else {
                    ImageView imageView = new ImageView(new Image("file:source/charity/" + item));
                    imageView.setFitHeight(30);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    setGraphic(imageView);
                }
            }
        });
        tcAmount.setCellFactory((TableColumn<Charity, Double> param) -> new TableCell<Charity, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                if (empty) {
                    setText("");
                } else {
                    setText("$" + Formatter.toMoney(item));
                }
            }
        });
        lbTotal.setText("Total Sponsorship: $" + Formatter.toMoney(new SponsorshipDAO().pegarTodos().stream().mapToDouble(sponsorShip -> sponsorShip.getAmount().doubleValue()).sum()));
        lbQuantidade.setText("Charities: " + charitys.size());
    }

    @FXML
    private void btSortActionEvent(ActionEvent actionEvent) {
        switch (cbSortBy.getSelectionModel().getSelectedItem()) {
            case "Logo":

                break;
            case "Charity name":
                charitys.sort((Charity o1, Charity o2) -> o1.getCharityName().compareTo(o2.getCharityName()));
                break;
            case "Total amount":
                charitys.sort((Charity o1, Charity o2) -> Double.compare(new SponsorshipDAO().pegarPorCharity(o1).stream().mapToDouble(sponsorship -> sponsorship.getAmount().doubleValue()).sum(), new SponsorshipDAO().pegarPorCharity(o2).stream().mapToDouble(sponsorship -> sponsorship.getAmount().doubleValue()).sum()));
                break;
        }
    }
}
