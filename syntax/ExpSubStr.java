public class ExpSubStr extends Exp{

  Exp exp1, exp2, exp3;

  public ExpSubStr(Exp e1, Exp e2, Exp e3){
    exp1 = e1;
    exp2 = e2;
    exp3 = e3;
  }

  public Exp getExpString(){
    return exp1;
  }

  public Exp getStart(){
    return exp2;
  }

  public Exp getEnd(){
    return exp3;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpSubStr(this, arg);
  }

  @Override
  public String toString() {
    return "substr(" + exp1.toString() + ", " + exp2.toString() + ", " + exp3.toString() + ")";
  }
}

