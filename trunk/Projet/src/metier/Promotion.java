package metier;

import java.sql.Date;

import basededonnees.SGBD;

public class Promotion {

	public Promotion(String idPromotion, String nomPromotion, Date dateDebut,
			Date dateFin, double pourcentagePromo) {
		super();
		this.idPromotion = idPromotion;
		this.nomPromotion = nomPromotion;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.pourcentagePromo = pourcentagePromo;
		ajouterBDD();
	}
	
	private String idPromotion;
	private String nomPromotion;
	private Date dateDebut;
	private Date dateFin;
	private double pourcentagePromo;
	
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
	
	public void ajouterBDD() {
		
		String s = SGBD.transformation(this.dateDebut);
		String s2 = SGBD.transformation(this.dateFin);
		
		
		String requete = "INSERT INTO PROMO (IDPROMO, NOMPROMO, DATEDEBUT," +
		" DATEFIN, POURCENTAGEPROMO) VALUES ( "
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
		+")";
		
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
	
	public void supprimerPromoBDD(){
		
		String requete= "DELETE FROM PROMO WHERE IDARTICLE='"+this.idPromotion+"'";
		
		SGBD.executeUpdate(requete);	
	}
	
}
