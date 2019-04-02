package Entities;

public class CategorieService {
	int id;
	String nom;
	String description;
	String image;
	
	public CategorieService() {
		
	}
	
	public CategorieService(int id,String nom,String description) {
		this.id=id;
		this.nom=nom;
		this.description=description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
