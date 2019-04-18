/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieProduit;
import Entities.ListAchat;
import Entities.produit;
import Services.CategorieProduitService;
import Services.ImageService;
import Services.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    private String imageee="";
    @FXML
    private Button openFile;
    @FXML
    private TableView<ListAchat> listDachat;
    @FXML
    private TableColumn<ListAchat, String> imageAchat;
    @FXML
    private TableColumn<ListAchat, String> produitAchat;
    @FXML
    private TableColumn<ListAchat, String> prixAchat;
    @FXML
    private TableColumn<ListAchat, String> QuantiteaAchat;
    @FXML
    private BarChart<String, Number> statstique;
    @FXML
    private ListView<produit> lisProduit;
    @FXML
    private TextField rechercheCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            statstique.getData().clear();
            modbtn.setDisable(true);
            supbtn.setDisable(true);
            ajoutbtn.setDisable(false);
            imageAchat.setCellValueFactory(new PropertyValueFactory<ListAchat, String>("im"));
            produitAchat.setCellValueFactory(new PropertyValueFactory<ListAchat, String>("Nom"));
            prixAchat.setCellValueFactory(new PropertyValueFactory<ListAchat, String>("prix"));
            QuantiteaAchat.setCellValueFactory(new PropertyValueFactory<ListAchat, String>("Quantite"));
            loadDataFromDatabase();
            lisProduit.setCellFactory(item -> new ListCell<produit>() {
                protected void updateItem(produit item, boolean bln) {
                    super.updateItem(item, bln);
                    if (item != null) {
                        Text nom = new Text(item.getNom());
                        Text prix = new Text(Integer.toString(item.getPrix()));
                        Text Quantite = new Text(Integer.toString(item.getQuantite()));
                        Text disponible = new Text("Disponible");
                        Text indisponible = new Text("Indisponible");
                        disponible.setStyle("-fx-font-smoothing-type: lcd;\n"
                                + " -fx-fill: green;\n"
                                + " -fx-font-size: 11pt;"
                                + "-fx-font-weight: bold;");
                        indisponible.setStyle("-fx-font-smoothing-type: lcd;\n"
                                + " -fx-fill: red;\n"
                                + " -fx-font-size: 11pt;"
                                + "-fx-font-weight: bold;");
                        Image marker = new Image("file:/wamp64/www/fixit/web/service/images/icons/quantite.png", 30, 30, false, false);
                        ImageView m = new ImageView(marker);
                        Image scoin = new Image("file:/wamp64/www/fixit/web/service/images/icons/scoin.png", 30, 30, false, false);
                        ImageView s = new ImageView(scoin);
                        Timestamp ts = item.getDate_exp();
                        Date date = new Date();
                        date.setTime(ts.getTime());
                        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
                        Text dateEx = new Text(formattedDate);

                        Image dateE = new Image("file:/wamp64/www/fixit/web/service/images/icons/date.png", 30, 30, false, false);
                        ImageView e = new ImageView(dateE);
                        HBox prixEnScoin = new HBox(s, prix);
                        HBox adresseM = new HBox(m, Quantite);
                        HBox da = new HBox(e, dateEx);
                        nom.setStyle("-fx-font-size: 35 arial;");
                        prix.setStyle("-fx-font-size: 20 arial;");
                        Button btn = item.getDetaille();
                        btn.setStyle("-fx-background-color: \n"
                                + "        linear-gradient(#FF0000, #FF0000),\n"
                                + "        linear-gradient(#FF0000 0%,     #FF0000,    #FF0000 100%),\n"
                                + "        linear-gradient(#FF0000 0%, #FF0000 50%);\n"
                                + "    -fx-font-weight: bold;    \n"
                                + "    -fx-background-radius: 8,7,6;\n"
                                + "    -fx-background-insets: 0,1,2;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "	-fx-pref-height: 25px;\n"
                                + "    -fx-pref-width: 158px;\n"
                                + "    -fx-text-fill: white;\n"
                                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

                        btn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                SupprimerProduitClicked(event);
                                /*
                        list.setVisible(false);
                        paginationOutilFront.setVisible(false);
                        location.setVisible(true);
                        Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/" + item.getImage(), 293, 295, false, false);
                        imageDetail.setImage(image1);
                        Image logo1 = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieOutil/" + item.getC().getLogo(), 30, 30, false, false);
                        logoDetail.setImage(logo1);
                        logoDetailText.setText(item.getNomCategorie());
                        adresseDetailText.setText(item.getAdresse() + "  ," + item.getVille());
                        scoinDetailText.setText(Integer.toString(item.getPrix()));
                        jour.setText(Integer.toString(item.getDureeMaximale()));
                        prix1.setText(Integer.toString(item.getPrix()));
                        int nouveauPrix = (int) (item.getPrix() * 1.25);
                        prix2.setText(Integer.toString(nouveauPrix));

                                 */
                            }
                        });
                        VBox vBox = new VBox(nom, adresseM, prixEnScoin, da, btn);
                        vBox.setStyle("-fx-font-color: transparent;");
                        vBox.setSpacing(10);

                        Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/Produit/" + item.getImage(), 200, 200, false, false);
                        ImageView img = new ImageView(image);

                        HBox hBox = new HBox(img, vBox);
                        hBox.setStyle("-fx-font-color: transparent;");
                        hBox.setSpacing(10);
                        // hBox.setStyle("-fx-alignment: center ;");
                        //hBox.gets
                        setGraphic(hBox);
                    }

                }
            });
            cat.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("Nom"));
            desc.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("description"));
            image.setCellValueFactory(new PropertyValueFactory<CategorieProduit, String>("im"));

            CategorieProduitService crud = new CategorieProduitService();
            try {
                statstique.getData().add(crud.graph());
                tabeCategorie.setItems(crud.afficherCategorie());
                listDachat.setItems(crud.listAchat());
                // TODO
            } catch (SQLException ex) {
                Logger.getLogger(EspaceProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
                ObservableList data = tabeCategorie.getItems();
                rechercheCategorie.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (oldValue != null && (newValue.length() < oldValue.length())) {
                        tabeCategorie.setItems(data);
                    }
                    String value = newValue.toLowerCase();
                    ObservableList<CategorieProduit> subentries = FXCollections.observableArrayList();
                    
                    long count = tabeCategorie.getColumns().stream().count();
                    for (int i = 0; i < tabeCategorie.getItems().size(); i++) {
                        for (int j = 0; j < count; j++) {
                            String entry = "" + tabeCategorie.getColumns().get(j).getCellData(i);
                            System.out.println(entry);
                            if (entry.toLowerCase().contains(value)) {
                                subentries.add(tabeCategorie.getItems().get(i));
                                break;
                            }
                        }
                    }
                    tabeCategorie.setItems(subentries);
                });
        });
    }

    @FXML
    private void AjouteClicked(ActionEvent event) {
        crud = new CategorieProduitService();

        CategorieProduit ct = new CategorieProduit();
        String nom = nomfield.getText();
        System.out.println(nom);
        String err = "";
        if (nom.length() == 0) {
            err += "\nchamp caégorie est vide";
            nomfield.setStyle("-fx-border-color : red");
        }
        if (descriptionfield.getText().length() == 0) {
            err += "\nchamp description est vide";
            descriptionfield.setStyle("-fx-border-color : red");

        }
        if(nom.equals(crud.verificationNom(nom)))
        { err += "\nchamp caégorie est existé";
            nomfield.setStyle("-fx-border-color : red");}

        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout de catégorie");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        } else {
            ct.setNom(nomfield.getText());
            ct.setDescription(descriptionfield.getText());
            ct.setImage(imageee);
            crud.ajouterCategorie(ct);
            Notifications notificationBuilder = Notifications.create().title("Notification").text("Catégorie est ajoutée avec succés").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
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
       Notifications notificationBuilder = Notifications.create().title("Notification").text("Categorie est supprimée avec succés").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
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
    private void ModifierClicked(ActionEvent event) {
        crud = new CategorieProduitService();

        c = tabeCategorie.getSelectionModel().getSelectedItem();
        CategorieProduit ct = new CategorieProduit();
        String nom = nomfield.getText();
        String err = "";
        if (nom.length() == 0) {
            err += "\nchamp caégorie est vide";
            nomfield.setStyle("-fx-border-color : red");
        }
        if (descriptionfield.getText().length() == 0) {
            err += "\nchamp description est vide";
            descriptionfield.setStyle("-fx-border-color : red");

        }
               if(nom.equals(crud.verificationNom(nom)))
        { err += "\nchamp caégorie est existé";
            nomfield.setStyle("-fx-border-color : red");}

        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout de catégorie");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        }
        else{
        c.setNom(nomfield.getText());
        c.setDescription(descriptionfield.getText());
        if (imageee != "") {
            c.setImage(imageee);
        }
        crud.modifierCategorie(c);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("Categorie est modifiée avec succés").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
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

    private void SupprimerProduitClicked(ActionEvent event) {
        produit p = new produit();
        Produit crud = new Produit();
        p = lisProduit.getSelectionModel().getSelectedItem();
        crud.supprimerProduit(p);
        System.out.println("produit ete supprimer");
        initialize(null, null);
    }

    private void loadDataFromDatabase() {
        try {
            Produit pro = new Produit();
            ObservableList<produit> rs = pro.getAllProduitBack();
            // paginationOutilFront.setPageFactory(this::createPage);
            lisProduit.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

}
