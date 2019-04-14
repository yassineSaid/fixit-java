/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Service;
import Entities.ServicesProposes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author SELON
 */
public class ServicesProposesService {
    Connection c=Connexion.getInstance().getCon();
    public void ajouterService(ServicesProposes C){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO services_proposes (nomService,categorieService,description) Values (?,?,?)");
	            pt.setString(1, C.getNom() );
	            pt.setString(2, C.getCategorieService());
	            pt.setString(3, C.getDescription());
	           
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
    
}
