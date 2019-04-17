/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.PaiementService;
import Services.ServiceService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SELON
 */
public class BackAccueilController implements Initializable {

    @FXML
    BackIndexController backIndexController;
    private User user;
    @FXML
    private BarChart<String, Float> statsAnnee;
    @FXML
    private NumberAxis revenus;
    @FXML
    private CategoryAxis mois;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            backIndexController.setUser(user);
            afficherStats();
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void afficherStats() {
        PaiementService ps = new PaiementService();
        XYChart.Series data = ps.statAnnee();
        statsAnnee.getData().addAll(data);
        statsAnnee.setBarGap(0);
        statsAnnee.setCategoryGap(30);
        for(Node n:statsAnnee.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #5998ff;");
        }

    }
}
