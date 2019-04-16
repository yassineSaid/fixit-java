package Services;

import Entities.Avis;
import Entities.Reclamation;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AvisService {

    Connection c = Connexion.getInstance().getCon();

    public void ajouterAvis(Avis avis) {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO avis (Description,Note,satisfaction,idUser) Values"
                    + " (?,?,?,?)");
            pt.setString(1, avis.getDescription());
            pt.setInt(2, avis.getNote());
            pt.setString(3, avis.getSatisfaction());
            pt.setInt(4, avis.getUser().getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Avis getUserAvis(int userId) {
        try {

            PreparedStatement pt = c.prepareStatement("SELECT * FROM avis where idUser=?");
            pt.setInt(1, userId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Avis avis = new Avis();
                avis.setId(rs.getInt("id"));
                avis.setNote(rs.getInt("note"));
                avis.setSatisfaction(rs.getString("Satisfaction"));
                avis.setDescription(rs.getString("description"));
                System.out.println(avis.getSatisfaction());
                return avis;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void modifierAvis(Avis avis) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE avis set note=?,satisfaction=?,description=? where idUser=?");
            pt.setInt(1, avis.getNote());
            pt.setString(2, avis.getSatisfaction());
            pt.setString(3, avis.getDescription());
            pt.setInt(4, avis.getUser().getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void bonus(int idUser) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE User set solde=solde+25 where id=?");
            pt.setInt(1, idUser);
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public float moyenneNotes() {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT AVG(note) from avis");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public String getUserName(int userId) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from User where id=?");
            pt.setInt(1, userId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "aaa";
    }

    public ObservableList<Avis> top5() {
        try {
            // System.out.println(idUser);
            System.out.println("aaaaaa");
            ObservableList<Avis> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select * From Avis  ORDER BY id DESC LIMIT 5");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("aaaaaa");
                Avis avis = new Avis();
                avis.setId(rs.getInt("id"));
                avis.setDescription(rs.getString("Description"));
                avis.setSatisfaction(rs.getString("satisfaction"));
                avis.setNote(rs.getInt("note"));
                
                User u = new User();
                u.setId(rs.getInt("idUser"));
                u.setUsername(this.getUserName(rs.getInt("idUser")));
                avis.setUser(u);
                list.add(avis);
            }
            return list;
        } catch (SQLException ex) {
                System.out.println(ex);
        }
        return null;
    }

}


