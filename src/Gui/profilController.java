package Gui;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class profilController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private TabPane Tabwidget;
    @FXML
    private Tab interfaceAjout;
    @FXML
    private AnchorPane ajouterRec;
    @FXML
    private Button ajouter_Rec;
    @FXML
    private Label email;
    @FXML
    private Label username;
    @FXML
    private ImageView photo;
    
    private User user;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

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
                        username.setText(user.getUsername());
                        nom.setText(user.getLastname());
                        prenom.setText(user.getFirstname());
                        email.setText(user.getEmail());
	    });
    }    
    @FXML
    private void modifierAction(ActionEvent event) {
        System.out.println("clicked");
        UserService us=new UserService();
        us.modifierNomPrenom(user, nom.getText(), prenom.getText());
        user=us.connect(user.getUsername());
        frontIndexController.setUser(user);
        frontIndexController.initialize(null, null);
     
    }
    
}
