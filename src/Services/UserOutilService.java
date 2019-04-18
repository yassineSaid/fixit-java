/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieOutil;
import Entities.Outil;
import Entities.User;
import Entities.UserOutil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author SL-WASSIM
 */
public class UserOutilService {

    Connection c = Connexion.getInstance().getCon();

    public Outil getOutil(int Id) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from outils where id=?");
            pt.setInt(1, Id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/" + rs.getString("image"), 150, 150, false, false);
                Outil outil = new Outil();
                outil.setId(rs.getInt("id"));
                outil.setNom(rs.getString("Nom"));
                outil.setQuantite(rs.getInt("Quantite"));
                outil.setDureeMaximale(rs.getInt("dureeMaximale"));
                outil.setPrix(rs.getInt("prix"));
                outil.setAdresse(rs.getString("adresse"));
                outil.setCodePostal(rs.getInt("codePostal"));
                outil.setVille(rs.getString("ville"));
                outil.setImage(rs.getString("image"));
                outil.setIm(new ImageView(image1));
                OutilService categorieService = new OutilService();
                CategorieOutil categorie = categorieService.getCategorieOutil(rs.getInt("idCategorieOutils"));
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
    
    public void inserer(UserOutil uo) {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO user_outil "
                    + "(dateLocation,dateRetour,total,idUser,idOutil) "
                    + "Values (?,?,?,?,?)");
            pt.setDate(1, uo.getDateLocation());
            pt.setDate(2, uo.getDateRetour());
            pt.setInt(3, uo.getTotal());
            pt.setInt(4, uo.getUser().getId());
            pt.setInt(5, uo.getOutil().getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     public ObservableList<UserOutil> mesOutils(User user) 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        PreparedStatement pt = c.prepareStatement("SELECT * FROM user_outil  where idUser=?");
            pt.setInt(1, user.getId());
            ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            UserOutil uo = new UserOutil();
            User u = new User();
            u=this.getUser(rs.getInt(4));
            Outil o = new Outil();
            o=this.getOutil(rs.getInt(5));
            uo.setDateLocation(rs.getDate(1));
            uo.setDateRetour(rs.getDate(2));
            uo.setTotal(rs.getInt(3));
            uo.setUser(u);
            uo.setOutil(o);
            list.add(uo);
        }
        return list;
    }
     public ObservableList<UserOutil> premierOutilRetourner(Outil outil) 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        PreparedStatement pt = c.prepareStatement("SELECT* FROM user_outil where idOutil=? ORDER BY dateRetour ");
            pt.setInt(1, outil.getId());
            ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            UserOutil uo = new UserOutil();
            User u = new User();
            u=this.getUser(rs.getInt(4));
            Outil o = new Outil();
            o=this.getOutil(rs.getInt(5));
            uo.setDateLocation(rs.getDate(1));
            uo.setDateRetour(rs.getDate(2));
            uo.setTotal(rs.getInt(3));
            uo.setUser(u);
            uo.setOutil(o);
            list.add(uo);
        }
        return list;
    }
     
     public ObservableList<UserOutil> verifierLocation(Outil outil,User user) 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        PreparedStatement pt = c.prepareStatement("SELECT * FROM user_outil where idOutil=? and idUser=?");
            pt.setInt(1, outil.getId());
            pt.setInt(2, user.getId());
            ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            UserOutil uo = new UserOutil();
            User u = new User();
            u=this.getUser(rs.getInt(4));
            Outil o = new Outil();
            o=this.getOutil(rs.getInt(5));
            uo.setDateLocation(rs.getDate(1));
            uo.setDateRetour(rs.getDate(2));
            uo.setTotal(rs.getInt(3));
            uo.setUser(u);
            uo.setOutil(o);
            list.add(uo);
        }
        return list;
    }
     
     public ObservableList<UserOutil> afficherOutil() 
             {
                 try
                 {
                     ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from user_outil";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            UserOutil uo = new UserOutil();
            User u = new User();
            u=this.getUser(rs.getInt(4));
            Outil o = new Outil();
            o=this.getOutil(rs.getInt(5));
            uo.setDateLocation(rs.getDate(1));
            uo.setDateRetour(rs.getDate(2));
            uo.setTotal(rs.getInt(3));
            uo.setUser(u);
            uo.setOutil(o);
            list.add(uo);
        }
        return list;
                 }
                 catch(SQLException ex)
                 {
                     System.out.println(ex);
                 }
                 return null;
        
    }
     public ObservableList<UserOutil> afficherOutilFront(User user) 
             {
                 try
                 {
                     ObservableList list = FXCollections.observableArrayList();
        PreparedStatement pt = c.prepareStatement("SELECT * FROM user_outil  where idUser=?");
            pt.setInt(1, user.getId());
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            UserOutil uo = new UserOutil();
            User u = new User();
            u=this.getUser(rs.getInt(4));
            Outil o = new Outil();
            o=this.getOutil(rs.getInt(5));
             Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/"+o.getImage(), 150, 150, false, false);
            uo.setDateLocation(rs.getDate(1));
            uo.setDateRetour(rs.getDate(2));
            uo.setTotal(rs.getInt(3));
            uo.setUser(u);
            uo.setOutil(o);
            uo.setIm(new ImageView(image1));
            list.add(uo);
        }
        return list;
                 }
                 catch(SQLException ex)
                 {
                     System.out.println(ex);
                 }
                 return null;
        
    }
     
     public void supprimerUserOutil(UserOutil uo) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from user_outil where idUser=? and idOutil=?");
            pt.setInt(1, uo.getUser().getId());
            pt.setInt(2, uo.getOutil().getId());
            pt.execute();
        } catch (SQLException ex) {
            System.out.println("teeeest");
            Logger.getLogger(OutilService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
