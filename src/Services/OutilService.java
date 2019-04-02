/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author SL-WASSIM
 */
import java.sql.Connection;
import java.sql.*;
import Entities.Outil;
import java.util.logging.Level;
import java.util.logging.Logger;
public class OutilService {
     Connection c=Connexion.getInstance().getCon();
    public void ajouterOutil(Outil O){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO outils "
                            + "(Nom,Quantite,dureeMaximale,prix,adresse,codePostal,ville,image,idCategorieOutils) "
                            + "Values (?,?,?,?,?,?,?,?,?)");
	            pt.setString(1, O.getNom() );
	            pt.setInt(2, O.getQuantite());
                    pt.setInt(3, O.getDureeMaximale());
                    pt.setInt(4, O.getPrix());
                    pt.setString(5, O.getAdresse());
                    pt.setInt(6, O.getCodePostal());
                    pt.setString(7, O.getVille());
                    pt.setString(8, O.getImage());
                    pt.setInt(9, O.getIdCategorieOutils());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
    public void modifierOutil(Outil O)
    {
        try{
        PreparedStatement pt=c.prepareStatement("update outils set "
                + "Nom=? , Quantite=? ,dureeMaximale=? , prix=? , adresse=? , codePostal=? , ville=? , image=? "
                + ", idCategorieOutils=? where id=? ");
                    pt.setString(1, O.getNom() );
	            pt.setInt(2, O.getQuantite());
                    pt.setInt(3, O.getDureeMaximale());
                    pt.setInt(4, O.getPrix());
                    pt.setString(5, O.getAdresse());
                    pt.setInt(6, O.getCodePostal());
                    pt.setString(7, O.getVille());
                    pt.setString(8, O.getImage());
                    pt.setInt(9, O.getIdCategorieOutils());
                    pt.setInt(10, O.getId());
                    pt.execute();
        }
        catch(SQLException ex) {}
    }
    public void afficherOutil()
    {
        try{
            PreparedStatement pt=c.prepareStatement("select * from outils");
            ResultSet rs=pt.executeQuery();
            while (rs.next())
            {
                System.out.println("Outil id: "+rs.getInt(1)+" nom: "+rs.getString(2)+" prix: "+rs.getString(5));
            }
        }
        catch(SQLException ex) {}
    }
    
    public void supprimerOutil(int id)
    {
         try {
            PreparedStatement pt=c.prepareStatement("delete from outils where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OutilService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
