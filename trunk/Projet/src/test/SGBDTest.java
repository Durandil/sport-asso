package test;

import java.util.ArrayList;

import application.Principale;
import basededonnees.SGBD;

import junit.framework.TestCase;

public class SGBDTest extends TestCase {
	public void testSelectListeString(){
		ArrayList<String> result= new ArrayList<String>();
		result.add("0256842210");
		result.add("0256986633");
		result.add("0299451210");
		
		assertEquals(result,SGBD.selectListeString("client","TELEPHONE"));
	}
//	public void testSelectListeDates(){
//		ArrayList<String> result = new ArrayList<String>();
//		String dateS = "05/11/2010";
//		String dateS2 = "07/11/2010";
//		result.add(dateS);
//		result.add(dateS2);
//		assertEquals(result,SGBD.selectListeDates(table, str, format) 
//	}
}

