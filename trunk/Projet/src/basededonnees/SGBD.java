package basededonnees;

import ihm.Gerant.FenetreFormulairePromotionsGerant;
import ihm.modeleTableau.ModeleTableauCatalogue;
import ihm.modeleTableau.ModeleTableauClient;
import ihm.modeleTableau.ModeleTableauCommande;

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

/**
 * Cette classe recense l'ensemble des méthodes qui font appel à la base de
 * données.
 * 
 * <p>
 * Outre la connexion et la déconnexion à la base de données, l'ensemble des
 * méthodes liées à la mise à jour ou l'interrogation de la base sont présentes
 * dans cette classe.
 * </p>
 * 
 */
public class SGBD {

	/**
	 * Permet de se connecter à la base.
	 */
	static private Connection c;

	/**
	 * Adresse de la base de données.
	 */
	final static String URL = "jdbc:oracle:thin:@oraens10g:1521:ORAENS";
	// URL à utiliser lorsque l'on est pas à l'Ensai :
	// final static String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";

	/**
	 * L'identifiant utilisé pour la connexion.
	 */

	private static final String ID = "id3198";


	/**
	 * Le mot de passe utilisé pour la connnexion.
	 */

	private static final String MDP = "id3198";


	/**
	 * Permet à l'utilisateur de se connecter à sa base de données.
	 * 
	 * <p>
	 * L'adresse URL de la base de données, l'identifiant et le mot de passe
	 * permettant de s'y connecter auront été définis au préalable.
	 * 
	 * Une Exception de type SQL est levée si la connexion échoue.
	 * </p>
	 * 
	 * @return Si la connexion s'est bien déroulée
	 */
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

	/**
	 * Permet à l'utilisateur de se déconnecter à sa base de données.
	 * 
	 * <p>
	 * Avant la déconnexion, la méthode sauvegarde les éventuels changements
	 * opérés sur le base de données. Une Exception de type SQL est levée si la
	 * connexion échoue.
	 * </p>
	 * 
	 * @return Si la déconnexion s'est bien déroulée
	 */
	public static boolean fermer() {
		boolean result = true;
		Statement st;
		try {

			st = c.createStatement();
			st.executeUpdate("COMMIT");

			c.close();
			System.out.println("Déconnexion à la base");
		} catch (SQLException e) {

			System.out.println("Echec de la tentative de connexion : "
					+ e.getMessage());
			result = false;
		}
		return result;
	}

	/**
	 * Effectue une mise à jour de la base de données.
	 * <p>
	 * La méthode commence par se connecter à la base avant d'exécuter la
	 * requête SQL de mise à jour qui a été saisie en paramètre.
	 * 
	 * Une Exception de type SQL est levée si la mise à jour échoue.
	 * 
	 * Dans tous les cas, la méthode ferme la connexion après la tentative de
	 * mise à jour.
	 * </p>
	 * 
	 * @param requete
	 *            La requête SQL à exécuter
	 */
	public static void executeUpdate(String requete) {
		connecter();
		Statement st;
		try {
			st = c.createStatement();

			st.executeUpdate(requete);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			fermer();

		}
	}

	/**
	 * Interroge la base de données.
	 * <p>
	 * La méthode commence par se connecter à la base avant d'exécuter la
	 * requête SQL de mise à jour qui a été saisie en paramètre.
	 * 
	 * Une Exception de type SQL est levée si l'interrogation échoue.
	 * 
	 * Dans tous les cas, la méthode ferme la connexion après la tentative
	 * d'interrogation.
	 * </p>
	 * 
	 * @param requete
	 *            La requête SQL à exécuter
	 * 
	 * @return L'objet ResultSet issu de la requête SQL exécutée
	 */
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

	/**
	 * Transforme un objet Date en une chaine de caractères.
	 * <p>
	 * Plus précisement, la méthode renvoie une chaine de caractères
	 * correspondant à la date précisée en paramètre directement utilisable dans
	 * une requête SQL.
	 * </p>
	 * 
	 * @param date
	 *            L'objet Date que l'on cherche à transformer
	 * @return Une chaîne de caractères pouvant être utilisée dans une requête
	 *         SQL
	 * 
	 * @see metier.Promotion#modifierPromoBDD(String, String, Date, Date,
	 *      String, boolean)
	 */
	public static String transformation(Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		String s;
		s = "TO_DATE('" + sqlDate + "', 'YYYY-MM-DD')";
		return s;
	}

