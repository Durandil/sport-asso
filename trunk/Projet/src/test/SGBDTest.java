package test;

import java.util.ArrayList;

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
}
