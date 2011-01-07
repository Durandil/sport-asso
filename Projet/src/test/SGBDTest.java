package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import junit.framework.TestCase;

/**
 * <b> La classe ParticulierTest permet de valider les méthodes présentes dans
 * la classe Particulier.</b>
 * <p>
 * Cette classe possède 10 tests. Elle hérite de la classe TestCase. Cette
 * classe est très importante car elle permet d'effectuer de nombreux tests des
 * autres classes Les méthodes de sélection dans la base de données sont
 * nombreuses et diverses et doivent être testées.
 * </p>
 * 
 * @see basededonnees.SGBD
 */

public class SGBDTest extends TestCase {

	/**
	 * Méthode qui teste la sélection d'une liste de String.
	 */

	public void testSelectListeString() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("0606060606");
		result.add("0645454545");
		System.out.println(result);
		System.out.println(SGBD.selectListeString("client", "TELEPHONE"));
		assertEquals(result, SGBD.selectListeString("client", "TELEPHONE"));
	}

	/**
	 * Méthode qui teste la sélection de dates ordonnées.
	 */

	public void testSelectListeDatesOrdonne() {
		ArrayList<String> result = new ArrayList<String>();
		String dateS = "01/01/11";
		String dateS2 = "01/02/11";
		String dateS3 = "01/03/11";
		result.add(dateS);
		result.add(dateS2);
		result.add(dateS3);
		assertEquals(result, SGBD.selectListeDatesOrdonne("Promo", "datefin",
				"dd/mm/yy", "IDPROMO"));
	}

	/**
	 * Méthode qui teste la sélection de nombres à virgule comme des
	 * pourcentages au sein de la base de données.
	 */

	public void testSelectListeFloat() {
		ArrayList<Float> result = new ArrayList<Float>();
		result.add((float) 25);
		result.add((float) 50);
		result.add((float) 40);
		assertEquals(result, SGBD.selectListeFloat("Promo", "PourcentagePromo"));
	}

	/**
	 * Méthode qui teste la sélection d'un élément de la base de données avec
	 * une condition.
	 */

	public void testselectStringConditionString() {
		String result = new String();
		result = "Raquettes à prix cassé";
		assertEquals(result, SGBD.selectStringConditionString("Promo",
				"NomPromo", "IDPromo", "PRO00001 "));
	}

	/**
	 * Méthode qui teste la sélection de deux listes d'éléments dans la base de
	 * données.
	 */

	public void testselectDeuxChampsString() {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0] = "Laroch";
		listeString[1] = "4 rue de la Breiz";
		listeString2[0] = "Dupont";
		listeString2[1] = "2 rue du Nord";
		result.add(listeString);
		result.add(listeString2);
		assertEquals(
				result.get(0)[0],
				SGBD.selectDeuxChampsString("Client", "NomClient",
						"AdresseClient").get(0)[0]);
		assertEquals(
				result.get(0)[1],
				SGBD.selectDeuxChampsString("Client", "NomClient",
						"AdresseClient").get(0)[1]);
		assertEquals(
				result.get(1)[0],
				SGBD.selectDeuxChampsString("Client", "NomClient",
						"AdresseClient").get(1)[0]);
		assertEquals(
				result.get(1)[1],
				SGBD.selectDeuxChampsString("Client", "NomClient",
						"AdresseClient").get(1)[1]);
	}

	/**
	 * Méthode qui teste que les articles qui apparaissent dans le menu
	 * réapprovisionnement sont bien en rupture de stock ou en quantité
	 * inférieure au seuil fixé pour être réapprovisionné.
	 */

	public void testselectArticlesReapprovisionnement() {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> listeString = new ArrayList<String>();
		ArrayList<String> listeString2 = new ArrayList<String>();
		ArrayList<String> listeString3 = new ArrayList<String>();
		ArrayList<String> listeString4 = new ArrayList<String>();
		listeString.add("ART00002");
		listeString2.add("Velo de Course");
		listeString3.add("0");
		listeString4.add("250");
		result.add(listeString);
		result.add(listeString2);
		result.add(listeString3);
		result.add(listeString4);
		assertEquals(result, SGBD.selectArticlesReapprovisionnement());
	}

	/**
	 * Méthode qui teste la sélection du nombre de points fidélité qu'un client
	 * possède sur sa carte de fidélité.
	 */

	public void testrecupererInformationFideliteClient() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("Oui");
		result.add("500");
		assertEquals(
				result,
				SGBD.recupererInformationFideliteClient("arthur.laroch@gmail.com"));
	}

	/**
	 * Méthode qui teste la récupération des informations liées à une commande
	 * enregistrée dans la base de données.
	 */

	public void testinformationCommande() {
		ArrayList<Object[]> result = new ArrayList<Object[]>();
		String[] listeString = new String[4];
		listeString[0] = "ART00001";
		listeString[1] = "Maillot de foot de Sochaux";
		listeString[2] = "70";
		listeString[3] = "8";
		result.add(listeString);

		assertEquals(result.get(0)[0], SGBD.informationCommande("comm0001")
				.get(0)[0]);
		assertEquals(result.get(0)[1], SGBD.informationCommande("comm0001")
				.get(0)[1]);
		assertEquals(result.get(0)[2], SGBD.informationCommande("comm0001")
				.get(0)[2]);
		assertEquals(result.get(0)[3], SGBD.informationCommande("comm0001")
				.get(0)[3]);
	}

	/**
	 * Méthode qui teste la récupération du bon pourcentage de promotion mise en
	 * place par le gérant sur un certain article.
	 */

	public void testRecupererPourcentagePromotionExceptionnelleArticle() {
		assertEquals("40",
				SGBD.recupererPourcentagePromotionExceptionnelleArticle(
						"ART00001", 0));
		assertEquals("50",
				SGBD.recupererPourcentagePromotionExceptionnelleArticle(
						"ART00003", 1));
	}

	/**
	 * Méthode qui teste la récupération du pourcentage de promotion en fonction
	 * du nombre d'articles commandé.
	 */

	public void testRecupererPourcentagePromotionDegressifArticleCommande() {
		assertEquals("92.5",
				SGBD.recupererPourcentagePromotionDegressifArticleCommande(
						"comm0001", "ART00001"));
	}
}
