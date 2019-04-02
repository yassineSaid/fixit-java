package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.CategorieService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieServiceService {

    Connection c=Connexion.getInstance().getCon();
    public void afficherCategorie(){
        try{
        PreparedStatement pt=c.prepareStatement("select * from categorie_Service");
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            System.out.println("cat: id:"+rs.getInt(1)+"nom:"+rs.getString(2)+"description:"+rs.getString(3));
        }
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
    }
    public void ajouterCategorie(CategorieService cs){
        try {
        
         PreparedStatement st=c.prepareStatement("insert into categorie_Service (nom,description) values(?,?)");
        st.setString(1,cs.getNom());
        st.setString(2,cs.getDescription());
        
        st.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        
    }
    public void modifierCategorie(CategorieService cs){
        try{
        PreparedStatement pt=c.prepareStatement("update categorie_Service set nom=?,description=? where id = ?");
        pt.setString(1,cs.getNom());
        pt.setString(2,cs.getDescription());
        pt.setInt(3, cs.getId());
        pt.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        
    }
    public void supprimerCategorie(int id){
          try {
            PreparedStatement pt=c.prepareStatement("delete from categorie_Service where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
