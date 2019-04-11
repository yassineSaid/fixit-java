/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Repos;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yassine
 */
public class ReposService {
    Connection C=Connexion.getInstance().getCon();
    public ObservableList<Repos> getUserRepos(User user)
    {
        ObservableList<Repos> data;
        data=FXCollections.observableArrayList();
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("select * from repos where User=?");
            pt.setInt(1,user.getId());
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                data.add(new Repos(rs.getInt("id"),rs.getInt("User")));
            }
        return data;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void ajouterRepos(int jour,User user)
    {
        try {
            PreparedStatement pt = C.prepareStatement("INSERT INTO repos(id,User) VALUES(?,?)");
            pt.setInt(1, jour);
            pt.setInt(2, user.getId());
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimerRepos(int jour,User user)
    {
        try {
            PreparedStatement pt = C.prepareStatement("DELETE FROM repos WHERE id=? AND User=?");
            pt.setInt(1, jour);
            pt.setInt(2, user.getId());
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
