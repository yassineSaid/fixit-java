/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieOutil;
import Entities.HistoriqueLocation;
import Entities.Outil;
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
 * @author SL-WASSIM
 */
public class HistoriqueLocationService {
    Connection c=Connexion.getInstance().getCon();
     public Outil getOutil(int Id) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from outils where id=?");
            pt.setInt(1, Id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/" + rs.getString(9), 150, 150, false, false);
                Outil outil = new Outil();
                outil.setId(rs.getInt(1));
                outil.setNom(rs.getString(2));
                outil.setQuantite(rs.getInt(3));
                outil.setDureeMaximale(rs.getInt(4));
                outil.setPrix(rs.getInt(5));
                outil.setAdresse(rs.getString(6));
                outil.setCodePostal(rs.getInt(7));
                outil.setVille(rs.getString(8));
                outil.setImage(rs.getString(9));
                outil.setIm(new ImageView(image1));
                OutilService categorieService = new OutilService();
                CategorieOutil categorie = categorieService.getCategorieOutil(rs.getInt(10));
                outil.setC(categorie);
                outil.setNomCategorie(categorie.getNom());
                return outil;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public User getUser(int Id) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from user where id=?");
            pt.setInt(1, Id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setImage(rs.getString("image"));
                user.setSolde(rs.getInt("solde"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public ObservableList<HistoriqueLocation> afficherOutil() 
             {
                 try
                 {
                     ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from historique_location";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            HistoriqueLocation ho = new HistoriqueLocation();
            User u = new User();
            u=this.getUser(rs.getInt(2));
            Outil o = new Outil();
            o=this.getOutil(rs.getInt(3));
            ho.setIdUser(u);
            ho.setIdOutil(o);
            ho.setDateLocation(rs.getDate(4));
            ho.setDateRetour(rs.getDate(5));
            ho.setTotal(rs.getInt(6));
            list.add(ho);
        }
        return list;
                 }
                 catch(SQLException ex)
                 {
                     System.out.println(ex);
                 }
                 return null;
        
    }
    
    public void archiver(HistoriqueLocation ho) {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO historique_location "
                    + "(dateLocation,dateRetour,total,idUser,idOutil) "
                    + "Values (?,?,?,?,?)");
            pt.setDate(1, ho.getDateLocation());
            pt.setDate(2, ho.getDateRetour());
            pt.setInt(3, ho.getTotal());
            pt.setInt(4, ho.getIdUser().getId());
            pt.setInt(5, ho.getIdOutil().getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public XYChart.Series<String,Number> graph(){
          try{
                XYChart.Series<String,Number> serie =new XYChart.Series<>();
                
        Statement st = c.createStatement();
        String req = "SELECT COUNT(h.idOutil),o.Nom FROM historique_location h  JOIN outils o on o.id=h.idOutil GROUP BY h.idOutil;";
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
    
}
