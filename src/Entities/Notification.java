/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author SL-WASSIM
 */
public class Notification {
    
	private int id;
	private String title;
        private String description;
	private String icon;
	private Date notificationDate;
	private byte seen;

    public Notification() {
    }

    public Notification(int id, String title, String description, String icon, Date notificationDate, byte seen) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.notificationDate = notificationDate;
        this.seen = seen;
    }

    public Notification(String title, String description, String icon, Date notificationDate, byte seen) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.notificationDate = notificationDate;
        this.seen = seen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public byte getSeen() {
        return seen;
    }

    public void setSeen(byte seen) {
        this.seen = seen;
    }
        
        
}
