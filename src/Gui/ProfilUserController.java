/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Horraire;
import Entities.Langue;
import Entities.User;
import Services.HorraireService;
import Services.LangueService;
import Services.UserLangueService;
import Services.UserService;
import Services.Utils;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProfilUserController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Label nom;
    @FXML
    private Label telephone;
    @FXML
    private Label email;
    @FXML
    private VBox horraires;

    String id;
    User user;
    @FXML
    private HBox langues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
            loadUser();
        });
    }

    public void loadUser() {
        UserService us = new UserService();
        HorraireService hs = new HorraireService();
        User u = us.getUser(id);
        loadLangues(u);
        loadHorraires(hs.getUserHorraire(u, 0), "dimanche");
        loadHorraires(hs.getUserHorraire(u, 1), "lundi");
        loadHorraires(hs.getUserHorraire(u, 2), "mardi");
        loadHorraires(hs.getUserHorraire(u, 3), "mercredi");
        loadHorraires(hs.getUserHorraire(u, 4), "jeudi");
        loadHorraires(hs.getUserHorraire(u, 5), "vendredi");
        loadHorraires(hs.getUserHorraire(u, 6), "samedi");
        loadImage(u);
        nom.setText(Utils.upperCaseFirst(u.getFirstname()) + " " + Utils.upperCaseFirst(u.getLastname()));
        email.setText(u.getEmail());
        if (u.getPhone() > 100) {
            telephone.setText(String.valueOf(u.getPhone()));
        } else {
            telephone.setText(" ");
        }
    }

    public void loadHorraires(ObservableList<Horraire> h, String jour) {
        if (h.size() > 0) {
            Label j = new Label(Utils.upperCaseFirst(jour) + ":");
            j.setStyle("-fx-font-weight: bold");
            horraires.getChildren().add(j);
            for (Horraire ho : h) {
                FontAwesome fs = new FontAwesome();
                Node icon = fs.create(FontAwesome.Glyph.CLOCK_ALT).color(Color.BLACK).size(17);
                icon.setId("icon");
                String hd = String.format("%02d", ho.getHeureDebut().getHours()) + ":" + String.format("%02d", ho.getHeureDebut().getMinutes());
                String hf = String.format("%02d", ho.getHeureFin().getHours()) + ":" + String.format("%02d", ho.getHeureFin().getMinutes());
                Label hh=new Label("De " + hd + " à " + hf);
                hh.setGraphic(icon);
                horraires.getChildren().add(hh);
            }
        }
    }

    public void loadLangues(User u)
    {
        UserLangueService uls=new UserLangueService();
        for (Langue l : uls.getUserLangue(u))
        {
            Label langue=new Label(Utils.upperCaseFirst(l.getLibelle()));
            FontAwesome fs = new FontAwesome();
            Node icon = fs.create(FontAwesome.Glyph.ANGLE_RIGHT).color(Color.BLACK).size(17);
            icon.setId("icon");
            langue.setGraphic(icon);
            langue.setFont(Font.font(18));
            langues.getChildren().add(langue);
        }
    }
    
    private void loadImage(User u) {
        File currDir = new File(System.getProperty("user.dir", "."));
        System.out.println(currDir.toPath().getRoot().toString());
        if (u.getImage() != null) {
            String path = "file:" + currDir.toPath().getRoot().toString() + "wamp64\\www\\fixit\\web\\uploads\\images\\user\\" + u.getImage();
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
}
