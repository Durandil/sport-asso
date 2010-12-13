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
 * <b>La classe Commande représente une commande</b>
 * <p>
 * Une commande est caractérisé par les informations suivantes  :
 * <ul>
 * <li>Un identifiant unique attribué définitivement, de la forme COMxxxxx</li>
 * <li>Une liste de LigneCommande</li>
 * <li>Le mail du client qui a effectué la commande</li>
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
	 * Le constructeur de la classe Commande fait appel à la méthode ajouterBDD()
	 * qui ajoute la commande dans la base de données.
	 * Il appelle également une méthode (majInfoCommandes()) qui met à jour la table LISTING_ARTICLES_COMMANDES.
	 * Une troisième méthode est appelée dans ce constructeur : elle met à jour la table ARTICLE,
	 * étant donné qu'à la suite d'une commande, les articles concernés voient leur stock diminuer
	 * 
	 * </p>
	 * 
	 * @param idCommande
	 *            L'identifiant unique de la commande.
	 * @param liste
	 *            La liste des lignes de commande.
	 * @param idClient
	 *            L'identifiant du client qui a passé la commande
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
     * Met à jour l'identifiant de la commande
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
     * Met à jour la liste des lignes de commande
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
     * Met à jour l'identifiant du client
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
     * Met à jour la date de la commande
     * 
     * @param date
     *            La date de la commande
     * 
     */
	public void setDate(Date date) {
		this.date = date;
	}
	
	

	/**
	 * Méthode qui prépare le panier
	 * 
	 * <p>
	 * Cette méthode commence par récupérer la liste des identifiants des articles disponibles.
	 * Elle ajoute ensuite l'ensemble de ces identifiants dans le panier et initialise leur quantité à 0
	 * </p> 
	 * 
	 * @return Le panier du client
	 * @see FenetreCommandeArticle
	 */
	
	public static ArrayList<String[]> preparerPanier(){
		ArrayList<String[]> panierClient = new ArrayList<String[]>();
		ArrayList<String> listeArticles=SGBD.selectListeStringOrdonneCondition("ARTICLE","IDARTICLE","IDARTICLE","STOCK > 0 and ETATARTICLE !='Supprimé'");
		
		for(int i=0;i<listeArticles.size();i++){
			String[] article={listeArticles.get(i),"0"};
			panierClient.add(article);
		}
		return panierClient;
		
	}
	
	/**
	 * Méthode qui vide le panier
	 * 
	 * <p>
	 * Cette méthode réinitialise chaque quantité d'article à 0 
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
	 * La méthode parcourt le panier jusqu'à ce que l'article recherché soit trouvé.
	 * Tant que l'article en question n'est pas dans le panier, un compteur s'incrémente au fur et à mesure
	 * Enfin, lorsque l'article est trouvé, la méthode retourne ce compteur, qui correspond
	 * à la position de l'article dans le panier (0 si l'article est le 1er article du panier, 1 s'il est
	 * le 2ème, etc.)
	 * </p> 
	 * 
	 * @param idArticle
	 * 		L'identifiant de l'article dont on cherche à savoir la position dans le panier
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

	

	/**
	 * Ajoute une quantité spécifique d'un article dans le panier
	 * 
	 * <p>
	 * La méthode commence par rechercher le stock de l'article concerné dans la table ARTICLE
	 * Elle récupère ensuite l'indice correspondant à la place de cet article dans le panier
	 * Par la suite deux cas se présentent :
	 * <ul>
	 * <li>Si la quantité désirée par le client est inférieure au stock, alors cette quantité 
	 * est ajoutée au panier.</li>
	 * <li>Sinon, cette quantité est limitée à la quantité disponible en stock et un message prévient le client
	 * que le stock serait épuisé s'il valide sa commande</li>
	 * </ul>
	 * </p> 
	 * 
	 * @param idArticle
	 * 		L'identifiant de l'article désiré par le client
	 * @param quantite
	 * 		La quantité commandée de l'article concerné
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
			pbStock.showMessageDialog(null, "Vous ne pouvez pas commander la quantité selectionnée, nous vous avons donné notre stock maximal", "Attention", JOptionPane.WARNING_MESSAGE, image);
		}
		
	}
	

	
	/**
	 * Enlève une quantité spécifique d'un article dans le panier
	 * 
	 * <p>
	 * La méthode commence par récupèrer l'indice correspondant à la place de cet article dans le panier
	 * Par la suite deux cas se présentent :
	 * <ul>
	 * <li>Si la quantité que le client veut retirer de son panier est inférieure à la quantité intiale
	 * alors cette quantité est retirée du panier.</li>
	 * <li>Sinon, cette quantité est limitée à la quantité initiale présente dans le panier
	 * et un message prévient le client que cette quantité tombe à 0 et qu'il ne peut supprimer
	 * plus que ce qu'il possèdait.</li>
	 * </ul>
	 * </p> 
	 * 
	 * @param idArticle
	 * 		L'identifiant de l'article que le client veut retirer de son panier
	 * @param quantite
	 * 		La quantité que le client veut retirer de son panier
	 * @param panier
	 * 		Le panier du client
	 * 
	 * @see Commande#rechercheArticleDansPanier(String, ArrayList)
	 * @see FenetreSuppressionPanier
	 */
	
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
	

	/**
	 * Méthode qui ajoute la commande à la table COMMANDE
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table afin
	 * de générer l'identifiant de la commande dans le format approprié.
	 * La requête se construit ensuite en fonction des caractéristiques de la commande
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
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

	/**
	 * Méthode qui met à jour la table LISTING_ARTICLES_COMMANDES
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'ensemble des identifiants et des quantités correspondantes
	 * présents dans la liste de LigneCommande et les concatène dans une chaîne de caractères.
	 * La requête se construit ensuite en fonction des caractéristiques de la commande
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
	public void majInfoCommandes() {

		String requete = null;
		for (int i = 0; i < liste.size(); i++) {
			
			requete = "'" + liste.get(i).getIdArticle() + "',"
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
	/**
	 * Méthode qui met à jour la table ARTICLE
	 * 
	 * <p>
	 * Pour chacun des articles présents dans la commande, la méthode soustrait la quantité commandée
	 * à la quantité en stock. S'il s'avère que le stock d'un article atteint 0, ce dernier sera considéré
	 * comme étant en rupture de stock.
	 * </p> 
	 * 
	 * @see BDD, Article
	 */
	public void majArticles(){
		String requete = null ;
		String idArticle=null;
		int quantiteReservee=0;
		String quantiteEnStock=null;
		
		for (int i = 0; i < liste.size(); i++) {
			idArticle=liste.get(i).getIdArticle();
			quantiteEnStock=SGBD.selectStringConditionString("ARTICLE", "STOCK", "IDARTICLE", idArticle);
			quantiteReservee=liste.get(i).getQuantite();
			int nouveauStock=Integer.parseInt(quantiteEnStock)-quantiteReservee;
			
			String etatArticle="En stock";
			if(nouveauStock==0){
				etatArticle="Rupture de stock";
			}
			
			requete = "UPDATE ARTICLE SET STOCK='"+ nouveauStock+"',ETATARTICLE='"+ etatArticle+"'"
					  +" WHERE IDARTICLE='"+ idArticle+"'";
			
			System.out.println(requete);
			
			SGBD.executeUpdate(requete);
			
		}
	}
	
		
	// calcul du montant d'un article pour un client donne
	public double MontantCommandePourUnArticle( String idClient, LigneCommande ligne,int estFidele) throws SQLException{
		String pourcentagePromoExc="0";
		String pourcentagePromoDegressif="0";
		double montantArticle = 0 ;
		
		// recuperation prix initial et quantite commandee
		String prixInit = SGBD.selectStringConditionString("ARTICLE", "PRIXINITIAL","IDARTICLE" ,ligne.getIdArticle());
		double prixInitial = Double.parseDouble(prixInit);
		int quantiteCommandee = ligne.getQuantite();
		JOptionPane.showMessageDialog(null,prixInitial + " "+ quantiteCommandee + " "+ ligne.getIdArticle());
	
		//  recuperation pourcentage degressif		
		pourcentagePromoDegressif = SGBD.recupererPourcentagePromotionDegressifArticleCommande(this.idCommande, ligne.getIdArticle());
			
		// recuperation pourcentage promotion exceptionnelle
		pourcentagePromoExc = SGBD.recupererPourcentagePromotionExceptionnelleArticle(ligne.getIdArticle(), estFidele);
		
		JOptionPane.showMessageDialog(null,pourcentagePromoDegressif + " " + pourcentagePromoExc);
		
		double promoAppliquee = Double.parseDouble(pourcentagePromoDegressif);
		if(Double.parseDouble(pourcentagePromoDegressif)<Double.parseDouble(pourcentagePromoExc)){
			promoAppliquee = Double.parseDouble(pourcentagePromoExc);
		}
		
		montantArticle = (Double) (quantiteCommandee*prixInitial*(1-promoAppliquee/100));
		JOptionPane.showMessageDialog(null,montantArticle);
		
		return montantArticle;
	}
	
	public double montantTotalArticle(ArrayList<LigneCommande> panier,String idClient) throws SQLException{
		double total = 0;
		
		// recuperation booleen fidelite		
		ArrayList<String> fideliteClient= SGBD.recupererInformationFideliteClient(idClient);
		String fidele = fideliteClient.get(0);
		int estFidele=0;
		if(fidele.equals("Oui")){
			estFidele=1;
		}
		
		for (LigneCommande ligneCommande : panier) {
			double montantArticle = MontantCommandePourUnArticle( idClient, ligneCommande,estFidele);
			total = total+montantArticle;
		}
		
		return total ;
	}
	
}
