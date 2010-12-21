package exception.Client;

import ihm.Accueil.FenetreDialogIdentification;

/**
 * Cette exception permet de traiter le cas o� le mot de passe saisi lors d'une
 * authentification est incorrect (autrement dit, diff�re de celui pr�sent dans
 * la base de donn�es pour le compte en question)
 * 
 * @see FenetreDialogIdentification
 */
public class ExceptionMotDePasseErrone extends Exception {

	/**
	 * Constructeur de la classe ExceptionMotDePasseErrone
	 * 
	 * @param msg
	 *            Message li� � l'exception
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
