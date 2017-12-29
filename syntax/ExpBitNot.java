package MySMPL.syntax;

import MySMPL.sys.SMPLException;


public class ExpBitNot extends Exp {

    Exp exp;

    public ExpBitNot(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S arg) throws SMPLException {
        return v.visitExpBitNot(this, arg);
    }

    @Override
    public String toString() {
        return "~" + exp.toString();
    }
}
