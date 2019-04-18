/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Avis;
import Entities.Bonus;
import Entities.ListAchat;
import Entities.Outil;
import Entities.User;
import Entities.UserOutil;
import Services.AvisService;
import Services.OutilService;
import Services.Produit;
import Services.RealisationServiceService;
import Services.UserOutilService;
import Services.UserService;
import Services.bonusService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.stage.Stage;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class FrontAccueilController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private GridView<VBox> users;
    @FXML
    private TextField rechercher;

    private User user;
    private String rech;
    @FXML
    private Pagination paginator;
    @FXML
    private ListView<Avis> listAvis;
    @FXML
    private AnchorPane recherchePane;
    @FXML
    private AnchorPane accueilPane;
    @FXML
    private ListView<User> emp;
    @FXML
    private Pagination outilDisponiblePagination;
    @FXML
    private ListView<Outil> outilDisponible;
    @FXML
    private Pagination mproduitPagination;
    @FXML
    private ListView<ListAchat> mproduit;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRech() {
        return rech;
    }

    public void setRech(String rech) {
        this.rech = rech;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accueilPane.setVisible(true);
        recherchePane.setVisible(false);
        Platform.runLater(() -> {
            frontIndexController.setUser(user);
            frontIndexController.loadImage();
            frontIndexController.refresh();
            frontIndexController.getAccueil().setStyle("-fx-background-color: #f4f4f4");
            init();
            Date date = new Date();

            long time = date.getTime();
            //System.out.println("Time in Milliseconds: " + time);

            Timestamp ts = new Timestamp(time);

            System.out.println(ts.getDate() + 1);
            if (ts.getDate() == 17) {
                bonus();
            } else {
                System.out.println("not today");
            }
            loadDataFromDatabase();
            afficherOutilsDisponobles();
        });
    }

    public void init() {
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            afficherUsers();
            rech = rechercher.getText();
        });
        rechercher.focusedProperty().addListener((ov, oldV, newV) -> {
            if (newV) {
                accueilPane.setVisible(false);
                recherchePane.setVisible(true);
                afficherUsers();
            }
        });
        if (rech != null) {
            rechercher.setText(rech);
            accueilPane.setVisible(false);
            recherchePane.setVisible(true);
        }
        afficherUsers();
        afficherAvis();
        EmployeDuMois();
    }

    public void afficherAvis() {
        AvisService avisServ = new AvisService();
        ObservableList<Avis> list = FXCollections.observableArrayList();
        list = avisServ.top5();
        paginator.setMaxPageIndicatorCount(5);
        paginator.setPageCount(list.size());
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            int pos = (paginator.getCurrentPageIndex() + 1) % paginator.getPageCount();
            paginator.setCurrentPageIndex(pos);
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        getAvis();
        listAvis.setCellFactory(item -> new ListCell<Avis>() {
            protected void updateItem(Avis item, boolean bln) {
                super.updateItem(item, bln);
                if (item != null) {
                    Text satisfaction = new Text(item.getSatisfaction());
                    Text description = new Text(item.getDescription());
                    Text userName = new Text(item.getUser().getUsername());
                    satisfaction.setStyle("-fx-font-size: 23 arial;");
                    description.setStyle("-fx-font-size: 14 arial;");
                    userName.setStyle("-fx-font-size: 18 arial;");
                    Rating rate = new Rating();
                    rate.setRating(item.getNote());
                    EventHandler<MouseEvent> handler = MouseEvent::consume;
                    rate.addEventFilter(MouseEvent.ANY, handler);
                    rate.setMaxHeight(1);
                    VBox vBox = new VBox(satisfaction, userName, description);
                    vBox.setStyle("-fx-font-color: transparent;");
                    vBox.setSpacing(10);
                    Text txt = new Text("");
                    VBox vBox1 = new VBox(txt, rate);
                    vBox1.setStyle("-fx-font-color: transparent;");
                    vBox1.setSpacing(15);
                    HBox hBox = new HBox(vBox1, vBox);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(50);
                    listAvis.setStyle("-fx-control-inner-background:  transparent; -fx-background-color:   rgba(255,255,255,0.1);");
                    setGraphic(hBox);

                    // hBox.setStyle("-fx-alignment: center ;");
                    //hBox.gets
                }
            }

        });
    }

    public void afficherUsers() {
        final String IDLE_STYLE = " -fx-background-color:  rgba(226,226,226,0.7); -fx-background-radius: 8,7,6;";
        final String HOVERED_STYLE = "-fx-background-color: rgba(255,255,255,0.3);";

        UserService us = new UserService();
        users.setItems(us.getUsersVbox(user.getId(), rechercher.getText()));

        users.setCellHeight(200);
        users.setCellWidth(150);
        users.setCellFactory(gridView -> new GridCell<VBox>() {
            public void updateItem(VBox item, boolean empty) {
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //setText(item.getName());
                    item.setCursor(Cursor.HAND);
                    item.setStyle(IDLE_STYLE);
                    item.setSpacing(10);
                    item.setOnMouseEntered(e -> item.setStyle(HOVERED_STYLE));
                    item.setOnMouseExited(e -> item.setStyle(IDLE_STYLE));
                    item.setOnMouseClicked((event) -> {
                        VBox v = (VBox) event.getSource();
                        System.out.println(v.getId());
                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/ProfilUser.fxml"));
                            Parent Rec = fxmlLoader.load();
                            ProfilUserController controller = fxmlLoader.<ProfilUserController>getController();
                            controller.setUser(user);
                            controller.setRech(rech);
                            controller.setId(v.getId());
                            Scene scene = new Scene(Rec);

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.show();
                            stage.setScene(scene);

                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    });
                    setGraphic(item);

                }

            }
        });

    }

    private ImageView loadImage(String imageUser) {
        File currDir = new File(System.getProperty("user.dir", "."));
        if (imageUser != null) {
            String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + imageUser;
            Image image = new Image(path);
            ImageView photo = new ImageView();
            photo.setFitWidth(100);
            photo.setFitHeight(100);
            photo.setImage(image);
            Image img = photo.getImage();
            if (img != null) {
                double w = 0;
                double h = 0;

                double ratioX = photo.getFitWidth() / img.getWidth();
                double ratioY = photo.getFitHeight() / img.getHeight();

                double reducCoeff = 0;
                if (ratioX >= ratioY) {
                    reducCoeff = ratioY;
                } else {
                    reducCoeff = ratioX;
                }

                w = img.getWidth() * reducCoeff;
                h = img.getHeight() * reducCoeff;

                photo.setX((photo.getFitWidth() - w) / 2);
                photo.setY((photo.getFitHeight() - h) / 2);
                return photo;
            }
        }
        return null;
    }

    public void getAvis() {

        AvisService avisServ = new AvisService();
        ObservableList<Avis> list = FXCollections.observableArrayList();
        list = avisServ.top5();
        listAvis.setItems(list);
        paginator.setPageFactory(this::createPage);
    }

    private Node createPage(int pageIndex) {

        AvisService avisServ = new AvisService();
        ObservableList<Avis> list = FXCollections.observableArrayList();
        list = avisServ.top5();
        int fromIndex;
        int toIndex;
        if (pageIndex == list.size()) {
            pageIndex = 0;
            fromIndex = pageIndex * 1;
            toIndex = Math.min(fromIndex + 1, list.size());
        } else {
            pageIndex = paginator.getCurrentPageIndex();
            fromIndex = pageIndex * 1;
            toIndex = Math.min(fromIndex + 1, list.size());
        }

        listAvis.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));

        return listAvis;
    }

    public void bonus() {
        bonusService bs = new bonusService();

        if (bs.getBonusExist(this.user.getId())) {
            System.out.println("you won't have a bonus !");
        } else {
            RealisationServiceService rss = new RealisationServiceService();
            if (rss.getAvgNoteUser(this.user.getId()) > 2) {
                Date localdate = new Date();
                java.sql.Date dateAttribution = new java.sql.Date(localdate.getTime());
                Bonus bonus = new Bonus(this.user, dateAttribution, 25);
                bs.insertBonus(bonus);
                UserService us = new UserService();
                us.modifierSolde(this.user, 25);
                this.setUser(us.getUser(Integer.toString(this.user.getId())));
                frontIndexController.setUser(this.user);
                frontIndexController.refresh();
                URL path = getClass().getResource("/Resources/scoin.png");
                Image img = new Image(path.toString());
                Notifications notificationBuilder = Notifications.create().title("Notification").text("Vous avez été bien noté pendant ce mois vous aurez un bonus de 25 SCOINs").graphic(new ImageView(img)).hideAfter(Duration.seconds(10)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked");
                    }
                });
                notificationBuilder.darkStyle();
                notificationBuilder.show();
            } else {
                System.out.println("you won't have cuz your average");
            }
        }
    }

    public void EmployeDuMois() {
        UserService us = new UserService();

        RealisationServiceService rss = new RealisationServiceService();
        ObservableList<User> list = FXCollections.observableArrayList();
        int[] tab = rss.getEmployeeoftheMonth();
        User u = us.getUser(Integer.toString(tab[1]));
        list.add(u);
        emp.setItems(list);
        emp.setCellFactory(item -> new ListCell<User>() {
            protected void updateItem(User item, boolean bln) {
                super.updateItem(item, bln);
                if (item != null) {
                    Text userName = new Text(item.getFirstname() + " " + item.getLastname());
                    userName.setStyle("-fx-font-size: 25 arial;");

                    Rating rate = new Rating();
                    rate.setRating(tab[0]);
                    EventHandler<MouseEvent> handler = MouseEvent::consume;
                    rate.addEventFilter(MouseEvent.ANY, handler);
                    rate.setMaxHeight(1);
                    VBox vBox = new VBox(userName, rate);
                    vBox.setStyle("-fx-font-color: transparent;");
                    File currDir = new File(System.getProperty("user.dir", "."));
                    String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit1\\web\\uploads\\images\\user\\" + item.getImage();
                    Image image = new Image(path, 100, 100, false, false);
                    ImageView img = new ImageView(image);

                    VBox vBox1 = new VBox(img, vBox);
                    vBox1.setStyle("-fx-font-color: transparent;");
                    vBox1.setSpacing(15);
                    HBox hBox = new HBox(vBox1, vBox);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(50);

                    setGraphic(hBox);
                    emp.setStyle("-fx-control-inner-background:  transparent; -fx-background-color:   rgba(255,255,255,0.1);");
                    // hBox.setStyle("-fx-alignment: center ;");
                    //hBox.gets
                }
            }

        });
    }
    private Node createPageOutil(int pageIndex) {
        OutilService recServ = new OutilService();
        
        ObservableList<Outil> data = FXCollections.observableArrayList();
        data = recServ.afficherOutil();
        int fromIndex = pageIndex * 1;
        int toIndex = Math.min(fromIndex + 1, data.size());
        outilDisponible.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        
        return outilDisponible;
    }
    private void loadDataFromDatabase() {
        try {
            OutilService service = new OutilService();
            ObservableList<Outil> rs = service.afficherOutilDisponible();
            outilDisponiblePagination.setPageFactory(this::createPageOutil);
            outilDisponiblePagination.setStyle("-fx-control-inner-background:  gris; -fx-background-color:   rgba(255,255,255,0.1);");
        } catch (Exception e) {
            //System.err.println("Got an exception! ");
            System.out.println("load outil front failed accueil");
            System.err.println(e.getMessage());
        }
    }

    public void afficherOutilsDisponobles() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
        int pos = (outilDisponiblePagination.getCurrentPageIndex() + 1) % outilDisponiblePagination.getPageCount();
        outilDisponiblePagination.setCurrentPageIndex(pos);
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        outilDisponible.setCellFactory(item -> new ListCell<Outil>() {
            protected void updateItem(Outil item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text prix = new Text(Integer.toString(item.getPrix()));
                Text adresse = new Text(item.getAdresse() + "  ," + item.getVille());
                Text nomCategorie = new Text(item.getNomCategorie());
                Image marker = new Image("file:/wamp64/www/fixit/web/service/images/icons/adresse.png", 30, 30, false, false);
                ImageView m = new ImageView(marker);
                Image scoin = new Image("file:/wamp64/www/fixit/web/service/images/icons/scoin.png", 30, 30, false, false);
                ImageView s = new ImageView(scoin);
                Image logo = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieOutil/" + item.getC().getLogo(), 30, 30, false, false);
                ImageView l = new ImageView(logo);
                HBox prixEnScoin = new HBox(s, prix);
                HBox adresseM = new HBox(m, adresse);
                HBox LogoCategorie = new HBox(l, nomCategorie);
                nom.setStyle("-fx-font-size: 30 arial;");
                prix.setStyle("-fx-font-size: 20 arial;");
                VBox vBox = new VBox(nom, LogoCategorie, adresseM, prixEnScoin);
                vBox.setStyle("-fx-background-color:  transparent;");
                vBox.setSpacing(10);
                
                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/" + item.getImage(), 150, 150, false, false);
                ImageView img = new ImageView(image);
                img.setStyle("-fx-background-color:  transparent");
                
                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-background-color:  transparent");
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
        });
    }
    private Node createPageProduit(int pageIndex) {
        Produit recServ = new Produit();
        
        ObservableList<ListAchat> data = FXCollections.observableArrayList();
        data = recServ.topProduit();
        int fromIndex = pageIndex * 1;
        int toIndex = Math.min(fromIndex + 1, data.size());
        mproduit.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        
        return outilDisponible;
    }
    private void loadDataFromDatabaseProduit() {
        try {
            Produit service = new Produit();
            ObservableList<ListAchat> rs = service.topProduit();
            mproduitPagination.setPageFactory(this::createPageProduit);
            mproduitPagination.setStyle("-fx-control-inner-background:  transparent; -fx-background-color:   rgba(255,255,255,0.1);");
        } catch (Exception e) {
            //System.err.println("Got an exception! ");
            System.out.println("load outil front failed accueil");
            System.err.println(e.getMessage());
        }
    }

    public void affichertopProduit() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
        int pos = (mproduitPagination.getCurrentPageIndex() + 1) % mproduitPagination.getPageCount();
        mproduitPagination.setCurrentPageIndex(pos);
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        mproduit.setCellFactory(item -> new ListCell<ListAchat>() {
            protected void updateItem(ListAchat item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text prix = new Text(Integer.toString(item.getPrix()));
                Text quantite = new Text(Integer.toString(item.getQuantite()));
                Image marker = new Image("file:/wamp64/www/fixit/web/service/images/icons/quantite.png", 30, 30, false, false);
                ImageView q = new ImageView(marker);
                Image scoin = new Image("file:/wamp64/www/fixit/web/service/images/icons/scoin.png", 30, 30, false, false);
                ImageView s = new ImageView(scoin);
                HBox prixEnScoin = new HBox(s, prix);
                HBox quantitlogo = new HBox(q, quantite);
                nom.setStyle("-fx-font-size: 30 arial;");
                prix.setStyle("-fx-font-size: 20 arial;");
                quantite.setStyle("-fx-font-size: 20 arial;");
                VBox vBox = new VBox(nom, prixEnScoin, quantitlogo);
                vBox.setStyle("-fx-background-color:  transparent;");
                vBox.setSpacing(10);
                
                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + item.getImage(), 150, 150, false, false);
                ImageView img = new ImageView(image);
                img.setStyle("-fx-background-color:  transparent");
                
                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-background-color:  transparent");
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
        });
    }

}
