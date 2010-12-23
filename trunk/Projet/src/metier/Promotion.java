package metier;

import ihm.Gerant.FenetreFormulairePromotionsGerant;
import java.sql.Date;
import java.util.ArrayList;
import basededonnees.SGBD;

/**
 * <b>La classe Promotion représente une promotion</b>
 * <p>
 * Une promotion est caractérisée par les informations suivantes (que l'on
 * retrouve dans la base de données) :
 * <ul>
 * <li>Un identifiant unique attribué définitivement, de la forme PROxxxxx</li>
 * <li>Un nom (une description)</li>
 * <li>Une date de début</li>
 * <li>Une date de fin</li>
 * <li>Le pourcentage de promotion (Plus exactement le pourcentage appliqué au
 * prix initial : "80" pour une réduction de 20%)</li>
 * <li>Un booléen indiquant si la promotion ne s'applique qu'aux personnes
 * possédant une carte de fidélité</li>
 * </ul>
 * </p>
 * 
 * @see basededonnees.BDD
 */
public class Promotion {

	// promoFidelite pour spécifier qui est concerné par la promotion
	// true si pour les détenteurs de carte de fidelité, false si pour tout le
	// monde

	/**
	 * L'Identifiant de la promotion, non modifiable
	 * 
	 * @see Promotion#getIdPromotion()
	 * @see Promotion#setIdPromotion(String)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#supprimerListing_PromoBDD(String)
	 * @see Promotion#supprimerPromoBDD(String)
	 */
	private String idPromotion;

	/**
	 * Le nom de la promotion
	 * 
	 * @see Promotion#getNomPromotion()
	 * @see Promotion#setNomPromotion(String)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private String nomPromotion;

	/**
	 * La date de début de la promotion
	 * 
	 * @see Promotion#getDateDebut()
	 * @see Promotion#setDateDebut(Date)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private Date dateDebut;

	/**
	 * La date de fin de la promotion
	 * 
	 * @see Promotion#getDateFin()
	 * @see Promotion#setDateFin(Date)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private Date dateFin;

	/**
	 * Le pourcentage de la promotion, ou plus exactement le pourcentage
	 * appliqué au prix initial : "80" pour une réduction de 20%
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
	 * l'ensemble des clients
	 * 
	 * @see Promotion#getPromoFidelite()
	 * @see Promotion#setPromoFidelite(boolean)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String,
	 *      boolean)
	 */
	private boolean promoFidelite;

