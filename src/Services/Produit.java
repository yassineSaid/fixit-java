/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ListAchat;
import Entities.User;
import Entities.CategorieProduit;
import Entities.LikeProduit;
import Entities.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ali Saidani
 */
public class Produit {
    

    Connection Cn = Connexion.getInstance().getCon();

    public ObservableList<User> getuserOwner(int idUser) {
        try {
            ObservableList<User> list = FXCollections.observableArrayList();
            // to correct id=11 -> id user connected
            PreparedStatement pt = Cn.prepareStatement("SELECT * FROM user where id=? ");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                list.add(u);
            }
            return list;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;

    }

    public ObservableList<CategorieProduit> getCategorie() {
        try {
            ObservableList<CategorieProduit> list = FXCollections.observableArrayList();
            PreparedStatement pt = Cn.prepareStatement("Select * from categorie_produit ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                CategorieProduit serv = new CategorieProduit();
                serv.setId(rs.getInt("id"));
                serv.setNom(rs.getString("Nom"));
                list.add(serv);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void ajouterProduit(produit p) {
        try {
            String req = "insert into produit (Nom, Quantite, idCategorieProduit, prix, date_exp, user, image) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getQuantite());
            ste.setInt(3, p.getIdCategorieProduit().getId());
            ste.setInt(4, p.getPrix());
            ste.setTimestamp(5, p.getDate_exp());
            ste.setInt(6, p.getUser().getId());
            ste.setString(7, p.getImage());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public ObservableList<produit> getAllMesProduit(int idUser) throws SQLException {
        ObservableList l = FXCollections.observableArrayList();
        PreparedStatement pt = Cn.prepareStatement("SELECT * FROM produit where user=? ");
        pt.setInt(1, idUser);
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + rs.getString(5), 120, 120, false, false);
            produit c = new produit();
            c.setId(rs.getInt("id"));
            c.setNom(rs.getString("Nom"));
            c.setQuantite(rs.getInt("quantite"));
            c.setPrix(rs.getInt("prix"));
            c.setDate_exp(rs.getTimestamp("date_exp"));
            c.setImage(rs.getString("image"));
            c.setIm(new ImageView(image1));
            l.add(c);
        }
        return l;
    }

    public void supprimerProduit(produit p) {
        try {
            String req = "DELETE from produit where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, p.getId());

            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void modifierProduit(produit p) {
        try {
            String req = "UPDATE produit set Nom=?, Quantite=?, idCategorieProduit=?, prix=?, date_exp=?, user=? ,image=? where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getQuantite());
            ste.setInt(3, p.getIdCategorieProduit().getId());
            ste.setInt(4, p.getPrix());
            ste.setTimestamp(5, p.getDate_exp());
            ste.setInt(6, p.getUser().getId());
            ste.setString(7, p.getImage());
            ste.setInt(8, p.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<produit> getAllProduit() {
        try {
            ObservableList<produit> list = FXCollections.observableArrayList();
            PreparedStatement pt = Cn.prepareStatement("Select * from produit ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + rs.getString(5), 120, 120, false, false);

                Button supprimer = new Button();
                supprimer.setText("Detaille");
                supprimer.setId(rs.getString(1));
                produit p = new produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("Nom"));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix(rs.getInt("prix"));
                p.setDate_exp(rs.getTimestamp("date_exp"));
                p.setImage(rs.getString("image"));
                p.setDetaille(supprimer);
                p.setIm(new ImageView(image1));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public produit DetailleProduit(int idProd) {
        try {
            PreparedStatement pt = Cn.prepareStatement("SELECT * FROM produit where id=? ");
            pt.setInt(1, idProd);
            ResultSet rs = pt.executeQuery();
            produit c = new produit();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + rs.getString(5), 120, 120, false, false);
                Button supprimer = new Button();
                supprimer.setText("Acheter");
                supprimer.setId(rs.getString(1));
                c.setIdProd(rs.getInt("id"));
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("Nom"));
                c.setQuantite(rs.getInt("quantite"));
                c.setPrix(rs.getInt("prix"));
                c.setDate_exp(rs.getTimestamp("date_exp"));
                c.setImage(rs.getString("image"));
                User u = this.getUser(rs.getInt("user"));
                c.setUser(u);
                c.setDetaille(supprimer);
                c.setIm(new ImageView(image1));
            }

            return c;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public int verifierQuantite(int id) {

        try {
            String req = "Select * from produit where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            produit c = new produit();
            while (rs.next()) {
                Button supprimer = new Button();
                supprimer.setText("Acheter");
                supprimer.setId(rs.getString(1));
                c.setIdProd(rs.getInt("id"));
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("Nom"));
                c.setQuantite(rs.getInt("quantite"));
                c.setPrix(rs.getInt("prix"));
                c.setDate_exp(rs.getTimestamp("date_exp"));
                c.setImage(rs.getString("image"));
                c.setDetaille(supprimer);
            }
            return c.getQuantite();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public void AcheterProduit(produit p, int quantite) {
        try {
            String req = "UPDATE produit set Quantite=? where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, quantite);
            ste.setInt(2, p.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int verifierSolde(int idUsr) {
        try {
            String req = "Select * from user where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, idUsr);
            ResultSet rs = ste.executeQuery();
            User c = new User();
            while (rs.next()) {
                c.setSolde(rs.getInt("solde"));
            }

            return c.getSolde();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return 0;
    }

    public void ModifierUserAchat(int idUsr1, int prix) {
        try {
            String req = "Update user set solde=? where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, prix);
            ste.setInt(2, idUsr1);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public User getUser(int idUsr) {
        try {
            String req = "Select * from user where id=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, idUsr);
            ResultSet rs = ste.executeQuery();
            User c = new User();
            while (rs.next()) {
                c.setSolde(rs.getInt("solde"));
                c.setId(rs.getInt("Id"));
            }

            return c;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    public void ajouterAchat(ListAchat l) {
        try {
            String req = "insert into achat_produit (Produit, Acheteur, quantite, prix, idAcheteur, idProduit, image, date) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, l.getNom());
            ste.setString(2, l.getAcheteur());
            ste.setInt(3, l.getQuantite());
            ste.setInt(4, l.getPrix());
            ste.setInt(5, l.getIdAcheteur());
            ste.setInt(6, l.getIdProduit());
            ste.setString(7, l.getImage());
            ste.setTimestamp(8, l.getDate());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public ObservableList<produit> Disponibleroduit() {
        try {
            ObservableList<produit> list = FXCollections.observableArrayList();
            PreparedStatement pt = Cn.prepareStatement("Select * from produit where quantite > 0 ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + rs.getString(5), 120, 120, false, false);

                Button supprimer = new Button();
                supprimer.setText("Detaille");
                supprimer.setId(rs.getString(1));
                produit p = new produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("Nom"));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix(rs.getInt("prix"));
                p.setDate_exp(rs.getTimestamp("date_exp"));
                p.setImage(rs.getString("image"));
                p.setDetaille(supprimer);
                p.setIm(new ImageView(image1));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ObservableList<produit> NonDisponibleroduit() {
        try {
            ObservableList<produit> list = FXCollections.observableArrayList();
            PreparedStatement pt = Cn.prepareStatement("Select * from produit where quantite = 0 ");

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Image image1 = new Image("file:/wamp64/www/fixit/web/uploads/images/produit/" + rs.getString(5), 120, 120, false, false);

                Button supprimer = new Button();
                supprimer.setText("Detaille");
                supprimer.setId(rs.getString(1));
                produit p = new produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("Nom"));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix(rs.getInt("prix"));
                p.setDate_exp(rs.getTimestamp("date_exp"));
                p.setImage(rs.getString("image"));
                p.setDetaille(supprimer);
                p.setIm(new ImageView(image1));
                list.add(p);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void ajouterLike(LikeProduit l) {
        try {

            String req = "insert into produit_like (produit, user) VALUES (?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, l.getProduit());
            ste.setInt(2, l.getUser());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public int NombreLike(int idProd) {
        int i=0;

        try {
            String req = "Select * from produit_like where produit=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, idProd);
            ResultSet rs = ste.executeQuery();
            LikeProduit c = new LikeProduit();
            while (rs.next()) {
                i++;
            }
            return i;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return 0;
    }
     public int LikeExiste(int idUser, int idProd) {
        int i=0;

        try {
            String req = "Select * from produit_like where user=? and produit=?";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, idUser);
            ste.setInt(2, idProd);
            ResultSet rs = ste.executeQuery();
            LikeProduit c = new LikeProduit();
            while (rs.next()) {
                i++;
                System.out.println(i);
            }
            return i;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return 0;
    }
}
