public class StmtPrint extends Exp {

	Exp exp;
	char esc;

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

	public char getEscChar(){
		return esc;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
		return v.visitStmtPrint(this, arg);
	}

	@Override
	public String toString() {
		return exp.toString() + esc;
	}
}
