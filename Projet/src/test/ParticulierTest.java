package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import metier.Particulier;
import junit.framework.TestCase;

/**
 * <b> La classe ParticulierTest permet de valider les méthodes présentes dans la classe Particulier.</b>
 * <p> Cette classe possède 2 tests. Elle hérite de la classe TestCase.
 * Les méthodes d'ajout, de modification ou de suppression dans la base de données
 * étant similaires dans les classes métier, nous n'avons testé leur bon fonctionnement 
 * que dans certaines classes.
 * Il est important que les tests de SGBD aient été effectués au préalable de ces tests
 * car ils permettent de vérifier les éléments qui sont dans la base de données 
 * donc de vérifier que les articles ont bien été ajoutés ou supprimés de la base.
 * </p>
 * 
 * @see metier.Particulier
 */
public class ParticulierTest extends TestCase {
	
	
	/**
	 * Méthodes qui testent la modification d'informations sur les clients dans la base de données.
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
