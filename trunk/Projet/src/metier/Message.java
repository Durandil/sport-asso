package metier;

import java.util.Date;

public class Message {
	
	private String sujet ;
	private String contenu ;
	private String expediteur;
	private Date dateEnvoi ;
	
	public Message(String sujet, String contenu, String expediteur, Date dateEnvoi) {
		super();
		this.sujet = sujet;
		this.contenu = contenu;
		this.expediteur = expediteur;
		this.dateEnvoi = dateEnvoi;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	
	
	
}
