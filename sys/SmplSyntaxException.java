package smpl.sys;

public class SmplSyntaxException extends SmplException {

    public SmplSyntaxException() {
        super("Syntax Error:");
    }
    
    public SmplSyntaxException(String text) {
        super(text);
    }    
    
    public SmplSyntaxException(String text, Throwable t) {
    	//throwable t alllows you to catch all errors and exceptions in java (runtime  errors)
        super(text, t);
    }

}