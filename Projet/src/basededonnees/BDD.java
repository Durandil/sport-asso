package basededonnees;

/**
 * Classe ayant pour but d'initialiser la base de donn�es.
 * 
 */
public class BDD {

	/**
	 * M�thode permettant d'initialiser la base de donn�es � travers les
	 * requ�tes SQL.
	 * 
	 * @see SGBD#executeUpdate(String)
	 */
	public static void init() {

		// Attention si la m�thode est lanc�e en dehors de l'Ensai elle ne
		// marchera pas
		// Passer en commentaire la 2�me moiti� de la m�thode, ex�cuter la 1�re
		// Puis passer la 1�re en commentaire et ex�cuter la seconde

		// On commence par supprimer les tables avant de les (re)cr�er
		SGBD.executeUpdate("DROP TABLE MESSAGE");
		SGBD.executeUpdate("DROP TABLE LISTING_PROMOS_ARTICLES");
		SGBD.executeUpdate("DROP TABLE LISTING_ARTICLES_COMMANDES");
		SGBD.executeUpdate("DROP TABLE CARTE_FIDELITE");
		SGBD.executeUpdate("DROP TABLE ARTICLE");
		SGBD.executeUpdate("DROP TABLE COMMANDE");
		SGBD.executeUpdate("DROP TABLE CLIENT");
		SGBD.executeUpdate("DROP TABLE VILLE");
		SGBD.executeUpdate("DROP TABLE PROMO");
		SGBD.executeUpdate("DROP TABLE TYPE_SPORT");
		SGBD.executeUpdate("DROP TABLE REDUCTION");
		SGBD.executeUpdate("DROP TABLE CATEGORIE");
		SGBD.executeUpdate("DROP TABLE QUANTITE");
		SGBD.executeUpdate("DROP SEQUENCE S_ARTICLE ");
		SGBD.executeUpdate("DROP SEQUENCE S_PROMOTION");
		SGBD.executeUpdate("DROP SEQUENCE S_FIDELITE");
		SGBD.executeUpdate("DROP SEQUENCE S_MESSAGE");
		SGBD.executeUpdate("DROP SEQUENCE S_COMMANDE");

		SGBD.executeUpdate("CREATE TABLE QUANTITE" + "(IDQUANTITE CHAR(8),"
				+ "QUANTITE NUMBER(4),"
				+ "CONSTRAINT PK_QUANTITE PRIMARY KEY (IDQUANTITE))");

		SGBD.executeUpdate("CREATE TABLE CATEGORIE" + "(IDCATEGORIE CHAR(8),"
				+ "NOMCATEGORIE VARCHAR(20)," + "QUANTITELIMITE NUMBER(6),"
				+ "CONSTRAINT PK_CATEGORIE PRIMARY KEY (IDCATEGORIE))");

		SGBD.executeUpdate("CREATE TABLE REDUCTION"
				+ "(IDCATEGORIE CHAR(8),"
				+ "IDQUANTITE CHAR(8),"
				+ "POURCENTAGE NUMBER(3,1),"
				+ "CONSTRAINT PK_REDUCTION PRIMARY KEY (IDCATEGORIE,IDQUANTITE),"
				+ "CONSTRAINT FK_CATEGORIE_QUANTITE FOREIGN KEY (IDQUANTITE) REFERENCES QUANTITE,"
				+ "CONSTRAINT FK_QUANTITE_CATEGORIE FOREIGN KEY (IDCATEGORIE) REFERENCES CATEGORIE)");

		SGBD.executeUpdate("CREATE TABLE TYPE_SPORT" + "(IDTYPE CHAR(8),"
				+ "NOMTYPE VARCHAR(20),"
				+ "CONSTRAINT PK_TYPE_SPORT PRIMARY KEY (IDTYPE))");

		SGBD.executeUpdate("CREATE SEQUENCE S_PROMOTION  Increment by 1 start with 5 nocache");
		SGBD.executeUpdate("CREATE TABLE PROMO" + "(IDPROMO CHAR(8),"
				+ "NOMPROMO VARCHAR(40)," 
				+ "DATEDEBUT DATE," 
				+ "DATEFIN DATE,"
				+ "POURCENTAGEPROMO NUMBER(3,1)," 
				+ "PROMOFIDELITE NUMBER,"
				+ "CONSTRAINT PK_PROMO PRIMARY KEY (IDPROMO))");

		SGBD.executeUpdate("CREATE TABLE VILLE" + "(IDVILLE CHAR(8),"
				+ "CODEPOSTAL CHAR(5)," + "NOMVILLE VARCHAR(20),"
				+ "CONSTRAINT PK_VILLE PRIMARY KEY (IDVILLE))");

		SGBD.executeUpdate("CREATE TABLE CLIENT"
				+ "(IDCLIENT VARCHAR(40),"
				+ " NOMCLIENT VARCHAR(40),"
				+ " PRENOMCLIENT VARCHAR(40),"
				+ " DENOMINATIONCLIENT VARCHAR(40),"
				+ " ADRESSECLIENT VARCHAR(60),"
				+ " IDVILLE CHAR(8),"
				+ " TELEPHONE CHAR(10),"
				+ " ETATCOMPTE VARCHAR(10),"
				+ "MOTDEPASSE VARCHAR(20),"
				+ "CONSTRAINT PK_CLIENT PRIMARY KEY (IDCLIENT),"
				+ "CONSTRAINT FK_CLIENT_VILLE FOREIGN KEY (IDVILLE) REFERENCES VILLE)");

		SGBD.executeUpdate("CREATE SEQUENCE S_COMMANDE Increment by 1 start with 5 nocache");
		SGBD.executeUpdate("CREATE TABLE COMMANDE"
				+ "(IDCOMMANDE CHAR(8),"
				+ "DATECOMMANDE DATE,"
				+ "IDCLIENT VARCHAR(40),"
				+ "MONTANTCOMMANDE NUMBER(8,2),"
				+ "CONSTRAINT PK_COMMANDE PRIMARY KEY (IDCOMMANDE),"
				+ "CONSTRAINT FK_COMMANDE_CLIENT FOREIGN KEY (IDCLIENT) REFERENCES CLIENT)");

		SGBD.executeUpdate("CREATE SEQUENCE S_ARTICLE Increment by 1 start with 9 nocache");
		SGBD.executeUpdate("CREATE TABLE ARTICLE"
				+ "(IDARTICLE CHAR(8),"
				+ "DESCRIPTION VARCHAR(40),"
				+ "PRIXINITIAL NUMBER(6,2),"
				+ "STOCK NUMBER(6),"
				+ "POIDS NUMBER(6),"
				+ "IDTYPE CHAR(8),"
				+ "IDCATEGORIE CHAR(8),"
				+ "ETATARTICLE VARCHAR(20),"
				+ "CONSTRAINT PK_ARTICLE PRIMARY KEY (IDARTICLE),"
				+ "CONSTRAINT FK_ARTICLE_TYPE_SPORT FOREIGN KEY (IDTYPE) REFERENCES TYPE_SPORT,"
				+ "CONSTRAINT FK_ARTICLE_CATEGORIE FOREIGN KEY (IDCATEGORIE) REFERENCES CATEGORIE)");

		SGBD.executeUpdate("CREATE SEQUENCE S_FIDELITE  Increment by 1 start with 3 nocache");
		SGBD.executeUpdate("CREATE TABLE CARTE_FIDELITE"
				+ "(IDCARTEFIDELITE CHAR(8),"
				+ "NBPOINTS NUMBER(5),"
				+ "IDCLIENT VARCHAR(40),"
				+ "CONSTRAINT PK_CARTE_FIDELITE PRIMARY KEY (IDCARTEFIDELITE),"
				+ "CONSTRAINT FK_CARTE_FIDELITE_CLIENT FOREIGN KEY (IDCLIENT) REFERENCES CLIENT)");

		SGBD.executeUpdate("CREATE TABLE LISTING_ARTICLES_COMMANDES"
				+ "(IDCOMMANDE CHAR(8),"
				+ "IDARTICLE CHAR(8),"
				+ "QUANTITECOMMANDEE NUMBER(5),"
				+ "CONSTRAINT PK_LISTING_ARTICLES_COMMANDES PRIMARY KEY (IDCOMMANDE, IDARTICLE),"
				+ "CONSTRAINT FK_COMMANDE_ARTICLE FOREIGN KEY (IDARTICLE) REFERENCES ARTICLE,"
				+ "CONSTRAINT FK_ARTICLE_COMMANDE FOREIGN KEY (IDCOMMANDE) REFERENCES COMMANDE)");

		SGBD.executeUpdate("CREATE TABLE LISTING_PROMOS_ARTICLES"
				+ "(IDARTICLE CHAR(8),"
				+ "IDPROMO CHAR(8),"
				+ "CONSTRAINT PK_LISTING_PROMOS_ARTICLES PRIMARY KEY (IDPROMO, IDARTICLE),"
				+ "CONSTRAINT FK_PROMO_ARTICLE FOREIGN KEY (IDARTICLE) REFERENCES ARTICLE,"
				+ "CONSTRAINT FK_ARTICLE_PROMO FOREIGN KEY (IDPROMO) REFERENCES PROMO)");

		SGBD.executeUpdate("CREATE SEQUENCE S_MESSAGE  Increment by 1 start with 6 nocache");
		SGBD.executeUpdate("CREATE TABLE MESSAGE"
				+ "(IDMESSAGE CHAR(8),"
				+ "SUJETMESSAGE VARCHAR(90),"
				+ "CONTENUMESSAGE VARCHAR(300),"
				+ "IDCLIENT VARCHAR(40),"
				+ "DATEMESSAGE DATE,"
				+ "ESTENVOYEAUGERANT NUMBER(2),"
				+ "CONSTRAINT PK_MESSAGE PRIMARY KEY (IDMESSAGE),"
				+ "CONSTRAINT FK_MESSAGE_CLIENT FOREIGN KEY (IDCLIENT) REFERENCES CLIENT)");

	}

}
