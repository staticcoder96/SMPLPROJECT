package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpRead extends Exp{

  public ExpRead(){
  }
  
  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpRead(this, arg);
  }

  @Override
  public String toString(){
    return "read()";
  }
}
