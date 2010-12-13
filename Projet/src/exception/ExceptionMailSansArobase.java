package exception;


public class ExceptionMailSansArobase extends Exception {

	public ExceptionMailSansArobase(String msg) {
		super(msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}


}
