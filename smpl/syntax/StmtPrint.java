package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtPrint extends Exp{

    Exp exp;

    public StmtPrint(){
    }

    public StmtPrint(Exp exp){
        this.exp = exp;
    }

    public Exp getExp(){
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
        return v.visitPrintStmt(this, arg);
    }

    @Override
    public String toString(){
        return "print " + exp.toString();
    }
}
