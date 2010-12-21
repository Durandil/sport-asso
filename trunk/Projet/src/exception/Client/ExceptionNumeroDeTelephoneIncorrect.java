package exception.Client;

public class ExceptionNumeroDeTelephoneIncorrect extends Exception {
	
	public ExceptionNumeroDeTelephoneIncorrect(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
	
}
