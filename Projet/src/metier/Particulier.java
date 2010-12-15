package metier;

import java.util.ArrayList;

import ihm.Accueil.FenetreDialogIdentification;
import basededonnees.SGBD;

/**
 * <b>La classe Particulier repr�sente un particulier</b>
 * <p>
 * Un particulier est caract�ris� par les informations suivantes  :
 * <ul>
 * <li>Une adresse mail qui sert d'identifiant unique et non modifiable par le client repr�sentant l'association</li>
 * <li>Un nom</li>
 * <li>Un pr�nom</li>
 * <li>Une adresse</li>
 * <li>L'identifiant de la ville o� l'association est bas�e</li>
 * <li>Un num�ro de t�l�phone</li>
 * <li>Le statut du client (ici, "Particulier")</li>
 * <li>Un bool�en d�terminant si l'association poss�de une carte de fid�lit�</li>
 * <li>L'�tat du compte de l'association (Activ� si le bool�en correspondant est vrai et D�sactiv� sinon)</li>
 * <li>Un mot de passe g�n�r� automatiquement</li>
 * </ul>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @see BDD
 * @see Client
 */

public class Particulier extends Client {

	/**
	 * Le nom du client
	 * 
	 * @see Particulier#getNom()
	 * @see Particulier#setNom(String)
	 * @see Particulier#ajouterBDD()
	 * @see Particulier#modifierBDDparticulier(String, String, String, String, String, String)
	 * 
	 */
	private String nom;
	
	
	/**
	 * Le pr�nom du client
	 * 
	 * @see Particulier#getNom()
	 * @see Particulier#setNom(String)
	 * @see Particulier#ajouterBDD()
	 * @see Particulier#modifierBDDparticulier(String, String, String, String, String, String)
	 * 
	 */
	private String prenom;

	/**
	 * Constructeur de la classe Particulier
	 * <p>
	 * Le constructeur de la classe Particulier fait appel � la m�thode ajouterBDD()
	 * qui l'ajoute dans la base de donn�es. 
	 * La m�thode ajouterFideliteBDD() instancie un nouvel objet CarteFidelite et de ce fait
	 * cr�e une nouvelle ligne dans la table CARTE_FIDELITE si le client a �mis 
	 * le souhait de poss�der une carte.
	 * L'�tat du compte est par d�faut initialis� sur Activ� (bool�en estActif vrai)
	 * Le mot de passe est g�n�r� automatiquement.
	 * </p>
	 * 
	 * @param nom
	 *            Le nom du client
	 * @param prenom
	 *            Le pr�nom du client
	 * @param mail
	 *            Le mail de l'association, qui est utilis� comme identifiant et n'est pas modifiable par le client
	 * @param adresse
	 *            L'adresse de l'association
	 * @param idVille
	 *            L'identifiant de la ville o� est bas�e l'association
	 * @param telephone
	 *            Le num�ro de t�l�phone de l'association
	 * @param estFidele
	 *            D�termine si l'association poss�de une carte de fid�lit� ou non du magasin
	 *            
	 * @see Particulier#nom
	 * @see Particulier#prenom
	 * @see Client#mail
	 * @see Client#adresse
	 * @see Client#idVille
	 * @see Client#telephone
	 * @see Client#particulierAssociation
	 * @see Client#estFidele
	 * @see Client#estActif
	 * @see Client#motDePasse
	 * @see Client#ajouterFideliteBDD()
	 * @see Particulier#ajouterBDD()
	 * 
	 */
	public Particulier(String nom, String prenom, String mail, String adresse,
			 String idVille,
			String telephone, boolean estFidele) {
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;	
		this.idVille = idVille;
		this.telephone = telephone;
		this.particulierAssociation = "Particulier";
		this.estFidele = estFidele;
		this.estActif = true;
		this.motDePasse = genererMdp();
		ajouterBDD();
		ajouterFideliteBDD();
	}

	/**
     * Retourne le nom du particulier
     * 
     * @return Le nom du particulier
     * 
     */
	public String getNom() {
		return nom;
	}

	/**
     * Met � jour le nom du particulier
     * 
     * @param nom
     *            Le nom du particulier
     * 
     */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
     * Retourne le pr�nom du particulier
     * 
     * @return Le pr�nom du particulier
     * 
     */
	public String getPrenom() {
		return prenom;
	}

	/**
     * Met � jour le pr�nom du particulier
     * 
     * @param prenom
     *            Le pr�nom du particulier
     * 
     */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Ajoute le particulier dans la table CLIENT de la base de donn�es
	 * 
	 * <p>
	 * Cette m�thode commence par cr�er une cha�ne de caract�res d�pendant de 
	 * la valeur du bool�en (D�sactiv� si le bool�en est faux, Activ� sinon)
	 * 
	 * La requ�te se construit ensuite en fonction des caract�ristiques de l'article
	 * saisies lors de l'appel du constructeur
	 * </p> 
	 * 
	 * @see BDD
	 */
	public void ajouterBDD() {
		
		String actif = null;

		if (this.estActif == false) {
			actif = "D�sactiv�";
		} else {
			actif = "Activ�";
		}

		String requete = "INSERT INTO CLIENT (IDCLIENT, NOMCLIENT, PRENOMCLIENT, ADRESSECLIENT, IDVILLE"
				+ " , TELEPHONE, ETATCOMPTE, MOTDEPASSE) VALUES ( "
				+ "'"
				+ this.mail
				+ "',"
				+ "'"
				+ this.nom
				+ "',"
				+ "'"
				+ this.prenom
				+ "',"
				+ "'"
				+ this.adresse
				+ "',"
				+ "'"
				+ this.idVille
				+ "',"
				+ "'"
				+ this.telephone
				+ "',"
				+ "'"
				+ actif
				+ "',"
				+ "'"
				+ this.motDePasse + "')";

		SGBD.executeUpdate(requete);

	}


	
	/**
	 * Modifie les caract�ristiques du particulier dans la table CLIENT de la base de donn�es
	 * 
	 * <p>
	 * �tant donn� que le client peut modifier son code postal et non l'identifiant de la ville
	 * (auquel il n'a pas acc�s), la m�thode commence par r�cup�rer cet identifiant dans la table VILLE
	 *
	 * La requ�te se construit ensuite en fonction des caract�ristiques de l'article
	 * saisies lors de l'appel de la m�thode
	 * </p> 
	 * 
	 * @see BDD
	 */
	
	public static void modifierBDDparticulier(String idClient, String nom,
			String prenom, String adresse,String codePostal, String telephone) {

		String idVille = SGBD.selectStringConditionString("VILLE", "IDVILLE", "CODEPOSTAL", codePostal);
		
		String requete = " UPDATE CLIENT SET NOMCLIENT='" + nom
				+ "',PRENOMCLIENT='" + prenom + "',ADRESSECLIENT='" + adresse
				+  "',IDVILLE='" + idVille
				+ "',TELEPHONE='" + telephone  
				+ "' " + "WHERE IDCLIENT ='" + idClient + "'";

		System.out.println(requete);

		SGBD.executeUpdate(requete);
	}
	


}
