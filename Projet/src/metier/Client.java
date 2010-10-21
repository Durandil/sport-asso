package metier;

import basededonnees.SGBD;

public abstract class Client {

	// Classe Abstraite Client, dont héritent les classes Association et Particulier
	
	protected String idClient;
	protected String mail;
	protected String adresse;
	protected String ville;
	protected String codePostal;
	protected String telephone;
	protected String particulierAssociation;
	protected int nbPointsFidelite;
	protected boolean estFidele;
	protected boolean estActif;
	protected String motDePasse;
	
	// Méthode permettant de générer un mot de passe
	// Fait appel à la fonction random et aux caractères ASCII
	// Longueur : 7 caractères
	protected String genererMdp(){
		String s = "";
		for(int i=0;i<7;i++){
			int a =   (int) ((int)  93* Math.random()) +33;
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

	public String getParticulierAssociation() {
		return particulierAssociation;
	}

	public void setParticulierAssociation(String particulierAssociation) {
		this.particulierAssociation = particulierAssociation;
	}

	public int getNbPointsFidelite() {
		return nbPointsFidelite;
	}

	public void setNbPointsFidelite(int nbPointsFidelite) {
		this.nbPointsFidelite = nbPointsFidelite;
	}

	public boolean isEstFidele() {
		return estFidele;
	}

	public void setEstFidele(boolean estFidele) {
		this.estFidele = estFidele;
	}

	public boolean isEstActif() {
		return estActif;
	}

	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
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

	
	
	
	

	

}
