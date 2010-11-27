package metier;

import basededonnees.SGBD;

public class Article {

	


	// Constructeur d'un article
	// catSport correspond à la catégorie du sport auquel l'article se rattache
	// catPrix correspond à la catégorie "prix" (ensemble de couples
	// (quantité/réduction))

	//Le constructeur ajoute l'article dans la table ARTICLES
	
	public Article(String idArticle, String description, String typeSport,
			float poids, double prixInitial, int stock, String etat, String catPrix) {
		super();

		this.idArticle = idArticle;
		this.description = description;
		this.typeSport = typeSport;
		this.poids = poids;
		this.prixInitial = prixInitial;
		this.stock = stock;
		this.setEtat(etat);
		this.catPrix = catPrix;
		ajouterBDD();
	}

	private String idArticle;
	private String typeSport;
	private String description;
	private float poids;
	private double prixInitial;
	private int stock;
	private String etat;
	private String catPrix;


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

	// Méthode permettant d'ajouter un article dans la table ARTICLES
	public void ajouterBDD() {
				

		String requete = "INSERT INTO ARTICLE (IDARTICLE, DESCRIPTION, PRIXINITIAL," +
				" STOCK, POIDS,IDTYPE,IDCATEGORIE, ETATARTICLE) VALUES ( S_ARTICLE.NextVal"
				+ ",'"
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
				+"')";

		SGBD.executeUpdate(requete);
		
	}
	
	public static void modifierArticleBDD(String numArticle,String description,String prix,String poids,String stock){
		
		// POUR LE MOMENT ON CONSIDERE QU'ON NE MODIFIE PAS LA CATEGORIE DE PRIX ET DE SPORT
		// D'UN ARTICLE donc on ne tient pas compte du todo ci dessous
		
		// TODO pour type de sport et categorie de prix, il faudrait récupérer
		// l'identifiant à partir du nom qui sera affiché dans le menu déroulant
		// du formulaire de modification d'un article
		
		System.out.println("Article");
		
		// POUR CEUX QUI ONT ORACLE CHEZ EUX, il faut enlever le point de virgule en fin de requete
		
		String requete=" UPDATE ARTICLE" 
					   +" SET DESCRIPTION = '" + description +"'," +
					   	"PRIXINITIAL = '" + prix +"'," +
					   	"STOCK = '" + stock + "', " +
					   	"POIDS = '" + poids +"' WHERE IDARTICLE='"+
					   	numArticle +"'"
						;
		
		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
	}
	
	public static void supprimerArticleBDD(String numArticle){
		
		String requete = "DELETE FROM ARTICLE WHERE IDARTICLE='"+numArticle+"'";
		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
		
		String requete2 ="COMMIT";
		
		SGBD.executeUpdate(requete2);
	}
}
