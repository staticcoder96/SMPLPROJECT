package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class SmplProgram extends Exp{
    StmtSeq seq;

    public SmplProgram(StmtSeq s){
	   seq = s;
    }

    public StmtSequence getSeq(){
        return seq;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
        return v.visitSmplProgram(this, arg);
    }

    public String toString(){
	   return seq.toString();
    }
}
