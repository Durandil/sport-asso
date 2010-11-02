package application;

import java.sql.Date;
import java.util.ArrayList;

import metier.Article;
import metier.Association;
import metier.Categorie;
import metier.Commande;
import metier.Gerant;
import metier.LigneCommande;
import metier.Particulier;
import basededonnees.BDD;
import basededonnees.SGBD;

public class Principale {

/** 
 * @throws Exception **/

	public static void main(String[] args) throws Exception {

		//Gerant.activDesactivCompte();
		BDD.init();
			
//
//		  //Création de la table CLIENTS 
//		BDD.creerTable("CLIENTS");
//		  
//		  
//		  //Instanciation d'une association 
//		Association ass = new Association("Idylliste", "idylliste@ensai.fr", "Ensai", "Rennes",
//		  "35000", "02 23 45 78 96",true);
//		  
//		  // Instanciation d'une  autre Association 
//		Association ass2 = new Association("BDE", "bde@ensai.fr", "Ensai", "Rennes",
//				  "35000", "02 56 84 22 10",true);
//		
//		  //Instanciation d'un particulier 
//		Particulier p = new Particulier("Louvel", "Alexis", "alexis.louvel@ensai.fr",
//		  "Campus de KL", "Bruz", "35000", "02 56 98 66 33",true);


		
//		  // Création de la table ARTICLES 
//			BDD.creerTable("ARTICLES");
//		  
//		  // Instanciation de 10 articles (et ajout dans la table 
//		Article art1 = new Article("ART001", "Maillot", "Omnisport", 200,
//				50.20, 0,"Rupture de stock", "2");
//		Article art2 = new Article("ART002", "Ballon", "Sport d''équipe", 200,
//				15.30, 100,"En stock", "1");
//		Article art3 = new Article("ART003", "Chaussettes Blanches",
//				"Omnisport", 50, 10, 50,"En stock", "1");
//		Article art4 = new Article("ART004", "Bonnet de bain", "Natation", 75,
//				15, 0,"Rupture de stock", "1");
//		Article art5 = new Article("ART005", "Club", "Golf", 500, 100, 10,"En stock", "3");
//		Article art6 = new Article("ART006", "Chaussettes Noires", "Omnisport",
//				50, 10, 50,"En stock", "1");
//		Article art7 = new Article("ART007", "Selle", "Équitation", 250, 50, 5,"Déstockage",
//				"3");
//		Article art8 = new Article("ART008", "Chaussures NIKE", "Running", 250,
//				100, 60,"En stock", "2");
//		Article art9 = new Article("ART009", "Kimono", "Sport de combat", 750,
//				35, 30,"Déstockage", "2");
//		Article art10 = new Article("ART010", "Raquette de tennis débutant",
//				"Sport de raquette", 170, 20, 50,"En stock", "3");

		/** TODO : Gérer le format Date **/
		
		// Création de 2 commandes
		// Création et MAJ des tables COMMANDES et INFOCOMMANDES
/*		
		BDD.creerTable("COMMANDES");
		BDD.creerTable("INFOCOMMANDES");
		LigneCommande lc1 = new LigneCommande("ART002", 3);
		LigneCommande lc2 = new LigneCommande("ART001", 1);
		ArrayList al = new ArrayList<LigneCommande>();
		al.add(lc1);
		al.add(lc2);


		
		
		String dateS = "05/12/2010";
		Date date= SGBD.stringToDate(dateS,"dd/MM/yyyy");
		Commande c1 = new Commande("COM1", "alexis.louvel@ensai.fr", al, date);

		  
		 
		
		LigneCommande lc3 = new LigneCommande("ART010", 12);
		LigneCommande lc4 = new LigneCommande("ART007", 3);
		LigneCommande lc5 = new LigneCommande("ART008", 7);
		ArrayList al2 = new ArrayList<LigneCommande>();
		al2.add(lc3);
		al2.add(lc4);
		al2.add(lc5);

		String dateS2 = "19/10/2010";
		Date date2= SGBD.stringToDate(dateS2,"dd/MM/yyyy");
		Commande c2 = new Commande("COM2", "idylliste@ensai.fr", al2, date2);
		
*/	 

		
/*		  
		  // Création de la table CAT1(Catégorie 1)
		  
		ArrayList<double[]> alcat1 = new ArrayList<double[]>();

		double[] d1 = new double[2];
		d1[0] = 10.0;
		d1[1] = 0.9;
		double[] d2 = new double[2];
		d2[0] = 20.0;
		d2[1] = 0.8;
		double[] d3 = new double[2];
		d3[0] = 30.0;
		d3[1] = 0.7;
		alcat1.add(d1);
		alcat1.add(d2);
		alcat1.add(d3);

		Categorie cat1 = new Categorie("CAT1", alcat1);
		cat1.creerTable();
		 
*/
	}

}
