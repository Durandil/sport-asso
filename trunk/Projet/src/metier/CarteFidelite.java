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

	public String getIdCarte() {
		return idCarte;
	}
	public void setIdCarte(String idCarte) {
		this.idCarte = idCarte;
	}
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public int getNombreDePoints() {
		return nombreDePoints;
	}
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
}
