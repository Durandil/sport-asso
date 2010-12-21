package exception.Promotion;

/**
 * Cette exception permet de traiter le cas où la date de début de la promotion
 * est postérieure à celle de fin de la promotion
 */
public class ExceptionOrdreDeDeuxDates extends Exception {

	/**
	 * Constructeur de la classe ExceptionOrdreDeDeuxDates
	 * 
	 * @param msg
	 *            Message lié à l'exception
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
