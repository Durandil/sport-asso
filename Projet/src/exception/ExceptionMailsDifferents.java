package exception;

public class ExceptionMailsDifferents extends Exception {

	public ExceptionMailsDifferents(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
