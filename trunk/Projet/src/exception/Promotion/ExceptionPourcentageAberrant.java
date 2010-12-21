package exception.Promotion;

/**
 * Cette exception permet de traiter le cas o� un pourcentage saisi est aberrant
 * (inf�rieur � 0% ou sup�rieur � 100%)
 */
public class ExceptionPourcentageAberrant extends Exception {

	/**
	 * Constructeur de la classe ExceptionChampVide
	 * 
	 * @param msg
	 *            Message li� � l'exception
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