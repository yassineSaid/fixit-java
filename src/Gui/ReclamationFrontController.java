package Gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import Services.ReclamationService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.Vector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReclamationFrontController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private Tab interfaceAjout;
    @FXML
    private TabPane Tabwidget;
    @FXML
    private AnchorPane ajouterRec;
    @FXML
    private ComboBox<User> userReclamer;
    @FXML
    private ComboBox<Service> serviceRendu;
    @FXML
    private ComboBox<String> dateService;
    @FXML
    private TextField motif;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouter_Rec;

    @FXML
    private ListView<Reclamation> listRec;
    @FXML
    private Button modifierRec;
    private int id;
    @FXML
    private Button supprimerRec;
    @FXML
    private Button annuler;
    private User user;
    @FXML
    private Tab mesReclamationsTab;
    @FXML
    private Pagination paginationRecFront;
    @FXML
    private ListView<Reclamation> listReclam;
    @FXML
    private Label userDetail;
    @FXML
    private Label dateReclamationDetail;
    @FXML
    private Label userReclameDetail;
    @FXML
    private Label serviceDetail;
    @FXML
    private Label objetDetail;
    @FXML
    private Label descriptionDetail;
    @FXML
    private ProgressBar progressRec;
    @FXML
    private Label etape;
    @FXML
    private AnchorPane details;
    @FXML
    private ProgressIndicator progrssCircle;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            frontIndexController.getEspaceRec().setStyle("-fx-background-color: #f4f4f4");
            ObservableList<User> list = FXCollections.observableArrayList();
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            ReclamationService r = new ReclamationService();
            list = r.getusersreclamer(user.getId());
            userReclamer.setItems(list);
            serviceRendu.setItems(null);
            dateService.setItems(null);
            afficherReclamations();
            ajouter_Rec.setVisible(true);
            modifierRec.setVisible(false);
            supprimerRec.setVisible(false);
            annuler.setVisible(false);
            motif.setText("");
            description.setText("");
            details.setVisible(false);
            getReclamations();
            listReclam.setCellFactory(v -> new Poules());

        });

    }

    @FXML
    public void selectservice() {
        ObservableList<Service> list = FXCollections.observableArrayList();
        ReclamationService r = new ReclamationService();
        list = r.getServiceuserreclamer(userReclamer.getValue().getUsername());
        serviceRendu.setItems(list);
    }

    @FXML
    public void selectdate() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ReclamationService r = new ReclamationService();
        list = r.getDateuserreclamer(userReclamer.getValue().getUsername(), serviceRendu.getValue().getNom());
        dateService.setItems(list);
    }

    @FXML
    public void ajouterReclamation() throws ParseException {
        ReclamationService recServ = new ReclamationService();
        User UserReclame = new User();
        UserReclame = userReclamer.getValue();
        Service service = new Service();
        service = serviceRendu.getValue();
        String stringDate = dateService.getValue();
        Date localdate = new Date();
        java.sql.Date dateReclamation = new java.sql.Date(localdate.getTime());
        String err = "";
        if (UserReclame == null) {
            err += "Selectionner un utilisateur";
            userReclamer.setStyle("-fx-border-color : red");
        }
        if (service == null) {
            err += "\nSelectionner un service";
            serviceRendu.setStyle("-fx-border-color : red");
        }
        if (stringDate == null) {
            err += "\nSelectionner une date";
            dateService.setStyle("-fx-border-color : red");
        }
        if (motif.getText().length() == 0) {
            err += "\nle champs description ne doit pas etre vide";
            motif.setStyle("-fx-border-color : red");
        }
        if (description.getText().length() == 0) {
            err += "\nle champs motif ne doit pas être vide";
            description.setStyle("-fx-border-color : red");
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inscription");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        } else {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = formatter1.parse(stringDate);
            java.sql.Date dateRealisation = new java.sql.Date(date1.getTime());
            Reclamation rec = new Reclamation(motif.getText(), description.getText(), UserReclame, this.getUser(), dateReclamation, 0, 0, 0, service, dateRealisation);
            recServ.ajouterRaclamation(rec);
            afficherReclamations();
            initialize(null, null);

        }

    }

    public void afficherReclamations() {
        ReclamationService r = new ReclamationService();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        list = r.afficherReclamation(this.getUser().getId());
        listRec.setItems(list);
    }

    @FXML
    private void remplirChamps(MouseEvent event) {

        ReclamationService recServ = new ReclamationService();

        /* if (event.getClickCount() == 2) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/detailReclamationFront.fxml"));
                Parent Rec = fxmlLoader.load();
                Scene scene = new Scene(Rec);
                DetailReclamationFrontController controller = fxmlLoader.<DetailReclamationFrontController>getController();
                Reclamation rec = new Reclamation();
                rec = listRec.getSelectionModel().getSelectedItem();
                controller.setReclamation(rec);
                controller.setUserc(this.getUser());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.show();
                stage.setScene(scene);

            } catch (IOException ex) {
                System.out.println(ex);
            }
        }*/
        if (event.getClickCount() == 1) {
            Reclamation rec = new Reclamation();
            rec = listRec.getSelectionModel().getSelectedItem();
            if (rec.getSeen() == 1 || (rec.getTraite() == 1 && rec.getSeen() == 1)) {

                modifierRec.setDisable(true);
                supprimerRec.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reclamation vu ou traité");
                alert.setHeaderText(null);
                alert.setContentText("Cette Reclamation est déja vu par les administrateurs\n vous ne pouvez plus la supprimer ni la modifier\n");
                alert.showAndWait();
            } else {
                motif.setText(rec.getObjet());
                description.setText(rec.getDescription());
                System.out.println(rec.getUserReclame());
                userReclamer.setValue(rec.getUserReclame());
                serviceRendu.setValue(rec.getIdServiceRealise());
                String dateRealisation = rec.getDateRealisation().toString();
                dateService.setValue(dateRealisation);
                annuler.setVisible(true);
                this.id = rec.getId();
                ajouter_Rec.setVisible(false);
                modifierRec.setVisible(true);
                supprimerRec.setVisible(true);
                modifierRec.setDisable(false);
                supprimerRec.setDisable(false);

            }
        }
    }

    @FXML
    private void modifierRecAction(ActionEvent event) throws ParseException {
        ReclamationService recServ = new ReclamationService();
        User UserReclame = new User();
        UserReclame = userReclamer.getValue();
        Service service = new Service();
        service = serviceRendu.getValue();
        String stringDate = dateService.getValue();
        Date localdate = new Date();
        java.sql.Date dateReclamation = new java.sql.Date(localdate.getTime());
        String err = "";
        if (UserReclame == null) {
            err += "Selectionner un utilisateur";
            userReclamer.setStyle("-fx-border-color : red");
        }
        if (service == null) {
            err += "\nSelectionner un service";
            serviceRendu.setStyle("-fx-border-color : red");
        }
        if (stringDate == null) {
            err += "\nSelectionner une date";
            dateService.setStyle("-fx-border-color : red");
        }
        if (motif.getText().length() == 0) {
            err += "\nle champs description ne doit pas etre vide";
            motif.setStyle("-fx-border-color : red");
        }
        if (description.getText().length() == 0) {
            err += "\nle champs motif ne doit pas être vide";
            description.setStyle("-fx-border-color : red");
        }
        if (err != "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs erronée");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
            err = "";
        } else {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = formatter1.parse(stringDate);
            java.sql.Date dateRealisation = new java.sql.Date(date1.getTime());
            Reclamation rec = new Reclamation(this.id, motif.getText(), description.getText(), UserReclame, user, dateReclamation, 0, 0, 0, service, dateRealisation);
            recServ.modifierReclamation(rec);
            afficherReclamations();
            initialize(null, null);
        }
    }

    @FXML
    private void supprimerRecAction(ActionEvent event) {
        ReclamationService recServ = new ReclamationService();
        recServ.supprimerReclamation(this.id);
        afficherReclamations();
        initialize(null, null);
    }

    @FXML
    private void annulerAction(ActionEvent event) {
        initialize(null, null);
    }

    private Node createPage(int pageIndex) {
        ReclamationService recServ = new ReclamationService();

        ObservableList<Reclamation> data = FXCollections.observableArrayList();
        data = recServ.afficherReclamation(this.getUser().getId());
        int fromIndex = pageIndex * 5;
        int toIndex = Math.min(fromIndex + 5, data.size());
        listReclam.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return listReclam;
    }

    // Hbox et Vbox
    private void getReclamations() {
        ReclamationService r = new ReclamationService();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        list = r.afficherReclamation(this.getUser().getId());
        listReclam.setItems(list);
        paginationRecFront.setPageFactory(this::createPage);
    }

    @FXML
    private void detailsReclmations(MouseEvent event) {
        details.setVisible(true);
        Reclamation rec = new Reclamation();
        rec = listReclam.getSelectionModel().getSelectedItem();
        userDetail.setText(this.user.getUsername());
        dateReclamationDetail.setText(rec.getDateReclamation().toString());
        userReclameDetail.setText(rec.getUserReclame().getUsername());
        serviceDetail.setText(rec.getIdServiceRealise().getNom());
        description.setText(rec.getDescription());
        objetDetail.setText(rec.getObjet());
        if (rec.getSeen() == 0) {
            progressRec.setProgress(0.02);
            progrssCircle.setProgress(0.02);
            etape.setText("Votre reclamation est envoyé avec succés \n elle est en cours de traitement");
        }
        if (rec.getSeen() == 1) {
            progressRec.setProgress(0.5);
            progrssCircle.setProgress(0.5);
            etape.setText("Votre reclamation est vu par l'un de nos \n administrateur elle sera bientôt traité");
        }
        if (rec.getSeen() == 1 && rec.getTraite() == 1) {
            progressRec.setProgress(1.0);
            progrssCircle.setProgress(1.0);
            etape.setText("Votre reclamation a été bien traité");
        }
        if (event.getClickCount() == 2) {
            if (rec.getTraite() == 1) {
                File currDir = new File(System.getProperty("user.dir", "."));
                String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit1/web/RapportsReclamation/r" + rec.getId() + ".pdf";
                File f = new File(path);
                try {
                    Desktop.getDesktop().open(f);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
    }

    public class Poules extends ListCell<Reclamation> {

        public Poules() {
        }

        protected void updateItem(Reclamation item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text userrec = new Text("Contre :" + item.getUserReclame());
                Text date = new Text("Le : " + item.getDateReclamation());
                Text objet = new Text("Objet : " + item.getObjet());
                userrec.setStyle("-fx-font-size: 15 arial;");
                date.setStyle("-fx-font-size: 15 arial;");
                objet.setStyle("-fx-font-size: 15 arial;");
                Image traite = new Image("file:/wamp64/www/fixit/web/service/images/icons/traite.png", 60, 80, false, false);
                ImageView traiteV = new ImageView(traite);
                Image encours = new Image("file:/wamp64/www/fixit/web/service/images/icons/encours.jpg", 60, 80, false, false);
                ImageView encoursV = new ImageView(encours);

                VBox vBox = new VBox(userrec, date, objet);
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);
                if (item.getTraite() == 1) {
                    HBox hBox = new HBox(traiteV, vBox);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                } else {
                    HBox hBox = new HBox(encoursV, vBox);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                }

                // hBox.setStyle("-fx-alignment: center ;");
                //hBox.gets
            }
        }
    }
}
