/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Entities.User;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DetailReclamationFrontController implements Initializable {

    @FXML
    private Label user;
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
    private ProgressBar progressRec;
    
    private User userc;
    
    private Reclamation reclamation;
    @FXML
    private Label etape;
    @FXML
    private Button retour;

    public User getUserc() {
        return userc;
    }

    public void setUserc(User userc) {
        this.userc = userc;
    }

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
                user.setText(this.userc.getUsername());
                dateReclamation.setText(this.reclamation.getDateReclamation().toString());
                userReclame.setText(this.reclamation.getUserReclame().getUsername());
                service.setText(this.reclamation.getIdServiceRealise().getNom());
                description.setText(this.reclamation.getDescription());
                objet.setText(this.reclamation.getObjet());
                if(this.reclamation.getSeen()==0)
                {
                    progressRec.setProgress(0.02);
                    etape.setText("Votre reclamation est envoyé avec succés elle est en cours de traitement");
                }
                if(this.reclamation.getSeen()==1)
                {
                    progressRec.setProgress(0.5);
                    etape.setText("Votre reclamation est vu par l'un de nos administrateur elle sera bientôt traité");
                }
                if(reclamation.getSeen()==1 && reclamation.getTraite()==1)
                {
                    progressRec.setProgress(1.0);
                    etape.setText("Votre reclamation bien traité");
                }
	    });
    }    

    @FXML
    private void Retour(ActionEvent event) {
         try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/reclamationFront.fxml"));
            Parent Rec = fxmlLoader.load();
            reclamationFront controller = fxmlLoader.<reclamationFront>getController();
            controller.setUser(this.getUserc());
            
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
