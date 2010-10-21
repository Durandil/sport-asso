package metier;

import java.sql.Date;
import java.util.ArrayList;

import basededonnees.SGBD;

public class Commande {

	// Une liste de Commande se définit par un identifiant
	// une liste de lignes de commande
	// Une date, et le client qui en est à l'origine

	// Le Constructeur ajouter la Commande à la table COMMANDES
	public Commande(String idCommande, String idClient,
			ArrayList<LigneCommande> liste, String date) {
		this.idCommande = idCommande;
		this.idClient = idClient;
		this.liste = liste;
		this.date = date;
		ajouterBDD();
	}

	private String idClient;
	private String date;
	private String idCommande;
	private ArrayList<LigneCommande> liste;

	public void setIdCommande(String idCommande) {
		this.idCommande = idCommande;
	}

	public String getIdCommande() {
		return idCommande;
	}

	public void setListe(ArrayList<LigneCommande> liste) {
		this.liste = liste;
	}

	public ArrayList<LigneCommande> getListe() {
		return liste;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	// Méthode permettant d'ajouter cette commande à la table COMMANDES
	public void ajouterBDD() {

		String requete = "INSERT INTO COMMANDES (IDENTIFIANT, DATECOMMANDE, IDCLIENT) VALUES ( "
				+ "'"
				+ this.idCommande
				+ "',"
				+ "'"
				+ this.date
				+ "',"
				+ "'"
				+ idClient + "')";
		SGBD.executeUpdate(requete);
	}

	// Méthode qui crée la table COMMANDE+idCommande
	public void creerTable() {

		SGBD.executeUpdate("DROP TABLE " + idCommande);
		SGBD.executeUpdate("CREATE TABLE " + idCommande
				+ " (ARTICLE VARCHAR(40), " + " QUANTITE NUMBER(3))");
		String requete = null;
		for (int i = 0; i < liste.size(); i++) {
			requete = "'" + liste.get(i).getArticle() + "',"
					+ liste.get(i).getQuantite();
			SGBD.executeUpdate("INSERT INTO " + idCommande
					+ " (ARTICLE, QUANTITE)  VALUES" + "(" + requete + ")");
		}

	}

}
