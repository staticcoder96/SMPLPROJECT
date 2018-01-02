package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class Statement extends Exp{

    Exp exp;

    public Statement(){
	super();
    }

    public Statement(Exp e){
	exp = e;
    }

    public Exp getExp(){
	return exp;
    }

    public Object visit(Visitor v, Object arg)
	throws Exception
    {
	return v.visitStatement(this, arg);
    }

    public String toString() {
	return exp.toString();
    }
}
