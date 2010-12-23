package exception.Client;

/**
 * Cette exception permet de traiter le cas o� l'adresse mail saisie lors d'une
 * inscription ne poss�de pas d'arobase
 */
public class ExceptionMailSansArobase extends Exception {

	/**
	 * Constructeur de la classe ExceptionMailSansArobase
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionMailSansArobase(String msg) {
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
