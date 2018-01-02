public class StmtCase extends Statement{

  ArrayList<Options> options;
  ArrayList<Exp> pred;
  ArrayList<Exp> con;
  Exp exp;
  Boolean ifelse;

  public StmtCase(Exp e){
    exp = e;
    form = true;
  }
  
  public StmtCase(Options lst){
    pred.add(lst.getPred());
    con.add(lst.getCon());
    ifelse= false;
  }
  
  public StmtCase(ArrayList<Options> lst){
    options=lst;
    for(Options i:lst)
    {
      pred.add(i.getPred());
      con.add(i.getCon());
    }
    ifelse= false;
  }

  public ArrayList<Option> getOptions(){
    return options;
  }

  public ArrayList<Exp> getPred(){
    return pred;
  }

  public ArrayList<Exp> getCon(){
    return cons;
  }

  public Exp getExp(){
    return exp;
  }
  
  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    return v.visitStmtCase(this, arg);
  }

  @Override
  public String toString() {
    if(ifelse)
    {
      return "ELSE: " +exp.toString();
    }
    else{return "case{ \t [" + options.toString() + "] \t}";}
  }
}
