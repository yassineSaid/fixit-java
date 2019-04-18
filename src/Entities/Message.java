/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author Yassine
 */
public class Message {
    int id;
    int expediteur,destinataire;
    String contenu;
    boolean vu;
    Timestamp date;

    public Message(int id, int expediteur, int destinataire, boolean vu, Timestamp date,String contenu) {
        this.id = id;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.vu = vu;
        this.date = date;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(int expediteur) {
        this.expediteur = expediteur;
    }

    public int getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(int destinataire) {
        this.destinataire = destinataire;
    }

    public boolean isVu() {
        return vu;
    }

    public void setVu(boolean vu) {
        this.vu = vu;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
    
    
}
