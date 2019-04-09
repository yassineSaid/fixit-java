/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Button EspaceProd;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            System.out.println(ex);
        }
    }

    @FXML
    private void espaceProduit(ActionEvent event) {
                 try {
			
		 			 
		 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/CategorieProduit.fxml"));   
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
