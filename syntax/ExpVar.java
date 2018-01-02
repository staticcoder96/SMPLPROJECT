public class ExpVar extends Exp{

	String var;

	public ExpVar(String v){
		var = v;
	}

	public String getVar(){
		return var;
	}

	@Override
	public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
		return v.visitExpVar(this, arg);
	}

	@Override
	public String toString(){
		return var;
	}
}
