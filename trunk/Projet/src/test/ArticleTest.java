package test;

import basededonnees.SGBD;
import metier.Article;
import metier.LigneCommande;
import metier.Promotion;
import junit.framework.TestCase;

/**
 * <b> La classe ArticleTest permet de valider les méthodes présentes dans la classe Article.</b>
 * <p> Cette classe possède 5 tests de méthodes. Elle hérite de la classe TestCase.
 * Il est important que les tests de SGBD aient été effectués au préalable de ces tests
 * car ils permettent de vérifier les éléments qui sont dans la base de données 
 * donc de vérifier que les articles ont bien été ajoutés ou supprimés de la base.
 * </p>
 * 
 * @see metier.Article
 */

public class ArticleTest extends TestCase {
	
	/**
	 * Méthodes qui testent la modification des articles dans la base de données.
	 */
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
	
	/**
	 * Méthodes qui testent la modification des stocks des articles.
	 * ajout d'articles
	 */
	public void testModifierStockArticleBDD(){
		Article.modifierStockArticleBDD("ART00001",12 );
		String result="362";
		assertEquals(result, SGBD.selectStringConditionString("Article", "Stock","IDArticle" , "ART00001"));
	}
	
	/**
	 * retirer des articles en stock.
	 */
	public void testModifierStockArticleBDD2(){
		Article.modifierStockArticleBDD("ART00001",-12 );
		String result="350";
		assertEquals(result, SGBD.selectStringConditionString("Article", "Stock","IDArticle" , "ART00001"));
	}
	
	/**
	 * Méthode qui valide la suppression d'articles dans la base de données
	 * (Ils passent à l'état "supprimé").
	 */
	public void testSupprimerArticleBDD(){
		Article.supprimerArticleBDD("ART00003");
		assertEquals("Supprimé", SGBD.selectStringConditionString("ARTICLE", "ETATARTICLE", "IDARTICLE", "ART00003"));
		
		String requete= "UPDATE ARTICLE SET ETATARTICLE='En stock'"
			+" WHERE IDARTICLE='ART00003'";
		System.out.println(requete);
		SGBD.executeUpdate(requete);
	}
	

}

