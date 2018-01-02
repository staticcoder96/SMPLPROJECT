public class ExpReadInt extends Exp{

  public ExpReadInt() {
  }
  
  public ExpReadInt(Exp exp) {
       this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpReadInt(this, arg);
  }

  @Override
  public String toString(){
    return "readInt(" + exp.toString() + ")";
  }
}
