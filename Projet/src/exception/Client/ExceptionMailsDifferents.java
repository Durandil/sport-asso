package exception.Client;

/**
 * Cette exception permet de traiter le cas o� le mail saisi dans le champ de
 * confirmation ne correspond pas � celui pr�sent dans le champ initial
 */
public class ExceptionMailsDifferents extends Exception {

	/**
	 * Constructeur de la classe ExceptionMailsDifferents
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionMailsDifferents(String msg) {
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
