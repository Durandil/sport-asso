package basededonnees;

public class BDD {
/**TODO Gérer les clés primaires et secondaires**/
	
	// Méthode permettant de créer la table que l'on veut 
	// à partir du String passé en paramètre
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
