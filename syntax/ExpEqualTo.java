package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpEqualTo extends Exp {

  Exp num1, num2;

  public ExpEq(Exp e1, Exp e2) {
    num1 = e1;
    num2 = e2;
  }

  public Exp getExpL(){
    return num1;
  }

  public Exp getExpR() {
    return num2;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpEqualTo(this, arg);
  }

  @Override
  public String toString() {
    return num1.toString() + " = " + num2.toString();
  }
}


