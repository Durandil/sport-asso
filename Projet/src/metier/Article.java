package metier;

import basededonnees.SGBD;

public class Article {

	// Constructeur d'un article
	// catSport correspond à la catégorie du sport auquel l'article se rattache
	// catPrix correspond à la catégorie "prix" (ensemble de couples
	// (quantité/réduction))

	//Le constructeur ajoute l'article dans la table ARTICLES
	
	public Article(String idArticle, String description, String catSport,
			float poids, double prixInitial, int stock, String catPrix) {
		super();

		this.idArticle = idArticle;
		this.description = description;
		this.catSport = catSport;
		this.poids = poids;
		this.prixInitial = prixInitial;
		this.stock = stock;
		this.catPrix = catPrix;
		ajouterBDD();
	}

	private String idArticle;
	private String catSport;
	private String description;
	private float poids;
	private double prixInitial;
	private int stock;
	private String catPrix;


	public String getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	public String getCatSport() {
		return catSport;
	}

	public void setCatSport(String catSport) {
		this.catSport = catSport;
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

	public String getCatPrix() {
		return catPrix;
	}

	public void setCatPrix(String catPrix) {
		this.catPrix = catPrix;
	}

	// Méthode permettant d'ajouter un article dans la table ARTICLES
	public void ajouterBDD() {

		String requete = "INSERT INTO ARTICLES (IDENTIFIANT, DESCRIPTION, CATSPORT, POIDS, PRIXINITIAL, STOCK, CATPRIX) VALUES ( "
				+ "'"
				+ this.idArticle
				+ "',"
				+ "'"
				+ this.description
				+ "',"
				+ "'"
				+ this.catSport
				+ "',"
				+ this.poids
				+ ","
				+ this.prixInitial 
				+ "," 
				+ this.stock 
				+ ","
				+ "'"
				+ this.catPrix
				+ "'"
				+") ";

		SGBD.executeUpdate(requete);
	}
	
}
