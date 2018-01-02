package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplInt extends SmplValue {
    int value;
    
    public SmplInt(int val) {
        super(SmplTypes.INTEGER);
        value = val;
    }
    
    @Override
    public boolean isInt() {
        return true;
    }
    
    @Override
    public int intValue() {
        return value;
    }
    
    @Override
    public double realValue() {
        return value;
    }
    
    @Override
    public SmplValue add(SmplValue val) throws SmplException {
        if (! val.isNumber()) {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        } else if (val.isInt()) {
            return new SmplInt(intValue() + val.intValue());
        } else {
            return new SmplReal(realValue() + val.realValue());
        }
    }
    
    @Override
    public SmplValue sub(SmplValue val) throws SmplException {
        if (! val.isNumber()) {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        } else if (val.isInt()) {
            return new SmplInt(intValue() - val.intValue());
        } else {
            return new SmplReal(realValue() - val.realValue());
        }
    }
    @Override
    public SmplValue mul(SmplValue val) throws SmplException {
        if (! val.isNumber()) {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        } else if (val.isInt()) {
            return new SmplInt(intValue() * val.intValue());
        } else {
            return new SmplReal(realValue() * val.realValue());
        }
    }
    @Override
    public SmplValue div(SmplValue val) throws SmplException {
        if (! val.isNumber()) {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        } else if (val.isInt()) {
            return new SmplInt(intValue() / val.intValue());
        } else {
            return new SmplReal(realValue() / val.realValue());
        }
    }
    
    @Override
    public SmplValue mod(SmplValue val) throws SmplException {
        if (! val.isNumber()) {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        } else if (val.isInt()) {
            return new SmplInt(intValue() % val.intValue());
        } else {
            return new SmplReal(realValue() % val.realValue());
        }
    }
}
