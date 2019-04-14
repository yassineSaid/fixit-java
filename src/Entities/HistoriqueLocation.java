/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SL-WASSIM
 */
public class HistoriqueLocation implements Serializable {
    private int id;
    private int idUser;
    private int idOutil;
    private Date dateLocation;
    private Date dateRetour;
    private int total;

    public HistoriqueLocation() {
    }

    public HistoriqueLocation(Integer id) {
        this.id = id;
    }

    public HistoriqueLocation(Integer id, int idUser, int idOutil, Date dateLocation, Date dateRetour, int total) {
        this.id = id;
        this.idUser = idUser;
        this.idOutil = idOutil;
        this.dateLocation = dateLocation;
        this.dateRetour = dateRetour;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdOutil() {
        return idOutil;
    }

    public void setIdOutil(int idOutil) {
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
