package MySMPL.syntax;

import MySMPL.semantics.Visitor;
import MySMPL.sys.SmplException;

public class ExpLogicNot extends Exp {

  Exp exp;

  public ExpLogicNot(Exp e) {
    exp = e;
  }

  public Exp getExp(){
    return exp;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpLogicNot(this, arg);
  }

  @Override
  public String toString() {
    return "not " + exp.toString();
  }
}

