package exception;

public class ExceptionExcesDeCaracteres extends Exception {

	public ExceptionExcesDeCaracteres(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
