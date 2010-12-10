package metier;

import ihm.FenetreChoixCatalogue;
import ihm.Client.FenetreCommandeArticle;
import ihm.Client.FenetreSuppressionPanier;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import basededonnees.SGBD;


/**
 * <b>La classe Commande repr�sente une commande</b>
 * <p>
 * Une commande est caract�ris� par les informations suivantes  :
 * <ul>
 * <li>Un identifiant unique attribu� d�finitivement, de la forme COMxxxxx</li>
 * <li>Une liste de LigneCommande</li>
 * <li>Le mail du client qui a effectu� la commande</li>
 * <li>Une date</li>
 * </ul>
 * </p>
 * 
 * @see BDD, LigneCommande, Promotion, FenetreCommandeArticle
 */

public class Commande {
	

	private String idCommande;
	private ArrayList<LigneCommande> liste;
	private String idClient;
	private Date date;
	
	/**
	 * Constructeur de la classe Commande
	 * <p>
	 * Le constructeur de la classe Commande fait appel � la m�thode ajouterBDD()
	 * qui ajoute la commande dans la base de donn�es.
	 * Il appelle �galement une m�thode (majInfoCommandes()) qui met � jour la table LISTING_ARTICLES_COMMANDES.
	 * Une troisi�me m�thode est appel�e dans ce constructeur : elle met � jour la table ARTICLE,
	 * �tant donn� qu'� la suite d'une commande, les articles concern�s voient leur stock diminuer
	 * 
	 * </p>
	 * 
	 * @param idCommande
	 *            L'identifiant unique de la commande.
	 * @param liste
	 *            La liste des lignes de commande.
	 * @param idClient
	 *            L'identifiant du client qui a pass� la commande
	 * @param date
	 *            La date de la commande
	 *            
	 * @see Commande#idCommande
	 * @see Commande#liste
	 * @see Commande#idClient
	 * @see Commande#date
	 * @see Commande#ajouterBDD()	 
	 * @see Commande#majInfoCommandes()
	 * @see Commande#majArticles()
	 */
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

	 /**
     * Retourne l'identifiant de la commande
     * 
     * @return L'identifiant de la commande
     * 
     */
	public String getIdCommande() {
		return idCommande;
	}
	
	
    /**
     * Met � jour l'identifiant de la commande
     * 
     * @param idCommande
     *            L'identifiant unique de la commande
     * 
     */
	public void setIdCommande(String idCommande) {
		this.idCommande = idCommande;
	}

	 /**
     * Retourne la liste des lignes de commande
     * 
     * @return La liste des lignes de commandes
     * 
     */
	public ArrayList<LigneCommande> getListe() {
		return liste;
	}
	
	
	 /**
     * Met � jour la liste des lignes de commande
     * 
     * @param liste
     *            Liste des lignes de commandes
     * 
     */
	public void setListe(ArrayList<LigneCommande> liste) {
		this.liste = liste;
	}

	
	
	 /**
     * Retourne l'identifiant du client
     * 
     * @return L'identifiant du client
     * 
     */
	public String getIdClient() {
		return idClient;
	}

	 /**
     * Met � jour l'identifiant du client
     * 
     * @param idClient
     *            L'identifiant du client
     * 
     */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	 /**
     * Retourne la date de la commande
     * 
     * @return La date de la commande
     * 
     */
	public Date getDate() {
		return date;
	}

	 /**
     * Met � jour la date de la commande
     * 
     * @param date
     *            La date de la commande
     * 
     */
	public void setDate(Date date) {
		this.date = date;
	}
	
	

	/**
	 * M�thode qui pr�pare le panier
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer la liste des identifiants des articles disponibles.
	 * Elle ajoute ensuite l'ensemble de ces identifiants dans le panier et initialise leur quantit� � 0
	 * </p> 
	 * 
	 * @return Le panier du client
	 * @see FenetreCommandeArticle
	 */
	
	public static ArrayList<String[]> preparerPanier(){
		ArrayList<String[]> panierClient = new ArrayList<String[]>();
		ArrayList<String> listeArticles=SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK > 0");
		
		for(int i=0;i<listeArticles.size();i++){
			String[] article={listeArticles.get(i),"0"};
			panierClient.add(article);
		}
		return panierClient;
		
	}
	
	/**
	 * M�thode qui vide le panier
	 * 
	 * <p>
	 * Cette m�thode r�initialise chaque quantit� d'article � 0 
	 * </p> 
	 * 
	 * @param panier
	 * 		Le panier du client
	 * @see FenetreCommandeArticle
	 */


	public static void viderPanier(ArrayList<String[]> panier){
		
		for (int i =0; i< panier.size(); i++){
			panier.get(i)[1]="0";
		}
		
	}
		
	/**
	 * Recherche la position d'un article dans le panier
	 * 
	 * <p>
	 * La m�thode parcourt le panier jusqu'� ce que l'article recherch� soit trouv�.
	 * Tant que l'article en question n'est pas dans le panier, un compteur s'incr�mente au fur et � mesure
	 * Enfin, lorsque l'article est trouv�, la m�thode retourne ce compteur, qui correspond
	 * � la position de l'article dans le panier (0 si l'article est le 1er article du panier, 1 s'il est
	 * le 2�me, etc.)
	 * </p> 
	 * 
	 * @param idArticle
	 * 		L'identifiant de l'article dont on cherche � savoir la position dans le panier
	 * @param panierClient
	 * 		Le panier du client
	 * @return L'emplacement de l'article dans le panier
	 * 
	 * @see Commande#ajouterArticlePanier(String, String, ArrayList)
	 * @see Commande#enleverArticlePanier(String, String, ArrayList)
	 * @see FenetreCommandeArticle
	 */
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

	
//	M�thode qui ajoute une quantit� sp�cifique d'un article dans le panier 

