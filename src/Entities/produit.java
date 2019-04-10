/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

/**
 *
 * @author Ali Saidani
 */
public class produit {
  private int id;
  private String Nom;
  private int Quantite;
  private  categorie_produit idCategorieProduit;
  private int prix;
  public Timestamp date_exp;
  private User user;
  private String image;
  public produit(){}

    public produit(String Nom, int Quantite, categorie_produit idCategorieProduit, int prix, Timestamp date_exp, User user, String image) {
        this.Nom = Nom;
        this.Quantite = Quantite;
        this.idCategorieProduit = idCategorieProduit;
        this.prix = prix;
        this.date_exp = date_exp;
        this.user = user;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public categorie_produit getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(categorie_produit idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Timestamp getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Timestamp date_exp) {
        this.date_exp = date_exp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
