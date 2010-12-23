package exception.Client;

/**
 * Cette exception permet de traiter le cas où l'adresse mail saisie lors d'une
 * inscription ne possède pas d'arobase
 */
public class ExceptionMailSansArobase extends Exception {

	/**
	 * Constructeur de la classe ExceptionMailSansArobase
	 * 
	 * @param msg
	 *            Message lié à l'exception
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
