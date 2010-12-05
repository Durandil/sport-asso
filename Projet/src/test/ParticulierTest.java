package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import metier.Particulier;
import junit.framework.TestCase;

public class ParticulierTest extends TestCase {
	
	public void testModifierBDDparticulier(){
		Particulier.modifierBDDparticulier("jean.dupont@laposte.net", "Dupont", "Jean", "10 rue du Sud", "Bruz", "ville002", "35170", "0645454545");
		ArrayList<String> result=new ArrayList<String>();
		result.add("4 rue de la Breiz");
		result.add("10 rue du Sud");
		assertEquals(result, SGBD.selectListeString("Client", "AdresseClient"));
	}

}
