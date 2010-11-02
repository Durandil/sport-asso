package basededonnees;

public class BDD {
/**TODO Gérer les clés primaires et secondaires**/
	
	// Méthode permettant de créer la table que l'on veut 
	// à partir du String passé en paramètre
	public static void init(){
		SGBD.executeUpdate("CREATE TABLE QUANTITE" +
				"(IDQUANTITE CHAR(8),"+
				"QUANTITE NUMBER(4),"+
				"CONSTRAINT PK_QUANTITE PRIMARY KEY (IDQUANTITE)");


		SGBD.executeUpdate("CREATE TABLE CATEGORIE" +
				"(IDCATEGORIE CHAR(8),"+
				"NOMCATEGORIE VARCHAR(20),"+
				"QUANTITELIMITE NUMBER(6),"+
				"CONSTRAINT PK_CATEGORIE PRIMARY KEY (IDCATEGORIE)");
	

		SGBD.executeUpdate("CREATE TABLE REDUCTION" +
				"(IDCATEGORIE CHAR(8),"+
				"IDQUANTITE CHAR(8),"+
				"POURCENTAGE NUMBER(5),"+
				"CONSTRAINT PK_DISPOSE PRIMARY KEY (IDCATEGORIE,IDQUANTITE),"+
				"CONSTRAINT FK_CATEGORIE_QUANTITE FOREIGN KEY ");
		
		Create table Dispose
		(idCategorie char(8),
		idQuantite char(8),
		pourcentage number(5),
		constraint pk_DISPOSE Primary Key (idCategorie, idQuantite),
		constraint fk_CATEGORIE_QUANTITE Foreign Key (idQuantite) references QUANTITE,
		constraint fk_QUANTITE_CATEGORIE Foreign Key (idCategorie) references CATEGORIE)


		Create table TYPE_SPORT
		(idType char(8),
		NomType varchar(20),
		constraint pk_TYPE_SPORT Primary Key (idType))


		Create table PROMO
		(idPromo char(8),
		NomPromo varchar(20),
		dateDebut Date,
		dateFin Date,
		PourcentagePromo number(3),
		constraint pk_PROMO Primary Key (idPromo))

		Create table VILLE
		(idVille char(8),
		NomVille varchar(20),
		constraint pk_VILLE Primary Key (idVille)
		)


		Create table CLIENT
		(idClient char(8),
		Mdp varchar(20),
		NomClient varchar(20),
		PrenomClient varchar(20),
		AdresseClient varchar(20),
		ClientActif boolean,
		idVille char(8),
		constraint pk_article Primary Key (idClient),
		constraint fk_CLIENT_VILLE Foreign Key (idVille) references VILLE)

		Create table COMMANDE
		(idCommande char(8),
		DateCommande Date,
		idClient char(8),
		constraint pk_COMMANDE Primary Key (idCommande),
		constraint fk_COMMANDE_CLIENT Foreign Key (idClient) references CLIENT
		)



		Create table ARTICLE
		(idArticle char(8),
		PrixInitial number(10),
		NomArticle varchar(20),
		Stock number(6),
		Poids number(5),
		idType char(8),
		idCategorie char(8),
		constraint pk_article Primary Key (idArticle),
		constraint fk_ARTICLE_TYPE_SPORT Foreign Key (idType) references TYPE_SPORT,
		constraint fk_ARTICLE_CATEGORIE Foreign Key (idCategorie) references CATEGORIE)

		Create table CARTE_FIDELITE
		(idCarteFidelite char(8),
		Nbpoints number(5),
		idClient char(8)
		constraint pk_CARTE_FIDELITE Primary Key (idCarteFidelite),
		constraint fk_CARTE_FIDELITE_CLIENT Foreign Key (idClient) references CLIENT)

		Create table Se_trouve
		(idArticle char(8),
		idCommande char(8),
		quantiteCommande number(5),
		constraint pk_SE_TROUVE Primary Key (idCommande, idArticle),
		constraint fk_COMMANDE_ARTICLE Foreign Key (idArticle) references ARTICLE
		constraint fk_ARTICLE_COMMANDE Foreign Key (idCommande) references COMMANDE)


		Create table Fait_objet
		(idArticle char(8),
		idPromo char(8),
		constraint pk_FAIT_OBJET Primary Key (idPromo, idArticle),
		constraint fk_PROMO_ARTICLE Foreign Key (idArticle) references ARTICLE
		constraint fk_ARTICLE_PROMO Foreign Key (idPromo) references PROMO))



	}
	
	public static void creerTable(String table) {

		SGBD.executeUpdate("DROP TABLE " + table);

		if (table.equals("CLIENTS")) {
			SGBD.executeUpdate("CREATE TABLE " + table 
					+ " (MAIL VARCHAR(40),"
					+ " NOM VARCHAR(40),"
					+ " PRENOM VARCHAR(40),"
					+ " DENOMINATION VARCHAR(40),"
					+ " ADRESSE VARCHAR(60),"
					+ " VILLE VARCHAR(40),"
					+ " CODEPOSTAL VARCHAR(5),"
					+ " TELEPHONE VARCHAR(20),"
					+ " CARTEFIDEL VARCHAR(3),"
					+ " NBPOINTS NUMBER(6),"
					+ " ETATCOMPTE VARCHAR(9), "
					+ " MOTDEPASSE VARCHAR(7))"
					);
			
		}
		
		if(table.equals("ARTICLES")){
	
			SGBD.executeUpdate("CREATE TABLE " + table 
					+ " (IDENTIFIANT VARCHAR(10), " 
					+ " DESCRIPTION VARCHAR(40), "
					+ " CATSPORT VARCHAR(40), "
					+ " POIDS NUMBER(8), "
					+ " PRIXINITIAL NUMBER(6,2), "
					+ " STOCK NUMBER(4),"
					+ " ETAT VARCHAR(20),"
					+ " CATPRIX VARCHAR(3))"
					);
		}
		
		if(table.equals("COMMANDES")){
			SGBD.executeUpdate("CREATE TABLE " + table 
					+ "( IDENTIFIANT VARCHAR(10), " 
					+ " DATECOMMANDE DATE, "
					+ " IDCLIENT VARCHAR(40))"
					);
		}
		
		if(table.equals("INFOCOMMANDES")){
			SGBD.executeUpdate("CREATE TABLE " + table 
					+ " (IDCOMMANDE VARCHAR(10), " 
					+ " DATECOMMANDE DATE, "
					+ " IDCLIENT VARCHAR(40)," 
					+ " IDARTICLE VARCHAR(10),"
					+ " QUANTITE NUMBER(3))"
					);
		}
		
	}
	
	
}
