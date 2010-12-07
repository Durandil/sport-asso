package metier;

import java.util.ArrayList;

import ihm.FenetreFormulaireArticleGerant;
import basededonnees.SGBD;

/**
 * <b>La classe Article représente un article</b>
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
 * <p>
 * 
 * </p>
 * 
 * @see BDD,LigneCommande,Promotion
 */
public class Article {

	/**
	 * L'Identifiant de l'article, non modifiable
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#supprimerArticleBDD(String)
	 */
	private String idArticle;

	/**
	 * La description de l'article, modifiable par le gérant
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String)
	 */
	private String description;

	/**
	 * Le prix de l'article, modifiable par le gérant
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String)
	 */
	private double prixInitial;

	/**
	 * La quantité en stock de l'article, variant selon les commandes des
	 * clients. Le gérant peut également modifier le stock de l'article.
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String)
	 */
	private int stock;

	/**
	 * Le poids de l'article (en grammes), modifiable par le gérant
	 * 
	 * @see Article#ajouterBDD()
	 * @see Article#modifierArticleBDD(String, String, String, String, String)
	 */
	private float poids;

	/**
	 * La catégorie de sport auquel l'article se rattache
	 * 
	 * @see Article#ajouterBDD()
	 */
	private String typeSport;

	/**
	 * La catégorie de prix auquel l'article se rattache
	 * 
	 * @see Article#ajouterBDD()
	 */
	private String catPrix;

	/**
	 * L'état de l'article : varie selon les commandes des clients. Les
	 * réapprovisionnement éventuels du gérant affectent également l'état de
	 * l'article
	 * 
	 * @see Article#ajouterBDD()
	 */
	private String etat;

	/**
	 * Constructeur de la classe Article
	 * <p>
	 * Le constructeur de la classe Article fait appel à la méthode ajouterBDD()
	 * qui ajoute l'Article dans la base de données.
	 * </p>
	 * 
	 * @param idArticle
	 *            L'identifiant unique de l'article.
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
	 * @see Article#idArticle
	 * @see Article#description
	 * @see Article#prixInitial
	 * @see Article#stock
	 * @see Article#poids
	 * @see Article#typeSport
	 * @see Article#catPrix
	 * @see Article#etat
	 * @see Article#ajouterBDD()
	 */

	public Article(String idArticle, String description, double prixInitial,
			int stock, float poids, String typeSport, String catPrix,
			String etat) {

		super();
		this.idArticle = idArticle;
		this.description = description;
		this.prixInitial = prixInitial;
		this.stock = stock;
		this.poids = poids;
		this.typeSport = typeSport;
		this.catPrix = catPrix;
		this.etat = etat;
		ajouterBDD();
	}

	public String getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	public String getTypeSport() {
		return typeSport;
	}

	public void setTypeSport(String typeSport) {
		this.typeSport = typeSport;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public double getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(double prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}

	public String getCatPrix() {
		return catPrix;
	}

	public void setCatPrix(String catPrix) {
		this.catPrix = catPrix;
	}

	/**
	 * Ajoute l'article dans la table ARTICLE de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table afin
	 * de générer l'identifiant de l'article dans le format approprié.
	 * La requête se construit ensuite en fonction des caractéristiques de l'article
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
	public void ajouterBDD() {

		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
				"S_ARTICLE.NEXTVAL");

		String numArticle = "";
		int numeroArticle = 0;

		numeroArticle = Integer.parseInt(idNonFini.get(0));

		if (numeroArticle < 10) {
			numArticle = "ART0000" + numeroArticle;
		}
		if (numeroArticle < 100 && numeroArticle >= 10) {
			numArticle = "ART000" + numeroArticle;
		}
		if (numeroArticle < 1000 && numeroArticle >= 100) {
			numArticle = "ART00" + numeroArticle;
		}
		if (numeroArticle < 10000 && numeroArticle >= 1000) {
			numArticle = "ART0" + numeroArticle;
		}
		if (numeroArticle < 100000 && numeroArticle >= 10000) {
			numArticle = "ART" + numeroArticle;
		}

		String requete = "INSERT INTO ARTICLE (IDARTICLE, DESCRIPTION, PRIXINITIAL,"
				+ " STOCK, POIDS,IDTYPE,IDCATEGORIE, ETATARTICLE) VALUES ( '"
				+ numArticle
				+ "','"
				+ this.description
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

	}

	
	
	/**
	 * Modifie un article qui est déjà présent dans la table ARTICLE de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table afin
	 * de générer l'identifiant de l'article dans le format approprié.
	 * La requête se construit ensuite en fonction des caractéristiques de l'article
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
	
	public static void modifierArticleBDD(String numArticle,
			String description, String prix, String poids, String stock, 
			String idType, String idCat) {

		// POUR LE MOMENT ON CONSIDERE QU'ON NE MODIFIE PAS LA CATEGORIE DE PRIX
		// ET DE SPORT
		// D'UN ARTICLE donc on ne tient pas compte du todo ci dessous

		// TODO pour type de sport et categorie de prix, il faudrait récupérer
		// l'identifiant à partir du nom qui sera affiché dans le menu déroulant
		// du formulaire de modification d'un article


		String requete = " UPDATE ARTICLE" + " SET DESCRIPTION = '"
				+ description + "'," + "PRIXINITIAL = '" + prix + "',"
				+ "STOCK = '" + stock + "', " + "IDTYPE ='"+ idType + "',"
				+ "IDCATEGORIE ='" + idCat + "',"+ "POIDS = '" + poids
				+ "' WHERE IDARTICLE='" + numArticle + "'";

		System.out.println(requete);

		SGBD.executeUpdate(requete);
		SGBD.executeUpdate("COMMIT");
	}

	
	/**
	 * Supprime l'article dont l'identifiant est spécifié de la table ARTICLE 
	 * de la base de données
	 * 
	 * @param numArticle
	 * 			L'identifiant de l'article
	 * @see BDD
	 */
	public static void supprimerArticleBDD(String numArticle) {

		String requete = "DELETE FROM ARTICLE WHERE IDARTICLE='" + numArticle
				+ "'";
		System.out.println(requete);

		SGBD.executeUpdate(requete);

		String requete2 = "COMMIT";

		SGBD.executeUpdate(requete2);
	}

	
	/**
	 * Modifie le stock d'un article présent dans la table ARTICLE
	 * 
	 * @param numArticle
	 * 			L'identifiant de l'article
	 * @param stock
	 * 			Nouveau stock de l'article
	 * @see BDD
	 */
	public static void modifierStockArticleBDD(String numArticle, int stock) {

		// POUR CEUX QUI ONT ORACLE CHEZ EUX, il faut enlever le point de
		// virgule en fin de requete
		String stockPresentBase = SGBD.selectStringConditionString("ARTICLE",
				"STOCK", "IDARTICLE", numArticle);
		System.out.println(stockPresentBase);
		int nouveauStock = stock + Integer.parseInt(stockPresentBase);


		String requete = "UPDATE ARTICLE SET STOCK = '" + nouveauStock
				+ "',ETATARTICLE='En stock' WHERE IDARTICLE='" + numArticle
				+ "'";

		System.out.println(nouveauStock);
		

		System.out.println(requete);

		SGBD.executeUpdate(requete);
		SGBD.executeUpdate("COMMIT");
	}

}
