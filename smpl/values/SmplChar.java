package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplChar extends SmplValue{

	char val;

	public SmplChar(char v){
		super(SmplTypes.INTEGER);
		val = v;
	}

	public SmplChar(int v){
		val = (char) v;
	}

	@Override
	public SmplTypes getType(){
		return SmplTypes.CHAR;
	}

	@Override
	public String toString() {
		return ""+val+"";
	}
}
