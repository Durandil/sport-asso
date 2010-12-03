package test;

import metier.Promotion;
import junit.framework.TestCase;

public class PromotionTest extends TestCase {
	
//	public void testVerifierDatePromotion(){
//		boolean b=true;
//		assertEquals(b, Promotion.verifierDatePromotion("2015", "12", "24"));
//		boolean bo=false;
//		assertEquals(bo, Promotion.verifierDatePromotion("2010", "15", "09"));
//		assertEquals(bo, Promotion.verifierDatePromotion("2011", "02", "30"));
//		assertEquals(bo, Promotion.verifierDatePromotion("2014", "4", "31"));
//		
//	}
//
//	public void testVerifierOrdreDeuxDate(){
//		boolean b=true;
//		assertEquals(b, Promotion.verifierOrdreDeuxDate("2010", "12", "3", "2010", "12", "5"));
//		
//	}
	public void testVerifierOrdreDeuxDate2(){
		boolean b=true;
		boolean bo=false;
		System.out.println(Promotion.verifierOrdreDeuxDate("2010", "12", "3", "2010", "12", "3"));
		assertEquals(bo, Promotion.verifierOrdreDeuxDate("2010", "12", "3", "2010", "12", "3"));
		
	}
}
