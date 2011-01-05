package metier;

import ihm.Client.FenetreChoixCatalogue;
import ihm.Client.FenetreCommandeArticle;
import ihm.Client.FenetreSuppressionPanier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import basededonnees.SGBD;

/**
 * <b>La classe Commande repr�sente une commande.</b>
 * <p>
 * Une commande est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribu� d�finitivement, de la forme COMxxxxx</li>
 * <li>Une liste de LigneCommande</li>
 * <li>Le mail du client qui a effectu� la commande</li>
 * <li>Une date</li>
 * <li>Un montant</li>
 * </ul>
 * </p>
 * 
 * @see basededonnees.BDD
 * @see LigneCommande
 * @see Promotion
 * @see FenetreCommandeArticle
 */

public class Commande {

	/**
	 * L'Identifiant de la commande, non modifiable.
	 * 
	 * @see Commande#getIdCommande()
	 * @see Commande#setIdCommande(String)
	 * @see Commande#ajouterBDD()
	 * @see Commande#majInfoCommandes()
	 */
	private String idCommande;

	/**
	 * La liste des lignes de commande.
	 * 
	 * @see Commande#getListe()
	 * @see Commande#setListe(ArrayList)
	 * @see LigneCommande
	 * @see Commande#ajouterBDD()
	 * @see Commande#majInfoCommandes()
	 * @see Commande#montantCommandePourUnArticle(String, LigneCommande, int)
	 */
	private ArrayList<LigneCommande> liste;

	/**
	 * L'identifiant du client qui passe la commande.
	 * 
	 * @see Commande#getIdClient()
	 * @see Commande#setIdClient(String)
	 * @see Commande#ajouterBDD()
	 * @see Commande#montantCommandePourUnArticle(String, LigneCommande, int)
	 * @see Commande#montantTotalArticle(ArrayList, String)
	 */
	private String idClient;

	/**
	 * La date de la commande.
	 * 
	 * @see Commande#getDate()
	 * @see Commande#setDate(Date)
	 * @see Commande#ajouterBDD()
	 */
	private Date date;

	/**
	 * Le montant de la commande.
	 * 
	 * @see Commande#getMontant()
	 * @see Commande#setMontant(int)
	 * @see Commande#ajouterBDD()
	 * @see Commande#majMontantCommande(int)
	 */
	private int montant;

	/**
	 * Constructeur de la classe Commande.
	 * <p>
	 * Le constructeur de la classe Commande fait appel � la m�thode
	 * ajouterBDD() qui ajoute la commande dans la base de donn�es.<br>
	 * Il appelle �galement une m�thode (majInfoCommandes()) qui met � jour la
	 * table LISTING_ARTICLES_COMMANDES. <br>
	 * Une troisi�me m�thode est appel�e dans ce constructeur : elle met � jour
	 * la table ARTICLE, �tant donn� qu'� la suite d'une commande, les articles
	 * concern�s voient leur stock diminuer
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
		this.montant = 0;
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
	 * Retourne le montant de la commande
	 * 
	 * @return Le montant de la commande
	 * 
	 */
	public int getMontant() {
		return montant;
	}

	/**
	 * Met � jour le montant de la commande
	 * 
	 * @param montant
	 *            Le montant de la commande
	 * 
	 */
	public void setMontant(int montant) {
		this.montant = montant;
	}

	/**
	 * M�thode qui pr�pare le panier
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer la liste des identifiants des
	 * articles disponibles. Elle ajoute ensuite l'ensemble de ces identifiants
	 * dans le panier et initialise leur quantit� � 0.
	 * </p>
	 * 
	 * @return Le panier du client
	 * @see FenetreCommandeArticle
	 */

	public static ArrayList<String[]> preparerPanier() {
		ArrayList<String[]> panierClient = new ArrayList<String[]>();
		ArrayList<String> listeArticles = SGBD
				.selectListeStringOrdonneCondition("ARTICLE", "IDARTICLE",
						"IDARTICLE", "STOCK > 0 and ETATARTICLE !='Supprim�'");

		for (int i = 0; i < listeArticles.size(); i++) {
			String[] article = { listeArticles.get(i), "0" };
			panierClient.add(article);
		}
		return panierClient;

	}

