package ihm;

// Classe provisoire permettant pour le moment de faire tourner la partie IHM
// mais sera remplacée par la classe correspondant aux clients

public class DialogInfo {
	private String nom, prenom,adresse,ville,motDePasse;
	private String codePostal, telephone,identifiant,denomination;
	private int pointFidelite;
	//private boolean fidelite;

	public DialogInfo(){}
	
	// constructeur particulier
	public DialogInfo(String nom,String adresse, String prenom,String ville,String codePostal,String telephone,String identifiant){
		this.nom = nom;
		this.prenom = prenom;
		this.denomination=null;
		this.ville=ville;
		this.adresse=adresse;
		this.codePostal=codePostal;
		this.telephone=telephone;
		this.identifiant=identifiant;
		this.motDePasse=this.genererMotPasse(8);
		this.pointFidelite=0;
		//this.fidelite=fidele;
	}
	
	// constructeur association
	public DialogInfo(String denomination,String adresse,String ville,String codePostal,String telephone,String identifiant){
		this.nom = null;
		this.prenom = null;
		this.denomination=denomination;
		this.ville=ville;
		this.adresse=adresse;
		this.codePostal=codePostal;
		this.telephone=telephone;
		this.identifiant=identifiant;
		this.motDePasse=this.genererMotPasse(8);
		this.pointFidelite=0;
		//this.fidelite=fidele;
	}
	
	
	public String genererMotPasse(int tailleMotPasse){
		String passwordObtenu="";
		
		for(int i=0;i<tailleMotPasse;i++){
			int alea=(int)(Math.random()*9);
			passwordObtenu=passwordObtenu + String.valueOf(alea);
		}
		return passwordObtenu;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	

	public int getPointFidelite() {
		return pointFidelite;
	}

	public void setPointFidelite(int pointFidelite) {
		this.pointFidelite = pointFidelite;
	}

}
