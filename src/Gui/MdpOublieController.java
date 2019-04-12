/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    private Button confirmerEmail1;
    private String codeV;
    private String message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        code.setVisible(false);
        confirmerCode.setVisible(false);
        mdp.setVisible(false);
        mdpConfirmation.setVisible(false);
        confirmerMdp.setVisible(false);
        erreurEmail.setVisible(false);
        Platform.runLater(() -> {
        });
    }

    @FXML
    private void confirmerEmailAction(ActionEvent event) {
        UserService us = new UserService();
        erreurEmail.setVisible(false);
        if (!us.checkEmail(email.getText())) {
            erreurEmail.setVisible(true);
        } else {
            try {
                codeV = us.recupPassword(email.getText());
                confirmerEmail.setDisable(true);
                email.setDisable(true);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Récupération du mot de passe");
                alert.setHeaderText("Un code de confirmation sera envoyé sur l'adresse e-mail que vous nous avez communiqués");
                alert.setContentText("Entrez le code que vous avez reçu dans le champ adéquat");
                code.setVisible(true);
                confirmerCode.setVisible(true);
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println(ex);
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite, veuillez réessayer");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void annulerAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/login.fxml"));
            Parent front = fxmlLoader.load();
            LoginController controller = fxmlLoader.<LoginController>getController();
            Scene scene = new Scene(front);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException iOException) {
        }
    }

    @FXML
    private void verifierCode(ActionEvent event) {
        if (code.getText().equals(codeV)) {
            code.setDisable(true);
            confirmerCode.setDisable(true);
            mdp.setVisible(true);
            mdpConfirmation.setVisible(true);
            confirmerMdp.setVisible(true);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le code de vérification que vous avez entré est incorrect");
            alert.showAndWait();
        }
    }

    @FXML
    private void confirmationMdp(ActionEvent event) {
        UserService us = new UserService();
        Vector<String> erreurs = new Vector<String>();
        boolean erreur = false;
        if (!mdp.getText().equals(mdpConfirmation.getText())) {
            erreur = true;
            erreurs.add("Les mots de passes ne correspondent pas");
        }
        if (mdp.getText().length() < 4) {
            erreur = true;
            erreurs.add("Le mot de passe doit dépasser 4 caractères");
        }
        if (erreur) {
            message = "";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Récupération du mot de passe");
            alert.setHeaderText(null);
            erreurs.forEach(m -> message += m + "\n");
            alert.setContentText(message);
            alert.showAndWait();
            erreurs.clear();
        } else {
            us.changerMdp(email.getText(), mdp.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Récupération du mot de passe");
            alert.setHeaderText(null);
            alert.setContentText("Votre mot de passe a été modifier, veuillez vous reconnectez à nouveau");
            alert.showAndWait();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/login.fxml"));
                Parent front = fxmlLoader.load();
                LoginController controller = fxmlLoader.<LoginController>getController();
                Scene scene = new Scene(front);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.show();
                stage.setScene(scene);
            } catch (IOException iOException) {
            }
        }
    }

}
