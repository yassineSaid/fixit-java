/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.categorie_produit;
import Services.CategorieProduit;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ali Saidani
 */
public class CategorieProduitController implements Initializable {
     private CategorieProduit crud;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField descriptionfield;
    @FXML
    private Button ajoutbtn;
    @FXML
    private TextField imagefield;
    @FXML
    private TableView<categorie_produit> tabeCategorie;
    @FXML
    private TableColumn<categorie_produit, String> Nomfield;
    @FXML
    private TableColumn<categorie_produit, String> descrfield;
    @FXML
    private TableColumn<categorie_produit, String> imagfield;
    @FXML
    private Button modbtn;
    @FXML
    private Button supbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modbtn.setVisible(false);
        supbtn.setVisible(false);
        ajoutbtn.setVisible(true);
     
             
        Nomfield.setCellValueFactory(new PropertyValueFactory<categorie_produit,String>("Nom"));
     descrfield.setCellValueFactory(new PropertyValueFactory<categorie_produit,String>("description"));
     imagfield.setCellValueFactory(new PropertyValueFactory<categorie_produit,String>("image"));
     
       CategorieProduit crud = new CategorieProduit();
        try {
            tabeCategorie.setItems(crud.getAllCategorie());
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CategorieProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }

    @FXML
    private void AjouteClicked(ActionEvent event) {
        crud = new CategorieProduit();

        categorie_produit ct = new categorie_produit();
        System.out.println(ct);

        ct.setNom(nomfield.getText());
        ct.setDescription(descriptionfield.getText());
        ct.setImage(imagefield.getText());
        crud.ajouterCategorie(ct);
        System.out.println("categorie ajoutée");
       initialize(null,null);
    }
    private categorie_produit c = new categorie_produit();

    @FXML
    private void itemSelected(MouseEvent event) {
        modbtn.setVisible(true);
        supbtn.setVisible(true);
        ajoutbtn.setVisible(false);

        c = tabeCategorie.getSelectionModel().getSelectedItem();

        nomfield.setText(c.getNom());
        descriptionfield.setText(c.getDescription());
        imagefield.setText(c.getImage());

    }

    @FXML
    private void supprimeClicked(ActionEvent event) {
        crud = new CategorieProduit();
        c = tabeCategorie.getSelectionModel().getSelectedItem();
        crud.supprimerCategorie(c);
        System.out.println("categorie supprimer");
         initialize(null,null);
    }

    @FXML
    private void ModifierClicked(ActionEvent event) {
        crud = new CategorieProduit();

        c = tabeCategorie.getSelectionModel().getSelectedItem();
        System.out.println(c);

        c.setNom(nomfield.getText());
        c.setDescription(descriptionfield.getText());
        c.setImage(imagefield.getText());
        crud.modifierCategorie(c);
        System.out.println("categorie modifier");
         initialize(null,null);
    }

}
