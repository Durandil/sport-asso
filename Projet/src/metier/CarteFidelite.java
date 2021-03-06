package metier;

import ihm.Client.FenetreCommandeArticle;
import ihm.Client.FenetreFideliteClient;
import java.util.ArrayList;
import basededonnees.SGBD;

/**
 * <b>La classe CarteFidelite repr�sente une carte de fid�lit�.</b>
 * <p>
 * Une carte de fid�lit� est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique et non modifiable, de la forme FIDxxxxx</li>
 * <li>Une adresse mail, identifiant du client qui poss�de la carte</li>
 * <li>Le nombre de points qu'elle poss�de</li>
 * </ul>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @see basededonnees.BDD
 * @see Client
 */
public class CarteFidelite {

	/**
	 * L'identifiant de la carte de fid�lit�, non modifiable.
	 * 
	 * @see CarteFidelite#ajouterBDD()
	 * @see CarteFidelite#getIdCarte()
	 * @see CarteFidelite#setIdCarte(String)
	 * 
	 */
	private String idCarte;

	/**
	 * L'adrese mail (l'identifiant) du client qui poss�de la carte.
	 * 
	 * @see CarteFidelite#ajouterBDD()
	 * @see CarteFidelite#getIdClient()
	 * @see CarteFidelite#setIdClient(String)
	 * 
	 */
	private String idClient;

	/**
	 * Le nombre de points de la carte de fid�lit�.
	 * 
	 * @see CarteFidelite#ajouterBDD()
	 * @see CarteFidelite#getNombreDePoints()
	 * @see CarteFidelite#setNombreDePoints(int)
	 * 
	 */
	private int nombreDePoints;

	/**
	 * Constructeur de la classe CarteFidelite.
	 * <p>
	 * Ce constructeur fait appel � la m�thode ajouterBDD() qui ajoute la carte
	 * de fidelit� dans la base de donn�es. Il ne prend pas en param�tre
	 * l'identifiant de la carte car ce dernier est automatiquement g�n�r�
	 * lorsque la carte est ajout�e dans la base.
	 * </p>
	 * 
	 * @param idClient
	 *            L'identifiant du client (son adresse mail)
	 * @param nombreDePoints
	 *            Le nombre de points de la carte de fid�lit�
	 * 
	 * @see Client#ajouterFideliteBDD()
	 * 
	 */

	public CarteFidelite(String idClient, int nombreDePoints) {
		super();

		this.idClient = idClient;
		this.nombreDePoints = nombreDePoints;
		ajouterBDD();
	}

	/**
	 * Retourne l'id de la carte de fid�lit�
	 * 
	 * @return L'identifiant de la carte de fid�lit�
	 * 
	 */
	public String getIdCarte() {
		return idCarte;
	}

	/**
	 * Met � jour l'identifiant de la carte de fid�lit�
	 * 
	 * @param idCarte
	 *            L'identifiant de la carte de fid�lit�
	 * 
	 */
	public void setIdCarte(String idCarte) {
		this.idCarte = idCarte;
	}

	/**
	 * Retourne le mail du client (son identifiant)
	 * 
	 * @return L'identifiant du client
	 * 
	 */
	public String getIdClient() {
		return idClient;
	}

	/**
	 * Met � jour l'identifiant du client
	 * 
	 * @param idClient
	 *            L'identifiant du client
	 * 
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	/**
	 * Retourne le nombre de points de la carte de fid�lit�
	 * 
	 * @return Le nombre de points de la carte de fid�lit�
	 * 
	 */
	public int getNombreDePoints() {
		return nombreDePoints;
	}

	/**
	 * Met � jour le nombre de points de la carte de fid�lit�
	 * 
	 * @param nombreDePoints
	 *            Le nombre de points de la carte de fid�lit�
	 * 
	 */
	public void setNombreDePoints(int nombreDePoints) {
		this.nombreDePoints = nombreDePoints;
	}

	/**
	 * Ajoute la carte de fidelite dans la table CARTE_FIDELITE de la base de
	 * donn�es.
	 * 
	 * <p>
	 * Cette m�thode commence par r�cup�rer l'indice de s�quence de la table
	 * afin de g�n�rer l'identifiant de l'article dans le format appropri�.
	 * 
	 * La requ�te se construit ensuite en fonction des caract�ristiques de
	 * l'article saisies lors de l'appel du constructeur
	 * </p>
	 * 
	 * @see basededonnees.BDD
	 */

