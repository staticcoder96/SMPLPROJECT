public class ExpDef extends Exp{

	Exp exp;
	String var;

	public ExpDef(String id, Exp e){
		var = id;
		exp = e;
	}

	public String getVar(){
		return var;
	}

	public Exp getExp(){
		return exp;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
		return v.visitExpDef(this, arg);
	}

	@Override
	public String toString(){
		return "def " , var.toString(), exp.toString();
	}
}
