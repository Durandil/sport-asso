package exception.Promotion;


/**
 * Cette exception permet de traiter le cas o� une date saisie appartient au pass�.
 */
public class ExceptionDateAvantAujourdhui extends Exception {

	/**
	 * Constructeur de la classe ExceptionDateAvantAujourdhui
	 * 
	 * @param msg
	 * 			Message li� � l'exception
	 */
	public ExceptionDateAvantAujourdhui(String msg){
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
