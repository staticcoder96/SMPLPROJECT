/*
 * Adapted fro FnPlot
 */

package smpl.values;

import smpl.sys.SmplException;


public class TypeSmplException extends SmplException {

	private static final long serialVersionUID = 1L;

	public TypeSmplException(){
		super("Type Error");
	}

	public TypeSmplException(String message) {
        super(message);
    }
    
    public TypeSmplException(SmplType expected, SmplType received) {
        super("Type Error: Expected " + expected + " but got " + received);
    }
    
    public TypeSmplException(String message, Throwable cause) {
        super(message, cause);
    }
}