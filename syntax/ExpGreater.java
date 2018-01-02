public class ExpGreater extends Exp{

  Exp exp1, exp2;

  public ExpGreater(Exp e1, Exp e2) {
    exp1 = e1;
    exp2 = e2;
  }

  public Exp getExpL(){
    return exp1;
  }

  public Exp getExpR(){
    return exp2;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpGt(this, arg);
  }

  @Override
  public String toString() {
    return exp1.toString() + " > " + exp2.toString();
  }
}

