/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Notification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author SL-WASSIM
 */
public class DetailNotificationController implements Initializable {

    @FXML
    private Button confirmer;
    @FXML
    private Button supprimer;
    @FXML
    private Button retour;
    Notification notification;

    
    
    public Notification getNotification() {
        return notification;
        // TODO
    }    

    /**
     * Initializes the controller class.
     */
    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmerAction(ActionEvent event) {
    }

    @FXML
    private void supprimerAction(ActionEvent event) {
    }

    @FXML
    private void retourAction(ActionEvent event) {
    }
    
}
