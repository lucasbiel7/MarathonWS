/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.Notifications;

/**
 *
 * @author OCTI01
 */
public class Message {

    public enum Tipo {
        WARNING, ERROR, INFORMATION
    }

    public static void show(String title, String msg, Tipo tipo) {
        Notifications notifications = Notifications.create();
        notifications.title(title);
        notifications.text(msg);
        switch (tipo) {
            case ERROR:
                notifications.showError();
                break;
            case WARNING:
                notifications.showWarning();
                break;
            case INFORMATION:
                notifications.showInformation();
                break;
        }
    }

    public static boolean showConfirm(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        return alert.showAndWait().get().equals(ButtonType.YES);
    }
}
