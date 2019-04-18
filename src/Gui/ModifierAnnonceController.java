/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.CategorieService;
import Entities.Service;
import Entities.User;
import Services.AnnonceService;
import Services.CategorieServiceService;
import Services.ServiceService;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Achref Bannouri
 */
public class ModifierAnnonceController implements Initializable {
        String msg = "";
        private User user;
    	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
        private ObservableList data;
        private ObservableList<Service> datas;
        
        private static int idan;

    public static int getIdan() {
        return idan;
    }

    public static void setIdan(int idan) {
        ModifierAnnonceController.idan = idan;
    }

    
        
    
    @FXML
    private FrontIndexController frontIndexController;  
    @FXML
    private TextField description;
    @FXML
    private TextField type;
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
    private ComboBox<Service> service;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
        frontIndexController.setUser(user);
        frontIndexController.initialize(null, null);
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
    
    
    
    @FXML
    private void ModifierAction(ActionEvent event){
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
        
        Alert a1 = new Alert(Alert.AlertType.WARNING);
                    a1.setTitle("Modifier l'annonce");
                    a1.setContentText("vous voulez vraiment mofidier cette annonce?");
                    Optional<ButtonType> result = a1.showAndWait();
                
                    if (result.get() == ButtonType.OK) {
                            	Date da = Date.valueOf(date.getValue());
    	Annonce l = sa.last();
    	int num = Integer.parseInt(tel.getText());
    	int nbrd =l.getNbr_d();
    	int nbro =0 ;
        long prix = Long.parseLong(montant.getText());
        if ("demande".equals(type.getText())) {
        	nbrd=l.getNbr_d()+1;
        	nbro=l.getNbr_o();
        }
        else if ("offre".equals(type.getText())) {
        	nbro=l.getNbr_o()+1;
        	nbrd=l.getNbr_d();
        }
        int nbrc=nbrd+nbro;
        
            
                          Annonce a = new Annonce (
            idan,
            description.getText(),
            type.getText(),
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
                    sa.modifierAnnonce(a,idan);                    
                    }
    }
    }
}
                    
