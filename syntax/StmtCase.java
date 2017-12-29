package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;
import java.util.*;
import smpl.syntax.Exp;
import smpl.syntax.Option;

public class StmtCase extends Statement  {

  ArrayList<Option> options;
  ArrayList<Exp> pred;
  ArrayList<Exp> cons;
  Exp exp;
  Boolean form;


  public StmtCase(Exp e) {
    exp = e;
    form = true;
  }
  
  public StmtCase(ArrayList<Option> o) {
    options = o;

    for(Option i : o)
    {
      pred.add(i.getPred());
      cons.add(i.getCon());
    }
    System.out.println("test1");
    form = false;
  }

  public StmtCase(Option o) {

    pred.add(o.getPred());
    cons.add(o.getCon());
    form = false;
    System.out.println("test");
  }

  

  public ArrayList<Option> getOptions(){
    return options;
  }

  public ArrayList<Exp> getPredicate(){
    return pred;
  }

  public ArrayList<Exp> getConsequent(){
    return cons;
  }

  public Exp getExp(){
    return exp;
  }

  public Boolean getForm(){
    return form; 
  }
  

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitStmtCase(this, arg);
  }

  @Override
  public String toString() {
    if(form)
    {
      return "ELSE :" +exp.toString();
    }
    else{return "case{\n[" + options.toString() + "] \n}";}
  }
}