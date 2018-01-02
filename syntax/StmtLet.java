public class StmtLet extends Exp {

	ArrayList<Binding> bindings;
	Exp body;

	public StmtLet(ArrayList<Binding> bindings, Exp body){
		this.bindings = bindings;
		this.body = body;
	}

	public ArrayList<Binding> getBindings(){
		return bindings;
	}

	public Exp getBody(){
		return body;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
		return v.visitStmtLet(this, arg);
	}

	@Override
	public String toString() {
		return "let " + bindings +" "+ body;
	}
}
