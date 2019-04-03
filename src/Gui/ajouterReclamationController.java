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
import Entities.User;
import Services.ReclamationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;

public class ajouterReclamationController implements Initializable 
{
	@FXML
	private Tab interfaceAjout;
	@FXML
	private TabPane Tabwidget;
	@FXML
	private AnchorPane ajouterRec;
	@FXML
	private ComboBox<String> userReclamer;
	@FXML
	private ComboBox<String> serviceRendu;
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{

           // date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
           // img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
           // real_col.setCellValueFactory(new PropertyValueFactory<>("realisateur")); 
           // ObservableList<article> oblist=FXCollections.observableArrayList();

		   
		Platform.runLater(() -> {
			ObservableList<String> list = FXCollections.observableArrayList();
			ReclamationService r= new ReclamationService();
			list=r.getusersreclamer(user.getId());	
			userReclamer.setItems(list);
	    });
		
	}
	
	
	@FXML
	public void selectservice()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		ReclamationService r= new ReclamationService();
		list=r.getServiceuserreclamer(userReclamer.getValue().toString());
		serviceRendu.setItems(list);
		System.out.println(user.getEmail());
	}
	
	
	@FXML
	public void selectdate()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		ReclamationService r= new ReclamationService();
		list=r.getDateuserreclamer(userReclamer.getValue().toString(),serviceRendu.getValue().toString());
		dateService.setItems(list);
	}
	
	@FXML
	public void ajouterReclamation() throws ParseException 
	{ 
		ReclamationService recServ= new ReclamationService();
		int userReclameId=recServ.RecupereridUser(userReclamer.getValue());
		int serviceId=recServ.RecupereridService(serviceRendu.getValue());
		int userId=this.getUser().getId();
		String stringDate=dateService.getValue(); 
		SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=formatter1.parse(stringDate);
		java.sql.Date dateRealisation = new java.sql.Date(date1.getTime());
		Date localdate = new Date();
		java.sql.Date dateReclamation = new java.sql.Date(localdate.getTime());
		Reclamation rec = new Reclamation(motif.getText(),description.getText(),userReclameId,userId,dateReclamation,0,0,0,serviceId,dateRealisation);
		recServ.ajouterRaclamation(rec);
		
		
	}
}
