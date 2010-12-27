package exception.Client;

import metier.LigneCommande;
import metier.Promotion;

/**
 * Cette exception permet de traiter le cas o� un client saisit un caract�re
 * interdit
 */
public class ExceptionCaractereInterdit extends Exception {

	/**
	 * Constructeur de la classe ExceptionCaractereInterdit
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionCaractereInterdit(String msg) {
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
