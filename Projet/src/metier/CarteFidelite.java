package metier;

import java.util.ArrayList;

import basededonnees.SGBD;

/**
 * <b>La classe CarteFidelite représente une carte de fidélité</b>
 * <p>
 * Une carte de fidélité est caractérisée par les informations suivantes  :
 * <ul>
 * <li>Un identifiant unique et non modifiable, de la forme FIDxxxxx</li>
 * <li>Une adresse mail, identifiant du client qui possède la carte</li>
 * <li>Le nombre de points qu'elle possède</li>
 * </ul>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @see BDD,Client
 */
public class CarteFidelite {
	
	/**
	 * L'identifiant de la carte de fidélité, non modifiable
	 * 
	 * @see CarteFidelite#ajouterBDD()
	 */
	private String idCarte;
	
	
	/**
	 * L'adrese mail (l'identifiant) du client qui possède la carte
	 * 
	 * @see CarteFidelite#ajouterBDD()
	 */
	private String idClient;
	
	
	/**
	 * Le nombre de points de la carte de fidélité
	 * 
	 * @see CarteFidelite#ajouterBDD()
	 */
	private int nombreDePoints;
	
	
	/**
	 * Constructeur de la classe CarteFidelite
	 * <p>
	 * Ce constructeur fait appel à la méthode ajouterBDD()
	 * qui ajoute la carte de fidelité dans la base de données.
	 * Il ne prend pas en paramètre l'identifiant de la carte car ce dernier est 
	 * automatiquement généré lorsque la carte est ajoutée dans la base
	 * </p>
	 * 
	 * @param idClient
	 *            Le mail du client (son identifiant)
	 * @param nombreDePoints
	 *            Le nombre de points de la carte de fidélité
	 *            
	 * @see Client#ajouterFideliteBDD()
	 */

	
	public CarteFidelite(String idClient, int nombreDePoints) {
		super();
		
		this.idClient = idClient;
		this.nombreDePoints = nombreDePoints;
		ajouterBDD();
	}

	 /**
     * Retourne l'id de la carte de fidélité
     * 
     * @return L'identifiant de la carte de fidélité
     * 
     */
	public String getIdCarte() {
		return idCarte;
	}
	
	
    /**
     * Met à jour l'identifiant de la carte de fidélité
     * 
	 * @param idCarte
	 *            L'identifiant de la carte de fidélité
     * 
     */
	public void setIdCarte(String idCarte) {
		this.idCarte = idCarte;
	}
	
	 /**
     * Retourne le mail du client (son identifiant)
     * 
     * @return L'identifiant du client
     * 
     */
	public String getIdClient() {
		return idClient;
	}
	
    /**
     * Met à jour l'identifiant du client
     * 
	 * @param idClient
	 *            L'identifiant du client
     * 
     */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	 /**
     * Retourne le nombre de points de la carte de fidélité
     * 
     * @return Le nombre de points de la carte de fidélité
     * 
     */
	public int getNombreDePoints() {
		return nombreDePoints;
	}
	
    /**
     * Met à jour le nombre de points de la carte de fidélité
     * 
	 * @param  nombreDePoints
	 *            Le nombre de points de la carte de fidélité
     * 
     */
	public void setNombreDePoints(int nombreDePoints) {
		this.nombreDePoints = nombreDePoints;
	}


	
	/**
	 * Ajoute la carte de fidelite dans la table CARTE_FIDELITE de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table afin
	 * de générer l'identifiant de l'article dans le format approprié.
	 * 
	 * La requête se construit ensuite en fonction des caractéristiques de l'article
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
	
	public void ajouterBDD(){
		
			
			ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
			"S_FIDELITE.NextVal");

	
	int numeroFidelite = 0;

	numeroFidelite = Integer.parseInt(idNonFini.get(0));

	if (numeroFidelite < 10) {
		this.idCarte = "FID0000" + numeroFidelite;
	}
	if (numeroFidelite < 100 && numeroFidelite >= 10) {
		this.idCarte = "FID000" + numeroFidelite;
	}
	if (numeroFidelite < 1000 && numeroFidelite >= 100) {
		this.idCarte = "FID00" + numeroFidelite;
	}
	if (numeroFidelite < 10000 && numeroFidelite >= 1000) {
		this.idCarte = "FID0" + numeroFidelite;
	}
	if (numeroFidelite < 100000 && numeroFidelite >= 10000) {
		this.idCarte = "FID" + numeroFidelite;
	}


	String requete2 = "INSERT INTO CARTE_FIDELITE (IDCARTEFIDELITE, NBPOINTS, IDCLIENT)"
			+ "VALUES ('" 
			+ this.idCarte
			+ "','" 
			+ this.nombreDePoints
			+ "','"
			+ this.idClient
			+ "')";
	System.out.println(requete2);
	SGBD.executeUpdate(requete2);

		
	}
	
	/**
	 * Modifie le nombre de points sur la carte de fidelite dans la table CARTE_FIDELITE de la base de données
	 * 
	 * <p>
	 * Cette méthode effectue la modification du nombre de points sur la carte de fidelité associé
	 * à l'identifiant du client entré en paramètre.
	 * </p> 
	 * 
	 * @see BDD
	 * 
	 * @param String idClient : identifiant du client
	 * @param int nbrePoints : nouveau nombre de points sur la carte
	 */
	
	public static void modifierBDDcarteFidelite(String idClient,int nbrePoints){
		
		String requete="UPDATE CARTE_FIDELITE SET NBPOINTS="+nbrePoints+" WHERE IDCLIENT='"+idClient+"'";
		
		SGBD.executeUpdate(requete);
		
	}
	
	/**
	 * calcule le bon de réduction associé au nombre de points sur la carte de fidelite 
	 * 
	 * <p>
	 * Cette méthode va en fonction du nombre de points sur la carte de fidélité calculer 
	 * le montant du bon de réduction pour les clients possédant un compte fidélité
	 * </p> 
	 * 
	 * @param int nbrePoints : nombre de points sur la carte
	 */
	public static int calculerBonsReductions(int nbrePoints){
		int bonAchat=0;
		
		if(nbrePoints <= 0) {
			bonAchat=0;
		}
		else if(nbrePoints<101) {
			bonAchat=5;
		}
		else if(nbrePoints<201){
			bonAchat=12;
		}
		else if(nbrePoints<501){
			bonAchat=30;
		}
		else if(nbrePoints<1001){
			bonAchat=70;
		}
		else if(nbrePoints<2001){
			bonAchat = 150 ;
		}
		else bonAchat=150 ;
		
		return bonAchat;
	}
	
}
