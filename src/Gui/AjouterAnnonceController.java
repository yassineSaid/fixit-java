package Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Annonce;
import Services.CategorieServiceService;
import Services.AnnonceService;
import Services.ServiceService;
import Entities.CategorieService;
import Entities.Service;
import Entities.User;
import java.util.Vector;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;

public class AjouterAnnonceController implements Initializable {

private ObservableList data;
private ObservableList<String> t;
private ObservableList<Service> datas;
    String msg = "";
    @FXML
    private FrontIndexController frontIndexController;  
    @FXML
    private TextField description;
    /*@FXML
    private TextField type;*/
    @FXML
    private TextField montant;
    @FXML
    private DatePicker date;
    @FXML
    private TextField adresse;
    @FXML
    private TextField tel;
    @FXML
    private ComboBox<CategorieService> categorie;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<Service> service;
    Image image=new Image(getClass().getResourceAsStream("/Resources/tik.png"));

    
    
    private User user;
   
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

    
    @FXML
    private void AjouterAction(ActionEvent event) {
        boolean erreur = false;
    	AnnonceService sa = AnnonceService.getInstance();
        Vector<String> erreurs = new Vector<String>();
        if (!sa.validTel(tel.getText())) {
            erreur = true;
            erreurs.add("Le numéro de téléphone est invalide");
        }
        if (erreur) {
            msg = "";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Postuler");
            alert.setHeaderText(null);
            erreurs.forEach(m -> msg += m + "\n");
            alert.setContentText(msg);
            alert.showAndWait();
            erreurs.clear();
        } else {
    	Annonce l = sa.last();
    	int num = Integer.parseInt(tel.getText());
    	int nbrd = l.getNbr_d() ;
    	int nbro = l.getNbr_o() ;
    	Date da = Date.valueOf(date.getValue());
    	long prix = Long.parseLong(montant.getText());
        if ("demande".equals(type.getValue())) {
        	nbrd=nbrd+1;        	
        }
        else if ("offre".equals(type.getValue())) {
        	nbro=nbro+1;
        }
        int nbrc=nbrd+nbro;
        
 
        Annonce a = new Annonce (
            description.getText(),
            type.getValue(),
            prix,                  
            adresse.getText(),
            da,
            num,
            nbrc,
            nbrd,
            nbro,
            user.getId(),
            service.getSelectionModel().getSelectedItem().getId(),
            categorie.getSelectionModel().getSelectedItem().getId()                
                 );
        
        
        sa.ajouterAnnonce(a);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("Votre Annonce a été ajouté avec succés").graphic(new ImageView(image)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            notificationBuilder.darkStyle();
        notificationBuilder.show();
        
        try {
             FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/annonceFront.fxml"));   
                        Parent An  = Loader.load(); 

                        AnnonceFrontController controller = Loader.<AnnonceFrontController>getController();
		 	controller.setUser(this.getUser());                                        
                        Scene scene = new Scene(An);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                        stage.setScene(scene);  
        } catch (IOException ex) {
            Logger.getLogger(AjouterAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
        AnnonceService sa = AnnonceService.getInstance();
    	Annonce l = sa.last();
        //System.out.println(l.getNbr_d()+l.getNbr_o());
        frontIndexController.setUser(user);
        frontIndexController.initialize(null, null);
            ObservableList<String> options = 
    FXCollections.observableArrayList(
        "offre",
        "demande"
    );
    type.setItems(options);
        CategorieServiceService su = CategorieServiceService.getInstance();
        data=FXCollections.observableArrayList();
        ResultSet r =su.affichercategories();
        
            try {
                while (r.next()){
                    CategorieService u = new CategorieService();
                    u.setNom(r.getString("Nom"));
                    u.setId(r.getInt("id"));
                
                    data.add(u);
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categorie.setItems(data);
        
            StringConverter<CategorieService> converter = new StringConverter<CategorieService>() {
            @Override
            public String toString(CategorieService object) {
                return object.getNom();
            }

            @Override
            public CategorieService fromString(String string) {
                return null;
            }
        
         };
        categorie.setConverter(converter);
        
        
        
        ServiceService ss = ServiceService.getInstance();
        
        datas=FXCollections.observableArrayList();
        ResultSet rs =ss.afficherservices();

        try {
    while (rs.next()){
        Service s = new Service();
        s.setNom(rs.getString("Nom"));
        s.setId(rs.getInt("id"));
        
        datas.add(s);
         
    }
} catch (SQLException ex) {
    Logger.getLogger(AjouterAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
}

service.setItems(datas);

        StringConverter<Service> converters = new StringConverter<Service>() {
    @Override
    public String toString(Service object) {
        return object.getNom();
    }

    @Override
    public Service fromString(String string) {
        return null;
    }

 };
service.setConverter(converters);
        });
   } 
    
}
