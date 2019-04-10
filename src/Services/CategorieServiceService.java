package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.CategorieService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

public class CategorieServiceService {

    Connection c=Connexion.getInstance().getCon();
    public List<CategorieService> afficherCategorie(){
        List<CategorieService> cc=new ArrayList<>();
        try{
        PreparedStatement pt=c.prepareStatement("select * from categorie_Service");
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            System.out.println("cat: id:"+rs.getInt(1)+"nom:"+rs.getString(2)+"description:"+rs.getString(3));
            CategorieService catS=new CategorieService(
                    rs.getString(2),
                    rs.getString(3)
            );
            cc.add(catS);
                
        }
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        return cc;
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
    public ObservableList<String> listeCate(){
         ObservableList<String> cc=FXCollections.observableArrayList();
        try{
        PreparedStatement pt=c.prepareStatement("select DISTINCT nom from categorie_Service");
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
           
            cc.add(rs.getString("nom"));
               return cc; 
        }
        }
        catch(SQLException ex){
            System.out.println("erreur: "+ex.getMessage());
        }
        return null;
        
    }
    public void rechercherCategorie(String nom)
    {
        try{
            
        List<CategorieService> cc=new ArrayList<>();
            PreparedStatement pt=c.prepareStatement("select * from categorie_Service where nom like %?%");
            pt.setString(1, nom);
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
            System.out.println("cat: id:"+rs.getInt(1)+"nom:"+rs.getString(2)+"description:"+rs.getString(3));
            CategorieService catS=new CategorieService(
                    rs.getString(2),
                    rs.getString(3)
            );
            cc.add(catS);
                
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


}
