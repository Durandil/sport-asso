package exception;

import ihm.Accueil.FenetreDialogIdentification;

/**
 * Cette exception permet de traiter le cas où une adresse mail est saisie lors
 * d'une authentification alors qu'elle ne figure pas dans la base de données.
 * 
 * @see FenetreDialogIdentification
 */
public class ExceptionCompteInexistant extends Exception {

	/**
	 * Constructeur de la classe ExceptionCompteInexistant
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionCompteInexistant(String msg) {
		super(msg);
	}

	/**
	 * Retourne le message de l'exception
	 * 
	 * @return Le message de l'exception
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
