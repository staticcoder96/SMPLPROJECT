package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplBoolean extends SmplValue{

	boolean val;

	public SmplBoolean(boolean v){
    super(SmplTypes.BOOLEAN);
		val = v;
	}

	@Override
	public SmplTypes getType(){
		return SmplTypes.BOOLEAN;
	}

	public SmplValue eq(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplTypes.BOOLEAN)
			return make(val == arg.boolValue());
		else
			return make(false);
	}

	public SmplValue neq(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplTypes.BOOLEAN)
			return make(val != arg.boolValue());
		else
			return make(true);
	}

	public SmplValue or(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplTypes.BOOLEAN)
			return make(val || arg.boolValue());
		else
			throw new SmplTypeException(SmplTypes.BOOLEAN, arg.getType());
	}

	public SmplValue and(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplTypes.BOOLEAN)
			return make(val && arg.boolValue());
		else
			throw new SmplTypeException(SmplTypes.BOOLEAN, arg.getType());
	}

	public SmplValue not() throws SmplException {
			return make(!val);
	}

	//@Override
	public boolean boolValue() throws SmplTypeException {
		return val;
	}

	@Override
	public String toString() {
		return val ? "true" : "false";
	}
}
