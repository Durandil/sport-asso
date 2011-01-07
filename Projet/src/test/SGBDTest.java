package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import junit.framework.TestCase;

/**
 * <b> La classe ParticulierTest permet de valider les m�thodes pr�sentes dans
 * la classe Particulier.</b>
 * <p>
 * Cette classe poss�de 10 tests. Elle h�rite de la classe TestCase. Cette
 * classe est tr�s importante car elle permet d'effectuer de nombreux tests des
 * autres classes Les m�thodes de s�lection dans la base de donn�es sont
 * nombreuses et diverses et doivent �tre test�es.
 * </p>
 * 
 * @see basededonnees.SGBD
 */

public class SGBDTest extends TestCase {

	/**
	 * M�thode qui teste la s�lection d'une liste de String.
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
	 * M�thode qui teste la s�lection de dates ordonn�es.
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
	 * M�thode qui teste la s�lection de nombres � virgule comme des
	 * pourcentages au sein de la base de donn�es.
	 */

	public void testSelectListeFloat() {
		ArrayList<Float> result = new ArrayList<Float>();
		result.add((float) 25);
		result.add((float) 50);
		result.add((float) 40);
		assertEquals(result, SGBD.selectListeFloat("Promo", "PourcentagePromo"));
	}

	/**
	 * M�thode qui teste la s�lection d'un �l�ment de la base de donn�es avec
	 * une condition.
	 */

	public void testselectStringConditionString() {
		String result = new String();
		result = "Raquettes � prix cass�";
		assertEquals(result, SGBD.selectStringConditionString("Promo",
				"NomPromo", "IDPromo", "PRO00001 "));
	}

	/**
	 * M�thode qui teste la s�lection de deux listes d'�l�ments dans la base de
	 * donn�es.
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
	 * M�thode qui teste que les articles qui apparaissent dans le menu
	 * r�approvisionnement sont bien en rupture de stock ou en quantit�
	 * inf�rieure au seuil fix� pour �tre r�approvisionn�.
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
	 * M�thode qui teste la s�lection du nombre de points fid�lit� qu'un client
	 * poss�de sur sa carte de fid�lit�.
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
	 * M�thode qui teste la r�cup�ration des informations li�es � une commande
	 * enregistr�e dans la base de donn�es.
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
	 * M�thode qui teste la r�cup�ration du bon pourcentage de promotion mise en
	 * place par le g�rant sur un certain article.
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
	 * M�thode qui teste la r�cup�ration du pourcentage de promotion en fonction
	 * du nombre d'articles command�.
	 */

	public void testRecupererPourcentagePromotionDegressifArticleCommande() {
		assertEquals("92.5",
				SGBD.recupererPourcentagePromotionDegressifArticleCommande(
						"comm0001", "ART00001"));
	}
}
