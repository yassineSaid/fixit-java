/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.Like_DislikeService;
import Services.MessageService;
import Services.RealisationServiceService;
import Services.UserService;
import Services.Utils;
import Services.bonusService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FrontIndexController implements Initializable {

    @FXML
    private Button espaceServ;
    @FXML
    private Button espaceOut;
    @FXML
    private Button espaceProd;
    @FXML
    private Button espaceRec;
    @FXML
    private Label userName;
    @FXML
    private ImageView photo;

    private User user;
    @FXML
    private Button espaceAvis;
    @FXML
    private Button profil;
    @FXML
    private Button deconnexion;
    @FXML
    private Button accueil;
    @FXML
    private Label likenumber;
    @FXML
    private Label dislikenbr;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Button getEspaceServ() {
        return espaceServ;
    }

    public void setEspaceServ(Button espaceServ) {
        this.espaceServ = espaceServ;
    }

    public Button getEspaceOut() {
        return espaceOut;
    }

    public void setEspaceOut(Button espaceOut) {
        this.espaceOut = espaceOut;
    }

    public Button getEspaceProd() {
        return espaceProd;
    }

    public void setEspaceProd(Button espaceProd) {
        this.espaceProd = espaceProd;
    }

    public Button getEspaceRec() {
        return espaceRec;
    }

    public void setEspaceRec(Button espaceRec) {
        this.espaceRec = espaceRec;
    }

    public Button getEspaceAvis() {
        return espaceAvis;
    }

    public void setEspaceAvis(Button espaceAvis) {
        this.espaceAvis = espaceAvis;
    }

    public Button getProfil() {
        return profil;
    }

    public void setProfil(Button profil) {
        this.profil = profil;
    }

    public Button getAccueil() {
        return accueil;
    }

    public void setAccueil(Button accueil) {
        this.accueil = accueil;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            if (user != null) {
                refresh();
                
            }

        });
    }
    public void refresh(){
        MessageService ms=new MessageService();
        FontAwesome fs = new FontAwesome();
        Node icon = fs.create(FontAwesome.Glyph.SIGN_OUT).color(Color.WHITE).size(17);
        icon.setId("icon");
        Node icon1 = fs.create(FontAwesome.Glyph.INFO_CIRCLE).color(Color.web("#017c00")).size(17);
        icon1.setId("icon"); 
        deconnexion.setGraphic(icon);
        userName.setText(Utils.upperCaseFirst(user.getFirstname()) + " " + Utils.upperCaseFirst(user.getLastname()));
        loadImage();
        getLikeDislike();
        if (ms.checkUnseen(user.getId())) {
            profil.setGraphic(icon1);
        }
        else profil.setGraphic(null);
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (ms.checkUnnotified(user.getId())){
                URL path =getClass().getResource("/Resources/message.mp3");
                AudioClip ac=new AudioClip(path.toString());
                ac.play();
                profil.setGraphic(icon1);
                ms.setNotified(user.getId());
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
    public void loadImage() {
        File currDir = new File(System.getProperty("user.dir", "."));
        System.out.println(currDir.toPath().getRoot().toString());
        if (user.getImage() != null) {
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
    }

    @FXML
    private void espaceServAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceServiceFront.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceServiceFrontController controller = fxmlLoader.<EspaceServiceFrontController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void espaceProdAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceProduitFront.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceProduitFrontController controller = fxmlLoader.<EspaceProduitFrontController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
     @FXML
    private void profilAction(ActionEvent event) {
    try {
    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/Profil.fxml"));
    Parent Rec = fxmlLoader.load();
    ProfilController controller = fxmlLoader.<ProfilController>getController();
    controller.setUser(this.getUser());
    Scene scene = new Scene(Rec);
    
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.show();
    stage.setScene(scene);
    
    } catch (IOException ex) {
    System.out.println(ex);
    }
    
    }
    @FXML
    private void espaceAvisAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/avisFront.fxml"));
            Parent Rec = fxmlLoader.load();
            AvisFrontController controller = fxmlLoader.<AvisFrontController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/login.fxml"));
            Parent back = fxmlLoader.load();
            Scene scene = new Scene(back);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void espaceOutAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/EspaceOutilFront.fxml"));
            Parent Rec = fxmlLoader.load();
            EspaceOutilFrontController controller = fxmlLoader.<EspaceOutilFrontController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void espaceRecAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/reclamationFront.fxml"));
            Parent Rec = fxmlLoader.load();
            ReclamationFrontController controller = fxmlLoader.<ReclamationFrontController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/FrontAccueil.fxml"));
            Parent Rec = fxmlLoader.load();
            FrontAccueilController controller = fxmlLoader.<FrontAccueilController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(Rec);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
        @FXML
    private void espaceAnAction(ActionEvent event) {
        try {
			
		 			 
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/annonceFront.fxml"));   
            Parent An = Loader.load();          
	    AnnonceFrontController controller = Loader.<AnnonceFrontController>getController();
            controller.setUser(this.getUser());
            Scene scene = new Scene(An);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void getLikeDislike()
    {
         Like_DislikeService lds = new Like_DislikeService();
        int nbrLike= lds.countLikes(this.user.getId());
        int nbrDislike = lds.countDislikes(this.user.getId());
        System.out.println(nbrDislike);
        
        
        FontAwesome fsLike = new FontAwesome();
        Node iconLike = fsLike.create(FontAwesome.Glyph.THUMBS_ALT_UP).color(Color.WHITE).size(25);
        iconLike.setId("likeupempty");
        likenumber.setGraphic(iconLike);
        likenumber.setText(" /"+nbrLike);
         FontAwesome fsDislike = new FontAwesome();
        Node iconDislike = fsDislike.create(FontAwesome.Glyph.THUMBS_ALT_DOWN).color(Color.WHITE).size(25);
        iconLike.setId("dislikefull");
        dislikenbr.setGraphic(iconDislike);
        dislikenbr.setText(" /"+nbrDislike);
        
    }
    
}
