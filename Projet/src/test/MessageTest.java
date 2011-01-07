package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import metier.Message;
import junit.framework.TestCase;

/**
 * <b> La classe MessageTest permet de valider les méthodes présentes dans la classe Message.</b>
 * <p> Cette classe possède 1 test. Elle hérite de la classe TestCase.
 * Les méthodes d'ajout, de modification ou de suppression dans la base de données
 * étant similaires dans les classes métier, nous n'avons testé leur bon fonctionnement 
 * que dans certaines classes.
 * Il est important que les tests de SGBD aient été effectués au préalable de ces tests
 * car ils permettent de vérifier les éléments qui sont dans la base de données 
 * donc de vérifier que les articles ont bien été ajoutés ou supprimés de la base.
 * </p>
 * 
 * @see metier.Message
 */
public class MessageTest extends TestCase {
	

	/**
	 * Méthode qui teste la suppression de message dans la base de données.
	 */
	
	public void testSupprimerBDD(){
		Message.supprimerBDD("MES00002");
		ArrayList<String> result=new ArrayList<String>();
		result.add("MES00001");
		assertEquals(result, SGBD.selectListeString("Message", "IDMessage"));
	}

}
