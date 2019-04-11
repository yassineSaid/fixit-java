/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField ccNumber;
    @FXML
    private TextField nbScoin;
    @FXML
    private TextField annee;
    @FXML
    private TextField mois;
    @FXML
    private TextField montant;
    private User user;
    @FXML
    private Label erreurDonnees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {

        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void payerAction(ActionEvent event) {
        float mn;
        int nbS;
        nbS = Integer.parseInt(nbScoin.getText());
        mn = nbS / 2;
        Stripe.apiKey = "sk_test_rkfr2kuDbj8a7LRmarLt40W7";
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", nbS*100);
        chargeMap.put("currency", "usd");
        Map<String, Object> cardMap = new HashMap<String, Object>();
        cardMap.put("number", ccNumber.getText());
        cardMap.put("exp_month", Integer.parseInt(mois.getText()));
        cardMap.put("exp_year", Integer.parseInt(annee.getText()));
        chargeMap.put("card", cardMap);
        try {
            Charge charge = Charge.create(chargeMap);
            Button payer=(Button) event.getSource();
            payer.setDisable(true);
            if (charge.getStatus().equals("succeeded"))
            {
                UserService us=new UserService();
                us.modifierSolde(user, nbS);
                payer.setDisable(false);
            }
            else
            {
            }
        } catch (StripeException e) {
            erreurDonnees.setText("Vérifiez vos données");
            Button payer=(Button) event.getSource();
            payer.setDisable(false);
        }
    }

    @FXML
    private void quitterAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void verifier(TextField tf) {
        String val = tf.getText();
        System.out.println(val);
        String newVal = "";
        for (char ch : val.toCharArray()) {
            if (Character.isDigit(ch)) {
                newVal += ch;
            }
        }
        tf.setText(newVal);
    }

    @FXML
    private void changerAction(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        char ch = event.getCharacter().charAt(0);
        if (!Character.isDigit(ch)) {
            event.consume();
        }
        //verifier((TextField) event.getSource());
    }

    @FXML
    private void updateMontant(KeyEvent event) {
        float mn;
        int nbS;
        nbS = Integer.parseInt(nbScoin.getText());
        mn = nbS / 2;
        montant.setText(String.valueOf(mn) + " DT");
    }

}
