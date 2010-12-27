package metier;

import java.util.ArrayList;
import basededonnees.SGBD;

/**
 * <b>La classe Article représente un article.</b>
 * <p>
 * Un article est caractérisé par les informations suivantes (que l'on retrouve
 * dans la base de données) :
 * <ul>
 * <li>Un identifiant unique attribué définitivement, de la forme ARTxxxxx</li>
 * <li>Une description</li>
 * <li>Un prix initial (avant éventuelles réductions et/ou promotions)</li>
 * <li>La quantité disponible en stock dans le magasin</li>
 * <li>Un poids</li>
 * <li>L'identifiant de la catégorie du sport auquel l'article se rattache</li>
 * <li>L'identifiant de la catégorie "Prix" (ensemble de couples
 * Quantité/Réduction)</li>
 * <li>Un état (En stock, Rupture de stock, Déstocké, etc.)</li>
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
	 * La description de l'article, modifiable par le gérant
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
	 * Le prix de l'article, modifiable par le gérant
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
	 * La quantité en stock de l'article, variant selon les commandes des
	 * clients. Le gérant peut également modifier le stock de l'article.
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
	 * Le poids de l'article (en grammes), modifiable par le gérant
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
	 * La catégorie de sport auquel l'article se rattache
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#getTypeSport()
	 * @see Article#setTypeSport(String)
	 * 
	 */
	private String typeSport;

	/**
	 * La catégorie de prix auquel l'article se rattache
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#getCatPrix()
	 * @see Article#setCatPrix(String)
	 * 
	 */
	private String catPrix;

	/**
	 * L'état de l'article : varie selon les commandes des clients. Les
	 * réapprovisionnement éventuels du gérant affectent également l'état de
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
	 * Le constructeur de la classe Article fait appel à la méthode ajouterBDD()
	 * qui ajoute l'Article dans la base de données.
	 * </p>
	 * 
	 * @param description
	 *            La description de l'article.
	 * @param prixInitial
	 *            Le prix de l'article avant éventielles promotions et/ou
	 *            réductions.
	 * @param stock
	 *            La quantité en stock de l'article.
	 * @param poids
	 *            Le poids de l'article.
	 * @param typeSport
	 *            L'identifiant de la catégorie de sport de l'article.
	 * @param catPrix
	 *            L'identifiant de la catégorie prix de l'article.
	 * @param etat
	 *            L'état de l'article en magasin.
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
	 * Met à jour l'id de l'article
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
	 * @return L'identifiant du type de sport auquel l'article est rattaché
	 * 
	 */
	public String getTypeSport() {
		return typeSport;
	}

	/**
	 * Met à jour l'id du type de sport de l'article
	 * 
	 * @param typeSport
	 *            L'identifiant de la catégorie de sport de l'article.
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
	 * Met à jour la description de l'article
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
	 * Met à jour le poids de l'article
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
	 * @return Le prix initial de l'article (avant éventuelles promotions et/ou
	 *         réductions)
	 * 
	 */
	public double getPrixInitial() {
		return prixInitial;
	}

	/**
	 * Met à jour le prix de l'article
	 * 
	 * @param prixInitial
	 *            Le prix de l'article avant éventuelles promotions et/ou
	 *            réductions.
	 * 
	 */
	public void setPrixInitial(double prixInitial) {
		this.prixInitial = prixInitial;
	}

	/**
	 * Retourne la quantité en stock de l'article
	 * 
	 * @return La quantité en stock de l'article
	 * 
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Met à jour la quantité en stock de l'article
	 * 
	 * @param stock
	 *            La quantité en stock de l'article.
	 * 
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Retourne l'état de l'article dans le magasin
	 * 
	 * @return L'état de l'article dans le magasin (En stock, Déstocké, En
	 *         rupture de stock, Supprimé)
	 * 
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Met à jour l'état de l'article dans le magasin
	 * 
	 * @param etat
	 *            L'état de l'article en magasin.
	 * 
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Retourne l'identifiant de la catégorie de prix de l'article
	 * 
	 * @return L'identifiant de la catégorie de prix de l'article
	 * 
	 */
	public String getCatPrix() {
		return catPrix;
	}

	/**
	 * Met à jour l'identifiant de la catégorie de prix
	 * 
	 * @param catPrix
	 *            L'identifiant de la catégorie prix de l'article.
	 * 
	 */
	public void setCatPrix(String catPrix) {
		this.catPrix = catPrix;
	}

	/**
	 * Ajoute l'article dans la table ARTICLE de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table
	 * afin de générer l'identifiant de l'article dans le format approprié. La
	 * requête se construit ensuite en fonction des caractéristiques de
	 * l'article saisies lors de l'appel du constructeur.<br>
	 * La méthode replaceAll est utilisée pour doubler les éventuelles
	 * apostrophes présentes dans la description, évitant ainsi une erreur lors
	 * de l'exécution de la requête.
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
	 * Modifie un article qui est déjà présent dans la table ARTICLE de la base
	 * de données.
	 * <p>
	 * La requête se construit en fonction des caractéristiques de l'article
	 * saisies lors de l'appel de la méthode. La méthode replaceAll est utilisée
	 * pour doubler les éventuelles apostrophes présentes dans la description,
	 * évitant ainsi une erreur lors de l'exécution de la requête
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
	 *            La quantité en stock de l'article.
	 * @param idType
	 *            L'identifiant de la catégorie de sport de l'article.
	 * @param idCat
	 *            L'identifiant de la catégorie prix de l'article.
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
	 * Supprime l'article dont l'identifiant est spécifié de la table ARTICLE de
	 * la base de données
	 * 
	 * @param idArticle
	 *            L'identifiant de l'article
	 * @see basededonnees.BDD
	 */
	public static void supprimerArticleBDD(String idArticle) {

		String requete = "UPDATE ARTICLE SET ETATARTICLE='Supprimé' WHERE IDARTICLE='"
				+ idArticle + "'";

		SGBD.executeUpdate(requete);

	}

	/**
	 * Modifie le stock d'un article présent dans la table ARTICLE
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
