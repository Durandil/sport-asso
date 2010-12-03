package metier;

import java.sql.Date;
import java.util.ArrayList;

import basededonnees.SGBD;

public class Promotion {

	//promoFidelite pour spécifier qui est concerné par la promotion
	//true si pour les détenteurs de carte de fidelité, false si pour tout le monde 
	
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
	
	public void ajouterBDD() {
		
//		compteurIdentifiantMessage = compteurIdentifiantMessage+1;
//		System.out.println(compteurIdentifiantMessage);
//		String idMessage=""+compteurIdentifiantMessage;
		
		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL", "S_PROMOTION.NEXTVAL");
		
		String numPromo = "";
		int numeroPromo=0;
		

			numeroPromo=Integer.parseInt(idNonFini.get(0));
			
			if(numeroPromo<10){
				 numPromo= "MES0000" + numeroPromo;
			}
			if(numeroPromo<100 && numeroPromo>=10){
				numPromo= "MES000" + numeroPromo;
			}
			if(numeroPromo<1000 && numeroPromo>=100){
				numPromo= "MES00" + numeroPromo;
			}
			if(numeroPromo<10000 && numeroPromo>=1000){
				numPromo= "MES0" + numeroPromo;
			}
			if(numeroPromo<100000 && numeroPromo>=10000){
				numPromo= "MES" + numeroPromo;
			}
		
		this.idPromotion =numPromo;
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
	
	public void modifierPromoBDD(){
		String dateD = SGBD.transformation(this.dateDebut);
		String dateF = SGBD.transformation(this.dateFin);
		
		String requete=" UPDATE PROMO" +
		   " SET NOMPROMO = '" + this.nomPromotion +"'," +
		   	"    DATEDEBUT = '" + dateD +"'," +
		   	"    DATEFIN= '" + dateF + "', " +
		   	"    POURCENTAGEPROMO= '" + this.pourcentagePromo + "'"
			;
		
		SGBD.executeUpdate(requete);
	}
	
	public static void supprimerPromoBDD(String idPromotion){
		
		String requete= "DELETE FROM PROMO WHERE IDARTICLE='"+idPromotion+"'";
		
		SGBD.executeUpdate(requete);	
	}
	
	public static boolean verifierDatePromotion(String annee,String mois,String jour) throws Exception{
		boolean resultat=true;
		
		String dateP = jour+mois+annee;
		
		Date datePromotion= SGBD.stringToDate(dateP,"ddMMyyyy");
		int moisDate=Integer.parseInt(mois);
		int jourDate=Integer.parseInt(jour);
		int anneeDate=Integer.parseInt(annee);
//		Date datePromotion= (Date) new java.sql.Date(anneeDate,moisDate,jourDate);
			
		Date dateJour = new Date(System.currentTimeMillis());
		
		
		System.out.println(dateJour.toString());
		System.out.println(datePromotion.toString());
		
		if(datePromotion.before(dateJour)){
			resultat=false;
		}
		
		if((moisDate==4 | moisDate==6 | moisDate==9 | moisDate==11) & jourDate==31){
			resultat=false;
		}
		
		if(moisDate==2 | jourDate>28){
			resultat=false;
		}
		
		return resultat;
	}
	
	public static boolean verifierOrdreDeuxDate(String anneeAvant,String moisAvant,String jourAvant,String anneeApres,String moisApres,String jourApres){
		boolean resultat=true;
		int moisDateAvant=Integer.parseInt(moisAvant);
		int jourDateAvant=Integer.parseInt(jourAvant);
		int anneeDateAvant=Integer.parseInt(anneeAvant);
		int moisDateApres=Integer.parseInt(moisApres);
		int jourDateApres=Integer.parseInt(jourApres);
		int anneeDateApres=Integer.parseInt(anneeApres);
		
		@SuppressWarnings("deprecation")
		Date datePromotionAvant =(Date) new java.util.Date(anneeDateAvant,moisDateAvant,jourDateAvant);
		System.out.println(datePromotionAvant);
		@SuppressWarnings("deprecation")
		Date datePromotionApres= (Date) new java.util.Date(anneeDateApres, moisDateApres, jourDateApres);
		
		if(datePromotionAvant.after(datePromotionApres)){
			resultat=false;
		}

		
		return resultat;
	}
	
}
