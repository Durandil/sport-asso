package metier;

import ihm.FenetreDialogIdentification;
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
		this.codePostal = codePostal;
		this.nomVille = nomVille;
		this.idVille = idVille;
		this.telephone = telephone;
		this.particulierAssociation = "Association";
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
		",NOMVILLE, CODEPOSTAL, TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "+
		"'"+this.mail+"',"
		+"'"+this.denomination+"',"
		+"'"+this.adresse+"',"
		+"'"+this.idVille+"',"
		+"'"+this.nomVille+"',"
		+"'"+this.codePostal+"',"
		+"'"+this.telephone+"',"
		+"'"+actif+"',"
		+"'"+this.motDePasse+"')";
		

		SGBD.executeUpdate(requete);
		

		if (this.estFidele) {
			
			CompteurIdcarte++;
			String idCarte=""+CompteurIdcarte;
			String requete2 = "INSERT INTO CARTE_FIDELITE (IDCARTEFIDELITE, NBPOINTS, IDCLIENT)" +
			"VALUES (S_FIDELITE.NextVal,"
			+"'0',"
			+"'"+this.mail+"')";
			System.out.println(requete2);
			SGBD.executeUpdate(requete2);
		}
	}
	
public static void modifierBDDassoc(String idClient,String nom,String prenom,String denomination,String adresse,String codePostal,String telephone){
		
		String ville = SGBD.selectStringConditionString("VILLE", "NOMVILLE", "CODEPOSTAL", codePostal);
		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE", "CODEPOSTAL", codePostal);
		
		String requete = " UPDATE CLIENT SET NOMCLIENT='"+nom+"',PRENOMCLIENT='"+prenom+"',DENOMINATIONCLIENT='"+denomination+"',ADRESSECLIENT='"+adresse+"',NOMVILLE='"+ville+"',IDVILLE='"+idVille+"',TELEPHONE='"+telephone+"',CODEPOSTAL='"+codePostal+"';";
		
		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
	}

}
