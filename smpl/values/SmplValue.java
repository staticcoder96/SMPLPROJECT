package smpl.values;

import java.util.*;
import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;
import smpl.syntax.*;

public class SmplValue {
    public static final SmplValue NO_VALUE = null;
    
//    private Object val;
    private SmplTypes type;
    
    public static SmplValue make(int val) {
        return new SmplInt(val);
    }
    
    public static SmplValue make(double val) {
        return new SmplReal(val);
    }

    public static SmplValue make(boolean val) {
        return new SmplBoolean(val);
    }

    public static SmplValue make(char val) {
        return new SmplChar(val);
    }
    
    public static SmplValue makeStr(String val) {
        return new SmplString(val);
    }

    public static SmplValue makeList(SmplValue val, SmplList next) {
        return new SmplList(val,next);
    }

    public static SmplValue makePair(SmplValue val1, SmplValue val2) {
        return new SmplPair(val1, val2);
    }

    public SmplValue(SmplTypes type) {
        this.type = type;
    }
    
    protected void setType(SmplTypes t) {
        type = t;
    }

    public SmplTypes getType() {
        return type;
    }
    
    /**
     *
     * @return <code>true</code> if this value represents an integer
     */
    public boolean isInt() {
	return type == SmplTypes.INTEGER;
    }
    
    /**
     *
     * @return The integer that this value represents
     * @throws SmplException if this value does not represent an integer 
     */
    public int intValue() throws SmplException {
        throw new SmplTypeException(SmplTypes.INTEGER, type);
    }

    /**
     *
     * @return <code>true</code> if this value represents a floating point number
     */
    public boolean isReal() {
	return type == SmplTypes.REAL;
    }
    
    /**
     *
     * @return The floating point number that this value represents
     * @throws SmplException if this value does not represent a real number 
     */
    public double realValue() throws SmplException {
        throw new SmplTypeException(SmplTypes.REAL, type);
    }
    
    public boolean boolValue() throws SmplException {
        throw new SmplTypeException(SmplTypes.BOOLEAN, type);
    }

    /*public static ExpList makeList(SmplValue val1, SmplValue val2) {
        return new ExpList(val1, val2);
    }

    public static ExpList makeList(ArrayList<?> v) {
        if(v.size() > 0){
            return new ExpList(v.remove(0), makeList(v));
        } else {
            return new ExpEmptyList();
        }

    } */
    /**
     *
     * @return <code>true</code> if this value represents either an (exact) 
     * integer or a real (inexact floating point) number.
     */
    public boolean isNumber() {
        return isInt() || isReal();
    }

    
    /**
     * Add two values
     * @param val The value to be added to this one.
     * @return The sum of the two values
     * @throws SmplException if the types of this value and the given one are
     * incompatible.
     */
    public SmplValue add(SmplValue val) throws SmplException {
        throw new SmplTypeException(SmplTypes.REAL, val.getType());
    }
    
    /**
     * Subtract two values
     * @param val The value to be subtracted from this one.
     * @return The difference of the two values
     * @throws SmplException if the types of this value and the given one are
     * incompatible.
     */
    public SmplValue sub(SmplValue val) throws SmplException {
        throw new SmplTypeException(SmplTypes.REAL, val.getType());
    }
    
    /**
     * Multiply two values
     * @param val The value to be multiplied by this one.
     * @return The product of the two values
     * @throws SmplException if the types of this value and the given one are
     * incompatible.
     */
    public SmplValue mul(SmplValue val) throws SmplException {
        throw new SmplTypeException(SmplTypes.REAL, val.getType());
    }
    
    /**
     * Divide two values to find the quotient
     * @param val The divisor.
     * @return The quotient of this value and the given one.
     * @throws SmplException if the types of this value and the given one are
     * incompatible.
     */
    public SmplValue div(SmplValue val) throws SmplException {
        throw new SmplTypeException(SmplTypes.REAL, val.getType());
    }
    
    /**
     * Divide two values to find the remainder
     * @param val The divisor
     * @return The modulo (remainder) of this value and the given one.
     * @throws SmplException if the types of this value and the given one are
     * incompatible.
     */
    public SmplValue mod(SmplValue val) throws SmplException {
        throw new SmplTypeException(SmplTypes.REAL, val.getType());
    }
    
}
