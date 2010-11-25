package metier;

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
			String codePostal, String nomVille, String idVille, String telephone, boolean estFidele) {
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
	//		  est pris en compte dans l'insertion
	public void ajouterBDD(){
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
		
		
		
		String requete = "INSERT INTO CLIENT (IDCLIENT, NOMCLIENT, PRENOMCLIENT, ADRESSECLIENT, IDVILLE" +
				",NOMVILLE, CODEPOSTAL, TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "+
				"'"+this.mail+"',"
				+"'"+this.nom+"',"
				+"'"+this.prenom+"',"
				+"'"+this.adresse+"',"
				+"'"+this.idVille+"',"
				+"'"+this.nomVille+"',"
				+"'"+this.codePostal+"',"
				+"'"+this.telephone+"',"
				+"'"+actif+"',"
				+"'"+this.motDePasse+"')";

		SGBD.executeUpdate(requete);
		


		if (this.estFidele) {
		System.out.println("est fidèle");
			CompteurIdcarte++;
			String idCarte=""+CompteurIdcarte;
			String requete2 = "INSERT INTO CARTE_FIDELITE (IDCARTEFIDELITE, NBPOINTS, IDCLIENT)" +
			"VALUES ( S_FIDELITE.NextVal" +","+
			"0,"
			+"'"+FenetreDialogIdentification.clientUserIdentifiant+"')";
			SGBD.executeUpdate(requete2);
		}
	

	}


}
