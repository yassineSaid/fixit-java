package Entities;

import javafx.scene.image.ImageView;

public class CategorieService {
	int id;
	String nom;
	String description;
	String image;
        ImageView im;
	
	public CategorieService() {
		
	}
	
	public CategorieService(String nom,String description) {
		this.nom=nom;
		this.description=description;
	}
        public CategorieService(int id,String nom) {
		this.id=id;
		this.nom=nom;
	}
        public CategorieService(int id,String nom,String description,ImageView im) {
            this.id=id;
            this.nom=nom;
             this.description=description;
             this.im=im;
	}

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
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
        @Override
        public String toString(){
            return nom;
        }

}
