/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author SL-WASSIM
 */
public class EspaceOutilFrontController implements Initializable {
    @FXML
    private FrontIndexController frontIndexController;

    @FXML
    private VBox root;
    @FXML
    private ImageView ip;
    
    private User user;

     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
        Image image =new Image("file:/wamp64/www/fixit/web/uploads/images/categorieOutil/electricité", 120, 120, false, false);
         ip = new ImageView(image);
         root = new VBox(10, ip);
        });
    }    
    
}
