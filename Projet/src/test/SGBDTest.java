package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import junit.framework.TestCase;

public class SGBDTest extends TestCase {
	
// test select liste opérationnel	

		public void testSelectListeString(){
		ArrayList<String> result= new ArrayList<String>();
		result.add("0606060606");
		result.add("0645454545");
		System.out.println(result);
		System.out.println(SGBD.selectListeString("client","TELEPHONE"));
		assertEquals(result,SGBD.selectListeString("client","TELEPHONE"));
	}
	
// test select date pour date fin (l'ordre doit être le même dans la base et dans le résultat)	
// fonctionne

		public void testSelectListeDatesOrdonne(){
		ArrayList<String> result = new ArrayList<String>();
		String dateS = "01/01/11";
		String dateS2 = "01/02/11";
		String dateS3 = "01/03/11";
		result.add(dateS);
		result.add(dateS2);
		result.add(dateS3);
		assertEquals(result,SGBD.selectListeDatesOrdonne("Promo","datefin" ,"dd/mm/yy","IDPROMO")); 
	}
	
// test select liste float
// ok
	
	public void testSelectListeFloat(){
		ArrayList<Float> result = new ArrayList<Float>();
		result.add((float) 25);
		result.add((float) 50);
		result.add((float) 40);
		assertEquals(result,SGBD.selectListeFloat("Promo", "PourcentagePromo"));
	}
	
// test sélection String sous condition string
	// ok
	
	public void testselectStringConditionString(){
		String result = new String();
		result = "Raquettes à prix cassé";
		assertEquals(result, SGBD.selectStringConditionString("Promo", "NomPromo", "IDPromo","PRO00001 " ));
	}	
	
// test sélection 2 champs string
	// ok
	
	public void testselectDeuxChampsString(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="Laroch";
		listeString[1]="4 rue de la Breiz";
		listeString2[0]="Dupont";
		listeString2[1]="2 rue du Nord";
		result.add(listeString);
		result.add(listeString2);
		assertEquals(result.get(0)[0], SGBD.selectDeuxChampsString("Client", "NomClient", "AdresseClient").get(0)[0]);
		assertEquals(result.get(0)[1], SGBD.selectDeuxChampsString("Client", "NomClient", "AdresseClient").get(0)[1]);
		assertEquals(result.get(1)[0], SGBD.selectDeuxChampsString("Client", "NomClient", "AdresseClient").get(1)[0]);
		assertEquals(result.get(1)[1], SGBD.selectDeuxChampsString("Client", "NomClient", "AdresseClient").get(1)[1]);
	}
	
//  TODO
	
//	public void testrecupererAttributClient(){
//		ArrayList<String> result=new ArrayList<String>();
//		result.add("bde@ensai.fr");
//		result.add(" ");
//		result.add(" ");
//		result.add("BDE");
//		result.add("Ensai");
//		result.add("Bruz");
//		result.add("35170");
//		result.add("0256842210");
//		result.add("Activé");
//		System.out.println(result);
//		System.out.println(SGBD.recupererAttributClient("bde@ensai.fr"));
//		assertEquals(result, SGBD.recupererAttributClient("bde@ensai.fr"));
//		
//			}

// test sur les articles en réapprovisionnement bon	
	
	public void testselectArticlesReapprovisionnement(){
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> listeString= new ArrayList<String>();
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
	
//  test qui fonctionne	
	
	public void testrecupererInformationFideliteClient(){
		ArrayList<String> result= new ArrayList<String>();
		result.add("Oui");
		result.add("500");
		assertEquals(result, SGBD.recupererInformationFideliteClient("arthur.laroch@gmail.com"));
	}
	
// test qui fonctionne 
	
//	public void testrecupererInformationRechercheClient(){
//		ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
//		ArrayList<String> listeString1 = new ArrayList<String>();
//		ArrayList<String> listeString2 = new ArrayList<String>();
//		ArrayList<String> listeString3 = new ArrayList<String>();
//		ArrayList<String> listeString4 = new ArrayList<String>();
//		listeString1.add("arthur.laroch@gmail.com");
//		listeString2.add("Laroch");
//		listeString3.add("Arthur");
//		listeString4.add("");
//		result.add(listeString1);
//		result.add(listeString2);
//		result.add(listeString3);
//		result.add(listeString4);
//		System.out.println(result);
//		System.out.println(SGBD.recupererInformationRechercheClient("", "laroch"," " , ""));
//		assertEquals(result,SGBD.recupererInformationRechercheClient("", "laroch"," " , ""));
//	}

// test opérationnel	
	
	public void testinformationCommande(){
		ArrayList<Object[]> result=new ArrayList<Object[]>();
		String[] listeString = new String[4];
		listeString[0]="ART00001";
		listeString[1]="Maillot de foot de Sochaux";
		listeString[2]="70";
		listeString[3]="8";
		result.add(listeString);

		assertEquals(result.get(0)[0], SGBD.informationCommande("comm0001").get(0)[0]);
		assertEquals(result.get(0)[1], SGBD.informationCommande("comm0001").get(0)[1]);
		assertEquals(result.get(0)[2], SGBD.informationCommande("comm0001").get(0)[2]);
		assertEquals(result.get(0)[3], SGBD.informationCommande("comm0001").get(0)[3]);
	}
//  test ok	
	public void testRecupererPourcentagePromotionExceptionnelleArticle(){
		assertEquals("40", SGBD.recupererPourcentagePromotionExceptionnelleArticle("ART00001",0 ));
		assertEquals("50", SGBD.recupererPourcentagePromotionExceptionnelleArticle("ART00003",1 ));
	}
	
	
// test ok
	
	
	public void testRecupererPourcentagePromotionDegressifArticleCommande(){
		System.out.println(SGBD.recupererPourcentagePromotionDegressifArticleCommande("comm0001", "ART00001"));
		assertEquals("92.5", SGBD.recupererPourcentagePromotionDegressifArticleCommande("comm0001", "ART00001"));
	}
}



