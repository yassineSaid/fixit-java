/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieOutil;
import Entities.HistoriqueLocation;
import Entities.Outil;
import Entities.User;
import Entities.UserOutil;
import Services.CategorieOutilService;
import Services.Connexion;
import Services.HistoriqueLocationService;
import Services.OutilService;
import Services.ImageService;
import Services.UserOutilService;
import Services.UserService;
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
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    String logooo = "";
    String imageee = "";
    @FXML
    private Button openFileImage;
    @FXML
    private Button openFileLogo;
    @FXML
    private TableView<UserOutil> tableOutilsLoues;
    @FXML
    private TableColumn<UserOutil, String> nomUser;
    @FXML
    private TableColumn<UserOutil, String> userNomOutil;
    @FXML
    private TableColumn<UserOutil, String> dateLocation;
    @FXML
    private TableColumn<UserOutil, String> dateRetour;
    @FXML
    private TableColumn<UserOutil, String> prix;
    @FXML
    private Button retourner;
    @FXML
    private TableView<HistoriqueLocation> tableHistorique;
    @FXML
    private TableColumn<HistoriqueLocation, String> nomUser1;
    @FXML
    private TableColumn<HistoriqueLocation, String> userNomOutil1;
    @FXML
    private TableColumn<HistoriqueLocation, String> dateLocation1;
    @FXML
    private TableColumn<HistoriqueLocation, String> dateRetour1;
    @FXML
    private TableColumn<HistoriqueLocation, String> prix1;
    private ImageView iiiiiiiiiiiiiii;
    private ImageView imageNotification;
    @FXML
    private BackIndexController backIndexController;
    private User user;
    /**
     * Initializes the controller class.
     */
     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                backIndexController.setUser(user);
                backIndexController.initialize(null, null);
                ObservableList<CategorieOutil> list = FXCollections.observableArrayList();
                CategorieOutilService categorie2 = new CategorieOutilService();
                list = categorie2.getALLCategorie();
                inputCategorieOutil.setItems(list);
                modifier.setDisable(true);
                supprimer.setDisable(true);
                inputCategorie.setText("");

                modifierOutil.setDisable(true);
                supprimerOutil.setDisable(true);
                retourner.setDisable(true);

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

                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                logo.setCellValueFactory(new PropertyValueFactory<>("Im"));

                CategorieOutilService categorie = new CategorieOutilService();
                try {
                    table.setItems(categorie.afficherCategorie());
                    // TODO
                } catch (SQLException ex) {
                    Logger.getLogger(EspaceProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
                }

                nomOutil.setCellValueFactory(new PropertyValueFactory<>("nom"));
                dureeOutil.setCellValueFactory(new PropertyValueFactory<>("dureeMaximale"));
                image.setCellValueFactory(new PropertyValueFactory<>("Im"));
                prixOutil.setCellValueFactory(new PropertyValueFactory<>("prix"));
                addresseOutil.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                codePostalOutil.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
                categorieOutil.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
                quantiteOutil.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                villeOutil.setCellValueFactory(new PropertyValueFactory<>("ville"));

                OutilService outil = new OutilService();
                tableOutil.setItems(outil.afficherOutil());

                nomUser.setCellValueFactory(new PropertyValueFactory<>("user"));
                userNomOutil.setCellValueFactory(new PropertyValueFactory<>("outil"));
                dateLocation.setCellValueFactory(new PropertyValueFactory<>("dateLocation"));
                dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
                prix.setCellValueFactory(new PropertyValueFactory<>("total"));

                UserOutilService uo = new UserOutilService();

                tableOutilsLoues.setItems(uo.afficherOutil());

                nomUser1.setCellValueFactory(new PropertyValueFactory<>("idUser"));
                userNomOutil1.setCellValueFactory(new PropertyValueFactory<>("idOutil"));
                dateLocation1.setCellValueFactory(new PropertyValueFactory<>("dateLocation"));
                dateRetour1.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
                prix1.setCellValueFactory(new PropertyValueFactory<>("total"));

                HistoriqueLocationService ho = new HistoriqueLocationService();
                tableHistorique.setItems(ho.afficherOutil());
                // TODO

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
            }
        });
    }

    @FXML
    private void supprimer(ActionEvent event) {
        CategorieOutilService categorie = new CategorieOutilService();
        CategorieOutil c = table.getSelectionModel().getSelectedItem();
        categorie.supprimerCategorie(c.getId());
        Image img = new Image(getClass().getResourceAsStream("/Resources/delete.png"),50,50,false,false);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("La catégorie " + c.getNom() + " est supprimée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
        System.out.println("categorie supprimée");
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
        String err="";
        if (c.getNom().equals("")) {
            System.out.println(c.getNom());
            inputCategorie.setStyle("-fx-border-color: red;");
            err += "Le nom de la catégorie ne doit être vide";
        }
        else if(categorie.verifierCategorie(c.getNom())) {
            
            inputCategorie.setStyle("-fx-border-color: red;");
            err += "Le nom de la catégorie doit être unique";
        }
        else{
            inputCategorie.setStyle("-fx-border-color: transparent;");
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modification");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        }
        else{
            if (logooo != "") {
            c.setLogo(logooo);
        }
        categorie.modifierCategorie(c);
        logooo = "";
        Image img = new Image(getClass().getResourceAsStream("/Resources/edit.png"),50,50,false,false);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("La catégorie " + c.getNom() + " est modifiée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
        System.out.println("categorie modifiée");
        }
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        CategorieOutilService categorie = new CategorieOutilService();
        CategorieOutil c = new CategorieOutil();
        c.setNom(inputCategorie.getText());
        c.setLogo(logooo);
        String err = "";
        if (c.getNom().equals("")) {
            inputCategorie.setStyle("-fx-border-color: red;");
            err += "Le nom de la catégorie ne doit être vide";
        }
        else if(categorie.verifierCategorie(c.getNom())) {
            
            inputCategorie.setStyle("-fx-border-color: red;");
            err += "Le nom de la catégorie doit être unique";
        }
        else{
            inputCategorie.setStyle("-fx-border-color: transparent;");
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        } else {
            categorie.ajouterCategorie(c);
            logooo = "";
            Image img = new Image(getClass().getResourceAsStream("/Resources/tik.png"),50,50,false,false);
            Notifications notificationBuilder = Notifications.create().title("Notification").text("La catégorie " + c.getNom() + " est ajoutée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            initialize(null, null);
            System.out.println("categorie ajoutée");
        }

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
        Image img = new Image(getClass().getResourceAsStream("/Resources/delete.png"),50,50,false,false);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("L'outil " + o.getNom() + " est supprimé avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
    }

    @FXML
    private void confirmerAction(ActionEvent event) {
        
        
            OutilService outil = new OutilService();
        String err="";
         if (inputNom.getText().equals("")) {
        inputNom.setStyle("-fx-border-color: red;");
        err += "Le nom de l'outil ne doit être vide";
        }
        else if(outil.verifierOutil(inputNom.getText())) {
        
        inputNom.setStyle("-fx-border-color: red;");
        err += "\nLe nom de l'outil doit être unique";
        }
        else{
        inputNom.setStyle("-fx-border-color: transparent;");
        }
        if (inputVille.getText().equals("")) {
        inputVille.setStyle("-fx-border-color: red;");
        err += "\nLe champ ville ne doit être vide";
        }
        else{
        inputVille.setStyle("-fx-border-color: transparent;");
        }
        if (inputAddresse.getText().equals("")) {
        inputAddresse.setStyle("-fx-border-color: red;");
        err += "\nLe champ adresse ne doit être vide";
        }
        else{
        inputAddresse.setStyle("-fx-border-color: transparent;");
        }
        if (inputQuantite.getText().equals("")) {
        inputQuantite.setStyle("-fx-border-color: red;");
        err += "\nLe champ quantitée ne doit être vide";
        }
        else{
        inputQuantite.setStyle("-fx-border-color: transparent;");
        }
        if (inputPrix.getText().equals("")) {
        inputPrix.setStyle("-fx-border-color: red;");
        err += "\nLe champ prix ne doit être vide";
        }
        else{
        inputPrix.setStyle("-fx-border-color: transparent;");
        }
        if (inputDuree.getText().equals("")) {
        inputDuree.setStyle("-fx-border-color: red;");
        err += "\nLe champ durée maximale ne doit être vide";
        }
        else{
        inputDuree.setStyle("-fx-border-color: transparent;");
        }
        if (inputCategorieOutil.getValue()==null) {
        inputCategorieOutil.setStyle("-fx-border-color: red;");
        err += "\nVous devez choisir une catégorie";
        }
        else{
        inputCategorieOutil.setStyle("-fx-border-color: transparent;");
        }
        if (inputCodePostal.getText().equals("") ) {
        inputCodePostal.setStyle("-fx-border-color: red;");
        err += "\nLe champ code postal ne doit être vide";
        }
        else if(Integer.parseInt(inputCodePostal.getText())<1000 || Integer.parseInt(inputCodePostal.getText())>9999){
        
        inputCodePostal.setStyle("-fx-border-color: red;");
        err += "\nLe champ code postal doit comporté 4 chiffres";
        }
        else{
        inputCodePostal.setStyle("-fx-border-color: transparent;");
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modification");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        }
        else{
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
        Image img = new Image(getClass().getResourceAsStream("/Resources/tik.png"),50,50,false,false);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("L'outil " + o.getNom() + " est ajouté avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
        }
       
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
        String err="";
         if (inputNom.getText().equals("")) {
        inputNom.setStyle("-fx-border-color: red;");
        err += "Le nom de l'outil ne doit être vide";
        }
        else if(outil.verifierOutil(inputNom.getText())) {
        
        inputNom.setStyle("-fx-border-color: red;");
        err += "\nLe nom de l'outil doit être unique";
        }
        else{
        inputNom.setStyle("-fx-border-color: transparent;");
        }
        if (inputVille.getText().equals("")) {
        inputVille.setStyle("-fx-border-color: red;");
        err += "\nLe champ ville ne doit être vide";
        }
        else{
        inputVille.setStyle("-fx-border-color: transparent;");
        }
        if (inputAddresse.getText().equals("")) {
        inputAddresse.setStyle("-fx-border-color: red;");
        err += "\nLe champ adresse ne doit être vide";
        }
        else{
        inputAddresse.setStyle("-fx-border-color: transparent;");
        }
        if (inputQuantite.getText().equals("")) {
        inputQuantite.setStyle("-fx-border-color: red;");
        err += "\nLe champ quantitée ne doit être vide";
        }
        else{
        inputQuantite.setStyle("-fx-border-color: transparent;");
        }
        if (inputPrix.getText().equals("")) {
        inputPrix.setStyle("-fx-border-color: red;");
        err += "\nLe champ prix ne doit être vide";
        }
        else{
        inputPrix.setStyle("-fx-border-color: transparent;");
        }
        if (inputDuree.getText().equals("")) {
        inputDuree.setStyle("-fx-border-color: red;");
        err += "\nLe champ durée maximale ne doit être vide";
        }
        else{
        inputDuree.setStyle("-fx-border-color: transparent;");
        }
        if (inputCategorieOutil.getValue()==null) {
        inputCategorieOutil.setStyle("-fx-border-color: red;");
        err += "\nVous devez choisir une catégorie";
        }
        else{
        inputCategorieOutil.setStyle("-fx-border-color: transparent;");
        }
        if (inputCodePostal.getText().equals("") ) {
        inputCodePostal.setStyle("-fx-border-color: red;");
        err += "\nLe champ code postal ne doit être vide";
        }
        else if(Integer.parseInt(inputCodePostal.getText())<1000 || Integer.parseInt(inputCodePostal.getText())>9999){
        
        inputCodePostal.setStyle("-fx-border-color: red;");
        err += "\nLe champ code postal doit comporté 4 chiffres";
        }
        else{
        inputCodePostal.setStyle("-fx-border-color: transparent;");
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modification");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        }
        else{
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
        Image img = new Image(getClass().getResourceAsStream("/Resources/edit.png"),50,50,false,false);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("L'outil " + o.getNom() + " est modifié avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
        }
        
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

    @FXML
    private void retournerAction(ActionEvent event) {
        HistoriqueLocation hl = new HistoriqueLocation();
        HistoriqueLocationService hs = new HistoriqueLocationService();
        UserOutilService us = new UserOutilService();
        OutilService os = new OutilService();
        UserOutil uo = tableOutilsLoues.getSelectionModel().getSelectedItem();
        hl.setDateLocation(uo.getDateLocation());
        hl.setDateRetour(uo.getDateRetour());
        hl.setIdOutil(uo.getOutil());
        hl.setIdUser(uo.getUser());
        hl.setTotal(uo.getTotal());
        hs.archiver(hl);

        os.updateQuantie(uo.getOutil(), (1));
        us.supprimerUserOutil(uo);
        UserService userservice = new UserService();
        userservice.modifierSolde(uo.getUser(), 10);
        initialize(null, null);
    }

    @FXML
    private void itemSelectedOutilLoues(MouseEvent event) {
        retourner.setDisable(false);
    }

    private void verifier(TextField tf) {
        String val = tf.getText();
        System.out.println(val);
        String newVal = "";
        for (char ch : val.toCharArray()) {
            if (Character.isDigit(ch)) {
                newVal += ch;
            }
        }
        tf.setText(newVal);
    }

    @FXML
    private void changerAction(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }
    }
}
