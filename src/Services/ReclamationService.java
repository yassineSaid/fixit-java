package Services;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReclamationService {

    Connection c = Connexion.getInstance().getCon();

    public ObservableList<User> getusersreclamer(int idUser) {
        try {
            ObservableList<User> list = FXCollections.observableArrayList();
            // to correct id=11 -> id user connected
            PreparedStatement pt = c.prepareStatement("SELECT * FROM user "
                    + "where id in "
                    + "(select DISTINCT idUserOffreur from realisation_service where idUserDemandeur=?) ");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFirstname(rs.getString("firstname"));
                u.setLastname(rs.getString("lastname"));
                list.add(u);
            }
            return list;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;

    }

    public ObservableList<Service> getServiceuserreclamer(String userName) {
        try {
            ObservableList<Service> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select * from service s join realisation_service r on r.idservice=s.id "
                    + "where idUserOffreur=(select id from User where username =?)");
            pt.setString(1, userName);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Service serv = new Service();
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

    public ObservableList<String> getDateuserreclamer(String userName, String serviceName) {
        try {
            ObservableList<String> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select DateRealisation from realisation_service "
                    + "WHERE idUserOffreur=(SELECT id FROM user WHERE username=?) "
                    + "and idservice=(select id from service WHERE Nom=?)");
            pt.setString(1, userName);
            pt.setString(2, serviceName);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                list.add(new String(rs.getString(1)));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void ajouterRaclamation(Reclamation rec) {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO reclamation (Object,Description,userreclame,DateReclamation,"
                    + "user,idServiceRealise,dateRealisation,seen,traite,archive) Values (?,?,?,?,?,?,?,?,?,?)");
            pt.setString(1, rec.getObjet());
            pt.setString(2, rec.getDescription());
            pt.setInt(3, rec.getUserReclame().getId());
            pt.setDate(4, rec.getDateReclamation());
            pt.setInt(5, rec.getUserReclamant().getId());
            pt.setInt(6, rec.getIdServiceRealise().getId());
            pt.setDate(7, rec.getDateRealisation());
            pt.setInt(8, rec.getSeen());
            pt.setInt(9, rec.getTraite());
            pt.setInt(10, rec.getArchive());
            System.out.println(rec.toString());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<Reclamation> afficherReclamation(int idUser) {
        try {
           // System.out.println(idUser);
            ObservableList<Reclamation> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select * From Reclamation where user = ?");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setObjet(rs.getString("Object"));
                rec.setDescription(rs.getString("Description"));

                User u = new User();
                u.setId(rs.getInt("userreclame"));
                System.out.println(rs.getInt("userreclame"));
                u.setUsername(this.getUserName(rs.getInt("userreclame")));
                u.setFirstname(this.getUserName(rs.getInt("userreclame")));
                u.setLastname(this.getUserName(rs.getInt("userreclame")));
                System.out.println(u);
                rec.setUserReclame(u);

                User u1 = new User();
                u1.setId(rs.getInt("user"));
                u1.setUsername(this.getUserName(u1.getId()));
                u1.setFirstname(this.getUserName(rs.getInt("user")));
                u1.setLastname(this.getUserName(rs.getInt("user")));
                rec.setUserReclamant(u1);

                rec.setDateReclamation(rs.getDate("DateReclamation"));
                rec.setSeen(rs.getInt("seen"));
                rec.setTraite(rs.getInt("Traite"));
                rec.setArchive(rs.getInt("archive"));
                rec.setDateRealisation(rs.getDate("dateRealisation"));
                Service serv = new Service();
                serv.setId(rs.getInt("idServiceRealise"));
                serv.setNom(this.getServiceName(serv.getId()));
                rec.setIdServiceRealise(serv);
                list.add(rec);
            }
            return list;
        } catch (SQLException ex) {

        }
        return null;
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
    
     public String getUserEmail(int userId) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from User where id=?");
            pt.setInt(1, userId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "aaa";
    }
     public String getUserFirstName(int userId) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from User where id=?");
            pt.setInt(1, userId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getString("firstName");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "aaa";
    }
      public String getUserLastName(int userId) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * from User where id=?");
            pt.setInt(1, userId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getString("lastname");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "aaa";
    }

    public String getServiceName(int serviceId) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT Nom from Service where id=?");
            pt.setInt(1, serviceId);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getString("Nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "aaa";
    }

    public void modifierReclamation(Reclamation rec) {

        try {
            PreparedStatement pt = c.prepareStatement("UPDATE Reclamation set Object=?,Description=?,userreclame=?,DateReclamation=?,user=?,"
                    + "idServiceRealise=?,dateRealisation=?,seen=?,traite=?,archive=? where id=?");

            pt.setString(1, rec.getObjet());
            pt.setString(2, rec.getDescription());
            pt.setInt(3, rec.getUserReclame().getId());
            pt.setDate(4, rec.getDateReclamation());
            pt.setInt(5, rec.getUserReclamant().getId());
            pt.setInt(6, rec.getIdServiceRealise().getId());
            pt.setDate(7, rec.getDateRealisation());
            pt.setInt(8, rec.getSeen());
            pt.setInt(9, rec.getTraite());
            pt.setInt(10, rec.getArchive());
            pt.setInt(11, rec.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void supprimerReclamation(int recId) {
        try {
            PreparedStatement pt = c.prepareStatement("Delete from Reclamation where id = ?");
            pt.setInt(1, recId);
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<Reclamation> getAllReclamation() {
        try {
            ObservableList<Reclamation> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select * From Reclamation");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setObjet(rs.getString("Object"));
                rec.setDescription(rs.getString("Description"));

                User u = new User();
                u.setId(rs.getInt("userreclame"));
                u.setUsername(this.getUserName(rs.getInt("userreclame")));
                u.setEmail(this.getUserEmail(rs.getInt("userreclame")));
                u.setFirstname(this.getUserName(rs.getInt("userreclame")));
                u.setLastname(this.getUserName(rs.getInt("userreclame")));
                rec.setUserReclame(u);

                User u1 = new User();
                u1.setId(rs.getInt("user"));
                u1.setUsername(this.getUserName(u1.getId()));
                u1.setEmail(this.getUserEmail(rs.getInt("user")));
                u1.setFirstname(this.getUserName(rs.getInt("user")));
                u1.setLastname(this.getUserName(rs.getInt("user")));
                rec.setUserReclamant(u1);

                rec.setDateReclamation(rs.getDate("DateReclamation"));
                rec.setSeen(rs.getInt("seen"));
                rec.setTraite(rs.getInt("Traite"));
                rec.setArchive(rs.getInt("archive"));
                rec.setDateRealisation(rs.getDate("dateRealisation"));
                Service serv = new Service();
                serv.setId(rs.getInt("idServiceRealise"));
                serv.setNom(this.getServiceName(serv.getId()));
                rec.setIdServiceRealise(serv);
                list.add(rec);
            }
            return list;
        } catch (SQLException ex) {

        }
        return null;
    }

    public ObservableList<Reclamation> getReclamationArchive() {
        try {
            ObservableList<Reclamation> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select * From Reclamation where archive=1");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setObjet(rs.getString("Object"));
                rec.setDescription(rs.getString("Description"));

                User u = new User();
                u.setId(rs.getInt("userreclame"));
                System.out.println(rs.getInt("userreclame"));
                u.setUsername(this.getUserName(rs.getInt("userreclame")));
                rec.setUserReclame(u);

                User u1 = new User();
                u1.setId(rs.getInt("user"));
                u1.setUsername(this.getUserName(u1.getId()));
                rec.setUserReclamant(u1);

                rec.setDateReclamation(rs.getDate("DateReclamation"));
                rec.setSeen(rs.getInt("seen"));
                rec.setTraite(rs.getInt("Traite"));
                rec.setArchive(rs.getInt("archive"));
                rec.setDateRealisation(rs.getDate("dateRealisation"));
                Service serv = new Service();
                serv.setId(rs.getInt("idServiceRealise"));
                serv.setNom(this.getServiceName(serv.getId()));
                rec.setIdServiceRealise(serv);
                list.add(rec);
            }
            return list;
        } catch (SQLException ex) {

        }
        return null;
    }

    public ObservableList<Reclamation> getReclamationNonTraite() {
        try {
            ObservableList<Reclamation> list = FXCollections.observableArrayList();
            // to correct idUserDemandeur -> idUserOffreur
            PreparedStatement pt = c.prepareStatement("Select * From Reclamation where traite=0");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setObjet(rs.getString("Object"));
                rec.setDescription(rs.getString("Description"));

                User u = new User();
                u.setId(rs.getInt("userreclame"));
                System.out.println(rs.getInt("userreclame"));
                u.setUsername(this.getUserName(rs.getInt("userreclame")));
                rec.setUserReclame(u);

                User u1 = new User();
                u1.setId(rs.getInt("user"));
                u1.setUsername(this.getUserName(u1.getId()));
                rec.setUserReclamant(u1);

                rec.setDateReclamation(rs.getDate("DateReclamation"));
                rec.setSeen(rs.getInt("seen"));
                rec.setTraite(rs.getInt("Traite"));
                rec.setArchive(rs.getInt("archive"));
                rec.setDateRealisation(rs.getDate("dateRealisation"));
                Service serv = new Service();
                serv.setId(rs.getInt("idServiceRealise"));
                serv.setNom(this.getServiceName(serv.getId()));
                rec.setIdServiceRealise(serv);
                list.add(rec);
            }
            return list;
        } catch (SQLException ex) {

        }
        return null;
    }

    public void archiverReclamation(Reclamation rec) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE Reclamation set archive=1 where id=?");
            pt.setInt(1, rec.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void traiterReclamation(Reclamation rec) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE Reclamation set traite=1 where id=?");
            pt.setInt(1, rec.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     public void vuReclamation(Reclamation rec) {
        try {
            PreparedStatement pt = c.prepareStatement("UPDATE Reclamation set seen=1 where id=?");
            pt.setInt(1, rec.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     
     public int countReclamationUser(int idUser)
     {
          try {
            PreparedStatement pt = c.prepareStatement("Select count(*) From Reclamation where userreclame=?");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
            
        } catch (SQLException ex) {

        }
        return -1;
     }
    
    

}
