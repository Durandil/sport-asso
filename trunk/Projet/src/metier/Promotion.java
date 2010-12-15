package metier;

import java.sql.Date;
import java.util.ArrayList;
import basededonnees.SGBD;

/**
 * <b>La classe Promotion repr�sente une promotion</b>
 * <p>
 * Une promotion est caract�ris�e par les informations suivantes (que l'on retrouve
 * dans la base de donn�es) :
 * <ul>
 * <li>Un identifiant unique attribu� d�finitivement, de la forme PROxxxxx</li>
 * <li>Un nom (une description)</li>
 * <li>Une date de d�but</li>
 * <li>Une date de fin</li>
 * <li>Le pourcentage de promotion (Plus exactement le pourcentage appliqu� au prix initial : "80" pour une r�duction de 20%)</li>
 * <li>Un bool�en indiquant si la promotion ne s'applique qu'aux personnes poss�dant une carte de fid�lit�</li>
 * </ul>
 * </p>
 * 
 * @see BDD
 */
public class Promotion {

	//promoFidelite pour sp�cifier qui est concern� par la promotion
	//true si pour les d�tenteurs de carte de fidelit�, false si pour tout le monde 
	
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
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String, boolean)
	 */
	private String nomPromotion;
	
	
	/**
	 * La date de d�but de la promotion
	 * 
	 * @see Promotion#getDateDebut()
	 * @see Promotion#setDateDebut(Date)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String, boolean)
	 * @see Promotion#verifierChampPromotion(String, String, String, String, String, String, String, String)
	 * @see Promotion#verifierDatePromotion(String, String, String)
	 * @see Promotion#verifierOrdreDeuxDate(String, String, String, String, String, String)
	 */
	private Date dateDebut;
	
	/**
	 * La date de fin de la promotion
	 * 
	 * @see Promotion#getDateFin()
	 * @see Promotion#setDateFin(Date)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String, boolean)
	 * @see Promotion#verifierChampPromotion(String, String, String, String, String, String, String, String)
	 * @see Promotion#verifierDatePromotion(String, String, String)
	 * @see Promotion#verifierOrdreDeuxDate(String, String, String, String, String, String)
	 */
	private Date dateFin;
	
	/**
	 * Le pourcentage de la promotion, ou plus exactement le pourcentage 
	 * appliqu� au prix initial : "80" pour une r�duction de 20%
	 * 
	 * @see Promotion#getPourcentagePromo()
	 * @see Promotion#setPourcentagePromo(double)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String, boolean)
	 */
	private double pourcentagePromo;
	
	/**
	 * Indique si la promotion ne s'applique qu'aux clients fid�les ou � l'ensemble des clients
	 * 
	 * @see Promotion#getPromoFidelite()
	 * @see Promotion#setPromoFidelite(boolean)
	 * @see Promotion#ajouterBDD()
	 * @see Promotion#modifierPromoBDD(String, String, Date, Date, String, boolean)
	 */
	private boolean promoFidelite;
	
	/**
	 * Constructeur de la classe Promotion
	 * <p>
	 * Le constructeur de la classe Promotion fait appel � la m�thode ajouterBDD()
	 * qui ajoute la promotion dans la base de donn�es.
	 * </p>
	 * 
	 * @param nomPromotion
	 *            Le nom de la promotion.
	 * @param dateDebut
	 *            La date de d�but de la promotion.
	 * @param dateFin
	 *            La date de fin de la promotion.
	 * @param pourcentagePromo
	 *            Le pourcentage de la promotion
	 * @param promoFidelite
	 *            Indique si la promotion ne s'applique qu'aux clients fid�les ou � l'ensemble des clients
	 *            
	 * @see Promotion#nomPromotion
	 * @see Promotion#dateDebut
	 * @see Promotion#dateFin
	 * @see Promotion#pourcentagePromo
	 * @see Promotion#promoFidelite
	 * @see Promotion#ajouterBDD()
	 */
	public Promotion(String nomPromotion, Date dateDebut,
			Date dateFin, double pourcentagePromo, boolean promoFidelite) {
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
     * Met � jour l'id de la promotion
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
     * Met � jour le nom de la promotion
     * 
     * @param nomPromotion
     *            Le nom de la promotion
     * 
     */
	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}
	
	 /**
     * Retourne la date de d�but de la promotion
     * 
     * @return La date de d�but de la promotion
     * 
     */
	public Date getDateDebut() {
		return dateDebut;
	}
	
	 /**
     * Met � jour la date de d�but de la promotion
     * 
     * @param dateDebut
     *            La date de d�but de la promotion
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
     * Met � jour la date de fin de la promotion
     * 
     * @param dateDebut
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
     * Met � jour le pourcentage de la promotion
     * 
     * @param pourcentagePromo
     *            Le pourcentage de la promotion
     * 
     */
	public void setPourcentagePromo(double pourcentagePromo) {
		this.pourcentagePromo = pourcentagePromo;
	}
	
	 /**
     * Retourne le bool�en indiquant si la promotion ne s'applique qu'aux clients poss�dant une carte de fid�lit�
     * 
     * @return Le bool�en indiquant si la promotion ne s'applique qu'aux clients poss�dant une carte de fid�lit�
     * 
     */
	public boolean getPromoFidelite() {
		return promoFidelite;
	}
	
	 /**
     * Met � jour le bool�en indiquant si la promotion ne s'applique qu'aux clients poss�dant une carte de fid�lit�
     * 
     * @param promoFidelite
     *            Le bool�en indiquant si la promotion ne s'applique qu'aux clients poss�dant une carte de fid�lit�
     * 
     */
	public void setPromoFidelite(boolean promoFidelite) {
		this.promoFidelite = promoFidelite;
	}

	


	/**
	 * Ajoute la promotion dans la table PROMO de la base de donn�es
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer l'indice de s�quence de la table afin
	 * de g�n�rer l'identifiant de la promotion dans le format appropri�.
	 * Ensuite, la m�thode g�n�re deux cha�nes de caract�res � partir des dates 
	 * ainsi qu'un entier qui vaut 0 si la promotion concerne l'ensemble des clients 
	 * et 1 sinon. 
	 * La requ�te se construit ensuite en fonction des caract�ristiques de la promotion
	 * saisies lors de l'appel du constructeur.
	 * 
	 * </p> 
	 * 
	 * @see Promotion#Promotion(String, Date, Date, double, boolean)
	 * @see SGBD#transformation(Date)
	 * @see BDD
	 */
	public void ajouterBDD() {
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_PROMOTION.NEXTVAL");

		int numeroPromo=0;
		numeroPromo=Integer.parseInt(idNonFini.get(0));
		
		if(numeroPromo<10){
			this.idPromotion= "PRO0000" + numeroPromo;
		}
		if(numeroPromo<100 && numeroPromo>=10){
			this.idPromotion= "PRO000" + numeroPromo;
		}
		if(numeroPromo<1000 && numeroPromo>=100){
			this.idPromotion= "PRO00" + numeroPromo;
		}
		if(numeroPromo<10000 && numeroPromo>=1000){
			this.idPromotion= "PRO0" + numeroPromo;
		}
		if(numeroPromo<100000 && numeroPromo>=10000){
			this.idPromotion= "PRO" + numeroPromo;
		}
		
		String s = SGBD.transformation(this.dateDebut);
		String s2 = SGBD.transformation(this.dateFin);
		int pf = 0;
		
		if(this.promoFidelite==true){
			pf = 1;
		}
		
		String requete = "INSERT INTO PROMO (IDPROMO, NOMPROMO, DATEDEBUT," +
		" DATEFIN, POURCENTAGEPROMO, PROMOFIDELITE) VALUES ( "
		+ "'"
		+ this.idPromotion
		+ "',"
		+ "'"
		+ this.nomPromotion
		+ "',"
		+ s 
		+ "," 
		+ s2
		+ "," 
		+ this.pourcentagePromo
		+ ","
		+ pf
		+")";
		System.out.println(requete);
		SGBD.executeUpdate(requete);
	}
	
	
	/**
	 * Modifie une promotion qui est d�j� pr�sent dans la table PROMO de la base de donn�es
	 * 
	 * <p>
	 * Similairement � la m�thode ajouterBDD(), cette m�thode commence par 
	 * transformer les dates en cha�nes de caract�res puis cr�e un nouvel entier
	 * en fonction de la valeur du bool�en promoFidele
	 * La requ�te se construit ensutie en fonction des autres caract�ristiques 
	 * de la promotion saisies lors de l'appel de la m�thode
	 * </p> 
	 * 
	 * @param idPromo
	 *            L'identifiant unique de la promotion.
	 * @param nomPromotion
	 *            Le nom de la promotion.
	 * @param dateDebut
	 *            La date de d�but de la promotion
	 * @param dateFin
	 *            La date de fin de la promotion
	 * @param pourcentagePromo
	 *            Le pourcentage de la promotion
	 * @param promoFidele
	 *            L'identifiant de la cat�gorie de sport de l'article.
	 * 
	 * @see Promotion#ajouterBDD()
	 * @see SGBD#transformation(Date)
	 * @see BDD
	 */
	public static void modifierPromoBDD(String idPromo,String nomPromotion, Date dateDebut,Date dateFin, String pourcentagePromo,boolean promoFidele){
		
		String dateD = SGBD.transformation(dateDebut);
		String dateF = SGBD.transformation(dateFin);
		int pf=0;
		if(promoFidele==true){
			pf = 1;
		}
		
		String requete=" UPDATE PROMO" +
		   " SET NOMPROMO = '" + nomPromotion +"'," +
		   	"    DATEDEBUT = " + dateD +"," +
		   	"    DATEFIN= " + dateF + ", " +
		   	"    POURCENTAGEPROMO= " + pourcentagePromo + ", "+
		   	"	 PROMOFIDELITE="+ pf +""+
		   " WHERE IDPROMO='"+ idPromo+"'"
			;
		
		SGBD.executeUpdate(requete);
	}

	/**
	 * Supprime toutes les lignes de la table LISTING_PROMOS_ARTICLES 
	 * o� l'identifiant de la promotion est identique � celui pr�cis� en param�tre
	 * 
	 * @param idPromo
	 *            L'identifiant unique de la promotion.
	 *            
	 * @see BDD
	 */
	public static void supprimerListing_PromoBDD(String idPromotion){
		
		
		
		String requete= "DELETE FROM LISTING_PROMOS_ARTICLES WHERE IDPROMO='"+idPromotion+"'";
		
		SGBD.executeUpdate(requete);	
	}	
	
	/**
	 * Supprime la promotion de la table PROMO dont l'identifiant est
	 * sp�cifi� en param�tre
	 * 
	 * @param idPromo
	 *            L'identifiant unique de la promotion.
	 *            
	 * @see BDD
	 */
	public static void supprimerPromoBDD(String idPromotion){
		
		String requete= "DELETE FROM PROMO WHERE IDPROMO='"+idPromotion+"'";
		
		SGBD.executeUpdate(requete);	
	}
	
	/**
	 * V�rifie si la date saisie est coh�rente (� travers l'ann�e, le mois et le jour entr�s en param�tres)
	 * Pour cela, la fonction proc�de �tape par �tape
	 * <p>
	 * La m�thode suppose � l'origine que la date est coh�rente.
	 * Elle cr�e une Date correspondant aux param�tres saisis.
	 * Les param�tres sont ensuite transform�s en entiers.
	 * Une nouvelle instance de Date est cr��e, dateJour, qui correspond � la date actuelle
	 * Par la suite, trois tests sont effectu�s
	 * <ul>
	 * <li>Le premier v�rifie que la date de la promotion pr�c�de la date du jour</li>
	 * <li>Le second v�rifie que le jour de la date n'est pas 31 si le mois comporte 30 jours
	 * <li>Le dernier v�rifie que le mois de f�vrier comporte 28 jours au plus
	 * </ul>
	 * Si au moins l'un de ces tests est faux alors la fonction renvoie la valeur "faux".
	 * Si tous les tests sont r�ussis, la valeur renvoy�e par la fonction est "vrai".
	 * </p> 
	 * 
	 * @param annee
	 *            L'ann�e de la date
	 * @param mois
	 *            Le mois de la date
	 * @param jour
	 *            Le jour de la date
	 * 
	 * @see Promotion#ajouterBDD()
	 * @see SGBD#transformation(Date)
	 * @see BDD
	 */
	public static boolean verifierDatePromotion(String annee,String mois,String jour) throws Exception{
		boolean resultat=true;
		String dateP = jour+mois+annee;
		Date datePromotion= SGBD.stringToDate(dateP,"ddMMyyyy");
		int moisDate=Integer.parseInt(mois);
		int jourDate=Integer.parseInt(jour);
		int anneeDate=Integer.parseInt(annee);
		
		Date dateJour = new Date(System.currentTimeMillis());
		
		System.out.println(dateJour.toString());
		System.out.println(datePromotion.toString());
		System.out.println(datePromotion.compareTo(dateJour));
		
		if(datePromotion.before(dateJour)){
			resultat=false;
			System.out.println("date avant aujourd'hui");
		}
		
		if((moisDate==4 | moisDate==6 | moisDate==9 | moisDate==11) & jourDate==31){
			resultat=false;
			System.out.println("Probl�me du 31 des mois avril, juin, septembre et novembre");
		}
		
		if(moisDate==2 & jourDate>28){
			resultat=false;
			System.out.println("probl�me du mois de f�vrier");
		}
		
		return resultat;
	}
	
