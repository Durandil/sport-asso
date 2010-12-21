package exception.Promotion;

/**
 * Cette exception permet de traiter le cas o� la date de d�but de la promotion
 * est post�rieure � celle de fin de la promotion
 */
public class ExceptionOrdreDeDeuxDates extends Exception {

	/**
	 * Constructeur de la classe ExceptionOrdreDeDeuxDates
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionOrdreDeDeuxDates(String msg) {
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
