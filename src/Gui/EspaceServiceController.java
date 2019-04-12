/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Entities.User;
import Services.CategorieServiceService;
import Services.Connexion;
import Services.ReclamationService;
import Services.ServiceService;
import Services.ImageService;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class EspaceServiceController implements Initializable {

    @FXML
    private TextField nomCat;
    @FXML
    private TextArea descriptionCat;
    @FXML
    private Button ajoutCat;
    private TableColumn idCatAff;
    @FXML
    private TableColumn<CategorieService, String> nomCatAff;
    @FXML
    private TableColumn descriptionCatAff;
    //private CategorieServiceService cs = new CategorieServiceService();
    @FXML
    private TableView<CategorieService> categorie;
    private ObservableList<CategorieService> data;
    private ObservableList<Service> dataS;
    private ObservableList<Service> dataH;
    @FXML
    private Button supprimer;
    private CategorieService categ;
    @FXML
    private TableColumn<CategorieService, String> imageCatAff;
    @FXML
    private ComboBox<CategorieService> categoS;
    @FXML
    private TextField nomService;
    @FXML
    private Button ajoutService;
    @FXML
    private TextArea descriptionService;
    @FXML
    private TableView<Service> service;
    @FXML
    private TableColumn<Service, String> imageServiceAff;
    @FXML
    private TableColumn<Service, String> nomServiceAff;
    @FXML
    private TableColumn descriptionServiceAff;
    @FXML
    private TableColumn<Service, String> categorieServiceAff;
    @FXML
    private Button supprimerService;
    @FXML
    private AnchorPane ajouterCat;
    @FXML
    private Button ajouterC;
    @FXML
    private AnchorPane listCat;
    @FXML
    private Button retour;
    @FXML
    private Button retour2;
    @FXML
    private AnchorPane ajouterServ;
    @FXML
    private Button ajouterS;
    @FXML
    private TextField rechercherCat;
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
    private Button supprimerHistorique;
    @FXML
    private Button recupererHistorique;
    @FXML
    private TableColumn<Service, String> etatHistoriqueAff;
    @FXML
    private TableColumn<String, String> etatHistoriqueAff1;
    private Label idLabAff;
    private Label nomLabAff;
    @FXML
    private Label labelCategorie;
    @FXML
    private Label labelService;
    @FXML
    private Button modifier;
    @FXML
    private TextField nomModif;
    @FXML
    private TextArea descriptionModif;
    @FXML
    private Label labNomModif;
    @FXML
    private Label labDescriptionModif;
    @FXML
    private Button importerImage;
    String logooo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Platform.runLater(() -> {
            ObservableList<CategorieService> list = FXCollections.observableArrayList();
            CategorieServiceService r = new CategorieServiceService();
            list = r.listeCate();
            categoS.setItems(list);
            try {
                Connection c = Connexion.getInstance().getCon();
                data = FXCollections.observableArrayList();
                ResultSet rs = c.createStatement().executeQuery("select * from categorie_Service");
                while (rs.next()) {

                    Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieService/" + rs.getString(4), 200, 100, false, false);

                    // data.add(new CategorieService(rs.getInt(1),rs.getString(2),rs.getString(3),new ImageView("file:/wamp64/www/fixit/web/uploads/images/categorieService/"+rs.getString(4))));
                    data.add(new CategorieService(rs.getInt(1),rs.getString(2), rs.getString(3), new ImageView(image1)));
                }
                imageCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService,String>("im"));
                //idCatAff.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService,String>("nom"));
                descriptionCatAff.setCellValueFactory(new PropertyValueFactory<CategorieService,String>("description"));
                imageServiceAff.setCellValueFactory(new PropertyValueFactory<>("im"));
                nomServiceAff.setCellValueFactory(new PropertyValueFactory<>("nom"));
                descriptionServiceAff.setCellValueFactory(new PropertyValueFactory<>("description"));
                categorieServiceAff.setCellValueFactory(new PropertyValueFactory<>("idCategorieService"));

                imageHistoriqueAff.setCellValueFactory(new PropertyValueFactory<>("im"));
                nomHistoriqueAff.setCellValueFactory(new PropertyValueFactory<>("nom"));
                descriptionHistoriqueAff.setCellValueFactory(new PropertyValueFactory<>("description"));
                categorieHistoriqueAff.setCellValueFactory(new PropertyValueFactory<>("idCategorieService"));
                //etatHistoriqueAff.setCellValueFactory(new PropertyValueFactory<>(""));
                // etatHistoriqueAff.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Hi"));
                categorie.setEditable(true);
                nomCatAff.setCellFactory(TextFieldTableCell.forTableColumn());
                descriptionCatAff.setCellFactory(TextFieldTableCell.forTableColumn());
                nomServiceAff.setCellFactory(TextFieldTableCell.forTableColumn());
                descriptionServiceAff.setCellFactory(TextFieldTableCell.forTableColumn());
                categorie.setItems(null);
                categorie.setItems(data);

                ServiceService s = new ServiceService();
                dataH = FXCollections.observableArrayList();
                dataS = FXCollections.observableArrayList();
                dataH = s.afficherServiceHistorique();
                dataS = s.afficherService();
                historique.setItems(dataH);
                service.setItems(dataS);
                service.setEditable(true);
                supprimer.setDisable(true);
                supprimerService.setDisable(true);
                ObservableList dataR = categorie.getItems();
                rechercherCat.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (oldValue != null && (newValue.length() < oldValue.length())) {
                        categorie.setItems(dataR);
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

                //Image image=new Image("file:\\Users\\SELON\\Desktop\\unnamed.png");
                //photo.setImage(image);
            } catch (Exception e) {
                System.out.println(e);
            }

        });

    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    void ajouterCat(ActionEvent event) {
        CategorieServiceService Cat = new CategorieServiceService();
        CategorieService catServ = new CategorieService(nomCat.getText(), descriptionCat.getText());
        Cat.ajouterCategorie(catServ);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        CategorieServiceService cc = new CategorieServiceService();
        cc.supprimerCategorie(categorie.getSelectionModel().getSelectedItem().getId());
        this.initialize(null, null);

        //categorie.getItems().remove(categorie.getSelectionModel().getSelectedItem());
    }
  
    @FXML
    private void ajouterService(ActionEvent event) {
        ServiceService serv = new ServiceService();
        CategorieService c = new CategorieService();
        c = categoS.getValue();
        Service s = new Service(nomService.getText(), 1, descriptionService.getText(), 0, c.getId());
        serv.ajouterService(s);
    }

    @FXML
    private void supprimerService(ActionEvent event) {

        ServiceService cc = new ServiceService();
        //cc.supprimerService(service.getSelectionModel().getSelectedItem().getId());
        Service cat = new Service();
        cat = service.getSelectionModel().getSelectedItem();
        cat.setVisible(0);
        cc.modifierService(cat);
        // etatHistoriqueAff.setText("supprimé");
        etatHistoriqueAff.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("supprimé"));
        this.initialize(null, null);
    }
    /*private void modifierNomService(TableColumn.CellEditEvent<Service, String> event) {
        service.setEditable(true);
        ServiceService s = new ServiceService();
        Service serv = service.getSelectionModel().getSelectedItem();
        serv.setNom(event.getNewValue());
        System.out.println(event.getNewValue());
        s.modifierService(serv);

        //this.initialize(null, null);
    }*/
   /* private void modifierDescriptionService(TableColumn.CellEditEvent<Service, String> event) {
        service.setEditable(true);
        ServiceService s = new ServiceService();
        Service serv = service.getSelectionModel().getSelectedItem();
        serv.setDescription(event.getNewValue());
        System.out.println(event.getNewValue());
        s.modifierService(serv);

        //this.initialize(null, null);
    }*/

    @FXML
    private void ajouterC(ActionEvent event) {
        categorie.setVisible(false);
        supprimer.setVisible(false);
        ajouterC.setVisible(false);
        ajouterCat.setVisible(true);
        labelCategorie.setVisible(false);
    }

    @FXML
    private void retour(ActionEvent event) {

        categorie.setVisible(true);
        supprimer.setVisible(true);
        ajouterC.setVisible(true);
        ajouterCat.setVisible(false);

        labelCategorie.setVisible(true);

    }

    @FXML
    private void retour2(ActionEvent event) {

        service.setVisible(true);
        supprimerService.setVisible(true);
        ajouterS.setVisible(true);
        ajouterServ.setVisible(false);
        labelService.setVisible(true);
    }

    @FXML
    private void ajouterS(ActionEvent event) {

        service.setVisible(false);
        supprimerService.setVisible(false);
        ajouterS.setVisible(false);
        ajouterServ.setVisible(true);
        labelService.setVisible(false);
    }

    @FXML
    private void recupererHistorique(ActionEvent event) {
        ServiceService cc = new ServiceService();
        //cc.supprimerService(service.getSelectionModel().getSelectedItem().getId());
        Service cat = new Service();
        cat = historique.getSelectionModel().getSelectedItem();
        cat.setVisible(1);
        cc.modifierService(cat);
        // etatHistoriqueAff.setText("supprimé");
        //etatHistoriqueAff.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("supprimé"));
        this.initialize(null, null);
    }

    @FXML
    private void modifierCategorie(ActionEvent event) {
        CategorieServiceService cs = new CategorieServiceService();
        
        CategorieService c = new CategorieService();

        c = categorie.getSelectionModel().getSelectedItem();
        c.setNom(nomModif.getText());
        c.setDescription(descriptionModif.getText());
        System.out.println(descriptionModif.getText());
        cs.modifierCategorie(c);
        System.out.println(c.getId());
        initialize(null, null);
    }
    @FXML
    private void itemSelected(MouseEvent event) {
        
        nomModif.setText(categorie.getSelectionModel().getSelectedItem().getNom());
        descriptionModif.setText(categorie.getSelectionModel().getSelectedItem().getDescription());
    }

    @FXML
    private void importerImage(ActionEvent event) {
            final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            
            File currDir = new File(System.getProperty("user.dir", "."));
        System.out.println(currDir.toPath().getRoot().toString());
            
            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/categorieService/";
            ImageService u = new ImageService();
            try {
                u.upload(file, path);
            } catch (IOException ex) {
                Logger.getLogger(EspaceOutilBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            logooo = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }




}
