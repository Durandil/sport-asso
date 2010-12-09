package metier;

import java.util.ArrayList;

import basededonnees.SGBD;

//	La classe Carte Fidelite représente une carte de fidélité.
//	Celle-ci se décrit par son identifiant, l'identifiant du client auquel elle se rattache
//	ainsi que le nombre de points qu'elle possède
	

public class CarteFidelite {
	
	private String idCarte;
	private String idClient;
	private int nombreDePoints;
	
	
	
//	Constructeur de la carte de fidélité
//	Il ne prend pas en paramètre l'identifiant de la carte car ce dernier est automatiquement généré lorsque
//	la carte est ajoutée dans la base
	
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

//	Méthode ajoutant la carte de fidélité dans la base
//	Elle génère de ce fait un identifiant
	
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
