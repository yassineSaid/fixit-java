package Entities;

import java.io.Serializable;
import javafx.scene.control.Button;

public class UserLangue  {
	private int idLangue;

	private int idUser;
        
        private Button supprimer;

	public UserLangue() {
	}

        public UserLangue(int idLangue, int idUser, Button supprimer) {
            this.idLangue = idLangue;
            this.idUser = idUser;
            this.supprimer = supprimer;
        }

	public int getIdLangue() {
		return this.idLangue;
	}

	public void setIdLangue(int idLangue) {
		this.idLangue = idLangue;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

        public Button getSupprimer() {
            return supprimer;
        }

        public void setSupprimer(Button supprimer) {
            this.supprimer = supprimer;
        }

    @Override
    public String toString() {
        return "UserLangue{" + "idLangue=" + idLangue + ", idUser=" + idUser + '}';
    }
        
}