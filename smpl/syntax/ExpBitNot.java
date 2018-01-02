package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpBitNot extends Exp {

    Exp exp;

    public ExpBitNot(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitExpBitNot(this, arg);
    }

    @Override
    public String toString() {
        return "~ " + exp.toString();
    }
}
