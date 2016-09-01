/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.FxMananger.Tipo;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author OCTI01
 */
public class Run extends Application {

    @Override
    public void start(Stage primaryStage) {
        FxMananger.showWindow(FxMananger.carregarComponente("Principal"), "Início", Tipo.EXIT_ON_CLOSE, Tipo.MAXIMIZADO).show();
    }

    public void pegarImagem() {
        getClass();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
