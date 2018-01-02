package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtPrintLn extends Exp{

    Exp exp;

    public StmtPrintLn(){
    }

    public StmtPrintLn(Exp exp){
        this.exp = exp;
    }

    public Exp getExp(){
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
        return v.visitPrintLnStmt(this, arg);
    }

    @Override
    public String toString(){
        return "println " + exp.toString();
    }
}
