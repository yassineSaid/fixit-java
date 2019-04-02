/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FrontIndexController implements Initializable {

    @FXML
    private Button espaceServ;
    @FXML
    private Button espaceOut;
    @FXML
    private Button espaceProd;
    @FXML
    private Button espaceRec;
    @FXML
    private Label userName;
    
    private User user;
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
	    	userName.setText(user.getFirstname()+" "+user.getLastname());
	    });
    }    

    @FXML
    private void espaceServAction(ActionEvent event) {
    }

    @FXML
    private void espaceOutAction(ActionEvent event) {
    }

    @FXML
    private void espaceProdAction(ActionEvent event) {
    }

    @FXML
    private void espaceRecAction(ActionEvent event) {
        try {
			
		 			 
		 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/ajouterReclamation.fxml"));   
		 	Parent Rec = fxmlLoader.load();          
		 	ajouterReclamationController controller = fxmlLoader.<ajouterReclamationController>getController();
		 	controller.setUser(this.getUser());
                        Scene scene = new Scene(Rec);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
