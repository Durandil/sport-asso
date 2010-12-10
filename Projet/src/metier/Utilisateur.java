package metier;

public abstract class Utilisateur {

	protected String mail;
	protected String adresse;
	protected String idVille;
	protected String telephone;
	protected String motDePasse;

	protected String genererMdp(){
		String s = "";
		for(int i=0;i<7;i++){
			int a =   (int) ((int)  74* Math.random()) +48;
			char c = (char) a;
			 s = s+c;}
		return s;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getIdVille() {
		return idVille;
	}

	public void setVille(String idVille) {
		this.idVille = idVille;
	}


	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
