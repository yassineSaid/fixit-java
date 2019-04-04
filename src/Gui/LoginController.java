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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errot.setVisible(false);

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

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/backIndex.fxml"));
                    Parent back = fxmlLoader.load();
                    Scene scene = new Scene(back);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.show();
                    stage.setScene(scene);
                } else {
                    //System.out.println(U.getRoles());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/frontIndex.fxml"));
                    Parent front = fxmlLoader.load();
                    FrontIndexController controller = fxmlLoader.<FrontIndexController>getController();
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
            ActionEvent ae=new ActionEvent(event.getSource(),event.getTarget());
            connexionAction(ae);
        }
    }

}
