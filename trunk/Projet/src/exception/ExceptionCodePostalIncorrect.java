package exception;

public class ExceptionCodePostalIncorrect extends Exception {

	public ExceptionCodePostalIncorrect(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
