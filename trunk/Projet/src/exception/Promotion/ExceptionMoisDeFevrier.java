package exception.Promotion;

/**
 * Cette exception permet de traiter le cas où une date fait appel au 29ème jour (ou plus)
 * du mois de février pour une année non bissextile
 */
public class ExceptionMoisDeFevrier extends Exception {
	
	/**
	 * Constructeur de la classe ExceptionMoisDeFevrier
	 * 
	 * @param msg
	 * 			Message lié à l'exception
	 */
	public ExceptionMoisDeFevrier(String msg){
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
