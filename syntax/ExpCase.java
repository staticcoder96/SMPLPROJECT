public class ExpCase extends Exp {

  ArrayList<ExpPair> list;

  public ExpCase(){
    list = new ArrayList();
  }

  public ExpCase(ArrayList<ExpPair> list){
    this.list = list;
  }

  public ArrayList<ExpPair> getList(){
    return list;
  }

  @Override
  public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException{
    return v.visitExpCase(this, arg);
  }

  @Override
  public String toString() {
    return list.toString();
  }
}

