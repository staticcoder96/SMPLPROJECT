package smpl.sys;

import smpl.syntax.SMPLExp;

public class SmplException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private SMPLExp source;
    
    public SmplException() {
        super();
    }
    
    public SmplException(String text) {
        super (text);
    }
    
    public SmplException(String text, Throwable cause) {
        super(text, cause);
    }
    
    public SmplException(SMPLExp exp, String text) {
        super(text);
        source = exp;
    }
    
    public SmplException(SMPLExp exp, String text, Throwable cause) {
        super(text, cause);
        source = exp;
    }
    
    public SMPLExp getSource() {
        return source;
    }
    
    protected void setSource(SMPLExp src) {
        source = src;
    }
}