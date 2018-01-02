package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

public class SmplChar extends SmplValue{

	char val;

	public SmplChar(char v){
		val = v;
	}

	public SmplChar(int v){
		val = (char) v;
	}

	@Override
	public SmplType getType(){
		return SmplType.CHARACTER;
	}

	@Override
	public String toString() {
		return ""+val+"";
	}
}
