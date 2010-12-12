package metier;

import java.sql.Date;
import java.util.ArrayList;


import basededonnees.SGBD;

/**
 * <b>La classe Message représente un message</b>
 * <p>
 * Un message est caractérisé par les informations suivantes (que l'on retrouve
 * dans la base de données) :
 * <ul>
 * <li>Un identifiant unique attribué définitivement, de la forme MESxxxxx</li>
 * <li>Un sujet</li>
 * <li>Un contenu (le corps du message)</li>
 * <li>Un expéditeur</li>
 * <li>Une date d'envoi</li>
 * <li>Un booléen qui indique si le message a été envoyé au gérant (le gérant peut répondre aux messages de ses clients)</li>
 * </ul>
 * </p>
 * 
 * @see BDD
 */
public class Message {
	
	/**
	 * L'Identifiant du message, non modifiable
	 * 
	 * @see Message#ajouterBDD()
	 * @see Message#supprimerBDD(String)
	 * @see Message#supprimerAllBDD()
	 */
	private String idMessage ;
	
	/**
	 * Le sujet du message.
	 * 
	 * @see Message#ajouterBDD()
	 */
	private String sujet ;
	
	/**
	 * Le contenu du message.
	 * 
	 * @see Message#ajouterBDD()
	 */
	private String contenu ;
	
	/**
	 * L'expéditeur du message.
	 * 
	 * @see Message#ajouterBDD()
	 */
	private String expediteur;
	
	/**
	 * La date d'envoi du message.
	 * 
	 * @see Message#ajouterBDD()
	 */
	private Date dateEnvoi ;
	
	/**
	 * Le booléen indiquant si le message a été envoyé au gérant.
	 * 
	 * @see Message#ajouterBDD()
	 */
	private boolean estEnvoyeAuGerant ;
	
	/**
	 * Constructeur de la classe Message
	 * <p>
	 * Le constructeur de la classe Message fait appel à la méthode ajouterBDD()
	 * qui ajoute le message dans la base de données.
	 * </p>
	 * 
	 * @param sujet
	 *            Le sujet du message.
	 * @param contenu
	 *            Le contenu du message.
	 * @param expediteur
	 *            L'éxpéditeur du message.
	 * @param dateEnvoi
	 *            La date du message.
	 * @param messageEnvoye
	 *            Le booléen indiquant si le message a été envoyé au gérant.
	 * 
	 * @see Message#sujet
	 * @see Message#contenu
	 * @see Message#expediteur
	 * @see Message#dateEnvoi
	 * @see Message#estEnvoyeAuGerant
	 * @see Message#ajouterBDD()
	 */
	public Message(String sujet, String contenu, String expediteur, Date dateEnvoi,boolean messageEnvoye) {
	
		this.sujet = sujet;
		this.contenu = contenu;
		this.expediteur = expediteur;
		this.dateEnvoi = dateEnvoi;
		this.estEnvoyeAuGerant = messageEnvoye ;
		ajouterBDD();
	}
	
	
	 /**
     * Retourne l'id du message
     * 
     * @return L'identifiant du message
     * 
     */
	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}


	/**
     * Met à jour l'id du message
     * 
     * @param idMessage
     *            L'identifiant unique du message
     * 
     */
	public String getIdMessage() {
		return idMessage;
	}
	
	

	 /**
     * Retourne le sujet du message
     * 
     * @return Le sujet du message
     * 
     */
	public String getSujet() {
		return sujet;
	}

	/**
     * Met à jour le sujet du message
     * 
     * @param sujet
     *            Le sujet du message
     * 
     */
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	 /**
     * Retourne le contenu du message
     * 
     * @return Le contenu du message
     * 
     */
	public String getContenu() {
		return contenu;
	}

	
	/**
     * Met à jour le contenu du message
     * 
     * @param contenu
     *            Le contenu du message
     * 
     */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	 /**
     * Retourne l'expéditeur du message
     * 
     * @return L'expéditeur du message
     * 
     */
	public String getExpediteur() {
		return expediteur;
	}

	
	/**
     * Met à jour l'expéditeur du message
     * 
     * @param expediteur
     *            L'expéditeur du message
     * 
     */
	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}

	 /**
     * Retourne la date d'envoi du message
     * 
     * @return La date d'envoi du message
     * 
     */
	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	
	/**
     * Met à jour la date d'envoi du message
     * 
     * @param dateEnvoi
     *            La date d'envoi du message
     * 
     */
	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	
	 /**
     * Retourne le booléen indiquant si le message est envoyé au gérant
     * 
     * @return Si le message a été envoyé au gérant
     * 
     */
	public boolean isEstEnvoye() {
		return estEnvoyeAuGerant;
	}

	/**
     * Met à jour le booléen indiquant si le message est envoyé au gérant
     * 
     * @param estEnvoye
     *            Le booléen indiquant si le message est envoyé au gérant
     * 
     */
	public void setEstEnvoye(boolean estEnvoye) {
		this.estEnvoyeAuGerant = estEnvoye;
	}
	// Méthode permettant d'ajouter un message dans la table MESSAGE sachant l'expéditeur
	// et le nombre de message dans la table MESSAGE ( à améliorer)
	public void ajouterBDD() {
		
		String s = SGBD.transformation(this.dateEnvoi);

		
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_MESSAGE.NEXTVAL");
		

		int numeroMessage=0;
		

		numeroMessage=Integer.parseInt(idNonFini.get(0));
			
		if(numeroMessage<10){
			this.idMessage= "MES0000" + numeroMessage;
		}
		if(numeroMessage<100 && numeroMessage>=10){
			this.idMessage= "MES000" + numeroMessage;
		}
		if(numeroMessage<1000 && numeroMessage>=100){
			this.idMessage= "MES00" + numeroMessage;
		}
		if(numeroMessage<10000 && numeroMessage>=1000){
			this.idMessage= "MES0" + numeroMessage;
		}
		if(numeroMessage<100000 && numeroMessage>=10000){
			this.idMessage= "MES" + numeroMessage;
		}
		
		int envoiMessageGerant=0 ;
		
		if(this.estEnvoyeAuGerant==true){
			envoiMessageGerant=1;
		}
			
			
		String requete = "INSERT INTO MESSAGE (IDMESSAGE,SUJETMESSAGE,CONTENUMESSAGE," +
				"IDCLIENT,DATEMESSAGE, ESTENVOYEAUGERANT) VALUES ( '"+ this.idMessage +"',"
				+ "'"+ this.sujet + "',"
				+ "'"+ this.contenu + "','"
				+ this.expediteur+ "'," 
				+ s+" , " 
				+ envoiMessageGerant +" )";
		System.out.println(requete);
		SGBD.executeUpdate(requete);
		
	}
	
	
	public static void supprimerBDD(String identifiantMessage){

		
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
