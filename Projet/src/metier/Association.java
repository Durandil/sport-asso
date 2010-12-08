package metier;

import java.util.ArrayList;

import ihm.Accueil.FenetreDialogIdentification;
import basededonnees.SGBD;

public class Association extends Client {

	private String denomination;


	// Constructeur d'une Association :
	// Celle-ci se d�finit (entre autres) par une d�nomination
	
	// Le constructeur ajoute l'association dans la table CLIENTS
	// Et g�n�re un mot de passe lors de l'instanciation

	public Association(String denomination, String mail, String adresse,
			String codePostal, String nomVille, String idVille, String telephone, boolean estFidele) {
		this.denomination = denomination;
		this.mail = mail;
		this.adresse = adresse;
		this.idVille = idVille;
		this.telephone = telephone;
		this.particulierAssociation = "Association";
		this.nbPointsFidelite = 0;
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
		ajouterBDD();
		ajouterFideliteBDD();
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
		
		
		String requete = "INSERT INTO CLIENT (IDCLIENT, DENOMINATIONCLIENT, ADRESSECLIENT, IDVILLE" +
		", TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "+
		"'"+this.mail+"',"
		+"'"+this.denomination+"',"
		+"'"+this.adresse+"',"
		+"'"+this.idVille+"',"
		+"'"+this.telephone+"',"
		+"'"+actif+"',"
		+"'"+this.motDePasse+"')";
		

		SGBD.executeUpdate(requete);
	}
	
	public void ajouterFideliteBDD(){
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
	
	
	public static void modifierBDDassoc(String idClient,String denomination,String adresse,String codePostal,String telephone){
		
		
		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE", "CODEPOSTAL", codePostal);
		
		String requete = " UPDATE CLIENT SET DENOMINATIONCLIENT='"+denomination+"',ADRESSECLIENT='"+adresse+"',IDVILLE='"+idVille+"',TELEPHONE='"+telephone
		+"' WHERE IDCLIENT ='" + idClient +"'";
		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
	}

}
