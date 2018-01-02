package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplBool extends SmplValue{

	boolean val;

	public SmplBool(boolean v){
    super(SmplTypes.BOOLEAN);
		val = v;
	}

	@Override
	public SmplType getType(){
		return SmplType.BOOLEAN;
	}


	public SmplValue eq(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplType.BOOLEAN)
			return new val == arg.boolValue();
		else
			return false;
	}

	public SmplValue neq(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplType.BOOLEAN)
			return new val != arg.boolValue();
		else
			return true;
	}

	public SmplValue or(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplType.BOOLEAN)
			return new val || arg.boolValue();
		else
			throw new TypeSmplException(SmplType.BOOLEAN, arg.getType());
	}

	public SmplValue and(SmplValue arg) throws SmplException {
		if(arg.getType() == SmplType.BOOLEAN)
			return new val && arg.boolValue();
		else
			throw new TypeSmplException(SmplType.BOOLEAN, arg.getType());
	}

	public SmplValue not() throws SmplException {
			return make(!val);
	}

	public boolean boolValue() throws TypeSmplException {
		return val;
	}

	@Override
	public String toString() {
		return val ? "true" : "false";
	}
}
