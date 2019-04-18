/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.AvisService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EspaceAvisBackController implements Initializable {

    @FXML
    private Rating rating;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rating.setMaxHeight(1);
        EventHandler<MouseEvent> handler = MouseEvent::consume;
        rating.addEventFilter(MouseEvent.ANY, handler);
        AvisService avisServ = new AvisService();
        rating.setRating(avisServ.moyenneNotes());
        insertDataInPieChart();
        Platform.runLater(() -> {

        });
    }
    public void insertDataInPieChart()
    {
        AvisService avisServ = new AvisService();
        int nbrNon= avisServ.getNonSatisfait();
        int nbrMoy = avisServ.getMoyennementSatisfait();
        int nbrTot = avisServ.getTotalementSatisfait();
         ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Moyennement Satisfait", nbrMoy),
                new PieChart.Data("Tatalement Satisfait", nbrTot),
                new PieChart.Data("Non Satisfait", nbrNon));
        pieChart.setData(pieChartData);
        
    }
}
