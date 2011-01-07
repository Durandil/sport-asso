package metier;

import ihm.Gerant.FenetreFormulairePromotionsGerant;
import java.sql.Date;
import java.util.ArrayList;
import basededonnees.SGBD;

/**
 * <b>La classe Promotion représente une promotion.</b>
 * <p>
 * Une promotion est caractérisée par les informations suivantes (que l'on
 * retrouve dans la base de données) :
 * <ul>
 * <li>Un identifiant unique attribué définitivement, de la forme PROxxxxx</li>
 * <li>Un nom (une description)</li>
 * <li>Une date de début</li>
 * <li>Une date de fin</li>
 * <li>Le pourcentage de la promotion</li>
 * <li>Un booléen indiquant si la promotion ne s'applique qu'aux personnes
 * possédant une carte de fidélité</li>
 * </ul>
 * </p>
 * 
 * @see basededonnees.BDD
 */
public class Promotion {

	/**
	 * L'Identifiant de la promotion, non modifiable.
	 * 
	 * @see Promotion#getIdPromotion()
	 * @see Promotion#setIdPromotion(String)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#supprimerListing_PromoBDD(String)
	 * @see Promotion#supprimerPromoBDD(String)
	 */
	private String idPromotion;

	/**
	 * Le nom de la promotion.
	 * 
	 * @see Promotion#getNomPromotion()
	 * @see Promotion#setNomPromotion(String)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private String nomPromotion;

	/**
	 * La date de début de la promotion.
	 * 
	 * @see Promotion#getDateDebut()
	 * @see Promotion#setDateDebut(Date)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private Date dateDebut;

	/**
	 * La date de fin de la promotion.
	 * 
	 * @see Promotion#getDateFin()
	 * @see Promotion#setDateFin(Date)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private Date dateFin;

	/**
	 * Le pourcentage de la promotion.
	 * 
	 * @see Promotion#getPourcentagePromo()
	 * @see Promotion#setPourcentagePromo(double)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private double pourcentagePromo;

	/**
	 * Indique si la promotion ne s'applique qu'aux clients fidèles ou à
	 * l'ensemble des clients.
	 * 
	 * @see Promotion#getPromoFidelite()
	 * @see Promotion#setPromoFidelite(boolean)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private boolean promoFidelite;

	/**
	 * Constructeur de la classe Promotion.
	 * <p>
	 * Le constructeur de la classe Promotion fait appel à la méthode
	 * ajouterBDD() qui ajoute la promotion dans la base de données.
	 * </p>
	 * 
	 * @param nomPromotion
	 *            Le nom de la promotion.
	 * @param dateDebut
	 *            La date de début de la promotion.
	 * @param dateFin
	 *            La date de fin de la promotion.
	 * @param pourcentagePromo
	 *            Le pourcentage de la promotion
	 * @param promoFidelite
	 *            Indique si la promotion ne s'applique qu'aux clients fidèles
	 *            ou à l'ensemble des clients
	 * 
	 * @see Promotion#nomPromotion
	 * @see Promotion#dateDebut
	 * @see Promotion#dateFin
	 * @see Promotion#pourcentagePromo
	 * @see Promotion#promoFidelite
	 * @see Promotion#ajouterBDD()
	 */
	public Promotion(String nomPromotion, Date dateDebut, Date dateFin,
			double pourcentagePromo, boolean promoFidelite) {
		super();

		this.nomPromotion = nomPromotion;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.pourcentagePromo = pourcentagePromo;
		this.promoFidelite = promoFidelite;
		ajouterBDD();
	}

	/**
	 * Retourne l'id de la promotion
	 * 
	 * @return L'identifiant de la promotion
	 * 
	 */
	public String getIdPromotion() {
		return idPromotion;
	}

	/**
	 * Met à jour l'id de la promotion
	 * 
	 * @param idPromotion
	 *            L'identifiant unique de la promotion
	 * 
	 */
	public void setIdPromotion(String idPromotion) {
		this.idPromotion = idPromotion;
	}

	/**
	 * Retourne le nom de la promotion
	 * 
	 * @return Le nom de la promotion
	 * 
	 */
	public String getNomPromotion() {
		return nomPromotion;
	}

	/**
	 * Met à jour le nom de la promotion
	 * 
	 * @param nomPromotion
	 *            Le nom de la promotion
	 * 
	 */
	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	/**
	 * Retourne la date de début de la promotion
	 * 
	 * @return La date de début de la promotion
	 * 
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * Met à jour la date de début de la promotion
	 * 
	 * @param dateDebut
	 *            La date de début de la promotion
	 * 
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Retourne la date de fin de la promotion
	 * 
	 * @return La date de fin de la promotion
	 * 
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * Met à jour la date de fin de la promotion
	 * 
	 * @param dateFin
	 *            La date de fin de la promotion
	 * 
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Retourne le pourcentage de la promotion
	 * 
	 * @return Le pourcentage de la promotion
	 * 
	 */
	public double getPourcentagePromo() {
		return pourcentagePromo;
	}

	/**
	 * Met à jour le pourcentage de la promotion
	 * 
	 * @param pourcentagePromo
	 *            Le pourcentage de la promotion
	 * 
	 */
	public void setPourcentagePromo(double pourcentagePromo) {
		this.pourcentagePromo = pourcentagePromo;
	}

	/**
	 * Retourne le booléen indiquant si la promotion ne s'applique qu'aux
	 * clients possédant une carte de fidélité
	 * 
	 * @return Le booléen indiquant si la promotion ne s'applique qu'aux clients
	 *         possédant une carte de fidélité
	 * 
	 */
	public boolean getPromoFidelite() {
		return promoFidelite;
	}

