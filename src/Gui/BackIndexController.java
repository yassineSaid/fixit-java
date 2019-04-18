/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.ServiceService;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class BackIndexController implements Initializable {

    @FXML
    private Button espaceOutil;
    @FXML
    private Button espaceReclamation;
    @FXML
    private Button service;
    @FXML
    private Button EspaceProd;
    private User user;
    @FXML
    private Button espaceAvis;
    @FXML
    private Button accueil;
    @FXML
    private Button espaceUtilisateurs;

    public Button getEspaceOutil() {
        return espaceOutil;
    }

    public void setEspaceOutil(Button espaceOutil) {
        this.espaceOutil = espaceOutil;
    }

    public Button getEspaceReclamation() {
        return espaceReclamation;
    }

    public void setEspaceReclamation(Button espaceReclamation) {
        this.espaceReclamation = espaceReclamation;
    }

    public Button getService() {
        return service;
    }

    public void setService(Button service) {
        this.service = service;
    }

    public Button getEspaceProd() {
        return EspaceProd;
    }

    public void setEspaceProd(Button EspaceProd) {
        this.EspaceProd = EspaceProd;
    }

    public Button getEspaceAvis() {
        return espaceAvis;
    }

    public void setEspaceAvis(Button espaceAvis) {
        this.espaceAvis = espaceAvis;
    }

    public Button getAccueil() {
        return accueil;
    }

    public void setAccueil(Button accueil) {
        this.accueil = accueil;
    }

    public Button getEspaceUtilisateurs() {
        return espaceUtilisateurs;
    }

    public void setEspaceUtilisateurs(Button espaceUtilisateurs) {
        this.espaceUtilisateurs = espaceUtilisateurs;
    }
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //accueil.requestFocus();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void espaceService(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceServiceBack.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceServiceBackController controller = fxmlLoader.<EspaceServiceBackController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void espaceOutil(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceOutilBack.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceOutilBackController controller = fxmlLoader.<EspaceOutilBackController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    @FXML
    private void espaceReclamation(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/reclamationBack.fxml"));
            Parent Rec = fxmlLoader.load();
            ReclamationBackController controller = fxmlLoader.<ReclamationBackController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    @FXML
    private void espaceProduit(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceProduitBack.fxml"));
            Parent Rec = fxmlLoader.load();
            //EspaceProduitBackController controller = fxmlLoader.<EspaceProduitBackController>getController();
            //controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/login.fxml"));
            Parent back = fxmlLoader.load();
            Scene scene = new Scene(back);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void espaceUtilisateursAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceUtilisateursBack.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceUtilisateursBackController controller = fxmlLoader.<EspaceUtilisateursBackController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void espaceAvis(ActionEvent event) {
         try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceAvisBack.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceAvisBackController controller = fxmlLoader.<EspaceAvisBackController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    @FXML
    private void accueilAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/BackAccueil.fxml"));
            Parent Rec = fxmlLoader.load();
            BackAccueilController controller = fxmlLoader.<BackAccueilController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

}
