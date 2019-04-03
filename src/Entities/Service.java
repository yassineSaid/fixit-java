package Entities;

public class Service {
    
private int id;
private String nom;
private int visible;    
private String description;
private String image;
private int nbrProviders;
private int idCategorieService;

    public Service() {
        
    }
    public Service(String nom, int visible, String description, int nbrProviders, int idCategorieService) {
        this.nom=nom;
        this.visible = visible;
        this.description = description;
        this.nbrProviders = nbrProviders;
        this.idCategorieService = idCategorieService;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the visible
     */
    public int getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(int visible) {
        this.visible = visible;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the nbrProviders
     */
    public int getNbrProviders() {
        return nbrProviders;
    }

    /**
     * @param nbrProviders the nbrProviders to set
     */
    public void setNbrProviders(int nbrProviders) {
        this.nbrProviders = nbrProviders;
    }

    /**
     * @return the idCategorieService
     */
    public int getIdCategorieService() {
        return idCategorieService;
    }

    /**
     * @param idCategorieService the idCategorieService to set
     */
    public void setIdCategorieService(int idCategorieService) {
        this.idCategorieService = idCategorieService;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
