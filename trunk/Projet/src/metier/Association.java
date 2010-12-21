package metier;

import basededonnees.SGBD;

/**
 * <b>La classe Association repr�sente une association</b>
 * <p>
 * Une association est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le
 * client repr�sentant l'association</li>
 * <li>Une d�nomination</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville o� l'association est bas�e</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Le statut du client (ici, "Association")</li>
 * <li>Un bool�en d�terminant si l'association poss�de une carte de fid�lit�</li>
 * <li>L'�tat du compte de l'association (Activ� si le bool�en correspondant est
 * vrai et D�sactiv� sinon)</li>
 * <li>Un mot de passe g�n�r� automatiquement</li>
 * </ul>
 * </p>
 * 
 * @see basededonnees.BDD
 * @see Client
 */
public class Association extends Client {

	/**
	 * La d�nomination (le nom) de l'association
	 * 
	 * @see Association#ajouterBDD()
	 * @see Association#modifierBDDassoc(String, String, String, String, String)
	 * @see Association#getDenomination()
	 * @see Association#setDenomination(String)
	 */
	private String denomination;

	/**
	 * Constructeur de la classe Association
	 * <p>
	 * Le constructeur de la classe Association fait appel � la m�thode
	 * ajouterBDD() qui l'ajoute dans la base de donn�es. La m�thode
	 * ajouterFideliteBDD() instancie un nouvel objet CarteFidelite et de ce
	 * fait cr�e une nouvelle ligne dans la table CARTE_FIDELITE si le client a
	 * �mis le souhait de poss�der une carte. L'�tat du compte est par d�faut
	 * initialis� sur Activ� (bool�en estActif vrai) Le mot de passe est g�n�r�
	 * automatiquement.
	 * </p>
	 * 
	 * @param denomination
	 *            La d�nomination de l'association
	 * @param mail
	 *            Le mail de l'association, qui est utilis� comme identifiant et
	 *            n'est pas modifiable par le client
	 * @param adresse
	 *            L'adresse de l'association
	 * @param idVille
	 *            L'identifiant de la ville o� est bas�e l'association
	 * @param telephone
	 *            Le num�ro de t�l�phone de l'association
	 * @param estFidele
	 *            D�termine si l'association poss�de une carte de fid�lit� ou
	 *            non du magasin
	 * 
	 * @see Association#denomination
	 * @see Client#mail
	 * @see Client#adresse
	 * @see Client#idVille
	 * @see Client#telephone
	 * @see Client#particulierAssociation
	 * @see Client#estFidele
	 * @see Client#estActif
	 * @see Client#motDePasse
	 * @see Client#ajouterFideliteBDD()
	 * @see Association#ajouterBDD()
	 */
	public Association(String denomination, String mail, String adresse,
			String idVille, String telephone, boolean estFidele) {
		this.denomination = denomination;
		this.mail = mail;
		this.adresse = adresse;
		this.idVille = idVille;
		this.telephone = telephone;
		this.particulierAssociation = "Association";
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
		ajouterBDD();
		ajouterFideliteBDD();
		System.out.println("Votre mot de passe est : " + this.motDePasse);

	}

	/**
	 * Retourne la d�nomination de l'association
	 * 
	 * @return La d�nomination de l'association
	 * 
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * Met � jour la d�nomination de l'association
	 * 
	 * @param denomination
	 *            La d�nomination de l'association
	 * 
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	/**
	 * Ajoute l'association dans la table CLIENT de la base de donn�es
	 * 
	 * <p>
	 * Cette m�thode commence par cr�er une cha�ne de caract�res d�pendant de la
	 * valeur du bool�en (D�sactiv� si le bool�en est faux, Activ� sinon)
	 * 
	 * La requ�te se construit ensuite en fonction des caract�ristiques de
	 * l'article saisies lors de l'appel du constructeur
	 * </p>
	 * <b>Note :</b> La m�thode replaceAll est utilis�e pour remplacer les apostrophes
	 * par des doubles apostrophes (pour �viter des erreurs dans la requ�te SQL) 
	 * @see basededonnees.BDD
	 */

	public void ajouterBDD() {

		String actif = null;

		if (this.estActif == false) {
			actif = "D�sactiv�";
		} else {
			actif = "Activ�";
		}

		String requete = "INSERT INTO CLIENT (IDCLIENT, DENOMINATIONCLIENT, ADRESSECLIENT, IDVILLE"
				+ ", TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "
				+ "'"
				+ this.mail
				+ "',"
				+ "'"
				+ this.denomination.replaceAll("'", "''")
				+ "',"
				+ "'"
				+ this.adresse.replaceAll("'", "''")
				+ "',"
				+ "'"
				+ this.idVille.replaceAll("'", "''")
				+ "',"
				+ "'"
				+ this.telephone
				+ "',"
				+ "'"
				+ actif
				+ "',"
				+ "'"
				+ this.motDePasse + "')";

		SGBD.executeUpdate(requete);
	}

	/**
	 * Modifie les caract�ristiques de l'association dans la table CLIENT de la
	 * base de donn�es
	 * 
	 * <p>
	 * �tant donn� que le client peut modifier son code postal et non
	 * l'identifiant de la ville (auquel il n'a pas acc�s), la m�thode commence
	 * par r�cup�rer cet identifiant dans la table VILLE
	 * 
	 * La requ�te se construit ensuite en fonction des caract�ristiques de
	 * l'article saisies lors de l'appel de la m�thode
	 * </p>
	 * <b>Note :</b> La m�thode replaceAll est utilis�e pour remplacer les apostrophes
	 * par des doubles apostrophes (pour �viter des erreurs dans la requ�te SQL) 
	 * 
	 * @see basededonnees.BDD
	 */
	public static void modifierBDDassoc(String idClient, String denomination,
			String adresse, String codePostal, String telephone) {

		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE",
				"CODEPOSTAL", codePostal);

		String requete = " UPDATE CLIENT SET DENOMINATIONCLIENT='"
				+ denomination.replaceAll("'", "''") + "',ADRESSECLIENT='" + adresse.replaceAll("'", "''") +
				"',IDVILLE='" + idVille.replaceAll("'", "''") + "',TELEPHONE='" + telephone + "' WHERE IDCLIENT ='"
				+ idClient + "'";
		System.out.println(requete);

		SGBD.executeUpdate(requete);

	}

}
