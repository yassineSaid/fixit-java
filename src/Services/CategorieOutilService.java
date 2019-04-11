/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.*;
import Entities.CategorieOutil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author SL-WASSIM
 */
public class CategorieOutilService {
     Connection c=Connexion.getInstance().getCon();
    public void ajouterCategorie(CategorieOutil C){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO categorie_outils (nom,logo) Values (?,?)");
	            pt.setString(1, C.getNom() );
	            pt.setString(2, C.getLogo());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
    public void modifierCategorie(CategorieOutil C)
    {
        try{
            
        PreparedStatement pt=c.prepareStatement("update categorie_outils set nom=? , logo=? where id=? ");
        pt.setString(1,C.getNom());
        pt.setString(2,C.getLogo());
        pt.setInt(3,C.getId());
        pt.executeUpdate();
        }
        catch(SQLException ex) {}
    }
    public ObservableList<CategorieOutil> afficherCategorie() 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from categorie_outils";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieOutil/"+rs.getString(3), 80, 80, false, false);
            CategorieOutil categorie = new CategorieOutil();
            categorie.setId(rs.getInt(1));
            categorie.setNom(rs.getString(2));
            categorie.setLogo(rs.getString(3));
            categorie.setIm(new ImageView(image1));
            list.add(categorie);
        }
        return list;
    }
    
    
    public void supprimerCategorie(int id)
    {
         try {
            PreparedStatement pt=c.prepareStatement("delete from categorie_outils where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieOutilService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<CategorieOutil> getALLCategorie() {
        try {
            ObservableList<CategorieOutil> list = FXCollections.observableArrayList();
            PreparedStatement pt = c.prepareStatement("Select * from categorie_outils ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                CategorieOutil categorie = new CategorieOutil();
                categorie.setId(rs.getInt("id"));
                categorie.setNom(rs.getString("Nom"));
                list.add(categorie);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    
}
