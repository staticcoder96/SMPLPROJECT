package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;

public class SmplPair extends SmplValue {

	SmplValue val1, val2;

	public SmplPair(SmplValue val1, SmplValue val2){
		super(SmplTypes.PAIR);
	}

	public SmplValue getFirstValue(){
		return val1;
	}

	public SmplValue getSecondValue(){
		return val2;
	}

	@Override
	public String toString() {
		return "(" + val1.toString() + " . " + val2.toString() + ")";
	}
}