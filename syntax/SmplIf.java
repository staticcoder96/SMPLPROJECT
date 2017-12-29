package MySMPL.syntax;

import MySMPL.semantics.Visitor;
import MySMPL.sys.SmplException;


public class ExpIf extends Exp {

  Exp con, ifArg, elseArg;
  Boolean ifelse;

  public ExpIf(Exp con, Exp ifArg, Exp elseArg){

    this.con = con;
    this.ifArg = ifArg;
    this.elseArg = elseArg;

    if(elseArg != null)
    {
    	this.ifelse= true;
    }
    else
    { 
    	this.ifelse = false; 
    }
    
  }

  public Exp getCondition(){
    return this.con;
  }

  public Exp getIfArg(){
    return this.ifArg;
  }

  public Exp getElseArg(){
    return this.elseArg;
  }

  public Boolean getElse(){
    return this.ifelse;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitExpIf(this, arg);
  }

  @Override
  public String toString() {
    if(ifelse)
    {
    	return "IF " +con.toString() +" THEN " +ifArg.toString() +" [ELSE " +elseArg.toString() +"]"; 
    }
    else
    { 
    	return "IF " +con.toString() +" THEN " +ifArg.toString(); 
    }
  }
}
