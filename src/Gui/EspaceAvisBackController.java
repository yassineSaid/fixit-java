/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.AvisService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EspaceAvisBackController implements Initializable {

    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rating.setMaxHeight(1);
        EventHandler<MouseEvent> handler = MouseEvent::consume;
        rating.addEventFilter(MouseEvent.ANY, handler);
        AvisService avisServ = new AvisService();
        rating.setRating(avisServ.moyenneNotes());
        Platform.runLater(() -> {

        });
    }
}
