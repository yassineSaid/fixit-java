/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import java.sql.Connection;
import Gui.AjouterAnnonceController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Annonce;
import Entities.CategorieService;
import Entities.Service;
import Services.Connexion;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnnonceService {
	Connection c = Connexion.getInstance().getCon();
    
    public static AnnonceService sa;

public static AnnonceService getInstance() {
if(sa == null )
sa = new AnnonceService();
return sa;

}


public void ajouterAnnonce(Annonce an) {
    try {
        Statement st = c.createStatement();
        String req = "insert into annonce (description,type,montant,adresse,date,tel,nbr_c,nbr_d,nbr_o,IdUser,IdService,CategorieService) values('" + an.getDescription() + "','" + an.getType()+ "','" + an.getMontant()+ "','"+an.getAdresse() +"','" + an.getDate()  + "' ,'" + an.getTel()  + "','" + an.getNbr_c()  + "','" + an.getNbr_d()  + "','" + an.getNbr_o()  + "','" + an.getIdUser()  + "','" + an.getIdService()  + "','" + an.getCategorieService() +"')";
        st.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println("erreur " + ex.getMessage());
    }
}

public void modifierAnnonce(Annonce an, int id) {
try {
PreparedStatement pt = c.prepareStatement("update annonce set  description = ?,type = ?,montant = ?,adresse = ?,date = ?,tel = ?, nbr_c = ?,nbr_d = ?,nbr_o = ?,IdUser = ?,IdService = ?,CategorieService = ? where id = ? ");


//Date da = Date.valueOf(an.getDate());
pt.setString(1, an.getDescription());
pt.setString(2, an.getType());
pt.setLong(3, an.getMontant());
pt.setString(4, an.getAdresse());
pt.setDate(5, an.getDate());
pt.setInt(6, an.getTel());
pt.setInt(7, an.getNbr_c());
pt.setInt(8, an.getNbr_d());
pt.setInt(9, an.getNbr_o());
pt.setInt(10, an.getIdUser());
pt.setInt(11, an.getIdService());
pt.setInt(12, an.getCategorieService());
pt.setInt(13, id);
pt.executeUpdate();
} catch (SQLException ex) {
System.out.println("erreur " + ex.getMessage());
}
}

public ResultSet afficher(int id) {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from annonce where IdUser != ? AND annonce.date>=CURRENT_DATE() ORDER BY annonce.date ASC;");
    pt.setInt(1, id);
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}
public ResultSet afficherBack() {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from annonce ;");
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}

public ResultSet offres(int id) {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from annonce where type = 'offre' AND annonce.date>=CURRENT_DATE() AND IdUser != ? ORDER BY annonce.date ASC ;");
    pt.setInt(1, id);
    rs = pt.executeQuery();
   

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}
public ResultSet demandes(int id) {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from annonce where type = 'demande' AND annonce.date>=CURRENT_DATE() AND IdUser != ? ORDER BY annonce.date ASC;");
    pt.setInt(1, id);
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}

public ResultSet getCategorie() {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from categorie_service");
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}



public Annonce last() {
    ResultSet rs = null;
    Annonce a = new Annonce();
try {
    PreparedStatement pt = c.prepareStatement("SELECT * FROM Annonce ORDER BY ID DESC LIMIT 1 ");
    rs = pt.executeQuery();
                while (rs.next()){
    a.setId(rs.getInt("id"));
    a.setDescription(rs.getString("Description"));
    a.setType(rs.getString("type"));
    a.setMontant(rs.getLong("montant"));
    a.setAdresse(rs.getString("adresse"));
    a.setDate(rs.getDate("date"));
    a.setTel(rs.getInt("tel"));
    a.setNbr_c(rs.getInt("nbr_c"));
    a.setNbr_d(rs.getInt("nbr_d"));
    a.setNbr_o(rs.getInt("nbr_o"));
    a.setIdUser(rs.getInt("IdUser"));
    a.setIdService(rs.getInt("IdService"));
    a.setCategorieService(rs.getInt("CategorieService"));
                }

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return a;
}



public void supprimerAnnonce(int id) {
try {
PreparedStatement pt = c.prepareStatement("delete from annonce where id = ?");
pt.setInt(1, id);
pt.execute();
} catch (SQLException ex) {
System.out.println("erreur " + ex.getMessage());
}
}

public ResultSet mesAnnonces (int id) {
   ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from annonce where IdUser = ?");
    pt.setInt(1, id);
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs; 
}


public Annonce DetailAnnonce(int id) {
    ResultSet rs = null;
    Annonce a = new Annonce();
try {
    PreparedStatement pt = c.prepareStatement("SELECT * FROM Annonce where id = ? ");
    pt.setInt(1, id);
    rs = pt.executeQuery();
                while (rs.next()){
    a.setId(rs.getInt("id"));
    a.setDescription(rs.getString("Description"));
    a.setType(rs.getString("type"));
    a.setMontant(rs.getLong("montant"));
    a.setAdresse(rs.getString("adresse"));
    a.setDate(rs.getDate("date"));
    a.setTel(rs.getInt("tel"));
    a.setNbr_c(rs.getInt("nbr_c"));
    a.setNbr_d(rs.getInt("nbr_d"));
    a.setNbr_o(rs.getInt("nbr_o"));
    a.setIdUser(rs.getInt("IdUser"));
    a.setIdService(rs.getInt("IdService"));
    a.setCategorieService(rs.getInt("CategorieService"));
    }

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return a;
}

public ResultSet AnnonceUser (int idu) {
   ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from user where id = ?");
    pt.setInt(1, idu);
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs; 
}

 public boolean validTel(String tel) {
        String regex = "^((\\+|00)216)?([2579][0-9]{7}|(3[012]|4[01]|8[0128])[0-9]{6}|42[16][0-9]{5})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    
 }
 public Service getService(int Id)
        {
            try
            {
                PreparedStatement pt=c.prepareStatement("SELECT * from service where id=?");
                pt.setInt(1, Id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
                    Service service = new Service();
            service.setId(rs.getInt(1));
            service.setNom(rs.getString(2));
            service.setDescription(rs.getString(3));
            service.setImage(rs.getString(4));
                    return service;
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return null;     
        }

}

