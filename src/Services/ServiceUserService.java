/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Service;
import Entities.ServiceUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author SELON
 */
public class ServiceUserService {
    
    Connection c=Connexion.getInstance().getCon();
    public ObservableList<ServiceUser> afficherServiceUser(int id) 
             {
        try
            {                    
                ObservableList<ServiceUser> list = FXCollections.observableArrayList();

                PreparedStatement pt=c.prepareStatement("SELECT * from service_user where idUser=?");
                pt.setInt(1, id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
            ServiceUser su = new ServiceUser();
            su.setDescription(rs.getString("description"));
            su.setPrix(rs.getFloat("prix"));
            su.setIdUser(rs.getInt("idUser"));
            su.setIdService(rs.getInt("idService"));
            list.add(su);
                   
                }
                return list;
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return null;  
    }
    public String  getServiceName(int id) throws SQLException
    {
       String list ="";

                PreparedStatement pt=c.prepareStatement("SELECT nom from service where id=?");
                pt.setInt(1, id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
            Service su = new Service();
            su.setNom(rs.getString("nom"));        
            list+=rs.getString("nom");
                   
                }
                return list;
    }
      public String  getServiceImage(int id) throws SQLException
    {
                String list ="";

                PreparedStatement pt=c.prepareStatement("SELECT image_service from service where id=?");
                pt.setInt(1, id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
            Service su = new Service();
            su.setNom(rs.getString("image_service"));        
            list+=rs.getString("image_service");
                   
                }
                return list;
    }
    public void ajouterServiceUser(ServiceUser C){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO service_user (description,prix,idUser,idService) Values (?,?,?,?)");
	            pt.setString(1, C.getDescription());
	            pt.setFloat(2, C.getPrix());
	            pt.setInt(3, C.getIdUser());
	            pt.setInt(4, C.getIdService());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
     public void supprimerServiceUser(int idS,int idU)
    {
         try {
            PreparedStatement pt=c.prepareStatement("delete from service_user where idService=? and idUser=?");
            pt.setInt(1,idS);
            pt.setInt(2,idU);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
       public ObservableList<Integer>  getActifUser () throws SQLException
    {
                String list ="";
                
                ObservableList<Integer> liste = FXCollections.observableArrayList();
                PreparedStatement pt=c.prepareStatement("SELECT idUser,Count(*) from service_user group by idUser order by count(*) desc");
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
            //ServiceUser su = new ServiceUser();  
           // su.set
            liste.addAll(rs.getInt(1),rs.getInt(2));
            
                return liste;
            //list+=rs.getInt(1);
                   
                }
                return null;
    }
    
}
