package Services;

import Entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvisService 
{
    	 Connection c= Connexion.getInstance().getCon();
         public void ajouterAvis(Avis avis)
         {
              try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO avis (Description,Note,satisfaction,idUser) Values"
                            + " (?,?,?,?)");
	            pt.setString(1, avis.getDescription() );
	            pt.setInt(2, avis.getNote());
	            pt.setString(3,avis.getSatisfaction());
	            pt.setInt(4, avis.getUser().getId());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
         }
         
         public Avis getUserAvis(int userId)
         {
             try
	        {
                    
	            PreparedStatement pt=c.prepareStatement("SELECT * FROM avis where idUser=?");
	            pt.setInt(1, userId);
	            ResultSet rs=pt.executeQuery();
                    while(rs.next())
                    {
                        Avis avis = new Avis();
                        avis.setId(rs.getInt("id"));
                        avis.setNote(rs.getInt("note"));
                        avis.setSatisfaction(rs.getString("Satisfaction"));
                        avis.setDescription(rs.getString("description"));
                        System.out.println(avis.getSatisfaction());
                        return avis;
                    }
                    
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
             return null;  
         }
         
         public void modifierAvis(Avis avis)
         {
             try
	        {
	            PreparedStatement pt=c.prepareStatement("UPDATE avis set note=?,satisfaction=?,description=? where idUser=?");
	            pt.setString(1, avis.getDescription() );
	            pt.setInt(2, avis.getNote());
	            pt.setString(3,avis.getSatisfaction());
	            pt.setInt(4, avis.getUser().getId());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
         }

}
