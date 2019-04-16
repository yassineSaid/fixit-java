/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Avis;
import Entities.User;
import Services.AvisService;
import Services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.stage.Stage;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class FrontAccueilController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private GridView<VBox> users;
    @FXML
    private TextField rechercher;

    User user;
    @FXML
    private Pagination paginator;
    @FXML
    private ListView<Avis> listAvis;
    @FXML
    private AnchorPane recherchePane;
    @FXML
    private AnchorPane accueilPane;

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
        accueilPane.setVisible(true);
        recherchePane.setVisible(false);
        Platform.runLater(() -> {
            System.out.println(user.getId());
            frontIndexController.setUser(user);
            frontIndexController.refresh();
            rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
                afficherUsers();
            });
            rechercher.focusedProperty().addListener((ov, oldV, newV) -> {
                if (newV) {
                    accueilPane.setVisible(false);
                    recherchePane.setVisible(true);
                    afficherUsers();
                } 
            });
            afficherUsers();
            afficherAvis();
        });
    }

    public void afficherAvis() {
        AvisService avisServ = new AvisService();
        ObservableList<Avis> list = FXCollections.observableArrayList();
        list = avisServ.top5();
        paginator.setMaxPageIndicatorCount(5);
        paginator.setPageCount(list.size());
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            int pos = (paginator.getCurrentPageIndex() + 1) % paginator.getPageCount();
            paginator.setCurrentPageIndex(pos);
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        getAvis();
        listAvis.setCellFactory(v -> new Poules());
    }

    public void afficherUsers() {
        final String IDLE_STYLE = " -fx-background-color:  rgba(226,226,226,0.7); -fx-background-radius: 8,7,6;";
        final String HOVERED_STYLE = "-fx-background-color: rgba(255,255,255,0.3);";

        UserService us = new UserService();
        users.setItems(us.getUsersVbox(user.getId(), rechercher.getText()));

        users.setCellHeight(200);
        users.setCellWidth(150);
        users.setCellFactory(gridView -> new GridCell<VBox>() {
            public void updateItem(VBox item, boolean empty) {
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //setText(item.getName());
                    item.setCursor(Cursor.HAND);
                    item.setStyle(IDLE_STYLE);
                    item.setSpacing(10);
                    item.setOnMouseEntered(e -> item.setStyle(HOVERED_STYLE));
                    item.setOnMouseExited(e -> item.setStyle(IDLE_STYLE));
                    item.setOnMouseClicked((event) -> {
                        VBox v = (VBox) event.getSource();
                        System.out.println(v.getId());
                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/ProfilUser.fxml"));
                            Parent Rec = fxmlLoader.load();
                            ProfilUserController controller = fxmlLoader.<ProfilUserController>getController();
                            controller.setUser(user);
                            controller.setId(v.getId());
                            Scene scene = new Scene(Rec);

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.show();
                            stage.setScene(scene);

                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    });
                    setGraphic(item);

                }

            }
        });

    }

    private ImageView loadImage(String imageUser) {
        File currDir = new File(System.getProperty("user.dir", "."));
        if (imageUser != null) {
            String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + imageUser;
            Image image = new Image(path);
            ImageView photo = new ImageView();
            photo.setFitWidth(100);
            photo.setFitHeight(100);
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
                return photo;
            }
        }
        return null;
    }

    public void getAvis() {

        AvisService avisServ = new AvisService();
        ObservableList<Avis> list = FXCollections.observableArrayList();
        list = avisServ.top5();
        listAvis.setItems(list);
        paginator.setPageFactory(this::createPage);
    }

    private Node createPage(int pageIndex) {

        AvisService avisServ = new AvisService();
        ObservableList<Avis> list = FXCollections.observableArrayList();
        list = avisServ.top5();
        int fromIndex;
        int toIndex;
        if (pageIndex == list.size()) {
            pageIndex = 0;
            fromIndex = pageIndex * 1;
            toIndex = Math.min(fromIndex + 1, list.size());
        } else {
            pageIndex = paginator.getCurrentPageIndex();
            fromIndex = pageIndex * 1;
            toIndex = Math.min(fromIndex + 1, list.size());
        }

        listAvis.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));

        return listAvis;
    }

    public class Poules extends ListCell<Avis> {

        public Poules() {
        }

        protected void updateItem(Avis item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text satisfaction = new Text(item.getSatisfaction());
                Text description = new Text(item.getDescription());
                Text userName = new Text(item.getUser().getUsername());
                satisfaction.setStyle("-fx-font-size: 23 arial;");
                description.setStyle("-fx-font-size: 14 arial;");
                userName.setStyle("-fx-font-size: 18 arial;");
                Rating rate = new Rating();
                rate.setRating(item.getNote());
                EventHandler<MouseEvent> handler = MouseEvent::consume;
                rate.addEventFilter(MouseEvent.ANY, handler);
                rate.setMaxHeight(1);
                VBox vBox = new VBox(satisfaction, userName, description);
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);
                Text txt = new Text("");
                VBox vBox1 = new VBox(txt, rate);
                vBox1.setStyle("-fx-font-color: transparent;");
                vBox1.setSpacing(15);
                HBox hBox = new HBox(vBox1, vBox);
                hBox.setStyle("-fx-font-color: transparent;");
                hBox.setSpacing(50);

                setGraphic(hBox);

                // hBox.setStyle("-fx-alignment: center ;");
                //hBox.gets
            }
        }
    }
}
