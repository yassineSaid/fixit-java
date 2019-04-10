/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Entities.categorie_produit;
import Entities.produit;
import Services.Produit;
import Services.ReclamationService;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ali Saidani
 */
public class AjoutProduitController implements Initializable {

    private Produit crud;

    @FXML
    private Button espaceServ;
    @FXML
    private Button espaceOut;
    @FXML
    private Button espaceProd;
    @FXML
    private Button espaceRec;
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
    @FXML
    private Label userName;
    private User user;
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
        Platform.runLater(() -> {

            //  Categorie
            ObservableList<categorie_produit> list1 = FXCollections.observableArrayList();
            Produit p1 = new Produit();
            list1 = p1.getCategorie();
            categorieProduit.setItems(list1);
            ObservableList<categorie_produit> list = FXCollections.observableArrayList();
            //list.addAll("","","");

        });

    }

    @FXML
    private void ajouterProduitClicked(ActionEvent event) throws ParseException {
        crud = new Produit();

        produit ct = new produit();
        System.out.println(ct);
        ct.setNom(motif.getText());
        ct.setQuantite(Integer.parseInt(Quantitefield.getText()));
          LocalDate stringDate = dateExp.getValue();
       // Timestamp timestamp = new Timestamp(localDate.toDateTimeAtStartOfDay().getMillis());
      //  ct.setDate_exp(dateExp.getValue());
        
        /*
        LocalDate stringDate = dateExp.getValue();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = formatter1.parse(stringDate);
        java.sql.Timestamp dateRealisation = new java.sql.Timestamp(date1.getTime());
        ct.setDate_exp(dateRealisation);*/
        ct.setUser(user);
        ct.setImage(imagefield.getText());
        ct.setPrix(Integer.parseInt(prix.getText()));
        System.out.println(ct.getDate_exp());
        System.out.println(ct.getImage());
        System.out.println(ct.getNom());
        System.out.println(ct.getPrix());
        System.out.println(ct.getQuantite());
        System.out.println(ct.getUser().getId());
        crud.ajouterProduit(ct);
        System.out.println("categorie ajoutée");

    }

    private void selectUser(ActionEvent event) {
        ObservableList<User> list = FXCollections.observableArrayList();
        Produit p = new Produit();
        list = p.getuserOwner(user.getId());
        userOwner.setItems(list);
    }

}
