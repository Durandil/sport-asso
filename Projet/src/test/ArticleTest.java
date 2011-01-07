package test;

import basededonnees.SGBD;
import metier.Article;
import metier.LigneCommande;
import metier.Promotion;
import junit.framework.TestCase;

/**
 * <b> La classe ArticleTest permet de valider les m�thodes pr�sentes dans la classe Article.</b>
 * <p> Cette classe poss�de 5 tests de m�thodes. Elle h�rite de la classe TestCase.
 * Il est important que les tests de SGBD aient �t� effectu�s au pr�alable de ces tests
 * car ils permettent de v�rifier les �l�ments qui sont dans la base de donn�es 
 * donc de v�rifier que les articles ont bien �t� ajout�s ou supprim�s de la base.
 * </p>
 * 
 * @see metier.Article
 */

public class ArticleTest extends TestCase {
	
	/**
	 * M�thodes qui testent la modification des articles dans la base de donn�es.
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
	 * M�thodes qui testent la modification des stocks des articles.
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
	 * M�thode qui valide la suppression d'articles dans la base de donn�es
	 * (Ils passent � l'�tat "supprim�").
	 */
	public void testSupprimerArticleBDD(){
		Article.supprimerArticleBDD("ART00003");
		assertEquals("Supprim�", SGBD.selectStringConditionString("ARTICLE", "ETATARTICLE", "IDARTICLE", "ART00003"));
		
		String requete= "UPDATE ARTICLE SET ETATARTICLE='En stock'"
			+" WHERE IDARTICLE='ART00003'";
		System.out.println(requete);
		SGBD.executeUpdate(requete);
	}
	

}

