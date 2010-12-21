package exception.Client;


/**
 * Cette exception permet de traiter le code postal saisi par un client est diff�rent de 5 chiffres.
 */
public class ExceptionCodePostalDifferentDeCinqChiffres extends Exception {

	/**
	 * Constructeur de la classe ExceptionCodePostalDifferentDeCinqChiffres
	 * 
	 * @param msg
	 * 			Message li� � l'exception
	 */
	public ExceptionCodePostalDifferentDeCinqChiffres(String msg){
		super(msg);
	}
	
	/**
	 * Retourne le message de l'exception
	 * 
	 * @return Le message de l'exception
	 */
	public String getMessage(){
		return super.getMessage();
	}
}
