/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Avis;
import Entities.RealisationService;
import Entities.User;
import Services.AvisService;
import Services.RealisationServiceService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AvisFrontController implements Initializable {

    private User user;
    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private Rating rating;
    @FXML
    private ComboBox<String> satisfaction;
    @FXML
    private TextField description;
    @FXML
    private Button noter;
    @FXML
    private Button modifier_note;
    @FXML
    private Label moyenneLb;
    @FXML
    private Separator sep;
    @FXML
    private Rating ratingAvg;
    @FXML
    private ListView<RealisationService> listServiceRealise;
    @FXML
    private Label val;
    @FXML
    private Rating evaluerNote;
    @FXML
    private Label label;
    @FXML
    private Button evaluer;
    @FXML
    private Label valueNote;
    @FXML
    private Label sur;

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
        //EventHandler<MouseEvent> handler = MouseEvent::consume;
        //ratingAvg.addEventFilter(MouseEvent.ANY, handler);
        Platform.runLater(() -> {
            Avis avis = new Avis();
            AvisService avisSer = new AvisService();
            frontIndexController.getEspaceAvis().setStyle("-fx-background-color: #f4f4f4");
            ObservableList<String> list = FXCollections.observableArrayList("Non Satisfait", "Moyennement Satisfait", "Totalement Satisfait");
            satisfaction.setItems(list);
            ratingAvg.setRating(avisSer.moyenneNotes());
            moyenneLb.setVisible(false);
            sep.setVisible(false);
            ratingAvg.setVisible(false);
            if (avisSer.getUserAvis(this.getUser().getId()) == null) {
                modifier_note.setVisible(false);
                rating.setRating(0.0);
                val.setText("0");
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
            } else {
                avis = avisSer.getUserAvis(this.getUser().getId());
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
                noter.setVisible(false);
                modifier_note.setVisible(true);
                rating.setRating((double) avis.getNote());
                satisfaction.setValue(avis.getSatisfaction());
                description.setText(avis.getDescription());
                val.setText(Integer.toString((int) rating.getRating()));
            }
            loadPage(false);
            getRealisation();
            afficherRealisation();

        });
    }

    public void afficherRealisation() {
        listServiceRealise.setCellFactory(item -> new ListCell<RealisationService>() {
            protected void updateItem(RealisationService item, boolean bln) {
                super.updateItem(item, bln);

                if (item != null) {
                    Text dateRealisation = new Text(item.getDateRealisation().toString());
                    Text userOffreur = new Text(item.getUserOffreur().getUsername());
                    Text Service = new Text(item.getService().getNom());
                    userOffreur.setStyle("-fx-font-size: 25 arial;");
                    Service.setStyle("-fx-font-size: 20 arial;");
                    dateRealisation.setStyle("-fx-font-size: 20 arial;");
                    String s = item.getService().getImage();
                    File currDir = new File(System.getProperty("user.dir", "."));
                    String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit1\\web\\uploads\\images\\service\\"+s;
                    Image image = new Image(path,200, 200, false, false);
                    ImageView img= new ImageView(image);
                    VBox vBox = new VBox(userOffreur, Service, dateRealisation);
                    vBox.setStyle("-fx-font-color: transparent;");
                    vBox.setSpacing(20);
                    HBox hBox = new HBox(img,vBox);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(10);
                    setGraphic(hBox);

                }
            }
        });
    }

    @FXML
    private void ratingchoose(MouseEvent event) {
        val.setText(Integer.toString((int) rating.getRating()));
        System.out.println(rating.getRating());
    }

    @FXML
    private void NoterAction(ActionEvent event) {
        AvisService avisServ = new AvisService();
        Avis avis = new Avis(description.getText(), (int) rating.getRating(), satisfaction.getValue().toString(), this.getUser());
        avisServ.ajouterAvis(avis);
        avisServ.bonus(this.user.getId());
        ratingAvg.setRating(avisServ.moyenneNotes());
        moyenneLb.setVisible(true);
        sep.setVisible(true);
        ratingAvg.setVisible(true);
    }

    @FXML
    private void modifierNoteAction(ActionEvent event) {
        AvisService avisServ = new AvisService();
        Avis avis = new Avis(description.getText(), (int) rating.getRating(), satisfaction.getValue().toString(), this.getUser());
        avisServ.modifierAvis(avis);
        ratingAvg.setRating(avisServ.moyenneNotes());
        moyenneLb.setVisible(true);
        sep.setVisible(true);
        ratingAvg.setVisible(true);
    }

    public void getRealisation() {
        RealisationServiceService rss = new RealisationServiceService();
        ObservableList<RealisationService> list = FXCollections.observableArrayList();
        list = rss.getServiceRealise(this.getUser().getId());
        listServiceRealise.setItems(list);
    }
    public void loadPage(Boolean visibility)
    {
        label.setVisible(visibility);
        evaluerNote.setVisible(visibility);
        evaluer.setVisible(visibility);
        valueNote.setVisible(visibility);
        sur.setVisible(visibility);
    }
    public void ajouterNote()
    {
        RealisationService rs = listServiceRealise.getSelectionModel().getSelectedItem();
        RealisationServiceService rss = new RealisationServiceService();
        rss.setNoteRealisationService((int)evaluerNote.getRating(), rs.getId());
    }
    @FXML
    private void serviceRealiseSelectionne(MouseEvent event) {
        loadPage(true);
        RealisationServiceService rss = new RealisationServiceService();
        RealisationService rs =rss.getNote(listServiceRealise.getSelectionModel().getSelectedItem().getId());
        if(rs.getNote()==0)
        {
            evaluerNote.setRating(0);
        }
        else
        {
            evaluerNote.setRating(rs.getNote());
        }
        
    }

    @FXML
    private void evaluerAction(ActionEvent event) {
        ajouterNote();
    }

}
