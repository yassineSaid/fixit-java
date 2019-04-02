package Services;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReclamationService 
{
	 Connection c= Connexion.getInstance().getCon();
	public ObservableList<String> getusersreclamer(int idUser) 
	 {
		 try
		 {
			 ObservableList<String> list = FXCollections.observableArrayList();
			 // to correct id=11 -> id user connected
			 PreparedStatement pt=c.prepareStatement("SELECT username FROM user "
			 		+ "where id in "
			 		+ "(select DISTINCT idUserOffreur from realisation_service where idUserDemandeur=?) ");
			 pt.setInt(1, idUser);
	         ResultSet rs= pt.executeQuery();
	         while(rs.next())
	         {
	        	 list.add(new String(rs.getString(1)));
	        	 
	         }
	         return list;
			 
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ex);
		 }
		return null;
		
		
	 }
	 
	 public ObservableList<String> getServiceuserreclamer(String userName)
	 {
		try
		{
			 ObservableList<String> list = FXCollections.observableArrayList();
			 // to correct idUserDemandeur -> idUserOffreur
			 PreparedStatement pt=c.prepareStatement("Select Nom from service s join realisation_service r on r.idservice=s.id "
			 		+ "where idUserOffreur=(select id from User where username =?)");
			 pt.setString(1, userName);
	         ResultSet rs= pt.executeQuery();
	         while(rs.next())
	         {
	        	 list.add(new String(rs.getString(1)));
	         }
			 return list;
		}
		
		catch(SQLException ex)
		{
			
		}
		return null;
	 }
	 
	 public ObservableList<String> getDateuserreclamer(String userName,String serviceName)
	 {
		 try
			{
				 ObservableList<String> list = FXCollections.observableArrayList();
				 // to correct idUserDemandeur -> idUserOffreur
				 PreparedStatement pt=c.prepareStatement("Select DateRealisation from realisation_service "
				 		+ "WHERE idUserOffreur=(SELECT id FROM user WHERE username=?) "
				 		+ "and idservice=(select id from service WHERE Nom=?)");
				 pt.setString(1, userName);
				 pt.setString(2, serviceName);
		         ResultSet rs= pt.executeQuery();
		         while(rs.next())
		         {
		        	 list.add(new String(rs.getString(1)));
		         }
				 return list;
			}
			
			catch(SQLException ex)
			{
				System.out.println(ex);
			}
			return null;
	 }
	 
	 public void ajouterRaclamation(Reclamation rec)
	 {
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO reclamation (Object,Description,userreclame,DateReclamation,"
	            		+ "user,idServiceRealise,dateRealisation,seen,traite,archive) Values (?,?,?,?,?,?,?,?,?,?)");
	            pt.setString(1, rec.getObjet() );
	            pt.setString(2, rec.getDescription());
	            pt.setInt(3, rec.getUserReclame());
	            pt.setDate(4, rec.getDateReclamation());
	            pt.setInt(5, rec.getUserReclamant());
	            pt.setInt(6, rec.getIdServiceRealise());
	            pt.setDate(7, rec.getDateRealisation());
	            pt.setInt(8, rec.getSeen());
	            pt.setInt(9, rec.getTraite());
	            pt.setInt(10, rec.getArchive());
	            System.out.println(rec.toString());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
	  }
	 
	 public int RecupereridUser(String userName)
	 {
		 try
		 {
			 int userId;
			 PreparedStatement pt=c.prepareStatement("SELECT id FROM user WHERE username=?");
			 pt.setString(1, userName);
	         ResultSet rs= pt.executeQuery();
	         while(rs.next())
	         {
	        	 userId=rs.getInt(1);
	        	 return userId;
	         }
	        
			
		 }
		 catch(SQLException ex)
		 {
			 
		 }
		 return 0;
		 
	 }
	 
	 
	 public int RecupereridService(String serviceName)
	 {
		 try
		 {
			 int serviceId;
			 PreparedStatement pt=c.prepareStatement("SELECT id FROM service WHERE NOM=?");
			 pt.setString(1, serviceName);
	         ResultSet rs= pt.executeQuery();
	         while(rs.next())
	         {
	        	 serviceId=rs.getInt(1);
	        	 return serviceId;
	         }
		 }
		 catch(SQLException ex)
		 {
			 
		 }
		 return -1;	 
	 }
        public ObservableList<Reclamation> getDateuserreclamer()
        {
            try
            {
                Reclamation rec = new Reclamation();
                ObservableList<Reclamation> list = FXCollections.observableArrayList();
		// to correct idUserDemandeur -> idUserOffreur
		PreparedStatement pt=c.prepareStatement("Select * From Reclamation");
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
                    rec.setObjet(rs.getString("Object"));
                    rec.setDescription(rs.getString("Description"));
                    rec.setUserReclame(rs.getInt("userreclame"));
                    rec.setUserReclamant(rs.getInt("user"));
                    rec.setDateReclamation(rs.getDate("DateReclamation"));
                    rec.setSeen(rs.getInt("seen"));
                    rec.setTraite(rs.getInt("Traite"));
                    rec.setArchive(rs.getInt("archive"));
                    rec.setDateRealisation(rs.getDate("datRealisation"));
                    rec.setIdServiceRealise(rs.getInt("idServiceRealise"));
                    
                    list.add(rec);
                }
                 return list;
            }
		
            catch(SQLException ex)
            {
			
            }
            return null;
        }
         
         
	 
}
