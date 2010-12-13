package metier;

import basededonnees.SGBD;

/**
 * <b>La classe abstraite Client repr�sente un client</b>
 * <p>
 * Les classes Particulier et Association h�ritent de la classe Client et de ce
 * fait ont pour caract�ristiques communes (en plus de celles r�pertori�es dans
 * la classe Utilisateur :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville de l'utilisateur</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Un mot de passe g�n�r� automatiquement</li>
 * <li>Un statut (Particulier ou Association)</li>
 * <li>Un bool�en indiquant la possession d'une carte de fid�lit� (ou non)</li>
 * <li>Un bool�en indiquant si le compte du client est actif</li>
 * </ul>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @see Particulier, Association
 */
public abstract class Client{
	
	/**
	 * Le mail du client, non modifiable
	 * 
	 * @see Particulier, Association
	 * 
	 */
	protected String mail;
	
	/**
	 * L'adresse du client
	 * 
	 * @see Particulier, Association
	 * 
	 */
	protected String adresse;
	
	/**
	 * L'identifiant de la ville du client
	 * 
	 * @see Particulier, Association
	 * 
	 */
	protected String idVille;
	
	/**
	 * Le num�ro de t�l�phone du client
	 * 
	 * @see Particulier, Association
	 * 
	 */
	protected String telephone;
	
	/**
	 * Le mot de passe du client
	 * 
	 * @see Particulier, Association
	 * @see Client#genererMdp()
	 * 
	 */
	protected String motDePasse;
	
	
	/**
	 * Le statut du client
	 * 
	 * @see Association#Association(String, String, String, String, String, boolean)
	 * @see Particulier#Particulier(String, String, String, String, String, String, boolean)
	 */	
	protected String particulierAssociation;
	
	/**
	 * La possession ou non d'une carte de fid�lit�
	 * 
	 * @see Association#Association(String, String, String, String, String, boolean)
	 * @see Particulier#Particulier(String, String, String, String, String, String, boolean)
	 */	
	protected boolean estFidele;
	
	
	/**
	 * L'�tat du compte client
	 * 
	 * @see Particulier,Association
	 */	
	protected boolean estActif;

	

	
	 /**
     * Retourne le mail du client (son identifiant)
     * 
     * @return Le mail du client
     * 
     */
	public String getMail() {
		return mail;
	}

	 /**
     * Met � jour le mail du client
     * 
	 * @param mail
	 *            Le mail du client
     * 
     */
	public void setMail(String mail) {
		this.mail = mail;
	}

	 /**
     * Retourne l'adresse du client
     * 
     * @return L'adresse du client
     * 
     */
	public String getAdresse() {
		return adresse;
	}

	 /**
     * Met � jour l'adresse du client
     * 
	 * @param mail
	 *            L'adresse du client
     */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	 /**
     * Retourne le num�ro de t�l�phone du client
     * 
     * @return Le num�ro de t�l�phone du client
     * 
     */
	public String getTelephone() {
		return telephone;
	}

	 /**
     * Met � jour le num�ro de t�l�phone du client
     * 
	 * @param telephone
	 *            Le num�ro de t�l�phone du client
     */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	 /**
     * Retourne l'identifiant de la ville du client
     * 
     * @return L'identifiant de la ville du client
     * 
     */
	public String getIdVille() {
		return idVille;
	}

	 /**
     * Met � jour l'identifiant de la ville du client
     * 
	 * @param idVille
	 *            L'identifiant de la ville du client
     */
	public void setIdVille(String idVille) {
		this.idVille = idVille;
	}

	 /**
     * Retourne le mot de passe du client
     * 
     * @return Le mot de passe du client
     * 
     */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	 /**
     * Met � jour le mot de passe du client
     * 
	 * @param motDePasse
	 *            Le mot de passe du client
     */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	 /**
     * Retourne le statut du client
     * 
     * @return Le statut du client
     * @see Particulier, Association
     * 
     */
	public String getParticulierAssociation() {
		return particulierAssociation;
	}

	
	 /**
     * Met � jour le statut du client
     * 
	 * @param particulierAssociation
	 *            Le statut du client
	 */
	public void setParticulierAssociation(String particulierAssociation) {
		this.particulierAssociation = particulierAssociation;
	}

	 /**
     * Retourne le bool�en indiquant si le client poss�de une carte de fid�lit� 
     * (vrai si le client est fid�le et faux sinon)
     * 
     * @return La fid�lit� du client
     * @see Particulier, Association
     * 
     */
	public boolean isEstFidele() {
		return estFidele;
	}

