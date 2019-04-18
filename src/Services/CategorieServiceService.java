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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CategorieServiceService {

    Connection c=Connexion.getInstance().getCon();
    public void ajouterCategorie(CategorieService C){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO categorie_Service (nom,description,image_categorie) Values (?,?,?)");
	            pt.setString(1, C.getNom() );
	            pt.setString(2, C.getDescription());
                    pt.setString(3, C.getImage());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
    public void modifierCategorie(CategorieService C)
    {
        try{
            
        PreparedStatement pt=c.prepareStatement("update categorie_Service set nom=?,description=? , image_categorie=? where id=? ");
        pt.setString(1,C.getNom());
        pt.setString(2,C.getDescription());
        pt.setString(3,C.getImage());
        pt.setInt(4,C.getId());
        pt.executeUpdate();
        }
        catch(SQLException ex) {}
    }
    public ObservableList<CategorieService> afficherCategorie() 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from categorie_Service";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/categorieService/"+rs.getString(4), 120, 120, false, false);
            CategorieService categorie = new CategorieService();
            categorie.setId(rs.getInt(1));
            categorie.setNom(rs.getString(2));
            categorie.setDescription(rs.getString(3));
            categorie.setImage(rs.getString(4));
            categorie.setIm(new ImageView(image1));
            list.add(categorie);
        }
        return list;
    }
    
    
    public void supprimerCategorie(int id)
    {
         try {
            PreparedStatement pt=c.prepareStatement("delete from categorie_Service where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<CategorieService> getALLCategorie() {
        try {
            ObservableList<CategorieService> list = FXCollections.observableArrayList();
            PreparedStatement pt = c.prepareStatement("Select * from categorie_Service ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                CategorieService categorie = new CategorieService();
                categorie.setId(rs.getInt("id"));
                categorie.setNom(rs.getString("Nom"));
                list.add(categorie);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    //ajouté par achref
        public static CategorieServiceService su;
        public static CategorieServiceService getInstance() {
        if(su == null )
            su = new CategorieServiceService();
        return su;
            
    }
    public ResultSet affichercategories() {
        ResultSet rs = null;
    try {
        PreparedStatement pt = c.prepareStatement("select * from categorie_service");
        rs = pt.executeQuery();

    } catch (SQLException ex) {
        System.out.println("erreur " + ex.getMessage());
    }
    return rs;
}


}
