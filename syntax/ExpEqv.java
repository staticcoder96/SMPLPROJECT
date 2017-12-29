package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpEqv extends Exp {

  Exp exp1, exp2;

  public ExpEqv(Exp e1, Exp e2) {
    exp1 = e1;
    exp2 = e2;
  
  }

  public Exp getExpFirst(){
    return exp1;
  }

  public Exp getExpSecond(){
    return exp2;
  }

  

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpEqv(this, arg);
  }

  @Override
  public String toString() {
    return "eqv?(" + exp1.toString() + ", " + exp2.toString() + ")";
  }
}