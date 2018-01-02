package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class ExpCall extends Exp{

  Exp exp;
  Exp lst;

  public ExpCall(Exp e){
    exp = e;
    lst = new ExpList();
  }

  public ExpCall(Exp e, Exp l){
    exp = e;
    lst = l;
  }

  public Exp getExpL(){
    return exp;
  }

  public Exp getExpR(){
    return lst;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpCall(this, arg);
  }

  @Override
  public String toString(){
    return "call(" + exp.toString() + ", " + lst.toString() + ")";
  }
}
