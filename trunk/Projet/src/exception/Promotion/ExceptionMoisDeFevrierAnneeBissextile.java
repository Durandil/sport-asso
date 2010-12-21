package exception.Promotion;

/**
 * Cette exception permet de traiter le cas où une date fait appel au 30ème jour (ou plus)
 * du mois de février pour une année bissextile
 */
public class ExceptionMoisDeFevrierAnneeBissextile extends Exception {

	/**
	 * Constructeur de la classe ExceptionMoisDeFevrierAnneeBissextile
	 * 
	 * @param msg
	 * 			Message lié à l'exception
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
