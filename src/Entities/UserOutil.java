/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SL-WASSIM
 */

public class UserOutil implements Serializable {
    private Date dateLocation;
    private Date dateRetour;
    private int total;
    private Outil outil;
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
