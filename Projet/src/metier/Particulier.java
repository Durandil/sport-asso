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
 * @see BDD,Client,Utilisateur
 */

public class Particulier extends Client {

	private String nom;
	private String prenom;

	// Constructeur d'un client Particulier
	// Le Particulier est défini (entre autres) par un nom et un prénom

	// Le constructeur permet d'ajouter le particulier dans la base CLIENTS
	// Et génère un mot de passe lors de l'instanciation
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	// Méthode permettant d'ajouter à la table CLIENTS une association
	// *Note* Le fait d'avoir une carte de fidélité/d'avoir un compte activé
	// est pris en compte dans l'insertion
	public void ajouterBDD() {
		String fidele = null;
		String actif = null;

		if (this.estFidele == false) {
			fidele = "Non";
		} else {
			fidele = "Oui";
		}

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

//	Cette méthode insère une nouvelle ligne dans la table CARTE_FIDELITE
		
	
	public void ajouterFideliteBDD() {
		if (this.estFidele) {
				
					CarteFidelite cf = new CarteFidelite(this.mail, 0);
					
		}
	}
	
//	Cette méthode est appelée lorsque le client désire modifier certaines informations le concernant
//	Elle récupère en premier lieu l'idVille correspondant au code postal saisi par le client et met ensuite
//	à jour la table CLIENT
	
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
