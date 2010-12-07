package test;

import java.util.ArrayList;

import basededonnees.SGBD;
import metier.Article;
import junit.framework.TestCase;

public class ArticleTest extends TestCase {
	

	public void testModifierArticleBDD(){
		Article.modifierArticleBDD("ART00002","Velo de Course" ,"251" ,"5" ,"0" );
		String result= "251";
		assertEquals(result, SGBD.selectStringConditionString("Article", "PrixInitial", "IDArticle", "ART00002"));
		
	}
	
	public void testModifierStockArticleBDD(){
		Article.modifierStockArticleBDD("ART00001",12 );
		String result="362";
		assertEquals(result, SGBD.selectStringConditionString("Article", "Stock","IDArticle" , "ART00001"));
	}
	
	public void testSupprimerArticleBDD(){
		Article.supprimerArticleBDD("ART00003");
		ArrayList<String> result = new ArrayList<String>();
		result.add("ART00001");
		result.add("ART00002");
		assertEquals(result, SGBD.selectListeString("ARTICLE","IDARTICLE"));
	}
	
}

