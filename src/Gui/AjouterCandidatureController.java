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
import Services.MailService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Achref Bannouri
 */
public class AjouterCandidatureController implements Initializable {
    String msg = "";
    @FXML
    private TextArea message;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private FrontIndexController frontIndexController;
    Image image=new Image(getClass().getResourceAsStream("/Resources/tik.png"));
    private User user;   	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
        private Annonce annonce;

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    
        private static  int idannonce;

    public static int getIdannonce() {
        return idannonce;
    }

    public static void setIdannonce(int idannonce) {
        AjouterCandidatureController.idannonce = idannonce;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(idannonce);
        Platform.runLater(() -> {
        frontIndexController.setUser(user);
        frontIndexController.initialize(null, null); });
    }    
    
    @FXML
    private void AjouterAction (ActionEvent event) throws Exception {
        boolean erreur = false;
        CandidatureService sc = CandidatureService.getInstance();
        Vector<String> erreurs = new Vector<String>();
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

            	
                String etat = "en attente";
                long millis=System.currentTimeMillis();  
                Date date=new Date(millis);
                                 
                Candidature c = new Candidature ( 
                  idannonce,
                  message.getText(),
                  etat,
                  email.getText(),
                  tel.getText(),
                  date,
                  user.getId()                  
                );                
                sc.ajouterCandidature(c);   
                Notifications notificationBuilder = Notifications.create().title("Notification").text("Votre Candidature a été ajouté avec succés").graphic(new ImageView(image)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                try {
                AnnonceService sa = AnnonceService.getInstance();
                ResultSet r = sa.AnnonceUser(idannonce);
                String msg = "Vous avez reçu une candidature pour votre annonce ";
                String sbj = "Nouvelle Candidature";
                String to = r.getString("email");
                MailService ms = new MailService(to,sbj,msg);
                ms.sendEmail();                


                } catch (SQLException ex) {
	            Logger.getLogger(AjouterCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
	        }
                try {
                        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/annonceFront.fxml"));   
                        Parent An  = Loader.load(); 

                        AnnonceFrontController controller = Loader.<AnnonceFrontController>getController();
		 	controller.setUser(this.getUser());                                        
                        Scene scene = new Scene(An);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                        stage.setScene(scene);                  
        } catch (IOException ex) {
            Logger.getLogger(AjouterCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
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
	                Logger.getLogger(AjouterCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
	            }
 
	    }
                   
}
