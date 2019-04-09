/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Entities.categorie_produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ali Saidani
 */
public class CategorieProduit {

    Connection Cn = Connexion.getInstance().getCon();

    public void ajouterCategorie(categorie_produit C) {
        try {
            String req = "insert into categorie_produit (Nom, description, image) VALUES (?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, C.getNom());
            ste.setString(2, C.getDescription());
            ste.setString(3, C.getImage());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void modifierCategorie(categorie_produit c) {
        try {
            String req = "UPDATE categorie_produit set Nom=?, description=? , image=? where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, c.getNom());
            ste.setString(2, c.getDescription());
            ste.setString(3, c.getImage());
            ste.setInt(4, c.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public ObservableList<categorie_produit> getAllCategorie() throws SQLException {
        ObservableList l = FXCollections.observableArrayList();
        Statement st = Cn.createStatement();
        String req = "select * from categorie_produit";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            categorie_produit c = new categorie_produit();
            c.setId(rs.getInt(1));
            c.setNom(rs.getString(2));
            c.setDescription(rs.getString(3));
            c.setImage(rs.getString(4));
            
            l.add(c);

        }
        return l;
    }

    public void supprimerCategorie(categorie_produit c) {
        try{
        String req="DELETE from categorie_produit where id=?";
         PreparedStatement ste = Cn.prepareStatement(req);
         ste.setInt(1, c.getId());
         
        ste.executeUpdate();
        }
       catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }

}
