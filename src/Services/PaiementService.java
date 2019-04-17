/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Horraire;
import Entities.Paiement;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;

/**
 *
 * @author Yassine
 */
public class PaiementService {
    Connection C=Connexion.getInstance().getCon();
    public void ajouterPaiement(User user,double montant, int nombreScoin, String stripeToken)
    {
        try {
            PreparedStatement pt = C.prepareStatement("INSERT INTO paiement(Montant,NombreScoin,stripeToken,IdUser,datePaiement) VALUES(?,?,?,?,now())");
            pt.setDouble(1, montant);
            pt.setInt(2, nombreScoin);
            pt.setString(3, stripeToken);
            pt.setInt(4, user.getId());
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Paiement> getHistorique(User user)
    {
        ObservableList<Paiement> data;
        data=FXCollections.observableArrayList();
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("select * from paiement where IdUser=? ORDER BY datePaiement");
            pt.setInt(1,user.getId());
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                data.add(new Paiement(rs.getDate("datePaiement"), rs.getInt("id"), rs.getInt("IdUser"), rs.getDouble("Montant"), rs.getInt("NombreScoin"), rs.getString("stripeToken")));
            }
        return data;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public XYChart.Series statAnnee(){
        XYChart.Series data = new XYChart.Series<>();
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("SELECT month(datePaiement) AS M,year(datePaiement) AS Y,sum(montant) MON from paiement" +
"                  where datediff(now(),datePaiement)/365<=1 group BY Y,M ORDER BY Y,M");
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                XYChart.Data d = new XYChart.Data(Utils.convertMonth(rs.getInt("M")),rs.getFloat("MON"));
                //displayLabelForData(d);
                data.getData().add(d);
                //data.add();
            }
        return data;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        private void displayLabelForData(XYChart.Data<String, Number> data) {
        final Node node = data.getNode();
        final Text dataText = new Text(data.getYValue() + "");
        node.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override
            public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
                Group parentGroup = (Group) parent;
                parentGroup.getChildren().add(dataText);
            }
        });
        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>(){
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
