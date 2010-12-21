package metier;

import basededonnees.SGBD;

/**
 * <b>La classe Association représente une association</b>
 * <p>
 * Une association est caractérisée par les informations suivantes :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le
 * client représentant l'association</li>
 * <li>Une dénomination</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville où l'association est basée</li>
 * <li>Un numéro de téléphone</li>
 * <li>Le statut du client (ici, "Association")</li>
 * <li>Un booléen déterminant si l'association possède une carte de fidélité</li>
 * <li>L'état du compte de l'association (Activé si le booléen correspondant est
 * vrai et Désactivé sinon)</li>
 * <li>Un mot de passe généré automatiquement</li>
 * </ul>
 * </p>
 * 
 * @see basededonnees.BDD
 * @see Client
 */
public class Association extends Client {

	/**
	 * La dénomination (le nom) de l'association
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
	 * Le constructeur de la classe Association fait appel à la méthode
	 * ajouterBDD() qui l'ajoute dans la base de données. La méthode
	 * ajouterFideliteBDD() instancie un nouvel objet CarteFidelite et de ce
	 * fait crée une nouvelle ligne dans la table CARTE_FIDELITE si le client a
	 * émis le souhait de posséder une carte. L'état du compte est par défaut
	 * initialisé sur Activé (booléen estActif vrai) Le mot de passe est généré
	 * automatiquement.
	 * </p>
	 * 
	 * @param denomination
	 *            La dénomination de l'association
	 * @param mail
	 *            Le mail de l'association, qui est utilisé comme identifiant et
	 *            n'est pas modifiable par le client
	 * @param adresse
	 *            L'adresse de l'association
	 * @param idVille
	 *            L'identifiant de la ville où est basée l'association
	 * @param telephone
	 *            Le numéro de téléphone de l'association
	 * @param estFidele
	 *            Détermine si l'association possède une carte de fidélité ou
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
	 * Retourne la dénomination de l'association
	 * 
	 * @return La dénomination de l'association
	 * 
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * Met à jour la dénomination de l'association
	 * 
	 * @param denomination
	 *            La dénomination de l'association
	 * 
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	/**
	 * Ajoute l'association dans la table CLIENT de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par créer une chaîne de caractères dépendant de la
	 * valeur du booléen (Désactivé si le booléen est faux, Activé sinon)
	 * 
	 * La requête se construit ensuite en fonction des caractéristiques de
	 * l'article saisies lors de l'appel du constructeur
	 * </p>
	 * <b>Note :</b> La méthode replaceAll est utilisée pour remplacer les apostrophes
	 * par des doubles apostrophes (pour éviter des erreurs dans la requête SQL) 
	 * @see basededonnees.BDD
	 */

	public void ajouterBDD() {

		String actif = null;

		if (this.estActif == false) {
			actif = "Désactivé";
		} else {
			actif = "Activé";
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
	 * Modifie les caractéristiques de l'association dans la table CLIENT de la
	 * base de données
	 * 
	 * <p>
	 * Étant donné que le client peut modifier son code postal et non
	 * l'identifiant de la ville (auquel il n'a pas accès), la méthode commence
	 * par récupérer cet identifiant dans la table VILLE
	 * 
	 * La requête se construit ensuite en fonction des caractéristiques de
	 * l'article saisies lors de l'appel de la méthode
	 * </p>
	 * <b>Note :</b> La méthode replaceAll est utilisée pour remplacer les apostrophes
	 * par des doubles apostrophes (pour éviter des erreurs dans la requête SQL) 
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
