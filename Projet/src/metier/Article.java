package metier;

import java.util.ArrayList;
import basededonnees.SGBD;

/**
 * <b>La classe Article repr�sente un article.</b>
 * <p>
 * Un article est caract�ris� par les informations suivantes (que l'on retrouve
 * dans la base de donn�es) :
 * <ul>
 * <li>Un identifiant unique attribu� d�finitivement, de la forme ARTxxxxx</li>
 * <li>Une description</li>
 * <li>Un prix initial (avant �ventuelles r�ductions et/ou promotions)</li>
 * <li>La quantit� disponible en stock dans le magasin</li>
 * <li>Un poids</li>
 * <li>L'identifiant de la cat�gorie du sport auquel l'article se rattache</li>
 * <li>L'identifiant de la cat�gorie "Prix" (ensemble de couples
 * Quantit�/R�duction)</li>
 * <li>Un �tat (En stock, Rupture de stock, D�stock�, etc.)</li>
 * </ul>
 * </p>
 * 
 * @see basededonnees.BDD
 * @see LigneCommande
 * @see Promotion
 */
public class Article {

	/**
	 * L'Identifiant de l'article, non modifiable
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#supprimerArticleBDD(String)
	 * @see Article#getIdArticle()
	 * @see Article#setIdArticle(String)
	 */
	private String idArticle;

	/**
	 * La description de l'article, modifiable par le g�rant
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String,
	 *      String, String)
	 * @see Article#getDescription()
	 * @see Article#setDescription(String)
	 * 
	 */
	private String description;

	/**
	 * Le prix de l'article, modifiable par le g�rant
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String,
	 *      String, String)
	 * @see Article#getPrixInitial()
	 * @see Article#setPrixInitial(double)
	 * 
	 */
	private double prixInitial;

	/**
	 * La quantit� en stock de l'article, variant selon les commandes des
	 * clients. Le g�rant peut �galement modifier le stock de l'article.
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String,
	 *      String, String)
	 * @see Article#getStock()
	 * @see Article#setStock(int)
	 * 
	 */
	private int stock;

	/**
	 * Le poids de l'article (en grammes), modifiable par le g�rant
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String,
	 *      String, String)
	 * @see Article#getPoids()
	 * @see Article#setPoids(float)
	 * 
	 */
	private float poids;

	/**
	 * La cat�gorie de sport auquel l'article se rattache
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#getTypeSport()
	 * @see Article#setTypeSport(String)
	 * 
	 */
	private String typeSport;

	/**
	 * La cat�gorie de prix auquel l'article se rattache
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#getCatPrix()
	 * @see Article#setCatPrix(String)
	 * 
	 */
	private String catPrix;

	/**
	 * L'�tat de l'article : varie selon les commandes des clients. Les
	 * r�approvisionnement �ventuels du g�rant affectent �galement l'�tat de
	 * l'article
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#getEtat()
	 * @see Article#setEtat(String)
	 * 
	 */
	private String etat;

