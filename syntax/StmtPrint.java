package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.syntax.Exp;
import smpl.sys.SmplException;

public class StmtPrint extends Exp {

	Exp exp;
	char terminator;

	public StmtPrint(Exp e){
		this(e, '\0');
	}

	public StmtPrint(Exp e, char t) {
		exp = e;
		terminator = t;
	}

	public Exp getExp(){
		return exp;
	}

	public char getTerminatingCharacter(){
		return terminator;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitStmtPrint(this, arg);
	}

	@Override
	public String toString() {
		return exp.toString() + terminator;
	}
}