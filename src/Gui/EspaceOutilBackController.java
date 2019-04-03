/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieOutil;
import Services.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SL-WASSIM
 */
public class EspaceOutilBackController implements Initializable {

    @FXML
    private TableView<CategorieOutil> table;
    @FXML
    private TableColumn<CategorieOutil, String> id;
    @FXML
    private TableColumn<CategorieOutil, String> nom;
    @FXML
    private TableColumn<CategorieOutil, String> logo;
    
    private ObservableList<CategorieOutil> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> {
            try{
    Connection c=Connexion.getInstance().getCon();
    data=FXCollections.observableArrayList();
    ResultSet rs=c.createStatement().executeQuery("select * from categorie_outils");
    while(rs.next()){
        data.add(new CategorieOutil(rs.getInt(1),rs.getString(2),rs.getString(3)));
    }
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
    
    table.setItems(null);
    table.setItems(data);
           }
        catch(Exception e){
            System.out.println(e);
        }

	    });
    }    
    
}
