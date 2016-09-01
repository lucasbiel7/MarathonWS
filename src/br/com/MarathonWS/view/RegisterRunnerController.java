/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.GerenciarJanela;
import br.com.MarathonWS.control.dao.CountryDAO;
import br.com.MarathonWS.control.dao.GenderDAO;
import br.com.MarathonWS.control.dao.RoleDAO;
import br.com.MarathonWS.control.dao.RunnerDAO;
import br.com.MarathonWS.control.dao.UserDAO;
import br.com.MarathonWS.model.entity.Country;
import br.com.MarathonWS.model.entity.Gender;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.entity.User;
import br.com.MarathonWS.model.util.Message;
import br.com.MarathonWS.model.util.Sessao;
import java.net.URL;
import java.time.Duration;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class RegisterRunnerController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pfSenha;
    @FXML
    private PasswordField pfConfirmarSenha;
    @FXML
    private TextField tfPrimerioNome;
    @FXML
    private TextField tfUltimoNome;
    @FXML
    private ComboBox<Gender> cbGenero;
    @FXML
    private DatePicker dpDataDeNascimento;
    @FXML
    private ComboBox<Country> cbCountry;

    private ObservableList<Country> countrys = FXCollections.observableArrayList();
    private ObservableList<Gender> genders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCountry.setItems(countrys);
        cbGenero.setItems(genders);
        countrys.setAll(new CountryDAO().pegarTodos());
        genders.setAll(new GenderDAO().pegarTodos());
        cbCountry.setCellFactory((ListView<Country> param) -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                if (empty) {
                    setGraphic(null);
                    setText("");
                } else {
                    ImageView imageView = new ImageView(new Image("file:source/flags/" + item.getCountryFlag()));
                    imageView.setFitHeight(25);
                    imageView.setFitWidth(25);
                    imageView.setPreserveRatio(true);
                    imageView.setVisible(true);
                    setText(item.toString());
                    setGraphic(imageView);
                }
            }
        });
        
    }

    @FXML
    private void btRegisterActionEvent(ActionEvent actionEvent) {
        User user = new User();
        user.setEmail(tfEmail.getText());
        user.setFirstName(tfPrimerioNome.getText());
        user.setLastName(tfUltimoNome.getText());
        user.setPassword(pfSenha.getText());
        user.setRoleId(new RoleDAO().pegarPorId("R"));
        if (user.getEmail().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || pfConfirmarSenha.getText().isEmpty() || cbGenero.getSelectionModel().getSelectedItem() == null || cbCountry.getSelectionModel().getSelectedItem() == null || dpDataDeNascimento.getValue() == null) {
            Message.show("Need all fields", "All fields are required on form!", Message.Tipo.ERROR);
        } else {
            Runner runner = new Runner();
            runner.setGender(cbGenero.getSelectionModel().getSelectedItem());
            runner.setCountryCode(cbCountry.getSelectionModel().getSelectedItem());
            runner.setDateOfBirth(Date.from(dpDataDeNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            runner.setEmail(user);
            if (user.getEmail().matches(".{1,}@\\w{1,}.\\w{1,}")) {
                if (user.getPassword().length() >= 6 && user.getPassword().matches(".*([A-Z]{1,}).*") && user.getPassword().matches(".*([a-z]{1,}).*") && user.getPassword().matches(".*([0-9]{1,}).*") && user.getPassword().matches(".*([!#@\\$%\\^]{1,}).*")) {
                    if (user.getPassword().equals(pfConfirmarSenha.getText())) {
                        long anos = Duration.between(runner.getDateOfBirth().toInstant(), new Date().toInstant()).getSeconds() / (60 * 60 * 24 * 365);
                        System.out.println(anos);
                        if (anos > 10) {
                            new UserDAO().cadastrar(user);
                            new RunnerDAO().cadastrar(runner);
                            Sessao.user = user;
                            FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.REGISTEREVENT);
                        } else {
                            Message.show("Date of birth invalid", "“Date of Birth” must be a valid date and the runner must\n"
                                    + "be at least 10 years old at the time of registration.", Message.Tipo.ERROR);
                        }
                    } else {
                        Message.show("Confirm password invalid", "The value of “Password Again” must match the value of “Password”.", Message.Tipo.ERROR);
                    }
                } else {
                    Message.show("Password invalid", "The password must meet the following requirements:\n"
                            + "At least 6 characters\n"
                            + "At least 1 uppercase letter\n"
                            + "At least 1 number/digit\n"
                            + "At least 1 of the following symbols: !@#$%^", Message.Tipo.ERROR);
                }
            } else {
                Message.show("Email invalid", "Email address must be in a valid format", Message.Tipo.ERROR);
            }
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.INICIO);
    }
}
