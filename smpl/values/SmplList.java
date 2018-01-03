package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;

public class SmplList extends SmplPair {

	public SmplList(SmplValue val, SmplList next){
		super(val, next);
	}

	public SmplTypes getType(){
		return SmplTypes.LIST;
	}

	public SmplList listValue(){
		return this;
	}

	public SmplValue getCurrentValue(){
		return getFirstValue();
	}

	public SmplList getNextValue(){
		return (SmplList) getSecondValue();
	}

	@Override
	public String toString() {
		return val1.toString() + " -> " + val2.toString();
	}
}