/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SL-WASSIM
 */
public class CategorieOutil {
    private int id;
    private String nom;
    private String logo;

    public CategorieOutil( String nom, String logo) {
        this.nom = nom;
        this.logo = logo;
    }

    public CategorieOutil(int id, String nom, String logo) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
    }
    
    public CategorieOutil() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    
}
