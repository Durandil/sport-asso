package exception;

import metier.LigneCommande;
import metier.Promotion;

/**
 * Cette classe permet de traiter le cas o� un client saisit un caract�re interdit
 * 
 */
public class ExceptionCaractereInterdit extends Exception {

	public ExceptionCaractereInterdit(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
	
}