	 /**
     * Met � jour le bool�en indiquant si le client poss�de une carte de fid�lit�
     * 
	 * @param estFidele
	 *            La possession ou non d'une carte de fid�lit�
	 */
	public void setEstFidele(boolean estFidele) {
		this.estFidele = estFidele;
	}

	 /**
     * Retourne le bool�en indiquant si le compte du client est activ� 
     * (vrai si le compte est activ� et faux sinon)
     * 
     * @return L'�tat du compte du client
     * @see Particulier, Association
     * 
     */
	public boolean isEstActif() {
		return estActif;
	}

	 /**
     * Met � jour le bool�en indiquant si le compte du client est activ�
     * 
	 * @param estActif
	 *            L'�tat du compte
	 */
	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}
	

	/**
	 * Cr�e un nouvel objet CarteFidelite si le client a signal� son souhait de poss�der une carte de fid�lit�
	 * 
	 * <p>
	 * De ce fait, la table CARTE_FIDELITE est mise � jour (plus de pr�cisions
	 * dans le commentaire concernant ce constructeur)
	 * </p> 
	 * 
	 * @see CarteFidelite#CarteFidelite(String, int)
	 * @see BDD
	 */
	public void ajouterFideliteBDD() {
		if (this.estFidele) {
				
					CarteFidelite cf = new CarteFidelite(this.mail, 0);
					
		}
	}
	
	/**
	 * G�n�re un mot de passe de 7 caract�res
	 * 
	 * <p>
	 * Cette m�thode choisit al�atoirement 7 caract�res dans la table des caract�res
	 * ASCII (les 48 premiers caract�res sont exclus car trop "exotiques")
	 * 
	 *
	 * </p> 
	 * 
	 * @return Le mot de passe g�n�r�
	 * @see Client,Gerant
	 */
	protected String genererMdp(){
		String s = "";
		for(int i=0;i<7;i++){
			int a =   (int) ((int)  74* Math.random()) +48;
			char c = (char) a;
			 s = s+c;}
		return s;
	}


	/**
	 * Modifie l'�tat du compte d'un client 
	 * 
	 * @param idClient
	 *            L'identifiant du client
	 * @param etatCompte
	 *            L'�tat du compte
	 */
	public static void modifierBDDclient(String idClient,String etatCompte) {

		String requete = " UPDATE CLIENT SET ETATCOMPTE='" + etatCompte
				+ "' WHERE IDCLIENT = '" + idClient+ "'" ;

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}



	/**
	 * V�rifie le bon format de l'adresse mail, du code postal et du num�ro de t�l�phone saisis
	 * 
	 * @param mail
	 *            Le mail saisi initialement par le client
	 * @param mailConfirmation
	 *            Le mail saisi dans le champ de confirmation par le client
	 * @param denomination
	 *            La d�nomination du client (si c'est une association)
	 * @param nom
	 *            Le nom du client (si c'est un particulier)
	 * @param prenom
	 *            Le pr�nom du client (si c'est un particulier)
	 * @param telephone
	 *            Le num�ro de t�l�phone du client
	 * @param codePostal
	 *            Le code postal du client
	 *            
	 *            
	 */
	
	public static int verifierCreationCompte(String mail,String mailConfirmation,
			String denomination,String nom,String prenom,String telephone,String codePostal){
		
		//si le compte ne comporte aucune erreur la fonction retournera 0
		// sinon elle renverra le num�ro de l'erreur
		int compteBon=0;
		
		if(!mail.contains("@")){
			compteBon=1;
		}
		if(!mail.equals(mailConfirmation)){
			compteBon=2;
		}
		if(codePostal.length() != 5){
			compteBon=3;
		}
		if(telephone.length() != 10){
			compteBon=8;
		}
		if(denomination.contains("'") | nom.contains("'") | prenom.contains("'") | mail.contains("'")){
			compteBon=4;
		}
		
		if(denomination.length()>40 | nom.length()>40 | prenom.length()>40 | mail.length()>40){
			compteBon=5;
		}
		
		try{
			int cp = Integer.parseInt(codePostal);
			long tel = Long.parseLong(telephone);

			if(cp<=999 | cp>=96000){
				compteBon=6;
				System.out.println(compteBon);
			}
			if(tel<100000000 | tel>= 800000000){
				compteBon = 7;
				System.out.println(compteBon);
			}
			
		}
		catch(NumberFormatException exc){
			System.out.println("le Code Postal ou le num�ro de t�l�phone indiqu� n'est pas bon");
		}
		
		return compteBon ;
	}

	
	
	
	

	

}
