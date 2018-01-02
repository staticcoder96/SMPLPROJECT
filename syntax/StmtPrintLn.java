public class StmtPrintLn extends Exp {

    SMPLExp smplExp;

    public StmtPrintLn() {
    }

    public StmtPrintLn(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return Exp;
    }

    @Override
    public <S, T> T visit(SMPLVisitor<S, T> v, S arg) throws SMPLException {
        return v.visitPrintStmt(this, arg);
    }

    @Override
    public String toString() {
        return "print: " + Exp.toString();
    }
}
