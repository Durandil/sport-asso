package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import metier.Message;
import junit.framework.TestCase;

public class MessageTest extends TestCase {
	
// test ok
	
	public void testSupprimerBDD(){
		Message.supprimerBDD("MES00002");
		ArrayList<String> result=new ArrayList<String>();
		result.add("MES00001");
		System.out.println(result);
		System.out.println(SGBD.selectListeString("Message", "IDMessage"));
		assertEquals(result, SGBD.selectListeString("Message", "IDMessage"));
	}

}
