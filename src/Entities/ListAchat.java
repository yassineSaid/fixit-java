/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author Ali Saidani
 */
public class ListAchat {

    private String Nom;
    private String Acheteur, image;
    private int id, Quantite, prix, idAcheteur, idProduit;

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getAcheteur() {
        return Acheteur;
    }

    public void setAcheteur(String Acheteur) {
        this.Acheteur = Acheteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ListAchat() {
    }

    
    public ListAchat(String Nom, String Acheteur, String image, int Quantite, int prix, int idAcheteur, int idProduit, Timestamp date) {
        this.Nom = Nom;
        this.Acheteur = Acheteur;
        this.image = image;
        this.Quantite = Quantite;
        this.prix = prix;
        this.idAcheteur = idAcheteur;
        this.idProduit = idProduit;
        this.date = date;
    }
    public Timestamp date;

}
