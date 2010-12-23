package exception.Client;

/**
 * Exception traitant le cas o� un nom�ro de t�l�phone ne poss�derait pas 10 chiffres.
 */
public class ExceptionNumeroDeTelephoneDifferentDeDixChiffres extends Exception{

	/**
	 * Constructeur de la classe ExceptionNumeroDeTelephoneDifferentDeDixChiffres
	 * 
	 * @param msg
	 *            Message li� � l'exception
	 */
	public ExceptionNumeroDeTelephoneDifferentDeDixChiffres(String msg) {
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
