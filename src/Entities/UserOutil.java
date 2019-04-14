/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SL-WASSIM
 */

public class UserOutil implements Serializable {
    private Date dateLocation;
    @Basic(optional = false)
    @Column(name = "dateRetour")
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    @Basic(optional = false)
    @Column(name = "total")
    private int total;
    @JoinColumn(name = "idOutil", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Outil outil;
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserOutil() {
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

    public Outil getOutils3() {
        return outil;
    }

    public void setOutils3(Outil outil) {
        this.outil = outil;
    }

    public User getUser3() {
        return user;
    }

    public void setUser3(User user) {
        this.user = user;
    }

   

    

   
}
