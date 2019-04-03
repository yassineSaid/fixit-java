/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView photo;
    
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
                if(user!=null)
                {
                    userName.setText(Utils.upperCaseFirst(user.getFirstname())+" "+Utils.upperCaseFirst(user.getLastname()));
                    loadImage();
                    
                }
                    
                
	    });
    }    
    @FXML
    private void loadImage()
    {
        File currDir = new File(System.getProperty("user.dir", "."));
        System.out.println(currDir.toPath().getRoot().toString());
        String path="file:"+currDir.toPath().getRoot().toString()+"wamp64\\www\\fixit\\web\\uploads\\images\\user\\"+user.getImage();
        Image image = new Image(path);  
        photo.setImage(image);
        Image img = photo.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = photo.getFitWidth() / img.getWidth();
            double ratioY = photo.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            photo.setX((photo.getFitWidth() - w) / 2);
            photo.setY((photo.getFitHeight() - h) / 2);

        }
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
            System.out.println(ex);
        }
    }
    @FXML
    private void profilAction(ActionEvent event) {
        try {
			
		 			 
		 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/profil.fxml"));   
		 	Parent Rec = fxmlLoader.load();          
		 	profilController controller = fxmlLoader.<profilController>getController();
		 	controller.setUser(this.getUser());
                        Scene scene = new Scene(Rec);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
