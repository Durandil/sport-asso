package exception.Client;

import ihm.Accueil.FenetreDialogIdentification;

/**
 * Cette exception permet de traiter le cas où le mot de passe saisi lors d'une
 * authentification est incorrect (autrement dit, diffère de celui présent dans
 * la base de données pour le compte en question)
 * 
 * @see FenetreDialogIdentification
 */
public class ExceptionMotDePasseErrone extends Exception {

	/**
	 * Constructeur de la classe ExceptionMotDePasseErrone
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionMotDePasseErrone(String msg) {
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
