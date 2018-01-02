package fractal.values;

import fractal.sys.FractalException;
import fractal.sys.FractalTypeException;

public class FractalValue {
    public static final FractalValue NO_VALUE = null;
    
//    private Object val;
    private FractalTypes type;
    
    public static FractalValue make(int val) {
        return new FractalInt(val);
    }
    
    public static FractalValue make(double val) {
        return new FractalReal(val);
    }
    
    public FractalValue(FractalTypes type) {
        this.type = type;
    }
    
    protected void setType(FractalTypes t) {
        type = t;
    }

    public FractalTypes getType() {
        return type;
    }
    
    /**
     *
     * @return <code>true</code> if this value represents an integer
     */
    public boolean isInt() {
	return type == FractalTypes.INTEGER;
    }
    
    /**
     *
     * @return The integer that this value represents
     * @throws FractalException if this value does not represent an integer 
     */
    public int intValue() throws FractalException {
        throw new FractalTypeException(FractalTypes.INTEGER, type);
    }

    /**
     *
     * @return <code>true</code> if this value represents a floating point number
     */
    public boolean isReal() {
	return type == FractalTypes.REAL;
    }
    
    /**
     *
     * @return The floating point number that this value represents
     * @throws FractalException if this value does not represent a real number 
     */
    public double realValue() throws FractalException {
        throw new FractalTypeException(FractalTypes.REAL, type);
    }
    
    /**
     *
     * @return <code>true</code> if this value represents either an (exact) 
     * integer or a real (inexact floating point) number.
     */
    public boolean isNumber() {
        return isInt() || isReal();
    }

    /**
     *
     * @return <code>true</code> if this value is an instance of a fractal
     */
    public boolean isFractal() {
	return type == FractalTypes.FRACTAL;
    }
    
    public Fractal fractalValue() throws FractalException {
        if (type == FractalTypes.FRACTAL) 
            return (Fractal) this;
        else
            throw new FractalTypeException(FractalTypes.FRACTAL, type);
    }
    
    /**
     * Add two values
     * @param val The value to be added to this one.
     * @return The sum of the two values
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public FractalValue add(FractalValue val) throws FractalException {
        throw new FractalTypeException(FractalTypes.REAL, val.getType());
    }
    
    /**
     * Subtract two values
     * @param val The value to be subtracted from this one.
     * @return The difference of the two values
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public FractalValue sub(FractalValue val) throws FractalException {
        throw new FractalTypeException(FractalTypes.REAL, val.getType());
    }
    
    /**
     * Multiply two values
     * @param val The value to be multiplied by this one.
     * @return The product of the two values
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public FractalValue mul(FractalValue val) throws FractalException {
        throw new FractalTypeException(FractalTypes.REAL, val.getType());
    }
    
    /**
     * Divide two values to find the quotient
     * @param val The divisor.
     * @return The quotient of this value and the given one.
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public FractalValue div(FractalValue val) throws FractalException {
        throw new FractalTypeException(FractalTypes.REAL, val.getType());
    }
    
    /**
     * Divide two values to find the remainder
     * @param val The divisor
     * @return The modulo (remainder) of this value and the given one.
     * @throws FractalException if the types of this value and the given one are
     * incompatible.
     */
    public FractalValue mod(FractalValue val) throws FractalException {
        throw new FractalTypeException(FractalTypes.REAL, val.getType());
    }
    
}
