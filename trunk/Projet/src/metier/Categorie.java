package metier;

import java.util.ArrayList;
import java.util.HashMap;

import basededonnees.SGBD;

public class Categorie {

	// Constructeur d'une catégorie d'articles
	// Celle-ci se définit par un identifiant et une liste de couples (quantité/réduction)
	// La réduction est exprimée à travers un coefficient multiplicateur inférieur à 1
	public Categorie(String identifiant,
			ArrayList<double[]> couplePromo) {
		this.identifiant = identifiant;
		this.couplePromo = couplePromo;
	}
	
	String identifiant;
	ArrayList<double[]> couplePromo;
	
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public ArrayList<double[]> getCouplePromo() {
		return couplePromo;
	}
	public void setCouplePromo(ArrayList<double[]> couplePromo) {
		this.couplePromo = couplePromo;
	}


	// Méthode permettant de créer une table :
	// Chaque Catégorie à une table qui lui est associée
	
	// Au moment de l'insertion, on ajoute le 1er élément du couple i puis le second
	public void creerTable(){
		SGBD.executeUpdate("DROP TABLE " + this.identifiant);
		SGBD.executeUpdate("CREATE TABLE " + this.identifiant
				+ " (QUANTITE NUMBER(3),"
				+ " COEFFICIENT NUMBER(3,2) )"
				);
		for(int i=0; i <this.couplePromo.size();i++)
		{

			String requete = "INSERT INTO " + this.identifiant+ " (QUANTITE, COEFFICIENT) VALUES ( "
				
				+ this.couplePromo.get(i)[0]
				+ ","
				+ this.couplePromo.get(i)[1]
				+ ") ";
		
		SGBD.executeUpdate(requete);
		}
		
	}
	
}