//	M�thode v�rifiant si la 1�re date entr�e en param�tre pr�c�de bien celle entr�e en 2�me param�tre
	public static boolean verifierOrdreDeuxDate(String anneeAvant,String moisAvant,String jourAvant,String anneeApres,String moisApres,String jourApres) throws Exception{
		boolean resultat=true;
		

		String dateP = jourAvant+moisAvant+anneeAvant;
		Date datePromotionAvant= SGBD.stringToDate(dateP,"ddMMyyyy");
		
		String datePAfter = jourApres+moisApres+anneeApres;
		Date datePromotionApres = SGBD.stringToDate(datePAfter,"ddMMyyyy");
		
		System.out.println(datePromotionAvant.toString());
		System.out.println(datePromotionApres.toString());
	
		if(datePromotionAvant.after(datePromotionApres)){
			resultat=false;
		}
		
		
		return resultat;
	}
	
	//V�rifie si tous les champs sont corrects
	public static int verifierChampPromotion(String anneeAvant,String moisAvant,String jourAvant,
			String anneeApres,String moisApres,String jourApres,String description,
			String pourcentagePromotion) {
		
		int champCorrect=0;
		
		try{
			boolean dateDebutPossible = Promotion.verifierDatePromotion(anneeAvant, moisAvant, jourAvant);
			boolean dateFinPossible=Promotion.verifierDatePromotion(anneeApres, moisApres, jourApres);
			boolean comparaisonDeuxDates;		
			comparaisonDeuxDates = Promotion.verifierOrdreDeuxDate(anneeAvant, moisAvant, jourAvant, anneeApres, moisApres, jourApres);
			
			if(dateDebutPossible==false | dateFinPossible == false){
				champCorrect = 1 ;
			}
			if(dateDebutPossible==true & dateFinPossible==true & comparaisonDeuxDates == false){
				champCorrect = 2 ;
			}
			if(description.length()==0 | pourcentagePromotion.length()==0){
				champCorrect = 3 ;
			}
			
			int pourcentage = Integer.parseInt(pourcentagePromotion);
			
			if(pourcentage<0 | pourcentage>100){
				champCorrect = 4 ;
			}
			if(description.length()>40){
				champCorrect=5;
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return champCorrect ; 
	}
	
}