	/**
	 * M�thode qui vide le panier.
	 * 
	 * <p>
	 * Cette m�thode r�initialise chaque quantit� d'article � 0.
	 * </p>
	 * 
	 * @param panier
	 *            Le panier du client
	 * @see FenetreCommandeArticle
	 */

	public static void viderPanier(ArrayList<String[]> panier) {

		for (int i = 0; i < panier.size(); i++) {
			panier.get(i)[1] = "0";
		}

	}

	/**
	 * Recherche la position d'un article dans le panier.
	 * 
	 * <p>
	 * La m�thode parcourt le panier jusqu'� ce que l'article recherch� soit
	 * trouv�. Tant que l'article en question n'est pas dans le panier, un
	 * compteur s'incr�mente au fur et � mesure.<br>
	 * Enfin, lorsque l'article est trouv�, la m�thode retourne ce compteur, qui
	 * correspond � la position de l'article dans le panier (0 si l'article est
	 * le 1er article du panier, 1 s'il est le 2�me, etc.)
	 * </p>
	 * 
	 * @param idArticle
	 *            L'identifiant de l'article dont on cherche � savoir la
	 *            position dans le panier
	 * @param panierClient
	 *            Le panier du client
	 * @return L'emplacement de l'article dans le panier
	 * 
	 * @see Commande#ajouterArticlePanier(String, int, ArrayList)
	 * @see Commande#enleverArticlePanier(String, String, ArrayList)
	 * @see FenetreCommandeArticle
	 */
	public static int rechercheArticleDansPanier(String idArticle,
			ArrayList<String[]> panierClient) {
		int compteurRechercheIdentifiant = 0;
		boolean trouve = false;

		while (trouve == false) {
			if (panierClient.get(compteurRechercheIdentifiant)[0]
					.equals(idArticle)) {
				trouve = true;
			} else {
				compteurRechercheIdentifiant = compteurRechercheIdentifiant + 1;
			}
		}
		return compteurRechercheIdentifiant;
	}

