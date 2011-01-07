package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import metier.Particulier;
import junit.framework.TestCase;

/**
 * <b> La classe ParticulierTest permet de valider les m�thodes pr�sentes dans la classe Particulier.</b>
 * <p> Cette classe poss�de 2 tests. Elle h�rite de la classe TestCase.
 * Les m�thodes d'ajout, de modification ou de suppression dans la base de donn�es
 * �tant similaires dans les classes m�tier, nous n'avons test� leur bon fonctionnement 
 * que dans certaines classes.
 * Il est important que les tests de SGBD aient �t� effectu�s au pr�alable de ces tests
 * car ils permettent de v�rifier les �l�ments qui sont dans la base de donn�es 
 * donc de v�rifier que les articles ont bien �t� ajout�s ou supprim�s de la base.
 * </p>
 * 
 * @see metier.Particulier
 */
public class ParticulierTest extends TestCase {
	
	
	/**
	 * M�thodes qui testent la modification d'informations sur les clients dans la base de donn�es.
	 */
	public void testModifierBDDparticulier(){
		Particulier.modifierBDDparticulier("jean.dupont@laposte.net", "Dupont", "Jean", "10 rue du Sud","35170", "0645454545");
		ArrayList<String> result=new ArrayList<String>();
		result.add("4 rue de la Breiz");
		result.add("10 rue du Sud");
		assertEquals(result, SGBD.selectListeString("Client", "AdresseClient"));
	}

	public void testModifierBDDparticulier2(){
		Particulier.modifierBDDparticulier("jean.dupont@laposte.net", "Dupont", "Jean", "2 rue du Nord","35170", "0645454545");
		ArrayList<String> result=new ArrayList<String>();
		result.add("4 rue de la Breiz");
		result.add("2 rue du Nord");
		assertEquals(result, SGBD.selectListeString("Client", "AdresseClient"));
	}
}
