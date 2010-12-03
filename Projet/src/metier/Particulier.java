package metier;

import java.util.ArrayList;

import ihm.FenetreDialogIdentification;
import basededonnees.SGBD;

public class Particulier extends Client {

	private String nom;
	private String prenom;

	// Constructeur d'un client Particulier
	// Le Particulier est défini (entre autres) par un nom et un prénom

	// Le constructeur permet d'ajouter le particulier dans la base CLIENTS
	// Et génère un mot de passe lors de l'instanciation
	public Particulier(String nom, String prenom, String mail, String adresse,
			String codePostal, String nomVille, String idVille,
			String telephone, boolean estFidele) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.setNomVille(nomVille);
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.particulierAssociation = "Particulier";
		this.nbPointsFidelite = 0;
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
		this.idVille = idVille;
		ajouterBDD();
		ajouterFideliteBDD();
		System.out.println("Votre mot de passe est : " + this.motDePasse);
	}

	@Override
	// Méthode toString qui est (pour l'instant) inutile
	public String toString() {
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

		return prenom + " " + nom + " est un " + particulierAssociation
				+ "\n Mail : " + mail + "\n Adresse : " + adresse
				+ "\n Telephone : " + telephone + "\n Carte de fidélité ? "
				+ fidele + "\n Nombre de points fidélité : " + nbPointsFidelite
				+ "\n État du compte : " + actif;

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
				+ ",NOMVILLE, CODEPOSTAL, TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "
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
				+ this.nomVille
				+ "',"
				+ "'"
				+ this.codePostal
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

	public void ajouterFideliteBDD() {
		if (this.estFidele) {

			ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
					"S_FIDELITE.NextVal");

			String numFidelite = "";
			int numeroFidelite = 0;

			numeroFidelite = Integer.parseInt(idNonFini.get(0));

			if (numeroFidelite < 10) {
				numFidelite = "FID0000" + numeroFidelite;
			}
			if (numeroFidelite < 100 && numeroFidelite >= 10) {
				numFidelite = "FID000" + numeroFidelite;
			}
			if (numeroFidelite < 1000 && numeroFidelite >= 100) {
				numFidelite = "FID00" + numeroFidelite;
			}
			if (numeroFidelite < 10000 && numeroFidelite >= 1000) {
				numFidelite = "FID0" + numeroFidelite;
			}
			if (numeroFidelite < 100000 && numeroFidelite >= 10000) {
				numFidelite = "FID" + numeroFidelite;
			}


			String requete2 = "INSERT INTO CARTE_FIDELITE (IDCARTEFIDELITE, NBPOINTS, IDCLIENT)"
					+ "VALUES ('" 
					+ numFidelite
					+ "','0',"
					+ "'"
					+ this.mail
					+ "')";
			System.out.println(requete2);
			SGBD.executeUpdate(requete2);
		}
	}

	public static void modifierBDDparticulier(String idClient, String nom,
			String prenom, String adresse, String codePostal, String telephone) {

		String ville = SGBD.selectStringConditionString("VILLE", "NOMVILLE",
				"CODEPOSTAL", codePostal);
		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE",
				"CODEPOSTAL", codePostal);

		String requete = " UPDATE CLIENT SET NOMCLIENT='" + nom
				+ "',PRENOMCLIENT='" + prenom + "',ADRESSECLIENT='" + adresse
				+ "',NOMVILLE='" + ville + "',IDVILLE='" + idVille
				+ "',TELEPHONE='" + telephone + "',CODEPOSTAL='" + codePostal
				+ "' " + "WHERE IDCLIENT ='" + idClient + "'";

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}
	


}
