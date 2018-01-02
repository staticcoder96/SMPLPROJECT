/*
 * SequencedFractal.java
 * Created on 30-Oct-2017, 12:45:20 AM
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fractal.values;

import fractal.semantics.InvocationContext;
import fractal.semantics.FractalEvaluator;
import fractal.semantics.FractalState;
import fractal.syntax.ASTFracInvocation;
import fractal.syntax.ASTStmtSequence;
import fractal.sys.FractalException;
import fractal.values.Fractal;
import fractal.values.FractalValue;

/**
 *
 * @author newts
 */
public class SequencedFractal extends Fractal {
    
    Fractal fractal1;
    Fractal fractal2;
    // hint: you will need something else besides those 2 data members

    public SequencedFractal(Fractal f1, Fractal f2, FractalState state) {
	// complete this properly
        super(0.0, null);  // wrong: placeholder to permit compilation
    }

    @Override
    public ASTStmtSequence getBody() {
        // unimplemented magic
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public InvocationContext deriveContext(InvocationContext context) {
	// more magic needed here
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
