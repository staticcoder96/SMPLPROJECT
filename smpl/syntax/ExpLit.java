package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;
import smpl.values.SmplValue;

public class ExpLit extends Exp {

  SmplValue val;

  public ExpLit(SmplValue v) {
    val = v;
  }

  public ExpLit(Integer v) {
    val = SmplValue.make(v);
  }
    
  public ExpLit(Double v) {
    val = SmplValue.make(v);
  }

  public ExpLit(boolean v) {
    val = SmplValue.make(v);
  }

  public SmplValue getVal() {
    return val;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpLit(this, arg);
  }

  @Override
  public String toString() {
    return val.toString();
  }
}

