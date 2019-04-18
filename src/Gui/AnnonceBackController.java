/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Annonce;
import Entities.Candidature;
import Services.AnnonceService;
import Services.CandidatureService;
import java.awt.Canvas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * FXML Controller class
 *
 * @author Achref Bannouri
 */
public class AnnonceBackController implements Initializable {
    private ObservableList<Annonce> data = FXCollections.observableArrayList();
    private ObservableList<Candidature> datac = FXCollections.observableArrayList();

    @FXML
    private Tab annoncetab;
    @FXML
    private TableView<Annonce> annonces;
    @FXML
    private TableColumn<Annonce, Integer> ida;
    @FXML
    private TableColumn<Annonce, Integer> servicea;
    @FXML
    private TableColumn<Annonce, String> descriptiona;
    @FXML
    private TableColumn<Annonce, String> typea;
    @FXML
    private TableColumn<Annonce, Long> prixa;
    @FXML
    private TableColumn<Annonce, String> adressea;
    @FXML
    private TableColumn<Annonce, Date> datea;
    @FXML
    private TableColumn<Annonce, Integer> tela;
    @FXML
    private TableColumn<Annonce, Integer> usera;
    @FXML
    private Tab candidaturetab;
    @FXML
    private TableView<Candidature> candidatures;
    @FXML
    private TableColumn<Candidature,Integer> idc;
    @FXML
    private TableColumn<Candidature, Integer> annoncec;
    @FXML
    private TableColumn<Candidature, String> messagec;
    @FXML
    private TableColumn<Candidature, String> etatc;
    @FXML
    private TableColumn<Candidature, String> emailc;
    @FXML
    private TableColumn<Candidature, String> telc;
    @FXML
    private TableColumn<Candidature, Date> datec;
    @FXML
    private TableColumn<Candidature, Integer> userc;
    @FXML
    private Tab stattab;
    @FXML
    private PieChart piechart;
    @FXML
    private Pagination paginator;
    @FXML
    private Pagination paginatorc;
    @FXML
    private AnchorPane stat;

    @FXML
    private Canvas canva;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
           ida.setCellValueFactory(new PropertyValueFactory<>("id"));
           servicea.setCellValueFactory(new PropertyValueFactory<>("IdService"));
	    descriptiona.setCellValueFactory(new PropertyValueFactory<>("description"));
	    typea.setCellValueFactory(new PropertyValueFactory<>("type"));
            prixa.setCellValueFactory(new PropertyValueFactory<>("montant"));
	    adressea.setCellValueFactory(new PropertyValueFactory<>("adresse"));
	    datea.setCellValueFactory(new PropertyValueFactory<>("date"));
            tela.setCellValueFactory(new PropertyValueFactory<>("tel"));
            usera.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            //buildAnnonces();
	    paginator.setPageFactory(this::createPage);
            
                idc.setCellValueFactory(new PropertyValueFactory<>("id"));            
                annoncec.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
                messagec.setCellValueFactory(new PropertyValueFactory<>("message"));
                etatc.setCellValueFactory(new PropertyValueFactory<>("etat"));
                emailc.setCellValueFactory(new PropertyValueFactory<>("email"));
                telc.setCellValueFactory(new PropertyValueFactory<>("tel"));
                datec.setCellValueFactory(new PropertyValueFactory<>("date"));
                userc.setCellValueFactory(new PropertyValueFactory<>("IdUser"));                
                //buildCandidatures();
                paginatorc.setPageFactory(this::createPageC);
                
