package exception;

/**
 * Cette exception permet de traiter le cas où un nombre de caractères trop
 * élevé est saisi dans un champ. Permet d'éviter les erreurs liées à la taille
 * lors de l'enregistrement dans la base de données.
 */
public class ExceptionExcesDeCaracteres extends Exception {

	/**
	 * Constructeur de la classe ExceptionExcesDeCaracteres
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionExcesDeCaracteres(String msg) {
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
