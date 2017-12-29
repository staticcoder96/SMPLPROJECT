package MySMPL.syntax;

import MySMPL.semantics.Visitor;
import MySMPL.sys.SmplException;

public class SmplProgram extends Exp {
    StmtSequence seq;

    public SmplProgram(StmtSequence s) {
	   seq = s;
    }

    public StmtSequence getSeq(){
        return seq;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitSmplProgram(this, arg);
    }

    public String toString() {
	   return seq.toString();
    }
}
