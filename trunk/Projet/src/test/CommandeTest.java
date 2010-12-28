package test;

import java.util.ArrayList;

import metier.Commande;
import junit.framework.TestCase;

public class CommandeTest extends TestCase {

// test ok
	
	public void testPreparerPanier(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="ART00001";
		listeString[1]="0";
		listeString2[0]="ART00003";
		listeString2[1]="0";
		result.add(listeString);
		result.add(listeString2);
		assertEquals(result.get(0)[0], Commande.preparerPanier().get(0)[0]);
		assertEquals(result.get(0)[1], Commande.preparerPanier().get(0)[1]);
		assertEquals(result.get(1)[0], Commande.preparerPanier().get(1)[0]);
		assertEquals(result.get(1)[1], Commande.preparerPanier().get(1)[1]);
	}
	
// test ok	
	
	public void testViderPanier(){
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="ART00001";
		listeString[1]="240";
		listeString2[0]="ART00003";
		listeString2[1]="12";
		panier.add(listeString);
		panier.add(listeString2);
		Commande.viderPanier(panier);
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString3 = new String[2];
		String[] listeString4 = new String[2];
		listeString3[0]="ART00001";
		listeString3[1]="0";
		listeString4[0]="ART00003";
		listeString4[1]="0";
		result.add(listeString3);
		result.add(listeString4);
		assertEquals(result.get(0)[1], panier.get(0)[1]);
		assertEquals(result.get(1)[1], panier.get(1)[1]);
	}

	// test ok
	public void testRechercheArticleDansPanier(){
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="ART00001";
		listeString[1]="240";
		listeString2[0]="ART00003";
		listeString2[1]="12";
		panier.add(listeString);
		panier.add(listeString2);
		assertEquals(0, Commande.rechercheArticleDansPanier("ART00001", panier));
		assertEquals(1, Commande.rechercheArticleDansPanier("ART00003", panier));

	}
	
	// Test ok
	public void testAjouterArticlePanier(){
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="ART00001";
		listeString[1]="20";
		listeString2[0]="ART00003";
		listeString2[1]="12";
		panier.add(listeString);
		panier.add(listeString2);
	
		Commande.ajouterArticlePanier("ART00001", 8, panier);
		assertEquals("28",panier.get(0)[1]);
	
		Commande.ajouterArticlePanier("ART00003", 12, panier);
		assertEquals("24",panier.get(1)[1]);
	}
	
	//Test ok
	public void testEnleverArticlePanier(){
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="ART00001";
		listeString[1]="20";
		listeString2[0]="ART00003";
		listeString2[1]="12";
		panier.add(listeString);
		panier.add(listeString2);
		Commande.enleverArticlePanier("ART00001", "4", panier);
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString3 = new String[2];
		String[] listeString4 = new String[2];
		listeString3[0]="ART00001";
		listeString3[1]="16";
		listeString4[0]="ART00003";
		listeString4[1]="12";
		result.add(listeString3);
		result.add(listeString4);

		assertEquals(result.get(0)[1],panier.get(0)[1]);
		Commande.enleverArticlePanier("ART00003", "12", panier);
		assertEquals(result.get(0)[1], panier.get(0)[1]);
		
//		normalement on ne peut pas enlever plus d'article qu'il n'y a dans le panier
//		Commande.enleverArticlePanier("ART00003", "15", panier);

	}
	
	
}
