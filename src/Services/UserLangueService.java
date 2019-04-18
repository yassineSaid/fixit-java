/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Langue;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;

/**
 *
 * @author Yassine
 */
public class UserLangueService {
    Connection C=Connexion.getInstance().getCon();
    public ObservableList<Langue> getUserLangue(User user)
    {
        ObservableList<Langue> data;
        data=FXCollections.observableArrayList();
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("select id,libelle from user_langue,langue where idUser=? and id=idLangue");
            pt.setInt(1,user.getId());
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                Button supprimer=new Button();
                FontAwesome fs=new FontAwesome();
                Node icon=fs.create(FontAwesome.Glyph.TRASH).color(Color.WHITE);
                icon.setId("icon");
                supprimer.setGraphic(icon);
                supprimer.setText("Supprimer");
                supprimer.getStyleClass().add("supprimer");
                supprimer.setId(rs.getString(1));
                data.add(new Langue(rs.getInt(1),rs.getString(2),supprimer));
            }
        return data;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void supprimerUserLangue(int id,User user)
    {
        try
        {
            PreparedStatement pt=C.prepareStatement("DELETE FROM user_langue WHERE idUser=? AND idLangue=?");
            pt.setInt(1, user.getId());
            pt.setInt(2, id);
            pt.execute();
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }
    public ObservableList<Langue> getLangues(User user)
    {
        try
        {
            PreparedStatement pt = C.prepareStatement("select distinct id,libelle from user_langue,langue where id not in (select idLangue from user_langue where idUser=?)");
            pt.setInt(1, user.getId());
            ResultSet rs=pt.executeQuery();
            ObservableList<Langue> data=FXCollections.observableArrayList();
            while(rs.next()){
                data.add(new Langue(rs.getInt(1),rs.getString(2),null));
            }
            return data;
	} catch (SQLException e) {
            e.printStackTrace();
	}
        return null;
    }
    public void ajouterUserLangue(int id,User user)
    {
        try {
            PreparedStatement pt = C.prepareStatement("INSERT INTO user_langue(idUser,idLangue) VALUES(?,?)");
            pt.setInt(1, user.getId());
            pt.setInt(2, id);
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String languePlusParlee()
    {
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("SELECT count(idUser),idLangue,libelle FROM user_langue,langue WHERE id=idLangue GROUP BY idLangue ORDER BY COUNT(idUser) DESC");
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                return rs.getString("libelle");
            }
        return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
