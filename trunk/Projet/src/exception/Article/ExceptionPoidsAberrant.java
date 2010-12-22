package exception.Article;

/**
 * Cette exception permet de traiter le cas o� le poids saisi est aberrant
 */
public class ExceptionPoidsAberrant extends Exception {

	/**
	 * Constructeur de la classe ExceptionPoidsAberrant
	 * 
	 * @param msg
	 *            Message li� � l'exception
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
