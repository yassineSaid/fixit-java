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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<ServicesProposes> afficherServicesProposes() 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from services_proposes";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
           
             ServicesProposes serv = new ServicesProposes();
            serv.setId(rs.getInt(1));
            serv.setNom(rs.getString(2));
            serv.setCategorieService(rs.getString(3));
            serv.setDescription(rs.getString(4));
            list.add(serv);
        }
        return list;
    }
    public void supprimerServicePropose(int id)
    {
         try {
            PreparedStatement pt=c.prepareStatement("delete from services_proposes where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesProposesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
