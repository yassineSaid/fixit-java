package Gui;

import Entities.Langue;
import Entities.User;
import Entities.UserLangue;
import Services.UserLangueService;
import Services.UserService;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class profilController implements Initializable {

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
                        username.setText(user.getUsername());
                        nom.setText(user.getLastname());
                        prenom.setText(user.getFirstname());
                        email.setText(user.getEmail());
                        loadImage();
                        afficherLanguesAction();
	    });
    }    
    @FXML
    private void modifierAction(ActionEvent event) {
        System.out.println("clicked");
        UserService us=new UserService();
        us.modifierNomPrenom(user, nom.getText(), prenom.getText());
        user=us.connect(user.getUsername());
        frontIndexController.setUser(user);
        frontIndexController.initialize(null, null);
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
    private void loadImage()
    {
        File currDir = new File(System.getProperty("user.dir", "."));
        System.out.println(currDir.toPath().getRoot().toString());
        String path="file:"+currDir.toPath().getRoot().toString()+"wamp64\\www\\fixit\\web\\uploads\\images\\user\\"+user.getImage();
        Image image = new Image(path);  
        photo.setImage(image);
        Image img = photo.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = photo.getFitWidth() / img.getWidth();
            double ratioY = photo.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
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
    private void supprimerAction(ActionEvent event)
    {
        Button button=(Button) event.getSource();
        UserLangueService usl=new UserLangueService();
        usl.supprimerUserLangue(parseInt(button.getId()), user);
        afficherLanguesAction();
    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        UserLangueService uls=new UserLangueService();
        uls.ajouterUserLangue(listLangues.getValue().getId(), user);
        afficherLanguesAction();
    }
}
