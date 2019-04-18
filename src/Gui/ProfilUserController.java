/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Horraire;
import Services.HorraireService;
import Entities.Langue;
import Entities.Like_Dislike;
import Services.UserLangueService;
import Entities.ServiceUser;
import Entities.User;
import Services.Like_DislikeService;
import Services.ServiceUserService;
import Services.UserService;
import Services.Utils;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProfilUserController implements Initializable {

    @FXML
    private FrontIndexController frontIndexController;
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

    private String id;
    private String rech;
    private User user;
    @FXML
    private HBox langues;
    @FXML
    private ListView<HBox> services;
    @FXML
    private Button like;
    @FXML
    private Button dislike;
    @FXML
    private Button retour;
    @FXML
    private Button contact;

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

    public String getRech() {
        return rech;
    }

    public void setRech(String rech) {
        this.rech = rech;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            loadUser();
            styleLikeDislike();
            prepareLikesDislikesCount();
        });
    }

    public void loadUser() {
        FontAwesome fs = new FontAwesome();
        Node icon = fs.create(FontAwesome.Glyph.SEND).color(Color.WHITE).size(16);
        icon.setId("icon");
        contact.setGraphic(icon);
        UserService us = new UserService();
        HorraireService hs = new HorraireService();
        User u = us.getUser(id);
        loadLangues(u);
        loadServices(u);
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
    
    public void loadServices(User u)
    {
        ServiceUserService r = new ServiceUserService();
        ObservableList<ServiceUser> list = r.afficherServiceUser(u.getId());
        ObservableList<HBox> listHbox=FXCollections.observableArrayList();
        for (ServiceUser su : list){
            HBox h=new HBox();
            HBox hp=new HBox();
            VBox v=new VBox();
            try {
                Label prix=new Label(String.valueOf((int)su.getPrix())+" SCoins");
                prix.setAlignment(Pos.CENTER_RIGHT);
                //prix.setFont(Font.font(17));
                hp.setAlignment(Pos.CENTER_RIGHT);
                hp.setMaxWidth(Double.MAX_VALUE);
                hp.getChildren().add(prix);
                
                Label nomService=new Label(r.getServiceName(su.getIdService()));
                //nomService.setFont(Font.font(17));
                nomService.setStyle("-fx-font-weight: bold");
                Label description=new Label(su.getDescription());
                //description.setFont(Font.font(16));
                v.getChildren().add(nomService);
                v.getChildren().add(description);
                h.getChildren().add(v);
                HBox.setHgrow(hp, Priority.ALWAYS);
                h.getChildren().add(hp);
                listHbox.add(h);
            } catch (SQLException ex) {
                Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        services.setItems(listHbox);
        services.applyCss();
        
    }
    
    private void loadImage(User u) {
        File currDir = new File(System.getProperty("user.dir", "."));
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
    public void styleLikeDislike()
    {
        Like_DislikeService lds = new Like_DislikeService();
        int get = lds.getInfo(this.user.getId(), Integer.parseInt(id));
        if(get==0)
        {
            animatedislike();
            animatenotLike();
        }
        else if(get==1)
        {
            animateLike();
            animatenotdislike();
        }
        else
        {
            animatenotLike();
            animatenotdislike();
        }
        /*FontAwesome fsLike = new FontAwesome();
        Node iconLike = fsLike.create(FontAwesome.Glyph.THUMBS_ALT_UP).color(Color.GREEN).size(25);
        iconLike.setId("likeup");
        FontAwesome fsDislike = new FontAwesome();
        Node iconDislike = fsDislike.create(FontAwesome.Glyph.THUMBS_ALT_DOWN).color(Color.RED).size(25);
        iconDislike.setId("likeup");
        like.setGraphic(iconLike);
        dislike.setGraphic(iconDislike);*/
    }
    
    public void prepareLikesDislikesCount()
    {
        Like_DislikeService lds = new Like_DislikeService();
        int nbrLike= lds.countLikes(Integer.parseInt(this.id));
        int nbrDislike = lds.countDislikes(Integer.parseInt(this.id));
        System.out.println(nbrDislike);
        like.setText(" /"+nbrLike);
        dislike.setText(" /"+nbrDislike);
    }

    @FXML
    private void likeAction(ActionEvent event) {
        Like_DislikeService lds = new Like_DislikeService();       
        if(lds.verifyLike(this.user.getId(),Integer.parseInt(this.id)))        
        {
            System.out.println("you have already liked this user !");
            lds.removeLikes(this.user.getId(),Integer.parseInt(this.id));
            animatenotLike();
        }
        else
        {
            if(lds.verifyDislike(this.user.getId(),Integer.parseInt(this.id)))
            {
                lds.removeLikes(this.user.getId(),Integer.parseInt(this.id));
                animatenotdislike();
                Like_Dislike l = new Like_Dislike(this.user.getId(),Integer.parseInt(this.id),1);
                lds.insertLikeDislike(l);
                animateLike();
                System.out.println("you have disliked this user remove it to like it");
            }
            else
            {
                Like_Dislike l = new Like_Dislike(this.user.getId(),Integer.parseInt(this.id),1);
                lds.insertLikeDislike(l);
                animateLike();
                System.out.println("like success");
            }
        }
    }

    @FXML
    private void dislikeAction(ActionEvent event) {
        Like_DislikeService lds = new Like_DislikeService();       
        if(lds.verifyDislike(this.user.getId(),Integer.parseInt(this.id)))        
        {
            System.out.println("you have already disliked this user !");
            lds.removeLikes(this.user.getId(),Integer.parseInt(this.id));
            animatenotdislike();
        }
        else
        {
            if(lds.verifyLike(this.user.getId(),Integer.parseInt(this.id)))
            {
                lds.removeLikes(this.user.getId(),Integer.parseInt(this.id));
                animatenotLike();
                Like_Dislike l = new Like_Dislike(this.user.getId(),Integer.parseInt(this.id),0);
                lds.insertLikeDislike(l);
                animatedislike();
                System.out.println("you have liked this user remove it to like it");
            }
            else
            {
                Like_Dislike l = new Like_Dislike(this.user.getId(),Integer.parseInt(this.id),0);
                lds.insertLikeDislike(l);
                animatedislike();
                System.out.println("dislike success");
            }
        }
    }
    public void animateLike()
    {
        Like_DislikeService lds = new Like_DislikeService();
        int nbrLike= lds.countLikes(Integer.parseInt(this.id));
        //int nbrDislike = lds.countDislikes(Integer.parseInt(this.id));
         FontAwesome fsLike = new FontAwesome();
        Node iconLike = fsLike.create(FontAwesome.Glyph.THUMBS_UP).color(Color.GREEN).size(25);
        iconLike.setId("likeupempty");
        like.setGraphic(iconLike);
        like.setText(" /"+nbrLike);
    }
    
    public void animatenotLike()
    {
        Like_DislikeService lds = new Like_DislikeService();
        int nbrLike= lds.countLikes(Integer.parseInt(this.id));
        //int nbrDislike = lds.countDislikes(Integer.parseInt(this.id));
         FontAwesome fsLike = new FontAwesome();
        Node iconLike = fsLike.create(FontAwesome.Glyph.THUMBS_ALT_UP).color(Color.GREEN).size(25);
        iconLike.setId("likeupfull");
        like.setGraphic(iconLike);
        like.setText(" /"+nbrLike);
    }
    
   
    public void animatedislike()
    {
         Like_DislikeService lds = new Like_DislikeService();
        //int nbrLike= lds.countLikes(Integer.parseInt(this.id));
        int nbrDislike = lds.countDislikes(Integer.parseInt(this.id));
         FontAwesome fsLike = new FontAwesome();
        Node iconLike = fsLike.create(FontAwesome.Glyph.THUMBS_DOWN).color(Color.RED).size(25);
        iconLike.setId("dislikefull");
        dislike.setGraphic(iconLike);
        dislike.setText(" /"+nbrDislike);
    }
     public void animatenotdislike()
    {
         Like_DislikeService lds = new Like_DislikeService();
        //int nbrLike= lds.countLikes(Integer.parseInt(this.id));
        int nbrDislike = lds.countDislikes(Integer.parseInt(this.id));
         FontAwesome fsLike = new FontAwesome();
        Node iconLike = fsLike.create(FontAwesome.Glyph.THUMBS_ALT_DOWN).color(Color.RED).size(25);
        iconLike.setId("dislikeempty");
        dislike.setGraphic(iconLike);
        dislike.setText(" /"+nbrDislike);
    }

    @FXML
    private void retourAction(ActionEvent event) {
        try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/FrontAccueil.fxml"));
                Parent Rec = fxmlLoader.load();
                Scene scene = new Scene(Rec);
                FrontAccueilController controller = fxmlLoader.<FrontAccueilController>getController();
                controller.setUser(this.user);
                controller.setRech(rech);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.show();
                stage.setScene(scene);

            } catch (IOException ex) {
                System.out.println(ex);
            }
    }

    @FXML
    private void contactAction(ActionEvent event) {
        try {
            UserService us = new UserService();
            User u = us.getUser(id);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/Conversation.fxml"));
            Parent root = fxmlLoader.load();
            ConversationController controller = fxmlLoader.<ConversationController>getController();
            controller.setCurrent(user);
            controller.setContacted(u);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
