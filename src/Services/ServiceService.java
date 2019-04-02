/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieService;
import Entities.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SELON
 */
public class ServiceService {
    Connection c=Connexion.getInstance().getCon();
    public void afficherService(){
        try{
        PreparedStatement pt=c.prepareStatement("select * from service");
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            System.out.println("Service: id:"+rs.getInt(1)+"nom:"+rs.getString(2)+"description:"+rs.getString(4)+"idCat"+rs.getString(7));
        }
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
    }
    public void ajouterService(Service cs){
        try {
        
         PreparedStatement st=c.prepareStatement("insert into service (nom,visible,description,nbrProviders,idCategorieService) values(?,?,?,?,?)");
        st.setString(1,cs.getNom());
        st.setInt(2,cs.getVisible());
        st.setString(3,cs.getDescription());
        st.setInt(4,cs.getNbrProviders());
        st.setInt(5,cs.getIdCategorieService());
        
        
        st.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        
    }
    public void modifierService(Service cs){
        try{
        PreparedStatement pt=c.prepareStatement("update service set nom=?,description=?,idCategorieService=? where id = ?");
        pt.setString(1,cs.getNom());
        pt.setString(2,cs.getDescription());
        pt.setInt(3,cs.getIdCategorieService());
        pt.setInt(4, cs.getId());
        pt.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        
    }
    public void supprimerService(int id){
          try {
            PreparedStatement pt=c.prepareStatement("delete from service where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
