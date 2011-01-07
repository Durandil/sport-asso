package exception.Client;

/**
 * Cette exception permet de traiter le cas où un client ne sélectionne pas
 *  d'item dans un menu déroulant JComboBox
 */
public class ExceptionAucunItemSelectionneDansJComboBox extends Exception {

	/**
	 * Constructeur de la classe ExceptionItemSelectionneJComboBox
	 * 
	 * @param msg
	 *            Message lié à l'exception
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