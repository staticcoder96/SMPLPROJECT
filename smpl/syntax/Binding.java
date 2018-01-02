package smpl.syntax;

public class Binding {

    String var;
    Exp valExp;

    public Binding(String id, Exp v) {
	   var = id;
	   valExp = v;
    }

    public String getVar() {
	   return var;
    }

    public Exp getValExp() {
	   return valExp;
    }

}
