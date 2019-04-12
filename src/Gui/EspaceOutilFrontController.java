/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image =new Image("file:/wamp64/www/fixit/web/uploads/images/categorieOutil/electricité", 120, 120, false, false);
        ImageView ip = new ImageView(image);
        VBox root = new VBox(10, ip);
        // TODO
    }    
    
}
