package application;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JTextField;

import oracle.sql.DATE;

import metier.Article;
import metier.Association;
import metier.Categorie;
import metier.Commande;
import metier.LigneCommande;
import metier.Particulier;

import basededonnees.SGBD;

public class Principale {

/** IMPORTANT : V�rifier si on peut acc�der � la Base ORACLE Ensai de la maison !**/

	public static void main(String[] args) {
			SGBD.connecter();
		//activDesactivCompte();
		/*
		 * //Cr�ation de la table CLIENTS SGBD.creerTable("CLIENTS");
		 * 
		 * 
		 * //Instanciation d'une association Association ass = new
		 * Association("Idylliste", "idylliste@ensai.fr", "Ensai", "Rennes",
		 * "35000", "02 23 45 78 96");
		 * 
		 * //Instanciation d'un particulier Particulier p = new
		 * Particulier("Louvel", "Alexis", "alexis.louvel@ensai.fr",
		 * "Campus de KL", "Bruz", "35000", "02 56 98 66 33");
		 */

		/*
		 * // Cr�ation de la table ARTICLES SGBD.creerTable("ARTICLES");
		 * 
		 * // Instanciation de 10 articles (et ajout dans la table Article art1
		 * = new Article("ART001","Maillot","Omnisport",200,50.20,10,"2");
		 * Article art2 = new
		 * Article("ART002","Ballon","Sport d''�quipe",200,15.30,100,"1");
		 * Article art3 = new
		 * Article("ART003","Chaussettes Blanches","Omnisport",50,10,50,"1");
		 * Article art4 = new
		 * Article("ART004","Bonnet de bain","Natation",75,15,100,"1"); Article
		 * art5 = new Article("ART005","Club","Golf",500,100,10,"3"); Article
		 * art6 = new
		 * Article("ART006","Chaussettes Noires","Omnisport",50,10,50,"1");
		 * Article art7 = new
		 * Article("ART007","Selle","�quitation",250,50,5,"3"); Article art8 =
		 * new Article("ART008","Chaussures NIKE","Running",250,100,60,"2");
		 * Article art9 = new
		 * Article("ART009","Kimono","Sport de combat",750,35,30,"2"); Article
		 * art10 = new
		 * Article("ART010","Raquette de tennis d�butant","Sport de raquette"
		 * ,170,20,50,"3");
		 */

		/** TODO : G�rer le format Date **/
		/*
		 * // Cr�ation de 2 commandes // MAJ de la table COMMANDES // Cr�ation
		 * des tables COM1 et COM2
		 * 
		 * LigneCommande lc1 = new LigneCommande("ART002", 3); LigneCommande lc2
		 * = new LigneCommande("ART001", 1); ArrayList al = new
		 * ArrayList<LigneCommande>(); al.add(lc1); al.add(lc2);
		 * SGBD.creerTable("COMMANDES");
		 * 
		 * String date="18/10/2010"; Commande c1 = new
		 * Commande("COM1","alexis.louvel@ensai.fr", al,date); c1.creerTable();
		 * 
		 * 
		 * LigneCommande lc3 = new LigneCommande("ART010", 12); LigneCommande
		 * lc4 = new LigneCommande("ART007", 3); LigneCommande lc5 = new
		 * LigneCommande("ART008", 7); ArrayList al2 = new
		 * ArrayList<LigneCommande>(); al2.add(lc3); al2.add(lc4); al2.add(lc5);
		 * 
		 * 
		 * String date2="19/10/2010"; Commande c2 = new
		 * Commande("COM2","idylliste@ensai.fr", al2,date2); c2.creerTable();
		 */

		/*
		 * 
		 * // Cr�ation de la table CAT1
		 * 
		 * ArrayList<double[]> alcat1 = new ArrayList<double[]>();
		 * 
		 * double[] d1 = new double[2]; d1[0] = 10.0; d1[1] = 0.9; double[] d2 =
		 * new double[2]; d2[0] = 20.0; d2[1] = 0.8; double[] d3 = new
		 * double[2]; d3[0] = 30.0; d3[1] = 0.7; alcat1.add(d1); alcat1.add(d2);
		 * alcat1.add(d3);
		 * 
		 * Categorie cat1 = new Categorie("CAT1", alcat1); cat1.creerTable();
		 */

	}

}
