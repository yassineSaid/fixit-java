/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author SL-WASSIM
 */
public class HistoriqueLocation implements Serializable {
    private int id;
    private User idUser;
    private Outil idOutil;
    private Date dateLocation;
    private Date dateRetour;
    private int total;

    public HistoriqueLocation() {
    }

    public HistoriqueLocation(int id, User idUser, Outil idOutil, Date dateLocation, Date dateRetour, int total) {
        this.id = id;
        this.idUser = idUser;
        this.idOutil = idOutil;
        this.dateLocation = dateLocation;
        this.dateRetour = dateRetour;
        this.total = total;
    }

    public HistoriqueLocation(User idUser, Outil idOutil, Date dateLocation, Date dateRetour, int total) {
        this.idUser = idUser;
        this.idOutil = idOutil;
        this.dateLocation = dateLocation;
        this.dateRetour = dateRetour;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Outil getIdOutil() {
        return idOutil;
    }

    public void setIdOutil(Outil idOutil) {
        this.idOutil = idOutil;
    }

    public Date getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(Date dateLocation) {
        this.dateLocation = dateLocation;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    


   

    

    
    
}
