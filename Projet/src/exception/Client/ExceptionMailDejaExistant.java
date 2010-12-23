package exception.Client;

/**
 * Cette exception permet de traiter le cas où l'adresse mail saisie lors d'une
 * inscription existe déjà dans la base
 */
public class ExceptionMailDejaExistant extends Exception {

	/**
	 * Constructeur de la classe ExceptionMailDejaExistant
	 * 
	 * @param msg
	 *            Message lié à l'exception
	 */
	public ExceptionMailDejaExistant(String msg) {
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
