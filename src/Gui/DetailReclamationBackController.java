/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DetailReclamationBackController implements Initializable {

    @FXML
    private Label dateReclamation;
    @FXML
    private Label userReclame;
    @FXML
    private Label service;
    @FXML
    private Label objet;
    @FXML
    private Label description;
    @FXML
    private Button archive;
    @FXML
    private Button traite;
    @FXML
    private Button retour;
    
    private Reclamation reclamation;

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater(() -> {
                dateReclamation.setText(this.reclamation.getDateReclamation().toString());
                userReclame.setText(this.reclamation.getUserReclame().getUsername());
                service.setText(this.reclamation.getIdServiceRealise().getNom());
                description.setText(this.reclamation.getDescription());
                objet.setText(this.reclamation.getObjet());
                ReclamationService recServ = new ReclamationService();
                recServ.vuReclamation(this.reclamation);
                if(this.reclamation.getTraite()==1)
                {
                    traite.setVisible(false);
                }
                if(this.reclamation.getArchive()==1)
                {
                    archive.setVisible(false);
                }
                
	    });
    }    

    @FXML
    private void archiveAction(ActionEvent event) {
        ReclamationService recServ = new ReclamationService();
        recServ.archiverReclamation(this.reclamation);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation Archivé avec succée");
        alert.showAndWait();
    }

    @FXML
    private void traiteAction(ActionEvent event) {
        ReclamationService recServ = new ReclamationService();
        recServ.traiterReclamation(this.reclamation);
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation traité avec succée");
        alert.showAndWait();
    }

    @FXML
    private void retourAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/reclamationBack.fxml"));
            Parent Rec = fxmlLoader.load();
            Scene scene = new Scene(Rec);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
