package exception;

public class ExceptionCodePostalDifferentDeCinqChiffres extends Exception {

	public ExceptionCodePostalDifferentDeCinqChiffres(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
