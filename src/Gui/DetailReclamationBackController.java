/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Services.MailService;
import Services.ReclamationService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DetailReclamationBackController implements Initializable {

    @FXML
    private Label dateReclamation;
    @FXML
    private Label userReclame;
    @FXML
    private Label service;
    @FXML
    private Label objet;
    @FXML
    private Label description;
    @FXML
    private Button archive;
    @FXML
    private Button traite;
    @FXML
    private Button retour;
    
    private Reclamation reclamation;
    @FXML
    private Label traiteLb;
    @FXML
    private Label userReclamant;
    @FXML
    private Label recTraitArch;
    @FXML
    private Label archiveLb;

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Platform.runLater(() -> {
                dateReclamation.setText(this.reclamation.getDateReclamation().toString());
                userReclame.setText(this.reclamation.getUserReclame().getUsername());
                service.setText(this.reclamation.getIdServiceRealise().getNom());
                description.setText(this.reclamation.getDescription());
                objet.setText(this.reclamation.getObjet());
                userReclamant.setText(this.reclamation.getUserReclamant().getUsername());
                ReclamationService recServ = new ReclamationService();
                if(this.reclamation.getSeen()==0)
                 {
                     recServ.vuReclamation(this.reclamation);
                     String mailContentReclamant="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html>\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<!--[if !mso]><!-->\n" +
"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
"<!--<![endif]-->\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"<title></title>\n" +
"<style type=\"text/css\">\n" +
"* {\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
"body {\n" +
"	Margin: 0;\n" +
"	padding: 0;\n" +
"	min-width: 100%;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"	mso-line-height-rule: exactly;\n" +
"}\n" +
"table {\n" +
"	border-spacing: 0;\n" +
"	color: #333333;\n" +
"	font-family: Arial, sans-serif;\n" +
"}\n" +
"img {\n" +
"	border: 0;\n" +
"}\n" +
".wrapper {\n" +
"	width: 100%;\n" +
"	table-layout: fixed;\n" +
"	-webkit-text-size-adjust: 100%;\n" +
"	-ms-text-size-adjust: 100%;\n" +
"}\n" +
".webkit {\n" +
"	max-width: 600px;\n" +
"}\n" +
".outer {\n" +
"	Margin: 0 auto;\n" +
"	width: 100%;\n" +
"	max-width: 600px;\n" +
"}\n" +
".full-width-image img {\n" +
"	width: 100%;\n" +
"	max-width: 600px;\n" +
"	height: auto;\n" +
"}\n" +
".inner {\n" +
"	padding: 10px;\n" +
"}\n" +
"p {\n" +
"	Margin: 0;\n" +
"	padding-bottom: 10px;\n" +
"}\n" +
".h1 {\n" +
"	font-size: 21px;\n" +
"	font-weight: bold;\n" +
"	Margin-top: 15px;\n" +
"	Margin-bottom: 5px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".h2 {\n" +
"	font-size: 18px;\n" +
"	font-weight: bold;\n" +
"	Margin-top: 10px;\n" +
"	Margin-bottom: 5px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".one-column .contents {\n" +
"	text-align: left;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".one-column p {\n" +
"	font-size: 14px;\n" +
"	Margin-bottom: 10px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".two-column {\n" +
"	text-align: center;\n" +
"	font-size: 0;\n" +
"}\n" +
".two-column .column {\n" +
"	width: 100%;\n" +
"	max-width: 300px;\n" +
"	display: inline-block;\n" +
"	vertical-align: top;\n" +
"}\n" +
".contents {\n" +
"	width: 100%;\n" +
"}\n" +
".two-column .contents {\n" +
"	font-size: 14px;\n" +
"	text-align: left;\n" +
"}\n" +
".two-column img {\n" +
"	width: 100%;\n" +
"	max-width: 280px;\n" +
"	height: auto;\n" +
"}\n" +
".two-column .text {\n" +
"	padding-top: 10px;\n" +
"}\n" +
".three-column {\n" +
"	text-align: center;\n" +
"	font-size: 0;\n" +
"	padding-top: 10px;\n" +
"	padding-bottom: 10px;\n" +
"}\n" +
".three-column .column {\n" +
"	width: 100%;\n" +
"	max-width: 200px;\n" +
"	display: inline-block;\n" +
"	vertical-align: top;\n" +
"}\n" +
".three-column .contents {\n" +
"	font-size: 14px;\n" +
"	text-align: center;\n" +
"}\n" +
".three-column img {\n" +
"	width: 100%;\n" +
"	max-width: 180px;\n" +
"	height: auto;\n" +
"}\n" +
".three-column .text {\n" +
"	padding-top: 10px;\n" +
"}\n" +
".img-align-vertical img {\n" +
"	display: inline-block;\n" +
"	vertical-align: middle;\n" +
"}\n" +
"@media only screen and (max-device-width: 480px) {\n" +
"table[class=hide], img[class=hide], td[class=hide] {\n" +
"	display: none !important;\n" +
"}\n" +
".contents1 {\n" +
"	width: 100%;\n" +
"}\n" +
".contents1 {\n" +
"	width: 100%;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#f3f2f0;\">\n" +
"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#f3f2f0;\">\n" +
"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f3f2f0;\" bgcolor=\"#f3f2f0;\">\n" +
"    <tr>\n" +
"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \n" +
"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\n" +
"            <tr>\n" +
"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\n" +
"                \n" +
"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\n" +
"                  <tr>\n" +
"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"                        <tbody>\n" +
"                          <tr>\n" +
"                            <td align=\"center\"><center>\n" +
"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\n" +
"                                  <tbody>\n" +
"                                    <tr>\n" +
"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\n" +
"                                        \n" +
"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f3f2f0\">\n" +
"                                          <tr>\n" +
"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:left;font-size:0;\" >                                          \n" +
"                                              </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                            <td>&nbsp;</td>\n" +
"                                          </tr>\n" +
"                                        </table></td>\n" +
"                                    </tr>\n" +
"                                  </tbody>\n" +
"                                </table>\n" +
"                              </center></td>\n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table></td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                <!-- ======= end header ======= --> \n" +
"                \n" +
"                <!-- ======= start hero ======= -->\n" +
"                \n" +
"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n" +
"                  <tr>\n" +
"                    <td background=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/42ba8b72-65d6-4dea-b8ab-3ecc12676337.jpg\" bgcolor=\"#2f9780\" width=\"100\" height=\"100\" valign=\"top\" align=\"center\" style=\"padding:50px 50px 50px 50px\">\n" +
" \n" +
"                      \n" +
"                      <div>\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <p style=\"color:#ffffff; font-size:60px; text-align:center; font-family: Verdana, Geneva, sans-serif\">FIX IT</p>\n" +
"                  \n" +
"                      </div>\n" +
"                      </td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n" +
"                  <tr>\n" +
"                    <td align=\"center\" style=\"padding:50px 50px 50px 50px\"><p style=\"color:#262626; font-size:24px; text-align:center; font-family: Verdana, Geneva, sans-serif\"><strong>Mr "+this.reclamation.getUserReclame().getUsername()+"</strong></p>\n" +
"                      <p style=\"color:#262626; font-size:16px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \">une reclamation qui a comme objet "+this.reclamation.getObjet()+
                            " a été bien établie contre vous <br />\n" +
"                      <p> Veuillez expliquer votre point de vue avant de traiter la réclamation </p>\n" +
"                        <br />\n" +
"                        <br />\n" +
"                      </p>\n" +
"                     \n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table>\n" +
"                      <p style=\"color:#000000; font-size:12px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \"> <br />\n" +
"                        <br />\n" +
"                        Lorem Ipsum loren ipsum</p></td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                </td>\n" +
"            </tr>\n" +
"          </table>\n" +
"        </div></td>\n" +
"    </tr>\n" +
"  </table>\n" +
"</center>\n" +
"</body>\n" +
"</html>";
        MailService mailService= new MailService(this.reclamation.getUserReclame().getEmail(),"Reclamation contre vous",mailContentReclamant);
        try
        {
          mailService.sendEmail();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
                 }
                
                
                if(this.reclamation.getArchive()==0 && this.reclamation.getTraite()==0)
                {
                    traite.setVisible(true);
                    archive.setVisible(true);
                    traiteLb.setVisible(false);
                    archiveLb.setVisible(false);
                    recTraitArch.setVisible(false);
                    
                }
                if(this.reclamation.getTraite()==1)
                {
                    traite.setVisible(false);
                    traiteLb.setVisible(true);
                    archiveLb.setVisible(false);
                    recTraitArch.setVisible(false);
                }
                if(this.reclamation.getArchive()==1)
                {
                    archive.setVisible(false);
                    traiteLb.setVisible(false);
                    archiveLb.setVisible(true);
                    recTraitArch.setVisible(false);
                }
                if(this.reclamation.getArchive()==1 && this.reclamation.getTraite()==1)
                {
                    traite.setVisible(false);
                    archive.setVisible(false);
                    traiteLb.setVisible(false);
                    archiveLb.setVisible(false);
                    recTraitArch.setVisible(true);
                }
                
                
	    });
    }    

    @FXML
    private void archiveAction(ActionEvent event) {
        ReclamationService recServ = new ReclamationService();
        recServ.archiverReclamation(this.reclamation);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation Archivé avec succée");
        alert.showAndWait();
    }

    @FXML
    private void traiteAction(ActionEvent event) {
        ReclamationService recServ = new ReclamationService();
        recServ.traiterReclamation(this.reclamation);
        String mailContentReclame="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html>\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<!--[if !mso]><!-->\n" +
"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
"<!--<![endif]-->\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"<title></title>\n" +
"<style type=\"text/css\">\n" +
"* {\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
"body {\n" +
"	Margin: 0;\n" +
"	padding: 0;\n" +
"	min-width: 100%;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"	mso-line-height-rule: exactly;\n" +
"}\n" +
"table {\n" +
"	border-spacing: 0;\n" +
"	color: #333333;\n" +
"	font-family: Arial, sans-serif;\n" +
"}\n" +
"img {\n" +
"	border: 0;\n" +
"}\n" +
".wrapper {\n" +
"	width: 100%;\n" +
"	table-layout: fixed;\n" +
"	-webkit-text-size-adjust: 100%;\n" +
"	-ms-text-size-adjust: 100%;\n" +
"}\n" +
".webkit {\n" +
"	max-width: 600px;\n" +
"}\n" +
".outer {\n" +
"	Margin: 0 auto;\n" +
"	width: 100%;\n" +
"	max-width: 600px;\n" +
"}\n" +
".full-width-image img {\n" +
"	width: 100%;\n" +
"	max-width: 600px;\n" +
"	height: auto;\n" +
"}\n" +
".inner {\n" +
"	padding: 10px;\n" +
"}\n" +
"p {\n" +
"	Margin: 0;\n" +
"	padding-bottom: 10px;\n" +
"}\n" +
".h1 {\n" +
"	font-size: 21px;\n" +
"	font-weight: bold;\n" +
"	Margin-top: 15px;\n" +
"	Margin-bottom: 5px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".h2 {\n" +
"	font-size: 18px;\n" +
"	font-weight: bold;\n" +
"	Margin-top: 10px;\n" +
"	Margin-bottom: 5px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".one-column .contents {\n" +
"	text-align: left;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".one-column p {\n" +
"	font-size: 14px;\n" +
"	Margin-bottom: 10px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".two-column {\n" +
"	text-align: center;\n" +
"	font-size: 0;\n" +
"}\n" +
".two-column .column {\n" +
"	width: 100%;\n" +
"	max-width: 300px;\n" +
"	display: inline-block;\n" +
"	vertical-align: top;\n" +
"}\n" +
".contents {\n" +
"	width: 100%;\n" +
"}\n" +
".two-column .contents {\n" +
"	font-size: 14px;\n" +
"	text-align: left;\n" +
"}\n" +
".two-column img {\n" +
"	width: 100%;\n" +
"	max-width: 280px;\n" +
"	height: auto;\n" +
"}\n" +
".two-column .text {\n" +
"	padding-top: 10px;\n" +
"}\n" +
".three-column {\n" +
"	text-align: center;\n" +
"	font-size: 0;\n" +
"	padding-top: 10px;\n" +
"	padding-bottom: 10px;\n" +
"}\n" +
".three-column .column {\n" +
"	width: 100%;\n" +
"	max-width: 200px;\n" +
"	display: inline-block;\n" +
"	vertical-align: top;\n" +
"}\n" +
".three-column .contents {\n" +
"	font-size: 14px;\n" +
"	text-align: center;\n" +
"}\n" +
".three-column img {\n" +
"	width: 100%;\n" +
"	max-width: 180px;\n" +
"	height: auto;\n" +
"}\n" +
".three-column .text {\n" +
"	padding-top: 10px;\n" +
"}\n" +
".img-align-vertical img {\n" +
"	display: inline-block;\n" +
"	vertical-align: middle;\n" +
"}\n" +
"@media only screen and (max-device-width: 480px) {\n" +
"table[class=hide], img[class=hide], td[class=hide] {\n" +
"	display: none !important;\n" +
"}\n" +
".contents1 {\n" +
"	width: 100%;\n" +
"}\n" +
".contents1 {\n" +
"	width: 100%;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#f3f2f0;\">\n" +
"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#f3f2f0;\">\n" +
"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f3f2f0;\" bgcolor=\"#f3f2f0;\">\n" +
"    <tr>\n" +
"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \n" +
"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\n" +
"            <tr>\n" +
"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\n" +
"                \n" +
"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\n" +
"                  <tr>\n" +
"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"                        <tbody>\n" +
"                          <tr>\n" +
"                            <td align=\"center\"><center>\n" +
"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\n" +
"                                  <tbody>\n" +
"                                    <tr>\n" +
"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\n" +
"                                        \n" +
"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f3f2f0\">\n" +
"                                          <tr>\n" +
"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:left;font-size:0;\" >                                          \n" +
"                                              </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                            <td>&nbsp;</td>\n" +
"                                          </tr>\n" +
"                                        </table></td>\n" +
"                                    </tr>\n" +
"                                  </tbody>\n" +
"                                </table>\n" +
"                              </center></td>\n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table></td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                <!-- ======= end header ======= --> \n" +
"                \n" +
"                <!-- ======= start hero ======= -->\n" +
"                \n" +
"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n" +
"                  <tr>\n" +
"                    <td background=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/42ba8b72-65d6-4dea-b8ab-3ecc12676337.jpg\" bgcolor=\"#2f9780\" width=\"100\" height=\"100\" valign=\"top\" align=\"center\" style=\"padding:50px 50px 50px 50px\">\n" +
" \n" +
"                      \n" +
"                      <div>\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <p style=\"color:#ffffff; font-size:60px; text-align:center; font-family: Verdana, Geneva, sans-serif\">FIX IT</p>\n" +
"                  \n" +
"                      </div>\n" +
"                      </td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n" +
"                  <tr>\n" +
"                    <td align=\"center\" style=\"padding:50px 50px 50px 50px\"><p style=\"color:#262626; font-size:24px; text-align:center; font-family: Verdana, Geneva, sans-serif\"><strong>Mr "+this.reclamation.getUserReclame().getUsername()+"</strong></p>\n" +
"                      <p style=\"color:#262626; font-size:16px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \">votre reclamation qui a comme objet "+this.reclamation.getObjet()+
                            "Une réclamation a été faite contre vous de la part de "+this.reclamation.getUserReclamant().getUsername()+
"                      <p> <strong>PS : 3 Reclamation et votre compte sera banné </strong></p>\n" +
"                        <br />\n" +
"                        <br />\n" +
"                      </p>\n" +
"                     \n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table>\n" +
"                      <p style=\"color:#000000; font-size:12px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \"> <br />\n" +
"                        <br />\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                </td>\n" +
"            </tr>\n" +
"          </table>\n" +
"        </div></td>\n" +
"    </tr>\n" +
"  </table>\n" +
"</center>\n" +
"</body>\n" +
"</html>";
        String mailContentReclamant="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html>\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"<!--[if !mso]><!-->\n" +
"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
"<!--<![endif]-->\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"<title></title>\n" +
"<style type=\"text/css\">\n" +
"* {\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
"body {\n" +
"	Margin: 0;\n" +
"	padding: 0;\n" +
"	min-width: 100%;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"	mso-line-height-rule: exactly;\n" +
"}\n" +
"table {\n" +
"	border-spacing: 0;\n" +
"	color: #333333;\n" +
"	font-family: Arial, sans-serif;\n" +
"}\n" +
"img {\n" +
"	border: 0;\n" +
"}\n" +
".wrapper {\n" +
"	width: 100%;\n" +
"	table-layout: fixed;\n" +
"	-webkit-text-size-adjust: 100%;\n" +
"	-ms-text-size-adjust: 100%;\n" +
"}\n" +
".webkit {\n" +
"	max-width: 600px;\n" +
"}\n" +
".outer {\n" +
"	Margin: 0 auto;\n" +
"	width: 100%;\n" +
"	max-width: 600px;\n" +
"}\n" +
".full-width-image img {\n" +
"	width: 100%;\n" +
"	max-width: 600px;\n" +
"	height: auto;\n" +
"}\n" +
".inner {\n" +
"	padding: 10px;\n" +
"}\n" +
"p {\n" +
"	Margin: 0;\n" +
"	padding-bottom: 10px;\n" +
"}\n" +
".h1 {\n" +
"	font-size: 21px;\n" +
"	font-weight: bold;\n" +
"	Margin-top: 15px;\n" +
"	Margin-bottom: 5px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".h2 {\n" +
"	font-size: 18px;\n" +
"	font-weight: bold;\n" +
"	Margin-top: 10px;\n" +
"	Margin-bottom: 5px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".one-column .contents {\n" +
"	text-align: left;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".one-column p {\n" +
"	font-size: 14px;\n" +
"	Margin-bottom: 10px;\n" +
"	font-family: Arial, sans-serif;\n" +
"	-webkit-font-smoothing: antialiased;\n" +
"}\n" +
".two-column {\n" +
"	text-align: center;\n" +
"	font-size: 0;\n" +
"}\n" +
".two-column .column {\n" +
"	width: 100%;\n" +
"	max-width: 300px;\n" +
"	display: inline-block;\n" +
"	vertical-align: top;\n" +
"}\n" +
".contents {\n" +
"	width: 100%;\n" +
"}\n" +
".two-column .contents {\n" +
"	font-size: 14px;\n" +
"	text-align: left;\n" +
"}\n" +
".two-column img {\n" +
"	width: 100%;\n" +
"	max-width: 280px;\n" +
"	height: auto;\n" +
"}\n" +
".two-column .text {\n" +
"	padding-top: 10px;\n" +
"}\n" +
".three-column {\n" +
"	text-align: center;\n" +
"	font-size: 0;\n" +
"	padding-top: 10px;\n" +
"	padding-bottom: 10px;\n" +
"}\n" +
".three-column .column {\n" +
"	width: 100%;\n" +
"	max-width: 200px;\n" +
"	display: inline-block;\n" +
"	vertical-align: top;\n" +
"}\n" +
".three-column .contents {\n" +
"	font-size: 14px;\n" +
"	text-align: center;\n" +
"}\n" +
".three-column img {\n" +
"	width: 100%;\n" +
"	max-width: 180px;\n" +
"	height: auto;\n" +
"}\n" +
".three-column .text {\n" +
"	padding-top: 10px;\n" +
"}\n" +
".img-align-vertical img {\n" +
"	display: inline-block;\n" +
"	vertical-align: middle;\n" +
"}\n" +
"@media only screen and (max-device-width: 480px) {\n" +
"table[class=hide], img[class=hide], td[class=hide] {\n" +
"	display: none !important;\n" +
"}\n" +
".contents1 {\n" +
"	width: 100%;\n" +
"}\n" +
".contents1 {\n" +
"	width: 100%;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"\n" +
"<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#f3f2f0;\">\n" +
"<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#f3f2f0;\">\n" +
"  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f3f2f0;\" bgcolor=\"#f3f2f0;\">\n" +
"    <tr>\n" +
"      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \n" +
"          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\n" +
"            <tr>\n" +
"              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\n" +
"                \n" +
"                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\n" +
"                  <tr>\n" +
"                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"                        <tbody>\n" +
"                          <tr>\n" +
"                            <td align=\"center\"><center>\n" +
"                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\n" +
"                                  <tbody>\n" +
"                                    <tr>\n" +
"                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\n" +
"                                        \n" +
"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f3f2f0\">\n" +
"                                          <tr>\n" +
"                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:left;font-size:0;\" >                                          \n" +
"                                              </td>\n" +
"                                          </tr>\n" +
"                                          <tr>\n" +
"                                            <td>&nbsp;</td>\n" +
"                                          </tr>\n" +
"                                        </table></td>\n" +
"                                    </tr>\n" +
"                                  </tbody>\n" +
"                                </table>\n" +
"                              </center></td>\n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table></td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                <!-- ======= end header ======= --> \n" +
"                \n" +
"                <!-- ======= start hero ======= -->\n" +
"                \n" +
"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n" +
"                  <tr>\n" +
"                    <td background=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/42ba8b72-65d6-4dea-b8ab-3ecc12676337.jpg\" bgcolor=\"#2f9780\" width=\"100\" height=\"100\" valign=\"top\" align=\"center\" style=\"padding:50px 50px 50px 50px\">\n" +
" \n" +
"                      \n" +
"                      <div>\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <br />\n" +
"                        <p style=\"color:#ffffff; font-size:60px; text-align:center; font-family: Verdana, Geneva, sans-serif\">FIX IT</p>\n" +
"                  \n" +
"                      </div>\n" +
"                      </td>\n" +
"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n" +
"                  <tr>\n" +
"                    <td align=\"center\" style=\"padding:50px 50px 50px 50px\"><p style=\"color:#262626; font-size:24px; text-align:center; font-family: Verdana, Geneva, sans-serif\"><strong>Mr "+this.reclamation.getUserReclamant().getUsername()+"</strong></p>\n" +
"                      <p style=\"color:#262626; font-size:16px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \">votre reclamation qui a comme objet "+this.reclamation.getObjet()+
                            "contre l'utilisateur : "+this.reclamation.getUserReclame().getUsername()+" a été bien traité de la part de nos adminiistrateurs<br />\n" +
"                      <p> votre réclamation a été bien traité par les administrateur</p>\n" +
"                        <br />\n" +
"                        <br />\n" +
"                      </p>\n" +
"                     \n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table>\n" +
"                      <p style=\"color:#000000; font-size:12px; text-align:center; font-family: Verdana, Geneva, sans-serif; line-height:22px \"> <br />\n" +
"                        <br />\n" +

"                  </tr>\n" +
"                </table>\n" +
"                \n" +
"                </td>\n" +
"            </tr>\n" +
"          </table>\n" +
"        </div></td>\n" +
"    </tr>\n" +
"  </table>\n" +
"</center>\n" +
"</body>\n" +
"</html>";
        MailService mailService= new MailService(this.reclamation.getUserReclamant().getEmail(),"Reclamation traité",mailContentReclamant);
        MailService mailService1= new MailService(this.reclamation.getUserReclame().getEmail(),"Reclamation traité",mailContentReclame);
        try
        {
          mailService.sendEmail();
          mailService1.sendEmail();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        if(recServ.countReclamationUser(this.reclamation.getUserReclame().getId())>=3)
        {
            UserService userServ = new UserService();
            userServ.bloquer(this.reclamation.getUserReclame().getId());
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation traité avec succée");
        alert.showAndWait();
    }

    @FXML
    private void retourAction(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/reclamationBack.fxml"));
            Parent Rec = fxmlLoader.load();
            Scene scene = new Scene(Rec);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
