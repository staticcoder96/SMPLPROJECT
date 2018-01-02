package smpl.values;

import smpl.sys.SmplException;


public class TypeSmplException extends SmplException {

	private static final long serialVersionUID = 1L;

	public TypeSmplException(){
		super("Type Error");
	}

	public TypeSmplException(String text) {
        super(text);
    }
    
    public TypeSmplException(SmplType expected, SmplType received) {
        super("Type Error: Expected " + expected + " but got " + received);
    }
    
    public TypeSmplException(String text, Throwable cause) {
        super(text, cause);
    }
}