/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.view;

import br.com.MarathonWS.control.FxMananger;
import br.com.MarathonWS.control.dao.CheckpointDAO;
import br.com.MarathonWS.control.dao.EventDAO;
import br.com.MarathonWS.control.dao.EventTypeDAO;
import br.com.MarathonWS.model.entity.Checkpoint;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.util.Sessao;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author OCTI01
 */
public class InteractiveMapController implements Initializable {

    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Circle ciStart;
    @FXML
    private Circle ciMid;
    @FXML
    private Circle ciLast;
    @FXML
    private Circle ci1;
    @FXML
    private Circle ci2;
    @FXML
    private Circle ci3;
    @FXML
    private Circle ci4;
    @FXML
    private Circle ci5;
    @FXML
    private Circle ci6;
    @FXML
    private Circle ci7;
    @FXML
    private Circle ci8;
    @FXML
    private ImageView ivMap;

    private PopOver poDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Circle> circles = new ArrayList<>();
        circles.add(ci1);
        circles.add(ci2);
        circles.add(ci3);
        circles.add(ci4);
        circles.add(ci5);
        circles.add(ci6);
        circles.add(ci7);
        circles.add(ci8);
        poDesc = new PopOver();
        int i = 1;
        for (Circle circle : circles) {
            Checkpoint checkpoint = new CheckpointDAO().pegarPorId(i);
            circle.setOnMouseReleased((MouseEvent me) -> {
                poDesc.setContentNode(FxMananger.carregarComponente("ViewCheckPoint", checkpoint));
                poDesc.setDetached(true);
                poDesc.show(ivMap);
            });
            i++;
        }
        ciStart.setOnMouseReleased(new StartClicked(new EventDAO().pegarPorMaratonaType(Sessao.marathon, new EventTypeDAO().pegarPorId("FM"))));
        ciMid.setOnMouseReleased(new StartClicked(new EventDAO().pegarPorMaratonaType(Sessao.marathon, new EventTypeDAO().pegarPorId("HM"))));
        ciLast.setOnMouseReleased(new StartClicked(new EventDAO().pegarPorMaratonaType(Sessao.marathon, new EventTypeDAO().pegarPorId("FR"))));
    }

    public class StartClicked implements EventHandler<MouseEvent> {

        private Event event;

        public StartClicked(Event event) {
            this.event = event;
        }

        @Override
        public void handle(MouseEvent event) {
            poDesc.setContentNode(FxMananger.carregarComponente("ViewCheckPoint", this.event));
            poDesc.setDetached(true);
            poDesc.show(ivMap);
        }
    }
}