	/**
	 * Constructeur de la classe Article.
	 * <p>
	 * Le constructeur de la classe Article fait appel � la m�thode ajouterBDD()
	 * qui ajoute l'Article dans la base de donn�es.
	 * </p>
	 * 
	 * @param description
	 *            La description de l'article.
	 * @param prixInitial
	 *            Le prix de l'article avant �ventielles promotions et/ou
	 *            r�ductions.
	 * @param stock
	 *            La quantit� en stock de l'article.
	 * @param poids
	 *            Le poids de l'article.
	 * @param typeSport
	 *            L'identifiant de la cat�gorie de sport de l'article.
	 * @param catPrix
	 *            L'identifiant de la cat�gorie prix de l'article.
	 * @param etat
	 *            L'�tat de l'article en magasin.
	 * 
	 * @see Article#description
	 * @see Article#prixInitial
	 * @see Article#stock
	 * @see Article#poids
	 * @see Article#typeSport
	 * @see Article#catPrix
	 * @see Article#etat
	 * @see Article#ajouterBDD()
	 */
	public Article(String description, double prixInitial, int stock,
			float poids, String typeSport, String catPrix, String etat) {

		this.description = description;
		this.prixInitial = prixInitial;
		this.stock = stock;
		this.poids = poids;
		this.typeSport = typeSport;
		this.catPrix = catPrix;
		this.etat = etat;
		ajouterBDD();
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
	 * Retourne l'identifiant du type de sport
	 * 
	 * @return L'identifiant du type de sport auquel l'article est rattach�
	 * 
	 */
	public String getTypeSport() {
		return typeSport;
	}

	/**
	 * Met � jour l'id du type de sport de l'article
	 * 
	 * @param typeSport
	 *            L'identifiant de la cat�gorie de sport de l'article.
	 * 
	 */
	public void setTypeSport(String typeSport) {
		this.typeSport = typeSport;
	}

	/**
	 * Retourne la description de l'article
	 * 
	 * @return La description de l'article
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Met � jour la description de l'article
	 * 
	 * @param description
	 *            La description de l'article.
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retourne le poids de l'article
	 * 
	 * @return Le poids de l'article (en grammes)
	 * 
	 */
	public float getPoids() {
		return poids;
	}

	/**
	 * Met � jour le poids de l'article
	 * 
	 * @param poids
	 *            Le poids de l'article.
	 * 
	 */
	public void setPoids(float poids) {
		this.poids = poids;
	}

	/**
	 * Retourne le prix initial de l'article
	 * 
	 * @return Le prix initial de l'article (avant �ventuelles promotions et/ou
	 *         r�ductions)
	 * 
	 */
	public double getPrixInitial() {
		return prixInitial;
	}

	/**
	 * Met � jour le prix de l'article
	 * 
	 * @param prixInitial
	 *            Le prix de l'article avant �ventuelles promotions et/ou
	 *            r�ductions.
	 * 
	 */
	public void setPrixInitial(double prixInitial) {
		this.prixInitial = prixInitial;
	}

	/**
	 * Retourne la quantit� en stock de l'article
	 * 
	 * @return La quantit� en stock de l'article
	 * 
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Met � jour la quantit� en stock de l'article
	 * 
	 * @param stock
	 *            La quantit� en stock de l'article.
	 * 
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Retourne l'�tat de l'article dans le magasin
	 * 
	 * @return L'�tat de l'article dans le magasin (En stock, D�stock�, En
	 *         rupture de stock, Supprim�)
	 * 
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Met � jour l'�tat de l'article dans le magasin
	 * 
	 * @param etat
	 *            L'�tat de l'article en magasin.
	 * 
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Retourne l'identifiant de la cat�gorie de prix de l'article
	 * 
	 * @return L'identifiant de la cat�gorie de prix de l'article
	 * 
	 */
	public String getCatPrix() {
		return catPrix;
	}

	/**
	 * Met � jour l'identifiant de la cat�gorie de prix
	 * 
	 * @param catPrix
	 *            L'identifiant de la cat�gorie prix de l'article.
	 * 
	 */
	public void setCatPrix(String catPrix) {
		this.catPrix = catPrix;
	}

	/**
	 * Ajoute l'article dans la table ARTICLE de la base de donn�es
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer l'indice de s�quence de la table
	 * afin de g�n�rer l'identifiant de l'article dans le format appropri�. La
	 * requ�te se construit ensuite en fonction des caract�ristiques de
	 * l'article saisies lors de l'appel du constructeur.<br>
	 * La m�thode replaceAll est utilis�e pour doubler les �ventuelles
	 * apostrophes pr�sentes dans la description, �vitant ainsi une erreur lors
	 * de l'ex�cution de la requ�te.
	 * </p>
	 * 
	 * @see Article#Article(String, double, int, float, String, String, String)
	 * @see basededonnees.BDD
	 */
	public void ajouterBDD() {

		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
				"S_ARTICLE.NEXTVAL");

		int numeroArticle = 0;
		numeroArticle = Integer.parseInt(idNonFini.get(0));

		if (numeroArticle < 10) {
			this.idArticle = "ART0000" + numeroArticle;
		}
		if (numeroArticle < 100 && numeroArticle >= 10) {
			this.idArticle = "ART000" + numeroArticle;
		}
		if (numeroArticle < 1000 && numeroArticle >= 100) {
			this.idArticle = "ART00" + numeroArticle;
		}
		if (numeroArticle < 10000 && numeroArticle >= 1000) {
			this.idArticle = "ART0" + numeroArticle;
		}
		if (numeroArticle < 100000 && numeroArticle >= 10000) {
			this.idArticle = "ART" + numeroArticle;
		}

		String requete = "INSERT INTO ARTICLE (IDARTICLE, DESCRIPTION, PRIXINITIAL,"
				+ " STOCK, POIDS,IDTYPE,IDCATEGORIE, ETATARTICLE) VALUES ( '"
				+ this.idArticle
				+ "','"
				+ this.description.replaceAll("'", "''")
				+ "',"
				+ this.prixInitial
				+ ","
				+ this.stock
				+ ","
				+ this.poids
				+ ",'"
				+ this.typeSport
				+ "','"
				+ this.catPrix
				+ "','"
				+ this.etat
				+ "')";
		SGBD.executeUpdate(requete);
		SGBD.executeUpdate("COMMIT");
	}

