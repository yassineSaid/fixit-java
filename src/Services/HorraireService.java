/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Horraire;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
public class HorraireService {
    Connection C=Connexion.getInstance().getCon();
    public ObservableList<Horraire> getUserHorraire(User user,int jour)
    {
        ObservableList<Horraire> data;
        data=FXCollections.observableArrayList();
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("select * from horraire where User=? and jour=?");
            pt.setInt(1,user.getId());
            pt.setInt(2,jour);
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                Button supprimer=new Button();
                FontAwesome fs=new FontAwesome();
                Node icon=fs.create(FontAwesome.Glyph.TRASH).color(Color.WHITE).size(10);
                icon.setId("icon");
                supprimer.setGraphic(icon);
                supprimer.getStyleClass().add("supprimer");
                supprimer.setMinHeight(0);
                supprimer.setMaxHeight(20);
                supprimer.setId(rs.getString(1));
                data.add(new Horraire(rs.getTime("heureDebut"),rs.getTime("heureFin"),rs.getInt("id"),rs.getInt("jour"),user.getId(),supprimer));
            }
        return data;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void supprimerHorraire(int id)
    {
        try
        {
            PreparedStatement pt=C.prepareStatement("DELETE FROM horraire WHERE id=?");
            pt.setInt(1, id);
            pt.execute();
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }
    public void supprimerJour(int jour,User user)
    {
        try
        {
            PreparedStatement pt=C.prepareStatement("DELETE FROM horraire WHERE jour=? AND User=?");
            pt.setInt(1, jour);
            pt.setInt(2, user.getId());
            pt.execute();
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }
    public void ajouterHorraire(User user,Time heureDebut,Time heureFin,int jour)
    {
        try {
            PreparedStatement pt = C.prepareStatement("INSERT INTO horraire(jour,heureDebut,heureFin,User) VALUES(?,?,?,?)");
            pt.setInt(1, jour);
            pt.setTime(2, heureDebut);
            pt.setTime(3, heureFin);
            pt.setInt(4, user.getId());
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkHorraire(User user,Time heureDebut,Time heureFin,int jour)
    {
        PreparedStatement pt;
        try {
            pt = C.prepareStatement("select * from horraire where User=? and jour=?");
            pt.setInt(1,user.getId());
            pt.setInt(2,jour);
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                if (rs.getTime("heureDebut").before(heureDebut) && rs.getTime("heureFin").after(heureDebut)) return false;
                if (rs.getTime("heureDebut").before(heureFin) && rs.getTime("heureFin").after(heureFin)) return false;
                if (heureDebut.before(rs.getTime("heureDebut")) && heureFin.after(rs.getTime("heureDebut"))) return false;
                if (heureDebut.before(rs.getTime("heureFin")) && heureFin.after(rs.getTime("heureFin"))) return false;
            }
        return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserLangueService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
