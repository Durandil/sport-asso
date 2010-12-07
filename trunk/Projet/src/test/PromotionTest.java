package test;

import metier.Promotion;
import junit.framework.TestCase;

public class PromotionTest extends TestCase {
	
	// test ok
	
//	public void testVerifierDatePromotion() throws Exception{
//		boolean b=true;
//		assertEquals(b, Promotion.verifierDatePromotion("2015", "12", "24"));
//		boolean bo=false;
//		assertEquals(bo, Promotion.verifierDatePromotion("2011", "02", "30"));
//		assertEquals(bo, Promotion.verifierDatePromotion("2014", "4", "31"));
//		
//	}

//	public void testVerifierOrdreDeuxDate() throws Exception{
//		boolean b=true;
//		assertEquals(b, Promotion.verifierOrdreDeuxDate("2010", "12", "03", "2010", "12", "05"));
//		
//	}
	public void testVerifierOrdreDeuxDate2() throws Exception{
		boolean b=true;
		boolean bo=false;
		assertEquals(b, Promotion.verifierOrdreDeuxDate("2010", "12", "03", "2010", "12", "03"));
		assertEquals(bo, Promotion.verifierOrdreDeuxDate("2010", "08", "27", "2010", "08", "25"));
		assertEquals(bo, Promotion.verifierOrdreDeuxDate("2010", "08", "27", "2010", "08", "25"));
		assertEquals(bo, Promotion.verifierOrdreDeuxDate("2010", "08", "27", "2010", "08", "25"));
		assertEquals(bo, Promotion.verifierOrdreDeuxDate("2010", "08", "27", "2010", "08", "25"));
	}
}
