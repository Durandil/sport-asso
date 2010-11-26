package test;

import java.util.ArrayList;

import metier.Promotion;

import application.Principale;
import basededonnees.SGBD;

import junit.framework.TestCase;

public class SGBDTest extends TestCase {
	
// test select liste opérationnel	

	/*	public void testSelectListeString(){
		ArrayList<String> result= new ArrayList<String>();
		result.add("0256842210");
		result.add("0256986633");
		result.add("0299451210");
		
		assertEquals(result,SGBD.selectListeString("client","TELEPHONE"));
	}*/
	
// test select date pour date fin (l'ordre doit être le même dans la base et dans le résultat)	

//		public void testSelectListeDates(){
//		ArrayList<String> result = new ArrayList<String>();
//		String dateS = "07/11/10";
//		String dateS2 = "30/11/10";
//		result.add(dateS);
//		result.add(dateS2);
//		assertEquals(result,SGBD.selectListeDates("Promo","datefin" ,"dd/mm/yy")); 
//	}
	
// test select liste float
	
//	public void testSelectListeFloat(){
//		ArrayList<Float> result = new ArrayList<Float>();
//		result.add((float) 0.3);
//		result.add((float) 0.4);
//		assertEquals(result,SGBD.selectListeFloat("Promo", "PourcentagePromo"));
//	}
	
// test sélection String sous condition string
	
//	public void testselectStringConditionString(){
//		String result = new String();
//		result = "Promo test";
//		assertEquals(result, SGBD.selectStringConditionString("Promo", "NomPromo", "IDPromo","PRO99999 " ));
//	}	
	
// test sélection 2 champs string
	
//	public void testselectDeuxChampsString(){
//		ArrayList<String[]> result = new ArrayList<String[]>();
//		String[] listeString = new String[2];
//		String[] listeString2 = new String[2];
//		listeString[0]="IDYLLISTE";
//		listeString[1]="Rennes";
//		listeString2[0]="BDE";
//		listeString2[1]="Bruz";
//		result.add(listeString);
//		result.add(listeString2);
////		System.out.println(result.get(1)[1]);
////		System.out.println(SGBD.selectDeuxChampsString("Client", "DenominationClient", "NomVille").get(1)[1]);		
//		
//		assertEquals(result.get(0)[0], SGBD.selectDeuxChampsString("Client", "DenominationClient", "NomVille").get(0)[0]);
//		assertEquals(result.get(0)[1], SGBD.selectDeuxChampsString("Client", "DenominationClient", "NomVille").get(0)[1]);
//		assertEquals(result.get(1)[0], SGBD.selectDeuxChampsString("Client", "DenominationClient", "NomVille").get(1)[0]);
//		assertEquals(result.get(1)[1], SGBD.selectDeuxChampsString("Client", "DenominationClient", "NomVille").get(1)[1]);
//	}
//	

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
	
	public void testselectArticlesReapprovisionnement(){
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> listeString= new ArrayList<String>();
		ArrayList<String> listeString2 = new ArrayList<String>();
		ArrayList<String> listeString3 = new ArrayList<String>();
		ArrayList<String> listeString4 = new ArrayList<String>();
		listeString.add("ART00003");
		listeString2.add("Velo de Course");
		listeString3.add("0");
		listeString4.add("250");
		result.add(listeString);
		result.add(listeString2);
		result.add(listeString3);
		result.add(listeString4);
		System.out.println(result);
		System.out.println(SGBD.selectArticlesReapprovisionnement());
		assertEquals(result, SGBD.selectArticlesReapprovisionnement());
	}
	
	public void testrecupererInformationFideliteClient(){
		ArrayList<String> result= new ArrayList<String>();
		result.add("500");
		assertEquals(result, SGBD.recupererInformationFideliteClient("arthur.laroch@gmail.com"));
	}
	
	public void testrecupererIdentifiantDernierEnregistrementTable(){
		
	}
}



