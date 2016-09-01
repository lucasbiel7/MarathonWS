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
import br.com.MarathonWS.control.dao.VolunteerDAO;
import br.com.MarathonWS.model.entity.Volunteer;
import br.com.MarathonWS.model.util.Message;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class ImportVolunteersController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private TextField tfCSV;
    @FXML
    private Label lbDescription;

    private FileChooser fcCSV;

    private File csv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fcCSV = new FileChooser();
        fcCSV.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
    }

    @FXML
    private void btBrowseActionEvent(ActionEvent actionEvent) {
        csv = fcCSV.showOpenDialog(apPrincipal.getScene().getWindow());
        if (csv != null) {
            try {
                tfCSV.setText(csv.getName());
                List<String> dados = Files.readAllLines(Paths.get(csv.getAbsolutePath()), Charset.defaultCharset());
                if (!dados.isEmpty()) {
                    String separador = dados.get(0).contains(";") ? ";" : ",";
                    String[] colunas = dados.get(0).split(separador);

                    if (colunas.length == 5) {
                        int i = 1;
                        lbDescription.setText("The CSV file should have following fields:\n\n");
                        for (String coluna : colunas) {
                            lbDescription.setText(lbDescription.getText() + "Field " + i + ": " + coluna + "\n");
                            i++;
                        }
                    } else {
                        lbDescription.setText("CSV invalid");
                    }
                } else {
                    lbDescription.setText("Empty archive");
                }
            } catch (IOException ex) {
                Logger.getLogger(ImportVolunteersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lbDescription.setText("");
            tfCSV.setText("");
        }
    }

    @FXML
    private void btImportActionEvent(ActionEvent actionEvent) {
        if (csv != null) {
            try {
                List<String> dados = Files.readAllLines(Paths.get(csv.getAbsolutePath()), Charset.defaultCharset());
                int id = 0, firstName = 0, lastName = 0, countryCode = 0, gender = 0;
                if (!dados.isEmpty()) {
                    String separador = dados.get(0).contains(";") ? ";" : ",";
                    String[] colunas = dados.get(0).split(separador);
                    int i = 0;
                    for (String coluna : colunas) {
                        switch (coluna.toLowerCase()) {
                            case "volunteerid":
                                id = i;
                                break;
                            case "firstname":
                                firstName = i;
                                break;
                            case "lastname":
                                lastName = i;
                                break;
                            case "countrycode":
                                countryCode = i;
                                break;
                            case "gender":
                                gender = i;
                                break;
                        }
                        i++;
                    }
                    dados.remove(0);
                    for (String dado : dados) {
                        colunas = dado.split(separador);
                        Volunteer volunteer = new Volunteer();
                        volunteer.setVolunteerId(Integer.parseInt(colunas[id]));
                        volunteer.setFirstName(colunas[firstName]);
                        volunteer.setLastName(colunas[lastName]);
                        volunteer.setGender(new GenderDAO().pegarPorId(colunas[gender].length() > 1 ? colunas[gender] : colunas[gender].replace("M", "Male").replace("F", "Female")));
                        volunteer.setCountryCode(new CountryDAO().pegarPorId(colunas[countryCode]));
                        if (new VolunteerDAO().pegarPorId(volunteer.getVolunteerId()) == null) {
                            new VolunteerDAO().cadastrar(volunteer);
                        } else {
                            new VolunteerDAO().editar(volunteer);
                        }
                    }
                    FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.janelaAntiga);
                    Message.show("Imported sucessfully", "All data were successfully imported!", Message.Tipo.INFORMATION);
                }
            } catch (IOException ex) {
                Logger.getLogger(ImportVolunteersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Message.show("Select the file", "It is mandatory selecting the file to import the data.", Message.Tipo.ERROR);
        }
    }

    @FXML
    private void btCancelActionEvent(ActionEvent actionEvent) {
        FxMananger.inserirPainel((ScrollPane) apPrincipal.getParent().getParent().getParent(), GerenciarJanela.Janela.VOLUNTEERMANAGER);
    }
}
