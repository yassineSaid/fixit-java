/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.Candidature;
import Entities.User;
import Services.CandidatureService;
import Services.MailService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achref Bannouri
 */
public class LesCandidaturesController implements Initializable {
    private static int Idannonce;

    public static int getIdannonce() {
        return Idannonce;
    }

    public static void setIdannonce(int Idannonce) {
        LesCandidaturesController.Idannonce = Idannonce;
    }
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    private ObservableList<Candidature> datac = FXCollections.observableArrayList();
            @FXML
            private FrontIndexController frontIndexController;
    
            @FXML
	    private TableView<Candidature> candidatures;
            @FXML
	    private  TableColumn<Candidature, String> message=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, String> email=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, String> tel=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, Date> date=new TableColumn<>();
            
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
                message.setCellValueFactory(new PropertyValueFactory<>("message"));
                email.setCellValueFactory(new PropertyValueFactory<>("email"));
                tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                buildCandidatures(); });
    }    
    
    
        private void buildCandidatures() {
	         CandidatureService sc = CandidatureService.getInstance();
	         datac=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sc.lesCandidatures(Idannonce);
	            while(r.next()){
                        datac.add(new Candidature(r.getInt("id"),r.getInt("id_annonce"),r.getString("message"),
	                		r.getString("etat"),r.getString("email"),r.getString("tel"),r.getDate(7),r.getInt("idUser")
	                		));
	            }
	            
	            candidatures.setItems(datac);
	        } catch (SQLException ex) {
	            Logger.getLogger(LesCandidaturesController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
               @FXML
	    private void ConfirmerAction(ActionEvent event) throws Exception {
	       
	                 try {
                        CandidatureService sc = CandidatureService.getInstance();
                        Candidature c = (Candidature) candidatures.getSelectionModel().getSelectedItem();
                        System.out.println(c.getId());
                        sc.ConfirmerCandidature(c.getId());
                        sc.RefuserCandidature(c.getId(), Idannonce);
                        String msg = "Votre candidature a été acceptée ";
                String sbj = "Candidature acceptée";
                String to = c.getEmail();
                MailService ms = new MailService(to,sbj,msg);
                ms.sendEmail();
	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/annonceFront.fxml"));   
		 	Parent An = Loader.load();          
		 	AnnonceFrontController controller = Loader.<AnnonceFrontController>getController();
		 	controller.setUser(this.getUser());
                        Scene scene = new Scene(An);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
	            } catch (IOException ex) {
	                Logger.getLogger(LesCandidaturesController.class.getName()).log(Level.SEVERE, null, ex);
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
	                Logger.getLogger(LesCandidaturesController.class.getName()).log(Level.SEVERE, null, ex);
	            }
 
	    }
      
} 
