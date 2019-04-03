/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Entities.User;
import Services.CategorieServiceService;
import Services.Connexion;
import Services.ReclamationService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class AjouterCategorieServiceController implements Initializable {

    @FXML
    private TextField nomCat;
    @FXML
    private TextArea descriptionCat;
    @FXML
    private Button ajoutCat;
    @FXML
    private TableColumn idCatAff;
    @FXML
    private TableColumn<CategorieService,String> nomCatAff;
    @FXML
    private TableColumn descriptionCatAff;
    private CategorieServiceService cs= new CategorieServiceService();
    @FXML
    private TableView<CategorieService> categorie;
    private ObservableList<CategorieService> data;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            try{
    Connection c=Connexion.getInstance().getCon();
    data=FXCollections.observableArrayList();
    ResultSet rs=c.createStatement().executeQuery("select * from categorie_Service");
    while(rs.next()){
        data.add(new CategorieService(rs.getInt(1),rs.getString(2),rs.getString(3)));
    }
    idCatAff.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomCatAff.setCellValueFactory(new PropertyValueFactory<>("nom"));
    descriptionCatAff.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    categorie.setItems(null);
    categorie.setItems(data);
           }
        catch(Exception e){
            System.out.println(e);
        }

	    });
        
         
    }    
    
    private User user;
    public User getUser() {
		return user;
	}
    public void setUser(User user) {
		this.user = user;
	}
    
    @FXML
    void ajouterCat(ActionEvent event) {
            CategorieServiceService Cat= new CategorieServiceService();
                CategorieService catServ = new CategorieService(nomCat.getText(),descriptionCat.getText());
		Cat.ajouterCategorie(catServ);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        CategorieServiceService cc =new CategorieServiceService();
        cc.supprimerCategorie(categorie.getSelectionModel().getSelectedItem().getId());
        this.initialize(null, null);
        
        //categorie.getItems().remove(categorie.getSelectionModel().getSelectedItem());
        
    }

    @FXML
    private void modifierNom(TableColumn.CellEditEvent<CategorieService, String> event) {
        CategorieServiceService cs = new CategorieServiceService();
        CategorieService cat=categorie.getSelectionModel().getSelectedItem();
        cat.setNom(event.getNewValue());
        
      this.initialize(null, null);
        
    }

  

    
}
