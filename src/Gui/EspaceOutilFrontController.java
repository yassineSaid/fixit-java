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
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button buttonLouer;
    @FXML
    private Button buttonRetour;
    @FXML
    private Label jour;
    @FXML
    private Pagination paginationOutilFront;
    @FXML
    private Label solde;
    @FXML
    private ImageView scoin12;
    @FXML
    private Button acheter;
    @FXML
    private Label erreur1;
    @FXML
    private Label erreur2;
    @FXML
    private Label erreur3;
    @FXML
    private TableColumn<UserOutil,String> imageMesOutils;
    @FXML
    private TableColumn<UserOutil,String> nomOutilMesOutils;
    @FXML
    private TableColumn<UserOutil,String> dateLocationMesOutils;
    @FXML
    private TableColumn<UserOutil,String> dateREtourMesOutils;
    @FXML
    private TableColumn<UserOutil,String> prixMesOutils;
    @FXML
    private TableView<UserOutil> tableMesOutils;
    private int idOutilInserer;
    
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
            frontIndexController.getEspaceOut().setStyle("-fx-background-color: #f4f4f4");
            imageMesOutils.setCellValueFactory(new PropertyValueFactory<>("im"));
                nomOutilMesOutils.setCellValueFactory(new PropertyValueFactory<>("Outil"));
                dateLocationMesOutils.setCellValueFactory(new PropertyValueFactory<>("dateLocation"));
                dateREtourMesOutils.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
                prixMesOutils.setCellValueFactory(new PropertyValueFactory<>("total"));
                
                UserOutilService uo  = new UserOutilService();
                tableMesOutils.setItems(uo.afficherOutilFront(user));
            
        });
        loadDataFromDatabase();
        list.setCellFactory(item -> new ListCell<Outil>(){
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
                btn.setOnMouseEntered(e -> btn.setStyle("    -fx-background-color: \n"
                        + "        linear-gradient(white, white),\n"
                        + "        linear-gradient(white 0%,     white,    white 100%),\n"
                        + "        linear-gradient(white 0%, white 50%);\n"
                        + "    -fx-font-weight: bold;    \n"
                        + "    -fx-background-radius: 8,7,6;\n"
                        + "    -fx-background-insets: 0,1,2;\n"
                        + "    -fx-font-size: 14px;\n"
                        + "	-fx-pref-height: 25px;\n"
                        + "    -fx-pref-width: 158px;\n"
                        + "    -fx-text-fill: #32CD32;\n"
                        + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"));
                btn.setOnMouseExited(e -> btn.setStyle("    -fx-background-color: \n"
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
                        + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"));
                
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
                        idOutilInserer=item.getId();
                        solde.setText(Integer.toString(user.getSolde()));
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
                                louerAction(event);
                                
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
                vBox.setStyle("-fx-background-color:  transparent;");
                vBox.setSpacing(10);
                
                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/" + item.getImage(), 200, 200, false, false);
                ImageView img = new ImageView(image);
                img.setStyle("-fx-background-color:  transparent");
                
                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-background-color:  transparent");
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
        });
        list.setVisible(true);
        paginationOutilFront.setVisible(true);
        location.setVisible(false);
        outilDejaLoue.setVisible(false);
        outilEpuise.setVisible(false);
        erreur1.setVisible(false);
        erreur2.setVisible(false);
        erreur3.setVisible(false);
        acheter.setVisible(false);
        buttonLouer.setDisable(true);
        list.setStyle("-fx-control-inner-background:  transparent;");
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
            //System.err.println("Got an exception! ");
            System.out.println("Gui.EspaceOutilFrontController.loadDataFromDatabase()");
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
    if(verifierCheck() && controleSaisieDate(event)){
    
         UserOutilService uoservice = new UserOutilService();
         OutilService os = new OutilService();
        
        UserOutil uo = new UserOutil();
        uo.setDateLocation(fromDateToSQLDate(dateLocation.getValue()));
        uo.setDateRetour(fromDateToSQLDate(dateRetour.getValue()));
        uo.setUser(user);
        uo.setTotal(Integer.parseInt(prixTotal.getText()));
        Outil o = uoservice.getOutil(idOutilInserer);
        os.updateQuantie(o);
        uo.setOutil(o);
        uoservice.inserer(uo);
        UserService us = new UserService();
        us.modifierSolde(user, idOutilInserer);
         user = us.connect(user.getUsername());
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
        solde.setText(String.valueOf(user.getSolde()));
        
        initialize(null, null);
    }
    
    }
    
    private boolean verifierDate(LocalDate dLocation, LocalDate dRetour) {
        
        java.sql.Date dL = fromDateToSQLDate(dLocation);
        java.sql.Date dR = fromDateToSQLDate(dRetour);
        Date ld = new Date();
        if (dR.compareTo(dL) >= 0) {
            return true;
        } else {
            return false;
        }
        
    }
    
    private java.sql.Date fromDateToSQLDate(LocalDate date) {
        try {
            String d1 = date.toString();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dateL;
            dateL = formatter1.parse(d1);
            java.sql.Date dL = new java.sql.Date(dateL.getTime());
            return dL;
        } catch (ParseException ex) {
            Logger.getLogger(EspaceOutilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    private int differenceEntreDeuxDatesEnJours(LocalDate date1, LocalDate date2) {
        
        try {
            String d1 = date1.toString();
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date11;
            date11 = formatter1.parse(d1);
            String d2 = date2.toString();
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date22;
            date22 = formatter1.parse(d2);
            int resultat = (int) ((date22.getTime() - date11.getTime()) / (1000 * 60 * 60 * 24));
            return resultat;
        } catch (ParseException ex) {
            Logger.getLogger(EspaceOutilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    private int calculerprix() {
        int prix;
        if (Integer.parseInt(jour.getText()) >= differenceEntreDeuxDatesEnJours(dateLocation.getValue(), dateRetour.getValue())) {
            return prix = differenceEntreDeuxDatesEnJours(dateLocation.getValue(), dateRetour.getValue()) * Integer.parseInt(prix1.getText());
        } else {
            return prix = differenceEntreDeuxDatesEnJours(dateLocation.getValue(), dateRetour.getValue()) * Integer.parseInt(prix2.getText());
        }
    }
    
    @FXML
    private boolean verifierCheck() {
        if (conditions.isSelected()) {
            erreur2.setVisible(false);
            conditions.setStyle("-fx-border-color: transparent;");
            return true;
        } else {
            erreur2.setVisible(true);
            erreur2.setText("vous devez accepté nos conditions!");
            conditions.setStyle("-fx-border-color: red;");
            return false;
        }
        
    }
    
    @FXML
    private boolean controleSaisieDate(ActionEvent event) {
        boolean test = false;
        
        if (dateRetour.getValue() != null) {
            try {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                c.setTime(sdf.parse(dateRetour.getValue().toString()));
                c.add(Calendar.DAY_OF_MONTH, 3);
                limite1.setText(fromDateToSQLDate(dateRetour.getValue()).toString());
                limite2.setText(sdf.format(c.getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(EspaceOutilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (dateLocation.getValue() != null) {
            LocalDate now = LocalDate.now();
            if (!verifierDate(now, dateLocation.getValue())) {
                
                erreur3.setVisible(true);
                erreur3.setText("Vous devez choisir une date supérieure à celle d'ajourd'hui !");
                
                dateLocation.setStyle("-fx-border-color: red;");
            } else {
                erreur3.setVisible(false);
                dateLocation.setStyle("-fx-border-color: transparent;");
            }
            
        }
        if (dateLocation.getValue() != null && dateRetour.getValue() != null) {
            
            if (!verifierDate(dateLocation.getValue(), dateRetour.getValue())) {
                erreur1.setVisible(true);
                erreur1.setText("Date invalide!");
                dateLocation.setStyle("-fx-border-color: red;");
                dateRetour.setStyle("-fx-border-color: red;");
                
            } else {
                erreur1.setVisible(false);
                dateLocation.setStyle("-fx-border-color: transparent;");
                dateRetour.setStyle("-fx-border-color: transparent;");
                prixTotal.setText(Integer.toString(calculerprix()));
                if (user.getSolde() >= (calculerprix() + 10)) {
                    prixTotal.setStyle("-fx-text-fill: #08941d;");
                    erreur1.setVisible(false);
                    test = true;
                } else if ((user.getSolde() >= calculerprix()) && (user.getSolde() < (calculerprix() + 10))) {
                    prixTotal.setStyle("-fx-text-fill: #f81919;");
                    erreur1.setVisible(true);
                    erreur1.setText("Il faut qu'il vous reste au moins 10 Scoins !");
                    acheter.setVisible(true);
                } else {
                    prixTotal.setStyle("-fx-text-fill: #f81919;");
                    erreur1.setVisible(true);
                    erreur1.setText("Solde insuffisant!");
                    acheter.setVisible(true);
                }
            }
        }
        return test;
    }
    
    @FXML
    private void acheterScoin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/paiement.fxml"));
        Parent root = fxmlLoader.load();
        PaiementController controller = fxmlLoader.<PaiementController>getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
        UserService us = new UserService();
        user = us.connect(user.getUsername());
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
        solde.setText(String.valueOf(user.getSolde()));
    }
    
    
    
}
