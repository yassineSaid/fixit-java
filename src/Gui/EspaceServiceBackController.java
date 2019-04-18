/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Service;
import Entities.ServiceUser;
import Entities.ServicesProposes;
import Entities.User;
import Services.CategorieServiceService;
import Services.Connexion;
import Services.ImageService;
import Services.ServiceService;
import Services.ImageService;
import Services.QuizUserService;
import Services.ServicesProposesService;
import Services.ServiceUserService;
import Services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class EspaceServiceBackController implements Initializable {

    @FXML
    private AnchorPane listCat;
    @FXML
    private TableView<CategorieService> categorie;
    @FXML
    private TableColumn<CategorieService, String> imageCatAff;
    @FXML
    private TableColumn<CategorieService, String> nomCatAff;
    @FXML
    private TableColumn<CategorieService, String> descriptionCatAff;
    @FXML
    private Button supprimer;
    @FXML
    private Button ajouterC;
    @FXML
    private TextField rechercherCat;
    @FXML
    private Label labelCategorie;
    @FXML
    private Button modifier;
    @FXML
    private TextField nomModif;
    @FXML
    private Label labNomModif;
    @FXML
    private Label labDescriptionModif;
    @FXML
    private TextArea descriptionModif;
    @FXML
    private Button importerImage;
    @FXML
    private TableView<Service> service;
    @FXML
    private TableColumn<Service, String> imageServiceAff;
    @FXML
    private TableColumn<Service, String> nomServiceAff;
    @FXML
    private TableColumn<Service, String> descriptionServiceAff;
    @FXML
    private TableColumn<Service, String> categorieServiceAff;
    @FXML
    private Button supprimerService;
    @FXML
    private AnchorPane ajouterServ;
    @FXML
    private ComboBox<CategorieService> categoS;
    @FXML
    private TextArea descriptionService;
    @FXML
    private Button ajoutService;
    @FXML
    private TextField nomService;
    @FXML
    private Button ajouterS;
    @FXML
    private Label labelService;
    String logooo = "";
    String imageee = "";
    String img = "";

    /**
     * Initializes the controller class.
     */
    private User user;
    @FXML
    private Button modifierService;
    @FXML
    private Button modifierS;
    @FXML
    private Button retourService;
    @FXML
    private TableColumn<Service, String> nbrProvidersAff;
    @FXML
    private Button openFileService;
    @FXML
    private Button supprimerHistorique;
    @FXML
    private Button recupererHistorique;
    @FXML
    private TableView<Service> historique;
    @FXML
    private TableColumn<Service, String> imageHistoriqueAff;
    @FXML
    private TableColumn<Service, String> nomHistoriqueAff;
    @FXML
    private TableColumn<Service, String> descriptionHistoriqueAff;
    @FXML
    private TableColumn<Service, String> categorieHistoriqueAff;
    @FXML
    private TableColumn<Service, String> nbrHistoriqueAff;
    @FXML
    private TextField rechercherService;
    @FXML
    private Pagination pagination;
    @FXML
    private Pagination paginationS;
    @FXML
    private BarChart<String, Number> stat;
    @FXML
    private TableView<ServicesProposes> tableProposition;
    @FXML
    private Button traiterProposition;
    @FXML
    private Button rejeterProposition;
    @FXML
    private TableColumn<ServicesProposes, String> nomServicePropose;
    @FXML
    private TableColumn<ServicesProposes, String> categorieServicePropose;
    @FXML
    private TableColumn<ServicesProposes, String> descriptionServicePropose;
    @FXML
    private TextField nomProposition;
    @FXML
    private ComboBox<CategorieService> categorieProposition;
    @FXML
    private TextField descriptionProposition;
    @FXML
    private Button imageProposition;
    @FXML
    private Button confirmerProposition;
    @FXML
    private Button retourProposition;
    @FXML
    private AnchorPane traitementProposition;
    @FXML
    private Label erreurService;
    private ImageView imageNotification;
    @FXML
    private ImageView imageNotificationDelete;
    @FXML
    private ImageView imageNotificationTik;
    @FXML
    private ImageView imageUserStat;
    @FXML
    private Label nomUserStat;
    @FXML
    private Label nbrServiceStat;
    @FXML
    private PieChart quizStat;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {

            ServiceService s = new ServiceService();
            stat.getData().add(s.graph());
            Connection c = Connexion.getInstance().getCon();
            refreshCategorie();
            refreshService();
            afficherHistorique();
            afficherProposition();
            initTableColumn();
            pagination.setPageFactory(this::createPage);
            paginationS.setPageFactory(this::createPageService);
            searchCategorie();
            searchService();
            refreshProposition();
            ActifUser();

            QuizUserService su = new QuizUserService();
            ObservableList<Integer> list = FXCollections.observableArrayList();
            int i, j, k;
            i = su.nombreUserQuiz1();
            j = su.nombreUserQuiz2();
            k = su.nombreUserQuiz3();

            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Quiz 1", i),
                            new PieChart.Data("Quiz 2", j),
                            new PieChart.Data("Quiz 3", k));
            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Imported Fruits");
            quizStat.setData(pieChartData);
            final Label caption = new Label("");
            //caption.setTextFill(Color.DARKORANGE);
            caption.setStyle("-fx-font: 24 arial;");
            quizStat.getData().forEach(data -> {
                 String percentage = String.format("%.0f",data.getPieValue());
                Tooltip toolTip = new Tooltip(percentage);
                Tooltip.install(data.getNode(), toolTip);
            });
        });

    }

    private void ActifUser() {

        ServiceUserService p = new ServiceUserService();

        ObservableList<Integer> test = FXCollections.observableArrayList();
        UserService us = new UserService();
        User u = new User();
        try {

            test = p.getActifUser();
            u = us.getUser(Integer.toString(test.get(0)));
            Image im = new Image("file:/wamp64/www/fixit/web/uploads/images/user/" + u.getImage(), 232, 202, false, false);
            //ImageView i=new ImageView(im);
            System.out.println(u.getImage());
            imageUserStat.setImage(im);
            nomUserStat.setText(u.getFirstname() + " " + u.getLastname());
            nbrServiceStat.setText(Integer.toString(test.get(1)) + " Services");
        } catch (SQLException ex) {
            Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshProposition() {
        traitementProposition.setVisible(false);
        traiterProposition.setVisible(true);
        rejeterProposition.setVisible(true);
        tableProposition.setVisible(true);
        traiterProposition.setDisable(true);
        rejeterProposition.setDisable(true);
    }

    private void searchCategorie() {
        ObservableList data = categorie.getItems();
        rechercherCat.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                categorie.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<CategorieService> subentries = FXCollections.observableArrayList();

            long count = categorie.getColumns().stream().count();
            for (int i = 0; i < categorie.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + categorie.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(categorie.getItems().get(i));
                        break;
                    }
                }
            }
            categorie.setItems(subentries);
        });
    }

    private void searchService() {
        ObservableList data2 = service.getItems();
        rechercherService.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                service.setItems(data2);
            }
            String value = newValue.toLowerCase();
            ObservableList<Service> subentries = FXCollections.observableArrayList();

            long count = service.getColumns().stream().count();
            for (int i = 0; i < service.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + service.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(service.getItems().get(i));
                        break;
                    }
                }
            }
            service.setItems(subentries);
        });
    }

    private void initTableColumn() {
        nomCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService, String>("nom"));
        descriptionCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService, String>("description"));
        imageCatAff.setCellValueFactory(new PropertyValueFactory<>("im"));

        imageServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("im"));
        nomServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("nom"));
        descriptionServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("description"));
        categorieServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("Categorie"));
        nbrProvidersAff.setCellValueFactory(new PropertyValueFactory<Service, String>("nbrProviders"));

        imageHistoriqueAff.setCellValueFactory(new PropertyValueFactory<Service, String>("im"));
        nomHistoriqueAff.setCellValueFactory(new PropertyValueFactory<Service, String>("nom"));
        descriptionHistoriqueAff.setCellValueFactory(new PropertyValueFactory<Service, String>("description"));
        categorieHistoriqueAff.setCellValueFactory(new PropertyValueFactory<Service, String>("Categorie"));
        nbrHistoriqueAff.setCellValueFactory(new PropertyValueFactory<Service, String>("nbrProviders"));

        nomServicePropose.setCellValueFactory(new PropertyValueFactory<ServicesProposes, String>("nom"));
        categorieServicePropose.setCellValueFactory(new PropertyValueFactory<ServicesProposes, String>("categorieService"));
        descriptionServicePropose.setCellValueFactory(new PropertyValueFactory<ServicesProposes, String>("description"));
    }

    private void afficherHistorique() {

        try {

            ServiceService serv = new ServiceService();

            historique.setItems(serv.afficherServiceHistorique());
        } catch (SQLException ex) {
            Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficherProposition() {

        try {
            ServicesProposesService sps = new ServicesProposesService();
            tableProposition.setItems(sps.afficherServicesProposes());
        } catch (SQLException ex) {
            Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshService() {
        service.setVisible(true);
        rechercherService.setVisible(true);
        paginationS.setVisible(true);
        ajouterS.setVisible(true);
        modifierS.setVisible(true);
        supprimerService.setVisible(true);
        ajouterServ.setVisible(false);
    }

    private void refreshCategorie() {

        modifier.setDisable(true);
        supprimer.setDisable(true);
    }

    private Node createPage(int pageIndex) {
        try {

            CategorieServiceService cs = new CategorieServiceService();

            ObservableList<CategorieService> data = FXCollections.observableArrayList();
            data = cs.afficherCategorie();
            int fromIndex = pageIndex * 2;
            int toIndex = Math.min(fromIndex + 2, data.size());
            categorie.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

            return categorie;
        } catch (SQLException ex) {

            System.out.println(ex);
        }
        return null;
    }

    private Node createPageService(int pageIndex) {
        try {

            ServiceService cs = new ServiceService();

            ObservableList<Service> data = FXCollections.observableArrayList();
            data = cs.afficherService();
            int fromIndex = pageIndex * 2;
            int toIndex = Math.min(fromIndex + 2, data.size());
            service.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

            return service;
        } catch (SQLException ex) {

            System.out.println(ex);
        }
        return null;
    }

    @FXML
    private void itemSelectedC(MouseEvent event) {
        modifier.setDisable(false);
        supprimer.setDisable(false);

        CategorieService c = categorie.getSelectionModel().getSelectedItem();

        nomModif.setText(c.getNom());
        descriptionModif.setText(c.getDescription());
    }

    @FXML
    private void supprimerC(ActionEvent event) {
        CategorieServiceService categorieS = new CategorieServiceService();
        CategorieService c = categorie.getSelectionModel().getSelectedItem();
        categorieS.supprimerCategorie(c.getId());
        Image img = imageNotificationDelete.getImage();

        Notifications notificationBuilder = Notifications.create().title("Notification").text("La Catégorie " + c.getNom() + " a été supprimé avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        System.out.println("categorie supprimer");
        initialize(null, null);
    }

    @FXML
    private void ajouterC(ActionEvent event) {
        String e= "Vous devez remplir le(s) champ(s)";
        int i = 0;
        if (nomModif.getText().equals("")) {
            nomModif.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            i = 1;
            e+="\nnom";
        } else {
            nomModif.setStyle("-fx-border-color: none ;");
        }
        if (descriptionModif.getText().equals("")) {
            descriptionModif.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            i = 1;
            e+="\ndescription";
        } else {
            descriptionModif.setStyle("-fx-border-color: none ;");
        }
        if (i==1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Categorie");
            alert.setHeaderText(null);
            alert.setContentText(e);
            alert.showAndWait();
        }
        else{
            CategorieServiceService categorieS = new CategorieServiceService();

            CategorieService c = new CategorieService();
            c.setNom(nomModif.getText());
            c.setDescription(descriptionModif.getText());
            c.setImage(logooo);
            categorieS.ajouterCategorie(c);
            Image img = imageNotificationTik.getImage();

            Notifications notificationBuilder = Notifications.create().title("Notification").text("La Catégorie " + c.getNom() + " a été ajoutée avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            System.out.println("categorie ajoutée");
            logooo = "";
            nomModif.clear();
            descriptionModif.clear();

            initialize(null, null);
        }
    }

    @FXML
    private void modifierC(ActionEvent event) {
        CategorieServiceService categorieS = new CategorieServiceService();
        CategorieService c = new CategorieService();

        c = categorie.getSelectionModel().getSelectedItem();
        c.setNom(nomModif.getText());
        c.setDescription(descriptionModif.getText());
        if (logooo != "") {
            c.setImage(logooo);
        }
        categorieS.modifierCategorie(c);
        Image img = imageNotificationTik.getImage();

        Notifications notificationBuilder = Notifications.create().title("Notification").text("La catégorie " + c.getNom() + " a été modifié avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        logooo = "";
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

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/categorieService/";
            ImageService u = new ImageService();
            try {
                u.upload(file, path);
            } catch (IOException ex) {
                Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            logooo = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }

    @FXML
    private void supprimerService(ActionEvent event) {
        ServiceService categorieS = new ServiceService();
        Service c = service.getSelectionModel().getSelectedItem();
        //categorieS.supprimerService(c.getId());
        c.setVisible(0);
        categorieS.modifierService(c);
        System.out.println("service supprimer");
        Image img = imageNotificationDelete.getImage();

        Notifications notificationBuilder = Notifications.create().title("Notification").text("Le Service " + c.getNom() + " a été supprimé avec succés vous pouvez le récupérer depuis l'historique").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
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
    private void ajouterService(ActionEvent event) {
        String e = "Vous devez remplir le(s) champ(s) ";
        int i = 0;
        if (nomService.getText().equals("")) {
            nomService.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            e += "\nnom ";
            i = 1;
        } else {
            nomService.setStyle("-fx-border-color: none ;");
        }
        if (descriptionService.getText().equals("")) {
            descriptionService.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            e += " \ndescription ";
            i = 1;
        } else {
            descriptionService.setStyle("-fx-border-color: none ;");
        }
        if (categoS.getValue() == null) {
            categoS.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            e += "\ncategorie ";
            i = 1;
        }
        categoS.setStyle("-fx-border-color: none ;");
        if (i==1) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Service");
            alert.setHeaderText(null);
            alert.setContentText(e);
            alert.showAndWait();
            e = "";
        } else {
            nomService.setStyle(null);
            ServiceService ss = new ServiceService();
            Service s = new Service();
            s.setNom(nomService.getText());
            s.setDescription(descriptionService.getText());
            s.setCategorie(categoS.getValue());
            s.setNbrProviders(0);
            s.setVisible(1);
            s.setImage(imageee);
            ss.ajouterService(s);
            Image img = imageNotificationTik.getImage();

            Notifications notificationBuilder = Notifications.create().title("Notification").text("Le Service " + s.getNom() + " a été ajouté avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            System.out.println("service ajoutée");
            imageee = "";
            nomService.clear();
            descriptionService.clear();

            initialize(null, null);
        }

    }

    @FXML
    private void ajouterS(ActionEvent event) {
        rechercherService.setVisible(false);
        paginationS.setVisible(false);
        ajouterServ.setVisible(true);
        supprimerService.setVisible(false);
        ajouterS.setVisible(false);
        modifierS.setVisible(false);
        service.setVisible(false);
        labelService.setVisible(false);
        modifierService.setVisible(false);
        ajoutService.setVisible(true);
        ObservableList<CategorieService> list = FXCollections.observableArrayList();
        CategorieServiceService r = new CategorieServiceService();
        list = r.getALLCategorie();
        categoS.setItems(list);

    }

    @FXML
    private void modifierService(ActionEvent event) {

        ServiceService categorieS = new ServiceService();
        Service c = new Service();

        c = service.getSelectionModel().getSelectedItem();
        c.setNom(nomService.getText());
        c.setDescription(descriptionService.getText());
        c.setCategorie(categoS.getValue());
        c.setVisible(1);
        if (imageee != "") {
            c.setImage(imageee);
        }
        categorieS.modifierService(c);
        Image img = imageNotificationTik.getImage();

        Notifications notificationBuilder = Notifications.create().title("Notification").text("Le Service " + c.getNom() + " a été modifié").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        imageee = "";
        initialize(null, null);
    }

    @FXML
    private void itemSelectedS(MouseEvent event) {
        modifierS.setDisable(false);
        supprimerService.setDisable(false);
    }

    @FXML
    private void modifierserv(ActionEvent event) {
        Service s = service.getSelectionModel().getSelectedItem();
        ajouterServ.setVisible(true);
        supprimerService.setVisible(false);
        ajouterS.setVisible(false);
        modifierS.setVisible(false);
        service.setVisible(false);
        labelService.setVisible(false);
        modifierService.setVisible(true);
        ajoutService.setVisible(false);
        nomService.setText(s.getNom());
        rechercherService.setVisible(false);
        descriptionService.setText(s.getDescription());
        categoS.setValue(s.getCategorie());
        ObservableList<CategorieService> list = FXCollections.observableArrayList();
        CategorieServiceService r = new CategorieServiceService();
        list = r.getALLCategorie();
        categoS.setItems(list);
        rechercherService.setVisible(true);
        paginationS.setVisible(false);
    }

    @FXML
    private void retourService(ActionEvent event) {

        ajouterServ.setVisible(false);
        labelService.setVisible(true);
        service.setVisible(true);
        ajouterS.setVisible(true);
        modifierS.setVisible(true);
        supprimerService.setVisible(true);
        service.setVisible(true);
        rechercherService.setVisible(true);
        paginationS.setVisible(true);

    }

    @FXML
    private void importerImageService(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

            File currDir = new File(System.getProperty("user.dir", "."));
            System.out.println(currDir.toPath().getRoot().toString());

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/service/";
            ImageService u = new ImageService();
            try {
                u.upload(file, path);
            } catch (IOException ex) {
                Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imageee = file.getName();
            System.out.println(imageee);
        } else {
            System.out.println("FICHIER erroné");
        }
    }

    @FXML
    private void supprimerHistorique(ActionEvent event) {
        ServiceService ss = new ServiceService();
        Service s = historique.getSelectionModel().getSelectedItem();
        Image img = imageNotificationDelete.getImage();

        Notifications notificationBuilder = Notifications.create().title("Notification").text("Le Service " + s.getNom() + " a été supprimé définitivement").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        ss.supprimerService(s.getId());
        this.initialize(null, null);
    }

    @FXML
    private void recupererHistorique(ActionEvent event) {
        ServiceService ss = new ServiceService();
        Service s = new Service();
        s = historique.getSelectionModel().getSelectedItem();
        s.setVisible(1);
        Image img = imageNotificationTik.getImage();

        Notifications notificationBuilder = Notifications.create().title("Notification").text("Le service " + s.getNom() + " a été récuperé avec succés").graphic(new ImageView(img)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        System.out.println(s.getId() + " " + s.getNom() + " " + s.getVisible());
        ss.modifierService(s);
        this.initialize(null, null);

    }

    @FXML
    private void traiterProposition(ActionEvent event) {
        traitementProposition.setVisible(true);
        traiterProposition.setVisible(false);
        rejeterProposition.setVisible(false);
        tableProposition.setVisible(false);

        ServicesProposes sp = new ServicesProposes();
        sp = tableProposition.getSelectionModel().getSelectedItem();
        nomProposition.setText(sp.getNom());
        descriptionProposition.setText(sp.getDescription());
        //    categorieProposition.setValue(sp.getCategorieService().con);
        ObservableList<CategorieService> list = FXCollections.observableArrayList();
        CategorieServiceService r = new CategorieServiceService();
        list = r.getALLCategorie();
        categorieProposition.setItems(list);
    }

    @FXML
    private void rejeterProposition(ActionEvent event) {
        ServicesProposes sp = new ServicesProposes();
        ServicesProposesService sps = new ServicesProposesService();
        sp = tableProposition.getSelectionModel().getSelectedItem();
        sps.supprimerServicePropose(sp.getId());

    }

    @FXML
    private void itemSelectedP(MouseEvent event) {
        traiterProposition.setDisable(false);
        rejeterProposition.setDisable(false);
        /*ServicesProposes sp =new ServicesProposes();
        sp=tableProposition.getSelectionModel().getSelectedItem();*/
    }

    @FXML
    private void confirmerProposition(ActionEvent event) {
        ServiceService ss = new ServiceService();
        Service s = new Service();
        ServicesProposesService sps = new ServicesProposesService();
        ServicesProposes sp = new ServicesProposes();
        s.setNom(nomProposition.getText());
        s.setDescription(descriptionProposition.getText());
        s.setCategorie(categorieProposition.getValue());
        s.setVisible(1);
        s.setNbrProviders(0);
        if (img != "") {
            s.setImage(img);
        }
        ss.ajouterService(s);
        sps.supprimerServicePropose(tableProposition.getSelectionModel().getSelectedItem().getId());

        img = "";
    }

    @FXML
    private void retourProposition(ActionEvent event) {

        traitementProposition.setVisible(false);
        traiterProposition.setVisible(true);
        rejeterProposition.setVisible(true);
        tableProposition.setVisible(true);
    }

    @FXML
    private void importerImageProposition(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

            File currDir = new File(System.getProperty("user.dir", "."));
            System.out.println(currDir.toPath().getRoot().toString());

            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/service/";
            ImageService u = new ImageService();
            try {
                u.upload(file, path);
            } catch (IOException ex) {
                Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            img = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }

}
