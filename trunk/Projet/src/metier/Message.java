package metier;

import java.sql.Date;
import java.util.ArrayList;


import basededonnees.SGBD;

/**
 * <b>La classe Message repr�sente un message</b>
 * <p>
 * Un message est caract�ris� par les informations suivantes (que l'on retrouve
 * dans la base de donn�es) :
 * <ul>
 * <li>Un identifiant unique attribu� d�finitivement, de la forme MESxxxxx</li>
 * <li>Un sujet</li>
 * <li>Un contenu (le corps du message)</li>
 * <li>Un exp�diteur</li>
 * <li>Une date d'envoi</li>
 * <li>Un bool�en qui indique si le message a �t� envoy� au g�rant (le g�rant peut r�pondre aux messages de ses clients)</li>
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
	 * L'exp�diteur du message.
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
	 * Le bool�en indiquant si le message a �t� envoy� au g�rant.
	 * 
	 * @see Message#ajouterBDD()
	 */
	private boolean estEnvoyeAuGerant ;
	
	/**
	 * Constructeur de la classe Message
	 * <p>
	 * Le constructeur de la classe Message fait appel � la m�thode ajouterBDD()
	 * qui ajoute le message dans la base de donn�es.
	 * </p>
	 * 
	 * @param sujet
	 *            Le sujet du message.
	 * @param contenu
	 *            Le contenu du message.
	 * @param expediteur
	 *            L'�xp�diteur du message.
	 * @param dateEnvoi
	 *            La date du message.
	 * @param messageEnvoye
	 *            Le bool�en indiquant si le message a �t� envoy� au g�rant.
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
     * Met � jour l'id du message
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
     * Met � jour le sujet du message
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
     * Met � jour le contenu du message
     * 
     * @param contenu
     *            Le contenu du message
     * 
     */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	 /**
     * Retourne l'exp�diteur du message
     * 
     * @return L'exp�diteur du message
     * 
     */
	public String getExpediteur() {
		return expediteur;
	}

	
	/**
     * Met � jour l'exp�diteur du message
     * 
     * @param expediteur
     *            L'exp�diteur du message
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
     * Met � jour la date d'envoi du message
     * 
     * @param dateEnvoi
     *            La date d'envoi du message
     * 
     */
	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	
	 /**
     * Retourne le bool�en indiquant si le message est envoy� au g�rant
     * 
     * @return Si le message a �t� envoy� au g�rant
     * 
     */
	public boolean isEstEnvoye() {
		return estEnvoyeAuGerant;
	}

	/**
     * Met � jour le bool�en indiquant si le message est envoy� au g�rant
     * 
     * @param estEnvoye
     *            Le bool�en indiquant si le message est envoy� au g�rant
     * 
     */
	public void setEstEnvoye(boolean estEnvoye) {
		this.estEnvoyeAuGerant = estEnvoye;
	}
	// M�thode permettant d'ajouter un message dans la table MESSAGE sachant l'exp�diteur
	// et le nombre de message dans la table MESSAGE ( � am�liorer)
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
