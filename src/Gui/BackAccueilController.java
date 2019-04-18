/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Notification;
import Entities.Outil;
import Entities.User;
import Entities.UserLangue;
import Entities.UserOutil;
import Services.NotificationService;
import Services.PaiementService;
import Services.ServiceService;
import Services.UserLangueService;
import Services.UserOutilService;
import Services.Utils;
import java.io.File;
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.glyphfont.FontAwesome;

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
    @FXML
    private HBox image1;
    @FXML
    private HBox image2;
    @FXML
    private HBox image3;
    @FXML
    private Label revenusAnnee;
    @FXML
    private Label revenusTotal;
    @FXML
    private Label languesParlee;
    @FXML
    private Button saveAsPng;
    int nbNotification;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            backIndexController.setUser(user);
            afficherStats();
            loadDataFromDatabase();
            afficherNotifications();
            listNotification.setStyle("-fx-control-inner-background:  transparent; -fx-background-color:   rgba(255,255,255,0.1);");
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void afficherNotifications() {
        listNotification.setCellFactory(item -> new ListCell<Notification>() {
            protected void updateItem(Notification item, boolean bln) {
                super.updateItem(item, bln);
                if (item != null) {
                    Text title = new Text(item.getTitle());
                    Text description = new Text(item.getDescription());
                    Text dateNotification = new Text(item.getNotificationDate().toString());
                    description.setWrappingWidth(150);
                    title.setStyle("-fx-font-weight: bold;	-fx-font-size: 14px; -fx-alignment: center ;");
                    description.setStyle("-fx-font-weight: bold;");
                    dateNotification.setStyle("-fx-font-weight: bold;");
                    Image image = new Image(getClass().getResourceAsStream("/Resources/location.png"), 50, 50, false, false);
                    ImageView img = new ImageView(image);
                    img.setStyle("	-fx-pref-height: 50px; -fx-pref-width: 50px;");
                    VBox vBox = new VBox(title, description, dateNotification);
                    vBox.setSpacing(10);
                    VBox vBoxImage = new VBox(new Text(), img, new Text());
                    vBoxImage.setSpacing(10);
                    HBox hBox = new HBox(vBoxImage, vBox);
                    hBox.setSpacing(10);
                    if (item.getSeen() == 0) {
                        hBox.setStyle("-fx-background-color:  #6db6c6");
                    } else {
                        hBox.setStyle("-fx-background-color:  transparent");
                    }
                    setGraphic(hBox);
                }
            }
        });
    }

    public void afficherStats() {
        PaiementService ps = new PaiementService();
        XYChart.Series data = ps.statAnnee();
        statsAnnee.getData().addAll(data);
        statsAnnee.setBarGap(0);
        statsAnnee.setCategoryGap(30);
        for (Node n : statsAnnee.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #5998ff;");
        }
        System.err.println(ps.revenusAnnuels());
        Label i1 = new Label();
        Label i2 = new Label();
        Label i3 = new Label();
        Label i4 = new Label();
        FontAwesome fs = new FontAwesome();
        Node icon1 = fs.create(FontAwesome.Glyph.DOLLAR).color(Color.web("#5b76ff")).size(50);
        icon1.setId("icon");
        i1.setGraphic(icon1);
        i1.setGraphic(icon1);
        i1.setAlignment(Pos.CENTER);
        image1.getChildren().add(i1);
        Node icon2 = fs.create(FontAwesome.Glyph.DOLLAR).color(Color.web("#ff3535")).size(50);
        icon2.setId("icon");
        i2.setGraphic(icon2);
        i2.setGraphic(icon2);
        i2.setAlignment(Pos.CENTER);
        image2.getChildren().add(i2);
        Node icon3 = fs.create(FontAwesome.Glyph.LANGUAGE).color(Color.web("#ff3535")).size(50);
        icon3.setId("icon");
        i3.setGraphic(icon3);
        i3.setGraphic(icon3);
        i3.setAlignment(Pos.CENTER);
        image3.getChildren().add(i3);
        Node icon4 = fs.create(FontAwesome.Glyph.SAVE).color(Color.WHITE).size(12);
        icon3.setId("icon");
        saveAsPng.setGraphic(icon4);
        revenusAnnee.setText(ps.revenusAnnuels() + " DT");
        revenusTotal.setText(ps.revenusTotaux() + " DT");
        UserLangueService uls = new UserLangueService();
        if (Utils.checkVoyelle(uls.languePlusParlee())) {
            languesParlee.setText("L'" + Utils.upperCaseFirst(uls.languePlusParlee()));
        } else {
            languesParlee.setText("Le " + Utils.upperCaseFirst(uls.languePlusParlee()));
        }

    }

    private void loadDataFromDatabase() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            NotificationService service = new NotificationService();
            if (service.checkNotification(nbNotification)) {
                loadNotifications();
                afficherNotifications();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        loadNotifications();
    }
    private void loadNotifications(){
        try {
            NotificationService service = new NotificationService();
            ObservableList<Notification> rs = service.afficherNotification();
            listNotification.getItems().clear();
            listNotification.setItems(rs);
            nbNotification=rs.size();
        } catch (Exception e) {
            //System.err.println("Got an exception! ");
            System.out.println("load notification failed");
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void afficherDetailNotification(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Notification n = (Notification) listNotification.getItems().get(listNotification.getSelectionModel().getSelectedIndex());
            NotificationService ns = new NotificationService();
            ns.modifierSeen(n);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/DetailNotification.fxml"));
            Parent root = fxmlLoader.load();
            DetailNotificationController controller = fxmlLoader.<DetailNotificationController>getController();
            controller.setNotification(n);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

    @FXML
    private void saveAsPngAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        WritableImage image = statsAnnee.snapshot(new SnapshotParameters(), null);

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                // TODO: handle exception here
            }
        }

        // TODO: probably use a file chooser here
    }
}
