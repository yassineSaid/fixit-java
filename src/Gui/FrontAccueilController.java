/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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
        final String IDLE_STYLE = " -fx-background-color:  rgba(226,226,226,0.7); -fx-background-radius: 8,7,6;";
        final String HOVERED_STYLE = "-fx-background-color: rgba(255,255,255,0.3);";

        UserService us = new UserService();
        users.setItems(us.getUsersVbox(user.getId(), rechercher.getText()));

        users.setCellHeight(200);
        users.setCellWidth(150);
        users.setCellFactory(gridView -> new GridCell<VBox>() {
            public void updateItem(VBox item, boolean empty) {
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //setText(item.getName());
                    item.setCursor(Cursor.HAND);
                    item.setStyle(IDLE_STYLE);
                    item.setSpacing(10);
                    item.setOnMouseEntered(e -> item.setStyle(HOVERED_STYLE));
                    item.setOnMouseExited(e -> item.setStyle(IDLE_STYLE));
                    item.setOnMouseClicked((event) -> {
                        VBox v = (VBox) event.getSource();
                        System.out.println(v.getId());
                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/ProfilUser.fxml"));
                            Parent Rec = fxmlLoader.load();
                            ProfilUserController controller = fxmlLoader.<ProfilUserController>getController();
                            controller.setUser(user);
                            controller.setId(v.getId());
                            Scene scene = new Scene(Rec);

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.show();
                            stage.setScene(scene);

                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
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
