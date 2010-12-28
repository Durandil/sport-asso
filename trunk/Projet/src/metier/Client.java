package metier;

import basededonnees.SGBD;

/**
 * <b>La classe abstraite Client repr�sente un client.</b>
 * <p>
 * Les classes Particulier et Association h�ritent de la classe Client et de ce
 * fait ont pour caract�ristiques communes :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le
 * client</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville de l'utilisateur</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Un mot de passe g�n�r� automatiquement</li>
 * <li>Un statut (Particulier ou Association)</li>
 * <li>Un bool�en indiquant la possession d'une carte de fid�lit� (ou non)</li>
 * <li>Un bool�en indiquant si le compte du client est actif</li>
 * </ul>
 * </p>
 * 
 * @see Particulier
 * @see Association
 */
public abstract class Client {

	/**
	 * Le mail du client, non modifiable.
	 * 
	 * @see Client#getMail()
	 * @see Client#setMail(String)
	 * @see Particulier
	 * @see Association
	 * 
	 */
	protected String mail;

	/**
	 * L'adresse du client.
	 * 
	 * @see Client#getAdresse()
	 * @see Client#setAdresse(String)
	 * @see Particulier
	 * @see Association
	 * 
	 */
	protected String adresse;

	/**
	 * L'identifiant de la ville du client.
	 * 
	 * @see Client#getIdVille()
	 * @see Client#setIdVille(String)
	 * @see Particulier
	 * @see Association
	 * 
	 */
	protected String idVille;

	/**
	 * Le num�ro de t�l�phone du client.
	 * 
	 * @see Client#getTelephone()
	 * @see Client#setTelephone(String)
	 * @see Particulier
	 * @see Association
	 * 
	 */
	protected String telephone;

	/**
	 * Le mot de passe du client.
	 * 
	 * @see Client#getMotDePasse()
	 * @see Client#setMotDePasse(String)
	 * @see Client#genererMdp()
	 * @see Particulier
	 * @see Association
	 * 
	 */
	protected String motDePasse;

	/**
	 * Le statut du client.
	 * 
	 * @see Client#getParticulierAssociation()
	 * @see Client#setParticulierAssociation(String)
	 * @see Association#Association(String, String, String, String, String,
	 *      boolean)
	 * @see Particulier#Particulier(String, String, String, String, String,
	 *      String, boolean)
	 */
	protected String particulierAssociation;

	/**
	 * La possession ou non d'une carte de fid�lit�.
	 * 
	 * @see Client#isEstFidele()
	 * @see Client#setEstFidele(boolean)
	 * @see Association#Association(String, String, String, String, String,
	 *      boolean)
	 * @see Particulier#Particulier(String, String, String, String, String,
	 *      String, boolean)
	 */
	protected boolean estFidele;

	/**
	 * L'�tat du compte client.
	 * 
	 * @see Client#isEstActif()
	 * @see Client#setEstActif(boolean)
	 * @see Particulier
	 * @see Association
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
	 * @param adresse
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
	 * @see Particulier
	 * @see Association
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
	 * @return Le statut du client
	 * @see Particulier
	 * @see Association
	 * 
	 */
	public boolean isEstFidele() {
		return estFidele;
	}

	/**
	 * Met � jour le bool�en indiquant si le client poss�de une carte de
	 * fid�lit�
	 * 
	 * @param estFidele
	 *            La possession ou non d'une carte de fid�lit�
	 */
	public void setEstFidele(boolean estFidele) {
		this.estFidele = estFidele;
	}

	/**
	 * Retourne le bool�en indiquant si le compte du client est activ� (vrai si
	 * le compte est activ� et faux sinon)
	 * 
	 * @return L'�tat du compte du client
	 * @see Particulier
	 * @see Association
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
	 * Cr�e un nouvel objet CarteFidelite si le client a signal� son souhait de
	 * poss�der une carte de fid�lit�.
	 * 
	 * <p>
	 * De ce fait, la table CARTE_FIDELITE est mise � jour (plus de pr�cisions
	 * dans le commentaire concernant ce constructeur).
	 * </p>
	 * 
	 * @see CarteFidelite#CarteFidelite(String, int)
	 * @see basededonnees.BDD
	 */
	public void ajouterFideliteBDD() {
		if (this.estFidele) {

			@SuppressWarnings("unused")
			CarteFidelite cf = new CarteFidelite(this.mail, 0);

		}
	}

	/**
	 * G�n�re un mot de passe de 7 caract�res.
	 * 
	 * <p>
	 * Cette m�thode choisit al�atoirement 7 caract�res dans la table des
	 * caract�res ASCII (les 48 premiers caract�res sont exclus car trop
	 * "exotiques").
	 * 
	 * 
	 * </p>
	 * 
	 * @return Le mot de passe g�n�r�
	 * @see Client
	 */
	protected String genererMdp() {
		String s = "";
		for (int i = 0; i < 7; i++) {
			int a = (int) ((int) 74 * Math.random()) + 48;
			char c = (char) a;
			s = s + c;
		}
		return s;
	}

	/**
	 * Modifie l'�tat du compte d'un client.
	 * 
	 * @param idClient
	 *            L'identifiant du client
	 * @param etatCompte
	 *            L'�tat du compte
	 */
	public static void modifierBDDclient(String idClient, String etatCompte) {

		String requete = " UPDATE CLIENT SET ETATCOMPTE='" + etatCompte
				+ "' WHERE IDCLIENT = '" + idClient + "'";

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}
	
	
}
