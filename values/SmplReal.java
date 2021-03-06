package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplReal extends SmplValue {
    
    double value;

    public SmplReal(double d) {
        super(SmplTypes.REAL);
        value = d;
    }
    
    @Override
    public boolean isReal() {
        return true;
    }
    
    @Override
    public double realValue() {
        return value;
    }
    
    public int intValue() {
        return (int) value;
    }
    
    @Override
    public SmplValue add(SmplValue val) throws SmplException {
        if (val.isNumber()) {
            return new SmplReal(realValue() + val.realValue());
        } else {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        }
    }

    @Override
    public SmplValue sub(SmplValue val) throws SmplException {
        if (val.isNumber()) {
            return new SmplReal(realValue() - val.realValue());
        } else {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        }
    }

    @Override
    public SmplValue mul(SmplValue val) throws SmplException {
        if (val.isNumber()) {
            return new SmplReal(realValue() / val.realValue());
        } else {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        }
    }

    @Override
    public FractalValue div(FractalValue val) throws FractalException {
        if (val.isNumber()) {
            return new FractalReal(realValue() / val.realValue());
        } else {
            throw new FractalTypeException(FractalTypes.REAL, val.getType());
        }
    }
    
    @Override
    public SmplValue mod(SmplValue val) throws SmplException {
        if (val.isNumber()) {
            return new SmplReal(realValue() % val.realValue());
        } else {
            throw new SmplTypeException(SmplTypes.REAL, val.getType());
        }
    }
}