	/**
	 * Modifie un article qui est d�j� pr�sent dans la table ARTICLE de la base
	 * de donn�es.
	 * <p>
	 * La requ�te se construit en fonction des caract�ristiques de l'article
	 * saisies lors de l'appel de la m�thode. La m�thode replaceAll est utilis�e
	 * pour doubler les �ventuelles apostrophes pr�sentes dans la description,
	 * �vitant ainsi une erreur lors de l'ex�cution de la requ�te
	 * </p>
	 * 
	 * @param idArticle
	 *            L'identifiant unique de l'article.
	 * @param description
	 *            La description de l'article.
	 * @param prix
	 *            Le prix de l'article.
	 * @param poids
	 *            Le poids de l'article.
	 * @param stock
	 *            La quantit� en stock de l'article.
	 * @param idType
	 *            L'identifiant de la cat�gorie de sport de l'article.
	 * @param idCat
	 *            L'identifiant de la cat�gorie prix de l'article.
	 * 
	 * @see basededonnees.BDD
	 */
	public static void modifierArticleBDD(String idArticle, String description,
			String prix, String poids, String stock, String idType, String idCat) {

		String requete = " UPDATE ARTICLE" + " SET DESCRIPTION = '"
				+ description.replaceAll("'", "''") + "'," + "PRIXINITIAL = '"
				+ prix + "'," + "STOCK = '" + stock + "', " + "IDTYPE ='"
				+ idType + "'," + "IDCATEGORIE ='" + idCat + "'," + "POIDS = '"
				+ poids + "' WHERE IDARTICLE='" + idArticle + "'";

		SGBD.executeUpdate(requete);

	}

	/**
	 * Supprime l'article dont l'identifiant est sp�cifi� de la table ARTICLE de
	 * la base de donn�es
	 * 
	 * @param idArticle
	 *            L'identifiant de l'article
	 * @see basededonnees.BDD
	 */
	public static void supprimerArticleBDD(String idArticle) {

		String requete = "UPDATE ARTICLE SET ETATARTICLE='Supprim�' WHERE IDARTICLE='"
				+ idArticle + "'";

		SGBD.executeUpdate(requete);

	}

	/**
	 * Modifie le stock d'un article pr�sent dans la table ARTICLE
	 * 
	 * @param numArticle
	 *            L'identifiant de l'article
	 * @param stock
	 *            Nouveau stock de l'article
	 * @see basededonnees.BDD
	 */
	public static void modifierStockArticleBDD(String numArticle, int stock) {

		String stockPresentBase = SGBD.selectStringConditionString("ARTICLE",
				"STOCK", "IDARTICLE", numArticle);
		System.out.println(stockPresentBase);
		int nouveauStock = stock + Integer.parseInt(stockPresentBase);

		String requete = "UPDATE ARTICLE SET STOCK = '" + nouveauStock
				+ "',ETATARTICLE='En stock' WHERE IDARTICLE='" + numArticle
				+ "'";

		SGBD.executeUpdate(requete);

	}

}
