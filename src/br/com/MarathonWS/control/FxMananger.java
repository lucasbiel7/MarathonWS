/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control;

import br.com.MarathonWS.control.GerenciarJanela.Janela;
import br.com.MarathonWS.model.util.Sessao;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author OCTI01
 */
public class FxMananger {

    public enum Tipo {
        MAXIMIZADO, RESIZABLE, FULLSCREEN, EXIT_ON_CLOSE;

        @Override
        public String toString() {
            return super.toString(); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public static final String VIEW = "/br.com.MarathonWS.control/".replace(".", "/").replace("control", "view");
    public static String title = Sessao.marathon.getMarathonName();

    public static Parent carregarComponente(String FXML) {
        try {
            return FXMLLoader.load(FxMananger.class.getResource(VIEW + FXML + ".fxml"));
        } catch (IOException ex) {
            System.out.println("Error ao carregar FXML");
            System.err.println(ex.getMessage());
            System.out.println(ex.getCause());
            return null;
        }
    }

    public static Parent carregarComponente(String FXML, Object object) {
        Parent parent = carregarComponente(FXML);
        parent.setUserData(object);
        return parent;
    }

    public static Stage showWindow(Parent parent, String title, Tipo... tipos) {
        Stage stage = new Stage();
        stage.setTitle(title);
        for (Tipo tipo : tipos) {
            switch (tipo) {
                case MAXIMIZADO:
                    stage.setMaximized(true);
                    break;
                case RESIZABLE:
                    stage.setResizable(true);
                    break;
                case FULLSCREEN:
                    stage.setFullScreen(true);
                    break;
                case EXIT_ON_CLOSE:
                    stage.setOnCloseRequest((WindowEvent event) -> {
                        Platform.exit();
                        System.exit(0);
                    });
                    break;
            }
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        return stage;
    }

    public static Stage showWindow(Parent parent, String title, Object data, Tipo... tipos) {
        Stage stage = showWindow(parent, title, tipos);
        parent.setUserData(data);
        return stage;
    }

    public static void inserirPainel(ScrollPane scrollPane, Parent parent) {
        Platform.runLater(() -> {
            ((Stage) scrollPane.getScene().getWindow()).setTitle(title);
        });
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(parent);
    }

    public static void inserirPainel(ScrollPane scrollPane, Parent parent, String title) {
        Platform.runLater(() -> {
            ((Stage) scrollPane.getScene().getWindow()).setTitle(FxMananger.title + (title.isEmpty() ? "" : " - ") + title);
        });
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(parent);
    }

    public static void inserirPainel(ScrollPane scrollPane, Janela janela) {
        GerenciarJanela.janelaAntiga = GerenciarJanela.janelaAtual;
        GerenciarJanela.janelaAtual = janela;
        Platform.runLater(() -> {
            ((Stage) scrollPane.getScene().getWindow()).setTitle(FxMananger.title + (title.isEmpty() ? "" : " - ") + janela.getTitulo());
        });
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(carregarComponente(janela.getArquivo()));
    }

    public static void inserirPainel(ScrollPane scrollPane, Janela janela, Object object) {
        GerenciarJanela.janelaAntiga = GerenciarJanela.janelaAtual;
        GerenciarJanela.janelaAtual = janela;
        Platform.runLater(() -> {
            ((Stage) scrollPane.getScene().getWindow()).setTitle(FxMananger.title + (title.isEmpty() ? "" : " - ") + janela.getTitulo());
        });
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(carregarComponente(janela.getArquivo(), object));
    }
}
