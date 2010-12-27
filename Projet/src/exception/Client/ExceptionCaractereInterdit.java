package exception.Client;

import metier.LigneCommande;
import metier.Promotion;

/**
 * Cette exception permet de traiter le cas où un client saisit un caractère
 * interdit
 */
public class ExceptionCaractereInterdit extends Exception {

	/**
	 * Constructeur de la classe ExceptionCaractereInterdit
	 * 
	 * @param msg
	 *            Message lié à l'exception
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
