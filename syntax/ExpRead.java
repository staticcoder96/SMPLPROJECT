public class ExpRead extends Exp{

  public ExpRead(){
  }
  
  public ExpRead(Exp exp){
        this.exp = exp;
  }

    public Exp getExp(){
        return exp;
    }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpRead(this, arg);
  }

  @Override
  public String toString(){
    return "read(" + exp.toString +")";
  }
}