	public void ajouterBDD() {

		ArrayList<String> idNonFini = SGBD.selectListeString("DUAL",
				"S_FIDELITE.NextVal");

		int numeroFidelite = 0;

		numeroFidelite = Integer.parseInt(idNonFini.get(0));

		if (numeroFidelite < 10) {
			this.idCarte = "FID0000" + numeroFidelite;
		}
		if (numeroFidelite < 100 && numeroFidelite >= 10) {
			this.idCarte = "FID000" + numeroFidelite;
		}
		if (numeroFidelite < 1000 && numeroFidelite >= 100) {
			this.idCarte = "FID00" + numeroFidelite;
		}
		if (numeroFidelite < 10000 && numeroFidelite >= 1000) {
			this.idCarte = "FID0" + numeroFidelite;
		}
		if (numeroFidelite < 100000 && numeroFidelite >= 10000) {
			this.idCarte = "FID" + numeroFidelite;
		}

		String requete2 = "INSERT INTO CARTE_FIDELITE (IDCARTEFIDELITE, NBPOINTS, IDCLIENT)"
				+ "VALUES ('"
				+ this.idCarte
				+ "','"
				+ this.nombreDePoints
				+ "','" + this.idClient + "')";

		SGBD.executeUpdate(requete2);

	}

	/**
	 * Modifie le nombre de points sur la carte de fidelite dans la table
	 * CARTE_FIDELITE de la base de donn�es.
	 * 
	 * <p>
	 * Cette m�thode effectue la modification du nombre de points sur la carte
	 * de fidelit� associ� � l'identifiant du client entr� en param�tre.
	 * </p>
	 * 
	 * 
	 * @param idClient
	 *            L'identifiant du client
	 * @param nbrePoints
	 *            Le nouveau nombre de points sur la carte
	 * @see basededonnees.BDD
	 * @see FenetreCommandeArticle
	 */

	public static void modifierBDDcarteFidelite(String idClient, int nbrePoints) {

		if (nbrePoints < 0) {
			nbrePoints = 0;
		}
		String requete = "UPDATE CARTE_FIDELITE SET NBPOINTS=" + nbrePoints
				+ " WHERE IDCLIENT='" + idClient + "'";

		SGBD.executeUpdate(requete);

	}

	/**
	 * D�termine le bon de r�duction associ� au nombre de points sur la carte de
	 * fidelite.
	 * 
	 * <p>
	 * Cette m�thode va en fonction du nombre de points sur la carte de fid�lit�
	 * d�terminer le montant du bon de r�duction pour les clients poss�dant un
	 * compte fid�lit�.
	 * </p>
	 * 
	 * @param nbrePoints
	 *            Le nombre de points sur la carte
	 * 
	 * @return La valeur du bon d'achat.
	 * 
	 * @see FenetreFideliteClient
	 * @see FenetreCommandeArticle
	 */
	public static int calculerBonsReductions(int nbrePoints) {
		int bonAchat = 0;

		if (nbrePoints < 100) {
			bonAchat = 0;
		} else if (nbrePoints < 200) {
			bonAchat = 5;
		} else if (nbrePoints < 500) {
			bonAchat = 12;
		} else if (nbrePoints < 1000) {
			bonAchat = 30;
		} else if (nbrePoints < 2000) {
			bonAchat = 70;
		} else
			bonAchat = 150;

		return bonAchat;
	}

	/**
	 * D�termine le nombre de points associ� au bon d'achat utilis� lors d'une
	 * commmande.
	 * 
	 * <p>
	 * Cette m�thode va en fonction du bon d'achat utilis� lors d'une commmande
	 * d�terminer le montant du nombre de points qui sera enlev� de la carte de
	 * fid�lit�.
	 * </p>
	 * 
	 * @param bonAchat
	 *            La valeur du bon d'achat.
	 * 
	 * @return Le nombre de points correspondant
	 * 
	 * @see FenetreFideliteClient
	 * @see FenetreCommandeArticle
	 */
	public static int calculerNombreDePoints(int bonAchat) {
		int nombreDePoints = 0;

		if (bonAchat == 0) {
			nombreDePoints = 0;
		} else if (bonAchat == 5) {
			nombreDePoints = 100;
		} else if (bonAchat == 12) {
			nombreDePoints = 200;
		} else if (bonAchat == 30) {
			nombreDePoints = 500;
		} else if (bonAchat == 70) {
			nombreDePoints = 1000;
		} else if (bonAchat == 150) {
			nombreDePoints = 2000;
		}

		return nombreDePoints;
	}
}
