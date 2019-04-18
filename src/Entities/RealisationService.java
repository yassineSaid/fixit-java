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
public class RealisationService {

    int id;
    Service service;
    User userOffreur;
    User userDemandeur;
    Date dateRealisation;
    int note;

    public RealisationService(int id, Service service, User userOffreur, User userDemandeur, Date dateRealisation, int note) {
        this.id = id;
        this.service = service;
        this.userOffreur = userOffreur;
        this.userDemandeur = userDemandeur;
        this.dateRealisation = dateRealisation;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUserOffreur() {
        return userOffreur;
    }

    public void setUserOffreur(User userOffreur) {
        this.userOffreur = userOffreur;
    }

    public User getUserDemandeur() {
        return userDemandeur;
    }

    public void setUserDemandeur(User userDemandeur) {
        this.userDemandeur = userDemandeur;
    }

    public Date getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

}
