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
import Services.ImageService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author SL-WASSIM
 */
public class EspaceOutilBackController implements Initializable {

    @FXML
    private TableView<CategorieOutil> table;
    @FXML
    private TableColumn<CategorieOutil, String> nom;
    @FXML
    private TableColumn<CategorieOutil, String> logo;

    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button ajouter;
    @FXML
    private TextField inputCategorie;
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
    private TableColumn<Outil, String> image;
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
    private ComboBox<CategorieOutil> inputCategorieOutil;
    @FXML
    private TextField rechercheCategorie;
    @FXML
    private TextField rechercheOutil;
    String logooo;
    String imageee;
    @FXML
    private Button openFileImage;
    @FXML
    private Button openFileLogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            ObservableList<CategorieOutil> list = FXCollections.observableArrayList();
            CategorieOutilService categorie2 = new CategorieOutilService();
            list = categorie2.getALLCategorie();
            inputCategorieOutil.setItems(list);
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
            openFileImage.setVisible(false);
            tableOutil.setVisible(true);
            ajouterOutil.setVisible(true);
            modifierOutil.setVisible(true);
            supprimerOutil.setVisible(true);
            rechercheOutil.setVisible(true);

            Connection c = Connexion.getInstance().getCon();
            nom.setCellValueFactory(new PropertyValueFactory<CategorieOutil, String>("nom"));
            logo.setCellValueFactory(new PropertyValueFactory<>("Im"));

            CategorieOutilService categorie = new CategorieOutilService();
            try {
                table.setItems(categorie.afficherCategorie());
                // TODO
            } catch (SQLException ex) {
                Logger.getLogger(EspaceProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

            nomOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("nom"));
            dureeOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("dureeMaximale"));
            image.setCellValueFactory(new PropertyValueFactory<Outil, String>("Im"));
            prixOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("prix"));
            addresseOutil.setCellValueFactory(new PropertyValueFactory<Outil, String>("adresse"));
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

            ObservableList data = table.getItems();
            rechercheCategorie.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (oldValue != null && (newValue.length() < oldValue.length())) {
                    table.setItems(data);
                }
                String value = newValue.toLowerCase();
                ObservableList<CategorieOutil> subentries = FXCollections.observableArrayList();

                long count = table.getColumns().stream().count();
                for (int i = 0; i < table.getItems().size(); i++) {
                    for (int j = 0; j < count; j++) {
                        String entry = "" + table.getColumns().get(j).getCellData(i);
                        System.out.println(entry);
                        if (entry.toLowerCase().contains(value)) {
                            subentries.add(table.getItems().get(i));
                            break;
                        }
                    }
                }
                table.setItems(subentries);
            });

            ObservableList data1 = tableOutil.getItems();
            rechercheOutil.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (oldValue != null && (newValue.length() < oldValue.length())) {
                    tableOutil.setItems(data1);
                }
                String value = newValue.toLowerCase();
                ObservableList<Outil> subentries = FXCollections.observableArrayList();

                long count = tableOutil.getColumns().stream().count();
                for (int i = 0; i < tableOutil.getItems().size(); i++) {
                    for (int j = 0; j < count; j++) {
                        String entry = "" + tableOutil.getColumns().get(j).getCellData(i);
                        System.out.println(entry);
                        if (entry.toLowerCase().contains(value)) {
                            subentries.add(tableOutil.getItems().get(i));
                            break;
                        }
                    }
                }
                tableOutil.setItems(subentries);
            });

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
        if (logooo != "") {
            c.setLogo(logooo);
        }
        categorie.modifierCategorie(c);
        logooo = "";
        initialize(null, null);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        CategorieOutilService categorie = new CategorieOutilService();

        CategorieOutil c = new CategorieOutil();
        c.setNom(inputCategorie.getText());
        c.setLogo(logooo);
        categorie.ajouterCategorie(c);
        System.out.println("categorie ajoutée");
        logooo = "";

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
        inputCategorieOutil.setValue(o.getC());

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
        openFileImage.setVisible(true);
        tableOutil.setVisible(false);
        ajouterOutil.setVisible(false);
        modifierOutil.setVisible(false);
        supprimerOutil.setVisible(false);
        rechercheOutil.setVisible(false);

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
        openFileImage.setVisible(true);
        tableOutil.setVisible(false);
        ajouterOutil.setVisible(false);
        modifierOutil.setVisible(false);
        supprimerOutil.setVisible(false);
        rechercheOutil.setVisible(false);
    }

    @FXML
    private void supprimerOutilAction(ActionEvent event) {

        OutilService outil = new OutilService();
        Outil o = tableOutil.getSelectionModel().getSelectedItem();
        outil.supprimerOutil(o.getId());
        System.out.println("outil supprimer");
        initialize(null, null);
    }

    @FXML
    private void confirmerAction(ActionEvent event) {
        OutilService outil = new OutilService();
        Outil o = new Outil();
        o.setNom(inputNom.getText());
        o.setImage(imageee);
        o.setQuantite(Integer.parseInt(inputQuantite.getText()));
        o.setDureeMaximale(Integer.parseInt(inputDuree.getText()));
        o.setPrix(Integer.parseInt(inputPrix.getText()));
        o.setCodePostal(Integer.parseInt(inputCodePostal.getText()));
        o.setVille(inputVille.getText());
        o.setAdresse(inputAddresse.getText());
        o.setC(inputCategorieOutil.getValue());
        outil.ajouterOutil(o);
        System.out.println("outil ajoutée");
        imageee = "";
        initialize(null, null);
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
        openFileImage.setVisible(false);
        tableOutil.setVisible(true);
        ajouterOutil.setVisible(true);
        modifierOutil.setVisible(true);
        supprimerOutil.setVisible(true);
        rechercheOutil.setVisible(true);
    }

    @FXML
    private void confirmerModificationAction(ActionEvent event) {
        OutilService outil = new OutilService();
        Outil o = new Outil();
        o = tableOutil.getSelectionModel().getSelectedItem();
        o.setNom(inputNom.getText());
        if (imageee != "") {
            o.setImage(imageee);
        }
        o.setQuantite(Integer.parseInt(inputQuantite.getText()));
        o.setDureeMaximale(Integer.parseInt(inputDuree.getText()));
        o.setPrix(Integer.parseInt(inputPrix.getText()));
        o.setCodePostal(Integer.parseInt(inputCodePostal.getText()));
        o.setVille(inputVille.getText());
        o.setAdresse(inputAddresse.getText());
        o.setC(inputCategorieOutil.getValue());
        outil.modifierOutil(o);
        System.out.println("outil ajoutée");
        imageee = "";
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

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/Outil/";
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

    @FXML
    private void importerLogo(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

            File currDir = new File(System.getProperty("user.dir", "."));
            System.out.println(currDir.toPath().getRoot().toString());

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/categorieOutil/";
            ImageService u = new ImageService();
            try {
                u.upload(file, path);
            } catch (IOException ex) {
                Logger.getLogger(EspaceOutilBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            logooo = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }
}
