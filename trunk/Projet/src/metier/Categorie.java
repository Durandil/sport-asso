package metier;

import java.util.ArrayList;
import java.util.HashMap;

import basededonnees.SGBD;

public class Categorie {

	// Constructeur d'une cat�gorie d'articles
	// Celle-ci se d�finit par un identifiant et une liste de couples (quantit�/r�duction)
	// La r�duction est exprim�e � travers un coefficient multiplicateur inf�rieur � 1
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


	// M�thode permettant de cr�er une table :
	// Chaque Cat�gorie � une table qui lui est associ�e
	
	// Au moment de l'insertion, on ajoute le 1er �l�ment du couple i puis le second
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
