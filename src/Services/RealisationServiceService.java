/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.RealisationService;
import Entities.Service;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class RealisationServiceService {

    Connection c = Connexion.getInstance().getCon();

    public User getUser(int idUser) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM user where id=?");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                return u;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Service getService(int idService) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM Service where id=?");
            pt.setInt(1, idService);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("Nom"));
                s.setImage(rs.getString("image_service"));
                return s;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ObservableList<RealisationService> getServiceRealise(int idUser) {
        try {
            ObservableList<RealisationService> list = FXCollections.observableArrayList();
            PreparedStatement pt = c.prepareStatement("SELECT * FROM realisation_service where idUserDemandeur=?");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User u = getUser(idUser);
                User u1 = getUser(rs.getInt("idUserOffreur"));
                Service s = getService(rs.getInt("idservice"));
                RealisationService r = new RealisationService(rs.getInt("id"), s, u1, u, rs.getDate("dateRealisation"), rs.getInt("note"));
                list.add(r);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void setNoteRealisationService(int note, int idRealisation) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE realisation_service set note=? where id=?");
            pt.setInt(1, note);
            pt.setInt(2, idRealisation);
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public RealisationService getNote(int idrealisation)
    {
       try {
            
            PreparedStatement pt = c.prepareStatement("SELECT * FROM realisation_service where id=?");
            pt.setInt(1, idrealisation);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User u = getUser(rs.getInt("idUserDemandeur"));
                User u1 = getUser(rs.getInt("idUserOffreur"));
                Service s = getService(rs.getInt("idservice"));
                RealisationService r = new RealisationService(rs.getInt("id"), s, u1, u, rs.getDate("dateRealisation"), rs.getInt("note"));
                return r;
            }
          
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public float getAvgNoteUser(int idUser)
    {
        try {
            
            PreparedStatement pt = c.prepareStatement("SELECT AVG(note) FROM realisation_service where idUserOffreur=? and MONTH(dateRealisation)=MONTH(SYSDATE())"
                    + " and  YEAR(dateRealisation)=YEAR(SYSDATE())");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getFloat("AVG(note)");
            }
          
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }
    public int[] getEmployeeoftheMonth()
    {
         int[] tab = {0,1};
        try {
           
            PreparedStatement pt = c.prepareStatement("SELECT MAX(avgn) a,q.idUserOffreur FROM ( SELECT AVG(note) avgn,idUserOffreur FROM realisation_service where MONTH(dateRealisation)=MONTH(SYSDATE()) and YEAR(dateRealisation)=YEAR(SYSDATE())) q");
           
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                tab[0]=rs.getInt("a");
                tab[1]= rs.getInt("idUserOffreur");
                return tab;
            }
          
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
