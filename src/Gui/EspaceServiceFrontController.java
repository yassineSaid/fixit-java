/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Outil;
import Entities.Service;
import Entities.User;
import Services.CategorieServiceService;
import Services.ServiceService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class EspaceServiceFrontController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    private User user;
    @FXML
    private ListView<CategorieService> listCategorie;
    @FXML
    private ListView<Service> listService;

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
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            loadCategorieFromDatabase();
            listCategorie.setCellFactory(lv -> new Poules());
        });
        // TODO
    }

    private void loadServiceFromDatabase(int id) {
        try {
            ServiceService service = new ServiceService();
            ObservableList<Service> rs = service.getAllServiceC(id);
            //paginationOutilFront.setPageFactory(this::createPage);
            listService.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    private void loadCategorieFromDatabase() {
        try {
            CategorieServiceService service = new CategorieServiceService();
            ObservableList<CategorieService> rs = service.afficherCategorie();
            //paginationOutilFront.setPageFactory(this::createPage);
            listCategorie.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public class Poules extends ListCell<CategorieService> {

        public Poules() {
        }

        protected void updateItem(CategorieService item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text description = new Text(item.getDescription());
                description.setWrappingWidth(300);;
                nom.setStyle("-fx-font-size: 25 arial;");
                description.setStyle("-fx-font-size: 15 arial;"
                        + "-fx-pref-width: 158px;");
                VBox vBox = new VBox(nom, description);
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);

                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieService/" + item.getImage(), 120, 120, false, false);
                ImageView img = new ImageView(image);

                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-font-color: transparent;");
                hBox.setSpacing(10);
                setGraphic(hBox);
                listCategorie.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        
                        CategorieService a = (CategorieService) listCategorie.getItems().get(listCategorie.getSelectionModel().getSelectedIndex());
                        loadServiceFromDatabase(a.getId());
                        listService.setCellFactory(lv -> new PoulesService());
                        System.out.println("test");
                    }
                });

            }
        }
    }

    public class PoulesService extends ListCell<Service> {

        public PoulesService() {
        }

        protected void updateItem(Service item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text description = new Text(item.getDescription());
                Text nbrProviders = new Text("Nombre de réalisateurs" + Integer.toString(item.getNbrProviders()));
                description.setWrappingWidth(300);
                nom.setStyle("-fx-font-size: 25 arial;");
                description.setStyle("-fx-font-size: 15 arial;"
                        + "-fx-pref-width: 158px;");
                nbrProviders.setStyle("-fx-font-size: 10 arial;");
                VBox vBox = new VBox(nom, description, nbrProviders);
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);

                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/service/" + item.getImage(), 120, 120, false, false);
                ImageView img = new ImageView(image);

                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-font-color: transparent;");
                hBox.setSpacing(10);
                setGraphic(hBox);

            }
        }
    }

}
