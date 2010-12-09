package metier;

import java.util.ArrayList;

import ihm.Accueil.FenetreDialogIdentification;
import basededonnees.SGBD;

/**
 * <b>La classe Particulier repr�sente un particulier</b>
 * <p>
 * Un particulier est caract�ris� par les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client repr�sentant l'association</li>
 * <li>Un nom</li>
 * <li>Un pr�nom</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville o� l'association est bas�e</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Le statut du client (ici, "Particulier")</li>
 * <li>Un bool�en d�terminant si l'association poss�de une carte de fid�lit�</li>
 * <li>L'�tat du compte de l'association (Activ� si le bool�en correspondant est vrai et D�sactiv� sinon)</li>
 * <li>Un mot de passe g�n�r� automatiquement</li>
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
	// Le Particulier est d�fini (entre autres) par un nom et un pr�nom

	// Le constructeur permet d'ajouter le particulier dans la base CLIENTS
	// Et g�n�re un mot de passe lors de l'instanciation
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

	// M�thode permettant d'ajouter � la table CLIENTS une association
	// *Note* Le fait d'avoir une carte de fid�lit�/d'avoir un compte activ�
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
			actif = "D�sactiv�";
		} else {
			actif = "Activ�";
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

//	Cette m�thode ins�re une nouvelle ligne dans la table CARTE_FIDELITE
		
	
	public void ajouterFideliteBDD() {
		if (this.estFidele) {
				
					CarteFidelite cf = new CarteFidelite(this.mail, 0);
					
		}
	}
	
//	Cette m�thode est appel�e lorsque le client d�sire modifier certaines informations le concernant
//	Elle r�cup�re en premier lieu l'idVille correspondant au code postal saisi par le client et met ensuite
//	� jour la table CLIENT
	
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
