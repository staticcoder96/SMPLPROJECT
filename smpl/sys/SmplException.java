package smpl.sys;

import smpl.syntax.Exp;

public class SmplException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private Exp source;
    
    public SmplException() {
        super();
    }
    
    public SmplException(String text) {
        super (text);
    }
    
    public SmplException(String text, Throwable cause) {
        super(text, cause);
    }
    
    public SmplException(Exp exp, String text) {
        super(text);
        source = exp;
    }
    
    public SmplException(Exp exp, String text, Throwable cause) {
        super(text, cause);
        source = exp;
    }
    
    public Exp getSource() {
        return source;
    }
    
    protected void setSource(Exp src) {
        source = src;
    }
}