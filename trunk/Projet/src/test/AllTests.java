package test;

import metier.LigneCommande;
import metier.Promotion;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * <b>La classe AllTests comporte l'ensemble des tests effectués.</b>
 * <p>
 * La classe AllTests est une Test Suite qui définit une méthode publique qui
 * renvoie un objet de type Test. Elle permet d'effectuer tous les tests mis en
 * place dans le package test
 * </p>
 * 
 */

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new TestSuite(SGBDTest.class));
		suite.addTest(new TestSuite(ParticulierTest.class));
		suite.addTest(new TestSuite(CommandeTest.class));
		suite.addTest(new TestSuite(ArticleTest.class));
		suite.addTest(new TestSuite(MessageTest.class));

		return suite;
	}

}
