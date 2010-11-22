package metier;

import basededonnees.SGBD;

public abstract class Client extends Utilisateur{

	// Classe Abstraite Client, dont h�ritent les classes Association et Particulier
	

	protected String particulierAssociation;
	protected int nbPointsFidelite;
	protected boolean estFidele;
	protected boolean estActif;
	protected String motDePasse;
	static int CompteurIdcarte=0;
	protected String idVille;
	protected String nomVille;
	//static int CompteurIdcarte=SGBD.recupererIdentifiantDernierEnregistrementTable("CARTE_FIDELITE", "IDCARTEFIDELITE");
	
	// M�thode permettant de g�n�rer un mot de passe
	// Fait appel � la fonction random et aux caract�res ASCII
	// Longueur : 7 caract�res



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
	
	public String getIdVille() {
		return idVille;
	}

	public void setIdVille(String idVille) {
		this.idVille = idVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public String getNomVille() {
		return nomVille;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	

	
	
	
	

	

}
