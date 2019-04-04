/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Avis;
import Entities.User;
import Services.AvisService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AvisFrontController implements Initializable {

    private User user;
    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private Rating rating;
    @FXML
    private ComboBox<String> satisfaction;
    @FXML
    private TextField description;
    @FXML
    private Button noter;
    @FXML
    private Button modifier_note;
    
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
            Avis avis = new Avis();
            AvisService avisSer= new AvisService();
            avis=avisSer.getUserAvis(this.getUser().getId());
            System.out.println(avis.getId());
            if(avis==null)
            {
                ObservableList<String> list = FXCollections.observableArrayList("Non Satisfait","Moyennement Satisfait","Totalement Satisfait");
             satisfaction.setItems(list);
                modifier_note.setVisible(false);
                rating.setRating(0.0);
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
            }
            else
            {
                noter.setVisible(false);
                modifier_note.setVisible(true);
                rating.setRating((double)avis.getNote());
                satisfaction.setValue(avis.getSatisfaction());
                description.setText(avis.getDescription());
            }
             
	    });
    }    

    @FXML
    private void ratingchoose(MouseEvent event) {
        System.out.print((int)rating.getRating());
    }

    @FXML
    private void NoterAction(ActionEvent event) {
        AvisService avisServ= new AvisService();
        Avis avis= new Avis(description.getText(),(int)rating.getRating(),satisfaction.getValue().toString(),this.getUser());
        avisServ.ajouterAvis(avis);
        
         
    }

    @FXML
    private void modifierNoteAction(ActionEvent event) {
    }
    
    
    
}
