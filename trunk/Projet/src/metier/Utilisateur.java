package metier;


/**
 * <b>La classe abstraite Utilisateur repr�sente un utilisateur (client ou g�rant)</b>
 * <p>
 * Tous les clients et le g�rant ont en commun les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville de l'utilisateur</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Un mot de passe g�n�r� automatiquement</li>
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
	 * Le num�ro de t�l�phone de l'utilisateur
	 * 
	 * @see Client, G�rant
	 * 
	 */
	protected String telephone;
	
	/**
	 * Le mot de passe de l'utilisateur
	 * 
	 * @see Client, G�rant
	 * @see Utilisateur#genererMdp()
	 * 
	 */
	protected String motDePasse;

	
	/**
	 * G�n�re un mot de passe de 7 caract�res
	 * 
	 * <p>
	 * Cette m�thode choisit al�atoirement 7 caract�res dans la table des caract�res
	 * ASCII (les 48 premiers caract�res sont exclus car trop "exotiques")
	 * 
	 *
	 * </p> 
	 * 
	 * @return Le mot de passe g�n�r�
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
