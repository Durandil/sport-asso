package exception;

public class ExceptionMailDejaExistant extends Exception {

	public ExceptionMailDejaExistant(String msg){
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}
}