	/**
	 * Ajoute une quantit� sp�cifique d'un article dans le panier
	 * 
	 * <p>
	 * La m�thode commence par rechercher le stock de l'article concern� dans la table ARTICLE
	 * Elle r�cup�re ensuite l'indice correspondant � la place de cet article dans le panier
	 * Par la suite deux cas se pr�sentent :
	 * Si la quantit� d�sir�e par le client est inf�rieure au stock, alors cette quantit� 
	 * est ajout�e au panier.
	 * Sinon, cette quantit� est limit�e � la quantit� disponible en stock et un message pr�vient le client
	 * que le stock serait �puis� s'il valide sa commande
	 * </p> 
	 * 
	 * @param idArticle
	 * 		L'identifiant de l'article d�sir� par le client
	 * @param quantite
	 * 		La quantit� command�e de l'article concern�
	 * @param panier
	 * 		Le panier du client
	 * 
	 * @see Commande#rechercheArticleDansPanier(String, ArrayList)
	 * @see FenetreChoixCatalogue
	 */
	
	public static void ajouterArticlePanier(String idArticle, int quantite,ArrayList<String[]> panier){
		String stockArticle= SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE", idArticle);
		int compteurRechercheIdentifiant=rechercheArticleDansPanier(idArticle, panier);
		JOptionPane pbStock ; 
		
		if((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])+quantite)<= Integer.parseInt(stockArticle)){
			panier.get(compteurRechercheIdentifiant)[1]=(Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])+quantite)+"";
		}
		else{
			panier.get(compteurRechercheIdentifiant)[1]=Integer.parseInt(stockArticle)+"";
			pbStock = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStock.showMessageDialog(null, "Vous ne pouvez pas commander la quantit� selectionn�e, nous vous avons donn� notre stock maximal", "Attention", JOptionPane.WARNING_MESSAGE, image);
		}
		
	}
	
//	M�thode qui enl�ve une quantit� sp�cifique d'un article dans le panier 
	
	/**
	 * Enl�ve une quantit� sp�cifique d'un article dans le panier
	 * 
	 * <p>
	 * La m�thode commence par r�cup�rer l'indice correspondant � la place de cet article dans le panier
	 * Par la suite deux cas se pr�sentent :
	 * Si la quantit� que le client veut retirer de son panier est inf�rieure � la quantit� intiale
	 * alors cette quantit� est retir�e du panier.
	 * Sinon, cette quantit� est limit�e � la quantit� initiale pr�sente dans le panier
	 * et un message pr�vient le client que cette quantit� tombe � 0 et qu'il ne peut supprimer
	 * plus que ce qu'il poss�dait.
	 * </p> 
	 * 
	 * @param idArticle
	 * 		L'identifiant de l'article que le client veut retirer de son panier
	 * @param quantite
	 * 		La quantit� que le client veut retirer de son panier
	 * @param panier
	 * 		Le panier du client
	 * 
	 * @see Commande#rechercheArticleDansPanier(String, ArrayList)
	 * @see FenetreSuppressionPanier
	 */
	
	public static void enleverArticlePanier(String idArticle, String quantite,ArrayList<String[]> panier){
		int compteurRechercheIdentifiant=rechercheArticleDansPanier(idArticle, panier);
		JOptionPane pbStockZero;
		
		System.out.println("Quantit� panier "+Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1]));
		System.out.println("quantite � retirer "+Integer.parseInt(quantite));
		
		if((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])-Integer.parseInt(quantite))>= 0){
			panier.get(compteurRechercheIdentifiant)[1]=(Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1])-Integer.parseInt(quantite))+"";
		}
		else{
			panier.get(compteurRechercheIdentifiant)[1]="0";
			pbStockZero = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStockZero.showMessageDialog(null, "Vous ne pouvez supprimer une quantit� sup�rieure � celle dans votre panier, l'article n'est plus dans votre panier", "Attention", JOptionPane.WARNING_MESSAGE, image);
		}
		
	}
	
	// M�thode permettant d'ajouter cette commande � la table COMMANDE
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

	// M�thode qui met � jour la table LISTING_ARTICLES_COMMANDES
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

	// M�thode qui met � jour l'�tat d'un article en fonction de la quantit� disponible
	public void majArticles(){
		String requete = null ;
		String nomArticle=null;
		int quantiteReservee=0;
		String quantiteEnStock=null;
		
		for (int i = 0; i < liste.size(); i++) {
			nomArticle=liste.get(i).getArticle();
			quantiteEnStock=SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE", nomArticle);
			quantiteReservee=liste.get(i).getQuantite();
			int nouveauStock=Integer.parseInt(quantiteEnStock)-quantiteReservee;
			
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
		int quantiteCommandee = ligne.getQuantite();
		
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
	//M�thode qui retourne le prix de la commande
	
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
//			//r�cup�rer le prix initial
//			int prixInit = (Integer) SGBD.informationCommande(this.idCommande).get(i)[2];
//			//quelle promotion appliquer selon la quantit� ?
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
