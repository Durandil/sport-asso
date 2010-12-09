package metier;

import java.util.ArrayList;



/**
 * <b>La classe Association représente une association</b>
 * <p>
 * Une association est caractérisée par les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client représentant l'association</li>
 * <li>Une dénomination</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville où l'association est basée</li>
 * <li>Un numéro de téléphone</li>
 * <li>Le statut du client (ici, "Association")</li>
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

import ihm.Accueil.FenetreDialogIdentification;
import basededonnees.SGBD;

public class Association extends Client {

	private String denomination;


	// Constructeur d'une Association :
	// Celle-ci se définit (entre autres) par une dénomination	
	// Le constructeur ajoute l'association dans la table CLIENT
	// Et génère un mot de passe lors de l'instanciation

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
	
	
	// Méthode permettant d'ajouter à la table CLIENTS une association
	// Le fait d'avoir une carte de fidélité/d'avoir un compte activé
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
	
//	Cette méthode insère une nouvelle ligne dans la table CARTE_FIDELITE
	
	public void ajouterFideliteBDD(){
		if (this.estFidele) {
			
			CarteFidelite cf = new CarteFidelite(this.mail, 0);

		}
	}
	
	
//	Cette méthode est appelée lorsque le client désire modifier certaines informations le concernant
//	Elle récupère en premier lieu l'idVille correspondant au code postal saisi par le client et met ensuite
//	à jour la table CLIENT
	
	public static void modifierBDDassoc(String idClient,String denomination,String adresse,String codePostal,String telephone){
		
		
		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE", "CODEPOSTAL", codePostal);
		
		String requete = " UPDATE CLIENT SET DENOMINATIONCLIENT='"+denomination+"',ADRESSECLIENT='"+adresse+"',IDVILLE='"+idVille+"',TELEPHONE='"+telephone
		+"' WHERE IDCLIENT ='" + idClient +"'";
		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
	}

}
