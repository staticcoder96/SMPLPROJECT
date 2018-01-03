package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplChar extends SmplValue{

	char val;

	public SmplChar(char v){
		super(SmplTypes.CHAR);
		val = v;
	}

	public SmplTypes getType(){
		return SmplTypes.CHAR;
	}

	@Override
	public String toString() {
		return ""+val+"";
	}
}
