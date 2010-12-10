package application;

import ihm.MenuGerant;
import ihm.MenuUtilisateur;
import ihm.Accueil.FenetreCompte;
import ihm.Client.FenetreCommandeArticle;

import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;

import metier.Article;
import metier.Association;
//import metier.Categorie;
import metier.Commande;
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

		//Initialiser la base de données
		//BDD.init();
		
		//System.out.println(SGBD.recupererInformationFideliteClient("cdecavele@ensai.fr").get(0));

		//FenetreCompte fen=new FenetreCompte();
		//MenuUtilisateur men = new MenuUtilisateur();
		MenuGerant menu= new MenuGerant();

//		java.util.Date date = new java.util.Date();
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//		String s = SGBD.transformation(sqlDate);
//		System.out.println(s);
		
		/** test statistique **/
//		String testStat = SGBD.statistiqueArticleClient("bda@ensai.fr");
//		System.out.println("Article le plus commandé : "+ testStat);
		
		/** Test commande article **/
//		java.util.Date date = new java.util.Date();
//		
//		@SuppressWarnings("deprecation")
//		java.sql.Date dateJour = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
//		ArrayList<LigneCommande> listing = new ArrayList<LigneCommande>();
//		listing.add(new LigneCommande("ART00001","50"));
//		
//		Commande testco = new Commande(null, "forum@ensai.fr", listing, dateJour);
		
		/** Test panier Client **/
//		ArrayList<String[]> panierClient = new ArrayList<String[]>();
//		ArrayList<String> listeClients=SGBD.selectListeStringOrdonne("ARTICLE","IDARTICLE","IDARTICLE");
//		
//		for(int i=0;i<listeClients.size();i++){
//			String[] client={listeClients.get(i),"0"};
//			panierClient.add(client);
//		}
//		
//		for (int i = 0; i < panierClient.size(); i++) {
//			System.out.println("ARTICLE : "+panierClient.get(i)[0]+", quantité dans panier :"+FenetreCommandeArticle.panierClient.get(i)[1]);
//		}
		
		  /**Instanciation de 2 articles tests (et ajout dans la table **/
//		Article art1 = new Article("ART99999", "Maillot spigouleux","TYP00001", 200,50.20, 0,"Rupture de stock", "CAT00001");
//		Article art2 = new Article("ART99998", "Ballon spigouleux", "TYP00002", 200,15.30, 100,"En stock", "CAT00002");
		
		/**Création d'une promo test**/
//		String dateS = "05/11/2010";
//		String dateS2 = "07/11/2010";
//		Date date= SGBD.stringToDate(dateS,"dd/MM/yyyy");
//		Date date2= SGBD.stringToDate(dateS2,"dd/MM/yyyy");
//		boolean b = true;
//		Promotion p = new Promotion("PRO99999","Promo test",date,date2,0.3,b);
//		
		/**Création d'une deuxième promo test**/
//		String dateS1 = "25/11/2010";
//		String dateS21 = "30/11/2010";
//		Date date1= SGBD.stringToDate(dateS1,"dd/MM/yyyy");
//		Date date21= SGBD.stringToDate(dateS21,"dd/MM/yyyy");
//		boolean bo = true;
//		Promotion p2 = new Promotion("PRO8745","Promo test2",date1,date21,0.4,bo);
//		
		/**Instanciation d'une catégorie test**/
//		Categorie cat = new Categorie("CAT99999","Test",100);
			  
		/**Instanciation d'une association**/ 

//		Association ASS = new Association("IDYLLISTE", "IDYLLISTE@ENSAI.FR", "ENSAI", "35000","Rennes","ville001",
//		  "0299451210", true);
//		  
		/**Instanciation d'une autre association**/ 
//		Association ass2 = new Association("BDE", "bde@ensai.fr", "Ensai",
//				  "35170","Bruz","ville034", "0256842210",true);
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
