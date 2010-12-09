package metier;

import java.util.ArrayList;



/**
 * <b>La classe Association repr�sente une association</b>
 * <p>
 * Une association est caract�ris�e par les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client repr�sentant l'association</li>
 * <li>Une d�nomination</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville o� l'association est bas�e</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Le statut du client (ici, "Association")</li>
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

import ihm.Accueil.FenetreDialogIdentification;
import basededonnees.SGBD;

public class Association extends Client {

	private String denomination;


	// Constructeur d'une Association :
	// Celle-ci se d�finit (entre autres) par une d�nomination	
	// Le constructeur ajoute l'association dans la table CLIENT
	// Et g�n�re un mot de passe lors de l'instanciation

	public Association(String denomination, String mail, String adresse,
			String idVille, String telephone, boolean estFidele) {
		this.denomination = denomination;
		this.mail = mail;
		this.adresse = adresse;
		this.idVille = idVille;
		this.telephone = telephone;
		this.particulierAssociation = "Association";
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
		ajouterBDD();
		ajouterFideliteBDD();
		System.out.println("Votre mot de passe est : " + this.motDePasse);

	}



	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	
	// M�thode permettant d'ajouter � la table CLIENTS une association
	// Le fait d'avoir une carte de fid�lit�/d'avoir un compte activ�
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
	
//	Cette m�thode ins�re une nouvelle ligne dans la table CARTE_FIDELITE
	
	public void ajouterFideliteBDD(){
		if (this.estFidele) {
			
			CarteFidelite cf = new CarteFidelite(this.mail, 0);

		}
	}
	
	
//	Cette m�thode est appel�e lorsque le client d�sire modifier certaines informations le concernant
//	Elle r�cup�re en premier lieu l'idVille correspondant au code postal saisi par le client et met ensuite
//	� jour la table CLIENT
	
	public static void modifierBDDassoc(String idClient,String denomination,String adresse,String codePostal,String telephone){
		
		
		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE", "CODEPOSTAL", codePostal);
		
		String requete = " UPDATE CLIENT SET DENOMINATIONCLIENT='"+denomination+"',ADRESSECLIENT='"+adresse+"',IDVILLE='"+idVille+"',TELEPHONE='"+telephone
		+"' WHERE IDCLIENT ='" + idClient +"'";
		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
	}

}
