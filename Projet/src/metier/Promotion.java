package metier;

import java.sql.Date;
import java.util.ArrayList;

import basededonnees.SGBD;

public class Promotion {

	//promoFidelite pour sp�cifier qui est concern� par la promotion
	//true si pour les d�tenteurs de carte de fidelit�, false si pour tout le monde 
	
	public Promotion(String idPromotion, String nomPromotion, Date dateDebut,
			Date dateFin, double pourcentagePromo, boolean promoFidelite) {
		super();
		this.idPromotion = idPromotion;
		this.nomPromotion = nomPromotion;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.pourcentagePromo = pourcentagePromo;
		this.promoFidelite = promoFidelite;
		ajouterBDD();
	}
	
	

	private String idPromotion;
	private String nomPromotion;
	private Date dateDebut;
	private Date dateFin;
	private double pourcentagePromo;
	private boolean promoFidelite;
	
	public String getIdPromotion() {
		return idPromotion;
	}
	public void setIdPromotion(String idPromotion) {
		this.idPromotion = idPromotion;
	}
	public String getNomPromotion() {
		return nomPromotion;
	}
	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public double getPourcentagePromo() {
		return pourcentagePromo;
	}
	public void setPourcentagePromo(float pourcentagePromo) {
		this.pourcentagePromo = pourcentagePromo;
	}
	public boolean getPromoFidelite() {
		return promoFidelite;
	}
	public void setPromoFidelite(boolean promoFidelite) {
		this.promoFidelite = promoFidelite;
	}
	public void setPourcentagePromo(double pourcentagePromo) {
		this.pourcentagePromo = pourcentagePromo;
	}
	

//	Ajoute la promotion dans la base de donn�es et g�n�re un identifiant
	
	
	public void ajouterBDD() {
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_PROMOTION.NEXTVAL");
		String numPromo = "";
		int numeroPromo=0;
		numeroPromo=Integer.parseInt(idNonFini.get(0));
		
		if(numeroPromo<10){
			numPromo= "PRO0000" + numeroPromo;
		}
		if(numeroPromo<100 && numeroPromo>=10){
			numPromo= "PRO000" + numeroPromo;
		}
		if(numeroPromo<1000 && numeroPromo>=100){
			numPromo= "PRO00" + numeroPromo;
		}
		if(numeroPromo<10000 && numeroPromo>=1000){
			numPromo= "PRO0" + numeroPromo;
		}
		if(numeroPromo<100000 && numeroPromo>=10000){
			numPromo= "PRO" + numeroPromo;
		}
		
		this.setIdPromotion(numPromo);
		String s = SGBD.transformation(this.dateDebut);
		String s2 = SGBD.transformation(this.dateFin);
		int pf = 0;
		System.out.println(s + "  " + s2);
		
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
	
	
//	M�thode permettant de modifier la promotion dans la base de donn�es
	public static void modifierPromoBDD(String numPromo,String nomPromotion, Date dateDebut,Date dateFin, String pourcentagePromo,boolean promoAdherent){
		
		String dateD = SGBD.transformation(dateDebut);
		String dateF = SGBD.transformation(dateFin);
		int pf=0;
		if(promoAdherent==true){
			pf = 1;
		}
		
		String requete=" UPDATE PROMO" +
		   " SET NOMPROMO = '" + nomPromotion +"'," +
		   	"    DATEDEBUT = " + dateD +"," +
		   	"    DATEFIN= " + dateF + ", " +
		   	"    POURCENTAGEPROMO= " + pourcentagePromo + ", "+
		   	"	 PROMOFIDELITE="+ pf +""+
		   " WHERE IDPROMO='"+ numPromo+"'"
			;
		
		System.out.println(requete);
		SGBD.executeUpdate(requete);
	}

//	M�thode permettant de supprimer la promotion de la base de donn�es
	public static void supprimerPromoBDD(String idPromotion){
		
		// TODO Supprimer de la base toutes les lignes de LISTING PROMO ARTICLES
		// ayant pour attribut idPromotion
		
		
		String requete= "DELETE FROM PROMO WHERE IDPROMO='"+idPromotion+"'";
		
		SGBD.executeUpdate(requete);	
	}
	
//	M�thode v�rifiant si la date saisie est coh�rente
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
