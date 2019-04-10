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
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author SELON
 */
public class ServiceService {
    Connection c=Connexion.getInstance().getCon();
    public ObservableList<Service> afficherService(){
        try{
         ObservableList<Service> list=FXCollections.observableArrayList();
        PreparedStatement pt=c.prepareStatement("select * from service where visible = 1");
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            System.out.println("Service: id:"+rs.getInt(1)+"nom:"+rs.getString(2)+"description:"+rs.getString(4)+"idCat"+rs.getString(7));
            Service s=new Service();
            s.setId(rs.getInt("id"));
            s.setNom(rs.getString("nom"));
            s.setDescription(rs.getString("description"));
            s.setIdCategorieService(rs.getInt("idCategorieService"));
            s.setNbrProviders(rs.getInt("NbrProviders"));
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/service/"+rs.getString("image_service"), 200, 100, false, false);
            s.setIm(new ImageView(image1));
            
            list.add(s);
        }
        return list;
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        return null;
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
