package exception.Client;

/**
 * Cette exception permet de traiter le cas où le compte du client a été
 * désactivé.
 */
public class ExceptionCompteDesactive extends Exception {

	/**
	 * Constructeur de la classe ExceptionCompteDesactive
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionCompteDesactive(String msg) {
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
