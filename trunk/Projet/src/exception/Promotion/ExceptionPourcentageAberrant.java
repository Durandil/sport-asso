package exception.Promotion;

/**
 * Cette exception permet de traiter le cas où un pourcentage saisi est aberrant
 * (inférieur à 0% ou supérieur à 100%)
 */
public class ExceptionPourcentageAberrant extends Exception {

	/**
	 * Constructeur de la classe ExceptionChampVide
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionPourcentageAberrant(String msg) {
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