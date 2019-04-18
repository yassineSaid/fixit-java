package Entities;

import java.sql.Date;

public class Annonce {
	private int id;
	private String description;
	private String type;
	private long montant;
	private int tel;
	private String adresse;
	private Date date;
	private int nbr_c;
	private int nbr_o;
	private int nbr_d;
	private int IdUser;
	private int IdService;
	private int CategorieService ;
        private String nomservice;
	
	public Annonce() {	
	}
	
	public Annonce(int nbr_o, int nbr_d) {
		this.nbr_o = nbr_o;
		this.nbr_d = nbr_d;
	}

	public Annonce(String description, String type, long montant, String adresse, Date date, int tel,
			   int nbr_c , int nbr_d,int nbr_o ,int IdUser, int IdService, int CategorieService) {
		this.description = description;
		this.type = type;
		this.montant = montant;
		this.adresse=adresse;
		this.date= date;
		this.tel = tel;
		this.nbr_c=nbr_c;
		this.nbr_o=nbr_o;
		this.nbr_d=nbr_d;
                this.IdUser=IdUser;
		this.CategorieService = CategorieService;
		this.IdService= IdService;
		
	}
	public Annonce(int id, String description, String type, long montant,String adresse, Date date, int tel, int nbr_c, int nbr_d, int nbr_o,
			int IdUserint, int IdService, int CategorieService   ) {
		this.id = id;
		this.description = description;
		this.type = type;
		this.montant = montant;
		this.tel = tel;
		this.adresse=adresse;
		this.date= date;
		this.nbr_c=nbr_c;
		this.nbr_o=nbr_o;
		this.nbr_d=nbr_d;
		this.IdUser=IdUser;
		this.CategorieService = CategorieService;
		this.IdService= IdService;
		
	}

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", description=" + description + ", type=" + type + ", montant=" + montant + ", tel=" + tel + ", adresse=" + adresse + ", date=" + date + ", nbr_c=" + nbr_c + ", nbr_o=" + nbr_o + ", nbr_d=" + nbr_d + ", IdUser=" + IdUser + ", IdService=" + IdService + ", CategorieService=" + CategorieService + '}';
    }






	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMontant() {
		return montant;
	}
	public void setMontant(long montant) {
		this.montant = montant;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNbr_c() {
		return nbr_c;
	}

	public void setNbr_c(int nbr_c) {
		this.nbr_c = nbr_c;
	}

	public int getNbr_o() {
		return nbr_o;
	}

	public void setNbr_o(int nbr_o) {
		this.nbr_o = nbr_o;
	}

	public int getNbr_d() {
		return nbr_d;
	}

	public void setNbr_d(int nbr_d) {
		this.nbr_d = nbr_d;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int idUser) {
		IdUser = idUser;
	}

	public int getIdService() {
		return IdService;
	}

	public void setIdService(int idService) {
		IdService = idService;
	}

	public int getCategorieService() {
		return CategorieService;
	}

	public void setCategorieService(int categorieService) {
		CategorieService = categorieService;
	}

    public String getNomservice() {
        return nomservice;
    }

    public void setNomservice(String nomservice) {
        this.nomservice = nomservice;
    }

    public Annonce(String description, String type, long montant, int tel, String adresse, Date date, int nbr_c, int nbr_o, int nbr_d, int IdUser, int IdService, int CategorieService, String nomservice) {
        this.description = description;
        this.type = type;
        this.montant = montant;
        this.tel = tel;
        this.adresse = adresse;
        this.date = date;
        this.nbr_c = nbr_c;
        this.nbr_o = nbr_o;
        this.nbr_d = nbr_d;
        this.IdUser = IdUser;
        this.IdService = IdService;
        this.CategorieService = CategorieService;
        this.nomservice = nomservice;
    }
 
}
