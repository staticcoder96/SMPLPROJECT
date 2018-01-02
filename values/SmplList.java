package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;

public class SmplList extends SmplPair {

	public SmplList(SmplValue<?> val, SmplList next){
		super(val, next);
	}

	public SmplList(SmplValue<?> val, SmplEmptyList nil){
		super(val, nil);
	}

	@Override
	public SmplType getType(){
		return SmplType.LIST;
	}

	@Override
	public SmplList listValue(){
		return this;
	}

	public SmplValue<?> getCurrentValue(){
		return getFirstValue();
	}

	public SmplList getNextValue(){
		return (SmplList) getSecondValue();
	}

	// check if pairs have equivalent values
	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.LIST){
			boolean eq = getFirstValue().eq(((SmplList)arg).getFirstValue()).boolValue() && 
			getSecondValue().eq(((SmplList)arg).getSecondValue()).boolValue();
			return make(eq);
		} else {
			return make(false);
		}
	}

	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.LIST){
			boolean neq = getFirstValue().neq(((SmplList)arg).getFirstValue()).boolValue() || 
			getSecondValue().neq(((SmplList)arg).getSecondValue()).boolValue();
			return make(neq);
		} else {
			return make(true);
		}
	}

	@Override
	public String toString() {
		return val1.toString() + " -> " + val2.toString();
	}
}