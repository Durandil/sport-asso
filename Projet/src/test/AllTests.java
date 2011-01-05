package test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

// Attention à n'exécuter qu'une seule fois (ne marchera pas de fois de suite)
// pour le refaire, refaire un BDD.init() et et re rentrer les insert into
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new TestSuite(SGBDTest.class));
		suite.addTest(new TestSuite(ParticulierTest.class));
		suite.addTest(new TestSuite(CommandeTest.class));
		suite.addTest(new TestSuite(ArticleTest.class));
		suite.addTest(new TestSuite(MessageTest.class));
		
		return suite;
	}

}
