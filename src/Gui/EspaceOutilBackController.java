/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieOutil;
import Entities.Outil;
import Services.CategorieOutilService;
import Services.Connexion;
import Services.OutilService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button ajouter;
    @FXML
    private TextField inputCategorie;
    @FXML
    private TableColumn<Outil, String> idOutil;
    @FXML
    private TableColumn<Outil, String> nomOutil;
    @FXML
    private TableColumn<Outil, String> quantiteOutil;
    @FXML
    private TableColumn<Outil, String> dureeOutil;
    @FXML
    private TableColumn<Outil, String> prixOutil;
    @FXML
    private TableColumn<Outil, String> addresseOutil;
    @FXML
    private TableColumn<Outil, String> codePostalOutil;
    @FXML
    private TableColumn<Outil, String> villeOutil;
    @FXML
    private TableColumn<Outil, String> imageOutil;
    @FXML
    private TableColumn<Outil, String> categorieOutil;
    @FXML
    private Button confirmerOutil;
    @FXML
    private Button retourOutil;
    @FXML
    private Button ajouterOutil;
    @FXML
    private Button modifierOutil;
    @FXML
    private Button supprimerOutil;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputQuantite;
    @FXML
    private TextField inputDuree;
    @FXML
    private TextField inputPrix;
    @FXML
    private TextField inputAddresse;
    @FXML
    private TextField inputCodePostal;
    @FXML
    private TextField inputVille;
    @FXML
    private Button confirmerModificationOutil;
    @FXML
    private Label nomL;
    @FXML
    private Label quantiteL;
    @FXML
    private Label addresseL;
    @FXML
    private Label dureeL;
    @FXML
    private Label codePostalL;
    @FXML
    private Label villeL;
    @FXML
    private Label categorieL;
    @FXML
    private Label prixL;
    @FXML
    private Label imageL;
    @FXML
    private TableView<Outil> tableOutil;
    @FXML
    private ComboBox<?> inputCategorieOutil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            modifier.setDisable(true);
            supprimer.setDisable(true);
            inputCategorie.setText("");
            
            modifierOutil.setDisable(true);
             supprimerOutil.setDisable(true);

            nomL.setVisible(false);
            quantiteL.setVisible(false);
            dureeL.setVisible(false);
            prixL.setVisible(false);
            imageL.setVisible(false);
            addresseL.setVisible(false);
            codePostalL.setVisible(false);
            villeL.setVisible(false);
            categorieL.setVisible(false);
            inputNom.setVisible(false);
            inputQuantite.setVisible(false);
            inputDuree.setVisible(false);
            inputPrix.setVisible(false);
            inputAddresse.setVisible(false);
            inputCodePostal.setVisible(false);
            inputVille.setVisible(false);
            inputCategorieOutil.setVisible(false);
            confirmerOutil.setVisible(false);
            retourOutil.setVisible(false);
            confirmerModificationOutil.setVisible(false);
            tableOutil.setVisible(true);
            ajouterOutil.setVisible(true);
            modifierOutil.setVisible(true);
            supprimerOutil.setVisible(true);

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
            
            idOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("id"));
            nomOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("nom"));
            dureeOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("dureeMaximale"));
            imageOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("image"));
            prixOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("prix"));
            addresseOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("addresse"));
            codePostalOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("codePostal"));
            categorieOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("nomCategorie"));
            quantiteOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("quantite"));
            villeOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("ville"));

            OutilService outil = new OutilService();
            try {
                tableOutil.setItems(outil.afficherOutil());
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
        initialize(null, null);
    }

    @FXML
    private void itemSelected(MouseEvent event) {
        modifier.setDisable(false);
        supprimer.setDisable(false);

        CategorieOutil c = table.getSelectionModel().getSelectedItem();

        inputCategorie.setText(c.getNom());

    }

    @FXML
    private void modifier(ActionEvent event) {

        CategorieOutilService categorie = new CategorieOutilService();
        CategorieOutil c = new CategorieOutil();

        c = table.getSelectionModel().getSelectedItem();
        c.setNom(inputCategorie.getText());
        c.setLogo("test2");
        categorie.modifierCategorie(c);
        initialize(null, null);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        CategorieOutilService categorie = new CategorieOutilService();

        CategorieOutil c = new CategorieOutil();
        c.setNom(inputCategorie.getText());
        c.setLogo("test");
        categorie.ajouterCategorie(c);
        System.out.println("categorie ajoutée");
        initialize(null, null);
    }

    @FXML
    private void itemSelectedOutil(MouseEvent event) {
        modifierOutil.setDisable(false);
        supprimerOutil.setDisable(false);

        Outil o = tableOutil.getSelectionModel().getSelectedItem();

        inputNom.setText(o.getNom());
        inputQuantite.setText(Integer.toString(o.getQuantite()));
        inputPrix.setText(Integer.toString(o.getPrix()));
        inputCodePostal.setText(Integer.toString(o.getCodePostal()));
        inputVille.setText(o.getVille());
        inputDuree.setText(Integer.toString(o.getDureeMaximale()));
        inputAddresse.setText(o.getAdresse());
        
    }

    @FXML
    private void ajouterOutilAction(ActionEvent event) {
        nomL.setVisible(true);
        quantiteL.setVisible(true);
        dureeL.setVisible(true);
        prixL.setVisible(true);
        imageL.setVisible(true);
        addresseL.setVisible(true);
        codePostalL.setVisible(true);
        villeL.setVisible(true);
        categorieL.setVisible(true);
        inputNom.setVisible(true);
        inputQuantite.setVisible(true);
        inputDuree.setVisible(true);
        inputPrix.setVisible(true);
        inputAddresse.setVisible(true);
        inputCodePostal.setVisible(true);
        inputVille.setVisible(true);
        inputCategorieOutil.setVisible(true);
        confirmerOutil.setVisible(true);
        retourOutil.setVisible(true);
        confirmerModificationOutil.setVisible(false);
        tableOutil.setVisible(false);
        ajouterOutil.setVisible(false);
        modifierOutil.setVisible(false);
        supprimerOutil.setVisible(false);
        
    }

    @FXML
    private void modifierOutilAction(ActionEvent event) {
        nomL.setVisible(true);
        quantiteL.setVisible(true);
        dureeL.setVisible(true);
        prixL.setVisible(true);
        imageL.setVisible(true);
        addresseL.setVisible(true);
        codePostalL.setVisible(true);
        villeL.setVisible(true);
        categorieL.setVisible(true);
        inputNom.setVisible(true);
        inputQuantite.setVisible(true);
        inputDuree.setVisible(true);
        inputPrix.setVisible(true);
        inputAddresse.setVisible(true);
        inputCodePostal.setVisible(true);
        inputVille.setVisible(true);
        inputCategorieOutil.setVisible(true);
        confirmerOutil.setVisible(false);
        retourOutil.setVisible(true);
        confirmerModificationOutil.setVisible(true);
        tableOutil.setVisible(false);
        ajouterOutil.setVisible(false);
        modifierOutil.setVisible(false);
        supprimerOutil.setVisible(false);
    }

    @FXML
    private void supprimerOutilAction(ActionEvent event) {
    }

    @FXML
    private void confirmerAction(ActionEvent event) {
    }

    @FXML
    private void retourAction(ActionEvent event) {
        nomL.setVisible(false);
        quantiteL.setVisible(false);
        dureeL.setVisible(false);
        prixL.setVisible(false);
        imageL.setVisible(false);
        addresseL.setVisible(false);
        codePostalL.setVisible(false);
        villeL.setVisible(false);
        categorieL.setVisible(false);
        inputNom.setVisible(false);
        inputQuantite.setVisible(false);
        inputDuree.setVisible(false);
        inputPrix.setVisible(false);
        inputAddresse.setVisible(false);
        inputCodePostal.setVisible(false);
        inputVille.setVisible(false);
        inputCategorieOutil.setVisible(false);
        confirmerOutil.setVisible(false);
        retourOutil.setVisible(false);
        confirmerModificationOutil.setVisible(false);
        tableOutil.setVisible(true);
        ajouterOutil.setVisible(true);
        modifierOutil.setVisible(true);
        supprimerOutil.setVisible(true);
    }

    @FXML
    private void confirmerModificationAction(ActionEvent event) {
    }
}
