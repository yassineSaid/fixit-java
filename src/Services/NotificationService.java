/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SL-WASSIM
 */
public class NotificationService {
    Connection c = Connexion.getInstance().getCon();
    
    public void ajouterNotification(Notification n) {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO notification (title,description,icon,route,seen) Values (?,?,?,?,?)");
            pt.setString(1, n.getTitle());
            pt.setString(2, n.getDescription());
            pt.setString(3, n.getIcon());
            pt.setString(4, Integer.toString(n.getTelephone()));
            pt.setInt(5, n.getSeen());
            pt.executeUpdate();
            System.out.println("notification ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public ObservableList<Notification> afficherNotification() {
        try {
            ObservableList list = FXCollections.observableArrayList();
            Statement st = c.createStatement();
            String req = "select * from notification order by notification_date DESC";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("id"));
                notification.setTitle(rs.getString("title"));
                notification.setDescription(rs.getString("description"));
                notification.setNotificationDate(rs.getDate("notification_date"));
                notification.setTelephone(Integer.parseInt(rs.getString("route")));
                notification.setIcon(rs.getString("icon"));
                notification.setSeen(rs.getByte("seen"));
                list.add(notification);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("select all notification load");
            System.out.println(ex);
        return null;
        }
    }
    public void modifierSeen(Notification n) {
        try {
            PreparedStatement pt = c.prepareStatement("update notification set  seen=?  where id=? ");
            pt.setInt(1, n.getSeen());
            pt.setInt(2, n.getId());
            pt.execute();
        } catch (SQLException ex) {
        }
    }
    
     public void supprimerNotification(int id) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from notification where id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OutilService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean checkNotification(int nb) {
        try {
            Statement st = c.createStatement();
            String req = "select count(*) AS nb from notification";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt("nb")>nb)
                    return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("select all notification load");
            System.out.println(ex);
        return false;
        }
    }
}