	/**
	 * Met à jour le booléen indiquant si la promotion ne s'applique qu'aux
	 * clients possédant une carte de fidélité
	 * 
	 * @param promoFidelite
	 *            Le booléen indiquant si la promotion ne s'applique qu'aux
	 *            clients possédant une carte de fidélité
	 * 
	 */
	public void setPromoFidelite(boolean promoFidelite) {
		this.promoFidelite = promoFidelite;
	}

	/**
	 * Ajoute la promotion dans la table PROMO de la base de données.
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table
	 * afin de générer l'identifiant de la promotion dans le format approprié.<br>
	 * Ensuite, la méthode génère deux chaînes de caractères à partir des dates
	 * ainsi qu'un entier qui vaut 0 si la promotion concerne l'ensemble des
	 * clients et 1 sinon.<br>
	 * La requête se construit ensuite en fonction des caractéristiques de la
	 * promotion saisies lors de l'appel du constructeur.
	 * 
	 * </p>
	 * 
	 * @see Promotion#Promotion(String, Date, Date, double, boolean)
	 * @see SGBD#transformation(Date)
	 * @see basededonnees.BDD
	 */
	public void ajouterBDD() {
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
				"S_PROMOTION.NEXTVAL");

		int numeroPromo = 0;
		numeroPromo = Integer.parseInt(idNonFini.get(0));

		if (numeroPromo < 10) {
			this.idPromotion = "PRO0000" + numeroPromo;
		}
		if (numeroPromo < 100 && numeroPromo >= 10) {
			this.idPromotion = "PRO000" + numeroPromo;
		}
		if (numeroPromo < 1000 && numeroPromo >= 100) {
			this.idPromotion = "PRO00" + numeroPromo;
		}
		if (numeroPromo < 10000 && numeroPromo >= 1000) {
			this.idPromotion = "PRO0" + numeroPromo;
		}
		if (numeroPromo < 100000 && numeroPromo >= 10000) {
			this.idPromotion = "PRO" + numeroPromo;
		}

		String s = SGBD.transformation(this.dateDebut);
		String s2 = SGBD.transformation(this.dateFin);
		int pf = 0;

		if (this.promoFidelite == true) {
			pf = 1;
		}

		String requete = "INSERT INTO PROMO (IDPROMO, NOMPROMO, DATEDEBUT,"
				+ " DATEFIN, POURCENTAGEPROMO, PROMOFIDELITE) VALUES ( " + "'"
				+ this.idPromotion + "'," + "'"
				+ this.nomPromotion.replaceAll("'", "''") + "'," + s + "," + s2
				+ "," + this.pourcentagePromo + "," + pf + ")";

		System.out.println(requete);
		SGBD.executeUpdate(requete);
	}

	/**
	 * Modifie une promotion qui est déjà présente dans la table PROMO de la
	 * base de données.
	 * 
	 * <p>
	 * Similairement à la méthode ajouterBDD(), cette méthode commence par
	 * transformer les dates en chaînes de caractères puis crée un nouvel entier
	 * en fonction de la valeur du booléen promoFidele. <br>
	 * La requête se construit ensutie en fonction des autres caractéristiques
	 * de la promotion saisies lors de l'appel de la méthode.
	 * </p>
	 * 
	 * @param idPromo
	 *            L'identifiant unique de la promotion.
	 * @param nomPromotion
	 *            Le nom de la promotion.
	 * @param dateDebut
	 *            La date de début de la promotion
	 * @param dateFin
	 *            La date de fin de la promotion
	 * @param pourcentagePromo
	 *            Le pourcentage de la promotion
	 * @param promoFidele
	 *            L'identifiant de la catégorie de sport de l'article.
	 * 
	 * @see Promotion#ajouterBDD()
	 * @see SGBD#transformation(Date)
	 * @see basededonnees.BDD
	 */
	public static void modifierPromoBDD(String idPromo, String nomPromotion,
			Date dateDebut, Date dateFin, String pourcentagePromo,
			boolean promoFidele) {

		String dateD = SGBD.transformation(dateDebut);
		String dateF = SGBD.transformation(dateFin);
		int pf = 0;
		if (promoFidele == true) {
			pf = 1;
		}

		String requete = " UPDATE PROMO" + " SET NOMPROMO = '"
				+ nomPromotion.replaceAll("'", "''") + "',"
				+ "    DATEDEBUT = " + dateD + "," + "    DATEFIN= " + dateF
				+ ", " + "    POURCENTAGEPROMO= " + pourcentagePromo + ", "
				+ "	 PROMOFIDELITE=" + pf + "" + " WHERE IDPROMO='" + idPromo
				+ "'";

		SGBD.executeUpdate(requete);
	}

	/**
	 * Supprime toutes les lignes de la table LISTING_PROMOS_ARTICLES où
	 * l'identifiant de la promotion est identique à celui précisé en paramètre.
	 * 
	 * @param idPromotion
	 *            L'identifiant unique de la promotion.
	 * 
	 * @see basededonnees.BDD
	 */
	public static void supprimerListing_PromoBDD(String idPromotion) {

		String requete = "DELETE FROM LISTING_PROMOS_ARTICLES WHERE IDPROMO='"
				+ idPromotion + "'";

		SGBD.executeUpdate(requete);
	}

	/**
	 * Supprime la promotion de la table PROMO dont l'identifiant est spécifié
	 * en paramètre.
	 * 
	 * @param idPromotion
	 *            L'identifiant unique de la promotion.
	 * 
	 * @see basededonnees.BDD
	 */
	public static void supprimerPromoBDD(String idPromotion) {

		String requete = "DELETE FROM PROMO WHERE IDPROMO='" + idPromotion
				+ "'";

		SGBD.executeUpdate(requete);
	}

}
