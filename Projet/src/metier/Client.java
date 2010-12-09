package metier;

import basededonnees.SGBD;

public abstract class Client extends Utilisateur{

	// Classe Abstraite Client, dont héritent les classes Association et Particulier
	// De ce fait, sont regroupées ici l'ensemble des caractéristiques communes aux deux entités :
	// Statut, Fidélité, État du compte, mot de passe, et identifiant de la ville
	

	protected String particulierAssociation;
	protected boolean estFidele;
	protected boolean estActif;
	protected String motDePasse;
	protected String idVille;
	


	public String getParticulierAssociation() {
		return particulierAssociation;
	}

	public void setParticulierAssociation(String particulierAssociation) {
		this.particulierAssociation = particulierAssociation;
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


	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

// Méthode modifiant l'état du compte client
	
	public static void modifierBDDclient(String idClient,String etatCompte) {

		String requete = " UPDATE CLIENT SET ETATCOMPTE='" + etatCompte
				+ "' WHERE IDCLIENT = '" + idClient+ "'" ;

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}

	

	
	
	
	

	

}
