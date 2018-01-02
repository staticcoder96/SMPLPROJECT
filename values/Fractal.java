/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.values;

import fractal.semantics.InvocationContext;
import fractal.semantics.FractalState;
import fractal.syntax.ASTStmtSequence;

/**
 *
 * @author newts
 */
public abstract class Fractal extends FractalValue {
    
    FractalState state;
    double scaleVal;
    
    public Fractal(double sv, FractalState st) {
        super(FractalTypes.FRACTAL);
        scaleVal = sv;
        state = st;
    }

    public Fractal(FractalState st) {
        this(1.0, st);
    }  
       
    @Override
    public boolean isFractal() {
        return true;
    }
        
    public double getScaleVal() {
        return scaleVal;
    }
    
    /**
     *
     * @return The code defining the actions of this fractal
     */
    public abstract ASTStmtSequence getBody();
    
    /**
     * Create the invocation context that should be used when this fractal is 
     * invoked (typically as a reference to 'self' in the body of a primitive
     * fractal).  The typical resulting context will have the same reference to
     * self, a level that is one less, and a distance that is scaled by
     * the Fractal's scale multiplier, relative to the current context.  But
     * special forms that generate fractals will typically need to deviate
     * from this behaviour.
     * @param context The current context for the currently running invocation
     * of the fractal's body
     * @return A new context that is derived from the current context, which 
     * will be installed as the context for the next invocation of the fractal.
     */
    public abstract InvocationContext deriveContext(InvocationContext context);
    
    @Override
    public String toString() {
        return "<Anonymous Fractal>";
    }
    
    /**
     *
     * @return A longer description of this fractal, than the toString() method.
     */
    public String toLongString() {
        return toString();
    }
}
