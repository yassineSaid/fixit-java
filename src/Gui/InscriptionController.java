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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConf;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField zip_code;
    @FXML
    private TextField phone;
    String message="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
        });
    }    

    @FXML
    private void inscriptionAction(ActionEvent event) {
        UserService us=new UserService();
        Vector<String> erreurs=new Vector<String>();
        boolean erreur=false;
        if (us.checkEmail(email.getText()))
        {
            erreur=true;
            erreurs.add("Cet email existe déjà");
        }
        if (us.checkUsername(username.getText()))
        {
            erreur=true;
            erreurs.add("Ce pseudo existe déjà");
        }
        if (!password.getText().equals(passwordConf.getText()))
        {
            erreur=true;
            erreurs.add("Les mots de passes ne correspondent pas");
        }
        if (password.getText().length()<4)
        {
            erreur=true;
            erreurs.add("Le mot de passe doit dépasser 4 caractères");
        }
        if (username.getText().length()<4)
        {
            erreur=true;
            erreurs.add("Le pseudo doit dépasser 4 caractères");
        }
        if (firstname.getText().length()==0)
        {
            erreur=true;
            erreurs.add("Entrez votre prenom");
        }
        if (lastname.getText().length()==0)
        {
            erreur=true;
            erreurs.add("Entrez votre nom");
        }
        if (!us.validEmail(email.getText()))
        {
            erreur=true;
            erreurs.add("L'email est invalide");
        }
        if (erreur)
        {
            message="";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inscription");
            alert.setHeaderText(null);
            erreurs.forEach(m -> message+=m+"\n");
            alert.setContentText(message);
            alert.showAndWait();
            erreurs.clear();
        }
        else
        {
            us.inscription(username.getText(), email.getText(), password.getText(), firstname.getText(), lastname.getText(), address.getText(), zip_code.getText(), city.getText(), phone.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription");
            alert.setHeaderText(null);
            alert.setContentText("Votre inscription a été effectuée avec succès");
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
        //inscription(String username, String email, String password, String firstname, String lastname, String address, String zip_code, String city, int phone)
    }

    @FXML
    private void changerAction(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }
        //verifier((TextField) event.getSource());
    }

    
}
