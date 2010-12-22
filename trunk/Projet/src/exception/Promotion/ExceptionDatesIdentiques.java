package exception.Promotion;

/**
 * Cette exception permet de traiter le cas où la date de début de la promotion
 * et celle de fin de la promotion sont identiques
 */
public class ExceptionDatesIdentiques extends Exception {

	/**
	 * Constructeur de la classe ExceptionDatesIdentiques
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionDatesIdentiques(String msg) {
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
