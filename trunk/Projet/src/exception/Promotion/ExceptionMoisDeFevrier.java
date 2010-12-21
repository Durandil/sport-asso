package exception.Promotion;

/**
 * Cette exception permet de traiter le cas o� une date fait appel au 29�me jour (ou plus)
 * du mois de f�vrier pour une ann�e non bissextile
 */
public class ExceptionMoisDeFevrier extends Exception {
	
	/**
	 * Constructeur de la classe ExceptionMoisDeFevrier
	 * 
	 * @param msg
	 * 			Message li� � l'exception
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
