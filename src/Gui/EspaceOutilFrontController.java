/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Outil;
import Entities.User;
import Entities.UserOutil;
import Services.OutilService;
import Services.UserOutilService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SL-WASSIM
 */
public class EspaceOutilFrontController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;

    private User user;

    @FXML
    private ListView<Outil> list;
    @FXML
    private AnchorPane location;
    @FXML
    private ImageView imageDetail;
    @FXML
    private ImageView logoDetail;
    @FXML
    private ImageView adresseDetail;
    @FXML
    private ImageView scoinDetail;
    @FXML
    private Label logoDetailText;
    @FXML
    private Label adresseDetailText;
    @FXML
    private Label scoinDetailText;
    @FXML
    private Label outilEpuise;
    @FXML
    private AnchorPane detailLocation;
    @FXML
    private Label outilDejaLoue;
    @FXML
    private DatePicker dateLocation;
    @FXML
    private DatePicker dateRetour;
    @FXML
    private Label prixTotal;
    @FXML
    private ImageView scoin1;
    @FXML
    private Label prix1;
    @FXML
    private ImageView scoin11;
    @FXML
    private Label prix2;
    @FXML
    private ImageView scoin111;
    @FXML
    private Label limite1;
    @FXML
    private Label limite2;
    @FXML
    private ImageView scoin1111;
    @FXML
    private CheckBox conditions;
    @FXML
    private Label erreur;
    @FXML
    private Button buttonLouer;
    @FXML
    private Button buttonRetour;
    @FXML
    private Label jour;
    @FXML
    private Pagination paginationOutilFront;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            loadDataFromDatabase();
            list.setCellFactory(lv -> new Poules());
            list.setVisible(true);
            paginationOutilFront.setVisible(true);
            location.setVisible(false);
            outilDejaLoue.setVisible(false);
            outilEpuise.setVisible(false);
            erreur.setVisible(false);
            buttonLouer.setDisable(true);
        });
    }

    private Node createPage(int pageIndex) {
        OutilService recServ = new OutilService();

        ObservableList<Outil> data = FXCollections.observableArrayList();
        data = recServ.afficherOutil();
        int fromIndex = pageIndex * 2;
        int toIndex = Math.min(fromIndex + 2, data.size());
        list.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return list;
    }

    private void loadDataFromDatabase() {
        try {
            OutilService service = new OutilService();
            ObservableList<Outil> rs = service.afficherOutil();
            paginationOutilFront.setPageFactory(this::createPage);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void retourAction(ActionEvent event) {
        list.setVisible(true);
        paginationOutilFront.setVisible(true);
        location.setVisible(false);
        initialize(null, null);
    }

    @FXML
    private void louerAction(ActionEvent event) {
    }

    private boolean verifierDate(LocalDate dLocation, LocalDate dRetouer) {
        try {
        String d1 = dLocation.toString();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateL;
        dateL = formatter1.parse(d1);
        java.sql.Date dL = new java.sql.Date(dateL.getTime());
        String d2 = dLocation.toString();
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateR;
        dateR = formatter1.parse(d2);
        java.sql.Date dR = new java.sql.Date(dateR.getTime());
            System.out.println(dR.compareTo(dL));
            return true;
            /*if(dR.compareTo(dL)>0)
            {
            
            }*/
        } catch (ParseException ex) {
        Logger.getLogger(EspaceOutilFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @FXML
    private boolean verifierCheck() {
        if(conditions.isSelected())
        {
            return true;
        }
        return false;
    }

    public class Poules extends ListCell<Outil> {

        public Poules() {
        }

        protected void updateItem(Outil item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text prix = new Text(Integer.toString(item.getPrix()));
                Text adresse = new Text(item.getAdresse() + "  ," + item.getVille());
                Text nomCategorie = new Text(item.getNomCategorie());
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
                Image marker = new Image("file:/wamp64/www/fixit/web/service/images/icons/adresse.png", 30, 30, false, false);
                ImageView m = new ImageView(marker);
                Image scoin = new Image("file:/wamp64/www/fixit/web/service/images/icons/scoin.png", 30, 30, false, false);
                ImageView s = new ImageView(scoin);
                Image logo = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieOutil/" + item.getC().getLogo(), 30, 30, false, false);
                ImageView l = new ImageView(logo);
                HBox prixEnScoin = new HBox(s, prix);
                HBox adresseM = new HBox(m, adresse);
                HBox LogoCategorie = new HBox(l, nomCategorie);
                nom.setStyle("-fx-font-size: 35 arial;");
                prix.setStyle("-fx-font-size: 20 arial;");
                Button btn = new Button("Détail sur l'outil");
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
                        UserOutilService uo = new UserOutilService();
                        ObservableList<UserOutil> list1 = FXCollections.observableArrayList();
                        ObservableList<UserOutil> list2 = FXCollections.observableArrayList();
                        try {
                            list1 = uo.premierOutilRetourner(item);
                        } catch (SQLException ex) {
                            Logger.getLogger(EspaceOutilFrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            list2 = uo.verifierLocation(item, user);
                        } catch (SQLException ex) {
                            Logger.getLogger(EspaceOutilFrontController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (item.getQuantite() > 0) {
                            if (list2.isEmpty()) {
                                buttonLouer.setDisable(false);
                                detailLocation.setVisible(true);

                            } else {
                                outilDejaLoue.setVisible(true);
                                detailLocation.setVisible(true);
                            }
                        } else {
                            if (list1.isEmpty()) {
                                outilEpuise.setVisible(true);
                                detailLocation.setVisible(false);
                            } else if (!list2.isEmpty()) {
                                outilDejaLoue.setVisible(true);
                                detailLocation.setVisible(true);
                            } else {
                                outilEpuise.setVisible(true);
                                detailLocation.setVisible(false);
                                outilEpuise.setText("L'outil sera disponible");
                            }
                        }

                    }
                });
                VBox vBox = new VBox();
                if (item.getQuantite() == 0) {
                    VBox vBox1 = new VBox(nom, indisponible, LogoCategorie, adresseM, prixEnScoin, btn);
                    vBox = vBox1;
                } else {
                    VBox vBox2 = new VBox(nom, disponible, LogoCategorie, adresseM, prixEnScoin, btn);
                    vBox = vBox2;
                }
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);

                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/" + item.getImage(), 200, 200, false, false);
                ImageView img = new ImageView(image);

                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-font-color: transparent;");
                hBox.setSpacing(10);
                // hBox.setStyle("-fx-alignment: center ;");
                //hBox.gets
                setGraphic(hBox);
            }
        }
    }

}
