/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Entities.categorie_produit;
import Entities.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ali Saidani
 */
public class Produit {

    Connection Cn = Connexion.getInstance().getCon();

    public ObservableList<User> getuserOwner(int idUser) {
        try {
            ObservableList<User> list = FXCollections.observableArrayList();
            // to correct id=11 -> id user connected
            PreparedStatement pt = Cn.prepareStatement("SELECT * FROM user where id=? ");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                list.add(u);
            }
            return list;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;

    }

    public ObservableList<categorie_produit> getCategorie() {
        try {
            ObservableList<categorie_produit> list = FXCollections.observableArrayList();
            PreparedStatement pt = Cn.prepareStatement("Select * from categorie_produit ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                categorie_produit serv = new categorie_produit();
                serv.setId(rs.getInt("id"));
                serv.setNom(rs.getString("Nom"));
                list.add(serv);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void ajouterProduit(produit p) {
        try {
            String req = "insert into produit (Nom, Quantite, idCategorieProduit, prix, date_exp, user, image) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getQuantite());
            ste.setInt(3, p.getIdCategorieProduit().getId());
            ste.setInt(4, p.getPrix());
            ste.setTimestamp(5, p.getDate_exp());
            ste.setInt(6, p.getUser().getId());
            ste.setString(7, p.getImage());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
