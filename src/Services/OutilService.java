/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author SL-WASSIM
 */
import Entities.CategorieOutil;
import java.sql.Connection;
import java.sql.*;
import Entities.Outil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OutilService {

    Connection c = Connexion.getInstance().getCon();

    public void ajouterOutil(Outil O) {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO outils "
                    + "(Nom,Quantite,dureeMaximale,prix,adresse,codePostal,ville,image,idCategorieOutils) "
                    + "Values (?,?,?,?,?,?,?,?,?)");
            pt.setString(1, O.getNom());
            pt.setInt(2, O.getQuantite());
            pt.setInt(3, O.getDureeMaximale());
            pt.setInt(4, O.getPrix());
            pt.setString(5, O.getAdresse());
            pt.setInt(6, O.getCodePostal());
            pt.setString(7, O.getVille());
            pt.setString(8, O.getImage());
            pt.setInt(9, O.getC().getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void modifierOutil(Outil O) {
        try {
            PreparedStatement pt = c.prepareStatement("update outils set "
                    + "Nom=? , Quantite=? ,dureeMaximale=? , prix=? , adresse=? , codePostal=? , ville=? , image=? "
                    + ", idCategorieOutils=? where id=? ");
            pt.setString(1, O.getNom());
            pt.setInt(2, O.getQuantite());
            pt.setInt(3, O.getDureeMaximale());
            pt.setInt(4, O.getPrix());
            pt.setString(5, O.getAdresse());
            pt.setInt(6, O.getCodePostal());
            pt.setString(7, O.getVille());
            pt.setString(8, O.getImage());
            pt.setInt(9, O.getC().getId());
            pt.setInt(10, O.getId());
            pt.execute();
        } catch (SQLException ex) {
        }
    }
    public CategorieOutil getCategorieOutil(int Id)
        {
            try
            {
                PreparedStatement pt=c.prepareStatement("SELECT * from categorie_outils where id=?");
                pt.setInt(1, Id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
                    CategorieOutil categorie = new CategorieOutil();
            categorie.setId(rs.getInt(1));
            categorie.setNom(rs.getString(2));
            categorie.setLogo(rs.getString(3));
                    return categorie;
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return null;     
        }

    public ObservableList<Outil> afficherOutil() 
            throws SQLException {
        ObservableList list = FXCollections.observableArrayList();
        Statement st = c.createStatement();
        String req = "select * from outils";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/Outil/"+rs.getString(9), 150, 150, false, false);
            Outil outil = new Outil();
            outil.setId(rs.getInt(1));
            outil.setNom(rs.getString(2));
            outil.setQuantite(rs.getInt(3));
            outil.setDureeMaximale(rs.getInt(4));
            outil.setPrix(rs.getInt(5));
            outil.setAdresse(rs.getString(6));
            outil.setCodePostal(rs.getInt(7));
            outil.setVille(rs.getString(8));
            outil.setImage(rs.getString(9));
            outil.setIm(new ImageView(image1));
            CategorieOutil categorie = this.getCategorieOutil(rs.getInt(10));
            outil.setC(categorie);
            outil.setNomCategorie(categorie.getNom());
            
            list.add(outil);
        }
        return list;
    }

    public void supprimerOutil(int id) {
        try {
            PreparedStatement pt = c.prepareStatement("delete from outils where id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OutilService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
