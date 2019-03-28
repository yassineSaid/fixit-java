package Gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.User;
import Services.Programme;
import Services.UserService;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class MainController {
	
	
	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	@FXML
	private Button connexion;
	@FXML
	private Text error;
	

	
	
	// Event Listener on Button[#connexion].onAction
	@FXML
	public void connexionAction(ActionEvent event) {
		error.setVisible(false);
		UserService us=new UserService();
		System.out.println();
		if (us.checkUser(login.getText(), password.getText()))
		{
			 try {
				 	User U=us.connect(login.getText());
				 	//System.out.println(U.getRoles());
				 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/Front.fxml"));   
				 	Parent front = fxmlLoader.load();          
				 	FrontController controller = fxmlLoader.<FrontController>getController();
				 	controller.setUser(U);
				 	
	                Scene scene = new Scene(front);
	               
	                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	                stage.show();
	                stage.setScene(scene);
	                
	            } catch (IOException ex) {
	                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	            }
		}
		else
		{
			error.setVisible(true);
		}
	}
}
