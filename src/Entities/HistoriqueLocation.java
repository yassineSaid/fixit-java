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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
@Table(name = "historique_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriqueLocation.findAll", query = "SELECT h FROM HistoriqueLocation h")
    , @NamedQuery(name = "HistoriqueLocation.findById", query = "SELECT h FROM HistoriqueLocation h WHERE h.id = :id")
    , @NamedQuery(name = "HistoriqueLocation.findByIdUser", query = "SELECT h FROM HistoriqueLocation h WHERE h.idUser = :idUser")
    , @NamedQuery(name = "HistoriqueLocation.findByIdOutil", query = "SELECT h FROM HistoriqueLocation h WHERE h.idOutil = :idOutil")
    , @NamedQuery(name = "HistoriqueLocation.findByDateLocation", query = "SELECT h FROM HistoriqueLocation h WHERE h.dateLocation = :dateLocation")
    , @NamedQuery(name = "HistoriqueLocation.findByDateRetour", query = "SELECT h FROM HistoriqueLocation h WHERE h.dateRetour = :dateRetour")
    , @NamedQuery(name = "HistoriqueLocation.findByTotal", query = "SELECT h FROM HistoriqueLocation h WHERE h.total = :total")})
public class HistoriqueLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @Column(name = "idOutil")
    private int idOutil;
    @Basic(optional = false)
    @Column(name = "dateLocation")
    @Temporal(TemporalType.DATE)
    private Date dateLocation;
    @Basic(optional = false)
    @Column(name = "dateRetour")
    @Temporal(TemporalType.DATE)
    private Date dateRetour;
    @Basic(optional = false)
    @Column(name = "total")
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriqueLocation)) {
            return false;
        }
        HistoriqueLocation other = (HistoriqueLocation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.HistoriqueLocation[ id=" + id + " ]";
    }
    
}
