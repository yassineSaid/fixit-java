/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieOutil;
import Services.CategorieOutilService;
import Services.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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
            Connection c = Connexion.getInstance().getCon();
            id.setCellValueFactory(new PropertyValueFactory<CategorieOutil, String>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<CategorieOutil, String>("nom"));
            logo.setCellValueFactory(new PropertyValueFactory<CategorieOutil, String>("logo"));

            CategorieOutilService categorie = new CategorieOutilService();
            try {
                table.setItems(categorie.afficherCategorie());
                // TODO
            } catch (SQLException ex) {
                Logger.getLogger(CategorieProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        CategorieOutilService categorie = new CategorieOutilService();
        CategorieOutil c = table.getSelectionModel().getSelectedItem();
        categorie.supprimerCategorie(c.getId());
        System.out.println("categorie supprimer");

    }
}
