/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class LoginController implements Initializable {

    @FXML
    private Button connexion;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label errot;
    @FXML
    private Button connexion1;
    @FXML
    private Button inscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errot.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                login.requestFocus();
            }
        });
    }

    @FXML
    private void connexionAction(ActionEvent event) {
        errot.setVisible(false);
        UserService us = new UserService();
        System.out.println();
        if (us.checkUser(login.getText(), password.getText())) {
            try {
                User U = us.connect(login.getText());
                if (U.getRoles() == "admin") {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/BackAccueil.fxml"));
                    Parent front = fxmlLoader.load();
                    BackAccueilController controller = fxmlLoader.<BackAccueilController>getController();
                    controller.setUser(U);
                    Scene scene = new Scene(front);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.show();
                    stage.setScene(scene);
                } else {
                    //System.out.println(U.getRoles());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/FrontAccueil.fxml"));
                    Parent front = fxmlLoader.load();
                    FrontAccueilController controller = fxmlLoader.<FrontAccueilController>getController();
                    controller.setUser(U);
                    Scene scene = new Scene(front);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.show();
                    stage.setScene(scene);
                    
                }

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            errot.setText("Verifier vos données");
            errot.setVisible(true);
        }
    }

    @FXML
    private void keyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
            connexionAction(ae);
        }
    }

    @FXML
    private void mdpOublieAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/MdpOublie.fxml"));
            Parent front = fxmlLoader.load();
            MdpOublieController controller = fxmlLoader.<MdpOublieController>getController();
            Scene scene = new Scene(front);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException iOException) {
        }
    }

    @FXML
    private void inscriptionAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/Inscription.fxml"));
            Parent front = fxmlLoader.load();
            InscriptionController controller = fxmlLoader.<InscriptionController>getController();
            Scene scene = new Scene(front);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException iOException) {
        }
    }

}