	/**
	 * Ajoute une quantit� sp�cifique d'un article dans le panier.
	 * 
	 * <p>
	 * La m�thode commence par rechercher le stock de l'article concern� dans la
	 * table ARTICLE. Elle r�cup�re ensuite l'indice correspondant � la place de
	 * cet article dans le panier.<br>
	 * Par la suite deux cas se pr�sentent :
	 * <ul>
	 * <li>Si la quantit� d�sir�e par le client est inf�rieure au stock, alors
	 * cette quantit� est ajout�e au panier.</li>
	 * <li>Sinon, cette quantit� est limit�e � la quantit� disponible en stock
	 * et un message pr�vient le client que le stock serait �puis� s'il valide
	 * sa commande</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idArticle
	 *            L'identifiant de l'article d�sir� par le client
	 * @param quantite
	 *            La quantit� command�e de l'article concern�
	 * @param panier
	 *            Le panier du client
	 * 
	 * @see Commande#rechercheArticleDansPanier(String, ArrayList)
	 * @see FenetreChoixCatalogue
	 */
	@SuppressWarnings("static-access")
	public static void ajouterArticlePanier(String idArticle, int quantite,
			ArrayList<String[]> panier) {
		String stockArticle = SGBD.selectStringConditionString("ARTICLE",
				"STOCK", "IDARTICLE", idArticle);
		int compteurRechercheIdentifiant = rechercheArticleDansPanier(
				idArticle, panier);
		JOptionPane pbStock;

		if ((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1]) + quantite) <= Integer
				.parseInt(stockArticle)) {
			panier.get(compteurRechercheIdentifiant)[1] = (Integer
					.parseInt(panier.get(compteurRechercheIdentifiant)[1]) + quantite)
					+ "";
		} else {
			panier.get(compteurRechercheIdentifiant)[1] = Integer
					.parseInt(stockArticle) + "";
			pbStock = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStock.showMessageDialog(
					null,
					"Vous ne pouvez pas commander la quantit� selectionn�e, nous vous avons donn� notre stock maximal",
					"Attention", JOptionPane.WARNING_MESSAGE, image);
		}

	}

	/**
	 * Enl�ve une quantit� sp�cifique d'un article dans le panier.
	 * 
	 * <p>
	 * La m�thode commence par r�cup�rer l'indice correspondant � la place de
	 * cet article dans le panier. <br>
	 * Par la suite deux cas se pr�sentent :
	 * <ul>
	 * <li>Si la quantit� que le client veut retirer de son panier est
	 * inf�rieure � la quantit� intiale alors cette quantit� est retir�e du
	 * panier.</li>
	 * <li>Sinon, cette quantit� est limit�e � la quantit� initiale pr�sente
	 * dans le panier et un message pr�vient le client que cette quantit� tombe
	 * � 0 et qu'il ne peut supprimer plus que ce qu'il poss�dait.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idArticle
	 *            L'identifiant de l'article que le client veut retirer de son
	 *            panier
	 * @param quantite
	 *            La quantit� que le client veut retirer de son panier
	 * @param panier
	 *            Le panier du client
	 * 
	 * @see Commande#rechercheArticleDansPanier(String, ArrayList)
	 * @see FenetreSuppressionPanier
	 */

	@SuppressWarnings("static-access")
	public static void enleverArticlePanier(String idArticle, String quantite,
			ArrayList<String[]> panier) {
		int compteurRechercheIdentifiant = rechercheArticleDansPanier(
				idArticle, panier);
		JOptionPane pbStockZero;

		System.out
				.println("Quantit� panier "
						+ Integer.parseInt(panier
								.get(compteurRechercheIdentifiant)[1]));
		System.out.println("quantite � retirer " + Integer.parseInt(quantite));

		if ((Integer.parseInt(panier.get(compteurRechercheIdentifiant)[1]) - Integer
				.parseInt(quantite)) >= 0) {
			panier.get(compteurRechercheIdentifiant)[1] = (Integer
					.parseInt(panier.get(compteurRechercheIdentifiant)[1]) - Integer
					.parseInt(quantite))
					+ "";
		} else {
			panier.get(compteurRechercheIdentifiant)[1] = "0";
			pbStockZero = new JOptionPane();
			ImageIcon image = new ImageIcon("src/images/warning.png");
			pbStockZero
					.showMessageDialog(
							null,
							"Vous ne pouvez supprimer une quantit� sup�rieure � celle dans votre panier, l'article n'est plus dans votre panier",
							"Attention", JOptionPane.WARNING_MESSAGE, image);
		}

	}

	/**
	 * M�thode qui ajoute la commande � la table COMMANDE
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer l'indice de s�quence de la table
	 * afin de g�n�rer l'identifiant de la commande dans le format appropri�. La
	 * requ�te se construit ensuite en fonction des caract�ristiques de la
	 * commande saisies lors de l'appel du constructeur.
	 * </p>
	 * 
	 * @see basededonnees.BDD
	 */
	public void ajouterBDD() {

		String s = SGBD.transformation(this.date);

		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
				"S_COMMANDE.NEXTVAL");

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
	 * M�thode qui met � jour le montant de la commande dans la table COMMANDE
	 * puisque celui a �t� fix� � z�ro dans le constructeur de Commande et que
	 * le calcul du montant intervient apr�s l'ajout de la commande dans la base
	 * de donn�es.
	 * 
	 * @param montantCommande
	 */

	public void majMontantCommande(int montantCommande) {

		SGBD.executeUpdate(" UPDATE COMMANDE SET MONTANTCOMMANDE="
				+ montantCommande + " WHERE IDCOMMANDE='" + this.idCommande
				+ "'");

		System.out.println(" UPDATE COMMANDE SET MONTANTCOMMANDE="
				+ montantCommande + " WHERE IDCOMMANDE='" + this.idCommande
				+ "'");
	}

	/**
	 * M�thode qui met � jour la table LISTING_ARTICLES_COMMANDES.
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer l'ensemble des identifiants et des
	 * quantit�s correspondantes pr�sents dans la liste de LigneCommande et les
	 * concat�ne dans une cha�ne de caract�res. La requ�te se construit ensuite
	 * en fonction des caract�ristiques de la commande saisies lors de l'appel
	 * du constructeur
	 * </p>
	 * 
	 * @see basededonnees.BDD
	 */
	public void majInfoCommandes() {

		String requete = null;
		for (int i = 0; i < liste.size(); i++) {

			requete = "'" + liste.get(i).getIdArticle() + "',"
					+ liste.get(i).getQuantite();
			SGBD.executeUpdate("INSERT INTO LISTING_ARTICLES_COMMANDES "
					+ " (IDCOMMANDE, IDARTICLE, QUANTITECOMMANDEE)  VALUES"
					+ "('" + this.idCommande + "'," + requete + ")");
		}

	}

	/**
	 * M�thode qui met � jour la table ARTICLE
	 * 
	 * <p>
	 * Pour chacun des articles pr�sents dans la commande, la m�thode soustrait
	 * la quantit� command�e � la quantit� en stock. S'il s'av�re que le stock
	 * d'un article atteint 0, ce dernier sera consid�r� comme �tant en rupture
	 * de stock.
	 * </p>
	 * 
	 * @see basededonnees.BDD
	 * @see Article
	 * @see SGBD#selectStringConditionString(String, String, String, String)
	 */
	public void majArticles() {
		String requete = null;
		String idArticle = null;
		int quantiteReservee = 0;
		String quantiteEnStock = null;

		for (int i = 0; i < liste.size(); i++) {
			idArticle = liste.get(i).getIdArticle();
			quantiteEnStock = SGBD.selectStringConditionString("ARTICLE",
					"STOCK", "IDARTICLE", idArticle);
			quantiteReservee = liste.get(i).getQuantite();
			int nouveauStock = Integer.parseInt(quantiteEnStock)
					- quantiteReservee;

			String etatArticle = "En stock";
			if (nouveauStock == 0) {
				etatArticle = "Rupture de stock";
			}

			requete = "UPDATE ARTICLE SET STOCK='" + nouveauStock
					+ "',ETATARTICLE='" + etatArticle + "'"
					+ " WHERE IDARTICLE='" + idArticle + "'";

			System.out.println(requete);

			SGBD.executeUpdate(requete);

		}
	}

	/**
	 * M�thode qui calcule le montant d'une ligne de commande pour un client
	 * 
	 * 
	 * <p>
	 * Pour d�terminer le montant d'une ligne de commande, autrement le co�t
	 * d'un article multipl� par la quantit�, en tenant compte de l'�ventuelle
	 * r�duction d�e � un achat massif (promo d�gressive) ou une promotion
	 * exceptionnelle ; la m�thode passe par les �tapes suivantes :
	 * <ul>
	 * <li>La r�cup�ration du prix initial de l'article, pr�sent dans la table
	 * ARTICLE</li>
	 * <li>La r�cup�ration du pourcentage d�gressif �ventuel qui s'appliquerait
	 * � l'article</li>
	 * <li>La r�cup�ration du pourcentage de la promotion exceptionnelle
	 * �ventuelle qui s'appliquerait � l'article. Celle-ci peut ne concerner que
	 * les clients poss�dant une carte de fid�lit�, c'est pourquoi il est
	 * n�cessaire de pr�ciser si le client est fid�le ou non.</li>
	 * <li>La comparaison des deux pourcentages susnomm�s afin de n'appliquer
	 * que le plus avantageux</li>
	 * <li>Enfin, le calcul du montant final</li>
	 * </ul>
	 * </p>
	 * 
	 * @param idClient
	 *            L'identifiant du client qui a pass� la commande
	 * @param ligne
	 *            Une ligne de commande, compos�e d'un article et de la quantit�
	 *            command�e
	 * @param estFidele
	 *            Indique si le client poss�de une carte de fid�lit� (1) ou non
	 *            (0)
	 * 
	 * @return Le montant de la ligne de commande
	 * 
	 * @see basededonnees.BDD
	 * @see Article
	 * @see SGBD#selectStringConditionString(String, String, String, String)
	 * @see SGBD#recupererPourcentagePromotionDegressifArticleCommande(String,
	 *      String)
	 * @see SGBD#recupererPourcentagePromotionExceptionnelleArticle(String, int)
	 */
	public double montantCommandePourUnArticle(String idClient,
			LigneCommande ligne, int estFidele) throws SQLException {
		String pourcentagePromoExc = "0";
		String pourcentagePromoDegressif = "0";
		double montantArticle = 0;

		// R�cup�ration du prix initial et de la quantit� command�e
		String prixInit = SGBD.selectStringConditionString("ARTICLE",
				"PRIXINITIAL", "IDARTICLE", ligne.getIdArticle());
		double prixInitial = Double.parseDouble(prixInit);
		int quantiteCommandee = ligne.getQuantite();

		// R�cup�ration du pourcentage d�gressif
		pourcentagePromoDegressif = SGBD
				.recupererPourcentagePromotionDegressifArticleCommande(
						this.idCommande, ligne.getIdArticle());

		// R�cup�ration du pourcentage d'une �ventuelle promotion exceptionnelle
		pourcentagePromoExc = SGBD
				.recupererPourcentagePromotionExceptionnelleArticle(
						ligne.getIdArticle(), estFidele);

		// Comparaison des pourcentages d�gressif et promotion exceptionnelle
System.out.println("Promo Degressif : " + pourcentagePromoDegressif);
System.out.println("Promo Excep : " + pourcentagePromoExc);
		double promoAppliquee = Double.parseDouble(pourcentagePromoDegressif);
		if (Double.parseDouble(pourcentagePromoDegressif) < Double
				.parseDouble(pourcentagePromoExc)) {
			promoAppliquee = Double.parseDouble(pourcentagePromoExc);
		}
		System.out.println(promoAppliquee);

		// Calcul du montant final
		montantArticle = (Double) (quantiteCommandee * prixInitial * (1 - promoAppliquee / 100));
		System.out.println(montantArticle);

		return montantArticle;
	}

	/**
	 * M�thode qui calcule le montant d'une ligne de commande pour un client
	 * 
	 * 
	 * <p>
	 * Apr�s avoir r�cup�r� le bool�en indiquant si le client poss�de une carte
	 * de fid�lit�, la m�thode applique la m�thode montantCommandePourUnArticle
	 * � chacune des lignes de commande pr�sente dans le panier, et renvoie le
	 * montant total de la commande.
	 * </p>
	 * 
	 * @param panier
	 *            Le panier du client
	 * @param idClient
	 *            L'identifiant du client
	 * 
	 * @return Le montant total de la commande
	 * 
	 * @see basededonnees.BDD
	 * @see Article
	 * @see SGBD#recupererInformationFideliteClient(String)
	 * @see Commande#montantCommandePourUnArticle(String, LigneCommande, int)
	 */
	public double montantTotalArticle(ArrayList<LigneCommande> panier,
			String idClient) throws SQLException {
		double total = 0;

		// R�cup�ration du bool�en indiquant si le client poss�de une carte de
		// fid�lit�
		ArrayList<String> fideliteClient = SGBD
				.recupererInformationFideliteClient(idClient);
		String fidele = fideliteClient.get(0);
		int estFidele = 0;
		if (fidele.equals("Oui")) {
			estFidele = 1;
		}

		for (LigneCommande ligneCommande : panier) {
			double montantArticle = montantCommandePourUnArticle(idClient,
					ligneCommande, estFidele);
			total = total + montantArticle;
		}

		return total;
	}

}
