package Gui;

import Entities.CategorieService;
import Entities.Horraire;
import Entities.Langue;
import Entities.Message;
import Entities.Repos;
import Entities.User;
import Services.HorraireService;
import Services.ImageService;
import Services.MessageService;
import Services.ReposService;
import Services.UserLangueService;
import Services.UserService;
import Services.Utils;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProfilController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
    @FXML
    private TabPane Tabwidget;
    @FXML
    private Tab interfaceAjout;
    @FXML
    private Label email;
    @FXML
    private Label username;
    @FXML
    private ImageView photo;

    private User user;
    private User Contacteduser;
    private String idMessage;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private AnchorPane profil;
    @FXML
    private Button modifier;
    @FXML
    private TableView<Langue> table;
    @FXML
    private TableColumn<Langue, String> langue;
    @FXML
    private TableColumn<Langue, Button> supprimer;
    @FXML
    private ComboBox<Langue> listLangues;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Horraire> lundiTab;
    @FXML
    private TableView<Horraire> mardiTab;
    @FXML
    private TableView<Horraire> mercrediTab;
    @FXML
    private TableView<Horraire> jeudiTab;
    @FXML
    private TableView<Horraire> vendrediTab;
    @FXML
    private TableView<Horraire> dimancheTab;
    @FXML
    private TableView<Horraire> samediTab;
    @FXML
    private TableColumn<Horraire, Time> deb1;
    @FXML
    private TableColumn<Horraire, Time> fin1;
    @FXML
    private TableColumn<Horraire, Time> deb2;
    @FXML
    private TableColumn<Horraire, Time> fin2;
    @FXML
    private TableColumn<Horraire, Time> deb3;
    @FXML
    private TableColumn<Horraire, Time> fin3;
    @FXML
    private TableColumn<Horraire, Time> deb4;
    @FXML
    private TableColumn<Horraire, Time> fin4;
    @FXML
    private TableColumn<Horraire, Time> deb5;
    @FXML
    private TableColumn<Horraire, Time> fin5;
    @FXML
    private TableColumn<Horraire, Time> deb0;
    @FXML
    private TableColumn<Horraire, Time> fin0;
    @FXML
    private TableColumn<Horraire, Time> deb6;
    @FXML
    private TableColumn<Horraire, Time> fin6;
    @FXML
    private CheckBox rep1;
    @FXML
    private CheckBox rep3;
    @FXML
    private CheckBox rep5;
    @FXML
    private CheckBox rep0;
    @FXML
    private CheckBox rep2;
    @FXML
    private CheckBox rep4;
    @FXML
    private CheckBox rep6;
    @FXML
    private ComboBox<Repos> listJours;
    @FXML
    private TableColumn<?, ?> supp1;
    @FXML
    private TableColumn<?, ?> supp2;
    @FXML
    private TableColumn<?, ?> supp3;
    @FXML
    private TableColumn<?, ?> supp4;
    @FXML
    private TableColumn<?, ?> supp5;
    @FXML
    private TableColumn<?, ?> supp0;
    @FXML
    private TableColumn<?, ?> supp6;
    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXTimePicker heureFin;
    @FXML
    private Label erreurJour;
    @FXML
    private Label erreurHeure1;
    @FXML
    private Label erreurHeureFin;
    @FXML
    private Label erreurHorraire;
    @FXML
    private Label solde;
    @FXML
    private Button modifier1;
    @FXML
    private TextField adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField zip;
    @FXML
    private TextField telephone;
    @FXML
    private ListView<HBox> listUsers;
    @FXML
    private ListView<VBox> messages;
    @FXML
    private TextArea nouveauMessage;
    @FXML
    private Button envoyer;
    @FXML
    private Tab messagerie;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {

            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            frontIndexController.getProfil().setStyle("-fx-background-color: #f4f4f4");
            refreshUser();
            afficherLanguesAction();
            afficherHorraireAction();
            afficherReposAction();
            loadUsersMessage();
            refreshMessage();
        });
    }

    @FXML
    public void refreshMessage() {
        nouveauMessage.setDisable(true);
        envoyer.setDisable(true);
        MessageService ms = new MessageService();
        FontAwesome fs = new FontAwesome();
        Node icon1 = fs.create(FontAwesome.Glyph.INFO_CIRCLE).color(Color.BLACK).size(13);
        icon1.setId("icon");
        if (ms.checkUnseen(user.getId())) {
            messagerie.setGraphic(icon1);
        }
        listUsers.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (listUsers.getSelectionModel().getSelectedIndex() != -1) {
                    HBox h = (HBox) listUsers.getItems().get(listUsers.getSelectionModel().getSelectedIndex());
                loadMessages(h.getId());
                nouveauMessage.setDisable(false);
                envoyer.setDisable(false);
                loadUsersMessage();
                }
            }
        });
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            loadUsersMessage();
            if (ms.checkUnnotified(user.getId())){
                messagerie.setGraphic(icon1);
                ms.setNotified(user.getId());
                
            if (idMessage!=null) loadMessages(idMessage);
            }
            
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    @FXML
    private void modifierAction(ActionEvent event) {
        String erreur = "";
        boolean e = false;
        if (nom.getText().length() == 0) {
            e = true;
            erreur += "Le champ nom ne peut pas rester vide.";
        }
        if (prenom.getText().length() == 0) {
            e = true;
            erreur += "\nLe champ prenom ne peut pas rester vide.";
        }
        if (e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modification");
            alert.setHeaderText(null);
            alert.setContentText(erreur);
            alert.showAndWait();
        } else {
            UserService us = new UserService();
            us.modifierUser(user, nom.getText(), prenom.getText(), adresse.getText(), ville.getText(), zip.getText(), Integer.parseInt(telephone.getText()));
            user = us.connect(user.getUsername());
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            refreshUser();
        }
    }

    @FXML
    private void changerAction(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }
    }

    void refreshUser() {
        username.setText(user.getUsername());
        nom.setText(user.getLastname());
        prenom.setText(user.getFirstname());
        email.setText(user.getEmail());
        adresse.setText(user.getAddress());
        zip.setText(user.getZip_code());
        ville.setText(user.getCity());
        telephone.setText(String.valueOf(user.getPhone()));
        heureDebut.set24HourView(true);
        heureFin.set24HourView(true);
        solde.setText("Vous avez " + String.valueOf(user.getSolde()) + " SCoins sur votre compte");
        loadImage();
    }

    private void afficherLanguesAction() {
        langue.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        supprimer.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        UserLangueService uls = new UserLangueService();
        table.setItems(uls.getUserLangue(user));
        for (Langue l : table.getItems()) {
            l.getSupprimer().setOnAction(this::supprimerAction);

        }
        listLangues.setItems(uls.getLangues(user));
    }

    private void afficherReposAction() {
        ReposService rs = new ReposService();
        ObservableList<Repos> repos;
        repos = FXCollections.observableArrayList();
        rs.getUserRepos(user).forEach(r -> {
            if (r.getId() == 0) {
                rep0.setSelected(true);
            }
            if (r.getId() == 1) {
                rep1.setSelected(true);
            }
            if (r.getId() == 2) {
                rep2.setSelected(true);
            }
            if (r.getId() == 3) {
                rep3.setSelected(true);
            }
            if (r.getId() == 4) {
                rep4.setSelected(true);
            }
            if (r.getId() == 5) {
                rep5.setSelected(true);
            }
            if (r.getId() == 6) {
                rep6.setSelected(true);
            }
        });
        if (!rep0.selectedProperty().get()) {
            repos.add(new Repos(0, user.getId()));
        }
        if (!rep1.selectedProperty().get()) {
            repos.add(new Repos(1, user.getId()));
        }
        if (!rep2.selectedProperty().get()) {
            repos.add(new Repos(2, user.getId()));
        }
        if (!rep3.selectedProperty().get()) {
            repos.add(new Repos(3, user.getId()));
        }
        if (!rep4.selectedProperty().get()) {
            repos.add(new Repos(4, user.getId()));
        }
        if (!rep5.selectedProperty().get()) {
            repos.add(new Repos(5, user.getId()));
        }
        if (!rep6.selectedProperty().get()) {
            repos.add(new Repos(6, user.getId()));
        }
        listJours.setItems(repos);
    }

    private void afficherHorraireAction() {
        HorraireService hs = new HorraireService();
        deb0.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb1.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb2.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb3.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb4.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb5.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        deb6.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        fin0.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin1.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin2.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin3.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin4.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin5.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        fin6.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        supp0.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp1.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp2.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp3.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp4.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp5.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        supp6.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        dimancheTab.setItems(hs.getUserHorraire(user, 0));
        lundiTab.setItems(hs.getUserHorraire(user, 1));
        mardiTab.setItems(hs.getUserHorraire(user, 2));
        mercrediTab.setItems(hs.getUserHorraire(user, 3));
        jeudiTab.setItems(hs.getUserHorraire(user, 4));
        vendrediTab.setItems(hs.getUserHorraire(user, 5));
        samediTab.setItems(hs.getUserHorraire(user, 6));
        dimancheTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        lundiTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        mardiTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        mercrediTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        jeudiTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        vendrediTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
        samediTab.getItems().forEach((h) -> {
            h.getSupprimer().setOnAction(this::supprimerHorraireAction);
        });
    }

    private void loadImage() {
        File currDir = new File(System.getProperty("user.dir", "."));
        String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + user.getImage();
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

    private void supprimerAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        UserLangueService usl = new UserLangueService();
        usl.supprimerUserLangue(parseInt(button.getId()), user);
        afficherLanguesAction();
    }

    private void supprimerHorraireAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        HorraireService hs = new HorraireService();
        hs.supprimerHorraire(parseInt(button.getId()));
        afficherHorraireAction();
    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        UserLangueService uls = new UserLangueService();
        uls.ajouterUserLangue(listLangues.getValue().getId(), user);
        afficherLanguesAction();
    }

    @FXML
    private void reposAction(ActionEvent event) {
        ReposService rs = new ReposService();
        HorraireService hs = new HorraireService();
        CheckBox cb = (CheckBox) event.getSource();
        if (cb.selectedProperty().get()) {
            hs.supprimerJour(parseInt(cb.getId().substring(3)), user);
            rs.ajouterRepos(parseInt(cb.getId().substring(3)), user);
            afficherHorraireAction();
        } else {
            rs.supprimerRepos(parseInt(cb.getId().substring(3)), user);
        }
        afficherReposAction();
    }

    @FXML
    private void ajouterHorraireAction(ActionEvent event) {
        boolean erreur = false;
        HorraireService uls = new HorraireService();
        if (listJours.getValue() == null) {
            erreurJour.setText("Sélectionnez un jour");
            erreur = true;
        } else {
            erreurJour.setText("");
        }
        if (heureDebut.getValue() == null) {
            erreurHeure1.setText("Sélectionnez l'heure de début");
            erreur = true;
        } else {
            erreurHeure1.setText("");
        }
        if (heureFin.getValue() == null) {
            erreurHeureFin.setText("Sélectionnez l'heure de fin");
            erreur = true;
        } else {
            erreurHeureFin.setText("");
        }
        if (!erreur) {
            if (heureDebut.getValue().isAfter(heureFin.getValue())) {
                erreurHorraire.setText("Vérifiez les heures");
                erreur = true;
            } else if (!uls.checkHorraire(user, java.sql.Time.valueOf(heureDebut.getValue()), java.sql.Time.valueOf(heureFin.getValue()), listJours.getValue().getId())) {
                erreurHorraire.setText("Vérifiez les heures");
                erreur = true;
            } else {
                erreurHorraire.setText("");
            }
        }
        if (!erreur) {
            uls.ajouterHorraire(user, java.sql.Time.valueOf(heureDebut.getValue()), java.sql.Time.valueOf(heureFin.getValue()), listJours.getValue().getId());
            afficherHorraireAction();
        }
    }

    @FXML
    private void acheterAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/paiement.fxml"));
        Parent root = fxmlLoader.load();
        PaiementController controller = fxmlLoader.<PaiementController>getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
        modifierAction(event);
        solde.setText("Vous avez " + String.valueOf(user.getSolde()) + " SCoins sur votre compte");
    }

    @FXML
    private void changerPhotoAction(ActionEvent event) {
        UserService us = new UserService();
        File currDir = new File(System.getProperty("user.dir", "."));
        String filename;
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String path = currDir.toPath().getRoot().toString() + "wamp64/www/fixit/web/uploads/images/user/";
            ImageService u = new ImageService();
            try {
                if (user.getImage() != null) {
                    File f = new File(currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + user.getImage());
                    f.delete();
                }
                filename = String.valueOf(user.getId()) + "." + u.getFileExtension(file);
                u.uploadNew(file, path, filename);
                us.ajouterImage(filename, user.getId());
                user = us.connect(user.getUsername());
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
                loadImage();
            } catch (IOException ex) {
                Logger.getLogger(EspaceOutilBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //logooo = file.getName();
        } else {
            System.out.println("FICHIER erroné");
        }
    }

    @FXML
    private void historiqueAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/HistoriquePaiement.fxml"));
        Parent root = fxmlLoader.load();
        HistoriquePaiementController controller = fxmlLoader.<HistoriquePaiementController>getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void loadUsersMessage() {
        File currDir = new File(System.getProperty("user.dir", "."));
        MessageService ms = new MessageService();
        UserService us = new UserService();
        ObservableList<Message> data = ms.getLastMessages(user.getId());
        ObservableList<HBox> listHbox = FXCollections.observableArrayList();
        for (Message m : data) {
            HBox h = new HBox();
            VBox v = new VBox();
            Label nomMess = new Label();
            String path;
            String content;
            if (m.getDestinataire() == user.getId()) {
                path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + us.getUserImage(m.getExpediteur());
                nomMess.setText(us.getUserName(m.getExpediteur()));
                if (m.getContenu().length() > 15) {
                    content = m.getContenu().substring(0, 15) + "...";
                } else {
                    content = m.getContenu();
                }
            } else {
                path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + us.getUserImage(m.getDestinataire());
                nomMess.setText(us.getUserName(m.getDestinataire()));
                if (m.getContenu().length() > 15) {
                    content = "Vous: " + m.getContenu().substring(0, 15) + "...";
                } else {
                    content = "Vous: " + m.getContenu();
                }
            }
            Image i = new Image(path, 50, 50, false, false);
            ImageView iv = new ImageView(i);

            Label contenu = new Label(content);

            Label date = new Label(Utils.formatDateTime(m.getDate()));
            date.setFont(Font.font(12));
            nomMess.setFont(Font.font(14));
            contenu.setFont(Font.font(15));
            v.getChildren().add(nomMess);
            v.getChildren().add(contenu);
            v.getChildren().add(date);

            h.getChildren().add(iv);
            h.getChildren().add(v);
            h.setSpacing(5);
            h.setId(String.valueOf(m.getId()));
            if (!m.isVu() && m.getDestinataire() == user.getId()) {
                nomMess.setStyle("-fx-font-weight: bold");
                contenu.setStyle("-fx-font-weight: bold");
                date.setStyle("-fx-font-weight: bold");
            }
            //h.setStyle("-fx-background-color: grey;-fx-padding: 0.25em 0.583em 0.25em 0.583em;");
            //else h.setStyle("-fx-padding: 0.25em 0.583em 0.25em 0.583em;");
            //h("");
            listHbox.add(h);
        }
        listUsers.setItems(listHbox);
        //listUsers.setStyle("-fx-control-inner-background:   rgba(255,255,255,0.1); -fx-background-color:   rgba(255,255,255,0.1);");
    }

    public void loadMessages(String id) {
        MessageService ms = new MessageService();
        UserService us = new UserService();
        ObservableList<Message> data;
        ObservableList<VBox> listHbox = FXCollections.observableArrayList();
        int idOther = ms.getIdOtherUser(Integer.parseInt(id), user.getId());
        User contacted = us.getUser(String.valueOf(idOther));
        Contacteduser = contacted;
        idMessage = id;
        ms.setSeen(user.getId(), idOther);
        frontIndexController.refresh();
        if (!ms.checkUnseen(user.getId())) {
            messagerie.setGraphic(null);
        }
        data = ms.getConversation(user.getId(), contacted.getId());
        for (Message m : data) {
            HBox h = new HBox();
            VBox v = new VBox();
            String nom;
            Label contenu = new Label(m.getContenu());
            if (m.getExpediteur() == user.getId()) {
                nom = Utils.upperCaseFirst(user.getFirstname());
                v.setAlignment(Pos.CENTER_RIGHT);
                contenu.setAlignment(Pos.CENTER_RIGHT);
            } else {
                nom = Utils.upperCaseFirst(contacted.getFirstname());
                v.setAlignment(Pos.CENTER_LEFT);
                contenu.setAlignment(Pos.CENTER_LEFT);
            }

            contenu.setFont(Font.font(18));
            contenu.setWrapText(true);
            contenu.setMaxWidth(600);

            Label heure = new Label("Par " + nom + ", le " + Utils.formatDateTime(m.getDate()));
            heure.setFont(Font.font(10));

            v.getChildren().add(contenu);
            v.getChildren().add(heure);

            listHbox.add(v);
        }
        messages.setItems(listHbox);
        messages.scrollTo(messages.getItems().size() - 1);
    }

    @FXML
    private void envoyerAction(ActionEvent event) {
        if (nouveauMessage.getText().length() < 1) {

        } else {
            MessageService ms = new MessageService();
            ms.envoyerMessage(new Message(0, user.getId(), Contacteduser.getId(), true, new Timestamp(System.currentTimeMillis()), nouveauMessage.getText()));
            loadMessages(idMessage);
            nouveauMessage.setText("");
            loadUsersMessage();
        }
    }

}
