/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class FrontAccueilController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private GridView<VBox> users;
    @FXML
    private TextField rechercher;

    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
                afficherUsers();
        });
            afficherUsers();
        });
    }

    @FXML
    public void afficherUsers() {
        UserService us = new UserService();
        users.setItems(us.getUsersVbox(user.getId(),rechercher.getText()));
        
        users.setCellHeight(200);
        users.setCellWidth(150);
        users.setCellFactory(gridView -> new GridCell<VBox>(){
            public void updateItem(VBox item, boolean empty) {
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                //setText(item.getName());
                item.setOnMouseClicked((event) -> {
                    VBox v=(VBox) event.getSource();
                    System.out.println(v.getId());
                });
                setGraphic(item);
                
            }

        }
        });
        
    }

    @FXML
    private ImageView loadImage(String imageUser) {
        File currDir = new File(System.getProperty("user.dir", "."));
        if (imageUser != null) {
            String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + imageUser;
            Image image = new Image(path);
            ImageView photo = new ImageView();
            photo.setFitWidth(100);
            photo.setFitHeight(100);
            photo.setImage(image);
            Image img = photo.getImage();
            if (img != null) {
                double w = 0;
                double h = 0;

                double ratioX = photo.getFitWidth() / img.getWidth();
                double ratioY = photo.getFitHeight() / img.getHeight();

                double reducCoeff = 0;
                if (ratioX >= ratioY) {
                    reducCoeff = ratioY;
                } else {
                    reducCoeff = ratioX;
                }

                w = img.getWidth() * reducCoeff;
                h = img.getHeight() * reducCoeff;

                photo.setX((photo.getFitWidth() - w) / 2);
                photo.setY((photo.getFitHeight() - h) / 2);
                return photo;
            }
        }
        return null;
    }
}
