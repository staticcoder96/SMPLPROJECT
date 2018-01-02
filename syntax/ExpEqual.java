public class ExpEqual extends Exp{

  Exp exp1, exp2;

  public ExpEqual(Exp e1, Exp e2){
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
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpEqual(this, arg);
  }

  public String toString(){
    return "Equal?(" + exp1.toString() + ", " + exp2.toString() + ")";
  }
}
