package metier;

import basededonnees.SGBD;

public abstract class Client extends Utilisateur{

	// Classe Abstraite Client, dont héritent les classes Association et Particulier
	// De ce fait, sont regroupées ici l'ensemble des caractéristiques communes aux deux entités :
	// Statut, Fidélité, État du compte, mot de passe, et identifiant de la ville
	

	protected String particulierAssociation;
	protected boolean estFidele;
	protected boolean estActif;

	


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
	

	/**
	 * Crée un nouvel objet CarteFidelite si le client a signalé son souhait de posséder une carte de fidélité
	 * 
	 * <p>
	 * De ce fait, la table CARTE_FIDELITE est mise à jour (plus de précisions
	 * dans le commentaire concernant ce constructeur)
	 * </p> 
	 * 
	 * @see CarteFidelite#CarteFidelite(String, int)
	 * @see BDD
	 */
		
	
	public void ajouterFideliteBDD() {
		if (this.estFidele) {
				
					CarteFidelite cf = new CarteFidelite(this.mail, 0);
					
		}
	}	


// Méthode modifiant l'état du compte client
	
	public static void modifierBDDclient(String idClient,String etatCompte) {

		String requete = " UPDATE CLIENT SET ETATCOMPTE='" + etatCompte
				+ "' WHERE IDCLIENT = '" + idClient+ "'" ;

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}

	public static boolean verifierCreationComption(String mail,String mailConformation,
			String denomination,String nom,String prenom,String telephone,String codePostal){
		
		boolean compteBon=true;
		
		if(!mail.contains("@")){
			compteBon=false;
		}
		if(!mail.equals(mailConformation)){
			compteBon=false;
		}
		if(codePostal.length() != 5){
			compteBon=false;
		}
		if(telephone.length() != 10){
			compteBon=false;
		}
		if(denomination.contains("'") | nom.contains("'") | prenom.contains("'") | mail.contains("'")){
			compteBon=false;
		}
		
		if(denomination.length()>40 | nom.length()>40 | prenom.length()>40 | mail.length()>40){
			compteBon=false;
		}
		
		try{
			int cp = Integer.parseInt(codePostal);
			int tel = Integer.parseInt(telephone);
			
			if(cp<=999 | cp>=96000){
				compteBon=false;
			}
			if(tel<0100000000 | tel>= 0700000000){
				compteBon = false;
			}
			
		}
		catch(NumberFormatException exc){
			System.out.println("le Code Postal ou le numéro de téléphone indidiqué n'est pas bon");
		}
		
		return compteBon ;
	}

	
	
	
	

	

}