	/**
	 * Transforme une chaîne de caractères en un objet Date.
	 * <p>
	 * Plus précisement, la méthode utilise le format spécifié en paramètre pour
	 * instancier un nouvel objet Date à partir de la chaîne de caractères.
	 * </p>
	 * 
	 * @param sDate
	 *            Chaîne de caractères qui exprime une date (exemple :
	 *            "25/12/2010")
	 * @param sFormat
	 *            Format de la chaîne de caractères. (exemple pour la date ci
	 *            dessus "dd/MM/yyyy". <br>
	 *            Ce format répond aux conventions d'écriture de la classe
	 *            SimpleDateFormat, utilisée dans cette méthode
	 * @return Un objet Date correspondant à la chaine de caractères saisie
	 * 
	 * @see ihm.Gerant.FenetreFormulairePromotionsGerant
	 */
	public static Date stringToDate(String sDate, String sFormat)
			throws Exception {

		java.sql.Date sDate2 = new java.sql.Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		sDate2 = new java.sql.Date(sdf.parse(sDate).getTime());
		return sDate2;
	}

	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments (de nature String)
	 * d'un champ issu d'une table tous deux précisés en paramètres.
	 * 
	 * @param table
	 *            Nom de la table sur laquelle s'applique la requête
	 * @param str
	 *            Champ de la table retourné par la requête dans un ArrayList
	 * 
	 * @return Un ArrayList du champ str
	 * 
	 * @see metier
	 * @see ihm.Accueil.FenetreDialogCreationCompte
	 * @see ihm.Gerant.FenetreFormulaireArticleGerant
	 * @see ihm.Client.FenetreDialogGestionCompteClient
	 * 
	 */
	public static ArrayList<String> selectListeString(String table, String str) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + str + " FROM " + table);

			while (res.next()) {

				String s = res.getObject(1).toString();

				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();

		}

		return listeString;
	}

	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments (de nature String)
	 * d'un champ issu d'une table tous deux précisés en paramètres. Ces
	 * éléments seront ordonnés selon la variable champOrdre de la table.
	 * 
	 * @param table
	 *            Nom de la table sur laquelle s'applique la requete
	 * @param str
	 *            Champ de la table retourné par la requête dans un ArrayList
	 * @param champOrdre
	 *            Champ de la table qui va déterminer l'ordre de la liste
	 *            retournée
	 * 
	 * @return Un ArrayList du champ str selon l'ordre déterminé par champOrdre
	 * 
	 * @see ihm.modeleTableau.ModelePromotion
	 */
	public static ArrayList<String> selectListeStringOrdonne(String table,
			String str, String champOrdre) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + str + " FROM " + table
					+ " ORDER BY " + champOrdre + " ASC");

			while (res.next()) {

				String s = res.getObject(1).toString();

				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();

		}

		return listeString;
	}

	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments ordonnés d'un champ
	 * de type date issu d'une table tous deux précisés en paramètres. Le format
	 * que l'on désire obtenir est aussi précisé en paramètre. <br>
	 * L'ordre des dates ainsi obtenues est déterminé par champOrdre.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ de date
	 * @param str
	 *            Nom du champ de date dans la table
	 * @param format
	 *            Format dans lequel on veut récupérer les dates dans la liste
	 * @param champOrdre
	 *            Champ qui va déterminer l'ordre de la liste
	 * 
	 * @return Un arraylist d'un champ de date ordonné
	 * 
	 * @see ihm.modeleTableau.ModelePromotion
	 */
	public static ArrayList<String> selectListeDatesOrdonne(String table,
			String str, String format, String champOrdre) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT TO_CHAR(" + str + ",'" + format
					+ "') FROM " + table + " ORDER BY " + champOrdre);

			while (res.next()) {

				String s = res.getObject(1).toString();
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();

		}

		return listeString;
	}

	/** TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO **/
	/** TODO : Méthode jamais appelée sauf pour les tests **/
	/** TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO **/
	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments d'un champ de type
	 * float issu d'une table tous deux précisés en paramètres.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ dont on veut obtenir la
	 *            liste
	 * @param var
	 *            Champ de la table de nature Float que l'on souhaite récupérer
	 * 
	 * @return L'ensemble des éléments (de nature Float) d'une variable issue
	 *         d'une table tous deux précisés en paramètres.
	 */
	public static ArrayList<Float> selectListeFloat(String table, String var) {
		connecter();
		ArrayList<Float> listeFloat = new ArrayList<Float>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + var + " FROM " + table);

			while (res.next()) {

				Float f = res.getFloat(1);
				listeFloat.add(f);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();

		}

		return listeFloat;
	}

	/** TODO TODO TODO TODO TODO TODO **/
	/** TODO : Méthode jamais appelée **/
	/** TODO TODO TODO TODO TODO TODO **/
	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments d'un champ de type
	 * Integer issu d'une table tous deux précisés en paramètres.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ dont on veut obtenir la
	 *            liste
	 * @param var
	 *            Champ de la table de nature Integer que l'on souhaite
	 *            récupérer
	 * @return L'ensemble des éléments (de nature Integer) d'une variable issue
	 *         d'une table tous deux précisés en paramètres
	 */
	public static ArrayList<Integer> selectListeInt(String table, String var) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + var + " FROM " + table);

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();

		}

		return listeInt;
	}

	/** TODO TODO TODO TODO TODO TODO **/
	/** TODO : Méthode jamais appelée **/
	/** TODO TODO TODO TODO TODO TODO **/
	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments d'un champ de type
	 * Integer issu d'une table tous deux précisés en paramètres. Ces éléments
	 * seront triés selon leur valeur d'une deuxième variable de la table.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ dont on veut obtenir la
	 *            liste
	 * @param var
	 *            Champ de la table de nature Integer que l'on souhaite
	 *            récupérer
	 * @param champOrdre
	 *            Variable de la table servant à ordonner les éléments obtenus
	 * 
	 * @return l'ensemble des éléments ordonnés (de nature Integer) d'une
	 *         variable issu d'une table tous deux précisés en paramètres
	 */
	public static ArrayList<Integer> selectListeIntOrdonne(String table,
			String var, String champOrdre) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + var + " FROM " + table
					+ " ORDER BY " + champOrdre + " ASC");

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();

		}

		return listeInt;
	}

	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments d'un champ de type
	 * Integer issu d'une table tous deux précisés en paramètres. Ces éléments
	 * seront triés selon leur valeur d'une deuxième variable de la table. <br>
	 * S'ajoute à cela une condition sur les élements sélectionnés.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ dont on veut obtenir la
	 *            liste
	 * @param var
	 *            Champ de la table de nature Integer que l'on souhaite
	 *            récupérer
	 * @param champOrdre
	 *            Variable de la table servant à ordonner les éléments obtenus
	 * @param cond
	 *            Condition WHERE qui va s'appliquer sur la table
	 * 
	 * @return L'ensemble des éléments (de nature Integer) d'une variable issus
	 *         d'une table avec une condition WHERE tous deux précisés en
	 *         paramètres
	 * 
	 * @see ihm.modeleTableau.ModeleTableauCatalogue
	 */
	public static ArrayList<Integer> selectListeIntOrdonneCondition(
			String table, String var, String champOrdre, String cond) {
		connecter();
		ArrayList<Integer> listeInt = new ArrayList<Integer>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + var + " FROM " + table
					+ " WHERE " + cond + " ORDER BY " + champOrdre + " ASC");

			while (res.next()) {

				Integer i = res.getInt(1);
				listeInt.add(i);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();
		}

		return listeInt;
	}

	/**
	 * Méthode permettant d'obtenir l'ensemble des éléments d'un champ de type
	 * String issu d'une table tous deux précisés en paramètres. Ces éléments
	 * seront triés selon les valeurs d'une deuxième variable de la table.
	 * S'ajoute à cela une condition sur les élements sélectionnés.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ dont on veut obtenir la
	 *            liste
	 * @param var
	 *            Champ de la table de nature String que l'on souhaite récupérer
	 * @param champOrdre
	 *            Variable de la table servant à ordonner les éléments obtenus
	 * @param cond
	 *            Condition WHERE qui va s'appliquer sur la table
	 * 
	 * @return L'ensemble des éléments (de nature String) d'une variable issus
	 *         d'une table avec une condition WHERE tous deux précisés en
	 *         paramètres
	 * 
	 * @see ihm.Gerant.FenetreFormulairePromotionsGerant
	 * @see ihm.Gerant.FicheClient
	 * @see ihm.modeleTableau.ModeleMessagerie
	 * @see ihm.modeleTableau.ModeleTableauCatalogue
	 * @see ihm.modeleTableau.ModeleTableauStatCommande
	 * @see metier.Commande#preparerPanier()
	 */
	public static ArrayList<String> selectListeStringOrdonneCondition(
			String table, String var, String champOrdre, String cond) {
		connecter();
		ArrayList<String> listeString = new ArrayList<String>();
		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT " + var + " FROM " + table
					+ " WHERE " + cond + " AND " + var
					+ " IS NOT NULL ORDER BY " + champOrdre + " ASC");

			while (res.next()) {

				String s = res.getObject(1).toString();
				;
				listeString.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();
		}

		return listeString;
	}

	/**
	 * Méthode permettant d'obtenir un élément d'un champ de type String issu
	 * d'une table tous deux précisés en paramètres. S'ajoute à cela une
	 * condition qui s'applique sur un autre champ.
	 * 
	 * @param table
	 *            Table dans laquelle se trouve le champ que l'on veut récupérer
	 * @param champ
	 *            Champ dans lequel on recupère l'élément
	 * @param champDeCondition
	 *            Champ sur lequel on met une condition dans la table
	 * @param condition
	 *            Condition sur le champ de la table : champDeCondition
	 * 
	 * @return Un élément d'un champ (de nature String) en apposant une
	 *         condition sur un autre champ (dont les éléments sont aussi de
	 *         nature String)
	 * 
	 * @see ihm.Accueil
	 * @see ihm.Client
	 * @see ihm.Gerant
	 */
	public static String selectStringConditionString(String table,
			String champ, String champDeCondition, String condition) {
		connecter();
		String s = null;
		Statement st = null;
		ResultSet res = null;

		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT " + champ + " FROM " + table
					+ " WHERE " + champDeCondition + " = '" + condition + "'");

			while (res.next()) {

				// Si le résultat est non nul tout se passe normalement
				if (res.getObject(1) != null) {

					s = res.getObject(1).toString();

				}
				// Sinon, on affecte un espace au String renvoyé (cf. Classe
				// FenetreDialogGestionCompteClient)
				// (lorsque l'on y chercher à vérifier si un client possède une
				// dénomination pour savoir si c'est un particulier)
				else {
					s = " ";
				}
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();
		}
		// Si la requête ne renvoie rien, on "remplit" s par "rien

		return s;
	}

	/**
	 * Méthode permettant d'obtenir un élément d'un champ de type Date issu
	 * d'une table tous deux précisés en paramètres. S'ajoute à cela une
	 * condition qui s'applique sur un autre champ, qui lui est de type String0
	 * 
	 * @param table
	 *            Nom de la table dans laquelle se situe l'élément à récupérer
	 * @param champDate
	 *            Nom du champ date à récupérer
	 * @param champDeCondition
	 *            Nom du champ sur lequel porte la condition
	 * @param condition
	 *            Condition exprimée sur champDeCondition
	 * @param format
	 *            Format sous lequel on retourne la date en String
	 * 
	 * @return Un élément d'un champ date sous forme de String en apposant une
	 *         condition sur un autre champ (dont les éléments sont aussi de
	 *         nature String)
	 * 
	 * @see ihm.Gerant.FenetreFormulairePromotionsGerant
	 * @see ihm.Client.FenetrePromotions
	 */
	public static String selectDateConditionString(String table,
			String champDate, String champDeCondition, String condition,
			String format) {
		connecter();
		String s = null;
		Statement st = null;
		ResultSet res = null;

		try {

			st = c.createStatement();

			res = st.executeQuery("SELECT TO_CHAR(" + champDate + ",'" + format
					+ "')" + " FROM " + table + " WHERE " + champDeCondition
					+ " = '" + condition + "'");

			while (res.next()) {

				// Si le résultat est non nul tout se passe normalement
				if (res.getObject(1) != null) {
					s = res.getObject(1).toString();
				}
				// Sinon, on affecte un espace au String renvoyé (cf. Classe
				// FenetreDialogGestionCompteClient)
				// (lorsque l'on y chercher à vérifier si un client possède une
				// dénomination pour savoir si c'est un particulier)
				else {
					s = " ";
				}
			}
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();
		}
		// Si la requête ne renvoie rien, on "remplit" s par "rien"

		return s;
	}

	/**
	 * Méthode permettant de récupérer l'ensemble des éléments de deux champs
	 * (de nature String) d'une table donnée (tous entrés en paramètres).
	 * 
	 * @param table
	 *            Table dans laquelle se situe les deux champs
	 * @param champ1
	 *            Premier champ dont on veut récupérer les données
	 * @param champ2
	 *            Deuxième champ dont on veut récupérer les données
	 * 
	 * @return Un ArrayList de String[] comprenant les données
	 * 
	 * @see ihm.Accueil.FenetreDialogIdentification
	 */
	public static ArrayList<String[]> selectDeuxChampsString(String table,
			String champ1, String champ2) {
		connecter();
		ArrayList<String[]> liste = new ArrayList<String[]>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT " + champ1 + "," + champ2 + " FROM "
					+ table);

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
			System.out.println("Tentative de sauvegarde");
			fermer();
		}
		return liste;
	}

	/**
	 * Récupère la valeur d'une statistiques sur le montant des commandes d'un
	 * client pour la fiche client parmi la moyenne, le minimum et le maximum.
	 * 
	 * 
	 * @param identifiant
	 *            Identifiant du client
	 * 
	 * @param statistique
	 *            Statistique sur le montant des commandes ("avg" pour la
	 *            moyenne,"max" pour le maximum et "min" pour le minimum)
	 * 
	 * @return La valeur de la statistique sur les commandes du client
	 * 
	 * @see ihm.Gerant.FicheClient
	 */
	public static String statistiqueClassiqueClient(String identifiant,
			String statistique) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		String rs = "0";

		try {
			st = c.createStatement();

			res = st.executeQuery("select " + statistique
					+ "(MONTANTCOMMANDE) from commande WHERE" + " IDCLIENT='"
					+ identifiant + "'");

			while (res.next()) {
				if (res.getString(1) != null) {
					rs = res.getString(1).toString();
				}
			}
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			fermer();
		}
		return rs;
	}

	/**
	 * Retourne le nombre de commandes effectuées par un client.
	 * 
	 * @param identifiant
	 *            Identifiant unique du client
	 * 
	 * @return Le nombre de commandes effectuées par le client
	 * 
	 * @see ihm.Gerant.FicheClient
	 */
	public static String nbreCommandeClient(String identifiant) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		String rs = "0";

		try {
			st = c.createStatement();

			res = st.executeQuery("select count(*) from commande where"
					+ " IDCLIENT='" + identifiant + "'");

			while (res.next()) {
				if (res.getString(1) != null) {
					rs = res.getString(1).toString();
				}
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			fermer();
		}
		return rs;
	}

	/**
	 * Retourne une liste contenant l'identifiant et la date de la plus grosse
	 * commande effectuée par un client au sens du montant de la commande.
	 * 
	 * @param identifiant
	 *            Identifiant unique du client (String)
	 * 
	 * @return Un ArrayList de String contenant l'identifiant et la date de la
	 *         plus grosse commande du client entrée en paramètre
	 * 
	 * @see ihm.Gerant.FicheClient
	 */
	public static ArrayList<String> statistiquePlusGrosseCommande(
			String identifiant) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		ArrayList<String> listeStat = new ArrayList<String>();
		String id = "Aucune";
		String date = "NA";
		// Si le client n'a effectué aucune commande jusqu'à aujourd'hui, alors
		// on retourne
		// "Aucune" pour l'identifiant et NA pour signifier qu'aucune date n'a
		// été trouvé

		try {
			st = c.createStatement();
			res = st.executeQuery("select IDCOMMANDE,DATECOMMANDE from commande "
					+ " WHERE MONTANTCOMMANDE=(SELECT max(MONTANTCOMMANDE) from commande)"
					+ " and IDCLIENT='" + identifiant + "'");

			while (res.next()) {
				if (res.getString(1) != null) {
					id = res.getString(1).toString();
				}
				if (res.getString(2) != null) {
					date = res.getString(2).toString();
				}
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			fermer();
			listeStat.add(id);
			listeStat.add(date);
		}
		return listeStat;
	}

	/** TODO TODO TODO TODO TODO TODO TODO **/
	/** TODO : Méthode à utiliser ou pas ? **/
	/** TODO TODO TODO TODO TODO TODO TODO **/
	// // Méthode qui récuperera les attributs d'un client à partir de son
	// identifiant
	// public static ArrayList<String> recupererAttributClient(String
	// mailIdentifiant){
	// connecter();
	// ArrayList<String> client = new ArrayList<String>();
	//
	// Statement st = null ;
	// ResultSet res= null;
	// try{
	// st=c.createStatement();
	// res=
	// st.executeQuery("SELECT IDCLIENT,NOMCLIENT,PRENOMCLIENT,DENOMINATIONCLIENT,"
	// +
	// "ADRESSECLIENT,CODEPOSTAL,NOMVILLE,TELEPHONE," +
	// "ETATCOMPTE FROM CLIENT VILLE" +
	// "WHERE IDCLIENT='"+ mailIdentifiant+"'" +
	// "AND CLIENT.IDVILLE = VILLE.IDVILLE;");
	//
	// ResultSetMetaData rsmd = res.getMetaData();
	//
	// for(int i=1;i==rsmd.getColumnCount();i++){
	// client.add(res.getString(i));
	// }
	//
	// }
	// catch(SQLException e){
	// System.out.println("Echec de la tentative d’interrogation : "
	// + e.getMessage());
	// }
	// finally{
	// System.out.println("Tentative de sauvegarde");
	// SGBD.executeUpdate("COMMIT");
	// fermer();
	// }
	//
	// return client;
	// }
	//

	/**
	 * Retourne la liste des articles dont le stock est inférieur à la quantité
	 * seuil ou en rupture de stock.
	 * 
	 * @return <p>
	 *         La liste des articles qui ont besoin d'être réapprovisionnés du
	 *         fait de leur quantité en stock insuffisante contenant :
	 *         <ul>
	 *         <li>un ArrayList avec les identifiants des articles.</li>
	 *         <li>un ArrayList avec la description de chaque article.</li>
	 *         <li>un ArrayList avec le stock présent dans la base de données.</li>
	 *         <li>un ArrayList avec le prix initial.</li>
	 *         </ul>
	 *         </p>
	 * 
	 * @see ModeleTableauCatalogue#ModeleTableauCatalogue(boolean, boolean)
	 */
	public static ArrayList<ArrayList<String>> selectArticlesReapprovisionnement() {

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

			// La requete va selectionner les articles qui sont rupture de stock
			// et dont la
			// quantité actuelle en stock est inférieure à celle de la catégorie
			// d'article
			// à laquelle ils appartiennent
			String requete = "select idarticle,description,stock,prixinitial "
					+ " from ARTICLE where (etatarticle !='Supprimé' and (stock=0 or idarticle in ( select idarticle from article, "
					+ " categorie where article.idcategorie = categorie.idcategorie and "
					+ " stock< categorie.QUANTITELIMITE)))";

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
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			System.out.println("Tentative de sauvegarde");
			fermer();
		}
		return article;
	}

	/** TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO **/
	/** TODO : Méthode jamais appelée sauf pour les tests **/
	/** TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO **/
	// TODO cette méthode doit permettre de récupérer l'ensemble des
	// indormations nécessaires
	// à la commande n° idCommande passée en paramètre
	// il faudrait calculer le total par article en tenant compte % promo selon
	// quantité
	public static ArrayList<Object[]> informationCommande(String idCommande) {
		SGBD.connecter();
		ArrayList<Object[]> commande = new ArrayList<Object[]>();

		Statement st = null;
		ResultSet res = null;
		try {

			st = c.createStatement();
			res = st.executeQuery("SELECT ARTICLE.IDARTICLE, DESCRIPTION, PRIXINITIAL,QUANTITECOMMANDEE"
					+ " FROM ARTICLE, LISTING_ARTICLES_COMMANDES"
					+ " WHERE ARTICLE.IDARTICLE=LISTING_ARTICLES_COMMANDES.IDARTICLE AND"
					+ " LISTING_ARTICLES_COMMANDES.IDCOMMANDE ='"
					+ idCommande
					+ "'");

			while (res.next()) {

				String[] listeString = new String[4];
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				String s3 = res.getObject(3).toString();
				String s4 = res.getObject(4).toString();
				listeString[0] = s;
				listeString[1] = s2;
				listeString[2] = s3;
				listeString[3] = s4;
				commande.add(listeString);
			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.fermer();
		}
		return commande;
	}

	/**
	 * Indique si un client possède une carte de fidélité et le nombre de points
	 * qu'il possède le cas échéant
	 * 
	 * @param identifiant
	 *            Identifant du client
	 * 
	 * @return Un arraylist indiquant l'adhésion ou non au programme fidélité du
	 *         client ("Oui"/"Non") et son nombre éventuel de points
	 * 
	 * @see ihm.Client.FenetreCommandeArticle
	 * @see ihm.Client.FenetreFideliteClient
	 * @see ihm.Gerant.FicheClient
	 * @see ihm.modeleTableau.ModeleTableauCommande
	 * @see metier.Commande
	 */
	public static ArrayList<String> recupererInformationFideliteClient(
			String identifiant) {
		SGBD.connecter();
		Statement st = null;
		ResultSet res = null;
		String fidele = "Non";
		String nbpoints = "0";
		ArrayList<String> resultat = new ArrayList<String>();

		try {
			st = c.createStatement();

			// ON VA TESTER POUR LE CLIENT SI SON IDCLIENT EST DANS LA TABLE DE
			// CEUX
			// QUI ONT UNE CARTE DE FIDELITE

			res = st.executeQuery("SELECT NBPOINTS FROM CARTE_FIDELITE,CLIENT"
					+ " WHERE CARTE_FIDELITE.IDCLIENT=CLIENT.IDCLIENT AND CARTE_FIDELITE.IDCLIENT='"
					+ identifiant + "'");

			while (res.next()) {

				// Si le résultat est non nul tout se passe normalement
				if (res.getObject(1) != null) {

					fidele = "Oui";
					nbpoints = res.getObject(1).toString();

				}
				// Sinon, on affecte un espace au String renvoyé (cf. Classe
				// FenetreDialogGestionCompteClient)
				// (lorsque l'on y chercher à vérifier si un client possède une
				// dénomination pour savoir si c'est un particulier)

			}
			resultat.add(fidele);
			resultat.add(nbpoints);

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {

			System.out.println("Sauvegarde");
			SGBD.fermer();
		}

		return resultat;
	}

	/**
	 * Cette méthode permet de selectionner tous les individus satifaisant l'un
	 * des critères remplis dans la fenetre de recherche d'un client chez le
	 * gérant.
	 * 
	 * @param idClient
	 *            Une chaine de caractère saisie dans le champ identifiant
	 *            client
	 * @param nomClient
	 *            Une chaine de caractère saisie dans le champ nom du client
	 *            pour les particuliers
	 * @param denomination
	 *            Une chaine de caractère saisie dans le champ denomination du
	 *            client pour les associations
	 * @param ville
	 *            Une chaine de caractère saisie dans le champ ville
	 * 
	 * @see ModeleTableauClient#ModeleTableauClient(String, String, String,
	 *      String)
	 * 
	 * @return <p>
	 *         Un ArrayList contenant :
	 *         <ul>
	 *         <li>un ArrayList de tous les identifiants correspondants à la
	 *         recherche.</li>
	 *         <li>un ArrayList de tous les noms de clients correspondants à la
	 *         recherche</li>
	 *         <li>un ArrayList de tous les prenoms de clients correspondants à
	 *         la recherche</li>
	 *         <li>un ArrayList de toutes les dénominatiosn client
	 *         correspondantes à la recherche</li>
	 *         </ul>
	 *         <p>
	 * 
	 * @see ihm.modeleTableau.ModeleTableauClient
	 */
	public static ArrayList<ArrayList<String>> recupererInformationRechercheClient(
			String idClient, String nomClient, String denomination, String ville) {
		SGBD.connecter();
		Statement st = null;
		ResultSet res = null;
		ArrayList<String> listeString1 = new ArrayList<String>();
		ArrayList<String> listeString2 = new ArrayList<String>();
		ArrayList<String> listeString3 = new ArrayList<String>();
		ArrayList<String> listeString4 = new ArrayList<String>();
		ArrayList<ArrayList<String>> informationsClient = new ArrayList<ArrayList<String>>();

		try {
			st = c.createStatement();
			String conditionWhereOr = "WHERE (";
			boolean ajouterChamp = false;

			if (!idClient.equals("")) {
				conditionWhereOr = conditionWhereOr + "IDCLIENT Like '%"
						+ idClient + "%'";
				ajouterChamp = true;
			}

			if (!denomination.equals("")) {
				if (ajouterChamp == true) {
					conditionWhereOr = conditionWhereOr + " or ";
					ajouterChamp = false;
				}
				conditionWhereOr = conditionWhereOr
						+ "DENOMINATIONCLIENT Like '%" + denomination + "%'";
				ajouterChamp = true;
			}

			if (!nomClient.equals("")) {
				if (ajouterChamp == true) {
					conditionWhereOr = conditionWhereOr + " or ";
					ajouterChamp = false;
				}
				conditionWhereOr = conditionWhereOr + "NOMCLIENT Like '%"
						+ nomClient + "%'";
				ajouterChamp = true;
			}

			if (!ville.equals("")) {
				if (ajouterChamp == true) {
					conditionWhereOr = conditionWhereOr + " or ";
					ajouterChamp = false;
				}
				conditionWhereOr = conditionWhereOr
						+ "LOWER(VILLE.NOMVILLE) Like '%" + ville.toLowerCase()
						+ "%'";
				ajouterChamp = true;
			}

			// si un utilisateur ne remplit aucun champ et appuie sur le bouton
			// il pourra voir la liste des clients
			if (conditionWhereOr.equals("WHERE (")) {
				conditionWhereOr = "WHERE VILLE.IDVILLE = CLIENT.IDVILLE";
				System.out.println("condition 5");
			} else {
				conditionWhereOr = conditionWhereOr
						+ " ) AND (VILLE.IDVILLE = CLIENT.IDVILLE) ";
			}

			res = st.executeQuery("SELECT IDCLIENT , NOMCLIENT, PRENOMCLIENT,DENOMINATIONCLIENT"
					+ " FROM CLIENT, VILLE " + conditionWhereOr);

			System.out
					.println("SELECT IDCLIENT , NOMCLIENT, PRENOMCLIENT,DENOMINATIONCLIENT"
							+ " FROM CLIENT, VILLE " + conditionWhereOr);

			while (res.next()) {

				String s, s2, s3, s4;
				s = res.getObject(1).toString();

				listeString1.add(s);
				if (res.getObject(2) != null) {

					s2 = res.getObject(2).toString();
					s3 = res.getObject(3).toString();
					listeString2.add(s2);
					listeString3.add(s3);
					s4 = " ";
					listeString4.add(s4);

				} else {
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
		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());
		} finally {
			System.out.println("Tentative de sauvegarde");
			SGBD.fermer();
		}

		return informationsClient;
	}

	/** TODO TODO TODO TODO TODO TODO **/
	/** TODO : Méthode jamais appelée **/
	/** TODO TODO TODO TODO TODO TODO **/
	/**
	 * Retourne le nombre de promotions exceptionnelles valables à l'instant
	 * présent dans la base de données. Elle est utilisée uniquement pour le
	 * calcul du montant d'une commande.
	 * 
	 * @see metier.Commande#montantCommandePourUnArticle(String,
	 *      metier.LigneCommande, int)
	 * 
	 * @param idArticle
	 *            L'identifiant unique de l'article
	 * @param estFidele
	 *            Indique si le client possède une carte de fidélité (1) ou non
	 *            (0)
	 * 
	 * @return Le nombre de promotions exceptionelles au jour d'aujourd'hui
	 * 
	 */
	public static int compterNbrePromoExceptionnellesArticle(String idArticle,
			int estFidele) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		int nbrePromo = 0;
		String requete = "";

		try {
			st = c.createStatement();

			if (estFidele == 0) {
				// si le client n'est pas adhérent au magasin, il ne peut
				// bénéficier que des promotions
				// sur les promotions concernant l'ensemble des clients
				requete = "select count(*) from promo,LISTING_PROMOS_ARTICLES "
						+ "where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO AND PROMOFIDELITE=0 "
						+ "and DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE "
						+ "AND LISTING_PROMOS_ARTICLES.IDARTICLE='" + idArticle
						+ "'";
			} else {
				// si le client est adhérent au magasin, il peut bénéficier de
				// l'ensemble
				// des promotions exceptionelles sur l'article
				requete = "select count(*) from promo,LISTING_PROMOS_ARTICLES "
						+ "where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO and "
						+ "DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE "
						+ "AND LISTING_PROMOS_ARTICLES.IDARTICLE='" + idArticle
						+ "'";
			}

			res = st.executeQuery(requete);

			String compteurPromotion = "0";

			while (res.next()) {
				if (res.getObject(1) != null) {
					compteurPromotion = res.getObject(1).toString();
				}
			}
			nbrePromo = Integer.parseInt(compteurPromotion);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException exc) {
			System.out.println(exc.getMessage());
		}

		return nbrePromo;
	}

	/**
	 * Retourne le pourcentage de promotion exceptionnelle existant sur un
	 * article entré en paramètre au moment où est exécuté la méthode. Ce
	 * pourcentage de promotion exceptionelle dépend aussi du fait que le client
	 * soit adhérent ou non.
	 * 
	 * @see metier.Commande#montantCommandePourUnArticle(String,
	 *      metier.LigneCommande, int)
	 * @see SGBD#compterNbrePromoExceptionnellesArticle(String, int)
	 * 
	 * @param idArticle
	 *            L'identifiant unique de l'article
	 * @param estFidele
	 *            Indique si le client possède une carte de fidélité (1) ou non
	 *            (0)
	 * 
	 * @return Le pourcentage actuel dans les promotions exceptionnelles
	 *         actuelles sur l'article donné ( dans un String)
	 * 
	 * @see metier.Commande
	 */
	public static String recupererPourcentagePromotionExceptionnelleArticle(
			String idArticle, int estFidele) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		String requete = "";
		String pourcentage = "0";

		try {
			st = c.createStatement();

			if (estFidele == 0) {
				// si le client n'est pas adhérent au magasin, il ne peut
				// bénéficier que des promotions
				// sur les promotions concernant l'ensemble des clients
				requete = "select max(pourcentagepromo) from promo,LISTING_PROMOS_ARTICLES "
						+ "where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO AND PROMOFIDELITE=0 "
						+ "and DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE "
						+ "AND LISTING_PROMOS_ARTICLES.IDARTICLE='"
						+ idArticle
						+ "'";
			} else {
				// si le client est adhérent au magasin, il peut bénéficier de
				// l'ensemble
				// des promotions exceptionelles sur l'article
				requete = "select max(pourcentagepromo) from promo,LISTING_PROMOS_ARTICLES "
						+ "where PROMO.IDPROMO= LISTING_PROMOS_ARTICLES.IDPROMO and "
						+ "DATEDEBUT<SYSDATE AND DATEFIN>SYSDATE "
						+ "AND LISTING_PROMOS_ARTICLES.IDARTICLE='"
						+ idArticle
						+ "'";
				System.out.println(requete);
			}

			res = st.executeQuery(requete);

			while (res.next()) {
				if (res.getObject(1) != null) {
					pourcentage = res.getObject(1).toString();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return pourcentage;
	}

	/**
	 * Cette méthode recherche le pourcentage de promotion dégressif d'un
	 * article d'une commande c'est à dire associé à la quantité commandée.
	 * <p>
	 * Pour informations :
	 * <ul>
	 * <li>Plus la quantité commandée sera importante, plus le pourcentage de
	 * promotion (degressif) sera important.</li>
	 * <li>Ce pourcentage est fonction de la catégorie de prix à laquelle est
	 * rattachée l'article.</li>
	 * <li>Quand la quantité commandée est inférieure au seuil minimal
	 * nécessaire pour bénéficier d'une promotion degressive sur l'article, la
	 * méthode retourne 0.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param identifiantCommande
	 *            idenifiant de la commande à laquelle appartient l'article
	 * @param idArticle
	 *            identifiant unique de l'article
	 * 
	 * @see metier.Commande
	 * @see metier.Commande#montantCommandePourUnArticle(String,LigneCommande,int)
	 * 
	 */
	public static String recupererPourcentagePromotionDegressifArticleCommande(
			String identifiantCommande, String idArticle) {
		connecter();
		Statement st = null;
		ResultSet res = null;
		String requete = "";
		// on met le pourcentage de promo degressif à zéro si la requete ne
		// retourne pas de resultat
		// cad si la quantite commandee de l'article est inférieure au seuil
		// minimal nécessaire
		// pour bénéficier d'une promotion degressive sur l'article
		String pourcentage = "0";

		try {
			st = c.createStatement();

			requete = "select max(pourcentage) from listing_articles_commandes,article,reduction,quantite"
					+ " where listing_articles_commandes.idarticle=article.idarticle"
					+ " and reduction.idcategorie=article.idcategorie"
					+ " and quantite.idquantite=reduction.idquantite"
					+ " and (listing_articles_commandes.quantitecommandee-quantite.quantite)>0"
					+ " and article.idarticle='"
					+ idArticle
					+ "'"
					+ " and idCommande='" + identifiantCommande + "'";

			res = st.executeQuery(requete);

			while (res.next()) {
				if (res.getObject(1) != null) {
					pourcentage = res.getObject(1).toString();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return pourcentage;
	}

	/**
	 * La méthode vérifie si le code postal saisi dans les formulaires existe
	 * dans la base de données des villes.
	 * 
	 * @param cp
	 *            Code postal saisi par l'utilisateur
	 * @return Si le code postal existe ou non
	 * 
	 * @see ihm.Accueil.FenetreDialogCreationCompte
	 * @see ihm.Client.FenetreDialogGestionCompteClient
	 * @see ihm.Gerant.FicheClient
	 */
	public static boolean verifierCodePostalExisteDansBase(String cp) {
		boolean existe = false;

		ArrayList<String> listeCodePostal = selectListeString("VILLE",
				"CODEPOSTAL");

		for (String codePostal : listeCodePostal) {

			if (codePostal.equals(cp)) {
				existe = true;
			}
		}

		return existe;
	}

}
