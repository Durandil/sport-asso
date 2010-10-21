package basededonnees;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;
import exception.ColonneInconnue;

public class SGBD {

	/**********************************************************************/
	/**Note : La plupart des m�thodes de cette classe sont issues du TP 2**/
	/**********************************************************************/
	static private Connection c;
	final static String URL = "jdbc:oracle:thin:@oraens10g:1521:ORAENS";
	private static final String ID = "id3199";
	private static final String MDP = "id3199";

	// M�thode issue du TP2
	public static boolean connecter() {
		boolean result = true;
		try { // une exception est soulev�e
			OracleDataSource ods = new OracleDataSource();
			ods.setUser(ID);
			ods.setPassword(MDP);
			ods.setURL(URL);
			c = ods.getConnection(); // connexion � la base
			System.out.println("Connexion � la base");
		} catch (SQLException e) {
			System.out.println("Echec de la tentative de connexion : "
					+ e.getMessage());
			result = false;

		}

		return result;
	}

	// M�thode issue du TP2
	public static boolean fermer() {
		boolean result = true;
		try {
			c.close();
			System.out.println("D�connexion � la base");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Echec de la tentative de connexion : "
					+ e.getMessage());
			result = false;
		}
		return result;
	}

	// M�thode issue du TP2
	public static void executeUpdate(String requete) {
		connecter();
		Statement st;
	
		try {
			st = c.createStatement();
			st.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fermer();
		}
	}

	// M�thode issue du TP2
	public static ResultSet executeQuery(String requete) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();
			res = st.executeQuery(requete);
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}
		
		return res;
	}

	// M�thode issue du TP2
	public static void insererTuples(String nomTable,
			ArrayList<String> nomColonnes, ArrayList<ArrayList<String>> valeurs) {
		connecter();
		String colonne = "(";
		for (int i = 0; i < nomColonnes.size(); i++) {

			if (i < nomColonnes.size() - 1) {
				colonne = colonne + nomColonnes.get(i) + " , ";
			} else {
				colonne = colonne + nomColonnes.get(i) + " )";
			}
		}

		for (List<String> ligneBD : valeurs) {
			String requete = new String();
			for (int j = 0; j < valeurs.get(1).size(); j++) {

				if (j < valeurs.get(1).size() - 1) {
					requete = requete + " '" + ligneBD.get(j) + "' ,";
				} else {
					requete = requete + " '" + ligneBD.get(j) + "' ";
				}
			}

			executeUpdate("INSERT INTO " + nomTable + " " + colonne + " VALUES"
					+ "(" + requete + ")");
		}

	}

	// M�thode issue du TP2
	public static void afficheSelectEtoile(String table) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT * FROM " + table);

			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			// Nombre de colonnes
			int taille = rsmd.getColumnCount();

			System.out.println("\n**********************************");
			// On affiche le nom des colonnes
			for (int i = 1; i <= taille; i++)
				System.out.print("\t" + rsmd.getColumnName(i).toUpperCase()
						+ "\t *");

			System.out.println("\n**********************************");

			while (res.next()) {
				for (int i = 1; i <= taille; i++)
					System.out.print("\t" + res.getObject(i).toString()
							+ "\t |");

				System.out.println("\n---------------------------------");

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}
	}

	// M�thode issue du TP2
	public static boolean testerTable(String nom) throws SQLException {
		boolean b = true;
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT * FROM " + nom);
		} catch (SQLException e) {
			b = false;
		} finally {
			fermer();
			return b;
		}

	}

	// M�thode issue du TP2
	public static void testerColonnes(String nomTable,
			ArrayList<String> nomColonne) throws ColonneInconnue {
		ArrayList<String> as = new ArrayList<String>(nomColonne);
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT * FROM " + nomTable);

			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			// Nombre de colonnes
			int taille = rsmd.getColumnCount();

			for (int j = 1; j <= taille; j++) {

				as.remove(rsmd.getColumnName(j));
			}

			if (as.size() > 0) {
				for (int i = 0; i < as.size(); i++) {
					System.out.println("La colonne " + as.get(i)
							+ " n'existe pas.");
				}
			} else {
				System.out.println("OK");
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		} finally {
			fermer();

		}
	}

	// M�thode issue du TP2
	public static String transformation(Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		String s;
		s = "TO_DATE('" + sqlDate + "', 'YYYY-MM-DD')";
		return s;
	}

	// M�thode issue du TP2
	public static void afficheSelectEtoileSelonProchainMois(String table,
			String colonneDate) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		Calendar date = Calendar.getInstance();
		int dateDebut = date.get(Calendar.MONTH) + 1;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT *  FROM " + table
					+ " WHERE EXTRACT(month from " + colonneDate + " ) = "
					+ dateDebut);
			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			// Nombre de colonnes
			int taille = rsmd.getColumnCount();

			while (res.next()) {
				for (int i = 1; i <= taille; i++)
					System.out.print("\t" + res.getObject(i).toString()
							+ "\t |");

				System.out.println("\n---------------------------------");

			}
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		} finally {
			fermer();

		}
	}
	
	/**TODO G�rer les cl�s primaires et secondaires**/
	
	// M�thode permettant de cr�er la table que l'on veut 
	// � partir du String pass� en param�tre
	public static void creerTable(String table) {

		executeUpdate("DROP TABLE " + table);

		if (table.equals("CLIENTS")) {
			executeUpdate("CREATE TABLE " + table 
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
					+ " ETATCOMPTE VARCHAR(9)) "
					);
			
		}
		
		if(table.equals("ARTICLES")){
	
			executeUpdate("CREATE TABLE " + table 
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
			executeUpdate("CREATE TABLE " + table 
					+ "( IDENTIFIANT VARCHAR(10), " 
					+ " DATECOMMANDE DATE, "
					+ " IDCLIENT VARCHAR(40))"
					);
		}
		
	}
	
	
	public static ArrayList<String[]> afficheSelectClients() {
		connecter();
		ArrayList<String[]> mailsClients = new ArrayList<String[]>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT MAIL,ETATCOMPTE FROM CLIENTS");

			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();
			
			
			while (res.next()) {
				
					String[] client = new String[2];
					String s = res.getObject(1).toString();
					String s2 = res.getObject(2).toString();
					client[0] =s;
					client[1] = s2;
					mailsClients.add(client);
					


			}
			
			

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}
		


		return mailsClients;
	}
}
