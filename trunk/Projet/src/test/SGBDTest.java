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

	/*	public void testSelectListeDates(){
		ArrayList<String> result = new ArrayList<String>();
		String dateS = "07/11/10";
		String dateS2 = "30/11/10";
		result.add(dateS);
		result.add(dateS2);
		assertEquals(result,SGBD.selectListeDates("Promo","datefin" ,"dd/mm/yy")); 
	}*/
	
	
}