	/**
	 * Constructeur de la classe Promotion
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
	 * Ajoute la promotion dans la table PROMO de la base de données
	 * 
	 * <p>
	 * Cette méthode commence par récupérer l'indice de séquence de la table
	 * afin de générer l'identifiant de la promotion dans le format approprié.
	 * Ensuite, la méthode génère deux chaînes de caractères à partir des dates
	 * ainsi qu'un entier qui vaut 0 si la promotion concerne l'ensemble des
	 * clients et 1 sinon. La requête se construit ensuite en fonction des
	 * caractéristiques de la promotion saisies lors de l'appel du constructeur.
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
	 * Modifie une promotion qui est déjà présent dans la table PROMO de la base
	 * de données
	 * 
	 * <p>
	 * Similairement à la méthode ajouterBDD(), cette méthode commence par
	 * transformer les dates en chaînes de caractères puis crée un nouvel entier
	 * en fonction de la valeur du booléen promoFidele La requête se construit
	 * ensutie en fonction des autres caractéristiques de la promotion saisies
	 * lors de l'appel de la méthode
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
	 * l'identifiant de la promotion est identique à celui précisé en paramètre
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
	 * en paramètre
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

	// /**
	// * Vérifie si la date saisie est cohérente (à travers l'année, le mois et
	// le
	// * jour entrés en paramètres) Pour cela, la fonction procède étape par
	// étape
	// * <p>
	// * <ul>
	// * <li>La méthode suppose à l'origine que la date est cohérente.</li>
	// * <li>Elle crée une Date correspondant aux paramètres saisis.</li>
	// * <li>Les paramètres sont ensuite transformés en entiers.</li>
	// * <li>Une nouvelle instance de Date est créée, dateJour, qui correspond à
	// * la date actuelle</li>
	// * <li>Par la suite, trois tests sont effectués :</li>
	// * <ul>
	// * <li>Le premier vérifie que la date de la promotion précède la date du
	// * jour</li>
	// * <li>Le second vérifie que le jour de la date n'est pas 31 si le mois
	// * comporte 30 jours
	// * <li>Le dernier vérifie que le mois de février comporte 28 jours au plus
	// * </ul>
	// * </ul> Si au moins l'un de ces tests est faux alors la fonction renvoie
	// la
	// * valeur "faux". Si tous les tests sont réussis, la valeur renvoyée par
	// la
	// * fonction est "vrai".
	// * </p>
	// *
	// * @param annee
	// * L'année de la date
	// * @param mois
	// * Le mois de la date
	// * @param jour
	// * Le jour de la date
	// *
	// * @return Si la date est cohérente
	// *
	// * @see Promotion#ajouterBDD()
	// * @see SGBD#transformation(Date)
	// * @see Promotion#verifierChampPromotion(String, String, String, String,
	// * String, String, String, String)
	// * @see basededonnees.BDD
	// */
	// public static boolean verifierDatePromotion(String annee, String mois,
	// String jour) throws Exception {
	// boolean resultat = true;
	// String dateP = jour + mois + annee;
	// Date datePromotion = SGBD.stringToDate(dateP, "ddMMyyyy");
	// int moisDate = Integer.parseInt(mois);
	// int jourDate = Integer.parseInt(jour);
	// @SuppressWarnings("unused")
	// int anneeDate = Integer.parseInt(annee);
	//
	// Date dateJour = new Date(System.currentTimeMillis());
	//
	// System.out.println(dateJour.toString());
	// System.out.println(datePromotion.toString());
	// System.out.println(datePromotion.compareTo(dateJour));
	//
	// if (datePromotion.before(dateJour)) {
	// resultat = false;
	// System.out.println("date avant aujourd'hui");
	// }
	//
	// if ((moisDate == 4 | moisDate == 6 | moisDate == 9 | moisDate == 11)
	// & jourDate == 31) {
	// resultat = false;
	// System.out
	// .println("Problème du 31 des mois avril, juin, septembre et novembre");
	// }
	//
	// if (moisDate == 2 & jourDate > 28) {
	// resultat = false;
	// System.out.println("problème du mois de février");
	// }
	//
	// return resultat;
	// }
	//
	// /**
	// * Vérifie si la première date précède la seconde.
	// * <p>
	// * La fonction concatène les paramètres pour former deux dates sous format
	// * String Ces String sont ensuite convertis en Date. La méthode vérifie si
	// * la première date est avant la seconde, auquel cas le booléen retourné
	// * sera "vrai" et "faux" sinon.
	// * </p>
	// *
	// * @param anneeAvant
	// * L'année de la première date
	// * @param moisAvant
	// * Le mois de la première date
	// * @param jourAvant
	// * Le jour de la première date
	// * @param anneeApres
	// * L'année de la seconde date
	// * @param moisApres
	// * Le mois de la seconde date
	// * @param jourApres
	// * Le jour de la seconde date
	// *
	// * @return Si la première date précède bien la seconde
	// *
	// * @see Promotion#ajouterBDD()
	// * @see SGBD#transformation(Date)
	// * @see basededonnees.BDD
	// * @see Promotion#verifierChampPromotion(String, String, String, String,
	// * String, String, String, String)
	// */
	// public static boolean verifierOrdreDeuxDate(String anneeAvant,
	// String moisAvant, String jourAvant, String anneeApres,
	// String moisApres, String jourApres) throws Exception {
	//
	// boolean resultat = true;
	//
	// String dateP = jourAvant + moisAvant + anneeAvant;
	// Date datePromotionAvant = SGBD.stringToDate(dateP, "ddMMyyyy");
	//
	// String datePApres = jourApres + moisApres + anneeApres;
	// Date datePromotionApres = SGBD.stringToDate(datePApres, "ddMMyyyy");
	//
	// System.out.println(datePromotionAvant.toString());
	// System.out.println(datePromotionApres.toString());
	//
	// if (datePromotionAvant.after(datePromotionApres)) {
	// resultat = false;
	// }
	//
	// return resultat;
	// }
	//
	// /**
	// * TODO : Devenu inutile
	// *
	// * Vérifie si les champs saisis sont conformes aux formats attendus
	// * <p>
	// * Cette méthode effectue des tests pour vérifier que les champs saisis
	// sont
	// * conformes. Pour ce faire, elle commence par faire appel aux fonctions
	// * verifierDatePromotion et verifierOrdreDeuxDates. Si les booléens
	// obtenus
	// * sont faux alors l'entier renvoyé sera différent de 0.<br>
	// * D'autre part, si la longueur de la description ou celle du pourcentage
	// * est égale à 0, l'entier passe à 3.<br>
	// * Ensuite, si le pourcentage est incohérent, inférieur à 0 ou supérieur à
	// * 100, l'entier renvoyé sera égal à 4.<br>
	// * Enfin, si la longueur de la description dépasse 40 caractères, alors
	// * l'entier renvoyé sera égal à 5.<br>
	// * Si tous les champs sont conformes alors l'entier sera égal à zéro. <br>
	// * <br>
	// * <b>Note : </b> L'utilisation d'un entier et non d'un booléen pour cette
	// * fonction provient du fait que selon l'erreur relevée, le message qui
	// sera
	// * affiché à l'écran sera différent.
	// *
	// * </p>
	// *
	// * @param anneeAvant
	// * L'année de la première date
	// * @param moisAvant
	// * Le mois de la première date
	// * @param jourAvant
	// * Le jour de la première date
	// * @param anneeApres
	// * L'année de la seconde date
	// * @param moisApres
	// * Le mois de la seconde date
	// * @param jourApres
	// * Le jour de la seconde date
	// * @param description
	// * La description de la promotion
	// * @param pourcentagePromotion
	// * Le pourcentage de la promotion
	// *
	// * @return Un entier qui est différent de 0 si les champs ne sont pas au
	// * format approprié
	// *
	// * @see Promotion#verifierOrdreDeuxDate(String, String, String, String,
	// * String, String)
	// * @see Promotion#verifierDatePromotion(String, String, String)
	// * @see Promotion#ajouterBDD()
	// * @see SGBD#transformation(Date)
	// * @see basededonnees.BDD
	// * @see FenetreFormulairePromotionsGerant
	// */
	// public static int verifierChampPromotion(String anneeAvant,
	// String moisAvant, String jourAvant, String anneeApres,
	// String moisApres, String jourApres, String description,
	// String pourcentagePromotion) {
	//
	// int champCorrect = 0;
	//
	// try {
	// boolean dateDebutPossible = Promotion.verifierDatePromotion(
	// anneeAvant, moisAvant, jourAvant);
	// boolean dateFinPossible = Promotion.verifierDatePromotion(
	// anneeApres, moisApres, jourApres);
	// boolean comparaisonDeuxDates;
	// comparaisonDeuxDates = Promotion.verifierOrdreDeuxDate(anneeAvant,
	// moisAvant, jourAvant, anneeApres, moisApres, jourApres);
	//
	// if (dateDebutPossible == false | dateFinPossible == false) {
	// champCorrect = 1;
	// }
	// if (dateDebutPossible == true & dateFinPossible == true
	// & comparaisonDeuxDates == false) {
	// champCorrect = 2;
	// }
	// if (description.length() == 0 | pourcentagePromotion.length() == 0) {
	// champCorrect = 3;
	// }
	//
	// int pourcentage = Integer.parseInt(pourcentagePromotion);
	//
	// if (pourcentage < 0 | pourcentage > 100) {
	// champCorrect = 4;
	// }
	// if (description.length() > 40) {
	// champCorrect = 5;
	// }
	// } catch (Exception ex) {
	// System.out.println(ex.getMessage());
	// }
	//
	// return champCorrect;
	// }

}
