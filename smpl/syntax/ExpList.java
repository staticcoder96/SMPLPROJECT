package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;
import java.util.*;

public class ExpList extends Exp {

  ArrayList<Exp> list;

  public ExpList(){
    list = new ArrayList();
  }

  public ExpList(ArrayList<Exp> list) {
    this.list = list;
  }

  public ArrayList<Exp> getList(){
    return list;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpList(this, arg);
  }

  @Override
  public String toString() {
    return list.toString();
  }
}

