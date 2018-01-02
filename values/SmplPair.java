package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;

public class SmplPair extends SmplValue<SmplPair> {

	SmplValue<?> val1, val2;

	public SmplPair(SmplValue<?> val1, SmplValue<?> val2){
		this.val1 = val1;
		this.val2 = val2;
	}

	@Override
	public SmplType getType(){
		return SmplType.PAIR;
	}

	public SmplValue<?> getFirstValue(){
		return val1;
	}

	public SmplValue<?> getSecondValue(){
		return val2;
	}

	// check if pairs have equivalent values
	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.PAIR){
			boolean eq = getFirstValue().eq(((SmplPair)arg).getFirstValue()).boolValue() && 
			getSecondValue().eq(((SmplPair)arg).getSecondValue()).boolValue();
			return make(eq);
		} else {
			return make(false);
		}
	}

	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.PAIR){
			boolean neq = getFirstValue().neq(((SmplPair)arg).getFirstValue()).boolValue() || 
			getSecondValue().neq(((SmplPair)arg).getSecondValue()).boolValue();
			return make(neq);
		} else {
			return make(true);
		}
	}

	@Override
	public String toString() {
		return "(" + val1.toString() + " . " + val2.toString() + ")";
	}
}