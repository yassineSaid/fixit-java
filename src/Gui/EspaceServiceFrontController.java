/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Outil;
import Entities.QuizUser;
import Entities.Service;
import Entities.User;
import Services.CategorieServiceService;
import Services.QuizUserService;
import Services.ServiceService;
import Services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class EspaceServiceFrontController implements Initializable {

   private ObservableList<String> dataQuiz = FXCollections.observableArrayList();
   @FXML
   private FrontIndexController frontIndexController;
    private User user;
    @FXML
    private ListView<CategorieService> listCategorie;
    @FXML
    private ListView<Service> listService;
    @FXML
    private ImageView imageQuiz1;
    @FXML
    private ImageView imageQuiz2;
    @FXML
    private ImageView imageQuiz3;
    @FXML
    private Button jouerQuiz1;
    @FXML
    private Button jouerQuiz2;
    @FXML
    private Button jouerQuiz3;
    @FXML
    private AnchorPane quiz1;
    @FXML
    private ComboBox<String> quiz1Question1=new ComboBox<>(dataQuiz);
    @FXML
    private ComboBox<String> quiz1Question2;
    @FXML
    private ComboBox<String> quiz1Question3;
    @FXML
    private ComboBox<String> quiz1Question4;
    @FXML
    private Button confirmerQuiz1;
    @FXML
    private Button retourQuiz1;
    @FXML
    private Label labelQ1;
    @FXML
    private Label labelQ2;
    @FXML
    private Label labelQ3;
    @FXML
    private Label labelQ4;
    @FXML
    private Label labelQ5;
    @FXML
    private ComboBox<String> quiz1Question5;
    @FXML
    private ImageView sg100;
    @FXML
    private ImageView sg500;
    @FXML
    private ImageView sg1000;
    @FXML
    private Label labelg100;
    @FXML
    private Label labelg500;
    @FXML
    private Label labelg1000;
    @FXML
    private Button confirmerQuiz2;
    @FXML
    private Button confirmerQuiz3;
    @FXML
    private Label labelQuizNum;
    @FXML
    private Label labelQ1Pass;
    @FXML
    private Label labelQ2Pass;
    @FXML
    private Label labelQ3Pass;
    @FXML
    private Label resultatQuiz;
    

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
           /*resultatQuiz.setStyle("-fx-font-weight: bold;");
        resultatQuiz.setFont(new Font("Baskerville Old Face",30));*/
           
            labelQ1.setMaxWidth(328);
            labelQ2.setMaxWidth(328);
            labelQ3.setMaxWidth(328);
            labelQ4.setMaxWidth(328);
            labelQ5.setMaxWidth(328);
           labelQ1.setWrapText(true);
           labelQ2.setWrapText(true);
           labelQ3.setWrapText(true);
           labelQ4.setWrapText(true);
           labelQ5.setWrapText(true);
           resultatQuiz.setVisible(true);
        quiz1Question1.getItems().clear();
        quiz1Question2.getItems().clear();
        quiz1Question3.getItems().clear();
        quiz1Question4.getItems().clear();
        quiz1Question5.getItems().clear();
            quiz1.setVisible(false);
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            frontIndexController.getEspaceServ().setStyle("-fx-background-color: #f4f4f4");
            loadCategorieFromDatabase();
            listCategorie.setCellFactory(lv -> new Poules());
            QuizUser qu =new QuizUser();
            QuizUserService qus =new QuizUserService();
            
            ObservableList<Integer> quizUser = FXCollections.observableArrayList();
            quizUser=qus.afficherQuizUser(this.getUser().getId());
            
            System.out.println(qus.getTentative(this.getUser().getId(),1));
            for(int i=0;i<quizUser.size();i++)
            {
                if(quizUser.get(i)==1)
                {
                    if(qus.getTentative(this.getUser().getId(), 1)==3){
                        
                        jouerQuiz1.setDisable(true);
                    labelQ1Pass.setTextFill(Color.web("red"));
                            
                    labelQ1Pass.setText("Vous avez déja passé ce quizz !");
                    labelg100.setVisible(false);
                    sg100.setVisible(false);
                    }
                     if(qus.getTentative(this.getUser().getId(), 1)==2){
                        
                        jouerQuiz1.setDisable(true);
                    labelQ1Pass.setTextFill(Color.web("red"));
                            
                    labelQ1Pass.setText("Tentatives épuisés ! ");
                    labelg100.setVisible(false);
                    sg100.setVisible(false);
                    }
                     if(qus.getTentative(this.getUser().getId(), 1)==1){
                        labelg100.setText("gagner 50");
                    labelQ1Pass.setTextFill(Color.web("red"));
                            
                    labelQ1Pass.setText("Il vous reste une tentative ! ");
                    }
                    
                }
                if(quizUser.get(i)==2)
                {
                    if(qus.getTentative(this.getUser().getId(), 2)==3){
                    
                        jouerQuiz2.setDisable(true);
                    labelQ2Pass.setTextFill(Color.web("red"));
                    labelQ2Pass.setText("Vous avez déja passé ce quizz !");
                    labelg500.setVisible(false);
                    sg500.setVisible(false);
                    }
                    if(qus.getTentative(this.getUser().getId(), 2)==2){
                    
                        jouerQuiz2.setDisable(true);
                    labelQ2Pass.setTextFill(Color.web("red"));
                    labelQ2Pass.setText("Tentatives épuisés !");
                    labelg500.setVisible(false);
                    sg500.setVisible(false);
                    }
                    if(qus.getTentative(this.getUser().getId(), 2)==1){
                        labelg500.setText("Gagner 250");
                    labelQ2Pass.setTextFill(Color.web("red"));
                    labelQ2Pass.setText("Il vous reste une tentative !");
                    }
                    
                    
                }
                if(quizUser.get(i)==3)
                {
                    
                    if(qus.getTentative(this.getUser().getId(), 3)==3){
                    
                    jouerQuiz3.setDisable(true);
                    labelQ3Pass.setTextFill(Color.web("red"));
                    labelQ3Pass.setText("Vous avez déja passé ce quizz !");
                    labelg1000.setVisible(false);
                    sg1000.setVisible(false);
                    }
                    if(qus.getTentative(this.getUser().getId(), 3)==2){
                    
                        
                    jouerQuiz3.setDisable(true);
                    labelQ3Pass.setTextFill(Color.web("red"));
                    labelQ3Pass.setText("Tentatives épuisés !");
                    labelg1000.setVisible(false);
                    sg1000.setVisible(false);
                    }
                    if(qus.getTentative(this.getUser().getId(), 3)==1){
                        labelg1000.setText("Gagner 500");
                    labelQ3Pass.setTextFill(Color.web("red"));
                    labelQ3Pass.setText("Il vous reste une tentative !");
                    }
                    
                }
            }
            /*if(qus.getTentative(this.getUser().getId(),1)==2){
                    jouerQuiz1.setDisable(true);
                    labelQ1Pass.setTextFill(Color.web("red"));
                            
                    labelQ1Pass.setText("Tentatives épuisés !");
            
        }
            if(qus.getTentative(this.getUser().getId(),1)==3){
                    jouerQuiz1.setDisable(true);
                    labelQ1Pass.setTextFill(Color.web("red"));
                            
                    labelQ1Pass.setText("Vous avez déja passé ce Quiz");
            
        }*/
            
        });
        // TODO
    }

    private void loadServiceFromDatabase(int id) {
        try {
            ServiceService service = new ServiceService();
            ObservableList<Service> rs = service.getAllServiceC(id);
            //paginationOutilFront.setPageFactory(this::createPage);
            listService.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    private void loadCategorieFromDatabase() {
        try {
            CategorieServiceService service = new CategorieServiceService();
            ObservableList<CategorieService> rs = service.afficherCategorie();
            //paginationOutilFront.setPageFactory(this::createPage);
            listCategorie.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void jouerQuiz1(ActionEvent event) {
        
        resultatQuiz.setVisible(false);
        retourQuiz1.setDisable(false);
        retourQuiz1.setVisible(true);
        quiz1.setVisible(true);
        labelQuizNum.setVisible(true);
        labelQuizNum.setVisible(true);
        labelQuizNum.setText("QUIZ 1");
        labelQ1Pass.setVisible(false);
        labelQ2Pass.setVisible(false);
        labelQ3Pass.setVisible(false);
        confirmerQuiz1.setVisible(true);
        confirmerQuiz2.setVisible(false);
        confirmerQuiz3.setVisible(false);
        jouerQuiz1.setVisible(false);
        jouerQuiz2.setVisible(false);
        jouerQuiz3.setVisible(false);
        imageQuiz1.setVisible(false);
        imageQuiz2.setVisible(false);
        imageQuiz3.setVisible(false);
        labelg100.setVisible(false);
        labelg500.setVisible(false);
        labelg1000.setVisible(false);
        sg100.setVisible(false);
        sg500.setVisible(false);
        sg1000.setVisible(false);
        labelQ1.setText("Quel animal ne vole pas ?  ");
        quiz1Question1.getItems().addAll("Le pingouin","La cigogne","Le manchot","Le pélican");
        labelQ2.setText("Qu’étudie un arachnologue");
        quiz1Question2.getItems().addAll("Les rats","Les serpents","Les araignées","Les aigles");
        labelQ3.setText("Quel est le cri de la brebis ?");
        quiz1Question3.getItems().addAll("Le beuglement","Le bêlement","Le jappement","Le vagissement");
        labelQ4.setText("Quel animal est à l’honneur dans le film « Jappeloup » ?");
        quiz1Question4.getItems().addAll("Un renard","Un serpent","Un tigre","Un cheval");
        labelQ5.setText("Quel animal est associé à Panurge dans une célèbre expression ?");
        quiz1Question5.getItems().addAll("Le renard","Un mouton","Le lion","Le zèbre");
        
        
    }

    @FXML
    private void jouerQuiz2(ActionEvent event) {
        
        resultatQuiz.setVisible(false);
      
        quiz1.setVisible(true);
        retourQuiz1.setDisable(false);
        retourQuiz1.setVisible(true);
        labelQuizNum.setVisible(true);
        labelQuizNum.setText("QUIZ 2");
        labelQ1Pass.setVisible(false);
        labelQ2Pass.setVisible(false);
        labelQ3Pass.setVisible(false);
        confirmerQuiz1.setVisible(false);
        confirmerQuiz2.setVisible(true);
        confirmerQuiz3.setVisible(false);
        jouerQuiz1.setVisible(false);
        jouerQuiz2.setVisible(false);
        jouerQuiz3.setVisible(false);
        imageQuiz1.setVisible(false);
        imageQuiz2.setVisible(false);
        imageQuiz3.setVisible(false);
        labelg100.setVisible(false);
        labelg500.setVisible(false);
        labelg1000.setVisible(false);
        sg100.setVisible(false);
        sg500.setVisible(false);
        sg1000.setVisible(false);
        labelQ1.setText(" Qu’est-ce qu’un ouistiti ?");
        quiz1Question1.getItems().addAll("Un aigle","Un poisson","Un chien","Un singe");
        labelQ2.setText("Dans quelle ville se trouve le Musée de l’Ermitage ?");
        quiz1Question2.getItems().addAll("Moscou","Saint-Pétersbourg","Kiev","Stockholm");
        labelQ3.setText("Quel arbre produit la noix de pécan ?");
        quiz1Question3.getItems().addAll("Le macadamia","Le noisetier","Le pacanier","Le pécunier");
        labelQ4.setText("Que fête-t-on le 1er mai ?");
        quiz1Question4.getItems().addAll("Le travail","Le printemps","Les mamans","Le Beaujolais");
        labelQ5.setText("Quelle est la capitale de l’Inde ?");
        quiz1Question5.getItems().addAll("Calcutta","Mumbai","New Delhi","Bangalore");
        
    }

    @FXML
    private void jouerQuiz3(ActionEvent event) {
        resultatQuiz.setVisible(false);
        quiz1.setVisible(true);
        retourQuiz1.setDisable(false);
        retourQuiz1.setVisible(true);
        labelQuizNum.setVisible(true);
        labelQuizNum.setText("QUIZ 2");
        labelQ1Pass.setVisible(false);
        labelQ2Pass.setVisible(false);
        labelQ3Pass.setVisible(false);
        confirmerQuiz1.setVisible(false);
        confirmerQuiz2.setVisible(false);
        confirmerQuiz3.setVisible(true);
        jouerQuiz1.setVisible(false);
        jouerQuiz2.setVisible(false);
        jouerQuiz3.setVisible(false);
        imageQuiz1.setVisible(false);
        imageQuiz2.setVisible(false);
        imageQuiz3.setVisible(false);
        labelg100.setVisible(false);
        labelg500.setVisible(false);
        labelg1000.setVisible(false);
        sg100.setVisible(false);
        sg500.setVisible(false);
        sg1000.setVisible(false);
        labelQ1.setText("Quel président français est à l’origine de la construction de la pyramide du Louvre ?");
        quiz1Question1.getItems().addAll("Georges Pompidou","François Mitterrand","Charles de Gaulle","Jacques Chirac");
        labelQ2.setText("À quel peintre doit-on « L'autoportrait au gilet vert » ?");
        quiz1Question2.getItems().addAll("Pierre Bonnard","Rembrandt","Eugène Delacroix","Jacques-Louis David");
        labelQ3.setText("Qui a peint le célèbre tableau « Café de nuit à Arles »  ?");
        quiz1Question3.getItems().addAll("Paul Cézanne","Claude Monet","Pablo Picasso","Vincent Van Gogh");
        labelQ4.setText(" Dans quelle ville européenne se trouve le musée Guggenheim ?");
        quiz1Question4.getItems().addAll("Berlin","Bilbao","Londres","Paris");
        labelQ5.setText("À quel mouvement artistique appartenaient Rembrandt, Vermeer,  Velázquez ou Rubens ?");
        quiz1Question5.getItems().addAll("Le baroque","Le rococo","Le pop art","Le symbolisme");
        
    }

    @FXML
    private void confirmerQuiz1(ActionEvent event) {
            QuizUser qu =new QuizUser();
            QuizUserService qus=new QuizUserService();
            if(qus.verifier(this.getUser().getId(), 1)){
                
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(1);
            qu.setTentative(0);
            qus.ajouterQuizUser(qu);
            }
            if(!qus.verifier(this.getUser().getId(), 1)){
                
            int i=qus.getTentative(this.getUser().getId(), 1);
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(1);
            qu.setTentative(i+1);
            qus.modifierQuizUser(qu);
            }
        if (quiz1Question1.getValue().equals("Le manchot")
                &&quiz1Question2.getValue().equals("Les araignées")
                &&quiz1Question3.getValue().equals("Le bêlement")
                &&quiz1Question4.getValue().equals("Un cheval")
                &&quiz1Question5.getValue().equals("Un mouton")){
            if(qus.getTentative(this.getUser().getId(), 1)==1){
                qu.setIdQuiz(1);
            qu.setTentative(3);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            UserService u=new UserService();
            u.modifierSolde(this.getUser(), 100);
            resultatQuiz.setText("Bravo vous Avez gagné 100 Scoins");
            }
            if(qus.getTentative(this.getUser().getId(), 1)==2){
                qu.setIdQuiz(1);
            qu.setTentative(3);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            UserService u=new UserService();
            u.modifierSolde(this.getUser(), 50);
            resultatQuiz.setText("Bravo vous Avez gagné 50 Scoins");
            }
            System.out.println("selon");
            
            
        }
        else {
            System.out.println("non");
            if(qus.getTentative(this.getUser().getId(), 1)==1){
                qu.setIdQuiz(1);
                qu.setTentative(1);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            resultatQuiz.setText("Vous avez échoué.Réessayer et gagner 50 SCoins!");
            labelg100.setText("Gagner 50");
            }
            if(qus.getTentative(this.getUser().getId(), 1)==2){
                qu.setTentative(2);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            resultatQuiz.setText("Tentative épuisés !");
            labelg100.setVisible(false);
            sg100.setVisible(false);
            }
            
        }
        quiz1.setVisible(false);
        labelQ1Pass.setVisible(true);
        labelQ2Pass.setVisible(true);
        labelQ3Pass.setVisible(true);
        confirmerQuiz1.setVisible(true);
        confirmerQuiz2.setVisible(false);
        confirmerQuiz3.setVisible(true);
        jouerQuiz1.setVisible(true);
        jouerQuiz2.setVisible(true);
        jouerQuiz3.setVisible(true);
        imageQuiz1.setVisible(true);
        imageQuiz2.setVisible(true);
        imageQuiz3.setVisible(true);
        labelg100.setVisible(true);
        labelg500.setVisible(true);
        labelg1000.setVisible(true);
        sg100.setVisible(true);
        sg500.setVisible(true);
        sg1000.setVisible(true);
        labelQuizNum.setVisible(false);
        
            this.initialize(null, null);
            
    }

    @FXML
    private void retourQuiz1(ActionEvent event) {
        quiz1.setVisible(false);
        labelQ1Pass.setVisible(true);
        labelQ2Pass.setVisible(true);
        labelQ3Pass.setVisible(true);
        confirmerQuiz1.setVisible(true);
        confirmerQuiz2.setVisible(false);
        confirmerQuiz3.setVisible(true);
        jouerQuiz1.setVisible(true);
        jouerQuiz2.setVisible(true);
        jouerQuiz3.setVisible(true);
        imageQuiz1.setVisible(true);
        imageQuiz2.setVisible(true);
        imageQuiz3.setVisible(true);
        labelg100.setVisible(true);
        labelg500.setVisible(true);
        labelg1000.setVisible(true);
        sg100.setVisible(true);
        sg500.setVisible(true);
        sg1000.setVisible(true);
        labelQuizNum.setVisible(false);
    }

    @FXML
    private void confirmerQuiz2(ActionEvent event) {
            
            QuizUser qu =new QuizUser();
            QuizUserService qus=new QuizUserService();
            if(qus.verifier(this.getUser().getId(), 2)){
                
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(2);
            qu.setTentative(0);
            qus.ajouterQuizUser(qu);
            }
            if(!qus.verifier(this.getUser().getId(), 2)){
                
            int i=qus.getTentative(this.getUser().getId(), 2);
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(2);
            qu.setTentative(i+1);
            qus.modifierQuizUser(qu);
            }
        if (quiz1Question1.getValue().equals("Un singe")
                &&quiz1Question2.getValue().equals("Saint-Pétersbourg")
                &&quiz1Question3.getValue().equals("Le pacanier")
                &&quiz1Question4.getValue().equals("Le travail")
                &&quiz1Question5.getValue().equals("New Delhi")){
            if(qus.getTentative(this.getUser().getId(), 2)==1){
                qu.setIdQuiz(2);
            qu.setTentative(3);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            UserService u=new UserService();
            u.modifierSolde(this.getUser(), 500);
            resultatQuiz.setText("Bravo vous Avez gagné 500 Scoins");
            }
            if(qus.getTentative(this.getUser().getId(), 2)==2){
                qu.setIdQuiz(2);
            qu.setTentative(3);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            UserService u=new UserService();
            u.modifierSolde(this.getUser(), 250);
            resultatQuiz.setText("Bravo vous Avez gagné 250 Scoins");
            }
            System.out.println("selon");
            
            
        }
        else {
            System.out.println("non");
            if(qus.getTentative(this.getUser().getId(),2)==1){
                qu.setIdQuiz(2);
                qu.setTentative(1);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            resultatQuiz.setText("Vous avez échoué.Réessayer et gagner 250 SCoins!");
            labelg500.setText("Gagner 250");
            }
            if(qus.getTentative(this.getUser().getId(), 2)==2){
                qu.setTentative(2);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            resultatQuiz.setText("Vous avez échoué !");
            labelg500.setVisible(false);
            sg500.setVisible(false);
            }
            this.initialize(null, null);
            
        }
        quiz1.setVisible(false);
        labelQ1Pass.setVisible(true);
        labelQ2Pass.setVisible(true);
        labelQ3Pass.setVisible(true);
        confirmerQuiz1.setVisible(true);
        confirmerQuiz2.setVisible(false);
        confirmerQuiz3.setVisible(true);
        jouerQuiz1.setVisible(true);
        jouerQuiz2.setVisible(true);
        jouerQuiz3.setVisible(true);
        imageQuiz1.setVisible(true);
        imageQuiz2.setVisible(true);
        imageQuiz3.setVisible(true);
        labelg100.setVisible(true);
        labelg500.setVisible(true);
        labelg1000.setVisible(true);
        sg100.setVisible(true);
        sg500.setVisible(true);
        sg1000.setVisible(true);
        labelQuizNum.setVisible(false);
        
    }

    @FXML
    private void confirmerQuiz3(ActionEvent event) {
            QuizUser qu =new QuizUser();
            QuizUserService qus=new QuizUserService();
            if(qus.verifier(this.getUser().getId(), 3)){
                
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(3);
            qu.setTentative(0);
            qus.ajouterQuizUser(qu);
            }
            if(!qus.verifier(this.getUser().getId(), 3)){
                
            int i=qus.getTentative(this.getUser().getId(), 3);
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(3);
            qu.setTentative(i+1);
            qus.modifierQuizUser(qu);
            }
        if (quiz1Question1.getValue().equals("François Mitterrand")
                &&quiz1Question2.getValue().equals("Eugène Delacroix")
                &&quiz1Question3.getValue().equals("Vincent Van Gogh")
                &&quiz1Question4.getValue().equals("Bilbao")
                &&quiz1Question5.getValue().equals("Le baroque")){
            if(qus.getTentative(this.getUser().getId(), 3)==1){
                qu.setIdQuiz(3);
            qu.setTentative(3);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            UserService u=new UserService();
            u.modifierSolde(this.getUser(), 1000);
            resultatQuiz.setText("Bravo vous Avez gagné 1000 Scoins");
            }
            if(qus.getTentative(this.getUser().getId(), 3)==2){
                qu.setIdQuiz(3);
            qu.setTentative(3);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            UserService u=new UserService();
            u.modifierSolde(this.getUser(), 500);
            resultatQuiz.setText("Bravo vous Avez gagné 500 Scoins");
            }
            System.out.println("selon");
            
            
        }
        else {
            System.out.println("non");
            if(qus.getTentative(this.getUser().getId(),3)==1){
                qu.setIdQuiz(3);
                qu.setTentative(1);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            resultatQuiz.setText("Vous avez échoué.Réessayer et gagner 500 SCoins!");
            labelg500.setText("Gagner 500");
            }
            if(qus.getTentative(this.getUser().getId(), 3)==2){
                qu.setTentative(2);
            qu.setIdUser(this.getUser().getId());
            qus.modifierQuizUser(qu);
            resultatQuiz.setText("Vous avez échoué !");
            labelg500.setVisible(false);
            sg500.setVisible(false);
            }
            this.initialize(null, null);
            
        }
        quiz1.setVisible(false);
        labelQ1Pass.setVisible(true);
        labelQ2Pass.setVisible(true);
        labelQ3Pass.setVisible(true);
        confirmerQuiz1.setVisible(true);
        confirmerQuiz2.setVisible(false);
        confirmerQuiz3.setVisible(true);
        jouerQuiz1.setVisible(true);
        jouerQuiz2.setVisible(true);
        jouerQuiz3.setVisible(true);
        imageQuiz1.setVisible(true);
        imageQuiz2.setVisible(true);
        imageQuiz3.setVisible(true);
        labelg100.setVisible(true);
        labelg500.setVisible(true);
        labelg1000.setVisible(true);
        sg100.setVisible(true);
        sg500.setVisible(true);
        sg1000.setVisible(true);
        labelQuizNum.setVisible(false);
    }

    public class Poules extends ListCell<CategorieService> {

        public Poules() {
        }

        protected void updateItem(CategorieService item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text description = new Text(item.getDescription());
                description.setWrappingWidth(300);;
                nom.setStyle("-fx-font-size: 25 arial;");
                description.setStyle("-fx-font-size: 15 arial;"
                        + "-fx-pref-width: 158px;");
                VBox vBox = new VBox(nom, description);
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);

                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieService/" + item.getImage(), 120, 120, false, false);
                ImageView img = new ImageView(image);

                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-font-color: transparent;");
                hBox.setSpacing(10);
                setGraphic(hBox);
                listCategorie.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        
                        CategorieService a = (CategorieService) listCategorie.getItems().get(listCategorie.getSelectionModel().getSelectedIndex());
                        loadServiceFromDatabase(a.getId());
                        listService.setCellFactory(lv -> new PoulesService());
                        System.out.println("test");
                    }
                });

            }
        }
    }

    public class PoulesService extends ListCell<Service> {

        public PoulesService() {
        }

        protected void updateItem(Service item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                Text nom = new Text(item.getNom());
                Text description = new Text(item.getDescription());
                Text nbrProviders = new Text("Nombre de réalisateurs" + Integer.toString(item.getNbrProviders()));
                description.setWrappingWidth(300);
                nom.setStyle("-fx-font-size: 25 arial;");
                description.setStyle("-fx-font-size: 15 arial;"
                        + "-fx-pref-width: 158px;");
                nbrProviders.setStyle("-fx-font-size: 10 arial;");
                VBox vBox = new VBox(nom, description, nbrProviders);
                vBox.setStyle("-fx-font-color: transparent;");
                vBox.setSpacing(10);

                Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/service/" + item.getImage(), 120, 120, false, false);
                ImageView img = new ImageView(image);

                HBox hBox = new HBox(img, vBox);
                hBox.setStyle("-fx-font-color: transparent;");
                hBox.setSpacing(10);
                setGraphic(hBox);

            }
        }
    }

}
