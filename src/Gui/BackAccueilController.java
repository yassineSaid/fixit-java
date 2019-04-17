/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Notification;
import Entities.Outil;
import Entities.User;
import Entities.UserOutil;
import Services.NotificationService;
import Services.PaiementService;
import Services.ServiceService;
import Services.UserOutilService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class BackAccueilController implements Initializable {

    @FXML
    BackIndexController backIndexController;
    private User user;
    @FXML
    private BarChart<String, Float> statsAnnee;
    @FXML
    private NumberAxis revenus;
    @FXML
    private CategoryAxis mois;
    @FXML
    private ListView<Notification> listNotification;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            backIndexController.setUser(user);
            afficherStats();
            loadDataFromDatabase();
            listNotification.setCellFactory(item -> new ListCell<Notification>(){
            protected void updateItem(Notification item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text title = new Text(item.getTitle());
                Text description = new Text(item.getDescription());
                description.setWrappingWidth(200);
                Text dateNotification = new Text(item.getNotificationDate().toString());
                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/user/" + item.getIcon(), 100, 100, false, false);
                ImageView img = new ImageView(image);
                
                
                VBox vBox = new VBox(title,description,dateNotification);
                /*if (item.getSeen()==0) {
                } else {
                vBox = vBox2;
                }*/
                //vBox.setStyle("-fx-background-color:  transparent;");
                vBox.setSpacing(10);
                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-background-color:  transparent");
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
        });
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void afficherStats() {
        PaiementService ps = new PaiementService();
        XYChart.Series data = ps.statAnnee();
        statsAnnee.getData().addAll(data);
        statsAnnee.setBarGap(0);
        statsAnnee.setCategoryGap(30);
        for(Node n:statsAnnee.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #5998ff;");
        }

    }
    private void loadDataFromDatabase() {
        try {
            NotificationService service = new NotificationService();
            ObservableList<Notification> rs = service.afficherNotification();
            listNotification.setItems(rs);
        } catch (Exception e) {
            //System.err.println("Got an exception! ");
            System.out.println("Gui.EspaceOutilFrontController.loadDataFromDatabase()");
            System.err.println(e.getMessage());
        }
    }
}
