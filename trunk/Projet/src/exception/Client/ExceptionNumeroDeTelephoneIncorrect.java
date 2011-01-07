package exception.Client;

/**
 * Exception traitant le cas o� un num�ro de t�l�phone ne respecterait pas la norme
 */
public class ExceptionNumeroDeTelephoneIncorrect extends Exception {
	
	/**
	 * Constructeur de la classe ExceptionNumeroDeTelephoneIncorrect
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionNumeroDeTelephoneIncorrect(String msg) {
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
