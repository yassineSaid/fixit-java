/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.ListAchat;
import Entities.User;
import Entities.categorie_produit;
import Entities.produit;
import Services.CategorieProduit;
import Services.Produit;
import Services.ReclamationService;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Ali Saidani
 */
public class EspaceProduitFrontController implements Initializable {

    private Produit crud;

    @FXML
    private TabPane Tabwidget;
    @FXML
    private Tab interfaceAjout;
    @FXML
    private AnchorPane ajouterRec;
    @FXML
    private TextField motif;
    @FXML
    private Button ajouter_Rec;
    private User user;
    private produit prod;
    @FXML
    private TextField Quantitefield;
    @FXML
    private TextField imagefield;
    private ComboBox<User> userOwner;
    @FXML
    private DatePicker dateExp;
    @FXML
    private ComboBox<categorie_produit> categorieProduit;
    @FXML
    private TextField prix;
    @FXML
    private Label MesProduit;
    @FXML
    private TableView<produit> MesProduitss;
    @FXML
    private TableColumn<produit, String> nomtab;
    @FXML
    private TableColumn<produit, String> dateExpira;
    @FXML
    private TableColumn<produit, String> quantitetab;
    @FXML
    private TableColumn<produit, String> prixTab;
    @FXML
    private TableColumn<produit, String> imageTab;
    @FXML
    private Tab ListProduitVendre;
    @FXML
    private AnchorPane ajouterRec1;
    @FXML
    private Tab ListProduitVendre1;
    @FXML
    private AnchorPane ajouterRec11;
    @FXML
    private Button modifierProduit;
    @FXML
    private Button ajouter_Rec2;
    private ListView<produit> listProduit;
    @FXML
    private TableColumn<produit, String> imageP;
    @FXML
    private TableColumn<produit, String> QuantiteP;
    @FXML
    private TableColumn<produit, String> prixP;
    @FXML
    private TableColumn<produit, String> dateP;
    @FXML
    private TableColumn<produit, Button> actionP;
    @FXML
    private TableColumn<produit, String> nomP;
    @FXML
    private TableView<produit> allProduit;
    @FXML
    private ImageView image;
    @FXML
    private TextField nomF;
    @FXML
    private TextField nomF1;
    @FXML
    private TextField nomF2;
    private int id;
    @FXML
    private Button AcheterField;
    @FXML
    private AnchorPane achat;
    @FXML
    private Label quantiteLab;
    @FXML
    private TextField QuantiteField;
    @FXML
    private Label quantiteLab1;
    @FXML
    private Button retourField;
    @FXML
    private Button retourField1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        afficher();

    }

    private void afficher() {
        Platform.runLater(() -> {
            modifierProduit.setDisable(true);
            ajouter_Rec2.setDisable(true);
            ajouter_Rec.setDisable(false);

            //  Categorie
            ObservableList<categorie_produit> list1 = FXCollections.observableArrayList();
            ObservableList<produit> list2 = FXCollections.observableArrayList();
            Produit p1 = new Produit();
            Produit p2 = new Produit();

            list1 = p1.getCategorie();
            categorieProduit.setItems(list1);
            motif.setText("");
            Quantitefield.setText("");
            prix.setText("");
            imagefield.setText("");
            dateExp.setValue(LocalDate.now());
            //mes produits
            nomtab.setCellValueFactory(new PropertyValueFactory<produit, String>("Nom"));
            imageTab.setCellValueFactory(new PropertyValueFactory<produit, String>("image"));
            quantitetab.setCellValueFactory(new PropertyValueFactory<produit, String>("Quantite"));
            prixTab.setCellValueFactory(new PropertyValueFactory<produit, String>("prix"));
            dateExpira.setCellValueFactory(new PropertyValueFactory<produit, String>("date_exp"));
            imageP.setCellValueFactory(new PropertyValueFactory<produit, String>("image"));
            nomP.setCellValueFactory(new PropertyValueFactory<produit, String>("Nom"));
            QuantiteP.setCellValueFactory(new PropertyValueFactory<produit, String>("Quantite"));
            prixP.setCellValueFactory(new PropertyValueFactory<produit, String>("prix"));
            dateP.setCellValueFactory(new PropertyValueFactory<produit, String>("date_exp"));
            actionP.setCellValueFactory(new PropertyValueFactory<produit, Button>("Detaille"));
            list2 = p2.getAllProduit();
            allProduit.setItems(list2);
            for (produit l : allProduit.getItems()) {
                l.getDetaille().setOnAction(this::DetailleProduitClicked);
            }

            Produit crud = new Produit();
            try {
                MesProduitss.setItems(crud.getAllMesProduit(user.getId()));
                System.out.println(p.getId());

                // TODO
            } catch (SQLException ex) {
                Logger.getLogger(EspaceProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void ajouterProduitClicked(ActionEvent event) throws ParseException {
        crud = new Produit();
        LocalDate today = LocalDate.now();
        produit ct = new produit();
        dateExp.getValue().compareTo(today);
        System.out.println(dateExp.getValue().compareTo(today));
        ct.setIdCategorieProduit(categorieProduit.getValue());
        ct.setNom(motif.getText());
        ct.setQuantite(Integer.parseInt(Quantitefield.getText()));
        LocalDate stringDate = dateExp.getValue();
        Timestamp timestamp = Timestamp.valueOf(stringDate.atStartOfDay().plusHours(1));
        ct.setDate_exp(timestamp);
        ct.setUser(user);
        ct.setImage(imagefield.getText());
        ct.setPrix(Integer.parseInt(prix.getText()));
        String s= Integer.toString(ct.getQuantite());
        System.out.println(s);
        if ((ct.getNom().length()==0) || (categorieProduit.getValue() == null)||(ct.getImage().length()==0)||(s.length()==0) ){
            System.out.println("vérifier vos données");
        } else {
            crud.ajouterProduit(ct);
            System.out.println("categorie ajoutée");
        }
            //crud.ajouterProduit(ct);
            //System.out.println("categorie ajoutée");
        //initialize();
        initialize(null, null);

    }

    private void selectUser(ActionEvent event) {
        ObservableList<User> list = FXCollections.observableArrayList();
        Produit p = new Produit();
        list = p.getuserOwner(user.getId());
        userOwner.setItems(list);
    }
    produit p = new produit();

    @FXML
    private void itemselected(MouseEvent event) {

        p = MesProduitss.getSelectionModel().getSelectedItem();

        motif.setText(p.getNom());
        Quantitefield.setText(Integer.toString(p.getQuantite()));
        imagefield.setText(p.getImage());
        prix.setText(Integer.toString(p.getPrix()));
        dateExp.setValue(p.getDate_exp().toLocalDateTime().toLocalDate());
        categorieProduit.setValue(p.getIdCategorieProduit());
        System.out.println(p.getId());
        modifierProduit.setDisable(false);
        ajouter_Rec2.setDisable(false);
        ajouter_Rec.setDisable(true);

    }

    @FXML
    private void modifierProduitClicked(ActionEvent event) {
        crud = new Produit();

        produit ct = new produit();
        ct = MesProduitss.getSelectionModel().getSelectedItem();
        ct.setIdCategorieProduit(categorieProduit.getValue());
        ct.setNom(motif.getText());
        ct.setQuantite(Integer.parseInt(Quantitefield.getText()));
        LocalDate stringDate = dateExp.getValue();
        Timestamp timestamp = Timestamp.valueOf(stringDate.atStartOfDay().plusHours(1));
        ct.setDate_exp(timestamp);
        ct.setUser(user);
        ct.setImage(imagefield.getText());
        ct.setPrix(Integer.parseInt(prix.getText()));

        crud.modifierProduit(ct);
        System.out.println(ct.getId());
        initialize(null, null);

    }

    @FXML
    private void supprimerProduitClicked(ActionEvent event) {
        crud = new Produit();
        p = MesProduitss.getSelectionModel().getSelectedItem();
        crud.supprimerProduit(p);
        System.out.println("produit ete supprimer");
        initialize(null, null);
    }

    private void DetailleProduitClicked(ActionEvent event) {
        Tabwidget.getSelectionModel().select(2);
        Button button = (Button) event.getSource();
        produit p1 = new produit();
        crud = new Produit();
        //rud.DetailleProduit();
        System.out.println(Integer.parseInt(button.getId()));
        p1 = crud.DetailleProduit(Integer.parseInt(button.getId()));
        nomF.setText(p1.getNom());
        nomF.setEditable(false);
        nomF1.setText(Integer.toString(p1.getQuantite()));
        nomF2.setText(Integer.toString(p1.getPrix()));
        nomF1.setEditable(false);
        nomF2.setEditable(false);
        prod = p1;
        quantiteLab.setVisible(false);
        quantiteLab1.setVisible(false);

    }

    @FXML
    private void AcheterField(ActionEvent event) {
        System.out.println(prod.getId());
        produit p1 = new produit();
        crud = new Produit();
        crud.verifierQuantite(prod.getId());
        if (Integer.parseInt(QuantiteField.getText()) > prod.getQuantite()) {
            quantiteLab.setVisible(true);
        } else {
            quantiteLab.setVisible(false);
            crud.verifierSolde(user.getId());
            if (Integer.parseInt(QuantiteField.getText()) * prod.getPrix() > crud.verifierSolde(user.getId())) {
                quantiteLab1.setVisible(true);
            } else if (prod.getUser().getId() != user.getId()) {
                quantiteLab1.setVisible(false);
                int x = 0;
                x = prod.getQuantite() - Integer.parseInt(QuantiteField.getText());
                int solde = 0, solde1 = 0, t = 0, solde2 = 0, solde3 = 0, tt = 0;

                solde = crud.verifierSolde(user.getId()) - (Integer.parseInt(QuantiteField.getText()) * prod.getPrix());
                System.out.println(crud.verifierSolde(user.getId()));
                crud.AcheterProduit(prod, x);
                solde1 = prod.getUser().getSolde() + (Integer.parseInt(QuantiteField.getText()) * prod.getPrix());

                this.crud.ModifierUserAchat(prod.getUser().getId(), solde1);
                this.crud.ModifierUserAchat(user.getId(), solde);
                System.out.println(crud.verifierSolde(prod.getUser().getId()));
                ListAchat l = new ListAchat();
                l.setNom(prod.getNom());
                l.setAcheteur(user.getFirstname());
                l.setIdAcheteur(user.getId());
                l.setIdProduit(prod.getId());
                l.setPrix(prod.getPrix());
                l.setQuantite(Integer.parseInt(QuantiteField.getText()));
                Date date = new Date();
                Timestamp ts = new Timestamp(date.getTime());
                l.setImage(prod.getImage());
                l.setDate(ts);
                crud.ajouterAchat(l);
                QuantiteField.setText("");

            } else if (prod.getUser().getId() == user.getId()) {
                int x = 0;
                x = prod.getQuantite() - Integer.parseInt(QuantiteField.getText());
                crud.AcheterProduit(prod, x);
                System.out.println("done babe");
                QuantiteField.setText("");
            }

        }

    }

    @FXML
    private void RetourMesProduits(ActionEvent event) {

        Tabwidget.getSelectionModel().select(0);
    }

    @FXML
    private void RetourAllProduit(ActionEvent event) {
        Tabwidget.getSelectionModel().select(1);
    }
}