                SetChart();
        });
    }    
    
    private void buildAnnonces() {
	         AnnonceService sa = AnnonceService.getInstance();
	         data=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sa.afficherBack();
	            while(r.next()){
                        data.add(new Annonce(r.getInt("id"),r.getString("description"),r.getString("type"),
	                		r.getLong("montant"),r.getString("adresse"),r.getDate(6),r.getInt("tel"),r.getInt("nbr_c"),r.getInt("nbr_d"),r.getInt("nbr_o"),
	                		r.getInt("IdUser"),r.getInt("IdService"),r.getInt("CategorieService")));
	            }
	            
	            annonces.setItems(data);

	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceBackController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
    
        private void buildCandidatures() {
	         CandidatureService sc = CandidatureService.getInstance();
	         datac=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sc.afficherBack();
	            while(r.next()){
                        datac.add(new Candidature(r.getInt("id"),r.getInt("id_annonce"),r.getString("message"),
	                		r.getString("etat"),r.getString("email"),r.getString("tel"),r.getDate(7),r.getInt("idUser")
	                		));
	            }
	            
	            candidatures.setItems(datac);
	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceFrontController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
    
    @FXML
      public void SupprimerAnAction(ActionEvent event){
               Alert a1 = new Alert(Alert.AlertType.WARNING);
                    a1.setTitle("supprimer une annone");
                    a1.setContentText("vous voulez vraiment supprimer cette annonce ?");
                    Optional<ButtonType> result = a1.showAndWait();
                
                    if (result.get() == ButtonType.OK) {
                        
        AnnonceService sa = new AnnonceService();
        ObservableList<Annonce> selectedAnnonces, allAnnonces;
        
        allAnnonces = annonces.getItems();
        selectedAnnonces = annonces.getSelectionModel().getSelectedItems();
        
        Annonce annonce = (Annonce) annonces.getSelectionModel().getSelectedItem();
        
        int id = annonce.getId();
        
        
        sa.supprimerAnnonce(id);
        selectedAnnonces.forEach(allAnnonces::remove);
        
        Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                            a2.setTitle("supprimer annonce");
                        a2.setContentText("annonce supprimée avec succés");
                        a2.show();
                    }
        
    }
      @FXML 
      public void PieChart(ActionEvent event)
      {
       PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset); 
        ChartViewer viewer = new ChartViewer(chart);
        //stat.getChildren().add(viewer);
        Stage stage=new Stage();
        stage.setScene(new Scene(viewer));
        stage.setTitle("Statistiques des annonces"); 
        stage.setWidth(700);
        stage.setHeight(390);
        stage.show();   
      }
            @FXML 
      public void BarChart(ActionEvent event)
      {
        
        CategoryDataset datasete = createDatasete();
        JFreeChart charte = createCharte(datasete); 
        ChartViewer viewere = new ChartViewer(charte);
        Stage stagee=new Stage();
        stagee.setScene(new Scene(viewere));
        stagee.setTitle("Statistiques des annonces"); 
        stagee.setWidth(700);
        stagee.setHeight(390);
        stagee.show();
      }
      @FXML
      public void SupprimerCanAction(ActionEvent event){
               Alert a1 = new Alert(Alert.AlertType.WARNING);
                    a1.setTitle("supprimer une candidature");
                    a1.setContentText("vous voulez vraiment supprimer cette candidature ?");
                    Optional<ButtonType> result = a1.showAndWait();
                
                    if (result.get() == ButtonType.OK) {
                        
        CandidatureService sa = new CandidatureService();
        ObservableList<Candidature> selectedCandidatures, allCandidatures;
        
        allCandidatures = candidatures.getItems();
        selectedCandidatures = candidatures.getSelectionModel().getSelectedItems();
        
        Candidature candidature = (Candidature) candidatures.getSelectionModel().getSelectedItem();
        
        int id = candidature.getId();
        
        
        sa.supprimerCandidature(id);
        selectedCandidatures.forEach(allCandidatures::remove);
        
        Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                            a2.setTitle("supprimer candidature");
                        a2.setContentText("candidature supprimée avec succés");
                        a2.show();
                    }
        
    }
      
    public void SetChart() {
        AnnonceService sa = AnnonceService.getInstance();
        Annonce a = sa.last();
        ObservableList<Data> s = FXCollections.observableArrayList(
                new PieChart.Data("Les offres", a.getNbr_o()),
                new PieChart.Data("Les Demandes", a.getNbr_d())
        );
        piechart.setData(s);
    }
        /*public void JFreeChart() {
        AnnonceService sa = AnnonceService.getInstance();
        Annonce a = sa.last();
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Les offres", a.getNbr_o());
        data.setValue("Les demandes", a.getNbr_d());
        JFreeChart chart = ChartFactory.createPieChart3D("Les offres par rapport aux demandes", data, false, false, false);
        try {
            ChartUtilities.saveChartAsJPEG(new File("C:\\FinalChart.jpeg"), chart, 500, 300);
        }
        catch (Exception e) {
            System.err.println("error" + e);
        }
        ObservableList<Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Les offres", a.getNbr_o()),
                new PieChart.Data("Les Demandes", a.getNbr_d())
        );
        piechart.setData(data);
    }*/
    private Node createPage(int pageIndex) {
        
	         AnnonceService sa = AnnonceService.getInstance();
	         data=FXCollections.observableArrayList();
	       
	        try {
	        	
 	            ResultSet r = sa.afficherBack();
	            while(r.next()){
                        data.add(new Annonce(r.getInt("id"),r.getString("description"),r.getString("type"),
	                		r.getLong("montant"),r.getString("adresse"),r.getDate(6),r.getInt("tel"),r.getInt("nbr_c"),r.getInt("nbr_d"),r.getInt("nbr_o"),
	                		r.getInt("IdUser"),r.getInt("IdService"),r.getInt("CategorieService")));
	            }
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceBackController.class.getName()).log(Level.SEVERE, null, ex);
	        }
        int fromIndex = pageIndex * 5;
        int toIndex = Math.min(fromIndex + 5, data.size());
        annonces.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return annonces;
    }
    
    
        private Node createPageC(int pageIndex) {
        
	         CandidatureService sc = CandidatureService.getInstance();
	         datac=FXCollections.observableArrayList();
	       
	       try {
	        	
 	            ResultSet r = sc.afficherBack();
	            while(r.next()){
                        datac.add(new Candidature(r.getInt("id"),r.getInt("id_annonce"),r.getString("message"),
	                		r.getString("etat"),r.getString("email"),r.getString("tel"),r.getDate(7),r.getInt("idUser")
	                		));
	            }
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(AnnonceBackController.class.getName()).log(Level.SEVERE, null, ex);
	        }
        int fromIndex = pageIndex * 5;
        int toIndex = Math.min(fromIndex + 5, datac.size());
        candidatures.setItems(FXCollections.observableArrayList(datac.subList(fromIndex, toIndex)));

        return candidatures;
    }

        
         private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        AnnonceService sa = AnnonceService.getInstance();
        Annonce a = sa.last();
        dataset.setValue("Les offres",a.getNbr_o());
        dataset.setValue("Les demandes", a.getNbr_d());        
        return dataset;
    }   
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            "Les offres et les demandes", dataset);

        // set a custom background for the chart
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), 
                new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

        // customise the title position and font
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);

        // use gradients and white borders for the section colours
        plot.setSectionPaint("Les offres", createGradientPaint(new Color(200, 200, 255), Color.BLUE));
        plot.setSectionPaint("Les demandes", createGradientPaint(new Color(255, 200, 200), Color.RED));
        plot.setDefaultSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));

        // customise the section label appearance
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);
        
        // add a subtitle giving the data source
        /*TextTitle source = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523", 
                new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);*/
        return chart;
    }   
    private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};
        return new RadialGradientPaint(center, radius, dist,
                new Color[] {c1, c2});
    }
    
    
        private static JFreeChart createCharte(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
            "Les offres par rapport aux demandes", null /* x-axis label*/, 
                "Nombre d'annonces" /* y-axis label */, dataset);
        chart.addSubtitle(new TextTitle("Le nombre des annonces de type offre et les annonces de type demande " 
               ));
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }
        
         private static CategoryDataset createDatasete() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        AnnonceService sa = AnnonceService.getInstance();
        Annonce a = sa.last();
        dataset.addValue(a.getNbr_o(), "Les offres", "");
        dataset.addValue(a.getNbr_d(), "Les demandes", "");
        return dataset;
    }
      
}
