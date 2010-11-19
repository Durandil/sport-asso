package metier;

import ihm.FenetreDialogIdentification;
import basededonnees.SGBD;

public class Particulier extends Client {

	private String nom;
	private String prenom;

	
	// Constructeur d'un client Particulier
	// Le Particulier est d�fini (entre autres) par un nom et un pr�nom
	
	// Le constructeur permet d'ajouter le particulier dans la base CLIENTS
	// Et g�n�re un mot de passe lors de l'instanciation
	public Particulier(String nom, String prenom, String mail, String adresse,
			String codeCommune, String telephone, boolean estFidele) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.codeCommune = codeCommune;
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
	// M�thode toString qui est (pour l'instant) inutile
	public String toString() {
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

		return prenom + " " + nom + " est un " + particulierAssociation
				+ "\n Mail : " + mail + "\n Adresse : " + adresse
				+ "\n Telephone : " + telephone + "\n Carte de fid�lit� ? "
				+ fidele + "\n Nombre de points fid�lit� : " + nbPointsFidelite
				+ "\n �tat du compte : " + actif;

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
			actif = "D�sactiv�";
		} else {
			actif = "Activ�";
		}
		
		
		
		String requete = "INSERT INTO CLIENT (IDCLIENT, NOMCLIENT, PRENOMCLIENT, ADRESSECLIENT, CODECOMMUNE" +
				", TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "+
				"'"+this.mail+"',"
				+"'"+this.nom+"',"
				+"'"+this.prenom+"',"
				+"'"+this.adresse+"',"
				+"'"+this.codeCommune+"',"
				+"'"+this.telephone+"',"
				+"'"+actif+"',"
				+"'"+this.motDePasse+"')";
		SGBD.executeUpdate(requete);
		
		
	if (this.estFidele) {
	
		CompteurIdcarte++;
		String idCarte="i"+CompteurIdcarte;
		String requete2 = "INSERT INTO CARTE_FIDELITE (IDCARTEFIDELITE, NBPOINTS, IDCLIENT)" +
		"VALUES (" +
		"'"+idCarte+"',"
		+"'0',"
		+"'"+FenetreDialogIdentification.clientUserIdentifiant+"')";
		SGBD.executeUpdate(requete2);
	}
	
	}
}