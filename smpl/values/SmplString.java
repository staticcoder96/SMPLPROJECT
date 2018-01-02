package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

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
	public SmplValue add(SmplValue arg) throws SmplException {
		return val + arg.SmplValue();
	}

	@Override
	public String toString() {
		return val;
	}
}
