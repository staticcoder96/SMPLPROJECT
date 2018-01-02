/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.values;

import fractal.semantics.InvocationContext;
import fractal.semantics.FractalEvaluator;
import fractal.syntax.ASTStatement;
import fractal.syntax.ASTStmtSequence;
import fractal.semantics.FractalState;
import fractal.sys.FractalException;
import java.util.ArrayList;

/**
 *
 * @author newts
 */
public class PrimitiveFractal extends Fractal {

    ASTStmtSequence body;
    
    public PrimitiveFractal(double scale, ArrayList<ASTStatement> b, FractalState st) {
        super(scale, st);
        body = new ASTStmtSequence(b);
    }

    @Override
    public ASTStmtSequence getBody() {
        return body;
    }
    
    @Override
    public InvocationContext deriveContext(InvocationContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString() {
        return "<Primitive Fractal>";
    }
    
    @Override
    public String toLongString() {
        return "FRACTAL\n" + body + "END";
    }
}
