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
        pt.execute();
        }
        catch(SQLException ex) {}
    }
    public void afficherCategorie()
    {
        try{
            PreparedStatement pt=c.prepareStatement("select * from categorie_outils");
            ResultSet rs=pt.executeQuery();
            while (rs.next())
            {
                System.out.println("Categorie id: "+rs.getInt(1)+"nom: "+rs.getString(2));
            }
        }
        catch(SQLException ex) {}
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
    
}