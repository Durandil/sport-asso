package exception.Promotion;

/**
 * Cette exception permet de traiter le cas o� une date fait appel au 30�me jour (ou plus)
 * du mois de f�vrier pour une ann�e bissextile
 */
public class ExceptionMoisDeFevrierAnneeBissextile extends Exception {

	/**
	 * Constructeur de la classe ExceptionMoisDeFevrierAnneeBissextile
	 * 
	 * @param msg
	 * 			Message li� � l'exception
	 */
	public ExceptionMoisDeFevrierAnneeBissextile(String msg){
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
