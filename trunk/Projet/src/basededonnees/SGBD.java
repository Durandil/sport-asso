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

	// Test SVN Google Code � l'Ensai
	/**********************************************************************/
	/**Note : La plupart des m�thodes de cette classe sont issues du TP 2**/
	/**********************************************************************/
	static private Connection c;
	final static String URL = "jdbc:oracle:thin:@oraens10g:1521:ORAENS";
	//URL � utiliser lorsque l'on est pas � l'Ensai :
	//final static String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";
	
	//Penser � modifier les id/mdp


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
			
			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {
				
				String s = res.getObject(1).toString();
				
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return listeString;
	}
	
	//M�thode permettant d'obtenir l'ensemble des �l�ments 
	//d'un champ de type date issu d'une table tous deux pr�cis�s en param�tres
	//LE format que l'on d�sire obtenir est aussi pr�cis� en param�tre
	public static ArrayList<String> selectListeDates(String table, String str, String format) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {
		
			st = c.createStatement();
			
			res = st.executeQuery("SELECT TO_CHAR("+str+",'" + format + "') FROM "+table );

			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				String s = res.getObject(1).toString();
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
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

			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				Float f = res.getFloat(1);
				listeFloat.add(f);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
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

			// R�cup�rer les m�ta donn�es
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);
				

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return listeInt;
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
			
			// R�cup�rer les m�ta donn�es

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

			// R�cup�rer les m�ta donn�es
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
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
			fermer();

		}

		return liste;
	}
	
	// M�thode qui permettra de r�cup�rer les statistiques sur le montant des commandes d'un
	// client pour la fiche client. avg ( moyenne), min et max.
	// pour le moment je me sers de la quantite, plus tard il faudra travailler sur
	// le prix x quantit� ( requete imbriqu�e � r�aliser)
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
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
			fermer();
		}
		return rs;
	}
	
	// M�thode qui r�cuperera les attributs d'un client � partir de son identifiant
	public static ArrayList<String> recupererAttributClient(String mailIdentifiant){
		connecter();
		ArrayList<String> client = new ArrayList<String>();
		
		Statement st = null ;
		ResultSet res= null;
		try{
			st=c.createStatement();
			res= st.executeQuery("SELECT IDCLIENT,NOMCLIENT,PRENOMCLIENT,DENOMINATIONCLIENT," +
								"ADRESSECLIENT,CODEPOSTAL,NOMVILLE,TELEPHONE,FIDELITE," +
								"ETATCOMPTE FROM CLIENT,VILLE " +
								"WHERE CLIENT.CODECOMMUNE=VILLE.CODECOMMUNE and " +
								"IDCLIENT='"+ mailIdentifiant+"';");
			
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
			fermer();
		}
		
		return client;
	}
	
	// M�thode qui permet de selectionner des articles 
	// dont le stock est inf�rieur � la quantit� seuil ou en rupture de stock
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
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());

		} finally {
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
			res= st.executeQuery("SELECT IDARTICLE, DESCRIPTION, PRIXINITIAL,QUANTITECOMMANDEE" +
								 "FROM ARTICLE LISTING_ARTICLES_COMMANDES" +
								 "WHERE ARTICLE.IDARTICLE=LISTING_ARTICLES_COMMANDES.IDARTICLE and" +
								 "IDCOMMANDE='"+ idCommande +"';");
			
			ResultSetMetaData rsmd = res.getMetaData();
			
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
			
		}
		finally{
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
		
		ArrayList <String> resultat = new ArrayList<String>();
		String estFidele="";
		
		try{
			st=c.createStatement();
			
			// ON VA TESTER POUR LE CLIENT SI SON IDCLIENT EST DANS LA TABLE DE CEUX
			// QUI ONT UNE CARTE DE FIDELITE
			res=st.executeQuery("SELECT NBPOINTS FROM CLIENT,CARTE_FIDELITE" +
								"WHERE CLIENT.IDCLIENT=CARTE_FIDELITE.IDCLIENT AND CLIENT.IDCLIENT='"+identifiant+"' ;");
			
			boolean champVide = res.getBoolean(1);
			
			// si le client n'a pas de compte fidelit� (champVide=true) 
			//alors on ajoute juste le booleen au vecteur sinon on met le booleen et le nb de points 
			estFidele=champVide+"";
			resultat.add(estFidele);
			
			if(champVide==false){
				resultat.add(res.getObject(1).toString());
			}
			
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d�interrogation : "
					+ e.getMessage());
		}
		finally{
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
			SGBD.fermer();
		}
		return resultat;
	}
	
	
	
}
