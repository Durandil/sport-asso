package test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new TestSuite(SGBDTest.class));
		suite.addTest(new TestSuite(PromotionTest.class));
		suite.addTest(new TestSuite(ParticulierTest.class));
		
		suite.addTest(new TestSuite(ArticleTest.class));
		suite.addTest(new TestSuite(MessageTest.class));
		return suite;
	}

}
