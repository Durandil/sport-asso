package exception.Client;

/**
 * Cette exception permet de traiter le cas o� le compte du client a �t�
 * d�sactiv�.
 */
public class ExceptionCompteDesactive extends Exception {

	/**
	 * Constructeur de la classe ExceptionCompteDesactive
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionCompteDesactive(String msg) {
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
