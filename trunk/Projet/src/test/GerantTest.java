package test;

import basededonnees.SGBD;
import metier.Gerant;
import junit.framework.TestCase;

public class GerantTest extends TestCase {
	
	public void testActivDesactivCompte(){
		// D�sactiver le compte de Laroch Arthur
		
		
		String result="D�sactiv�";
		assertEquals(result,SGBD.selectStringConditionString("Client","EtatCompte" , "NomClient", "Laroch") );
	}
}
