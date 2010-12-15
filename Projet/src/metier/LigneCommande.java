package metier;

import ihm.Client.FenetreCommandeArticle;

import java.sql.ResultSet;
import java.sql.SQLException;

import basededonnees.SGBD;


/**
 * <b>La classe LigneCommande repr�sente une ligne de commande</b>
 * <p>
 * Une ligne de commande est caract�ris�e par les informations suivantes  :
 * <ul>
 * <li>Un identifiant d'article</li>
 * <li>Une quantit� command�e de l'article en question</li>
 * </ul>
 * </p>

 * 
 * @see Commande 
 */
public class LigneCommande {

	/**
	 * L'Identifiant de l'article command�
	 * 
	 * @see LigneCommande#getIdArticle()
	 * @see LigneCommande#setIdArticle(String)
	 * @see Article
	 * 
	 */
	private String idArticle;
	
	
	/**
	 * La quantit� command�e de l'article
	 * 
	 * @see LigneCommande#getQuantite()
	 * @see LigneCommande#setQuantite(int)
	 * @see Article#ajouterBDD()
	 * @see Article#supprimerArticleBDD(String)
	 * 
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
	public LigneCommande(String idArticle, int quantite) {

		this.idArticle = idArticle;
		this.quantite = quantite;
	}

	 /**
     * Retourne l'id de l'article
     * 
     * @return L'identifiant de l'article
     * 
     */
	public String getIdArticle() {
		return idArticle;
	}
	
	/**
     * Met � jour l'id de l'article
     * 
     * @param idArticle
     *            L'identifiant unique de l'article
     * 
     */
	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	 /**
     * Retourne la quantit�
     * 
     * @return La quantit� command�e de l'article
     * 
     */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	
	/**
     * Met � jour la quantit�
     * 
     * @param quantite
     *            La quantit� command�e de l'article
     * 
     */
	public int getQuantite() {
		return quantite;
	}






}
