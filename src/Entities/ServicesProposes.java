/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SELON
 */
public class ServicesProposes {
    private String nom;
    private String categorieService;
    private String description;

    public ServicesProposes() {
    }

    public ServicesProposes(String nom, String categorieService, String description) {
        this.nom = nom;
        this.categorieService = categorieService;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(String categorieService) {
        this.categorieService = categorieService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
