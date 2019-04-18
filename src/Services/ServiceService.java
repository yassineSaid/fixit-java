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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author SELON
 */
public class ServiceService {
    Connection c=Connexion.getInstance().getCon();
    public void ajouterService(Service C){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO service (nom,visible,description,nbrProviders,image_service,idCategorieService) Values (?,?,?,?,?,?)");
	            pt.setString(1, C.getNom() );
	            pt.setInt(2, C.getVisible());
	            pt.setString(3, C.getDescription());
	            pt.setInt(4, C.getNbrProviders());
	            pt.setString(5, C.getImage());
                    pt.setInt(6, C.getCategorie().getId());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
    public void modifierService(Service C)
    {
        try{
            
        PreparedStatement pt=c.prepareStatement("update service set nom=?,description=? , image_service=? , idCategorieService=?,visible=? where id=? ");
        pt.setString(1,C.getNom());
        pt.setString(2,C.getDescription());
        pt.setString(3,C.getImage());
        pt.setInt(4,C.getCategorie().getId());
        pt.setInt(5,C.getVisible());
        pt.setInt(6,C.getId());
        pt.executeUpdate();
        }
        catch(SQLException ex) {}
    }
    public ObservableList<Service> afficherService() 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from service where visible=1";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/service/"+rs.getString("image_service"), 120, 120, false, false);
             Service serv = new Service();
            serv.setId(rs.getInt("id"));
            serv.setNom(rs.getString("nom"));
            serv.setDescription(rs.getString("description"));
            serv.setNbrProviders(rs.getInt("NbrProviders"));
            serv.setImage(rs.getString("image_service"));
            CategorieService categorie = this.getCategorieService(rs.getInt("idCategorieService"));
            serv.setCategorie(categorie);
            serv.setIm(new ImageView(image1));
            list.add(serv);
        }
        return list;
    }
    
    
    public void supprimerService(int id)
    {
         try {
            PreparedStatement pt=c.prepareStatement("delete from service where id=?");
            pt.setInt(1,id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public ObservableList<Service> getAllServiceC(int id) {
        try {
            ObservableList<Service> list = FXCollections.observableArrayList();
            PreparedStatement pt = c.prepareStatement("Select * from service where idCategorieService = ? ");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/service/"+rs.getString("image_service"), 120, 120, false, false);
             Service serv = new Service();
            serv.setId(rs.getInt("id"));
            serv.setNom(rs.getString("nom"));
            serv.setDescription(rs.getString("description"));
            serv.setNbrProviders(rs.getInt("NbrProviders"));
            serv.setImage(rs.getString("image_service"));
            CategorieService categorie = this.getCategorieService(rs.getInt("idCategorieService"));
            serv.setCategorie(categorie);
            serv.setIm(new ImageView(image1));
                list.add(serv);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
      public CategorieService getCategorieService(int Id)
        {
            try
            {
                PreparedStatement pt=c.prepareStatement("SELECT * from categorie_Service where id=?");
                pt.setInt(1, Id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
                    CategorieService categorie = new CategorieService();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
            categorie.setDescription(rs.getString("description"));
            categorie.setImage(rs.getString("image_categorie"));
                    return categorie;
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return null;     
        }
     public ObservableList<Service> afficherServiceHistorique() 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from service where visible=0";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/service/"+rs.getString(6), 120, 120, false, false);
             Service serv = new Service();
            serv.setId(rs.getInt("id"));
            serv.setNom(rs.getString("nom"));
            serv.setDescription(rs.getString("description"));
            serv.setNbrProviders(rs.getInt("NbrProviders"));
            serv.setImage(rs.getString("image_service"));
            CategorieService categorie = this.getCategorieService(rs.getInt("idCategorieService"));
            serv.setCategorie(categorie);
            serv.setIm(new ImageView(image1));
            list.add(serv);
        }
        return list;
    }
        public XYChart.Series<String,Number> graph(){
          try{
                XYChart.Series<String,Number> serie =new XYChart.Series<>();
                
        Statement st = c.createStatement();
        String req = "select count(s.id),c.nom from service s,categorie_Service c where s.idCategorieService=c.id group by s.idCategorieService;";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        
            serie.getData().add(new XYChart.Data<>(rs.getString(2),rs.getInt(1)));
        }
        return serie;
        }
          catch(SQLException ex){
              System.out.println(ex);
          }
          return null;
          }
        
        //ajouté par Achref
        public static ServiceService su;

    public static ServiceService getInstance() {
        if(su == null )
            su = new ServiceService();
        return su;
            
    }
    public ResultSet afficherservices() {
        ResultSet rs = null;
    try {
        PreparedStatement pt = c.prepareStatement("select * from service");
        rs = pt.executeQuery();

    } catch (SQLException ex) {
        System.out.println("erreur " + ex.getMessage());
    }
    return rs;
}
       public ResultSet nomservices(int id) {
        ResultSet rs = null;
    try {
        PreparedStatement pt = c.prepareStatement("select * from service where id =?");
        pt.setInt(1,id);
        rs = pt.executeQuery();

    } catch (SQLException ex) {
        System.out.println("erreur " + ex.getMessage());
    }
    return rs;
}
             public ObservableList<Service> serviceAccueil() {
        try {
            ObservableList list = FXCollections.observableArrayList();
            Statement st = c.createStatement();
            String req = "select * from service where nbrProviders>0";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/service/"+rs.getString(6), 120, 120, false, false);
                Service serv = new Service();
                serv.setId(rs.getInt(1));
                serv.setNom(rs.getString(2));
                serv.setDescription(rs.getString(4));
                serv.setNbrProviders(rs.getInt(5));
                serv.setImage(rs.getString(6));
                CategorieService categorie = this.getCategorieService(rs.getInt(7));
                serv.setCategorie(categorie);
                serv.setIm(new ImageView(image1));
                list.add(serv);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       
       
}
