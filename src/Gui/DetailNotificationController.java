/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieOutil;
import Entities.Notification;
import Services.CategorieOutilService;
import Services.NotificationService;
import Services.SmsOutilService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    @FXML
    BackAccueilController back;        
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
        SmsOutilService sms = new SmsOutilService();
        sms.sendSms("+216"+Integer.toString(notification.getTelephone()));
    }

    @FXML
    private void supprimerAction(ActionEvent event) {
        NotificationService ns = new NotificationService();
        ns.supprimerNotification(notification.getId());
        Image img = new Image(getClass().getResourceAsStream("/Resources/delete.png"));
        Notifications notificationBuilder = Notifications.create().title("Notification").text("La notification est supprimée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.out.println("notification supprimée");
        back.afficherNotifications();
    }

    @FXML
    private void retourAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
