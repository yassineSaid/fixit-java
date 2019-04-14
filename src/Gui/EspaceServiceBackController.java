/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Service;
import Entities.User;
import Services.CategorieServiceService;
import Services.Connexion;
import Services.ImageService;
import Services.ServiceService;
import Services.ImageService;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
    private TableColumn<Service, String> etatHistoriqueAff;
    @FXML
    private TableColumn<Service, String> etatHistoriqueAff1;
    @FXML
    private Button supprimerHistorique;
    @FXML
    private Button recupererHistorique;
    String logooo;
    String imageee;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            modifierS.setDisable(true);
            supprimerService.setDisable(true);
            modifier.setDisable(true);
            supprimer.setDisable(true);
            ajouterServ.setVisible(false);
            labelService.setVisible(true);
            service.setVisible(true);
            ajouterS.setVisible(true);
            modifierS.setVisible(true);
            supprimerService.setVisible(true);

            Connection c = Connexion.getInstance().getCon();
            nomCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService, String>("nom"));
            descriptionCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService, String>("description"));
            imageCatAff.setCellValueFactory(new PropertyValueFactory<>("im"));

            imageServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("im"));
            nomServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("nom"));
            descriptionServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("description"));
            categorieServiceAff.setCellValueFactory(new PropertyValueFactory<Service, String>("Categorie"));
            nbrProvidersAff.setCellValueFactory(new PropertyValueFactory<Service, String>("nbrProviders"));

            CategorieServiceService categorieS = new CategorieServiceService();
            ServiceService serv = new ServiceService();
            try {
                categorie.setItems(categorieS.afficherCategorie());
                service.setItems(serv.afficherService());
                // TODO
            } catch (SQLException ex) {
                Logger.getLogger(EspaceServiceBackController.class.getName()).log(Level.SEVERE, null, ex);

            }

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
                        System.out.println(entry);
                        if (entry.toLowerCase().contains(value)) {
                            subentries.add(categorie.getItems().get(i));
                            break;
                        }
                    }
                }
                categorie.setItems(subentries);
            });
        });

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
        System.out.println("categorie supprimer");
        initialize(null, null);
    }

    @FXML
    private void ajouterC(ActionEvent event) {
        CategorieServiceService categorieS = new CategorieServiceService();

        CategorieService c = new CategorieService();
        c.setNom(nomModif.getText());
        c.setDescription(descriptionModif.getText());
        c.setImage(logooo);
        categorieS.ajouterCategorie(c);
        System.out.println("categorie ajoutée");
        logooo = "";

        initialize(null, null);
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
        categorieS.supprimerService(c.getId());
        System.out.println("service supprimer");
        initialize(null, null);
    }

    @FXML
    private void ajouterService(ActionEvent event) {
        ServiceService ss = new ServiceService();

        Service s = new Service();
        s.setNom(nomService.getText());
        s.setDescription(descriptionService.getText());
        s.setCategorie(categoS.getValue());
        s.setNbrProviders(0);
        s.setVisible(1);
        s.setImage(imageee);
        ss.ajouterService(s);
        System.out.println("service ajoutée");
        imageee = "";

        initialize(null, null);
    }

    @FXML
    private void ajouterS(ActionEvent event) {

        ajouterServ.setVisible(true);
        supprimerService.setVisible(false);
        ajouterS.setVisible(false);
        modifierS.setVisible(false);
        service.setVisible(false);
        labelService.setVisible(false);
        modifierService.setVisible(false);
        ajoutService.setVisible(true);

    }

    @FXML
    private void recupererHistorique(ActionEvent event) {
    }

    @FXML
    private void modifierService(ActionEvent event) {
        ServiceService categorieS = new ServiceService();
        Service c = new Service();

        c = service.getSelectionModel().getSelectedItem();
        c.setNom(nomService.getText());
        c.setDescription(descriptionService.getText());
        c.setCategorie(categoS.getValue());
        if (imageee != "") {
            c.setImage(imageee);
        }
        categorieS.modifierService(c);
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
        descriptionService.setText(s.getDescription());
        categoS.setValue(s.getCategorie());
        ObservableList<CategorieService> list = FXCollections.observableArrayList();
        CategorieServiceService r = new CategorieServiceService();
        list = r.getALLCategorie();
        categoS.setItems(list);
    }

    @FXML
    private void retourService(ActionEvent event) {

        ajouterServ.setVisible(false);
        labelService.setVisible(true);
        service.setVisible(true);
        ajouterS.setVisible(true);
        modifierS.setVisible(true);
        supprimerService.setVisible(true);

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

}
