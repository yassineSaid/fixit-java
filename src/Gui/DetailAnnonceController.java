/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.User;
import Gui.FrontIndexController;
import Services.AnnonceService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Achref Bannouri
 */
public class DetailAnnonceController implements Initializable  {
            @FXML
            private FrontIndexController frontIndexController;
            @FXML
            private Label description;
            @FXML
            private Label type;
            @FXML
            private Label prix;
            @FXML
            private Label adresse;
            @FXML
            private Label date;
            @FXML
            private Label tel;            
            @FXML
            private Label service; 
            
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
        DetailAnnonceController.idannonce = idannonce;
    }


    

 
    
    private User user;	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
	          frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
                               System.out.println( user);

               AnnonceService sa = AnnonceService.getInstance();
               System.out.println( annonce);               System.out.println( idannonce);

               
                Annonce a;
              a = sa.DetailAnnonce(idannonce);
              description.setText(a.getDescription());
               type.setText(a.getType());
              prix.setText(String.valueOf(a.getMontant()));
               adresse.setText(a.getAdresse());
               DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
              date.setText(  dateFormat.format(a.getDate()));
              tel.setText(Integer.toString(a.getTel()));
              service.setText(Integer.toString(a.getIdService()));
        });

               


               
                
	           
	    }

	    
	    @FXML
	    private void PostulerAction(ActionEvent event) {
            try {
	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/ajouterCandidature.fxml"));   
		 	Parent An = Loader.load();          
		 	AjouterCandidatureController controller = Loader.<AjouterCandidatureController>getController();
		 	controller.setUser(this.getUser());
                        //AjouterCandidatureController.setIdannonce(DetailAnnonceController.getIdannonce());
                        Scene scene = new Scene(An);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
	            } catch (IOException ex) {
	                Logger.getLogger(DetailAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
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
	                Logger.getLogger(DetailAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
	            }
 
	    }
            
            
    
}
