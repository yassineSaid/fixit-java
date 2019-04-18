/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.Candidature;
import Entities.User;
import Services.AnnonceService;
import Services.CandidatureService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achref Bannouri
 */
public class ModifierCandidatureController implements Initializable {
    String msg = "";
    @FXML
    private TextArea message;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private FrontIndexController frontIndexController;
    private User user;
    	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
        private static int Idcn; 

    public static int getIdcn() {
        return Idcn;
    }

    public static void setIdcn(int Idcn) {
        ModifierCandidatureController.Idcn = Idcn;
    }
    private static int Idan;

    public static int getIdan() {
        return Idan;
    }

    public static void setIdan(int Idan) {
        ModifierCandidatureController.Idan = Idan;
    }
    
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null); 
        });
        }
    
    
        @FXML
	    private void RetournerAction(ActionEvent event) {
            try {
	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/annonceFront.fxml"));   
		 	Parent An = Loader.load();          
		 	AnnonceFrontController controller = Loader.<AnnonceFrontController>getController();
		 	controller.setUser(this.getUser());
                        //AjouterCandidatureController.setIdannonce(DetailAnnonceController.getIdannonce());
                        Scene scene = new Scene(An);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
	            } catch (IOException ex) {
	                Logger.getLogger(ModifierCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
	            }
 
	    }
            
            
            
                @FXML
    private void ModifierAction(ActionEvent event){
                boolean erreur = false;
        CandidatureService sc = CandidatureService.getInstance();
        Vector<String> erreurs = new Vector<String>();
        CandidatureService sa = CandidatureService.getInstance();
        if (!sc.validEmail(email.getText())) {
            erreur = true;
            erreurs.add("L'email est invalide");
        }
        if (!sc.validTel(tel.getText())) {
            erreur = true;
            erreurs.add("Le numéro de téléphone est invalide");
        }
        if (erreur) {
            msg = "";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Postuler");
            alert.setHeaderText(null);
            erreurs.forEach(m -> msg += m + "\n");
            alert.setContentText(msg);
            alert.showAndWait();
            erreurs.clear();
        } else {
        
        Alert a1 = new Alert(Alert.AlertType.WARNING);
                    a1.setTitle("Modifier la candidature");
                    a1.setContentText("vous voulez vraiment mofidier cette candidature?");
                    Optional<ButtonType> result = a1.showAndWait();
                
                    if (result.get() == ButtonType.OK) {
                String etat = "en attente";
                long millis=System.currentTimeMillis();  
                Date date=new Date(millis); 
        Candidature c = new Candidature (
            Idcn,
            Idan,
            message.getText(),
            etat,
            email.getText(),
            tel.getText(),                  
            date,
            user.getId()

                                         );
                          sa.modifierCandidature(c,Idcn);                    
                    }
                    }
    }

}
    

