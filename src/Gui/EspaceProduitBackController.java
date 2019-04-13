/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieProduit;
import Services.CategorieProduitService;
import Services.ImageService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ali Saidani
 */
public class EspaceProduitBackController implements Initializable {

    private CategorieProduitService crud;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField descriptionfield;
    @FXML
    private Button ajoutbtn;
    @FXML
    private TableView<CategorieProduit> tabeCategorie;
    @FXML
    private Button modbtn;
    @FXML
    private Button supbtn;
    @FXML
    private TableColumn<CategorieProduit, String> cat;
    @FXML
    private TableColumn<CategorieProduit, String> desc;
    @FXML
    private TableColumn<CategorieProduit, String> image;
    private String imageee;
    @FXML
    private Button openFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
        modbtn.setDisable(true);
        supbtn.setDisable(true);
        ajoutbtn.setDisable(false);

        cat.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("Nom"));
        desc.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("description"));
        image.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("im"));

        CategorieProduitService crud = new CategorieProduitService();
        try {
            tabeCategorie.setItems(crud.afficherCategorie());
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(EspaceProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    }

    @FXML
    private void AjouteClicked(ActionEvent event) {
        crud = new CategorieProduitService();

        CategorieProduit ct = new CategorieProduit();
        System.out.println(ct);

        ct.setNom(nomfield.getText());
        ct.setDescription(descriptionfield.getText());
        ct.setImage(imageee);
        crud.ajouterCategorie(ct);
        System.out.println("categorie ajoutée");
        initialize(null, null);
    }
    private CategorieProduit c = new CategorieProduit();

    @FXML
    private void itemSelected(MouseEvent event) {
        modbtn.setDisable(false);
        supbtn.setDisable(false);
        ajoutbtn.setDisable(true);

        c = tabeCategorie.getSelectionModel().getSelectedItem();

        nomfield.setText(c.getNom());
        descriptionfield.setText(c.getDescription());

    }

    @FXML
    private void supprimeClicked(ActionEvent event) {
        crud = new CategorieProduitService();
        c = tabeCategorie.getSelectionModel().getSelectedItem();
        crud.supprimerCategorie(c);
        System.out.println("categorie supprimer");
        initialize(null, null);
    }

    @FXML
    private void ModifierClicked(ActionEvent event) {
        crud = new CategorieProduitService();

        c = tabeCategorie.getSelectionModel().getSelectedItem();
        System.out.println(c);

        c.setNom(nomfield.getText());
        c.setDescription(descriptionfield.getText());
        if(imageee!=""){c.setImage(imageee);}
        crud.modifierCategorie(c);
        System.out.println("categorie modifier");
        initialize(null, null);
    }

    @FXML
    private void importerImage(ActionEvent event) {
         final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

            File currDir = new File(System.getProperty("user.dir", "."));
            System.out.println(currDir.toPath().getRoot().toString());

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/categorieProduit/";
            ImageService u = new ImageService();
            try {
                u.upload(file, path);
            } catch (IOException ex) {
                Logger.getLogger(EspaceOutilBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imageee = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }

}
