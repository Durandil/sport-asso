package application;

import ihm.FenetreCompte;
import ihm.MenuGerant;
import ihm.MenuUtilisateur;

import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;

import metier.Article;
import metier.Association;
import metier.Categorie;
import metier.Commande;
import metier.Gerant;
import metier.LigneCommande;
import metier.Message;
import metier.Particulier;
import metier.Promotion;
import basededonnees.BDD;
import basededonnees.SGBD;

public class Principale {

/** 
 * @throws Exception **/

	public static void main(String[] args) throws Exception {
/** TODO : Instancier un client via l'ihm**/
		//Initialiser la base de données
		//BDD.init();
		
		FenetreCompte fen=new FenetreCompte();
		//MenuUtilisateur men = new MenuUtilisateur();
		//MenuGerant menu= new MenuGerant();
		String id = SGBD.selectStringConditionString("CLIENT", "MOTDEPASSE","IDCLIENT", "iueihtam@laposte.fr");
		System.out.println(id);
		
//		java.util.Date date = new java.util.Date();
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//		String s = SGBD.transformation(sqlDate);
//		System.out.println(s);
//
		
		  /**Instanciation de 2 articles tests (et ajout dans la table **/
//		Article art1 = new Article("ART99999", "Maillot spigouleux","TYP00001", 200,
//				50.20, 0,"Rupture de stock", "CAT00001");
//		Article art2 = new Article("ART99998", "Ballon spigouleux", "TYP00002", 200,
//				15.30, 100,"En stock", "CAT00002");
		
		/**Création d'une promo test**/
//		String dateS = "05/11/2010";
//		String dateS2 = "07/11/2010";
//		Date date= SGBD.stringToDate(dateS,"dd/MM/yyyy");
//		Date date2= SGBD.stringToDate(dateS2,"dd/MM/yyyy");
//		boolean b=true;
//		Promotion p = new Promotion("PRO99999","Promo test",date,date2,0.7,b);
		
		/**Instanciation d'une catégorie test**/
//		Categorie cat = new Categorie("CAT99999","Test",100);
		
	
		
//		  
		  
		/**Instanciation d'une association**/ 

//		ASSOCIATION ASS = NEW ASSOCIATION("IDYLLISTE", "IDYLLISTE@ENSAI.FR", "ENSAI", "35047",
//		  "0299451210", TRUE);
//		  
		/**Instanciation d'une autre association**/ 
//		Association ass2 = new Association("BDE", "bde@ensai.fr", "Ensai",
//				  "35238", "0256842210",true);
//		
		/**Instanciation d'un particulier**/ 

		//Particulier p = new Particulier("Louvel", "Alexis", "alexis.louvel@ensai.fr","Campus de KL", "35047", "0256986633",true);

		/**Création d'un message test**/
//		String dateSM = "12/11/2010";
//		Date dateM = SGBD.stringToDate(dateSM, "dd/MM/yyyy");
//		Message m = new Message("Content", "Votre magasin est trop génial","idylliste@ensai.fr",dateM);
//	




		
		
		/** Création de 2 commandes **/
		/** ATTENTION : Il faut que les id d'articles et de clients existent déjà dans leurs tables respectives ! **/
		// Création et MAJ des tables COMMANDE et LISTING_ARTICLES_COMMANDES
		

//		LigneCommande lc1 = new LigneCommande("ART00002", 3);
//		LigneCommande lc2 = new LigneCommande("ART00001", 1);
//		ArrayList al = new ArrayList<LigneCommande>();
//		al.add(lc1);
//		al.add(lc2);
//	
//		String dateS = "05/12/2010";
//		Date date= SGBD.stringToDate(dateS,"dd/MM/yyyy");
//		Commande c1 = new Commande("COM99999", "alexis.louvel@ensai.fr", al, date);
//
//		  
		 
		
//		LigneCommande lc3 = new LigneCommande("ART00004", 12);
//		LigneCommande lc4 = new LigneCommande("ART00001", 3);
//		LigneCommande lc5 = new LigneCommande("ART00003", 7);
//		ArrayList al2 = new ArrayList<LigneCommande>();
//		al2.add(lc3);
//		al2.add(lc4);
//		al2.add(lc5);
//
//		String dateS2 = "19/10/2010";
//		Date date2= SGBD.stringToDate(dateS2,"dd/MM/yyyy");
//		Commande c2 = new Commande("COM99998", "idylliste@ensai.fr", al2, date2);
//		
 


	}

}
