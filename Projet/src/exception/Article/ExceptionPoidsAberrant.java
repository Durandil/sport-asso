package exception.Article;

/**
 * Cette exception permet de traiter le cas où le poids saisi est aberrant
 */
public class ExceptionPoidsAberrant extends Exception {

	/**
	 * Constructeur de la classe ExceptionPoidsAberrant
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionPoidsAberrant(String msg) {
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
