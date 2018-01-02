/*
 * CompositeFractal.java
 * Created on 28-Oct-2017, 1:09:46 PM
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
import fractal.syntax.ASTStmtSequence;
import fractal.sys.FractalException;

/**
 * The class that represents the result of composing two fractals
 * @author newts
 */
public class CompositeFractal extends Fractal {
    Fractal fractal1;
    Fractal fractal2;

    public CompositeFractal(Fractal f1, Fractal f2, FractalState st) {
	// figure this out yourself
        super(0.0, null);  // wrong: placeholder to permit compilation
	
    }
    
    @Override
    public ASTStmtSequence getBody() {
	// where the magic needs to happen
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public InvocationContext deriveContext(InvocationContext context) {
	// also where some magic is needed
	throw new UnsupportedOperationException("Not supported yet.");
    }

}
