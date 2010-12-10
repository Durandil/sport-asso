package metier;


/**
 * <b>La classe abstraite Utilisateur représente un utilisateur (client ou gérant)</b>
 * <p>
 * Tous les clients et le gérant ont en commun les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville de l'utilisateur</li>
 * <li>Un numéro de téléphone</li>
 * <li>Un mot de passe généré automatiquement</li>
 * </ul>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @see Client, Gerant
 */
public abstract class Utilisateur {

	/**
	 * Le mail de l'utilisateur, non modifiable
	 * 
	 * @see Client, Gerant
	 * 
	 */
	protected String mail;
	
	/**
	 * L'adresse de l'utilisateur
	 * 
	 * @see Client, Gerant
	 * 
	 */
	protected String adresse;
	
	/**
	 * L'identifiant de la ville de l'utilisateur
	 * 
	 * @see Client, Gerant
	 * 
	 */
	protected String idVille;
	
	/**
	 * Le numéro de téléphone de l'utilisateur
	 * 
	 * @see Client, Gérant
	 * 
	 */
	protected String telephone;
	
	/**
	 * Le mot de passe de l'utilisateur
	 * 
	 * @see Client, Gérant
	 * @see Utilisateur#genererMdp()
	 * 
	 */
	protected String motDePasse;

	
	/**
	 * Génère un mot de passe de 7 caractères
	 * 
	 * <p>
	 * Cette méthode choisit aléatoirement 7 caractères dans la table des caractères
	 * ASCII (les 48 premiers caractères sont exclus car trop "exotiques")
	 * 
	 *
	 * </p> 
	 * 
	 * @return Le mot de passe généré
	 * @see Client,Gerant
	 */
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
