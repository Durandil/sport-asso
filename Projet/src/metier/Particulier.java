package metier;

import java.util.ArrayList;

import ihm.Accueil.FenetreDialogIdentification;
import basededonnees.SGBD;

/**
 * <b>La classe Particulier représente un particulier</b>
 * <p>
 * Un particulier est caractérisé par les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client représentant l'association</li>
 * <li>Un nom</li>
 * <li>Un prénom</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville où l'association est basée</li>
 * <li>Un numéro de téléphone</li>
 * <li>Le statut du client (ici, "Particulier")</li>
 * <li>Un booléen déterminant si l'association possède une carte de fidélité</li>
 * <li>L'état du compte de l'association (Activé si le booléen correspondant est vrai et Désactivé sinon)</li>
 * <li>Un mot de passe généré automatiquement</li>
 * </ul>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @see BDD
 * @see Client
 */

public class Particulier extends Client {

	/**
	 * Le nom du client
	 * 
	 * @see Particulier#getNom()
	 * @see Particulier#setNom(String)
	 * @see Particulier#ajouterBDD()
	 * @see Particulier#modifierBDDparticulier(String, String, String, String, String, String)
	 * 
	 */
	private String nom;
	
	
	/**
	 * Le prénom du client
	 * 
	 * @see Particulier#getNom()
	 * @see Particulier#setNom(String)
	 * @see Particulier#ajouterBDD()
	 * @see Particulier#modifierBDDparticulier(String, String, String, String, String, String)
	 * 
	 */
	private String prenom;

	/**
	 * Constructeur de la classe Particulier
	 * <p>
	 * Le constructeur de la classe Particulier fait appel à la méthode ajouterBDD()
	 * qui l'ajoute dans la base de données. 
	 * La méthode ajouterFideliteBDD() instancie un nouvel objet CarteFidelite et de ce fait
	 * crée une nouvelle ligne dans la table CARTE_FIDELITE si le client a émis 
	 * le souhait de posséder une carte.
	 * L'état du compte est par défaut initialisé sur Activé (booléen estActif vrai)
	 * Le mot de passe est généré automatiquement.
	 * </p>
	 * 
	 * @param nom
	 *            Le nom du client
	 * @param prenom
	 *            Le prénom du client
	 * @param mail
	 *            Le mail de l'association, qui est utilisé comme identifiant et n'est pas modifiable par le client
	 * @param adresse
	 *            L'adresse de l'association
	 * @param idVille
	 *            L'identifiant de la ville où est basée l'association
	 * @param telephone
	 *            Le numéro de téléphone de l'association
	 * @param estFidele
	 *            Détermine si l'association possède une carte de fidélité ou non du magasin
	 *            
	 * @see Particulier#nom
	 * @see Particulier#prenom
	 * @see Client#mail
	 * @see Client#adresse
	 * @see Client#idVille
	 * @see Client#telephone
	 * @see Client#particulierAssociation
	 * @see Client#estFidele
	 * @see Client#estActif
	 * @see Client#motDePasse
	 * @see Client#ajouterFideliteBDD()
	 * @see Particulier#ajouterBDD()
	 * 
	 */
	public Particulier(String nom, String prenom, String mail, String adresse,
			 String idVille,
			String telephone, boolean estFidele) {
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;	
		this.idVille = idVille;
		this.telephone = telephone;
		this.particulierAssociation = "Particulier";
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
		ajouterBDD();
		ajouterFideliteBDD();
	}

	/**
     * Retourne le nom du particulier
     * 
     * @return Le nom du particulier
     * 
     */
	public String getNom() {
		return nom;
	}

	/**
     * Met à jour le nom du particulier
     * 
     * @param nom
     *            Le nom du particulier
     * 
     */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
     * Retourne le prénom du particulier
     * 
     * @return Le prénom du particulier
     * 
     */
	public String getPrenom() {
		return prenom;
	}

	/**
     * Met à jour le prénom du particulier
     * 
     * @param prenom
     *            Le prénom du particulier
     * 
     */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Ajoute le particulier dans la table CLIENT de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par créer une chaîne de caractères dépendant de 
	 * la valeur du booléen (Désactivé si le booléen est faux, Activé sinon)
	 * 
	 * La requête se construit ensuite en fonction des caractéristiques de l'article
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
	public void ajouterBDD() {
		
		String actif = null;

		if (this.estActif == false) {
			actif = "Désactivé";
		} else {
			actif = "Activé";
		}

		String requete = "INSERT INTO CLIENT (IDCLIENT, NOMCLIENT, PRENOMCLIENT, ADRESSECLIENT, IDVILLE"
				+ " , TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "
				+ "'"
				+ this.mail
				+ "',"
				+ "'"
				+ this.nom
				+ "',"
				+ "'"
				+ this.prenom
				+ "',"
				+ "'"
				+ this.adresse
				+ "',"
				+ "'"
				+ this.idVille
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
	 * Modifie les caractéristiques du particulier dans la table CLIENT de la base de données
	 * 
	 * <p>
	 * Étant donné que le client peut modifier son code postal et non l'identifiant de la ville
	 * (auquel il n'a pas accès), la méthode commence par récupérer cet identifiant dans la table VILLE
	 *
	 * La requête se construit ensuite en fonction des caractéristiques de l'article
	 * saisies lors de l'appel de la méthode
	 * </p> 
	 * 
	 * @see BDD
	 */
	
	public static void modifierBDDparticulier(String idClient, String nom,
			String prenom, String adresse,String codePostal, String telephone) {

		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE", "CODEPOSTAL", codePostal);
		
		String requete = " UPDATE CLIENT SET NOMCLIENT='" + nom
				+ "',PRENOMCLIENT='" + prenom + "',ADRESSECLIENT='" + adresse
				+  "',IDVILLE='" + idVille
				+ "',TELEPHONE='" + telephone  
				+ "' " + "WHERE IDCLIENT ='" + idClient + "'";

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}
	


}
