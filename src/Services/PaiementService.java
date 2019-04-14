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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
}
