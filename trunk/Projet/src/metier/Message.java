package metier;

import java.sql.Date;


import basededonnees.SGBD;

public class Message {
	
	private String idMessage ;
	private String sujet ;
	private String contenu ;
	private String expediteur;
	private Date dateEnvoi ;
	
	// ce static doit aider à faire un compteur des idMessage
	// A chaque fois qu'un message sera ajouté à la base, on l'incrementera de 1
	// voir méthode ajouterBDD ci-dessous
	private static int compteurIdentifiantMessage=0;
	//private static int compteurIdentifiantMessage=SGBD.recupererIdentifiantDernierEnregistrementTable("MESSAGE", "IDMESSAGE");
	
	public Message(String sujet, String contenu, String expediteur, Date dateEnvoi) {
		super();
		this.sujet = sujet;
		this.contenu = contenu;
		this.expediteur = expediteur;
		this.dateEnvoi = dateEnvoi;
		System.out.println(toString());
		ajouterBDD();
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
	
	
	
	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", sujet=" + sujet
				+ ", contenu=" + contenu + ", expediteur=" + expediteur
				+ ", dateEnvoi=" + dateEnvoi + "]";
	}

	// Méthode permettant d'ajouter un message dans la table MESSAGE sachant l'expéditeur
	// et le nombre de message dans la table MESSAGE ( à améliorer)
	public void ajouterBDD() {
		
		String s = SGBD.transformation(this.dateEnvoi);
		compteurIdentifiantMessage = compteurIdentifiantMessage+1;
		System.out.println(compteurIdentifiantMessage);
		String idMessage=""+compteurIdentifiantMessage;
		
		String requete = "INSERT INTO MESSAGE (IDMESSAGE,SUJETMESSAGE,CONTENUMESSAGE," +
				"IDCLIENT,DATEMESSAGE) VALUES ( '"+ idMessage +"',"
				+ "'"+ this.sujet + "',"
				+ "'"+ this.contenu + "','"
				+ this.expediteur+ "'," 
				+ s+")";
		
		SGBD.executeUpdate(requete);
		
	}
	
	// TODO il faut aussi implémenter la suppression d'un tuple (message) de la MESSAGE
	// ainsi qu'une méthode pour supprimer tous les messages de la table de la base de données
	// et du tableau
	
	public void supprimerBDD(){
		// TODO vérifier si la requete est correcte
		
		String requete = "DELETE FROM MESSAGE WHERE " +
				"IDMESSAGE = " +"'"+ this.idMessage+ "'"+
				" and SUJETMESSAGE = " +"'"+ this.sujet+ "'"+
				" and CONTENUMESSAGE = " +"'"+this.contenu+"'"+
				" and IDCLIENT = " +"'"+this.expediteur+"'"+
				" and DATEMESSAGE = " +"'"+this.dateEnvoi+"'"
		;

		
	}
	
	public static void supprimerAllBDD(){
		String requete = "DELETE FROM MESSAGE";
		
		SGBD.executeUpdate(requete);
	}
	
	
}
