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

	
	
	// Le Constructeur ajoute la Commande à la table COMMANDE
	// Et met à jour la table générique LISTING_ARTICLES_COMMANDES
	public Commande(String idCommande, String idClient,
			ArrayList<LigneCommande> liste, Date date) {
		this.idCommande = idCommande;
		this.idClient = idClient;
		this.liste = liste;
		this.date = date;
		ajouterBDD();
		majInfoCommandes();
		majArticles();
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
	
	
//	Méthode qui prépare le panier en récupérant la liste des identifiants des articles
//	qui sont disponibles
	
	public static ArrayList<String[]> preparerPanier(){
		ArrayList<String[]> panierClient = new ArrayList<String[]>();
		ArrayList<String> listeClients=SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK > 0");
		
		for(int i=0;i<listeClients.size();i++){
			String[] client={listeClients.get(i),"0"};
			panierClient.add(client);
		}
		return panierClient;
		
	}
	

//	Méthode qui vide le panier en replaçant chacun de ses élément par "0"
	public static void viderPanier(ArrayList<String[]> panier){
		
		for (int i =0; i< panier.size(); i++){
			panier.get(i)[1]="0";
		}
		
	}
	
//	Méthode qui recherche un article dans le panier et communique la quantité correspondante
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

	
//	Méthode qui ajoute une quantité spécifique d'un article dans le panier 

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
	
//	Méthode qui enlève une quantité spécifique d'un article dans le panier 
	public static void enleverArticlePanier(String idArticle, String quantite,ArrayList<String[]> panier){
		int compteurRechercheIdentifiant=rechercheArticleDansPanier(idArticle, panier);
		JOptionPane pbStockZero;
		
		System.out.println("Quantité panier "+Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1]));
		System.out.println("quantite à retirer "+Integer.parseInt(quantite));
		
		if((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])-Integer.parseInt(quantite))>= 0){
			panier.get(compteurRechercheIdentifiant)[1]=(Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])-Integer.parseInt(quantite))+"";
		}
		else{
			panier.get(compteurRechercheIdentifiant)[1]="0";
			pbStockZero = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStockZero.showMessageDialog(null, "Vous ne pouvez supprimer une quantité supérieure à celle dans votre panier, l'article n'est plus dans votre panier", "Attention", JOptionPane.WARNING_MESSAGE, image);
		}
		
	}
	
	// Méthode permettant d'ajouter cette commande à la table COMMANDE
	public void ajouterBDD() {

		String s = SGBD.transformation(this.date);
		
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_COMMANDE.NEXTVAL");
		
		
		String numCommande = "";
		int numeroCommande = 0;

		numeroCommande = Integer.parseInt(idNonFini.get(0));

		if (numeroCommande < 10) {
			numCommande = "COM0000" + numeroCommande;
		}
		if (numeroCommande < 100 && numeroCommande >= 10) {
			numCommande = "COM000" + numeroCommande;
		}
		if (numeroCommande < 1000 && numeroCommande >= 100) {
			numCommande = "COM00" + numeroCommande;
		}
		if (numeroCommande < 10000 && numeroCommande >= 1000) {
			numCommande = "COM0" + numeroCommande;
		}
		if (numeroCommande < 100000 && numeroCommande >= 10000) {
			numCommande = "COM" + numeroCommande;
		}
		this.setIdCommande(numCommande);
		String requete = "INSERT INTO COMMANDE (IDCOMMANDE, DATECOMMANDE, IDCLIENT) VALUES ( "
				+ "'" + numCommande + "'," + s + " , '" + this.idClient + "')";

		System.out.println(requete);
		
		SGBD.executeUpdate(requete);
	}

	// Méthode qui met à jour la table LISTING_ARTICLES_COMMANDES
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

	// Méthode qui met à jour l'état d'un article en fonction de la quantité disponible
	public void majArticles(){
		String requete = null ;
		String nomArticle=null;
		String quantiteReservee=null;
		String quantiteEnStock=null;
		
		for (int i = 0; i < liste.size(); i++) {
			nomArticle=liste.get(i).getArticle();
			quantiteEnStock=SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE", nomArticle);
			quantiteReservee=liste.get(i).getQuantite();
			int nouveauStock=Integer.parseInt(quantiteEnStock)-Integer.parseInt(quantiteReservee);
			
			String etatArticle="En stock";
			if(nouveauStock==0){
				etatArticle="Rupture de stock";
			}
			
			requete = "UPDATE ARTICLE SET STOCK='"+ nouveauStock+"',ETATARTICLE='"+ etatArticle+"'"
					  +" WHERE IDARTICLE='"+ nomArticle+"'";
			
			System.out.println(requete);
			
			SGBD.executeUpdate(requete);
			
		}
	}
	
	@Override
	public String toString() {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return "Commande [idClient=" + idClient + ", date=" + sqlDate + "]";
	}
	
	// calcul du montant d'un article pour un client donne
	public double MontantCommandePourUnArticle(String idClient, LigneCommande ligne) throws SQLException{
		String pourcentagePromoExc="0";
		String pourcentagePromoDegressif="0";
		double montantArticle = 0 ;
		ResultSet res = null ;
		ResultSet res2 = null ;
		
		// recuperation prix initial et quantite commandee
		String prixInit = SGBD.selectStringConditionString("ARTICLE", "PRIXINITIAL","IDARTICLE" ,ligne.getArticle());
		double prixInitial = Double.parseDouble(prixInit);
		int quantiteCommandee = Integer.parseInt(ligne.getQuantite());
		
		// recuperation booleen fidelite		
		ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(idClient);
		String fidele = fideliteClient.get(0);
		int estFidele=0;
		if(fidele.equals("Oui")){
			estFidele=1;
		}
		
		try{
			//  recuperation pourcentage degressif
			res = SGBD.executeQuery("select pourcentage from article a, categorie c, quantite q, listing_articles_commandes l, reduction r rownum=1"+
							" where a.idArticle=l.idArticle"+
							" and r.idCategorie=c.idCategorie"+
							" and r.idQuantite=q.idQuantite"+
							" and c.idCategorie=a.idCategorie"+
							" and (l.quantiteCommande-q.quantite)>=0"+
							" order by (l.quantiteCommande-q.quantite)");
			
			pourcentagePromoDegressif = res.getObject(1).toString();
			
			
			// recuperation pourcentage promotion exceptionnelle
			if(estFidele==0){
				res2 = SGBD.executeQuery("select pourcentagepromo rownum=1 from promo p,listing_promos_articles lpa"+
						" where p.idpromo = lpa.idPromo and lpa.idarticle='"+ ligne.getArticle()+"' and promoFidelite=0"+
						" order by pourcentagepromo desc");
			}
			else{
				res2 = SGBD.executeQuery("select pourcentagepromo rownum=1 from promo p,listing_promos_articles lpa"+
							" where p.idpromo = lpa.idPromo and lpa.idarticle= condition"+
							" order by pourcentagepromo desc");
			}
			
			pourcentagePromoExc = res2.getObject(1).toString();
		}
		catch(SQLException e){
			
			e.printStackTrace();
			
		}
		finally{
			
			double promoAppliquee = Double.parseDouble(pourcentagePromoDegressif);
			if(Double.parseDouble(pourcentagePromoDegressif)<Double.parseDouble(pourcentagePromoExc)){
				promoAppliquee = Double.parseDouble(pourcentagePromoExc);
			}
		
			montantArticle = quantiteCommandee*prixInitial*(1-promoAppliquee);
		}
		
		return montantArticle;
	}
	
	public double montantTotalArticle(ArrayList<LigneCommande> panier,String idClient) throws SQLException{
		double total = 0;
		
		for (LigneCommande ligneCommande : panier) {
			double montantArticle = MontantCommandePourUnArticle(idClient, ligneCommande);
			total = total+montantArticle;
		}
		
		return total ;
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
