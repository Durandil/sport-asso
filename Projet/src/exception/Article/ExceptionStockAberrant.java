package exception.Article;

/**
 * Cette exception permet de traiter le cas où le stock saisi est aberrant
 */
public class ExceptionStockAberrant extends Exception {

	/**
	 * Constructeur de la classe ExceptionStockAberrant
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionStockAberrant(String msg) {
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
