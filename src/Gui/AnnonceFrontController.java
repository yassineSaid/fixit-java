/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.Candidature;
import Entities.User;
import Services.AnnonceService;
import Services.CandidatureService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Achref Bannouri
 */
public class AnnonceFrontController implements Initializable {
    private ObservableList<Annonce> data = FXCollections.observableArrayList();
    private ObservableList<Annonce> datao = FXCollections.observableArrayList();
    private ObservableList<Annonce> datad = FXCollections.observableArrayList();
    private ObservableList<Annonce> mydata = FXCollections.observableArrayList();
    private ObservableList<Candidature> mydatac = FXCollections.observableArrayList();
    Image image=new Image(getClass().getResourceAsStream("/Resources/trash.png"));

	 private User user;
   
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
            @FXML
            private FrontIndexController frontIndexController;    
            @FXML
	    private TableView<Annonce> table;
	    @FXML
	    private  TableColumn<Annonce, Integer> servcol=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> descriptioncol=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> typecol=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> adressecol=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> telcol=new TableColumn<>();
	    @FXML
	    private TableColumn<Annonce, Date> datecol=new TableColumn<>();
            
            @FXML
	    private TableView<Annonce> taboffres;
	    @FXML
	    private  TableColumn<Annonce, Integer> servcolo=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> descriptioncolo=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, Long> prixcolo=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> adressecolo=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> telcolo=new TableColumn<>();
	    @FXML
	    private TableColumn<Annonce, Date> datecolo=new TableColumn<>();
            
            @FXML
	    private TableView<Annonce> tabdemandes;
	    @FXML
	    private  TableColumn<Annonce, Integer> servcold=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> descriptioncold=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, Long> prixcold=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> adressecold=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> telcold=new TableColumn<>();
	    @FXML
	    private TableColumn<Annonce, Date> datecold=new TableColumn<>();
            
            @FXML
	    private TableView<Annonce> tabmesannonces;
	    @FXML
	    private  TableColumn<Annonce, Integer> servcola=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> descriptioncola=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, Long> prixcola=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> adressecola=new TableColumn<>();
	    @FXML
	    private  TableColumn<Annonce, String> telcola=new TableColumn<>();
	    @FXML
	    private TableColumn<Annonce, Date> datecola=new TableColumn<>();
            
            @FXML
	    private TableView<Candidature> tabmescandidatures;
            @FXML
	    private  TableColumn<Candidature, Integer> annoncecolc=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, String> messagecolc=new TableColumn<>();
	    @FXML
	    private  TableColumn<Candidature, String> etatcolc=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, String> emailcolc=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, String> telcolc=new TableColumn<>();
            @FXML
	    private  TableColumn<Candidature, Date> datecolc=new TableColumn<>();
            
