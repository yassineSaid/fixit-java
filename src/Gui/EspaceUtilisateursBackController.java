/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class EspaceUtilisateursBackController implements Initializable {

    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colPseudo;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private ImageView photo;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label pseudo;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Label adresse;
    @FXML
    private Label ville;
    
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            refreshAction();
            afficherTableAction();
            System.out.println(user.getId());
        });
    }    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
   @FXML
   public void afficherTableAction()
   {
       UserService us=new UserService();
       colNom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
       colPrenom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
       colPseudo.setCellValueFactory(new PropertyValueFactory<>("username"));
       colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
       colRole.setCellValueFactory(new PropertyValueFactory<>("roles"));
       tableUser.setItems(us.getUsers());
   }
   @FXML
   public void refreshAction()
   {
       photo.setVisible(false);
       nom.setVisible(false);
       prenom.setVisible(false);
       pseudo.setVisible(false);
       email.setVisible(false);
       telephone.setVisible(false);
       adresse.setVisible(false);
       ville.setVisible(false);
       
   }
}
