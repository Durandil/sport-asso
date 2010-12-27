package test;

import basededonnees.SGBD;
import metier.Article;
import junit.framework.TestCase;

public class ArticleTest extends TestCase {
	

	public void testModifierArticleBDD(){
		Article.modifierArticleBDD("ART00002","Velo de Course" ,"251" ,"5" ,"0","TYP00006","CAT00001"  );
		String result= "251";
		assertEquals(result, SGBD.selectStringConditionString("Article", "PrixInitial", "IDArticle", "ART00002"));

	}
	
	public void testModifierArticleBDD2(){
		Article.modifierArticleBDD("ART00002","Velo de Course" ,"250" ,"5" ,"0","TYP00006","CAT00001"  );
		String result= "250";
		assertEquals(result, SGBD.selectStringConditionString("Article", "PrixInitial", "IDArticle", "ART00002"));
		
	}
	
	public void testModifierStockArticleBDD(){
		Article.modifierStockArticleBDD("ART00001",12 );
		String result="362";
		assertEquals(result, SGBD.selectStringConditionString("Article", "Stock","IDArticle" , "ART00001"));
	}
	
	public void testModifierStockArticleBDD2(){
		Article.modifierStockArticleBDD("ART00001",-12 );
		String result="350";
		assertEquals(result, SGBD.selectStringConditionString("Article", "Stock","IDArticle" , "ART00001"));
	}
	
	public void testSupprimerArticleBDD(){
		Article.supprimerArticleBDD("ART00003");
		assertEquals("Supprim�", SGBD.selectStringConditionString("ARTICLE", "ETATARTICLE", "IDARTICLE", "ART00003"));
		
		String requete= "UPDATE ARTICLE SET ETATARTICLE='En stock'"
			+" WHERE IDARTICLE='ART00003'";
		System.out.println(requete);
		SGBD.executeUpdate(requete);
	}
	

	
}

