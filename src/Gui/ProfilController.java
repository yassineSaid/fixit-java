package Gui;

import Entities.CategorieService;
import Entities.Horraire;
import Entities.Langue;
import Entities.Repos;
import Entities.Service;
import Entities.ServiceUser;
import Entities.ServicesProposes;
import Entities.User;
import Services.CategorieServiceService;
import Services.HorraireService;
import Services.ImageService;
import Services.ReposService;
import Services.ServiceService;
import Services.ServiceUserService;
import Services.ServicesProposesService;
import Services.UserLangueService;
import Services.UserService;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yassine
 */

public class ProfilController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private TabPane Tabwidget;
    @FXML
    private Tab interfaceAjout;
    @FXML
    private Label email;
    @FXML
    private Label username;
    @FXML
    private ImageView photo;

    private User user;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private AnchorPane profil;
    @FXML
    private Button modifier;
    @FXML
    private TableView<Langue> table;
    @FXML
    private TableColumn<Langue, String> langue;
    @FXML
    private TableColumn<Langue, Button> supprimer;
    @FXML
    private ComboBox<Langue> listLangues;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Horraire> lundiTab;
    @FXML
    private TableView<Horraire> mardiTab;
    @FXML
    private TableView<Horraire> mercrediTab;
    @FXML
    private TableView<Horraire> jeudiTab;
    @FXML
    private TableView<Horraire> vendrediTab;
    @FXML
    private TableView<Horraire> dimancheTab;
    @FXML
    private TableView<Horraire> samediTab;
    @FXML
    private TableColumn<Horraire, Time> deb1;
    @FXML
    private TableColumn<Horraire, Time> fin1;
    @FXML
    private TableColumn<Horraire, Time> deb2;
    @FXML
    private TableColumn<Horraire, Time> fin2;
    @FXML
    private TableColumn<Horraire, Time> deb3;
    @FXML
    private TableColumn<Horraire, Time> fin3;
    @FXML
    private TableColumn<Horraire, Time> deb4;
    @FXML
    private TableColumn<Horraire, Time> fin4;
    @FXML
    private TableColumn<Horraire, Time> deb5;
    @FXML
    private TableColumn<Horraire, Time> fin5;
    @FXML
    private TableColumn<Horraire, Time> deb0;
    @FXML
    private TableColumn<Horraire, Time> fin0;
    @FXML
    private TableColumn<Horraire, Time> deb6;
    @FXML
    private TableColumn<Horraire, Time> fin6;
    @FXML
    private CheckBox rep1;
    @FXML
    private CheckBox rep3;
    @FXML
    private CheckBox rep5;
    @FXML
    private CheckBox rep0;
    @FXML
    private CheckBox rep2;
    @FXML
    private CheckBox rep4;
    @FXML
    private CheckBox rep6;
    @FXML
    private ComboBox<Repos> listJours;
    @FXML
    private TableColumn<?, ?> supp1;
    @FXML
    private TableColumn<?, ?> supp2;
    @FXML
    private TableColumn<?, ?> supp3;
    @FXML
    private TableColumn<?, ?> supp4;
    @FXML
    private TableColumn<?, ?> supp5;
    @FXML
    private TableColumn<?, ?> supp0;
    @FXML
    private TableColumn<?, ?> supp6;
    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXTimePicker heureFin;
    @FXML
    private Label erreurJour;
    @FXML
    private Label erreurHeure1;
    @FXML
    private Label erreurHeureFin;
    @FXML
    private Label erreurHorraire;
    @FXML
    private Label solde;
    @FXML
    private Button modifier1;
    @FXML
    private Tab interfaceAjout1;
    @FXML
    private ListView<ServiceUser> mesServices;
    @FXML
    private Button ajouterUnService;
    @FXML
    private Button proposerUnService;
    @FXML
    private AnchorPane service;
    @FXML
    private AnchorPane addService;
    @FXML
    private ComboBox<CategorieService> categorie;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private Button ajouterS;
    @FXML
    private ComboBox<Service> serviceC;
    @FXML
    private AnchorPane proposerS;
    @FXML
    private TextField descriptionProposition;
    @FXML
    private TextField nomProposition;
    @FXML
    private ComboBox<CategorieService> categorieProposition;
    @FXML
    private Button confirmerProposition;
    @FXML
    private Button retourAjouterService;
    @FXML
    private Button retourPropService;
    @FXML
    private Label labelMesServices;
    @FXML
    private Button supprimerSU;

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
                frontIndexController.getProfil().setStyle("-fx-background-color: #f4f4f4");
                username.setText(user.getUsername());
                nom.setText(user.getLastname());
                prenom.setText(user.getFirstname());
                email.setText(user.getEmail());
                heureDebut.set24HourView(true);
                heureFin.set24HourView(true);
                solde.setText("Vous avez " + String.valueOf(user.getSolde()) + " SCoins sur votre compte");
                loadImage();
                afficherLanguesAction();
                afficherHorraireAction();
                afficherReposAction();
                /*SLIM*/ 
                ServiceUserService r = new ServiceUserService();
                ObservableList<ServiceUser> list = FXCollections.observableArrayList();
                list = r.afficherServiceUser(this.user.getId());
               // mesServices.setItems(list);
                
            loadServiceUserFromDatabase();
            mesServices.setCellFactory(lv -> new Poules());
                
           
 
        });
    }
  private void loadServiceUserFromDatabase() {
        try {
            ServiceUserService su = new ServiceUserService();
            ObservableList<ServiceUser> rs = su.afficherServiceUser(this.getUser().getId());
            //paginationOutilFront.setPageFactory(this::createPage);
            mesServices.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void supprimerSU(ActionEvent event) {
        ServiceUserService su =new ServiceUserService();
        ServiceUser s = mesServices.getSelectionModel().getSelectedItem();
        su.supprimerServiceUser(s.getIdService(), s.getIdUser());
        this.initialize(null, null);
    }
  public class Poules extends ListCell<ServiceUser> {

        public Poules() {
        }

        protected void updateItem(ServiceUser item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                try {
                    ServiceUserService ss=new ServiceUserService();
                    Text nom = new Text(ss.getServiceName(item.getIdService()));
                    Text description = new Text(item.getDescription());
                    Text prix = new Text(Float.toString(item.getPrix()));
                    Text s =new Text("SCoins");
                    description.setWrappingWidth(300);;
                    nom.setStyle("-fx-font-size: 25 arial;");
                    description.setStyle("-fx-font-size: 15 arial;"
                            + "-fx-pref-width: 158px;");
                    VBox vBox = new VBox(nom, description);
                    vBox.setStyle("-fx-font-color: transparent;");
                    vBox.setSpacing(10);
                    
                    Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/service/" + ss.getServiceImage(item.getIdService()), 120, 120, false, false);
                    ImageView img = new ImageView(image);
                    
                    HBox hBox = new HBox(img, vBox,prix,s);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                    /*   listCategorie.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                    
                    CategorieService a = (CategorieService) listCategorie.getItems().get(listCategorie.getSelectionModel().getSelectedIndex());
                    loadServiceFromDatabase(a.getId());
                    listService.setCellFactory(lv -> new PoulesService());
                    System.out.println("test");
                    }
                    });*/
                } catch (SQLException ex) {
                    Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    @FXML
    private void modifierAction(ActionEvent event) {
        UserService us = new UserService();
        us.modifierNomPrenom(user, nom.getText(), prenom.getText());
        user = us.connect(user.getUsername());
        frontIndexController.setUser(user);
        frontIndexController.initialize(null, null);
    }

    private void afficherLanguesAction() {
        langue.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        UserLangueService uls = new UserLangueService();
        table.setItems(uls.getUserLangue(user));
        for (Langue l : table.getItems()) {
            l.getSupprimer().setOnAction(this::supprimerAction);
            
        }
        listLangues.setItems(uls.getLangues(user));
    }

    private void afficherReposAction() {
        ReposService rs = new ReposService();
        ObservableList<Repos> repos;
        repos = FXCollections.observableArrayList();
        rs.getUserRepos(user).forEach(r -> {
            if (r.getId() == 0) {
                rep0.setSelected(true);
            }
            if (r.getId() == 1) {
                rep1.setSelected(true);
            }
            if (r.getId() == 2) {
                rep2.setSelected(true);
            }
            if (r.getId() == 3) {
                rep3.setSelected(true);
            }
            if (r.getId() == 4) {
                rep4.setSelected(true);
            }
            if (r.getId() == 5) {
                rep5.setSelected(true);
            }
            if (r.getId() == 6) {
                rep6.setSelected(true);
            }
        });
        if (!rep0.selectedProperty().get()) {
            repos.add(new Repos(0, user.getId()));
        }
        if (!rep1.selectedProperty().get()) {
            repos.add(new Repos(1, user.getId()));
        }
        if (!rep2.selectedProperty().get()) {
            repos.add(new Repos(2, user.getId()));
        }
        if (!rep3.selectedProperty().get()) {
            repos.add(new Repos(3, user.getId()));
        }
        if (!rep4.selectedProperty().get()) {
            repos.add(new Repos(4, user.getId()));
        }
        if (!rep5.selectedProperty().get()) {
            repos.add(new Repos(5, user.getId()));
        }
        if (!rep6.selectedProperty().get()) {
            repos.add(new Repos(6, user.getId()));
        }
        listJours.setItems(repos);
    }

    private void afficherHorraireAction() {
        HorraireService hs = new HorraireService();
        deb0.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb1.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb2.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb3.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb4.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb5.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb6.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        fin0.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin1.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin2.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin3.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin4.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin5.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin6.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        supp0.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp1.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp2.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp3.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp4.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp5.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp6.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        dimancheTab.setItems(hs.getUserHorraire(user, 0));
        lundiTab.setItems(hs.getUserHorraire(user, 1));
        mardiTab.setItems(hs.getUserHorraire(user, 2));
        mercrediTab.setItems(hs.getUserHorraire(user, 3));
        jeudiTab.setItems(hs.getUserHorraire(user, 4));
        vendrediTab.setItems(hs.getUserHorraire(user, 5));
        samediTab.setItems(hs.getUserHorraire(user, 6));
        dimancheTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        lundiTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        mardiTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        mercrediTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        jeudiTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        vendrediTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        samediTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
    }

    private void loadImage() {
        File currDir = new File(System.getProperty("user.dir", "."));
        String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + user.getImage();
        Image image = new Image(path);
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

        }
    }

    private void supprimerAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        UserLangueService usl = new UserLangueService();
        usl.supprimerUserLangue(parseInt(button.getId()), user);
        afficherLanguesAction();
    }

    private void supprimerHorraireAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        HorraireService hs = new HorraireService();
        hs.supprimerHorraire(parseInt(button.getId()));
        afficherHorraireAction();
    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        UserLangueService uls = new UserLangueService();
        uls.ajouterUserLangue(listLangues.getValue().getId(), user);
        afficherLanguesAction();
    }

    @FXML
    private void reposAction(ActionEvent event) {
        ReposService rs = new ReposService();
        HorraireService hs = new HorraireService();
        CheckBox cb = (CheckBox) event.getSource();
        if (cb.selectedProperty().get()) {
            hs.supprimerJour(parseInt(cb.getId().substring(3)), user);
            rs.ajouterRepos(parseInt(cb.getId().substring(3)), user);
            afficherHorraireAction();
        } else {
            rs.supprimerRepos(parseInt(cb.getId().substring(3)), user);
        }
        afficherReposAction();
    }

    @FXML
    private void ajouterHorraireAction(ActionEvent event) {
        boolean erreur = false;
        HorraireService uls = new HorraireService();
        if (listJours.getValue() == null) {
            erreurJour.setText("Sélectionnez un jour");
            erreur = true;
        } else {
            erreurJour.setText("");
        }
        if (heureDebut.getValue() == null) {
            erreurHeure1.setText("Sélectionnez l'heure de début");
            erreur = true;
        } else {
            erreurHeure1.setText("");
        }
        if (heureFin.getValue() == null) {
            erreurHeureFin.setText("Sélectionnez l'heure de fin");
            erreur = true;
        } else {
            erreurHeureFin.setText("");
        }
        if (!erreur) {
            if (heureDebut.getValue().isAfter(heureFin.getValue())) {
                erreurHorraire.setText("Vérifiez les heures");
                erreur = true;
            } else if (!uls.checkHorraire(user, java.sql.Time.valueOf(heureDebut.getValue()), java.sql.Time.valueOf(heureFin.getValue()), listJours.getValue().getId())) {
                erreurHorraire.setText("Vérifiez les heures");
                erreur=true;
            }
            else 
            {
                erreurHorraire.setText("");
            }
        }
        if (!erreur) {
            uls.ajouterHorraire(user, java.sql.Time.valueOf(heureDebut.getValue()), java.sql.Time.valueOf(heureFin.getValue()), listJours.getValue().getId());
            afficherHorraireAction();
        }
    }

    @FXML
    private void acheterAction(ActionEvent event) throws IOException {
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
        modifierAction(event);
        solde.setText("Vous avez " + String.valueOf(user.getSolde()) + " SCoins sur votre compte");
    }

    @FXML
    private void changerPhotoAction(ActionEvent event) {
        UserService us = new UserService();
        File currDir = new File(System.getProperty("user.dir", "."));
        String filename;
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/user/";
            ImageService u = new ImageService();
            try {
                if (user.getImage() != null) {
                    File f = new File(currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + user.getImage());
                    f.delete();
                }
                filename=String.valueOf(user.getId())+"."+u.getFileExtension(file);
                u.uploadNew(file, path, filename);
                us.ajouterImage(filename, user.getId());
                user = us.connect(user.getUsername());
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
                loadImage();
            } catch (IOException ex) {
                Logger.getLogger(EspaceOutilBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //logooo = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }
    @FXML
    private void historiqueAction(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/HistoriquePaiement.fxml"));
        Parent root = fxmlLoader.load();
        HistoriquePaiementController controller = fxmlLoader.<HistoriquePaiementController>getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
    }

    
    @FXML
    private void ajouterUnService(ActionEvent event) {
        mesServices.setVisible(false);
        ajouterUnService.setVisible(false);
        proposerUnService.setVisible(false);
        addService.setVisible(true);
        retourAjouterService.setVisible(true);
        supprimerSU.setVisible(false);
        this.initialize(null, null);
        
            ObservableList<CategorieService> list = FXCollections.observableArrayList();
            CategorieServiceService r = new CategorieServiceService();
            list = r.getALLCategorie();
            categorie.setItems(list);
            
    }

    @FXML
    private void proposerUnService(ActionEvent event) {
        
        mesServices.setVisible(false);
        ajouterUnService.setVisible(false);
        proposerUnService.setVisible(false);
        addService.setVisible(false);
        proposerS.setVisible(true);
        retourPropService.setVisible(true);
        supprimerSU.setVisible(false);
        
            ObservableList<CategorieService> list = FXCollections.observableArrayList();
            CategorieServiceService r = new CategorieServiceService();
            list = r.getALLCategorie();
            categorieProposition.setItems(list);
    }

    @FXML
    private void ajouterS(ActionEvent event) {
        ServiceUser s= new ServiceUser();
        ServiceUserService su = new ServiceUserService();
        s.setDescription(description.getText());
        s.setPrix(Integer.parseInt(prix.getText()));
        s.setIdService(serviceC.getValue().getId());
        s.setIdUser(this.user.getId());
        su.ajouterServiceUser(s);
        addService.setVisible(false);
        labelMesServices.setVisible(true);
        ajouterUnService.setVisible(true);
        proposerUnService.setVisible(true);
        mesServices.setVisible(true);
        supprimerSU.setVisible(true);
        this.initialize(null, null);
        
    }

    @FXML
    private void cat(ActionEvent event) {
        
            ObservableList<Service> listS = FXCollections.observableArrayList();
            ServiceService s = new  ServiceService();
            listS = s.getAllServiceC(categorie.getValue().getId());
            serviceC.setItems(listS);
    }

    @FXML
    private void confirmerProposition(ActionEvent event) {
        ServicesProposes s= new ServicesProposes();
        ServicesProposesService sp=new ServicesProposesService();
        s.setNom(nomProposition.getText());
        s.setDescription(descriptionProposition.getText());
        s.setCategorieService(categorieProposition.getValue().toString());
        sp.ajouterService(s);
        proposerS.setVisible(false);
        labelMesServices.setVisible(true);
        ajouterUnService.setVisible(true);
        proposerUnService.setVisible(true);
        mesServices.setVisible(true);    
        supprimerSU.setVisible(true);
        this.initialize(null, null);
    }

    @FXML
    private void retourAjouterService(ActionEvent event) {
        addService.setVisible(false);
        labelMesServices.setVisible(true);
        ajouterUnService.setVisible(true);
        proposerUnService.setVisible(true);
        mesServices.setVisible(true);
        supprimerSU.setVisible(true);
    }

    @FXML
    private void retourPropService(ActionEvent event) {
        
        proposerS.setVisible(false);
        labelMesServices.setVisible(true);
        ajouterUnService.setVisible(true);
        proposerUnService.setVisible(true);
        mesServices.setVisible(true);
        supprimerSU.setVisible(true);
    }

    

    
}
