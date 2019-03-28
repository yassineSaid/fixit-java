package Gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entities.User;
import Services.Programme;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FrontController implements Initializable{
	@FXML
	private Label label;
	private User user;
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(() -> {
	    	label.setText(user.getUsername());
	    });
	}
}
