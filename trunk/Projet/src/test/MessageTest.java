package test;

import java.util.ArrayList;

import basededonnees.SGBD;

import metier.Message;
import junit.framework.TestCase;

/**
 * <b> La classe MessageTest permet de valider les m�thodes pr�sentes dans la classe Message.</b>
 * <p> Cette classe poss�de 1 test. Elle h�rite de la classe TestCase.
 * Les m�thodes d'ajout, de modification ou de suppression dans la base de donn�es
 * �tant similaires dans les classes m�tier, nous n'avons test� leur bon fonctionnement 
 * que dans certaines classes.
 * Il est important que les tests de SGBD aient �t� effectu�s au pr�alable de ces tests
 * car ils permettent de v�rifier les �l�ments qui sont dans la base de donn�es 
 * donc de v�rifier que les articles ont bien �t� ajout�s ou supprim�s de la base.
 * </p>
 * 
 * @see metier.Message
 */
public class MessageTest extends TestCase {
	

	/**
	 * M�thode qui teste la suppression de message dans la base de donn�es.
	 */
	
	public void testSupprimerBDD(){
		Message.supprimerBDD("MES00002");
		ArrayList<String> result=new ArrayList<String>();
		result.add("MES00001");
		assertEquals(result, SGBD.selectListeString("Message", "IDMessage"));
	}

}
