package metier;

import java.sql.Date;
import java.util.ArrayList;


import basededonnees.SGBD;

public class Message {
	
	private String idMessage ;
	private String sujet ;
	private String contenu ;
	private String expediteur;
	private Date dateEnvoi ;
	private boolean estEnvoyeAuGerant ;
	
//	// ce static doit aider à faire un compteur des idMessage
//	// A chaque fois qu'un message sera ajouté à la base, on l'incrementera de 1
//	// voir méthode ajouterBDD ci-dessous
//	private static int compteurIdentifiantMessage=0;
//	//private static int compteurIdentifiantMessage=SGBD.recupererIdentifiantDernierEnregistrementTable("MESSAGE", "IDMESSAGE");
//	
	public Message(String sujet, String contenu, String expediteur, Date dateEnvoi,boolean messageEnvoye) {
		super();
		this.sujet = sujet;
		this.contenu = contenu;
		this.expediteur = expediteur;
		this.dateEnvoi = dateEnvoi;
		this.estEnvoyeAuGerant = messageEnvoye ;
		System.out.println(toString());
		ajouterBDD();
	}
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	
	
	public boolean isEstEnvoye() {
		return estEnvoyeAuGerant;
	}

	public void setEstEnvoye(boolean estEnvoye) {
		this.estEnvoyeAuGerant = estEnvoye;
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
				+ ", dateEnvoi=" + dateEnvoi + ", estEnvoyeAuGerant="
				+ estEnvoyeAuGerant + "]";
	}

	// Méthode permettant d'ajouter un message dans la table MESSAGE sachant l'expéditeur
	// et le nombre de message dans la table MESSAGE ( à améliorer)
	public void ajouterBDD() {
		
		String s = SGBD.transformation(this.dateEnvoi);
//		compteurIdentifiantMessage = compteurIdentifiantMessage+1;
//		System.out.println(compteurIdentifiantMessage);
//		String idMessage=""+compteurIdentifiantMessage;
		
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_MESSAGE.NEXTVAL");
		
		String numMessage = "";
		int numeroMessage=0;
		

		numeroMessage=Integer.parseInt(idNonFini.get(0));
			
		if(numeroMessage<10){
			numMessage= "MES0000" + numeroMessage;
		}
		if(numeroMessage<100 && numeroMessage>=10){
			numMessage= "MES000" + numeroMessage;
		}
		if(numeroMessage<1000 && numeroMessage>=100){
			numMessage= "MES00" + numeroMessage;
		}
		if(numeroMessage<10000 && numeroMessage>=1000){
			numMessage= "MES0" + numeroMessage;
		}
		if(numeroMessage<100000 && numeroMessage>=10000){
			numMessage= "MES" + numeroMessage;
		}
		
		int envoiMessageGerant=0 ;
		
		if(this.estEnvoyeAuGerant==true){
			envoiMessageGerant=1;
		}
			
			
		String requete = "INSERT INTO MESSAGE (IDMESSAGE,SUJETMESSAGE,CONTENUMESSAGE," +
				"IDCLIENT,DATEMESSAGE, ESTENVOYEAUGERANT) VALUES ( '"+ numMessage +"',"
				+ "'"+ this.sujet + "',"
				+ "'"+ this.contenu + "','"
				+ this.expediteur+ "'," 
				+ s+" , " 
				+ envoiMessageGerant +" )";
		System.out.println(requete);
		SGBD.executeUpdate(requete);
		
	}
	
	
	public static void supprimerBDD(String identifiantMessage){
		// TODO vérifier si la requete est correcte
		
		String requete = "DELETE FROM MESSAGE WHERE " +
				"IDMESSAGE = " +"'"+ identifiantMessage+ "'"
		;

		SGBD.executeUpdate(requete);
		SGBD.executeUpdate("COMMIT");
	}
	
	public static void supprimerAllBDD(){
		String requete = "DELETE FROM MESSAGE";
		
		SGBD.executeUpdate(requete);
	}
	
	public static int verifierChampMessage(String contenu,String sujet){
		int champCorrect = 0 ;
		
		if(contenu.length()>300){
			champCorrect = 1;
		}
		if(sujet.length()>90){
			champCorrect = 2 ;
		}
		if(sujet.contains("'") | contenu.contains("'")){
			champCorrect = 3 ;
		}
		if(contenu.length()==0){
			champCorrect=4;
		}
		
		return champCorrect ;
	}
}
