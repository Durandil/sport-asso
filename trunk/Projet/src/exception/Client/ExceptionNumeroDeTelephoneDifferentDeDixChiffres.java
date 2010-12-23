package exception.Client;

/**
 * Exception traitant le cas où un noméro de téléphone ne possèderait pas 10 chiffres.
 */
public class ExceptionNumeroDeTelephoneDifferentDeDixChiffres extends Exception{

	/**
	 * Constructeur de la classe ExceptionNumeroDeTelephoneDifferentDeDixChiffres
	 * 
	 * @param msg
	 *            Message lié à l'exception
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
