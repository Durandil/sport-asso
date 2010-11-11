package basededonnees;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import oracle.jdbc.pool.OracleDataSource;
import exception.ColonneInconnue;

public class SGBD {

	// Test SVN Google Code à l'Ensai
	/**********************************************************************/
	/**Note : La plupart des méthodes de cette classe sont issues du TP 2**/
	/**********************************************************************/
	static private Connection c;
	//final static String URL = "jdbc:oracle:thin:@oraens10g:1521:ORAENS";
	//URL à utiliser lorsque l'on est pas à l'Ensai :
	final static String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";
	
	//Penser à modifier les id/mdp
	private static final String ID = "id3199";
	private static final String MDP = "id3199";

	// Méthode issue du TP2
	public static boolean connecter() {
		boolean result = true;
		try { // une exception est soulevée
			OracleDataSource ods = new OracleDataSource();
			ods.setUser(ID);
			ods.setPassword(MDP);
			ods.setURL(URL);
			c = ods.getConnection(); // connexion à la base
			System.out.println("Connexion à la base");
		} catch (SQLException e) {
			System.out.println("Echec de la tentative de connexion : "
					+ e.getMessage());
			result = false;

		}

		return result;
	}

	// Méthode issue du TP2
	public static boolean fermer() {
		boolean result = true;
		try {
			c.close();
			System.out.println("Déconnexion à la base");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Echec de la tentative de connexion : "
					+ e.getMessage());
			result = false;
		}
		return result;
	}

	// Méthode issue du TP2
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

	// Méthode issue du TP2
	public static ResultSet executeQuery(String requete) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();
			res = st.executeQuery(requete);
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}
		
		return res;
	}

	// Méthode issue du TP2
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

	// Méthode issue du TP2
	public static void afficheSelectEtoile(String table) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT * FROM " + table);

			// Récupérer les méta données
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
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}
	}

	// Méthode issue du TP2
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

	// Méthode issue du TP2
	public static void testerColonnes(String nomTable,
			ArrayList<String> nomColonne) throws ColonneInconnue {
		ArrayList<String> as = new ArrayList<String>(nomColonne);
		connecter();
		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT * FROM " + nomTable);

			// Récupérer les méta données
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
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			fermer();

		}
	}

	// Méthode issue du TP2
	public static String transformation(Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		String s;
		s = "TO_DATE('" + sqlDate + "', 'YYYY-MM-DD')";
		return s;
	}
	
	public static Date stringToDate(String sDate, String sFormat)
			throws Exception {

		java.sql.Date sDate2 = new java.sql.Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		sDate2 = new java.sql.Date(sdf.parse(sDate).getTime());
		return sDate2;
	}

	
	// Méthode issue du TP2
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
			// Récupérer les méta données
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
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			fermer();

		}
	}
	
	//Méthode permettant d'obtenir l'ensemble des éléments (de nature String)
	//d'un champ issu d'une table tous deux précisés en paramètres
	public static ArrayList<String> selectListeString(String table, String str) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+str+" FROM "+table );

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				String s = res.getObject(1).toString();
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return listeString;
	}
	
	//Méthode permettant d'obtenir l'ensemble des éléments (de nature Float)
	//d'une variable issu d'une table tous deux précisés en paramètres
	public static ArrayList<Float> selectListeFloat(String table, String var) {
		connecter();
		ArrayList<Float> listeFloat = new ArrayList<Float>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table );

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				Float f = res.getFloat(1);
				listeFloat.add(f);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return listeFloat;
	}
	
	//Méthode permettant d'obtenir l'ensemble des éléments (de nature Integer)
	//d'une variable issu d'une table tous deux précisés en paramètres
	public static ArrayList<Integer> selectListeInt(String table, String var) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table );

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);
				

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return listeInt;
	}
	
	
	//Méthode permettant de récupérer un élément d'un champ (de nature String)
	//En apposant une condition sur un autre champ (dont les éléments sont aussi de nature String)
	public static String selectStringConditionString(String table, String champ, String champDeCondition, String condition) {
		connecter();
		String s = null;
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT "+champ+" FROM "+table+" WHERE "+ champDeCondition+" = '"
					+ condition + "'");

			// Récupérer les méta données
			while (res.next()) {

				s = res.getObject(1).toString();

			}
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return s;
	}
	
	//Méthode permettant de récupérer l'ensemble des éléments de DEUX champs (de nature string)
	//d'une table donnée (tous entrés en paramètres)
	public static ArrayList<String[]> selectDeuxChampsString(String table, String champ1, String champ2) {
		connecter();
		ArrayList<String[]> liste = new ArrayList<String[]>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT "+champ1+","+champ2+" FROM "+table);

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				String[] listeString = new String[2];
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				listeString[0] = s;
				listeString[1] = s2;
				liste.add(listeString);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return liste;
	}
	
	// Méthode qui permettra de récupérer les statistiques sur le montant des commandes d'un
	// client pour la fiche client. avg ( moyenne), min et max.
	// pour le moment je me sers de la quantite, plus tard il faudra travailler sur
	// le prix x quantité ( requete imbriquée à réaliser)
	public static String statistiqueClassiqueClient(String identifiant, String statistique){
		connecter();
		String resultat ;
		Statement st = null;
		ResultSet res = null;
		String rs = "";
		
		try {
			st = c.createStatement();
			res= st.executeQuery("SELECT"+ statistique + "(QUANTITE) FROM COMMANDES,INFOCOMMANDES,CLIENT" +
					"WHERE COMMANDES.IDENTIFIANT=INFOCOMMANDES.IDCOMMANDE and CLIENT.IDENTIFIANT=" +
					"COMMANDES.IDCLIENT and MAIL="+ identifiant +";");
			
			rs = res.getString(0);
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		}
		finally{
			fermer();
		}
		return rs;
	}
	
	// Méthode qui récuperera les attributs d'un client à partir de son identifiant
	public static ArrayList<String> recupererAttributClient(String mailIdentifiant){
		connecter();
		ArrayList<String> client = new ArrayList<String>();
		
		/*ArrayList<String> listeMails = new ArrayList<String>();
		listeMails = SGBD.selectListeString("CLIENTS", "MAIL");
		
		for(int i=0;i<listeMails.size();i++){
			if(mailIdentifiant.equals(listeMails.get(i))){
				
			}
			
		}*/
		
		Statement st = null ;
		ResultSet res= null;
		try{
			st=c.createStatement();
			res= st.executeQuery("SELECT MAIL,NOM,PRENOM,DENOMINATION,VILLE," +
								"CODEPOSTAL,TELEPHONE,FIDELITE,ACTIFCOMPTE FROM CLIENTS " +
								"WHERE mail='"+ mailIdentifiant+"';");
			ResultSetMetaData rsmd = res.getMetaData();
			
			for(int i=1;i==rsmd.getColumnCount();i++){
				client.add(res.getString(i));
			}
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		}
		finally{
			fermer();
		}
		
		return client;
	}
	
	// Méthode qui permet de selectionner des articles 
	// dont le stock est inférieur à la quantité seuil ou en rupture de stock
	public ArrayList<ArrayList<String>> selectArticlesReapprovisionnement(){
		
		connecter();
		ArrayList<ArrayList<String>> article = new ArrayList<ArrayList<String>>();
		Statement st = null;
		ResultSet res = null;
		
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("select idarticle,description,stock,prixinitial " + "from ARTICLE " +
					"where stock=0 or idarticle = ( select id article from articles,categorie " +
					"where article.idcategorie = categorie.idcategorie and stock< quantitelimite) ;" );

			while (res.next()) {

				ArrayList<String> listeString = new ArrayList<String>();
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				String s3 = res.getObject(3).toString();
				String s4 = res.getObject(4).toString();
				listeString.add(s);
				listeString.add(s2);
				listeString.add(s3);
				listeString.add(s4);
				article.add(listeString);
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			fermer();
		}
		
		return article ;
	}
	
	
	
}
