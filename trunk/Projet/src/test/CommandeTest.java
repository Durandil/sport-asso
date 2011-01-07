package test;

import java.util.ArrayList;

import metier.Commande;
import junit.framework.TestCase;

/**
 * <b> La classe CommandeTest permet de valider les m�thodes pr�sentes dans la
 * classe Commande.</b>
 * <p>
 * Cette classe poss�de 5 tests de m�thodes. Elle h�rite de la classe TestCase.
 * </p>
 * 
 * @see metier.Commande
 */

public class CommandeTest extends TestCase {

	/**
	 * M�thode qui teste la pr�paration du panier, c'est-�-dire les ajouts des
	 * articles ainsi que l'initialisation des quantit�s de chaque article dans
	 * le panier.
	 */

	public void testPreparerPanier() {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0] = "ART00001";
		listeString[1] = "0";
		listeString2[0] = "ART00003";
		listeString2[1] = "0";
		result.add(listeString);
		result.add(listeString2);
		assertEquals(result.get(0)[0], Commande.preparerPanier().get(0)[0]);
		assertEquals(result.get(0)[1], Commande.preparerPanier().get(0)[1]);
		assertEquals(result.get(1)[0], Commande.preparerPanier().get(1)[0]);
		assertEquals(result.get(1)[1], Commande.preparerPanier().get(1)[1]);
	}

	/**
	 * M�thode qui teste la m�thode viderPanier qui r�initialise les quantit�
	 * des articles du panier.
	 */

	public void testViderPanier() {
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0] = "ART00001";
		listeString[1] = "240";
		listeString2[0] = "ART00003";
		listeString2[1] = "12";
		panier.add(listeString);
		panier.add(listeString2);
		Commande.viderPanier(panier);
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString3 = new String[2];
		String[] listeString4 = new String[2];
		listeString3[0] = "ART00001";
		listeString3[1] = "0";
		listeString4[0] = "ART00003";
		listeString4[1] = "0";
		result.add(listeString3);
		result.add(listeString4);
		assertEquals(result.get(0)[1], panier.get(0)[1]);
		assertEquals(result.get(1)[1], panier.get(1)[1]);
	}

	/**
	 * M�thode qui teste la recherche des quantit�s des articles dans le panier.
	 */

	public void testRechercheArticleDansPanier() {
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0] = "ART00001";
		listeString[1] = "240";
		listeString2[0] = "ART00003";
		listeString2[1] = "12";
		panier.add(listeString);
		panier.add(listeString2);
		assertEquals(0, Commande.rechercheArticleDansPanier("ART00001", panier));
		assertEquals(1, Commande.rechercheArticleDansPanier("ART00003", panier));

	}

	/**
	 * M�thode qui teste l'ajout d'articles dans le panier.
	 */

	public void testAjouterArticlePanier() {
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0] = "ART00001";
		listeString[1] = "20";
		listeString2[0] = "ART00003";
		listeString2[1] = "12";
		panier.add(listeString);
		panier.add(listeString2);

		Commande.ajouterArticlePanier("ART00001", 8, panier);
		assertEquals("28", panier.get(0)[1]);

		Commande.ajouterArticlePanier("ART00003", 12, panier);
		assertEquals("24", panier.get(1)[1]);
	}

	/**
	 * M�thode qui teste la suppression d'articles du panier.
	 */
	public void testEnleverArticlePanier() {
		ArrayList<String[]> panier = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0] = "ART00001";
		listeString[1] = "20";
		listeString2[0] = "ART00003";
		listeString2[1] = "12";
		panier.add(listeString);
		panier.add(listeString2);
		Commande.enleverArticlePanier("ART00001", "4", panier);
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString3 = new String[2];
		String[] listeString4 = new String[2];
		listeString3[0] = "ART00001";
		listeString3[1] = "16";
		listeString4[0] = "ART00003";
		listeString4[1] = "12";
		result.add(listeString3);
		result.add(listeString4);

		assertEquals(result.get(0)[1], panier.get(0)[1]);
		Commande.enleverArticlePanier("ART00003", "12", panier);
		assertEquals(result.get(0)[1], panier.get(0)[1]);

	}

}
