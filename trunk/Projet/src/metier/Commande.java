package metier;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import basededonnees.SGBD;

public class Commande {

	// Une liste de Commande se définit par un identifiant
	// une liste de lignes de commande
	// Une date, et le client qui en est à l'origine

	
	
	// Le Constructeur ajoute la Commande à la table COMMANDES
	// Et met à jour la table générique INFOCOMMANDES
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
	
	public static ArrayList<String[]> preparerPanier(){
		ArrayList<String[]> panierClient = new ArrayList<String[]>();
		ArrayList<String> listeClients=SGBD.selectListeStringOrdonne("ARTICLE","IDARTICLE","IDARTICLE");
		
		for(int i=0;i<=listeClients.size();i++){
			panierClient.get(i)[0]=listeClients.get(i).toString();
			panierClient.get(i)[1]="0";
		}
		return panierClient;
		
	}
	
	public static int rechercheArticleDansPanier(String idArticle,ArrayList<String[]> panierClient){
		int compteurRechercheIdentifiant=0;
		boolean trouve=false;
		
		while(trouve==false){
			if(panierClient.get(compteurRechercheIdentifiant)[0].equals(idArticle)){
				trouve=true;
			}
			else{
				compteurRechercheIdentifiant=compteurRechercheIdentifiant+1;
			}
		}
		return compteurRechercheIdentifiant;
	}
	
	public static void ajouterArticlePanier(String idArticle, String quantite,ArrayList<String[]> panier){
		String stockArticle= SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE", idArticle);
		int compteurRechercheIdentifiant=rechercheArticleDansPanier(idArticle, panier);
		JOptionPane pbStock ; 
		
		if((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])+Integer.parseInt(quantite))<= Integer.parseInt(stockArticle)){
			panier.get(compteurRechercheIdentifiant)[1]=(Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])+Integer.parseInt(quantite))+"";
		}
		else{
			panier.get(compteurRechercheIdentifiant)[1]=Integer.parseInt(stockArticle)+"";
			pbStock = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStock.showMessageDialog(null, "Vous ne pouvez pas commander la quantité selectionnée, nous vous avons donné notre stock maximal", "Attention", JOptionPane.WARNING_MESSAGE, image);
		}
		
	}
	
	public static void enleverArticlePanier(String idArticle, String quantite,ArrayList<String[]> panier){
		int compteurRechercheIdentifiant=rechercheArticleDansPanier(idArticle, panier);
		JOptionPane pbStockZero;
		
		if((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])-Integer.parseInt(quantite))<= 0){
			panier.get(compteurRechercheIdentifiant)[1]=(Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])+Integer.parseInt(quantite))+"";
		}
		else{
			panier.get(compteurRechercheIdentifiant)[1]="0";
			pbStockZero = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStockZero.showMessageDialog(null, "Vous ne pouvez supprimer une quantité supérieure à celle dans votre panier, l'article n'est plus dans votre panier", "Attention", JOptionPane.WARNING_MESSAGE, image);
		}
		
	}
	
	// Méthode permettant d'ajouter cette commande à la table COMMANDES
	public void ajouterBDD() {

		String s = SGBD.transformation(this.date);
		
		String requete = "INSERT INTO COMMANDE (IDCOMMANDE, DATECOMMANDE) VALUES ( "
				+ "'"
				+ this.idCommande
				+ "',"
				+ s
				+ ")";
		
		SGBD.executeUpdate(requete);
	}

	// Méthode qui met à jour la table INFOCOMMANDES
	public void majInfoCommandes() {

		String requete = null;
		for (int i = 0; i < liste.size(); i++) {
			String s = SGBD.transformation(this.date);
			
			requete = "'" + liste.get(i).getArticle() + "',"
					+ liste.get(i).getQuantite();
			SGBD.executeUpdate("INSERT INTO LISTING_ARTICLES_COMMANDES " 
					+ " (IDCOMMANDE, IDARTICLE, QUANTITECOMMANDEE)  VALUES" 
					+ "('" 
					+ this.idCommande
					+ "',"
					+ requete + ")");
		}

	}

	@Override
	public String toString() {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return "Commande [idClient=" + idClient + ", date=" + sqlDate + "]";
	}
	
	
	//GRAND CHANTIER ...
	//Méthode qui retourne le prix de la commande
	
//	public double PrixCommande(){
//		//le client a-t-il une carte de fidelite ?
//		
//		//String idClient = SGBD.informationCommande(this.idCommande).get;
//	
//		String carteFidelite=SGBD.recupererInformationFideliteClient(this.idClient).get(0);
//		double prixCommande=0;
//		//pour chaque article de la commande...
//		
//		for (int i = 0; i < this.liste.size(); i++) {
//			
//			//récupérer le prix initial
//			int prixInit = (Integer) SGBD.informationCommande(this.idCommande).get(i)[2];
//			//quelle promotion appliquer selon la quantité ?
//			String typeArticle = (String) SGBD.informationCommande(this.idCommande).get(i)[0];
//			int quantiteCommandee = (Integer) SGBD.informationCommande(this.idCommande).get(i)[3];
//			ResultSet res1 = SGBD.executeQuery("select pourcentage from article a, categorie c, quantite q, listing_articles_commandes l, reduction r rownum=1 where a.idArticle=l.idArticle and r.idCategorie=c.idCategorie and r.idQuantite=q.idQuantite and c.idCategorie=a.idCategorie and l.quantiteCommande-q."+quantiteCommandee+">=0 and a.idArticle = "+typeArticle+"order by l.quantiteCommande-"+quantiteCommandee);
//			double promo1 = res1.getDouble(1);
//			//quelle promotion exceptionnelle appliquer ?
//			
//			double promo2 = SGBD.executeQuery("Select PourcentagePromo from Promo p, listing_promo_article lpa, Article a,  Where p.idPromo = lpa.idPromo and article.idArticle = "+typeArticle+ "And p.dateDebut < c.datecommande And p.dateFin > c.datecommande and PROMOFIDELITE=1").getDouble(1);
//			if (carteFidelite == "true"){
//			
//				if (promo2 == 0){
//					promo2 = SGBD.executeQuery("Select PourcentagePromo from Promo p, listing_promo_article lpa, Article a, Where p.idPromo = lpa.idPromo And a.idArticle = "+typeArticle+" And p.dateDebut < c.datecommande And p.dateFin > c.datecommande").getDouble(1);
//				}
//			}
//			else{
//				promo2 = SGBD.executeQuery("Select PourcentagePromo from Promo p, listing_promo_article lpa, Article a, listing_articles_commandes lac, commande c Where p.idPromo = lpa.idPromo And lpa.idArticle = a.idArticle  And a.idArticle = lac.idArticle And lac.idCommande=c.idCommande And lac.idCommande = "+this.idCommande+" And p.dateDebut < c.datecommande And p.dateFin > c.datecommande").getDouble(1);
//			}
//			//calcul du prix de la ligne de commande
//			double prixLigne;
//			if (promo1 > promo2){
//				prixLigne = prixInit*(1-promo1)*quantiteCommandee;}
//			else{
//				prixLigne = prixInit*(1-promo2)*quantiteCommandee;}
//			//calcul du prix total de la commande
//			prixCommande=prixCommande+prixLigne;
//		}
//		
//		return prixCommande;
//	}
	
}
