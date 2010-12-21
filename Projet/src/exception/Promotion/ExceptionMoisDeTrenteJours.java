package exception.Promotion;

/**
 * Cette exception permet de traiter le cas o� une date fait appel au 31�me jour
 * d'un mois qui n'en poss�de que 30
 */
public class ExceptionMoisDeTrenteJours extends Exception {

	/**
	 * Constructeur de la classe ExceptionMoisDeTrenteJours
	 * 
	 * @param msg
	 *            Message li� � l'exception
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
