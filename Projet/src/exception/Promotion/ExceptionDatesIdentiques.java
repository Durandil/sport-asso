package exception.Promotion;

/**
 * Cette exception permet de traiter le cas o� la date de d�but de la promotion
 * et celle de fin de la promotion sont identiques
 */
public class ExceptionDatesIdentiques extends Exception {

	/**
	 * Constructeur de la classe ExceptionDatesIdentiques
	 * 
	 * @param msg
	 *            Message li� � l'exception
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
