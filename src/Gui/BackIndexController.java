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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private Button service;

    @FXML
    void espaceService(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/ajouterCategorieService.fxml"));
            Parent Rec = fxmlLoader.load();
            AjouterCategorieServiceController controller = fxmlLoader.<AjouterCategorieServiceController>getController();
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
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
		this.user = user;
	}

    @FXML
    private void espaceReclamation(ActionEvent event) {
          try {
			
		 			 
		 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/reclamationBack.fxml"));   
		 	Parent Rec = fxmlLoader.load();         
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
