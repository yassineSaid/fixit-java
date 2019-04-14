/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class EspaceUtilisateursBackController implements Initializable {
    
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colPseudo;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private ImageView photo;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label pseudo;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Label adresse;
    @FXML
    private Label ville;
    
    private User user;
    @FXML
    private Button grade;
    @FXML
    private Button bloquer;
    @FXML
    private Button supprimer;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            refreshAction();
            afficherTableAction();
            setActionTab();
        });
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void afficherTableAction() {
        UserService us = new UserService();
        colNom.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colPseudo.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        FilteredList<User> filteredData = new FilteredList<>(us.getUsers(user.getId()), p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(u -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (u.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (u.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (u.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (u.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
        tableUser.setItems(sortedData);
    }
    
    public void refreshAction() {
        photo.setVisible(false);
        nom.setVisible(false);
        prenom.setVisible(false);
        pseudo.setVisible(false);
        email.setVisible(false);
        telephone.setVisible(false);
        adresse.setVisible(false);
        ville.setVisible(false);
        grade.setVisible(false);
        bloquer.setVisible(false);
        supprimer.setVisible(false);
    }
    
    public void setActionTab() {
        tableUser.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            refreshAction();
            if (newSelection != null) {
                afficherUserAction(newSelection);
                FontAwesome fs1 = new FontAwesome();
                Node icon1 = fs1.create(FontAwesome.Glyph.TRASH).color(Color.WHITE).size(17);
                icon1.setId("icon");
                supprimer.setGraphic(icon1);
                supprimer.setVisible(true);
                supprimer.setOnAction((event) -> {
                    UserService us = new UserService();
                    us.supprimer(newSelection.getId());
                    refreshAction();
                    afficherTableAction();
                    setActionTab();
                });
                if (newSelection.getRoles().equals("Administrateur")) {
                    FontAwesome fs = new FontAwesome();
                    Node icon = fs.create(FontAwesome.Glyph.ARROW_DOWN).color(Color.WHITE).size(17);
                    icon.setId("icon");
                    grade.setGraphic(icon);
                    grade.getStyleClass().add("supprimer");
                    grade.setTextAlignment(TextAlignment.CENTER);
                    grade.setText("Rétrograder");
                    grade.setOnAction((event) -> {
                        UserService us = new UserService();
                        us.retrograder(newSelection.getId());
                        refreshAction();
                        afficherTableAction();
                        setActionTab();
                    });
                } else {
                    FontAwesome fs = new FontAwesome();
                    Node icon = fs.create(FontAwesome.Glyph.ARROW_UP).color(Color.WHITE).size(17);
                    icon.setId("icon");
                    grade.setGraphic(icon);
                    grade.getStyleClass().clear();
                    grade.getStyleClass().add("button");
                    grade.setText("Promouvoir");
                    grade.setOnAction((event) -> {
                        UserService us = new UserService();
                        us.promouvoir(newSelection.getId());
                        refreshAction();
                        afficherTableAction();
                        setActionTab();
                    });
                }
                grade.setVisible(true);
                if (newSelection.getEnabled() == 1) {
                    FontAwesome fs = new FontAwesome();
                    Node icon = fs.create(FontAwesome.Glyph.BAN).color(Color.WHITE).size(17);
                    icon.setId("icon");
                    bloquer.setGraphic(icon);
                    bloquer.getStyleClass().add("supprimer");
                    bloquer.setTextAlignment(TextAlignment.CENTER);
                    bloquer.setText("Bloquer");
                    bloquer.setOnAction((event) -> {
                        UserService us = new UserService();
                        us.bloquer(newSelection.getId());
                        refreshAction();
                        afficherTableAction();
                        setActionTab();
                    });
                } else {
                    FontAwesome fs = new FontAwesome();
                    Node icon = fs.create(FontAwesome.Glyph.CHECK_CIRCLE).color(Color.WHITE).size(17);
                    icon.setId("icon");
                    bloquer.setGraphic(icon);
                    bloquer.getStyleClass().clear();
                    bloquer.getStyleClass().add("button");
                    bloquer.setText("Débloquer");
                    bloquer.setOnAction((event) -> {
                        UserService us = new UserService();
                        us.debloquer(newSelection.getId());
                        refreshAction();
                        afficherTableAction();
                        setActionTab();
                    });
                }
                bloquer.setVisible(true);
            }
        });
    }
    
    void afficherUserAction(User newSelection) {
        if (newSelection.getImage() != null) {
            photo.setVisible(true);
            loadImage(newSelection.getImage());
        }
        if (newSelection.getFirstname() != null) {
            prenom.setVisible(true);
            prenom.setText("Prénom: " + newSelection.getFirstname());
        }
        if (newSelection.getLastname() != null) {
            nom.setVisible(true);
            nom.setText("Nom: " + newSelection.getLastname());
        }
        if (newSelection.getEmail() != null) {
            email.setVisible(true);
            email.setText("Email: " + newSelection.getEmail());
        }
        if (newSelection.getUsername() != null) {
            pseudo.setVisible(true);
            pseudo.setText("Pseudo: " + newSelection.getUsername());
        }
        if (newSelection.getPhone() != 0) {
            telephone.setVisible(true);
            telephone.setText("Téléphone: " + newSelection.getPhone());
        }
        if (newSelection.getAddress() != null) {
            adresse.setVisible(true);
            adresse.setText("Adresse: " + newSelection.getAddress());
        }
        if (newSelection.getCity() != null) {
            ville.setVisible(true);
            ville.setText("Ville: " + newSelection.getCity());
        }
    }
    
    private void loadImage(String imageUser) {
        File currDir = new File(System.getProperty("user.dir", "."));
        if (imageUser != null) {
            String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + imageUser;
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
    }
    
}
