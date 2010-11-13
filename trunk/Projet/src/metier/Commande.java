package metier;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import basededonnees.SGBD;

public class Commande {

	// Une liste de Commande se d�finit par un identifiant
	// une liste de lignes de commande
	// Une date, et le client qui en est � l'origine

	
	
	// Le Constructeur ajoute la Commande � la table COMMANDES
	// Et met � jour la table g�n�rique INFOCOMMANDES
	public Commande(String idCommande, String idClient,
			ArrayList<LigneCommande> liste, Date date) {
		this.idCommande = idCommande;
		this.idClient = idClient;
		this.liste = liste;
		this.date = date;
		ajouterBDD();
		majInfoCommandes();
	}

	private String idClient;
	private Date date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	// M�thode permettant d'ajouter cette commande � la table COMMANDES
	public void ajouterBDD() {

		String s = SGBD.transformation(this.date);
		
		String requete = "INSERT INTO COMMANDES (IDENTIFIANT, DATECOMMANDE, IDCLIENT) VALUES ( "
				+ "'"
				+ this.idCommande
				+ "',"
				+ s
				+ ",'"
				+ this.idClient + "')";
		
		SGBD.executeUpdate(requete);
	}

	// M�thode qui met � jour la table INFOCOMMANDES
	public void majInfoCommandes() {

		String requete = null;
		for (int i = 0; i < liste.size(); i++) {
			String s = SGBD.transformation(this.date);
			requete = "'" + liste.get(i).getArticle() + "',"
					+ liste.get(i).getQuantite();
			SGBD.executeUpdate("INSERT INTO INFOCOMMANDES " 
					+ " (IDCOMMANDE, DATECOMMANDE, IDCLIENT, IDARTICLE, QUANTITE)  VALUES" 
					+ "('" 
					+ this.idCommande
					+ "',"
					+ s
					+ ","
					+ "'"
					+ this.idClient
					+ "',"
					+ requete + ")");
		}

	}

	@Override
	public String toString() {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return "Commande [idClient=" + idClient + ", date=" + sqlDate + "]";
	}
	

	
}
