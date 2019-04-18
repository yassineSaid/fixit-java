/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieService;
import Entities.Notification;
import Entities.Outil;
import Entities.QuizUser;
import Entities.Service;
import Entities.ServiceUser;
import Entities.ServicesProposes;
import Entities.User;
import Services.CategorieServiceService;
import Services.MailService;
import Services.NotificationService;
import Services.ProfilMesServices;
import Services.QuizUserService;
import Services.SMSService;
import Services.ServiceService;
import Services.ServiceUserService;
import Services.ServicesProposesService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.mail.MessagingException;
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
    private ComboBox<String> quiz1Question1 = new ComboBox<>(dataQuiz);
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
    @FXML
    private AnchorPane service;
    @FXML
    private Label labelMesServices;
    @FXML
    private ListView<ServiceUser> mesServices;
    @FXML
    private Button ajouterUnService;
    @FXML
    private Button proposerUnService;
    @FXML
    private AnchorPane addService;
    @FXML
    private ComboBox<CategorieService> categorie;
    @FXML
    private ComboBox<Service> serviceC;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private Button ajouterS;
    @FXML
    private Button retourAjouterService;
    @FXML
    private AnchorPane proposerS;
    @FXML
    private TextField descriptionProposition;
    @FXML
    private TextField nomProposition;
    @FXML
    private ComboBox<CategorieService> categorieProposition;
    @FXML
    private Button confirmerProposition;
    @FXML
    private Button retourPropService;
    @FXML
    private Button supprimerSU;
    @FXML
    private AnchorPane SMSForm;
    @FXML
    private TextField textSMS;
    @FXML
    private Button envoyerSMS;
    @FXML
    private Label labelSMS;
    @FXML
    private Button verifierCode;
    @FXML
    private AnchorPane serviceDetails;
    @FXML
    private ImageView imageServiceDetails;
    @FXML
    private Label nomServiceDetails;
    @FXML
    private Label categorieServiceDetails;
    @FXML
    private Label descriptionServiceDetails;
    @FXML
    private Label nbrServiceDetails;
    @FXML
    private Button retourDetails;
    @FXML
    private ImageView imageProviders;
    @FXML
    private ImageView imageCategorieDetails;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 private int montant;
    private int getMontant(){
        return this.montant;
    }
    private void setMontant(int i){
        this.montant=i;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            afficherServices();
            serviceDetails.setVisible(false);
            frontIndexController.setUser(user);
            frontIndexController.initialize(null, null);
            frontIndexController.getEspaceServ().setStyle("-fx-background-color: #f4f4f4");
            loadCategorieFromDatabase();
            loadServiceUserFromDatabase();
            listCategorie.setCellFactory(item -> new ListCell<CategorieService>() {
                protected void updateItem(CategorieService item, boolean bln) {
                    super.updateItem(item, bln);
                    if (item != null) {
                        Text nom = new Text(item.getNom());
                        Text description = new Text(item.getDescription());
                        description.setWrappingWidth(300);
                        nom.setStyle("-fx-font-size: 25 arial;");
                        description.setStyle("-fx-font-size: 15 arial;"
                                + "-fx-pref-width: 158px;");
                        VBox vBox = new VBox(nom, description);
                        vBox.setStyle("-fx-font-color: transparent;-fx-background-color: #F1F2F6;");
                        vBox.setSpacing(10);

                        Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieService/" + item.getImage(), 120, 120, false, false);
                        ImageView img = new ImageView(image);

                        HBox hBox = new HBox(img, vBox);
                        hBox.setStyle("-fx-font-color: transparent;-fx-background-color: #F1F2F6;");
                        hBox.setSpacing(10);
                        setGraphic(hBox);
                        listCategorie.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                            @Override
                            public void handle(javafx.scene.input.MouseEvent event) {

                                CategorieService a = (CategorieService) listCategorie.getItems().get(listCategorie.getSelectionModel().getSelectedIndex());
                                loadServiceFromDatabase(a.getId());
                                listService.setCellFactory(item -> new ListCell<Service>() {
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
                                            vBox.setStyle("-fx-font-color: transparent;-fx-background-color: #F1F2F6;");
                                            vBox.setSpacing(10);

                                            Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/service/" + item.getImage(), 120, 120, false, false);
                                            ImageView img = new ImageView(image);

                                            HBox hBox = new HBox(img, vBox);
                                            hBox.setStyle("-fx-font-color: transparent;-fx-background-color: #F1F2F6;");
                                            hBox.setSpacing(10);
                                            setGraphic(hBox);
                                            

                                        }
                                    }

                                });
                                System.out.println("test");
                            }
                        });

                    }
                }

            });
            checkQuiz();
            initQuiz();
            listCategorie.setStyle("-fx-background-color: transparent;");
            //listCategorie.

        });
        // TODO
    }

    private void initQuiz() {
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
        this.setMontant(0);
        //SMSForm.setVisible(false);
    }

    private void SendMail() throws Exception {
        String mailContentReclamant = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "<!--[if !mso]><!-->\n"
                + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
                + "<!--<![endif]-->\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n"
                + "<title></title>\n"
                + "<style type=\"text/css\">\n"
                + "* {\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + "body {\n"
                + "	Margin: 0;\n"
                + "	padding: 0;\n"
                + "	min-width: 100%;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "	mso-line-height-rule: exactly;\n"
                + "}\n"
                + "table {\n"
                + "	border-spacing: 0;\n"
                + "	color: #333333;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "}\n"
                + "img {\n"
                + "	border: 0;\n"
                + "}\n"
                + ".wrapper {\n"
                + "	width: 100%;\n"
                + "	table-layout: fixed;\n"
                + "	-webkit-text-size-adjust: 100%;\n"
                + "	-ms-text-size-adjust: 100%;\n"
                + "}\n"
                + ".webkit {\n"
                + "	max-width: 600px;\n"
                + "}\n"
                + ".outer {\n"
                + "	Margin: 0 auto;\n"
                + "	width: 100%;\n"
                + "	max-width: 600px;\n"
                + "}\n"
                + ".full-width-image img {\n"
                + "	width: 100%;\n"
                + "	max-width: 600px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".inner {\n"
                + "	padding: 10px;\n"
                + "}\n"
                + "p {\n"
                + "	Margin: 0;\n"
                + "	padding-bottom: 10px;\n"
                + "}\n"
                + ".h1 {\n"
                + "	font-size: 21px;\n"
                + "	font-weight: bold;\n"
                + "	Margin-top: 15px;\n"
                + "	Margin-bottom: 5px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".h2 {\n"
                + "	font-size: 18px;\n"
                + "	font-weight: bold;\n"
                + "	Margin-top: 10px;\n"
                + "	Margin-bottom: 5px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".one-column .contents {\n"
                + "	text-align: left;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".one-column p {\n"
                + "	font-size: 14px;\n"
                + "	Margin-bottom: 10px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".two-column {\n"
                + "	text-align: center;\n"
                + "	font-size: 0;\n"
                + "}\n"
                + ".two-column .column {\n"
                + "	width: 100%;\n"
                + "	max-width: 300px;\n"
                + "	display: inline-block;\n"
                + "	vertical-align: top;\n"
                + "}\n"
                + ".contents {\n"
                + "	width: 100%;\n"
                + "}\n"
                + ".two-column .contents {\n"
                + "	font-size: 14px;\n"
                + "	text-align: left;\n"
                + "}\n"
                + ".two-column img {\n"
                + "	width: 100%;\n"
                + "	max-width: 280px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".two-column .text {\n"
                + "	padding-top: 10px;\n"
                + "}\n"
                + ".three-column {\n"
                + "	text-align: center;\n"
                + "	font-size: 0;\n"
                + "	padding-top: 10px;\n"
                + "	padding-bottom: 10px;\n"
                + "}\n"
                + ".three-column .column {\n"
                + "	width: 100%;\n"
                + "	max-width: 200px;\n"
                + "	display: inline-block;\n"
                + "	vertical-align: top;\n"
                + "}\n"
                + ".three-column .contents {\n"
                + "	font-size: 14px;\n"
                + "	text-align: center;\n"
                + "}\n"
                + ".three-column img {\n"
                + "	width: 100%;\n"
                + "	max-width: 180px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".three-column .text {\n"
                + "	padding-top: 10px;\n"
                + "}\n"
                + ".img-align-vertical img {\n"
                + "	display: inline-block;\n"
                + "	vertical-align: middle;\n"
                + "}\n"
                + "@media only screen and (max-device-width: 480px) {\n"
                + "table[class=hide], img[class=hide], td[class=hide] {\n"
                + "	display: none !important;\n"
                + "}\n"
                + ".contents1 {\n"
                + "	width: 100%;\n"
                + "}\n"
                + ".contents1 {\n"
                + "	width: 100%;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#f3f2f0;\">\n"
                + "<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#f3f2f0;\">\n"
                + "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f3f2f0;\" bgcolor=\"#f3f2f0;\">\n"
                + "    <tr>\n"
                + "      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \n"
                + "          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\n"
                + "            <tr>\n"
                + "              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\n"
                + "                \n"
                + "                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\n"
                + "                  <tr>\n"
                + "                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td align=\"center\"><center>\n"
                + "                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\n"
                + "                                  <tbody>\n"
                + "                                    <tr>\n"
                + "                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\n"
                + "                                        \n"
                + "                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f3f2f0\">\n"
                + "                                          <tr>\n"
                + "                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:left;font-size:0;\" >                                          \n"
                + "                                              </td>\n"
                + "                                          </tr>\n"
                + "                                          <tr>\n"
                + "                                            <td>&nbsp;</td>\n"
                + "                                          </tr>\n"
                + "                                        </table></td>\n"
                + "                                    </tr>\n"
                + "                                  </tbody>\n"
                + "                                </table>\n"
                + "                              </center></td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table></td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                <!-- ======= end header ======= --> \n"
                + "                \n"
                + "                <!-- ======= start hero ======= -->\n"
                + "                \n"
                + "                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n"
                + "                  <tr>\n"
                + "                    <td background=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/42ba8b72-65d6-4dea-b8ab-3ecc12676337.jpg\" bgcolor=\"#2f9780\" width=\"100\" height=\"100\" valign=\"top\" align=\"center\" style=\"padding:50px 50px 50px 50px\">\n"
                + " \n"
                + "                      \n"
                + "                      <div>\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <p style=\"color:#ffffff; font-size:60px; text-align:center; font-family: Verdana, Geneva, sans-serif\">FIX IT</p>\n"
                + "                  \n"
                + "                      </div>\n"
                + "                      </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n"
                + "                  <tr>\n"
                + "                    <td align=\"center\" style=\"padding:50px 50px 50px 50px\"><p style=\"color:#262626; font-size:24px; text-align:center; font-family: Verdana, Geneva, sans-serif\"><strong>Mr l'administrateur</strong></p>\n"
                + "                      <p style=\"color:#262626; font-size:16px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \"> Un service a été proposé par " + this.getUser().getUsername() + " <br />\n"
                + "                      <p> Veuillez Vérifier cette proposition </p>\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                      </p>\n"
                + "                     \n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "                      <p style=\"color:#000000; font-size:12px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \"> <br />\n"
                + "                        <br />\n"
                + "                        </p></td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        </div></td>\n"
                + "    </tr>\n"
                + "  </table>\n"
                + "</center>\n"
                + "</body>\n"
                + "</html>";

        UserService us = new UserService();
        ObservableList<User> list = FXCollections.observableArrayList();
        ObservableList<User> list2 = FXCollections.observableArrayList();
        list = us.getUsers(this.getUser().getId());
        for (User u : list) {
            if (u.getRoles().equals("Administrateur")) {
                System.out.println(u.getEmail());
                //  MailService mailService= new MailService("slim.bensalah@esprit.tn","Services Proposés",mailContentReclamant);
                try {
                    MailService mailService = new MailService(u.getEmail(), "Services Proposés", mailContentReclamant);
                    mailService.sendEmail();
                } catch (MessagingException e) {
                    System.out.println(e);
                }
            }
        }
        // MailService mailService= new MailService(list2,"Reclamation contre vous",mailContentReclamant);

    }

    private void checkQuiz() {

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
        QuizUser qu = new QuizUser();
        QuizUserService qus = new QuizUserService();

        ObservableList<Integer> quizUser = FXCollections.observableArrayList();
        quizUser = qus.afficherQuizUser(this.getUser().getId());

        System.out.println(qus.getTentative(this.getUser().getId(), 1));
        for (int i = 0; i < quizUser.size(); i++) {
            if (quizUser.get(i) == 1) {
                if (qus.getTentative(this.getUser().getId(), 1) == 3) {

                    jouerQuiz1.setDisable(true);
                    labelQ1Pass.setTextFill(Color.web("red"));

                    labelQ1Pass.setText("Vous avez déja passé ce quizz !");
                    labelg100.setVisible(false);
                    sg100.setVisible(false);
                }
                if (qus.getTentative(this.getUser().getId(), 1) == 2) {

                    jouerQuiz1.setDisable(true);
                    labelQ1Pass.setTextFill(Color.web("red"));

                    labelQ1Pass.setText("Tentatives épuisés ! ");
                    labelg100.setVisible(false);
                    sg100.setVisible(false);
                }
                if (qus.getTentative(this.getUser().getId(), 1) == 1) {
                    labelg100.setText("gagner 5");
                    labelQ1Pass.setTextFill(Color.web("red"));

                    labelQ1Pass.setText("Il vous reste une tentative ! ");
                }

            }
            if (quizUser.get(i) == 2) {
                if (qus.getTentative(this.getUser().getId(), 2) == 3) {

                    jouerQuiz2.setDisable(true);
                    labelQ2Pass.setTextFill(Color.web("red"));
                    labelQ2Pass.setText("Vous avez déja passé ce quizz !");
                    labelg500.setVisible(false);
                    sg500.setVisible(false);
                }
                if (qus.getTentative(this.getUser().getId(), 2) == 2) {

                    jouerQuiz2.setDisable(true);
                    labelQ2Pass.setTextFill(Color.web("red"));
                    labelQ2Pass.setText("Tentatives épuisés !");
                    labelg500.setVisible(false);
                    sg500.setVisible(false);
                }
                if (qus.getTentative(this.getUser().getId(), 2) == 1) {
                    labelg500.setText("Gagner 5");
                    labelQ2Pass.setTextFill(Color.web("red"));
                    labelQ2Pass.setText("Il vous reste une tentative !");
                }

            }
            if (quizUser.get(i) == 3) {

                if (qus.getTentative(this.getUser().getId(), 3) == 3) {

                    jouerQuiz3.setDisable(true);
                    labelQ3Pass.setTextFill(Color.web("red"));
                    labelQ3Pass.setText("Vous avez déja passé ce quizz !");
                    labelg1000.setVisible(false);
                    sg1000.setVisible(false);
                }
                if (qus.getTentative(this.getUser().getId(), 3) == 2) {

                    jouerQuiz3.setDisable(true);
                    labelQ3Pass.setTextFill(Color.web("red"));
                    labelQ3Pass.setText("Tentatives épuisés !");
                    labelg1000.setVisible(false);
                    sg1000.setVisible(false);
                }
                if (qus.getTentative(this.getUser().getId(), 3) == 1) {
                    labelg1000.setText("Gagner 5");
                    labelQ3Pass.setTextFill(Color.web("red"));
                    labelQ3Pass.setText("Il vous reste une tentative !");
                }

            }
        }

    }

    private void loadServiceUserFromDatabase() {
        try {
            ServiceUserService su = new ServiceUserService();
            ObservableList<ServiceUser> rs = su.afficherServiceUser(this.getUser().getId());
            System.out.println(rs);
            //paginationOutilFront.setPageFactory(this::createPage);
            mesServices.setItems(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void afficherServices() {

        ServiceUserService r = new ServiceUserService();
        ObservableList<ServiceUser> list = FXCollections.observableArrayList();
        list = r.afficherServiceUser(this.user.getId());
        // mesServices.setItems(list);

        loadServiceUserFromDatabase();
        mesServices.setCellFactory(lv -> new ProfilMesServices());
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

        quiz1Question1.getItems().clear();
        quiz1Question2.getItems().clear();
        quiz1Question3.getItems().clear();
        quiz1Question4.getItems().clear();
        quiz1Question5.getItems().clear();
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
        quiz1Question1.getItems().addAll("Le pingouin", "La cigogne", "Le manchot", "Le pélican");
        labelQ2.setText("Qu’étudie un arachnologue");
        quiz1Question2.getItems().addAll("Les rats", "Les serpents", "Les araignées", "Les aigles");
        labelQ3.setText("Quel est le cri de la brebis ?");
        quiz1Question3.getItems().addAll("Le beuglement", "Le bêlement", "Le jappement", "Le vagissement");
        labelQ4.setText("Quel animal est à l’honneur dans le film « Jappeloup » ?");
        quiz1Question4.getItems().addAll("Un renard", "Un serpent", "Un tigre", "Un cheval");
        labelQ5.setText("Quel animal est associé à Panurge dans une célèbre expression ?");
        quiz1Question5.getItems().addAll("Le renard", "Un mouton", "Le lion", "Le zèbre");

    }

    @FXML
    private void jouerQuiz2(ActionEvent event) {

        quiz1Question1.getItems().clear();
        quiz1Question2.getItems().clear();
        quiz1Question3.getItems().clear();
        quiz1Question4.getItems().clear();
        quiz1Question5.getItems().clear();
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
        quiz1Question1.getItems().addAll("Un aigle", "Un poisson", "Un chien", "Un singe");
        labelQ2.setText("Dans quelle ville se trouve le Musée de l’Ermitage ?");
        quiz1Question2.getItems().addAll("Moscou", "Saint-Pétersbourg", "Kiev", "Stockholm");
        labelQ3.setText("Quel arbre produit la noix de pécan ?");
        quiz1Question3.getItems().addAll("Le macadamia", "Le noisetier", "Le pacanier", "Le pécunier");
        labelQ4.setText("Que fête-t-on le 1er mai ?");
        quiz1Question4.getItems().addAll("Le travail", "Le printemps", "Les mamans", "Le Beaujolais");
        labelQ5.setText("Quelle est la capitale de l’Inde ?");
        quiz1Question5.getItems().addAll("Calcutta", "Mumbai", "New Delhi", "Bangalore");

    }

    @FXML
    private void jouerQuiz3(ActionEvent event) {

        quiz1Question1.getItems().clear();
        quiz1Question2.getItems().clear();
        quiz1Question3.getItems().clear();
        quiz1Question4.getItems().clear();
        quiz1Question5.getItems().clear();
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
        quiz1Question1.getItems().addAll("Georges Pompidou", "François Mitterrand", "Charles de Gaulle", "Jacques Chirac");
        labelQ2.setText("À quel peintre doit-on « L'autoportrait au gilet vert » ?");
        quiz1Question2.getItems().addAll("Pierre Bonnard", "Rembrandt", "Eugène Delacroix", "Jacques-Louis David");
        labelQ3.setText("Qui a peint le célèbre tableau « Café de nuit à Arles »  ?");
        quiz1Question3.getItems().addAll("Paul Cézanne", "Claude Monet", "Pablo Picasso", "Vincent Van Gogh");
        labelQ4.setText(" Dans quelle ville européenne se trouve le musée Guggenheim ?");
        quiz1Question4.getItems().addAll("Berlin", "Bilbao", "Londres", "Paris");
        labelQ5.setText("À quel mouvement artistique appartenaient Rembrandt, Vermeer,  Velázquez ou Rubens ?");
        quiz1Question5.getItems().addAll("Le baroque", "Le rococo", "Le pop art", "Le symbolisme");

    }

    @FXML
    private void confirmerQuiz1(ActionEvent event) {
        int k = 0;
        String e="Vous devez répondre aux questions";
        if (quiz1Question1.getValue() == null) {
            quiz1Question1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion1";
        } else {
            quiz1Question1.setStyle("-fx-border-color:none ;");
        }
        if (quiz1Question2.getValue() == null) {
            quiz1Question2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion2";
        } else {
            quiz1Question2.setStyle("-fx-border-color: none;");
        }
        if (quiz1Question3.getValue() == null) {
            quiz1Question3.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion3";
        } else {
            quiz1Question3.setStyle("-fx-border-color: none");
        }
        if (quiz1Question4.getValue() == null) {
            quiz1Question4.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion4";
        } else {
            quiz1Question4.setStyle("-fx-border-color: nnoe ;");
        }
        if (quiz1Question5.getValue() == null) {
            quiz1Question5.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion5";
        } else {
            quiz1Question5.setStyle("-fx-border-color: none ;");
        }
        if (k == 0) {

            int j = 0;
            QuizUser qu = new QuizUser();
            QuizUserService qus = new QuizUserService();
            if (qus.verifier(this.getUser().getId(), 1)) {

                qu.setIdUser(this.getUser().getId());
                qu.setIdQuiz(1);
                qu.setTentative(0);
                qus.ajouterQuizUser(qu);
            }
            if (!qus.verifier(this.getUser().getId(), 1)) {

                int i = qus.getTentative(this.getUser().getId(), 1);
                qu.setIdUser(this.getUser().getId());
                qu.setIdQuiz(1);
                qu.setTentative(i + 1);
                qus.modifierQuizUser(qu);
            }
            if (quiz1Question1.getValue().equals("Le manchot")
                    && quiz1Question2.getValue().equals("Les araignées")
                    && quiz1Question3.getValue().equals("Le bêlement")
                    && quiz1Question4.getValue().equals("Un cheval")
                    && quiz1Question5.getValue().equals("Un mouton")) {
                j = 1;
                if (qus.getTentative(this.getUser().getId(), 1) == 1) {
                    qu.setIdQuiz(1);
                    qu.setTentative(3);
                    qu.setIdUser(this.getUser().getId());
                    qus.modifierQuizUser(qu);
                    UserService u = new UserService();
                    this.setMontant(10);
                    resultatQuiz.setText("Bravo vous Avez gagné 10 Scoins");
                }
                if (qus.getTentative(this.getUser().getId(), 1) == 2) {
                    qu.setIdQuiz(1);
                    qu.setTentative(3);
                    qu.setIdUser(this.getUser().getId());
                    qus.modifierQuizUser(qu);
                    UserService u = new UserService();
                this.setMontant(5);
                    resultatQuiz.setText("Bravo vous Avez gagné 5 Scoins");
                }

            } else {
                System.out.println("non");
                if (qus.getTentative(this.getUser().getId(), 1) == 1) {
                    qu.setIdQuiz(1);
                    qu.setTentative(1);
                    qu.setIdUser(this.getUser().getId());
                    qus.modifierQuizUser(qu);
                    resultatQuiz.setText("Vous avez échoué.Réessayer et gagner 5 SCoins!");
                    labelg100.setText("Gagner 5");
                }
                if (qus.getTentative(this.getUser().getId(), 1) == 2) {
                    qu.setTentative(2);
                    qu.setIdUser(this.getUser().getId());
                    qus.modifierQuizUser(qu);
                    resultatQuiz.setText("Tentative épuisés !");
                    labelg100.setVisible(false);
                    sg100.setVisible(false);
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
            if (j == 1) {
                SMSForm.setVisible(true);
                labelSMS.setText("Tapez votre numéro ,un code vous sera communiqué !");
                labelSMS.setVisible(true);
                labelQ1Pass.setVisible(false);
                labelQ2Pass.setVisible(false);
                labelQ3Pass.setVisible(false);
                confirmerQuiz1.setVisible(false);
                confirmerQuiz2.setVisible(false);
                confirmerQuiz3.setVisible(false);
                jouerQuiz1.setVisible(false);
                jouerQuiz2.setVisible(false);
                jouerQuiz3.setVisible(false);
                imageQuiz1.setVisible(false);
                imageQuiz2.setVisible(false);
                imageQuiz3.setVisible(false);
                sg100.setVisible(false);
                sg500.setVisible(false);
                sg1000.setVisible(false);
                labelg100.setVisible(false);
                labelg500.setVisible(false);
                labelg1000.setVisible(false);
                envoyerSMS.setVisible(true);
                verifierCode.setVisible(false);
                textSMS.clear();
                checkQuiz();
            }

        }
        else if(k==0){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quiz 1");
            alert.setHeaderText(null);
            alert.setContentText(e);
            alert.showAndWait();
        }
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
           int k = 0;
           String e="Vous dever répondre aux questions";
        if (quiz1Question1.getValue() == null) {
            quiz1Question1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion1";
        } else {
            quiz1Question1.setStyle("-fx-border-color:none ;");
        }
        if (quiz1Question2.getValue() == null) {
            quiz1Question2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion2";
        } else {
            quiz1Question2.setStyle("-fx-border-color: none;");
        }
        if (quiz1Question3.getValue() == null) {
            quiz1Question3.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion3";
        } else {
            quiz1Question3.setStyle("-fx-border-color: none");
        }
        if (quiz1Question4.getValue() == null) {
            quiz1Question4.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion4";
        } else {
            quiz1Question4.setStyle("-fx-border-color: nnoe ;");
        }
        if (quiz1Question5.getValue() == null) {
            quiz1Question5.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion5";
        } else {
            quiz1Question5.setStyle("-fx-border-color: none ;");
        }
        if (k == 0) {
        int j = 0;
        QuizUser qu = new QuizUser();
        QuizUserService qus = new QuizUserService();
        if (qus.verifier(this.getUser().getId(), 2)) {

            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(2);
            qu.setTentative(0);
            qus.ajouterQuizUser(qu);
        }
        if (!qus.verifier(this.getUser().getId(), 2)) {

            int i = qus.getTentative(this.getUser().getId(), 2);
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(2);
            qu.setTentative(i + 1);
            qus.modifierQuizUser(qu);
        }
        if (quiz1Question1.getValue().equals("Un singe")
                && quiz1Question2.getValue().equals("Saint-Pétersbourg")
                && quiz1Question3.getValue().equals("Le pacanier")
                && quiz1Question4.getValue().equals("Le travail")
                && quiz1Question5.getValue().equals("New Delhi")) {
            j = 1;
            if (qus.getTentative(this.getUser().getId(), 2) == 1) {
                qu.setIdQuiz(2);
                qu.setTentative(3);
                qu.setIdUser(this.getUser().getId());
                qus.modifierQuizUser(qu);
                this.setMontant(10);
                /*UserService u = new UserService();
                u.modifierSolde(this.getUser(), 500);*/
                resultatQuiz.setText("Bravo vous Avez gagné 10 Scoins");
            }
            if (qus.getTentative(this.getUser().getId(), 2) == 2) {
                qu.setIdQuiz(2);
                qu.setTentative(3);
                qu.setIdUser(this.getUser().getId());
                qus.modifierQuizUser(qu);
                this.setMontant(5);
                /*UserService u = new UserService();
                u.modifierSolde(this.getUser(), 250);*/
                resultatQuiz.setText("Bravo vous Avez gagné 5 Scoins");
            }

        } else {
            System.out.println("non");
            if (qus.getTentative(this.getUser().getId(), 2) == 1) {
                qu.setIdQuiz(2);
                qu.setTentative(1);
                qu.setIdUser(this.getUser().getId());
                qus.modifierQuizUser(qu);
                resultatQuiz.setText("Vous avez échoué.Réessayer et gagner 5 SCoins!");
                labelg500.setText("Gagner 50");
            }
            if (qus.getTentative(this.getUser().getId(), 2) == 2) {
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
        checkQuiz();
        if (j == 1) {
            SMSForm.setVisible(true);
            labelSMS.setText("Tapez votre numéro ,un code vous sera communiqué !");
            labelSMS.setVisible(true);
            labelQ1Pass.setVisible(false);
            labelQ2Pass.setVisible(false);
            labelQ3Pass.setVisible(false);
            confirmerQuiz1.setVisible(false);
            confirmerQuiz2.setVisible(false);
            confirmerQuiz3.setVisible(false);
            jouerQuiz1.setVisible(false);
            jouerQuiz2.setVisible(false);
            jouerQuiz3.setVisible(false);
            imageQuiz1.setVisible(false);
            imageQuiz2.setVisible(false);
            imageQuiz3.setVisible(false);
            sg100.setVisible(false);
            sg500.setVisible(false);
            sg1000.setVisible(false);
            labelg100.setVisible(false);
            labelg500.setVisible(false);
            labelg1000.setVisible(false);
            envoyerSMS.setVisible(true);
            verifierCode.setVisible(false);
            textSMS.clear();
            
        }
        }
        else{
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quiz 2");
            alert.setHeaderText(null);
            alert.setContentText(e);
            alert.showAndWait();
        }

    }

    @FXML
    private void confirmerQuiz3(ActionEvent event) {
           int k = 0;
           String e="Vous devez répondre aux questions";
        if (quiz1Question1.getValue() == null) {
            quiz1Question1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion1";
        } else {
            quiz1Question1.setStyle("-fx-border-color:none ;");
        }
        if (quiz1Question2.getValue() == null) {
            quiz1Question2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion2";
        } else {
            quiz1Question2.setStyle("-fx-border-color: none;");
        }
        if (quiz1Question3.getValue() == null) {
            quiz1Question3.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion3";
        } else {
            quiz1Question3.setStyle("-fx-border-color: none");
        }
        if (quiz1Question4.getValue() == null) {
            quiz1Question4.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion4";
        } else {
            quiz1Question4.setStyle("-fx-border-color: nnoe ;");
        }
        if (quiz1Question5.getValue() == null) {
            quiz1Question5.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k = 1;
            e+="\nQuestion5";
        } else {
            quiz1Question5.setStyle("-fx-border-color: none ;");
        }
        if (k == 0) {
        int j = 0;
        QuizUser qu = new QuizUser();
        QuizUserService qus = new QuizUserService();
        if (qus.verifier(this.getUser().getId(), 3)) {

            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(3);
            qu.setTentative(0);
            qus.ajouterQuizUser(qu);
        }
        if (!qus.verifier(this.getUser().getId(), 3)) {

            int i = qus.getTentative(this.getUser().getId(), 3);
            qu.setIdUser(this.getUser().getId());
            qu.setIdQuiz(3);
            qu.setTentative(i + 1);
            qus.modifierQuizUser(qu);
        }
        if (quiz1Question1.getValue().equals("François Mitterrand")
                && quiz1Question2.getValue().equals("Eugène Delacroix")
                && quiz1Question3.getValue().equals("Vincent Van Gogh")
                && quiz1Question4.getValue().equals("Bilbao")
                && quiz1Question5.getValue().equals("Le baroque")) {
            j = 1;
            if (qus.getTentative(this.getUser().getId(), 3) == 1) {
                qu.setIdQuiz(3);
                qu.setTentative(3);
                qu.setIdUser(this.getUser().getId());
                qus.modifierQuizUser(qu);
                this.setMontant(10);
                /*UserService u = new UserService();
                u.modifierSolde(this.getUser(), 1000);*/
                resultatQuiz.setText("Bravo vous Avez gagné 10 Scoins");
            }
            if (qus.getTentative(this.getUser().getId(), 3) == 2) {
                qu.setIdQuiz(3);
                qu.setTentative(3);
                qu.setIdUser(this.getUser().getId());
                qus.modifierQuizUser(qu);
                this.setMontant(5);
                /*UserService u = new UserService();
                u.modifierSolde(this.getUser(), 500);*/
                resultatQuiz.setText("Bravo vous Avez gagné 5 Scoins");
            }

        } else {
            System.out.println("non");
            if (qus.getTentative(this.getUser().getId(), 3) == 1) {
                qu.setIdQuiz(3);
                qu.setTentative(1);
                qu.setIdUser(this.getUser().getId());
                qus.modifierQuizUser(qu);
                resultatQuiz.setText("Vous avez échoué.Réessayer et gagner 5 SCoins!");
                labelg500.setText("Gagner 5");
            }
            if (qus.getTentative(this.getUser().getId(), 3) == 2) {
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
        if (j == 1) {
            SMSForm.setVisible(true);
            labelSMS.setText("Tapez votre numéro ,un code vous sera communiqué !");
            labelSMS.setVisible(true);
            labelQ1Pass.setVisible(false);
            labelQ2Pass.setVisible(false);
            labelQ3Pass.setVisible(false);
            confirmerQuiz1.setVisible(false);
            confirmerQuiz2.setVisible(false);
            confirmerQuiz3.setVisible(false);
            jouerQuiz1.setVisible(false);
            jouerQuiz2.setVisible(false);
            jouerQuiz3.setVisible(false);
            imageQuiz1.setVisible(false);
            imageQuiz2.setVisible(false);
            imageQuiz3.setVisible(false);
            sg100.setVisible(false);
            sg500.setVisible(false);
            sg1000.setVisible(false);
            labelg100.setVisible(false);
            labelg500.setVisible(false);
            labelg1000.setVisible(false);
            envoyerSMS.setVisible(true);
            verifierCode.setVisible(false);
            textSMS.clear();
            checkQuiz();
        }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quiz 3");
            alert.setHeaderText(null);
            alert.setContentText(e);
            alert.showAndWait();
        }
    }
   
    @FXML
    private void ajouterUnService(ActionEvent event) {
        mesServices.setVisible(false);
        ajouterUnService.setVisible(false);
        proposerUnService.setVisible(false);
        addService.setVisible(true);

        retourAjouterService.setVisible(true);
        supprimerSU.setVisible(false);
        this.initialize(null, null);

        ObservableList<CategorieService> list = FXCollections.observableArrayList();
        CategorieServiceService r = new CategorieServiceService();
        list = r.getALLCategorie();
        categorie.setItems(list);
    }

    @FXML
    private void proposerUnService(ActionEvent event) {
        mesServices.setVisible(false);
        ajouterUnService.setVisible(false);
        proposerUnService.setVisible(false);
        addService.setVisible(false);
        proposerS.setVisible(true);
        retourPropService.setVisible(true);
        supprimerSU.setVisible(false);

        ObservableList<CategorieService> list = FXCollections.observableArrayList();
        CategorieServiceService r = new CategorieServiceService();
        list = r.getALLCategorie();
        categorieProposition.setItems(list);
    }

    @FXML
    private void cat(ActionEvent event) {
        ObservableList<Service> listS = FXCollections.observableArrayList();
        ServiceService s = new ServiceService();
        listS = s.getAllServiceC(categorie.getValue().getId());
        serviceC.setItems(listS);
    }

    @FXML
    private void ajouterS(ActionEvent event) {
        int i = 0;
        if (prix.getText().equals("")) {
            prix.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            i = 1;
        } else {
            prix.setStyle("-fx-border-color: none;");
        }
        if (description.getText().equals("")) {
            description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            i = 1;
        } else {
            description.setStyle("-fx-border-color: none ;  ");
        }
        if (categorie.getValue() == null) {
            categorie.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            i = 1;
        } else {
            categorie.setStyle("-fx-border-color: none ;");
        }
        if (serviceC.getValue() == null) {
            serviceC.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            i = 1;
        } else {
            serviceC.setStyle("-fx-border-color: none ;");
        }
        if (i == 0) {
            ServiceUser s = new ServiceUser();
            ServiceUserService su = new ServiceUserService();
            s.setDescription(description.getText());
            s.setPrix(Integer.parseInt(prix.getText()));
            s.setIdService(serviceC.getValue().getId());
            s.setIdUser(this.user.getId());
            su.ajouterServiceUser(s);

            addService.setVisible(false);
            labelMesServices.setVisible(true);
            ajouterUnService.setVisible(true);
            proposerUnService.setVisible(true);
            mesServices.setVisible(true);
            supprimerSU.setVisible(true);
            this.initialize(null, null);
        }
    }

    @FXML
    private void retourAjouterService(ActionEvent event) {
        addService.setVisible(false);
        labelMesServices.setVisible(true);
        ajouterUnService.setVisible(true);
        proposerUnService.setVisible(true);
        mesServices.setVisible(true);
        supprimerSU.setVisible(true);
    }

    @FXML
    private void confirmerProposition(ActionEvent event) throws Exception {
        String e = "Vous devez remplir le(s) champ(s) ";
        int i = 0;
        if (nomProposition.getText().equals("")) {
            nomProposition.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            e += "nom ";
            i = 1;
        } else {
            nomProposition.setStyle("-fx-border-color: none;");
        }
        if (descriptionProposition.getText().equals("")) {
            descriptionProposition.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            e += "  description ";
            i = 1;
        } else {
            descriptionProposition.setStyle("-fx-border-color: none ;  ");
        }
        if (categorieProposition.getValue() == null) {
            categorieProposition.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            e += "  categorie ";
            i = 1;
        } else {
            categorieProposition.setStyle("-fx-border-color: none ;");
        }
        if (i == 0) {

            ServicesProposes s = new ServicesProposes();
            ServicesProposesService sp = new ServicesProposesService();
            s.setNom(nomProposition.getText());
            s.setDescription(descriptionProposition.getText());
            s.setCategorieService(categorieProposition.getValue().toString());
            sp.ajouterService(s);
            proposerS.setVisible(false);
            labelMesServices.setVisible(true);
            ajouterUnService.setVisible(true);
            proposerUnService.setVisible(true);
            mesServices.setVisible(true);
            supprimerSU.setVisible(true);
            SendMail();
             NotificationService ns = new NotificationService();
        Notification n = new Notification();
        n.setTitle("Proposition de service");
        n.setDescription(user+" a proposé le service "+s.getNom());
        Byte b=0;
        n.setSeen(b);
        n.setIcon("service");
        n.setTelephone(user.getPhone());
        ns.ajouterNotification(n);

            this.initialize(null, null);
        }
    }

    @FXML
    private void retourPropService(ActionEvent event) {

        proposerS.setVisible(false);
        labelMesServices.setVisible(true);
        ajouterUnService.setVisible(true);
        proposerUnService.setVisible(true);
        mesServices.setVisible(true);
        supprimerSU.setVisible(true);
    }

    @FXML
    private void supprimerSU(ActionEvent event) {
        ServiceUserService su = new ServiceUserService();
        ServiceUser s = mesServices.getSelectionModel().getSelectedItem();
        ServiceService a = new ServiceService();
        Service b = new Service();
        su.supprimerServiceUser(s.getIdService(), s.getIdUser());
        this.initialize(null, null);
    }

    @FXML
    private void envoyerSMS(ActionEvent event) {
        String num = "+216" + textSMS.getText();
        String sender = "FIXIT";
        String code = "Votre code est \n FIXIT-" + this.getUser().getId() + "-2019";
        SMSService sms = new SMSService();
        if(textSMS.getText().equals("")){
            textSMS.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else {
       // sms.sendSms(code, sender, num);
        //textSMS.clear();
       textSMS.setStyle("-fx-border-color: none ;");
        envoyerSMS.setVisible(false);
        verifierCode.setVisible(true);
        textSMS.clear();
        labelSMS.setText("Ecrivez le code que vous avez reçu ");
        }

    }

    @FXML
    private void verifierCode(ActionEvent event) {
        String code = "FIXIT-" + this.getUser().getId() + "-2019";
        int k=0;
        if(textSMS.getText().equals("")){
            textSMS.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            k=1;
        }
        if(k==0){
            
        
        if (textSMS.getText().equals(code)) {

            UserService u = new UserService();
            u.modifierSolde(this.getUser(), 10);
            System.out.println("ci bon");
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
            resultatQuiz.setVisible(true);
            SMSForm.setVisible(false);
            if(this.getMontant()==10)
            {
                
            resultatQuiz.setText("Bravo vous Avez gagné 10 SCoins");
            }
            else if(this.getMontant()==5){
                resultatQuiz.setText("Bravo vous Avez gagné 5 SCoins");
            }

        }
       else {
               
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vérification");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez vérifier vos données");
            alert.showAndWait();
               }
        }
    }

    @FXML
    private void detailsService(MouseEvent event) {
        if (event.getClickCount() == 2) {
            serviceDetails.setVisible(true);
            listCategorie.setVisible(false);
            listService.setVisible(false);
            Service s = new Service();
            s = listService.getSelectionModel().getSelectedItem();
            nomServiceDetails.setText(s.getNom());
            categorieServiceDetails.setText(s.getCategorie().getNom());
            descriptionServiceDetails.setText(s.getDescription());
            descriptionServiceDetails.setMaxWidth(350);
            descriptionServiceDetails.setWrapText(true);
            nomServiceDetails.setMaxWidth(357);
            nomServiceDetails.setWrapText(true);
            nbrServiceDetails.setText(Integer.toString(s.getNbrProviders()));
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/service/" + s.getImage(), 329, 170, false, false);
            imageServiceDetails.setImage(image1);
            
            Image image2 = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieService/" + s.getCategorie().getImage(), 92, 84, false, false);
            imageCategorieDetails.setImage(image2);

        }
    }

    @FXML
    private void retourDetails(ActionEvent event) {
        serviceDetails.setVisible(false);
        listCategorie.setVisible(true);
        listService.setVisible(true);
    }

    @FXML
    private void checkNumber(KeyEvent event) {

        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }

    }

}
