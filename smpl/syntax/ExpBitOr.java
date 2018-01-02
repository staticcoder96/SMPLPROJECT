package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpBitOr extends Exp {

    Exp exp1, exp2;

    public ExpBitOr(Exp exp1, Exp exp2) {
        this.exp1= exp1;
        this.exp2 = exp2;
    }

    public Exp getExpL() {
        return exp1;
    }

    public Exp getExpR() {
        return exp2;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitExpBitOr(this, arg);
    }

    @Override
    public String toString() {
        return exp1.toString() + "|" + exp2.toString() ;
    }
}
