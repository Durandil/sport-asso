package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import basededonnees.SGBD;

public class LigneCommande {

	// Une ligne de commande se définit par un article et la quantité associée
	public LigneCommande(String article, String quantite) {

		this.article = article;
		this.quantite = quantite;
	}

	private String article;
	private String quantite;

	public void setArticle(String article) {
		this.article = article;
	}

	public String getArticle() {
		return article;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	public String getQuantite() {
		return quantite;
	}

}
