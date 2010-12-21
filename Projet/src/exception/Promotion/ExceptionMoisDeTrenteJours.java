package exception.Promotion;

/**
 * Cette exception permet de traiter le cas où une date fait appel au 31ème jour
 * d'un mois qui n'en possède que 30
 */
public class ExceptionMoisDeTrenteJours extends Exception {

	/**
	 * Constructeur de la classe ExceptionMoisDeTrenteJours
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionMoisDeTrenteJours(String msg) {
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
