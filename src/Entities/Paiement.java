/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Yassine
 */
public class Paiement {

    private Date datePaiement;

    private int id;

    private int idUser;

    private double montant;

    private int nombreScoin;

    private String stripeToken;

    public Paiement() {
    }

    public Paiement(Date datePaiement, int id, int idUser, double montant, int nombreScoin, String stripeToken) {
        this.datePaiement = datePaiement;
        this.id = id;
        this.idUser = idUser;
        this.montant = montant;
        this.nombreScoin = nombreScoin;
        this.stripeToken = stripeToken;
    }

    public Date getDatePaiement() {
        return this.datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getNombreScoin() {
        return this.nombreScoin;
    }

    public void setNombreScoin(int nombreScoin) {
        this.nombreScoin = nombreScoin;
    }

    public String getStripeToken() {
        return this.stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }
}
