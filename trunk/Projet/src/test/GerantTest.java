package test;

import basededonnees.SGBD;
import metier.Gerant;
import junit.framework.TestCase;

public class GerantTest extends TestCase {
	
	public void testActivDesactivCompte(){
		// Désactiver le compte de Laroch Arthur
		
		
		String result="Désactivé";
		assertEquals(result,SGBD.selectStringConditionString("Client","EtatCompte" , "NomClient", "Laroch") );
	}
}
