package basededonnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Cette classe regroupe l'ensemble des méthodes appelées en rapport avec la table CLIENTS
public class BDDClients {

	static private Connection c;

	public static ArrayList<String[]> afficheSelectMailsEtatsClients() {
		SGBD.connecter();
		ArrayList<String[]> mailsEtatsClients = new ArrayList<String[]>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT MAIL,ETATCOMPTE FROM CLIENTS");

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				String[] client = new String[2];
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				client[0] = s;
				client[1] = s2;
				mailsEtatsClients.add(client);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			SGBD.fermer();

		}

		return mailsEtatsClients;
	}

	public static ArrayList<String> afficheSelectMailsClients() {
		SGBD.connecter();
		ArrayList<String> mailsClients = new ArrayList<String>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT MAIL FROM CLIENTS");

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				String s = res.getObject(1).toString();
				mailsClients.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			SGBD.fermer();

		}

		return mailsClients;
	}

	public static ArrayList<String[]> afficheSelectMailsMdpsClients() {
		SGBD.connecter();
		ArrayList<String[]> mailsMdpsClients = new ArrayList<String[]>();

		Statement st = null;
		ResultSet res = null;
		try {
			st = c.createStatement();

			res = st.executeQuery("SELECT MAIL,MOTDEPASSE FROM CLIENTS");

			// Récupérer les méta données
			ResultSetMetaData rsmd = res.getMetaData();

			while (res.next()) {

				String[] client = new String[2];
				String s = res.getObject(1).toString();
				String s2 = res.getObject(2).toString();
				client[0] = s;
				client[1] = s2;
				System.out.println(s + "   " + s2);
				mailsMdpsClients.add(client);

			}

		} catch (SQLException e) {
			System.out.println("Echec de la tentative d’interrogation : "
					+ e.getMessage());

		} finally {
			SGBD.fermer();

		}

		return mailsMdpsClients;
	}
}
