/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ali Saidani
 */
public class categorie_produit {

    private int id;
    private String Nom;
    private String description;
    private String image;
     public categorie_produit() {
    }

    public categorie_produit(String Nom, String description, String image, int id) {
        this.Nom = Nom;
        this.description = description;
        this.image = image;
           this.id=id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
