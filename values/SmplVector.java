package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;
import java.util.*;

public class SmplVector extends SmplValue<SmplVector> {

	ArrayList<SmplValue<?>> lst;

	public SmplVector(ArrayList<SmplValue<?>> lst){
		this.lst = lst;
	}

	@Override
	public SmplType getType(){
		return SmplType.VECTOR;
	}

	@Override
	public SmplVector vectorValue(){
		return this;
	}

	public ArrayList<SmplValue<?>> getList(){
		return lst;
	}

	// check if pairs have equivalent values
	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.VECTOR){
			boolean eq = lst.equals(((SmplVector)arg).getList());
			return make(eq);
		} else {
			return make(false);
		}
	}

	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.VECTOR){
			boolean eq = lst.equals(((SmplVector)arg).getList());
			return make(!eq);
		} else {
			return make(true);
		}
	}

	@Override
	public String toString() {
		String vals = "[:";
		for(int i=0; i<lst.size()-1; i++)
			vals += lst.get(i) + ", ";

		if(!lst.isEmpty())
			vals += lst.get(lst.size()-1);

		vals += ":]";

		return vals;
	}
}