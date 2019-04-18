/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Bonus;
import Entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class bonusService {
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
    
    public boolean getBonusExist(int idUser)
    {
      
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM bonus_token where userreclame=? and MONTH(datearrtibution)=MONTH(SYSDATE())"
                    + " and  YEAR(datearrtibution)=YEAR(SYSDATE())");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public void insertBonus(Bonus bonus)
    {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO bonus_token (userreclame,datearrtibution,montant) Values(?,?,?)");
            pt.setInt(1, bonus.getUser().getId());
            pt.setDate(2, bonus.getDateAttribution());
            pt.setInt(3, bonus.getMontant());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
