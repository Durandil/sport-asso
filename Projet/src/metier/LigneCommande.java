package metier;

import ihm.Client.FenetreCommandeArticle;

import java.sql.ResultSet;
import java.sql.SQLException;

import basededonnees.SGBD;


/**
 * <b>La classe LigneCommande représente une ligne de commande</b>
 * <p>
 * Une ligne de commande est caractérisée par les informations suivantes  :
 * <ul>
 * <li>Un identifiant d'article</li>
 * <li>Une quantité commandée de l'article en question</li>
 * </ul>
 * </p>

 * 
 * @see Commande 
 */
public class LigneCommande {

	/**
	 * L'Identifiant de l'article commandé
	 * 
	 * @see Article
	 */
	private String article;
	
	
	/**
	 * La quantité commandée de l'article
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#supprimerArticleBDD(String)
	 */
	private int quantite;
	
	/**
	 * Constructeur de la classe LigneCommande
	 * 
	 * @param idArticle
	 *            L'identifiant unique de l'article.
	 * @param description
	 *            La description de l'article.
	 * 
	 * @see LigneCommande#article
	 * @see LigneCommande#quantite
	 * @see FenetreCommandeArticle
	 */
	public LigneCommande(String article, int quantite) {

		this.article = article;
		this.quantite = quantite;
	}

	

	public void setArticle(String article) {
		this.article = article;
	}

	public String getArticle() {
		return article;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getQuantite() {
		return quantite;
	}

}
