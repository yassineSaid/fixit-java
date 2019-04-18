/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieProduit;
import Entities.ListAchat;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ali Saidani
 */
public class CategorieProduitService {

    Connection Cn = Connexion.getInstance().getCon();

    public void ajouterCategorie(Entities.CategorieProduit C) {
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

    public void modifierCategorie(Entities.CategorieProduit c) {
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

    public ObservableList<CategorieProduit> afficherCategorie()
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = Cn.createStatement();
        String req = "select * from categorie_produit";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieProduit/" + rs.getString(4), 120, 120, false, false);
            CategorieProduit categorie = new CategorieProduit();
            categorie.setId(rs.getInt(1));
            categorie.setNom(rs.getString(2));
            categorie.setDescription(rs.getString(3));
            categorie.setImage(rs.getString(4));
            categorie.setIm(new ImageView(image1));
            list.add(categorie);
        }
        return list;
    }

    public void supprimerCategorie(Entities.CategorieProduit c) {
        try {
            String req = "DELETE from categorie_produit where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, c.getId());

            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<ListAchat> listAchat() {
        try {
            ObservableList<ListAchat> list = FXCollections.observableArrayList();
            PreparedStatement pt = Cn.prepareStatement("SELECT* from achat_produit ");

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + rs.getString("image"), 120, 120, false, false);
                ListAchat p = new ListAchat();
                p.setNom(rs.getString("Produit"));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix(rs.getInt("prix"));
                p.setImage(rs.getString("image"));
                p.setIm(new ImageView(image1));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
      public XYChart.Series<String,Number> graph(){
          try{
                XYChart.Series<String,Number> serie =new XYChart.Series<>();
                
        Statement st = Cn.createStatement();
        String req = "SELECT SUM(p.quantite) somme ,p.produit   FROM achat_produit p GROUP BY p.idProduit ORDER BY somme DESC;";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        
            serie.getData().add(new XYChart.Data<>(rs.getString(2),rs.getInt(1)));
        }
        return serie;
        }
          catch(SQLException ex){
              System.out.println(ex);
          }
          return null;
          }
          public String verificationNom(String nom) {
        try {
            String req = "Select * from categorie_produit where Nom=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, nom);
     ResultSet rs = ste.executeQuery();
CategorieProduit c = new CategorieProduit();
            while (rs.next()) {
              c.setNom(rs.getString("nom"));
                
            }
            return c.getNom();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "";
    }
      

}
