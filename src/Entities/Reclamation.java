package Entities;

import java.sql.Date;

public class Reclamation 
{
    private int id;
    private String objet;
    private String description;
    private User userReclame;
    private User userReclamant;
    private Date dateReclamation;
    private int seen;
    private int traite;
    private int archive;
    private Service idServiceRealise;
    private Date dateRealisation;
    

    public Reclamation(String objet, String description, User userReclame, User userReclamant, Date dateReclamation, int seen, int traite, int archive, Service idServiceRealise, Date dateRealisation) {
        this.objet = objet;
        this.description = description;
        this.userReclame = userReclame;
        this.userReclamant = userReclamant;
        this.dateReclamation = dateReclamation;
        this.seen = seen;
        this.traite = traite;
        this.archive = archive;
        this.idServiceRealise = idServiceRealise;
        this.dateRealisation = dateRealisation;
    }

    public Reclamation(int id, String objet, String description, User userReclame, User userReclamant, Date dateReclamation, int seen, int traite, int archive, Service idServiceRealise, Date dateRealisation) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.userReclame = userReclame;
        this.userReclamant = userReclamant;
        this.dateReclamation = dateReclamation;
        this.seen = seen;
        this.traite = traite;
        this.archive = archive;
        this.idServiceRealise = idServiceRealise;
        this.dateRealisation = dateRealisation;
    }	
	
    public Reclamation() {
         //To change body of generated methods, choose Tools | Templates.
    }

	   @Override
    public String toString() {
        return dateReclamation + "\n" + objet + "\n" + description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getTraite() {
        return traite;
    }

    public void setTraite(int traite) {
        this.traite = traite;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }



    public Date getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public User getUserReclame() {
        return userReclame;
    }

    public void setUserReclame(User userReclame) {
        this.userReclame = userReclame;
    }

    public User getUserReclamant() {
        return userReclamant;
    }

    public void setUserReclamant(User userReclamant) {
        this.userReclamant = userReclamant;
    }

    public Service getIdServiceRealise() {
        return idServiceRealise;
    }

    public void setIdServiceRealise(Service idServiceRealise) {
        this.idServiceRealise = idServiceRealise;
    }
    
    

	
	
}