            @FXML
            private Pagination pagination;
            @FXML
            private Pagination paginationo;
            @FXML
            private Pagination paginationd;
            
	    
	    private static int idan;
	    public static int getId() {
	        return idan;
	    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
                AnnonceService sa = new AnnonceService();
                frontIndexController.setUser(user);
                frontIndexController.initialize(null, null);
	        servcol.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("IdService"));
	        descriptioncol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
	        typecol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("type"));
	        adressecol.setCellValueFactory(new PropertyValueFactory<Annonce, String>("adresse"));
	        datecol.setCellValueFactory(new PropertyValueFactory<Annonce, Date>("date"));
	         //buildData();
                 pagination.setPageFactory(this::createPage);
                 
                servcolo.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("IdService"));
	        descriptioncolo.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
	        telcolo.setCellValueFactory(new PropertyValueFactory<>("tel"));
	        adressecolo.setCellValueFactory(new PropertyValueFactory<Annonce, String>("adresse"));
	        datecolo.setCellValueFactory(new PropertyValueFactory<Annonce, Date>("date"));
                prixcolo.setCellValueFactory(new PropertyValueFactory<>("montant"));
                 //buildOffres();
                paginationo.setPageFactory(this::createPageo);
                                  
                servcold.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("IdService"));
	        descriptioncold.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
	        telcold.setCellValueFactory(new PropertyValueFactory<>("tel"));
	        adressecold.setCellValueFactory(new PropertyValueFactory<Annonce, String>("adresse"));
	        datecold.setCellValueFactory(new PropertyValueFactory<Annonce, Date>("date"));
                prixcold.setCellValueFactory(new PropertyValueFactory<>("montant"));
                //buildDemandes();
                paginationd.setPageFactory(this::createPaged);
 
                servcola.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("IdService"));
	        descriptioncola.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
	        telcola.setCellValueFactory(new PropertyValueFactory<>("tel"));
	        adressecola.setCellValueFactory(new PropertyValueFactory<Annonce, String>("adresse"));
	        datecola.setCellValueFactory(new PropertyValueFactory<Annonce, Date>("date"));
                prixcola.setCellValueFactory(new PropertyValueFactory<>("montant"));
                 buildMesAnnonces();
                 
                annoncecolc.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
                messagecolc.setCellValueFactory(new PropertyValueFactory<>("message"));
                etatcolc.setCellValueFactory(new PropertyValueFactory<>("etat"));
                emailcolc.setCellValueFactory(new PropertyValueFactory<>("email"));
                telcolc.setCellValueFactory(new PropertyValueFactory<>("tel"));
                datecolc.setCellValueFactory(new PropertyValueFactory<>("date"));
                buildMesCandidatures();
        });



                 
    }
private void buildData() {
	         AnnonceService sa = AnnonceService.getInstance();
	         data=FXCollections.observableArrayList();
                 ObservableList<Object> servname = FXCollections.observableArrayList();
	         
	        try {
	        	
 	            ResultSet r = sa.afficher(user.getId());
	            while(r.next()){
                        data.add(new Annonce(r.getInt("id"),r.getString("description"),r.getString("type"),
	                		r.getLong("montant"),r.getString("adresse"),r.getDate(6),r.getInt("tel"),r.getInt("nbr_c"),r.getInt("nbr_d"),r.getInt("nbr_o"),
	                		r.getInt("IdUser"),r.getInt("IdService"),r.getInt("CategorieService")));
	            }
	            
	            table.setItems(data);

	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
private void buildOffres() {
	         AnnonceService sa = AnnonceService.getInstance();
	         datao=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sa.offres(user.getId());
	            while(r.next()){
                        datao.add(new Annonce(r.getInt("id"),r.getString("description"),r.getString("type"),
	                		r.getLong("montant"),r.getString("adresse"),r.getDate(6),r.getInt("tel"),r.getInt("nbr_c"),r.getInt("nbr_d"),r.getInt("nbr_o"),
	                		r.getInt("IdUser"),r.getInt("IdService"),r.getInt("CategorieService")));
	            }
	            
	            taboffres.setItems(datao);

	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	        }
    }
private void buildDemandes() {
	         AnnonceService sa = AnnonceService.getInstance();
	         datad=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sa.demandes(user.getId());
	            while(r.next()){
                        datad.add(new Annonce(r.getInt("id"),r.getString("description"),r.getString("type"),
	                		r.getLong("montant"),r.getString("adresse"),r.getDate(6),r.getInt("tel"),r.getInt("nbr_c"),r.getInt("nbr_d"),r.getInt("nbr_o"),
	                		r.getInt("IdUser"),r.getInt("IdService"),r.getInt("CategorieService")));
	            }
	            
	            tabdemandes.setItems(datad);

	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	        }
    }

