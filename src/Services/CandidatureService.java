/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Annonce;
import Entities.Candidature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Achref Bannouri
 */
public class CandidatureService {
    Connection c = Connexion.getInstance().getCon();
    public static CandidatureService sc;
    
    public static CandidatureService getInstance() {
    if(sc == null )
    sc = new CandidatureService();
    return sc;

}
    public ResultSet mesCandidatures(int id) {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from candidature where idUser=?;");
    pt.setInt(1, id);
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}
    
    public void ajouterCandidature(Candidature cn) {
    try {
        Statement st = c.createStatement();
        String req = "insert into candidature (id_annonce,message,etat,email,tel,date,idUser) values('" + cn.getId_annonce() + "','" + cn.getMessage()+ "','" + cn.getEtat()+ "','"+cn.getEmail() +"','" + cn.getTel()  + "' ,'" + cn.getDate()  + "','" + cn.getIdUser()  +"')";
        st.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println("erreur " + ex.getMessage());
    }
}
public void supprimerCandidature(int id) {
try {
PreparedStatement pt = c.prepareStatement("delete from candidature where id = ?");
pt.setInt(1, id);
pt.execute();
} catch (SQLException ex) {
System.out.println("erreur " + ex.getMessage());
}
}

public ResultSet lesCandidatures(int id) {
    ResultSet rs = null;
try {
    PreparedStatement pt = c.prepareStatement("select * from candidature where id_annonce=?;");
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
    PreparedStatement pt = c.prepareStatement("select * from candidature;");
    rs = pt.executeQuery();

} catch (SQLException ex) {
    System.out.println("erreur " + ex.getMessage());
}
return rs;
}

public void ConfirmerCandidature(int idc) {
try {
PreparedStatement pt = c.prepareStatement("update candidature set etat = 'confrimée' where id=? ");
pt.setInt(1,idc);
pt.executeUpdate();
} catch (SQLException ex) {
System.out.println("erreur " + ex.getMessage());
}
}

public void RefuserCandidature(int idc, int ida) {
try {
PreparedStatement pte = c.prepareStatement("update candidature set etat = 'refusée' where NOT (id=?) AND candidature.id_annonce=? ");
pte.setInt(1,idc);
pte.setInt(2,ida);
pte.executeUpdate();
} catch (SQLException ex) {
System.out.println("erreur " + ex.getMessage());
}
}

public void modifierCandidature(Candidature cn, int id) {
try {
PreparedStatement pt = c.prepareStatement("update candidature set  message = ?,email = ?,tel = ? where id = ? ");


//Date da = Date.valueOf(an.getDate());
pt.setString(1, cn.getMessage());
pt.setString(2, cn.getEmail());
pt.setString(3, cn.getTel());
pt.setInt(4, id);
pt.executeUpdate();
} catch (SQLException ex) {
System.out.println("erreur " + ex.getMessage());
}
}
/*public void RealisationService(int ido,int idd,int ids)
{
    try {
        Statement st = c.createStatement();
        String req = "insert into realisation_service () values('" + cn.getId_annonce() + "','" + cn.getMessage()+ "','" + cn.getEtat()+ "','"+cn.getEmail() +"','" + cn.getTel()  + "' ,'" + cn.getDate()  + "','" + cn.getIdUser()  +"')";
        st.executeUpdate(req);
        }
        } catch (SQLException ex) {
        System.out.println("erreur " + ex.getMessage());
    }

}*/
    public boolean validEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validTel(String tel) {
        String regex = "^((\\+|00)216)?([2579][0-9]{7}|(3[012]|4[01]|8[0128])[0-9]{6}|42[16][0-9]{5})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }

}
