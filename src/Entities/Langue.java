package Entities;

import java.io.Serializable;
import javafx.scene.control.Button;

public class Langue  {

	private int id;

	private String libelle;
        
        private Button supprimer;

	public Langue() {
	}

        public Langue(int id, String libelle,Button supprimer) {
            this.id = id;
            this.libelle = libelle;
            this.supprimer = supprimer;
        }
        
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

        public Button getSupprimer() {
            return supprimer;
        }

        public void setSupprimer(Button supprimer) {
            this.supprimer = supprimer;
        }
        

        @Override
        public String toString() {
            return this.libelle;
        }
        
}