private void buildMesAnnonces() {
	         AnnonceService sa = AnnonceService.getInstance();
	         mydata=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sa.mesAnnonces(user.getId());
	            while(r.next()){
                        mydata.add(new Annonce(r.getInt("id"),r.getString("description"),r.getString("type"),
	                		r.getLong("montant"),r.getString("adresse"),r.getDate(6),r.getInt("tel"),r.getInt("nbr_c"),r.getInt("nbr_d"),r.getInt("nbr_o"),
	                		r.getInt("IdUser"),r.getInt("IdService"),r.getInt("CategorieService")));
	            }
	            
	            tabmesannonces.setItems(mydata);

	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	        }
    }
    private void buildMesCandidatures() {
	         CandidatureService sc = CandidatureService.getInstance();
	         mydatac=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sc.mesCandidatures(user.getId());
	            while(r.next()){
                        mydatac.add(new Candidature(r.getInt("id"),r.getInt("id_annonce"),r.getString("message"),
	                		r.getString("etat"),r.getString("email"),r.getString("tel"),r.getDate(7),r.getInt("idUser")
	                		));
	            }
	            
	            tabmescandidatures.setItems(mydatac);
	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
    
    
    	    @FXML
	    private void detail(ActionEvent event) {
	                try {
                        Annonce a =(Annonce)table.getSelectionModel().getSelectedItem();
                        DetailAnnonceController.setIdannonce(a.getId());

                        //System.out.println(a.getId());
                        //System.out.println(this.getUser().getId());

                        AnnonceService sa = AnnonceService.getInstance();

                        Annonce annonce = sa.DetailAnnonce(a.getId());
                        //System.out.println(annonce);

	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/detailAnnonce.fxml"));   
                        Parent An  = Loader.load(); 

                        DetailAnnonceController controller = Loader.<DetailAnnonceController>getController();
		 	controller.setUser(this.getUser());
                        controller.setAnnonce(annonce);
                        DetailAnnonceController.setIdannonce(a.getId());
                        AjouterCandidatureController.setIdannonce(a.getId());

                        Scene scene = new Scene(An);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                        stage.setScene(scene);

	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        	        
	    }
            
            
                	    @FXML
	    private void detailo(ActionEvent event) {
	                try {
                        Annonce a =(Annonce)taboffres.getSelectionModel().getSelectedItem();
                        DetailAnnonceController.setIdannonce(a.getId());

                        //System.out.println(a.getId());
                        //System.out.println(this.getUser().getId());

                        AnnonceService sa = AnnonceService.getInstance();

                        Annonce annonce = sa.DetailAnnonce(a.getId());
                        //System.out.println(annonce);

	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/detailAnnonce.fxml"));   
                        Parent An  = Loader.load(); 

                        DetailAnnonceController controller = Loader.<DetailAnnonceController>getController();
		 	controller.setUser(this.getUser());
                        controller.setAnnonce(annonce);
                        DetailAnnonceController.setIdannonce(a.getId());
                        AjouterCandidatureController.setIdannonce(a.getId());

                        Scene scene = new Scene(An);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                        stage.setScene(scene);

	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        	        
	    }
            
                	    @FXML
	    private void detaild(ActionEvent event) {
	                try {
                        Annonce a =(Annonce)tabdemandes.getSelectionModel().getSelectedItem();
                        DetailAnnonceController.setIdannonce(a.getId());

                        //System.out.println(a.getId());
                        //System.out.println(this.getUser().getId());

                        AnnonceService sa = AnnonceService.getInstance();

                        Annonce annonce = sa.DetailAnnonce(a.getId());
                        //System.out.println(annonce);

	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/detailAnnonce.fxml"));   
                        Parent An  = Loader.load(); 

                        DetailAnnonceController controller = Loader.<DetailAnnonceController>getController();
		 	controller.setUser(this.getUser());
                        controller.setAnnonce(annonce);
                        DetailAnnonceController.setIdannonce(a.getId());
                        AjouterCandidatureController.setIdannonce(a.getId());

                        Scene scene = new Scene(An);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                        stage.setScene(scene);

	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        	        
	    }
            
    @FXML
	    private void ModifierAnAction(ActionEvent event) {
	    try {
	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/modifierAnnonce.fxml"));   
		 	Parent An = Loader.load();          
		 	ModifierAnnonceController controller = Loader.<ModifierAnnonceController>getController();
		 	controller.setUser(this.getUser());
                        Annonce annonce = (Annonce) tabmesannonces.getSelectionModel().getSelectedItem();
                        ModifierAnnonceController.setIdan(annonce.getId());
                        Scene scene = new Scene(An);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	    }
            
            
                @FXML
	    private void ModifierCnAction(ActionEvent event) {
	    try {
	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/modifierCandidature.fxml"));   
		 	Parent An = Loader.load();          
		 	ModifierCandidatureController controller = Loader.<ModifierCandidatureController>getController();
		 	controller.setUser(this.getUser());
                        Candidature cn = (Candidature) tabmescandidatures.getSelectionModel().getSelectedItem();
                        ModifierCandidatureController.setIdcn(cn.getId());                        
                        ModifierCandidatureController.setIdan(cn.getId_annonce());
                        Scene scene = new Scene(An);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	    }
            
            
            
    @FXML
      public void SupprimerAnAction(ActionEvent event){                        
        AnnonceService sa = new AnnonceService();
        ObservableList<Annonce> selectedAnnonces, allAnnonces;
        
        allAnnonces = tabmesannonces.getItems();
        selectedAnnonces = tabmesannonces.getSelectionModel().getSelectedItems();
        
        Annonce annonce = (Annonce) tabmesannonces.getSelectionModel().getSelectedItem();
        
        int id = annonce.getId();
        
        
        sa.supprimerAnnonce(id);
        selectedAnnonces.forEach(allAnnonces::remove);
        
              Notifications notificationBuilder = Notifications.create().title("Notification").text("Votre annonce(s) a été supprimée avec succés").graphic(new ImageView(image)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        
    }
      
      
      
      @FXML
      public void SupprimerCanAction(ActionEvent event){
               
                        
        CandidatureService sa = new CandidatureService();
        ObservableList<Candidature> selectedCandidatures, allCandidatures;
        
        allCandidatures = tabmescandidatures.getItems();
        selectedCandidatures = tabmescandidatures.getSelectionModel().getSelectedItems();
        
        Candidature candidature = (Candidature) tabmescandidatures.getSelectionModel().getSelectedItem();
        
        int id = candidature.getId();
        
        
        sa.supprimerCandidature(id);
        selectedCandidatures.forEach(allCandidatures::remove);
        Notifications notificationBuilder = Notifications.create().title("Notification").text("Votre Candidature(s) a été supprimée avec succés").graphic(new ImageView(image)).hideAfter(Duration.seconds(15)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        
                    
        
    }
      
       @FXML
	    private void AjouterAnAction(ActionEvent event) {
	       
	                 try {
	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/ajouterAnnonce.fxml"));   
		 	Parent An = Loader.load();          
		 	AjouterAnnonceController controller = Loader.<AjouterAnnonceController>getController();
		 	controller.setUser(this.getUser());
                        Scene scene = new Scene(An);
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.show();
            stage.setScene(scene);
	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        
	        

	    }
            
            
            @FXML
	    private void lesCandidatures(ActionEvent event) {
	                Annonce a =(Annonce)tabmesannonces.getSelectionModel().getSelectedItem();
                        LesCandidaturesController.setIdannonce(a.getId());                 
              try {
                        AnnonceService sa = AnnonceService.getInstance();

                        Annonce annonce = sa.DetailAnnonce(a.getId());
                        //System.out.println(annonce);

	                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/lesCandidatures.fxml"));   
                        Parent An  = Loader.load(); 

                        LesCandidaturesController controller = Loader.<LesCandidaturesController>getController();
		 	controller.setUser(this.getUser());
                        LesCandidaturesController.setIdannonce(a.getId());
                        Scene scene = new Scene(An);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.show();
                        stage.setScene(scene);

	            } catch (IOException ex) {
	                Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        	       
	    }
            
            private Node createPage(int pageIndex) {
        
	 buildData();
        int fromIndex = pageIndex * 5;
        int toIndex = Math.min(fromIndex + 5, data.size());
        table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return table;
    }
private Node createPageo(int pageIndex) {
        
	buildOffres();
        int fromIndex = pageIndex * 5;
        int toIndex = Math.min(fromIndex + 5, data.size());
        taboffres.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return taboffres;
    }
                        
private Node createPaged(int pageIndex) {
        
	buildDemandes();
        int fromIndex = pageIndex * 5;
        int toIndex = Math.min(fromIndex + 5, data.size());
        tabdemandes.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return tabdemandes;
    }    
}
