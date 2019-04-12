/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Time;
import javafx.scene.control.Button;

/**
 *
 * @author Yassine
 */
public class Horraire {

    private Time heureDebut;

    private Time heureFin;

    private int id;

    private int jour;

    private int user;
    
    private Button supprimer;

    public Horraire() {
    }

    public Horraire(Time heureDebut, Time heureFin, int id, int jour, int user,Button supprimer) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.id = id;
        this.jour = jour;
        this.user = user;
        this.supprimer=supprimer;
    }

    public Time getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return this.heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJour() {
        return this.jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getUser() {
        return this.user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }
    
    
}
