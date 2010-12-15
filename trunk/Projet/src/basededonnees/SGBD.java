package basededonnees;

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

	// Test SVN Google Code � l'Ensai
	/**********************************************************************/
	/**Note : La plupart des m�thodes de cette classe sont issues du TP 2**/
	/**********************************************************************/
	static private Connection c;
	final static String URL = "jdbc:oracle:thin:@oraens10g:1521:ORAENS";
	//URL � utiliser lorsque l'on est pas � l'Ensai :
	//final static String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";
	
	/**  TODO TODO TODO TODO TODO TODO TODO   **/
	/** TODO : Penser � modifier les id/mdp ! **/
	/**  TODO TODO TODO TODO TODO TODO TODO   **/


	private static final String ID = "id3193";
	private static final String MDP = "id3193";

	private static String compteurViewStatistiqueArticle;
	
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
		Statement st;
		try {
			
			st = c.createStatement();
			st.executeUpdate("COMMIT");
			
			c.close();
			System.out.println("D�connexion � la base");
		} catch (SQLException e) {
			
			System.out.println("Echec de la tentative de connexion : "
					+ e.getMessage());
			System.out.println("RAISON 1: " + e.getCause());
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
			System.out.println("RAISON 2: " + e.getErrorCode());

		} finally {
			System.out.println("Tentative de sauvegarde");
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
	@SuppressWarnings("finally")
	public static boolean testerTable(String nom) throws SQLException {
		boolean b = true;
		connecter();
		Statement st = null;
		@SuppressWarnings("unused")
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
	
	public static Date stringToDate(String sDate, String sFormat)
			throws Exception {

		java.sql.Date sDate2 = new java.sql.Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		sDate2 = new java.sql.Date(sdf.parse(sDate).getTime());
		return sDate2;
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
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments (de nature String)
	//d'un champ issu d'une table tous deux pr�cis�s en param�tres
	public static ArrayList<String> selectListeString(String table, String str) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+str+" FROM "+table );

			while (res.next()) {
				
				String s = res.getObject(1).toString();
				
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeString;
	}
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments (de nature String)
	//d'un champ issu d'une table tous deux pr�cis�s en param�tres
	public static ArrayList<String> selectListeStringOrdonne(String table, String str,String champOrdre) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();

			res = st.executeQuery("SELECT "+str+" FROM "+table + " ORDER BY "+ champOrdre+" ASC");

			while (res.next()) {
				
				String s = res.getObject(1).toString();
				
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeString;
	}
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments ordonn�s
	//d'un champ de type date issu d'une table tous deux pr�cis�s en param�tres
	//LE format que l'on d�sire obtenir est aussi pr�cis� en param�tre
	public static ArrayList<String> selectListeDatesOrdonne(String table, String str, String format,String champOrdre) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT TO_CHAR("+str+",'" + format + "') FROM "+table +" ORDER BY "+ champOrdre);

			while (res.next()) {

				String s = res.getObject(1).toString();
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeString;
	}
	//M�thode permettant d'obtenir l'ensemble des �l�ments (de nature Float)
	//d'une variable issu d'une table tous deux pr�cis�s en param�tres
	public static ArrayList<Float> selectListeFloat(String table, String var) {
		connecter();
		ArrayList<Float> listeFloat = new ArrayList<Float>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table );

			while (res.next()) {

				Float f = res.getFloat(1);
				listeFloat.add(f);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeFloat;
	}
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments (de nature Integer)
	//d'une variable issu d'une table tous deux pr�cis�s en param�tres
	public static ArrayList<Integer> selectListeInt(String table, String var) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table );

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);
				

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeInt;
	}
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments ordonn�s(de nature Integer)
	//d'une variable issu d'une table tous deux pr�cis�s en param�tres
	public static ArrayList<Integer> selectListeIntOrdonne(String table, String var,String champOrdre) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table+" ORDER BY "+ champOrdre+" ASC");

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);
				

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeInt;
	}
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments ordonn�s(de nature Integer)
	//d'une variable issu d'une table avec une condition WHERE tous deux pr�cis�s en param�tres
	public static ArrayList<Integer> selectListeIntOrdonneCondition(String table, String var,String champOrdre,String cond) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table+" WHERE "+ cond +" ORDER BY "+ champOrdre+" ASC");

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);
				

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeInt;
	}
	
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments ordonn�s(de nature String)
	//d'une variable issu d'une table avec une condition WHERE tous deux pr�cis�s en param�tres
	public static ArrayList<String> selectListeStringOrdonneCondition(String table, String var,String champOrdre,String cond) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT "+var+" FROM "+table+" WHERE "+cond +" AND "+ var + " IS NOT NULL ORDER BY "+ champOrdre+" ASC");

			while (res.next()) {

				String s = res.getObject(1).toString();;
				listeString.add(s);
				
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return listeString;
	}
	
	//M�thode permettant de r�cup�rer un �l�ment d'un champ (de nature String)
	//En apposant une condition sur un autre champ (dont les �l�ments sont aussi de nature String)
	public static String selectStringConditionString(String table, String champ, String champDeCondition, String condition) {
		connecter();
		String s = null;
		Statement st = null;
		ResultSet res = null;

		try {

			st = c.createStatement();
			
			
			res = st.executeQuery("SELECT "+champ+" FROM "+table+" WHERE "+ champDeCondition+" = '"
					+ condition + "'");

			
				while (res.next()) {
					
			//Si le r�sultat est non nul tout se passe normalement
					if(res.getObject(1) != null){
					
						s = res.getObject(1).toString();
						
					}
			//Sinon, on affecte un espace au String renvoy� (cf. Classe FenetreDialogGestionCompteClient)
			//(lorsque l'on y chercher � v�rifier si un client poss�de une d�nomination pour savoir si c'est un particulier)
					else
					{
						s = " ";
					}

				}
				
			
			
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			fermer();

		}
		//Si la requ�te ne renvoie rien, on "remplit" s par "rien
		
		return s;
	}
	
	//M�thode permettant de r�cup�rer un �l�ment d'un champ (de nature String)
	//En apposant une condition sur un autre champ (dont les �l�ments sont aussi de nature String)
	public static String selectDateConditionString(String table, String champDate, String champDeCondition, String condition, String format) {
		connecter();
		String s = null;
		Statement st = null;
		ResultSet res = null;

		try {

			st = c.createStatement();
			
			
			res = st.executeQuery("SELECT TO_CHAR("+champDate+",'" + format + "')"+" FROM "+table+" WHERE "+ champDeCondition+" = '"
					+ condition + "'");
			
			System.out.println("SELECT TO_CHAR("+champDate+",'" + format + "')"+" FROM "+table+" WHERE "+ champDeCondition+" = '"
					+ condition + "'");
			
				while (res.next()) {
					
			//Si le r�sultat est non nul tout se passe normalement
					if(res.getObject(1) != null){
					
						s = res.getObject(1).toString();
						
					}
			//Sinon, on affecte un espace au String renvoy� (cf. Classe FenetreDialogGestionCompteClient)
			//(lorsque l'on y chercher � v�rifier si un client poss�de une d�nomination pour savoir si c'est un particulier)
					else
					{
						s = " ";
					}

				}
				
			
			
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			fermer();

		}
		//Si la requ�te ne renvoie rien, on "remplit" s par "rien
		
		return s;
	}
	
	//M�thode permettant de r�cup�rer l'ensemble des �l�ments de DEUX champs (de nature string)
	//d'une table donn�e (tous entr�s en param�tres)
	public static ArrayList<String[]> selectDeuxChampsString(String table, String champ1, String champ2) {
		connecter();
		ArrayList<String[]> liste = new ArrayList<String[]>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT "+champ1+","+champ2+" FROM "+table);
			

			while (res.next()) {

				String[] listeString = new String[2];
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				listeString[0] = s;
				listeString[1] = s2;
				liste.add(listeString);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();

		}

		return liste;
	}
	
	//TODO 
	// M�thode qui permettra de r�cup�rer les statistiques sur le montant des commandes d'un
	// client pour la fiche client.  moyenne, min et max.

	public static String statistiqueClassiqueClient(String identifiant, String statistique){
		connecter();
		Statement st = null;
		ResultSet res = null;
		String rs = "0";
		
		try {
			st = c.createStatement();

			res= st.executeQuery("select "+ statistique+"(MONTANTCOMMANDE) from commande WHERE" +
					" IDCLIENT='"+identifiant+"'");
			
			while(res.next()){
				if(res.getString(1) != null){
					rs = res.getString(1).toString();
				}
			}
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			fermer();
		}
		return rs;
	}
	
	public static String nbreCommandeClient(String identifiant){
		connecter();
		Statement st = null;
		ResultSet res = null;
		String rs = "0";
		
		try {
			st = c.createStatement();
			
			res= st.executeQuery("select count(*) from commande where" +
					" IDCLIENT='"+identifiant+"'");
			
			while(res.next()){
				if(res.getString(1) != null){
					rs = res.getString(1).toString();
				}				
			}
		
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			fermer();
		}
		return rs;
	}

	public static ArrayList<String> StatistiquePlusGrosseCommande(String identifiant){
		connecter();
		Statement st = null;
		ResultSet res = null;
		ArrayList<String> listeStat = new ArrayList<String>();
		String id ="Aucune";
		String date = "NA";

		try {
			st = c.createStatement();
			res= st.executeQuery("select IDCOMMANDE,DATECOMMANDE from commande " +
					" WHERE MONTANTCOMMANDE=(SELECT max(MONTANTCOMMANDE) from commande)" +
					" and IDCLIENT='"+identifiant+"'");
			
			
			while(res.next()){
				if(res.getString(1) != null){
					id=res.getString(1).toString();
				}
				if(res.getString(2) != null){
					date=res.getString(2).toString();
				}
			}
			
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			fermer();
			listeStat.add(id);
			listeStat.add(date);
		}
		return listeStat;
	}
	
	
	
	// M�thode qui permettra de r�cup�rer les statistiques sur l'article le plus commande par un client donn�
	public static String statistiqueArticleClient(String identifiant){
		connecter();
		Statement st = null;
		ResultSet res2=null;
		String rs = "Aucun";
		String nomVue="totalParArticle";
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_VUESTATARTICLE.NEXTVAL");
		compteurViewStatistiqueArticle = idNonFini.get(0);
		nomVue = nomVue+compteurViewStatistiqueArticle;
		System.out.println(nomVue);
		
		try {
			st = c.createStatement();
			
			st.executeQuery("create view "+ nomVue +" as " +
							"select idArticle,sum(quantitecommandee) as totalQuantite "+
							"from LISTING_ARTICLES_COMMANDES "+
							"where IDCOMMANDE IN (select idCommande from COMMANDE WHERE IDCLIENT='"+identifiant+"') GROUP BY IDARTICLE");
			
			st.executeUpdate("COMMIT");
			
			res2 = st.executeQuery(" select idArticle from "+ nomVue +
							" where totalQuantite = (select max(totalQuantite) from " +nomVue+" )");
			
			while(res2.next()){
				if(res2.getString(1) != null){
					rs = res2.getString(1).toString();
				}
			}
			
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();
		}
		return rs;
	}
	
	// TODO 
	// M�thode qui r�cuperera les attributs d'un client � partir de son identifiant
	public static ArrayList<String> recupererAttributClient(String mailIdentifiant){
		connecter();
		ArrayList<String> client = new ArrayList<String>();
		
		Statement st = null ;
		ResultSet res= null;
		try{
			st=c.createStatement();
			res= st.executeQuery("SELECT IDCLIENT,NOMCLIENT,PRENOMCLIENT,DENOMINATIONCLIENT," +
								"ADRESSECLIENT,CODEPOSTAL,NOMVILLE,TELEPHONE," +
								"ETATCOMPTE FROM CLIENT VILLE" +
								"WHERE IDCLIENT='"+ mailIdentifiant+"'" + "AND CLIENT.IDVILLE = VILLE.IDVILLE;");
			
			ResultSetMetaData rsmd = res.getMetaData();
			
			for(int i=1;i==rsmd.getColumnCount();i++){
				client.add(res.getString(i));
			}
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			fermer();
		}
		
		return client;
	}
	
	// M�thode qui permet de selectionner des articles 
	// dont le stock est inf�rieur � la quantit� seuil ou en rupture de stock
	public static ArrayList<ArrayList<String>> selectArticlesReapprovisionnement(){
		
		connecter();
		ArrayList<ArrayList<String>> article = new ArrayList<ArrayList<String>>();
		ArrayList<String> listeString1 = new ArrayList<String>();
		ArrayList<String> listeString2 = new ArrayList<String>();
		ArrayList<String> listeString3 = new ArrayList<String>();
		ArrayList<String> listeString4 = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		
		try {
		
			st = c.createStatement();
			
			String requete = "select idarticle,description,stock,prixinitial "+
			" from ARTICLE where (etatarticle !='Supprim�' and (stock=0 or idarticle in ( select idarticle from article, "
			+" categorie where article.idcategorie = categorie.idcategorie and "
			+" stock< categorie.QUANTITELIMITE)))";
			System.out.println(requete);
			res = st.executeQuery(requete);

			while (res.next()) {

				
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				String s3 = res.getObject(3).toString();
				String s4 = res.getObject(4).toString();
				listeString1.add(s);
				listeString2.add(s2);
				listeString3.add(s3);
				listeString4.add(s4);
				
			}
			article.add(listeString1);
			article.add(listeString2);
			article.add(listeString3);
			article.add(listeString4);

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			fermer();
		}
		
		return article ;
	}
	
	// TODO cette m�thode doit permettre de r�cup�rer l'ensemble des indormations n�cessaires 
	// � la commande n� idCommande pass�e en param�tre
	// il faudrait calculer le total par article en tenant compte % promo selon quantit�
	public static ArrayList<Object[]> informationCommande(String idCommande){
		SGBD.connecter();
		ArrayList<Object[]> commande = new ArrayList<Object[]>();
		
		Statement st = null ;
		ResultSet res= null;
		try{
			
			st=c.createStatement();
			res= st.executeQuery("SELECT ARTICLE.IDARTICLE, DESCRIPTION, PRIXINITIAL,QUANTITECOMMANDEE" +
								 " FROM ARTICLE, LISTING_ARTICLES_COMMANDES" +
								 " WHERE ARTICLE.IDARTICLE=LISTING_ARTICLES_COMMANDES.IDARTICLE AND" +
								 " LISTING_ARTICLES_COMMANDES.IDCOMMANDE ='"+ idCommande +"'");
			
			while (res.next()) {

				String[] listeString = new String[4];
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				String s3 = res.getObject(3).toString();
				String s4 = res.getObject(4).toString();
				listeString[0]=s;
				listeString[1]=s2;
				listeString[2]=s3;
				listeString[3]=s4;
				commande.add(listeString);
			}
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
			}
		finally{
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			SGBD.fermer();
		}
		
		
		return commande;
	}
	
	// cette m�thode a pour but de recuprer le boolean sur la fidelit� du client 
	// et son nombre �ventuel de points
	public static ArrayList<String> recupererInformationFideliteClient(String identifiant){
		SGBD.connecter();
		Statement st = null ;
		ResultSet res= null;
		String fidele = "Non";
		String nbpoints = "0";
		ArrayList <String> resultat = new ArrayList<String>();
		
		try{
			st=c.createStatement();
			
			// ON VA TESTER POUR LE CLIENT SI SON IDCLIENT EST DANS LA TABLE DE CEUX
			// QUI ONT UNE CARTE DE FIDELITE
			
			System.out.println("SELECT NBPOINTS FROM CARTE_FIDELITE,CLIENT" +
					" WHERE CARTE_FIDELITE.IDCLIENT=CLIENT.IDCLIENT AND CARTE_FIDELITE.IDCLIENT='"+identifiant+"'");
			
			res=st.executeQuery("SELECT NBPOINTS FROM CARTE_FIDELITE,CLIENT" +
								" WHERE CARTE_FIDELITE.IDCLIENT=CLIENT.IDCLIENT AND CARTE_FIDELITE.IDCLIENT='"+identifiant+"'");
			

			while (res.next()) {
			
		//Si le r�sultat est non nul tout se passe normalement
				if(res.getObject(1) != null){
				
					fidele = "Oui";
					nbpoints = res.getObject(1).toString();
					
				}
		//Sinon, on affecte un espace au String renvoy� (cf. Classe FenetreDialogGestionCompteClient)
		//(lorsque l'on y chercher � v�rifier si un client poss�de une d�nomination pour savoir si c'est un particulier)
		
			}
			
			resultat.add(fidele);
			resultat.add(nbpoints);
			
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			
			System.out.println("Sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			SGBD.fermer();
		}
		
		return resultat;
	}
	
	// cette methode permet de r�cup�rer le num�ro du dernier enregistrement de la table entr�e en parametre
	public static int recupererIdentifiantDernierEnregistrementTable(String table,String champ){
		SGBD.connecter();
		Statement st = null ;
		ResultSet res= null;
		int resultat=0;
		
		try{
			st=c.createStatement();
			res=st.executeQuery("SELECT MAX(TO_NUMBER("+champ+")) FROM "+table+ " ;");
			
			resultat=Integer.parseInt(res.getObject(1).toString());
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			System.out.println("Tentative de sauvegarde");
			SGBD.executeUpdate("COMMIT");
			SGBD.fermer();
		}
		return resultat;
	}
	
	// Cette m�thode permet de selectionner tous les individus satifaisant l'un des crit�res remplis
	// dans la fenetre de recherche d'un client chez le g�rant
	// Elle retournera uniquement l'identifiant de l'utilisateur
	// Pour r�cup�rer les autres attributs, on utilisera SelectConditionString
	public static ArrayList<ArrayList<String>> recupererInformationRechercheClient(String idClient,String nomClient,String denomination,String ville){
		SGBD.connecter();
		Statement st = null ;
		ResultSet res= null;
		ArrayList<String> listeString1 = new ArrayList<String>();
		ArrayList<String> listeString2 = new ArrayList<String>();
		ArrayList<String> listeString3 = new ArrayList<String>();
		ArrayList<String> listeString4 = new ArrayList<String>();
		ArrayList<ArrayList<String>> informationsClient = new ArrayList<ArrayList<String>>();
		
		try{
			st=c.createStatement();
			String conditionWhereOr= "WHERE (";
			boolean ajouterChamp=false;
			
			if(!idClient.equals("")){
				conditionWhereOr=conditionWhereOr+"IDCLIENT Like '%"+idClient +"%'";
				ajouterChamp=true;
			}
			
			if(!denomination.equals("")){
				if(ajouterChamp==true){
					conditionWhereOr=conditionWhereOr+" or ";
				}
				conditionWhereOr=conditionWhereOr+"DENOMINATIONCLIENT Like '%"+denomination +"%'";
				ajouterChamp=true;
			}
			
			if(!nomClient.equals("")){
				if(ajouterChamp==true){
					conditionWhereOr=conditionWhereOr+" or ";
					ajouterChamp=false;
				}
				conditionWhereOr=conditionWhereOr+"NOMCLIENT Like '%"+nomClient +"%'";
				ajouterChamp=true;
			}
			
			if(!ville.equals("")){
				if(ajouterChamp==true){
					conditionWhereOr=conditionWhereOr+" or ";
					ajouterChamp=false;
				}
				conditionWhereOr=conditionWhereOr+"NOMVILLE Like '%"+ville+"%'";
				ajouterChamp=true;
			}
			
			// si un utilisateur ne remplit aucun champ et appuie sur le bouton
			// il pourra voir la liste des clients
			if(conditionWhereOr.equals("WHERE (")){
				conditionWhereOr="WHERE VILLE.IDVILLE = CLIENT.IDVILLE";
				System.out.println("condition 5");
			}
			else{
				conditionWhereOr=conditionWhereOr+" ) AND (VILLE.IDVILLE = CLIENT.IDVILLE) ";
			}

			res=st.executeQuery("SELECT IDCLIENT , NOMCLIENT, PRENOMCLIENT,DENOMINATIONCLIENT" +
					" FROM CLIENT, VILLE " + conditionWhereOr );
			
			while (res.next()){
				
				String s,s2,s3,s4;
				s = res.getObject(1).toString();
				
				listeString1.add(s);
				if(res.getObject(2) != null){
					
					s2 = res.getObject(2).toString();
					s3 = res.getObject(3).toString();
					listeString2.add(s2);
					listeString3.add(s3);
					s4 =" ";
					listeString4.add(s4);
					
				}
				else{
					s2 = " ";
					s3 = " ";
					listeString2.add(s2);
					listeString3.add(s3);
					s4 = res.getObject(4).toString();
					listeString4.add(s4);
					
					
				}	
				
				
			}
			informationsClient.add(listeString1);
			informationsClient.add(listeString2);
			informationsClient.add(listeString3);
			informationsClient.add(listeString4);
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			System.out.println("Tentative de sauvegarde");
			//SGBD.executeUpdate("COMMIT");
			SGBD.fermer();
		}
		
		return informationsClient;
	}

	public static int compterNbrePromoExceptionnellesArticle(String idArticle,int estFidele){
		connecter();
		Statement st = null ;
		ResultSet res= null;
		int nbrePromo=0;
		String requete="";
		
		try{
			st=c.createStatement();
			
			if(estFidele==0){
				// si le client n'est pas adh�rent au magasin, il ne peut b�n�ficier que des promotions
				// sur les promotions concernant l'ensemble des clients
				requete="select count(*) from promo,LISTING_PROMOS_ARTICLES " +
						"where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO AND PROMOFIDELITE=0 " +
						"and DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE " +
						"AND LISTING_PROMOS_ARTICLES.IDARTICLE='"+idArticle+"'";
			}
			else{
				// si le client est adh�rent au magasin, il peut b�n�ficier de l'ensemble
				// des promotions exceptionelles sur l'article
				requete="select count(*) from promo,LISTING_PROMOS_ARTICLES " +
						"where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO and " +
						"DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE " +
						"AND LISTING_PROMOS_ARTICLES.IDARTICLE='"+idArticle+"'";
			}
			
			res=st.executeQuery(requete);
			
			String compteurPromotion="0";
			
			while(res.next()){
				if(res.getObject(1) != null){
					compteurPromotion=res.getObject(1).toString();
				}
			}
			nbrePromo=Integer.parseInt(compteurPromotion);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		catch(NumberFormatException exc){
			System.out.println(exc.getMessage());
		}
		
		
		return nbrePromo;
	}
	
	public static String recupererPourcentagePromotionExceptionnelleArticle(String idArticle,int estFidele){
		connecter();
		Statement st = null ;
		ResultSet res= null;
		String requete="";
		String pourcentage="0";
		
		try{
			st=c.createStatement();
			
			if(estFidele==0){
				// si le client n'est pas adh�rent au magasin, il ne peut b�n�ficier que des promotions
				// sur les promotions concernant l'ensemble des clients
				requete="select max(pourcentagepromo) from promo,LISTING_PROMOS_ARTICLES " +
						"where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO AND PROMOFIDELITE=0 " +
						"and DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE " +
						"AND LISTING_PROMOS_ARTICLES.IDARTICLE='"+idArticle+"'";
			}
			else{
				// si le client est adh�rent au magasin, il peut b�n�ficier de l'ensemble
				// des promotions exceptionelles sur l'article
				requete="select max(pourcentagepromo) from promo,LISTING_PROMOS_ARTICLES " +
						"where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO and " +
						"DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE " +
						"AND LISTING_PROMOS_ARTICLES.IDARTICLE='"+idArticle+"'";
			}
			
			res=st.executeQuery(requete);
			
			
			while(res.next()){
				if(res.getObject(1) != null){
					pourcentage=res.getObject(1).toString();
				}
			}	
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return pourcentage ;
	}
	
	public static String recupererPourcentagePromotionDegressifArticleCommande(String identifiantCommande, String idArticle){
		connecter();
		Statement st = null ;
		ResultSet res= null;
		String requete="";
		// on met le pourcentage de promo degressif si la requete ne retourne pas de resultat 
		//cad si la quantite commandee de l'article est inf�rieure au seuil minimal n�cessaire
		// pour b�n�ficier d'une promotion degressive sur l'article
		String pourcentage="0";
		
		try{
			st=c.createStatement();
			
			requete="select max(pourcentage) from listing_articles_commandes,article,reduction,quantite"+
				" where listing_articles_commandes.idarticle=article.idarticle"+
				" and reduction.idcategorie=article.idcategorie"+
				" and quantite.idquantite=reduction.idquantite"+
				" and (listing_articles_commandes.quantitecommandee-quantite.quantite)>0"+
				" and article.idarticle='"+idArticle+"'"+
				" and idCommande='"+identifiantCommande+"'";
			
			System.out.println(requete);
			res=st.executeQuery(requete);
			
			
			while(res.next()){
				if(res.getObject(1) != null){
					pourcentage=res.getObject(1).toString();
				}
			}	
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return pourcentage ;
	}
	
}
