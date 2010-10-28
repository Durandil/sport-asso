package metier;

import basededonnees.SGBD;

public class Particulier extends Client {

	private String nom;
	private String prenom;

	
	// Constructeur d'un client Particulier
	// Le Particulier est défini (entre autres) par un nom et un prénom
	
	// Le constructeur permet d'ajouter le particulier dans la base CLIENTS
	// Et génère un mot de passe lors de l'instanciation
	public Particulier(String nom, String prenom, String mail, String adresse,
			String ville, String codePostal, String telephone, boolean estFidele) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.particulierAssociation = "Particulier";
		this.nbPointsFidelite = 0;
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
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
		
		
		String requete = "INSERT INTO CLIENTS (MAIL, NOM, PRENOM, ADRESSE, VILLE" +
				", CODEPOSTAL, TELEPHONE, CARTEFIDEL, NBPOINTS, ETATCOMPTE, MOTDEPASSE) VALUES ( "+
				"'"+this.mail+"',"
				+"'"+this.nom+"',"
				+"'"+this.prenom+"',"
				+"'"+this.adresse+"',"
				+"'"+this.ville+"',"
				+"'"+this.codePostal+"',"
				+"'"+this.telephone+"',"
				+"'"+fidele+"',"
				+"'"+this.nbPointsFidelite+"',"
				+"'"+actif+"',"
				+"'"+this.motDePasse+"')";
		SGBD.executeUpdate(requete);
	}
}
