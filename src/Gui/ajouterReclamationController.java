package Gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import Services.ReclamationService;
import java.text.DateFormat;
import java.util.Iterator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;

public class ajouterReclamationController implements Initializable 
{
	@FXML
	private FrontIndexController frontIndexController;
	@FXML
	private Tab interfaceAjout;
	@FXML
	private TabPane Tabwidget;
	@FXML
	private AnchorPane ajouterRec;
	@FXML
	private ComboBox<User> userReclamer;
	@FXML
	private ComboBox<Service> serviceRendu;
	@FXML
	private ComboBox<String> dateService;
	@FXML
	private TextField motif;
	@FXML
	private TextArea description;
	@FXML
	private Button ajouter_Rec;
	
	private User user;	
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
        private ListView<Reclamation> listRec;
        @FXML
        private Button modifierRec;
        private int id;
        @FXML
        private Button supprimerRec;
        
        
        
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		Platform.runLater(() -> {

			ObservableList<User> list = FXCollections.observableArrayList();

                        frontIndexController.setUser(user);
                        frontIndexController.initialize(null, null);
			ObservableList<String> list = FXCollections.observableArrayList();

			ReclamationService r= new ReclamationService();
			list=r.getusersreclamer(user.getId());	
			userReclamer.setItems(list);
                        serviceRendu.setItems(null);
                        dateService.setItems(null);
                        afficherReclamations();
                        modifierRec.setVisible(false);
                        supprimerRec.setVisible(false);
                        motif.setText("");
                        description.setText("");
	    });
		
	}
	
	
	@FXML
	public void selectservice()
	{
		ObservableList<Service> list = FXCollections.observableArrayList();
		ReclamationService r= new ReclamationService();
		list=r.getServiceuserreclamer(userReclamer.getValue().toString());
		serviceRendu.setItems(list);
	}
	
	
	@FXML
	public void selectdate()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		ReclamationService r= new ReclamationService();
		list=r.getDateuserreclamer(userReclamer.getValue().getUsername(),serviceRendu.getValue().getNom());
		dateService.setItems(list);
	}
	
	@FXML
	public void ajouterReclamation() throws ParseException 
	{
		ReclamationService recServ= new ReclamationService();
		User userReclame = new  User();
                userReclame=userReclamer.getValue();
                Service service= new Service();
                service=serviceRendu.getValue();
		String stringDate=dateService.getValue(); 
		SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=formatter1.parse(stringDate);
		java.sql.Date dateRealisation = new java.sql.Date(date1.getTime());
		Date localdate = new Date();
		java.sql.Date dateReclamation = new java.sql.Date(localdate.getTime());
		Reclamation rec = new Reclamation(motif.getText(),description.getText(),userReclame,this.getUser(),dateReclamation,0,0,0,service,dateRealisation);
		recServ.ajouterRaclamation(rec);
                afficherReclamations();
                initialize(null, null);
	}
        
        public void afficherReclamations()
        {
            ReclamationService r= new ReclamationService();
            ObservableList<Reclamation> list = FXCollections.observableArrayList();
            list=r.afficherReclamation();
            listRec.setItems(list);
        }

    @FXML
    private void remplirChamps(MouseEvent event) {
     
        ReclamationService recServ= new ReclamationService();
        
        if(event.getClickCount()==2)
        {
            Reclamation rec= new Reclamation();
            rec= listRec.getSelectionModel().getSelectedItem();
            motif.setText(rec.getObjet());
            description.setText(rec.getDescription());
            userReclamer.setValue(rec.getUserReclame());
            serviceRendu.setValue(rec.getIdServiceRealise());
            String dateRealisation = rec.getDateRealisation().toString();
	    dateService.setValue(dateRealisation);
            ajouter_Rec.setVisible(false);
            modifierRec.setVisible(true);
            supprimerRec.setVisible(true);
            this.id=rec.getId();
        }
    }

    @FXML
    private void modifierRecAction(ActionEvent event) throws ParseException {
        ReclamationService recServ= new ReclamationService();
        User userReclame = new  User();
        userReclame=userReclamer.getValue();
        Service service= new Service();
        service = serviceRendu.getValue();
        String stringDate = dateService.getValue();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter1.parse(stringDate);
        java.sql.Date dateRealisation = new java.sql.Date(date1.getTime());
        Date localdate = new Date();
		java.sql.Date dateReclamation = new java.sql.Date(localdate.getTime());
	Reclamation rec = new Reclamation(this.id,motif.getText(),description.getText(),userReclame,user,dateReclamation,0,0,0,service,dateRealisation);
	recServ.modifierReclamation(rec);
        afficherReclamations();
        initialize(null, null);
    }

    @FXML
    private void supprimerRecAction(ActionEvent event) {
        ReclamationService recServ= new ReclamationService();
        recServ.supprimerReclamation(this.id);
        afficherReclamations();
        initialize(null, null);
    }   
}

