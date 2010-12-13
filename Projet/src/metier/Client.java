package metier;

import basededonnees.SGBD;

/**
 * <b>La classe abstraite Client représente un client</b>
 * <p>
 * Les classes Particulier et Association héritent de la classe Client et de ce
 * fait ont pour caractéristiques communes (en plus de celles répertoriées dans
 * la classe Utilisateur :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville de l'utilisateur</li>
 * <li>Un numéro de téléphone</li>
 * <li>Un mot de passe généré automatiquement</li>
 * <li>Un statut (Particulier ou Association)</li>
 * <li>Un booléen indiquant la possession d'une carte de fidélité (ou non)</li>
 * <li>Un booléen indiquant si le compte du client est actif</li>
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
	 * Le numéro de téléphone du client
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
	 * La possession ou non d'une carte de fidélité
	 * 
	 * @see Association#Association(String, String, String, String, String, boolean)
	 * @see Particulier#Particulier(String, String, String, String, String, String, boolean)
	 */	
	protected boolean estFidele;
	
	
	/**
	 * L'état du compte client
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
     * Met à jour le mail du client
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
     * Met à jour l'adresse du client
     * 
	 * @param mail
	 *            L'adresse du client
     */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	 /**
     * Retourne le numéro de téléphone du client
     * 
     * @return Le numéro de téléphone du client
     * 
     */
	public String getTelephone() {
		return telephone;
	}

	 /**
     * Met à jour le numéro de téléphone du client
     * 
	 * @param telephone
	 *            Le numéro de téléphone du client
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
     * Met à jour l'identifiant de la ville du client
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
     * Met à jour le mot de passe du client
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
     * Met à jour le statut du client
     * 
	 * @param particulierAssociation
	 *            Le statut du client
	 */
	public void setParticulierAssociation(String particulierAssociation) {
		this.particulierAssociation = particulierAssociation;
	}

	 /**
     * Retourne le booléen indiquant si le client possède une carte de fidélité 
     * (vrai si le client est fidèle et faux sinon)
     * 
     * @return La fidélité du client
     * @see Particulier, Association
     * 
     */
	public boolean isEstFidele() {
		return estFidele;
	}

	 /**
     * Met à jour le booléen indiquant si le client possède une carte de fidélité
     * 
	 * @param estFidele
	 *            La possession ou non d'une carte de fidélité
	 */
	public void setEstFidele(boolean estFidele) {
		this.estFidele = estFidele;
	}

	 /**
     * Retourne le booléen indiquant si le compte du client est activé 
     * (vrai si le compte est activé et faux sinon)
     * 
     * @return L'état du compte du client
     * @see Particulier, Association
     * 
     */
	public boolean isEstActif() {
		return estActif;
	}

	 /**
     * Met à jour le booléen indiquant si le compte du client est activé
     * 
	 * @param estActif
	 *            L'état du compte
	 */
	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}
	

	/**
	 * Crée un nouvel objet CarteFidelite si le client a signalé son souhait de posséder une carte de fidélité
	 * 
	 * <p>
	 * De ce fait, la table CARTE_FIDELITE est mise à jour (plus de précisions
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
	 * Génère un mot de passe de 7 caractères
	 * 
	 * <p>
	 * Cette méthode choisit aléatoirement 7 caractères dans la table des caractères
	 * ASCII (les 48 premiers caractères sont exclus car trop "exotiques")
	 * 
	 *
	 * </p> 
	 * 
	 * @return Le mot de passe généré
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
	 * Modifie l'état du compte d'un client 
	 * 
	 * @param idClient
	 *            L'identifiant du client
	 * @param etatCompte
	 *            L'état du compte
	 */
	public static void modifierBDDclient(String idClient,String etatCompte) {

		String requete = " UPDATE CLIENT SET ETATCOMPTE='" + etatCompte
				+ "' WHERE IDCLIENT = '" + idClient+ "'" ;

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}



	/**
	 * Vérifie le bon format de l'adresse mail, du code postal et du numéro de téléphone saisis
	 * 
	 * @param mail
	 *            Le mail saisi initialement par le client
	 * @param mailConfirmation
	 *            Le mail saisi dans le champ de confirmation par le client
	 * @param denomination
	 *            La dénomination du client (si c'est une association)
	 * @param nom
	 *            Le nom du client (si c'est un particulier)
	 * @param prenom
	 *            Le prénom du client (si c'est un particulier)
	 * @param telephone
	 *            Le numéro de téléphone du client
	 * @param codePostal
	 *            Le code postal du client
	 *            
	 *            
	 */
	
	public static int verifierCreationCompte(String mail,String mailConfirmation,
			String denomination,String nom,String prenom,String telephone,String codePostal){
		
		//si le compte ne comporte aucune erreur la fonction retournera 0
		// sinon elle renverra le numéro de l'erreur
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
			System.out.println("le Code Postal ou le numéro de téléphone indiqué n'est pas bon");
		}
		
		return compteBon ;
	}

	
	
	
	

	

}
