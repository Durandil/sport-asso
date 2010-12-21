package exception.Client;

/**
 * Cette exception permet de traiter le cas o� le code postal comporte bien cinq chiffres mais
 * ne respecte pas le format qui lui est propre (par exemple : 00024)
 */
public class ExceptionCodePostalIncorrect extends Exception {

	/**
	 * Constructeur de la classe ExceptionCodePostalIncorrect
	 * 
	 * @param msg
	 * 			Message li� � l'exception
	 */
	public ExceptionCodePostalIncorrect(String msg){
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
