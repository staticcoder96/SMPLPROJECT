package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpLazy extends Exp{

  Exp exp;
 
  public ExpLazy(){
    super();
  }

  public ExpLazy(Exp e){
    exp = e;
  }

  public Exp getExp(){
    return exp;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpLazy(this, arg);
  }

  @Override
  public String toString(){
    return "Lazy: " +exp.toString();
  }
}
