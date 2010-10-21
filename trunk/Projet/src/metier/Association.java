package metier;

import basededonnees.SGBD;

public class Association extends Client {

	private String denomination;

	// Constructeur d'une Association :
	// Celle-ci se d�finit (entre autres) par une d�nomination
	
	// Le constructeur ajoute l'association dans la table CLIENTS
	// Et g�n�re un mot de passe lors de l'instanciation
	public Association(String denomination, String mail, String adresse,String ville,
			String codePostal, String telephone) {
		this.denomination = denomination;
		this.mail = mail;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.particulierAssociation = "Association";
		this.nbPointsFidelite = 0;
		this.estFidele = false;
		this.estActif = true;
		ajouterBDD();
		this.motDePasse = genererMdp();
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

		return denomination + " est une " + particulierAssociation
				+ "\n Mail : " + mail + "\n Adresse : " + adresse
				+ "\n Telephone : " + telephone + "\n Carte de fid�lit� ? "
				+ fidele + "\n Nombre de points fid�lit� : " + nbPointsFidelite
				+ "\n �tat du compte : " + actif;

	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
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
		
		String requete = "INSERT INTO CLIENTS (MAIL, DENOMINATION, ADRESSE, VILLE" +
				", CODEPOSTAL, TELEPHONE, CARTEFIDEL, NBPOINTS, ETATCOMPTE) VALUES ( "+
				"'"+this.mail+"',"
				+"'"+this.denomination+"',"
				+"'"+this.adresse+"',"
				+"'"+this.ville+"',"
				+"'"+this.codePostal+"',"
				+"'"+this.telephone+"',"
				+"'"+fidele+"',"
				+"'"+this.nbPointsFidelite+"',"
				+"'"+actif+"')";
		SGBD.executeUpdate(requete);
	}

}
