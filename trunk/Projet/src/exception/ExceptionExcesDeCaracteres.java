package exception;

/**
 * Cette exception permet de traiter le cas o� un nombre de caract�res trop
 * �lev� est saisi dans un champ. Permet d'�viter les erreurs li�es � la taille
 * lors de l'enregistrement dans la base de donn�es.
 */
public class ExceptionExcesDeCaracteres extends Exception {

	/**
	 * Constructeur de la classe ExceptionExcesDeCaracteres
	 * 
	 * @param msg
	 *            Message li� � l'exception
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
