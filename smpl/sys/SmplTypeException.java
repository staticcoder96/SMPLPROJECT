package smpl.sys;

import smpl.sys.SmplException;
import smpl.values.SmplTypes;


public class SmplTypeException extends SmplException {

	private static final long serialVersionUID = 1L;

	public SmplTypeException(){
		super("Type Error");
	}

	public SmplTypeException(String text) {
        super(text);
    }
    
    public SmplTypeException(SmplTypes expected, SmplTypes received) {
        super("Type Error: Expected " + expected + " but got " + received);
    }
    
    public SmplTypeException(String text, Throwable cause) {
        super(text, cause);
    }
}