/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.ListAchat;
import Entities.User;
import Entities.CategorieProduit;
import Entities.LikeProduit;
import Entities.Outil;
import Entities.produit;
import Services.CategorieProduitService;
import Services.ImageService;
import Services.MailService;
import Services.Produit;
import Services.ReclamationService;
import Services.UserService;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class EspaceProduitFrontController implements Initializable {

    private String imageee = "";
    private ListAchat lis;

    private Produit crud;
    private int quantitee;
    @FXML
    private FrontIndexController frontIndexController;

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
    private ComboBox<User> userOwner;
    @FXML
    private DatePicker dateExp;
    @FXML
    private ComboBox<CategorieProduit> categorieProduit;
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
    private Button modifierProduit;
    @FXML
    private Button ajouter_Rec2;

    @FXML
    private TextField nomF;
    @FXML
    private TextField nomF1;
    @FXML
    private TextField nomF2;
    private int id;
    @FXML
    private Label quantiteLab;
    @FXML
    private TextField QuantiteField;
    @FXML
    private Label quantiteLab1;
    @FXML
    private Button openFile;
    @FXML
    private TextField scoins;
    @FXML
    private Button like;
    @FXML
    private TextField nbr;
    @FXML
    private ListView<produit> list;
    @FXML
    private ImageView imageDetailProduit;
    @FXML
    private Tab test;
    @FXML
    private TextField datee;
    @FXML
    private AnchorPane tous;
    @FXML
    private AnchorPane ajouterRec11;
    @FXML
    private Button AcheterField;
    @FXML
    private Button retourField1;

    @FXML
    private Button like1;
    @FXML
    private ListView<ListAchat> listProduitAchete;

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
        Platform.runLater(() -> {
            tous.setVisible(true);
            ajouterRec11.setVisible(false);
            loadDataFromDatabase();
            list.setCellFactory(item -> new ListCell<produit>() {
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
                                + "        linear-gradient(#32CD32, #32CD32),\n"
                                + "        linear-gradient(#32CD32 0%,     #32CD32,    #32CD32 100%),\n"
                                + "        linear-gradient(#32CD32 0%, #32CD32 50%);\n"
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
                                DetailleProduitClicked(event);
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
                        prix2.setText(Integer.toString(nouveauPrix));*/

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
            loadDataFromDatabaseAchat();
            listProduitAchete.setCellFactory(item -> new ListCell<ListAchat>() {
                protected void updateItem(ListAchat item, boolean bln) {
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
                        Timestamp ts = item.getDate();
                        Date date = new Date();
                        date.setTime(ts.getTime());
                        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
                        Text dateEx = new Text(formattedDate);

                        Image dateE = new Image("file:/wamp64/www/fixit/web/service/images/icons/date.png", 30, 30, false, false);
                        ImageView e = new ImageView(dateE);
                        HBox prixEnScoin = new HBox(s, prix);
                        HBox adresseM = new HBox(m, Quantite);
                        HBox da = new HBox(e, dateEx);
                        ProgressBar bar = new ProgressBar();
                        Date datee = new Date();

                        long time = datee.getTime();
                        //System.out.println("Time in Milliseconds: " + time);

                        Timestamp tss = new Timestamp(time);
                        Timestamp tsa = new Timestamp(time);
                        tss.setDate(tss.getDate() - 1);
                        nom.setStyle("-fx-font-size: 35 arial;");
                        prix.setStyle("-fx-font-size: 20 arial;");
                        Button btnn = item.getAnnuler();
                        btnn.setStyle("-fx-background-color: \n"
                                + "        linear-gradient(#32CD32, #32CD32),\n"
                                + "        linear-gradient(#32CD32 0%,     #32CD32,    #32CD32 100%),\n"
                                + "        linear-gradient(#32CD32 0%, #32CD32 50%);\n"
                                + "    -fx-font-weight: bold;    \n"
                                + "    -fx-background-radius: 8,7,6;\n"
                                + "    -fx-background-insets: 0,1,2;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "	-fx-pref-height: 25px;\n"
                                + "    -fx-pref-width: 158px;\n"
                                + "    -fx-text-fill: white;\n"
                                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");

                        btnn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println(item.getId());
                                RetourProduit(item.getIdProduit(), item.getQuantite(), item.getId(), item.getPrix());

                            }
                        });
                        if (item.getDate().before(tss)) {
                            btnn.setVisible(false);
                            bar.setVisible(false);
                        } else {
                            btnn.setVisible(true);
                            Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), event -> {

                                bar.setProgress(bar.getProgress() + 0.1);

                            }));
                            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
                            fiveSecondsWonder.play();
                        }
                        VBox vBox = new VBox(nom, adresseM, prixEnScoin, da, btnn, bar);
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

            list.setVisible(true);
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            frontIndexController.getEspaceProd().setStyle("-fx-background-color: #f4f4f4");
            afficher();
        });

    }

    private void afficher() {
        Platform.runLater(() -> {
            modifierProduit.setDisable(true);
            ajouter_Rec2.setDisable(true);
            ajouter_Rec.setDisable(false);

            //  Categorie
            ObservableList<CategorieProduit> list1 = FXCollections.observableArrayList();
            ObservableList<produit> list2 = FXCollections.observableArrayList();
            Produit p1 = new Produit();
            Produit p2 = new Produit();

            list1 = p1.getCategorie();
            categorieProduit.setItems(list1);
            motif.setText("");
            Quantitefield.setText("");
            prix.setText("");

            dateExp.setValue(LocalDate.now());
            //mes produits
            nomtab.setCellValueFactory(new PropertyValueFactory<produit, String>("Nom"));
            imageTab.setCellValueFactory(new PropertyValueFactory<produit, String>("im"));
            quantitetab.setCellValueFactory(new PropertyValueFactory<produit, String>("Quantite"));
            prixTab.setCellValueFactory(new PropertyValueFactory<produit, String>("prix"));
            dateExpira.setCellValueFactory(new PropertyValueFactory<produit, String>("date_exp"));

            Produit crud = new Produit();
            try {
                MesProduitss.setItems(crud.getAllMesProduit(user.getId()));
                ;

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
        int h = dateExp.getValue().compareTo(today);
        String err = "";
        if (motif.getText().length() == 0) {
            err += "\nchamp nom est vide";
            motif.setStyle("-fx-border-color : red");
        }
        if (Quantitefield.getText().length() == 0) {
            err += "\nchamp quantite est vide";
            Quantitefield.setStyle("-fx-border-color : red");
        }
        if (categorieProduit.getValue() == null) {
            err += "\nchamp categorie est vide";
            categorieProduit.setStyle("-fx-border-color : red");
        }
        if (prix.getText().length() == 0) {
            err += "\nchamp prix est vide";
            categorieProduit.setStyle("-fx-border-color : red");
        }
        if (h < 0) {
            err += "\ncette date est déja passé";

        }
        if (motif.getText().equals(crud.verificationNom(motif.getText()))) {
            err += "\nccette  produit est dèja existe";
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout de Produit");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        } else {
            ct.setIdCategorieProduit(categorieProduit.getValue());
            ct.setNom(motif.getText());
            ct.setQuantite(Integer.parseInt(Quantitefield.getText()));
            LocalDate stringDate = dateExp.getValue();
            Timestamp timestamp = Timestamp.valueOf(stringDate.atStartOfDay().plusHours(1));
            ct.setDate_exp(timestamp);
            ct.setUser(user);
            ct.setImage(imageee);
            ct.setPrix(Integer.parseInt(prix.getText()));
            crud.ajouterProduit(ct);
            Notifications notificationBuilder = Notifications.create().title("Notification").text("Produit ajoutée").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked");
        }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

        }

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

        prix.setText(Integer.toString(p.getPrix()));
        dateExp.setValue(p.getDate_exp().toLocalDateTime().toLocalDate());
        categorieProduit.setValue(p.getIdCategorieProduit());
        modifierProduit.setDisable(false);
        ajouter_Rec2.setDisable(false);
        ajouter_Rec.setDisable(true);

    }

    @FXML
    private void modifierProduitClicked(ActionEvent event) {
        crud = new Produit();
        LocalDate today = LocalDate.now();

        produit ct = new produit();
        int h = dateExp.getValue().compareTo(today);
        String err = "";
        if (motif.getText().length() == 0) {
            err += "\nchamp nom est vide";
            motif.setStyle("-fx-border-color : red");
        }
        if (Quantitefield.getText().length() == 0) {
            err += "\nchamp quantite est vide";
            Quantitefield.setStyle("-fx-border-color : red");
        }
        if (categorieProduit.getValue() == null) {
            err += "\nchamp categorie est vide";
            categorieProduit.setStyle("-fx-border-color : red");
        }
        if (prix.getText().length() == 0) {
            err += "\nchamp prix est vide";
            categorieProduit.setStyle("-fx-border-color : red");
        }
        if (motif.getText().equals(crud.verificationNom(motif.getText()))) {
            err += "\nccette  produit est dèja existe";
        }
        if (h < 0) {
            err += "\ncette date est déja passé";

        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("modification de Produit");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        } else {
            ct = MesProduitss.getSelectionModel().getSelectedItem();
            ct.setIdCategorieProduit(categorieProduit.getValue());
            ct.setNom(motif.getText());
            ct.setQuantite(Integer.parseInt(Quantitefield.getText()));
            LocalDate stringDate = dateExp.getValue();
            Timestamp timestamp = Timestamp.valueOf(stringDate.atStartOfDay().plusHours(1));
            ct.setDate_exp(timestamp);
            ct.setUser(user);
            ct.setPrix(Integer.parseInt(prix.getText()));
            if (imageee != "") {
                ct.setImage(imageee);
            }
            crud.modifierProduit(ct);
            Notifications notificationBuilder = Notifications.create().title("Notification").text("Produit modifiée avec succés").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
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
    private void supprimerProduitClicked(ActionEvent event) {
        crud = new Produit();
        p = MesProduitss.getSelectionModel().getSelectedItem();
        crud.supprimerProduit(p);
        System.out.println("produit ete supprimer");
        Notifications notificationBuilder = Notifications.create().title("Notification").text("Produit supprimée avec succés").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked");
        }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        initialize(null, null);
    }

    private void DetailleProduitClicked(ActionEvent event) {
        Platform.runLater(() -> {
            ajouterRec11.setVisible(true);
            list.setVisible(false);
            crud = new Produit();
            imageDetailProduit.setVisible(true);
            like.setVisible(true);
            like1.setVisible(false);
            scoins.setText(Integer.toString(crud.verifierSolde(user.getId())));
            Button button = (Button) event.getSource();
            produit p1 = new produit();

            //rud.DetailleProduit();
            p1 = crud.DetailleProduit(Integer.parseInt(button.getId()));
            //  image.setImage(p1.getIm());
            nomF.setText(p1.getNom());
            nomF.setEditable(false);
            Timestamp ts = p1.getDate_exp();
            Date date = new Date();
            date.setTime(ts.getTime());
            String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
            datee.setText(formattedDate);
            nomF1.setText(Integer.toString(p1.getQuantite()));
            nomF2.setText(Integer.toString(p1.getPrix()));
            nomF1.setEditable(false);
            nomF2.setEditable(false);
            prod = p1;
            if (crud.LikeExiste(user.getId(), prod.getId()) != 0) {

                like.setVisible(false);
                like1.setVisible(true);
            }
            nbr.setText(Integer.toString(crud.NombreLike(prod.getId())));
            quantiteLab.setVisible(false);
            quantiteLab1.setVisible(false);
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Produit/" + p1.getImage(), 274, 255, false, false);
            imageDetailProduit.setImage(image1);
        });

    }

    @FXML
    private void AcheterField(ActionEvent event) {

        produit p1 = new produit();
        crud = new Produit();
        crud.verifierQuantite(prod.getId());
        if (QuantiteField.getText().length() > 0) {
            if (Integer.parseInt(QuantiteField.getText()) > prod.getQuantite()) {

                QuantiteField.setText("");
                quantiteLab.setVisible(true);
            } else {
                quantiteLab.setVisible(false);
                crud.verifierSolde(user.getId());
                if (Integer.parseInt(QuantiteField.getText()) * prod.getPrix() > crud.verifierSolde(user.getId())) {

                    quantiteLab1.setVisible(true);
                    QuantiteField.setText("");
                } else if (prod.getUser().getId() != user.getId()) {
                    quantiteLab1.setVisible(false);
                    int x = 0;
                    x = prod.getQuantite() - Integer.parseInt(QuantiteField.getText());
                    int solde = 0, solde1 = 0, t = 0, solde2 = 0, solde3 = 0, tt = 0;

                    solde = crud.verifierSolde(user.getId()) - (Integer.parseInt(QuantiteField.getText()) * prod.getPrix());

                    crud.AcheterProduit(prod, x);
                    solde1 = prod.getUser().getSolde() + (Integer.parseInt(QuantiteField.getText()) * prod.getPrix());

                    this.crud.ModifierUserAchat(prod.getUser().getId(), solde1);
                    this.crud.ModifierUserAchat(user.getId(), solde);

                    ListAchat l = new ListAchat();
                    l.setNom(prod.getNom());
                    l.setAcheteur(user.getFirstname());
                    l.setIdAcheteur(user.getId());
                    l.setIdProduit(prod.getId());
                    l.setPrix(prod.getPrix() * Integer.parseInt(QuantiteField.getText()));
                    l.setQuantite(Integer.parseInt(QuantiteField.getText()));
                    Date date = new Date();
                    Timestamp ts = new Timestamp(date.getTime());
                    l.setImage(prod.getImage());
                    l.setDate(ts);
                    crud.ajouterAchat(l);
                    QuantiteField.setText("");
                    quantitee = l.getQuantite();
                    envoyeMail();

                    Notifications notificationBuilder = Notifications.create().title("Notification").text("achat fait avec succes").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked");
        }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                    refrechPage(prod);

                } else if (prod.getUser().getId() == user.getId()) {
                    int x = 0;
                    x = prod.getQuantite() - Integer.parseInt(QuantiteField.getText());
                    crud.AcheterProduit(prod, x);
                    QuantiteField.setText("");

                    refrechPage(prod);
                }

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout de catégorie");
            alert.setHeaderText(null);
            alert.setContentText("saisir le quantite ");
            alert.showAndWait();

        }
        UserService us = new UserService();
        user = us.getUser(String.valueOf(user.getId()));
        frontIndexController.setUser(user);
        frontIndexController.refresh();
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

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/produit/";
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
    private void LikeActionClicked(ActionEvent event) {
        LikeProduit l = new LikeProduit();
        l.setProduit(prod.getId());
        l.setUser(user.getId());
        crud.ajouterLike(l);
        crud.NombreLike(prod.getId());

        refrechPage(prod);
    }

    private void loadDataFromDatabase() {
        try {
            Produit pro = new Produit();
            ObservableList<produit> rs = pro.getAllProduit();
            // paginationOutilFront.setPageFactory(this::createPage);
            list.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void RetourAllProduit(ActionEvent event) {
        initialize(null, null);
        nomF.setText("");
        nomF.setEditable(false);
        datee.setText("");
        nomF2.setText("");
        nbr.setText("");
        imageDetailProduit.setVisible(false);

    }

    public void refrechPage(produit p) {
        Platform.runLater(() -> {

            like.setVisible(true);
            like1.setVisible(false);

            Tabwidget.getSelectionModel().select(1);

            produit p1 = new produit();
            crud = new Produit();
            scoins.setText(Integer.toString(crud.verifierSolde(user.getId())));
            //rud.DetailleProduit();

            p1 = crud.DetailleProduit(p.getId());
            //  image.setImage(p1.getIm());
            nomF.setText(p1.getNom());
            nomF.setEditable(false);
            Timestamp ts = p1.getDate_exp();
            Date date = new Date();
            date.setTime(ts.getTime());
            String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
            datee.setText(formattedDate);
            nomF1.setText(Integer.toString(p1.getQuantite()));
            nomF2.setText(Integer.toString(p1.getPrix()));
            nomF1.setEditable(false);
            nomF2.setEditable(false);

            if (crud.LikeExiste(user.getId(), p1.getId()) != 0) {

                like.setVisible(false);
                like1.setVisible(true);
            }
            nbr.setText(Integer.toString(crud.NombreLike(p1.getId())));
            quantiteLab.setVisible(false);
            quantiteLab1.setVisible(false);
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Produit/" + p1.getImage(), 274, 255, false, false);
            imageDetailProduit.setImage(image1);
        });

    }

    public void envoyeMail() {
        String mailContentReclamant = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "<!--[if !mso]><!-->\n"
                + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
                + "<!--<![endif]-->\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n"
                + "<title></title>\n"
                + "<style type=\"text/css\">\n"
                + "* {\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + "body {\n"
                + "	Margin: 0;\n"
                + "	padding: 0;\n"
                + "	min-width: 100%;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "	mso-line-height-rule: exactly;\n"
                + "}\n"
                + "table {\n"
                + "	border-spacing: 0;\n"
                + "	color: #333333;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "}\n"
                + "img {\n"
                + "	border: 0;\n"
                + "}\n"
                + ".wrapper {\n"
                + "	width: 100%;\n"
                + "	table-layout: fixed;\n"
                + "	-webkit-text-size-adjust: 100%;\n"
                + "	-ms-text-size-adjust: 100%;\n"
                + "}\n"
                + ".webkit {\n"
                + "	max-width: 600px;\n"
                + "}\n"
                + ".outer {\n"
                + "	Margin: 0 auto;\n"
                + "	width: 100%;\n"
                + "	max-width: 600px;\n"
                + "}\n"
                + ".full-width-image img {\n"
                + "	width: 100%;\n"
                + "	max-width: 600px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".inner {\n"
                + "	padding: 10px;\n"
                + "}\n"
                + "p {\n"
                + "	Margin: 0;\n"
                + "	padding-bottom: 10px;\n"
                + "}\n"
                + ".h1 {\n"
                + "	font-size: 21px;\n"
                + "	font-weight: bold;\n"
                + "	Margin-top: 15px;\n"
                + "	Margin-bottom: 5px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".h2 {\n"
                + "	font-size: 18px;\n"
                + "	font-weight: bold;\n"
                + "	Margin-top: 10px;\n"
                + "	Margin-bottom: 5px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".one-column .contents {\n"
                + "	text-align: left;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".one-column p {\n"
                + "	font-size: 14px;\n"
                + "	Margin-bottom: 10px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".two-column {\n"
                + "	text-align: center;\n"
                + "	font-size: 0;\n"
                + "}\n"
                + ".two-column .column {\n"
                + "	width: 100%;\n"
                + "	max-width: 300px;\n"
                + "	display: inline-block;\n"
                + "	vertical-align: top;\n"
                + "}\n"
                + ".contents {\n"
                + "	width: 100%;\n"
                + "}\n"
                + ".two-column .contents {\n"
                + "	font-size: 14px;\n"
                + "	text-align: left;\n"
                + "}\n"
                + ".two-column img {\n"
                + "	width: 100%;\n"
                + "	max-width: 280px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".two-column .text {\n"
                + "	padding-top: 10px;\n"
                + "}\n"
                + ".three-column {\n"
                + "	text-align: center;\n"
                + "	font-size: 0;\n"
                + "	padding-top: 10px;\n"
                + "	padding-bottom: 10px;\n"
                + "}\n"
                + ".three-column .column {\n"
                + "	width: 100%;\n"
                + "	max-width: 200px;\n"
                + "	display: inline-block;\n"
                + "	vertical-align: top;\n"
                + "}\n"
                + ".three-column .contents {\n"
                + "	font-size: 14px;\n"
                + "	text-align: center;\n"
                + "}\n"
                + ".three-column img {\n"
                + "	width: 100%;\n"
                + "	max-width: 180px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".three-column .text {\n"
                + "	padding-top: 10px;\n"
                + "}\n"
                + ".img-align-vertical img {\n"
                + "	display: inline-block;\n"
                + "	vertical-align: middle;\n"
                + "}\n"
                + "@media only screen and (max-device-width: 480px) {\n"
                + "table[class=hide], img[class=hide], td[class=hide] {\n"
                + "	display: none !important;\n"
                + "}\n"
                + ".contents1 {\n"
                + "	width: 100%;\n"
                + "}\n"
                + ".contents1 {\n"
                + "	width: 100%;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#f3f2f0;\">\n"
                + "<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#f3f2f0;\">\n"
                + "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f3f2f0;\" bgcolor=\"#f3f2f0;\">\n"
                + "    <tr>\n"
                + "      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \n"
                + "          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\n"
                + "            <tr>\n"
                + "              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\n"
                + "                \n"
                + "                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\n"
                + "                  <tr>\n"
                + "                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td align=\"center\"><center>\n"
                + "                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\n"
                + "                                  <tbody>\n"
                + "                                    <tr>\n"
                + "                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\n"
                + "                                        \n"
                + "                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f3f2f0\">\n"
                + "                                          <tr>\n"
                + "                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:left;font-size:0;\" >                                          \n"
                + "                                              </td>\n"
                + "                                          </tr>\n"
                + "                                          <tr>\n"
                + "                                            <td>&nbsp;</td>\n"
                + "                                          </tr>\n"
                + "                                        </table></td>\n"
                + "                                    </tr>\n"
                + "                                  </tbody>\n"
                + "                                </table>\n"
                + "                              </center></td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table></td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                <!-- ======= end header ======= --> \n"
                + "                \n"
                + "                <!-- ======= start hero ======= -->\n"
                + "                \n"
                + "                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n"
                + "                  <tr>\n"
                + "                    <td background=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/42ba8b72-65d6-4dea-b8ab-3ecc12676337.jpg\" bgcolor=\"#2f9780\" width=\"100\" height=\"100\" valign=\"top\" align=\"center\" style=\"padding:50px 50px 50px 50px\">\n"
                + " \n"
                + "                      \n"
                + "                      <div>\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <p style=\"color:#ffffff; font-size:60px; text-align:center; font-family: Verdana, Geneva, sans-serif\">FIX IT</p>\n"
                + "                  \n"
                + "                      </div>\n"
                + "                      </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n"
                + "                  <tr>\n"
                + "                    <td align=\"center\" style=\"padding:50px 50px 50px 50px\"><p style=\"color:#262626; font-size:24px; text-align:center; font-family: Verdana, Geneva, sans-serif\"><strong>Produit " + this.prod.getNom() + "</strong></p>\n"
                + "                      <p style=\"color:#262626; font-size:16px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \">vous avez une commande de quantite " + quantitee
                + "                   <br />\n  à l'adresse : " + prod.getUser().getAddress() + " <br />\n"
                + "                      <p> vous devez l'envoyer avant 24 heures</p>\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                      </p>\n"
                + "                     \n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "                      <p style=\"color:#000000; font-size:12px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \"> <br />\n"
                + "                        <br />\n"
                + "                        Lorem Ipsum loren ipsum</p></td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        </div></td>\n"
                + "    </tr>\n"
                + "  </table>\n"
                + "</center>\n"
                + "</body>\n"
                + "</html>";
        MailService mailService = new MailService(prod.getUser().getEmail(), "produit", mailContentReclamant);

        try {
            mailService.sendEmail();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("message envoyée");
        alert.showAndWait();
    }

    @FXML
    private void changerAction(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }
    }

    @FXML
    private void disLikeActionClicked(ActionEvent event) {
        LikeProduit l = new LikeProduit();

        crud.supprimerLike(user.getId(), prod.getId());
        crud.NombreLike(prod.getId());

        refrechPage(prod);
    }

    private void loadDataFromDatabaseAchat() {
        try {
            Produit pro = new Produit();
            ObservableList<ListAchat> rs = pro.getAllAchat(user.getId());
            // paginationOutilFront.setPageFactory(this::createPage);
            listProduitAchete.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void RetourProduit(int idProduit1, int quantite1, int id11, int prix1) {
        Produit pro = new Produit();
        int vendeur = pro.User1(idProduit1);
        int yy = pro.verifierSolde(user.getId()) - prix1;
        int zz = pro.verifierSolde(pro.User1(idProduit1)) + prix1;

        int x = pro.verifierQuantite(idProduit1);
        System.err.println("Prix: " + prix1);
        if (pro.verifierSolde(vendeur) < prix1) {
            System.out.println("test retour");
        } else {
            pro.retourStock(quantite1 + x, idProduit1);
            System.out.println(pro.verifierSolde(user.getId()));
            System.out.println(pro.verifierSolde(pro.User1(idProduit1)));
            System.out.println((pro.User1(idProduit1)));
            int y = pro.verifierSolde(user.getId()) + prix1;
            int z = pro.verifierSolde(pro.User1(idProduit1)) - prix1;
            pro.ModifierUserAchat(pro.User1(idProduit1), z);
            pro.ModifierUserAchat(user.getId(), y);
            pro.supprimeAchat(id11);
            //System.out.println(id1);
            UserService us = new UserService();
            user = us.getUser(String.valueOf(user.getId()));
            frontIndexController.setUser(user);
            frontIndexController.refresh();
            Notifications notificationBuilder = Notifications.create().title("Notification").text("annulation de commande est faits avec succés").hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked");
        }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

        }

    }
}
