/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Paiement;
import Entities.User;
import Services.PaiementService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class HistoriquePaiementController implements Initializable {

    @FXML
    private Label erreurDonnees;
    @FXML
    private TableView<Paiement> tableHisotrique;
    @FXML
    private TableColumn<Paiement, String> montant;
    @FXML
    private TableColumn<Paiement, String> nbScoins;
    @FXML
    private TableColumn<Paiement, Date> datePaiement;
    
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            afficherHistoriqueAction();
        });
    }    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public void afficherHistoriqueAction()
    {
        PaiementService ps=new PaiementService();
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        nbScoins.setCellValueFactory(new PropertyValueFactory<>("nombreScoin"));
        datePaiement.setCellValueFactory(new PropertyValueFactory<>("datePaiement"));
        tableHisotrique.setItems(ps.getHistorique(user));
    }
    
    @FXML
    private void quitterAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.close();
    }
}
