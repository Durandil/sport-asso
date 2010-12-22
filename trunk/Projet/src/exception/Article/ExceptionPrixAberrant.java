package exception.Article;

/**
 * Cette exception permet de traiter le cas où le poids saisi est aberrant
 */
public class ExceptionPrixAberrant extends Exception {

	/**
	 * Constructeur de la classe ExceptionPrixAberrant
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionPrixAberrant(String msg) {
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
