package smpl.sys;

import smpl.syntax.SMPLExp;


public class SmplUnboundException extends SmplException {
    private static final long serialVersionUID = 1L;

    public FractalUnboundException(String text) {
        super(text);
    }

    public FractalUnboundException(String text, Throwable cause) {
        super(text, cause);
    }

    public FractalUnboundException(SMPLExp exp, String text) {
        super(exp, text);
    }

    public FractalUnboundException(SMPLExp exp, String text, Throwable cause) {
        super(exp, text, cause);
    }
    
    public FractalUnboundException(SMPLExp exp) {
        super(exp, "Unbound variable " + exp);
    }
}