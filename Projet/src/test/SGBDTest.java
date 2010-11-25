package test;

import java.util.ArrayList;

import metier.Promotion;

import application.Principale;
import basededonnees.SGBD;

import junit.framework.TestCase;

public class SGBDTest extends TestCase {
	
// test select liste op�rationnel	

	/*	public void testSelectListeString(){
		ArrayList<String> result= new ArrayList<String>();
		result.add("0256842210");
		result.add("0256986633");
		result.add("0299451210");
		
		assertEquals(result,SGBD.selectListeString("client","TELEPHONE"));
	}*/
	
// test select date pour date fin (l'ordre doit �tre le m�me dans la base et dans le r�sultat)	

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
	
// test s�lection String sous condition string
	
//	public void testselectStringConditionString(){
//		String result = new String();
//		result = "Promo test";
//		assertEquals(result, SGBD.selectStringConditionString("Promo", "NomPromo", "IDPromo","PRO99999 " ));
//	}	
	
// test s�lection 2 champs string
	
	public void testselectDeuxChampsString(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] listeString = new String[2];
		String[] listeString2 = new String[2];
		listeString[0]="PRO99999";
		listeString[1]="Promo test";
		listeString2[0]="PRO8745";
		listeString2[1]="Promo test2";
		result.add(listeString);
		result.add(listeString2);
//		System.out.println(result.get(1)[0]);
//		System.out.println(SGBD.selectDeuxChampsString("Promo", "IDPromo", "NomPromo").get(1)[0]);
		boolean b;
		b= result.get(0)[0].equals(SGBD.selectDeuxChampsString("Promo", "IDPromo", "NomPromo").get(0)[0]);
		System.out.println(b);
		assertEquals(result, SGBD.selectDeuxChampsString("Promo", "IDPromo", "NomPromo"));
	}
	

}



