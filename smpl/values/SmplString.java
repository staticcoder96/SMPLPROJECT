package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;
import smpl.values.*;

public class SmplString extends SmplValue {

	String val;

	public SmplString(String v){
		super(SmplTypes.STRING);
		val = v;
	}

	public SmplTypes getType(){
		return SmplTypes.STRING;
	}

	@Override
	public SmplValue add(SmplValue arg) throws  SmplException {
		return val + makeStr(arg);
	}

	@Override
	public String toString() {
		return val;
	}
}
