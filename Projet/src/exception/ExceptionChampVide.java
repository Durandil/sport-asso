package exception;

/**
 * Cette exception permet de traiter le cas o� un champ est vide dans un formulaire
 */
public class ExceptionChampVide extends Exception {

	/**
	 * Constructeur de la classe ExceptionChampVide
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionChampVide(String msg) {
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
