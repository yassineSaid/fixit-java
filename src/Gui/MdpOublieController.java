/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class MdpOublieController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField code;
    @FXML
    private TextField mdp;
    @FXML
    private TextField mdpConfirmation;
    @FXML
    private Button confirmerEmail;
    @FXML
    private Button confirmerCode;
    @FXML
    private Button confirmerMdp;
    @FXML
    private Label erreurEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            code.setVisible(false);
            confirmerCode.setVisible(false);
            mdp.setVisible(false);
            mdpConfirmation.setVisible(false);
            confirmerMdp.setVisible(false);
            erreurEmail.setVisible(false);
        });
    }

    @FXML
    private void confirmerEmailAction(ActionEvent event) {
        UserService us = new UserService();
        erreurEmail.setVisible(false);
        if (!us.checkEmail(email.getText())) {
            erreurEmail.setVisible(true);
        } else {
            confirmerEmail.setDisable(true);
            email.setDisable(true);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Récupération du mot de passe");
            alert.setHeaderText("Un code de confirmation sera envoyé sur l'adresse e-mail que vous nous avez communiqués");
            alert.setContentText("Entrez le code que vous avez reçu dans le champ adéquat");
            code.setVisible(true);
            confirmerCode.setVisible(true);
            alert.showAndWait();
        }
    }

}
