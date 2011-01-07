package exception.Client;

/**
 * Cette exception permet de traiter le cas o� un client ne s�lectionne pas
 *  d'item dans un menu d�roulant JComboBox
 */
public class ExceptionAucunItemSelectionneDansJComboBox extends Exception {

	/**
	 * Constructeur de la classe ExceptionItemSelectionneJComboBox
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionAucunItemSelectionneDansJComboBox(String msg) {
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