/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import Services.ReclamationService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReclamationBackController implements Initializable {

    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, User> UserReclamant;
    @FXML
    private TableColumn<Reclamation, User> UserReclame;
    @FXML
    private TableColumn<Reclamation, Date> dateReclamation;
    @FXML
    private TableColumn<Reclamation, Service> Service;
    @FXML
    private TableColumn<Reclamation, Date> dateRealisation;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private ComboBox<?> typeReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
           UserReclamant.setCellValueFactory(new PropertyValueFactory<>("userReclamant"));
           UserReclame.setCellValueFactory(new PropertyValueFactory<>("userReclame"));
           dateReclamation.setCellValueFactory(new PropertyValueFactory<>("DateReclamation"));
           Service.setCellValueFactory(new PropertyValueFactory<>("idServiceRealise"));
           dateRealisation.setCellValueFactory(new PropertyValueFactory<>("dateRealisation"));
           objet.setCellValueFactory(new PropertyValueFactory<>("Objet"));
           description.setCellValueFactory(new PropertyValueFactory<>("Description"));
           ReclamationService recServ = new ReclamationService();
           ReclamationService r= new ReclamationService();
           ObservableList<Reclamation> listRec = FXCollections.observableArrayList();
           listRec=recServ.getAllReclamation();
           tableReclamation.setItems(listRec);
           ObservableList<Reclamation> listType = FXCollections.observableArrayList();
           
            
        });
    }    

    @FXML
    private void typeReclamationSelected(ActionEvent event) {
    }
    
}
