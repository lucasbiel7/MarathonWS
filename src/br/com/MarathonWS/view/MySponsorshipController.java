/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.dao.RegistrationDAO;
import br.com.MarathonWS.control.dao.RunnerDAO;
import br.com.MarathonWS.control.dao.SponsorshipDAO;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.entity.Sponsorship;
import br.com.MarathonWS.model.util.Formatter;
import br.com.MarathonWS.model.util.Sessao;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class MySponsorshipController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Label lbCharityName;
    @FXML
    private Circle ciLogoCharity;
    @FXML
    private Text tDescCharity;
    @FXML
    private TableView<Sponsorship> tvSponsorship;
    @FXML
    private TableColumn<Sponsorship, String> tcSponsor;
    @FXML
    private TableColumn<Sponsorship, BigDecimal> tcAmount;
    @FXML
    private Label lbTotal;

    private ObservableList<Sponsorship> sponsorships = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Runner runner = new RunnerDAO().pegarPorUsuario(Sessao.user);
        Registration registration = new RegistrationDAO().pegarPorRunner(runner);
        if (registration != null) {
            lbCharityName.setText(registration.getCharityId().getCharityName());
            ciLogoCharity.setFill(new ImagePattern(new Image("file:source/charity/" + registration.getCharityId().getCharityLogo())));
            tDescCharity.setText(registration.getCharityId().getCharityDescription());
        }
        tvSponsorship.setItems(sponsorships);
        sponsorships.setAll(new SponsorshipDAO().pegarPorRegistration(registration));
        tcSponsor.setCellValueFactory(new PropertyValueFactory<>("sponsorName"));
        tcAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tcAmount.setCellFactory((TableColumn<Sponsorship, BigDecimal> param) -> new TableCell<Sponsorship, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                if (empty) {
                    setText("");
                } else {
                    setText("$" + Formatter.toMoney(item.doubleValue()));
                }
            }
        });
        lbTotal.setText("$" + Formatter.toMoney(sponsorships.stream().mapToDouble(u -> u.getAmount().doubleValue()).sum()));
    }

}
