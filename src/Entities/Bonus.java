/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author lenovo
 */
public class Bonus {

    int id;
    User user;
    Date dateAttribution;
    int montant;

    public Bonus(User user, Date dateAttribution, int montant) {
        this.user = user;
        this.dateAttribution = dateAttribution;
        this.montant = montant;
    }

    public Bonus(int id, User user, Date dateAttribution, int montant) {
        this.id = id;
        this.user = user;
        this.dateAttribution = dateAttribution;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateAttribution() {
        return dateAttribution;
    }

    public void setDateAttribution(Date dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

}
