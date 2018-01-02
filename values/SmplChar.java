package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;
import static smpl.values.SmplValue.makeChar;

public class SmplChar extends SmplValue<SmplChar> {

	char val;

	public SmplChar() {
		this(0x20);
	}

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
	public SmplValue<?> add(SmplValue<?> arg) throws SmplException {
		return makeChar("" + (char)(val + arg.intValue()));
	}

	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		SmplType type = arg.getType();
		if(type == SmplType.INTEGER ||
			type == SmplType.CHARACTER ||
			type == SmplType.REAL)
			return make(val == arg.charValue());
		else
			return make(false);
	}

	public SmplValue<?> gt(SmplValue<?> arg) throws SmplException {
		SmplType type = arg.getType();
		if(type == SmplType.INTEGER ||
			type == SmplType.CHARACTER ||
			type == SmplType.REAL)
			return make(val > arg.charValue());
		else
			throw new TypeSmplException(SmplType.CHARACTER, arg.getType());
	}

	public SmplValue<?> lt(SmplValue<?> arg) throws SmplException {
		SmplType type = arg.getType();
		if(type == SmplType.INTEGER ||
			type == SmplType.CHARACTER ||
			type == SmplType.REAL)
			return make(val < arg.charValue());
		else
			throw new TypeSmplException(SmplType.CHARACTER, arg.getType());
	}

	public SmplValue<?> le(SmplValue<?> arg) throws SmplException {
		SmplType type = arg.getType();
		if(type == SmplType.INTEGER ||
			type == SmplType.CHARACTER ||
			type == SmplType.REAL)
			return make(val <= arg.charValue());
		else
			throw new TypeSmplException(SmplType.CHARACTER, arg.getType());
	}

	public SmplValue<?> ge(SmplValue<?> arg) throws SmplException {
		SmplType type = arg.getType();
		if(type == SmplType.INTEGER ||
			type == SmplType.CHARACTER ||
			type == SmplType.REAL)
			return make(val >= arg.charValue());
		else
			throw new TypeSmplException(SmplType.CHARACTER, arg.getType());
	}

	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		SmplType type = arg.getType();
		if(type == SmplType.INTEGER ||
			type == SmplType.CHARACTER ||
			type == SmplType.REAL)
			return make(val != arg.charValue());
		else
			return make(true);
	}

	public SmplValue<?> bitnot() throws SmplException {
		return make(~val);
	}

	public SmplValue<?> bitor(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.INTEGER ||
			arg.getType() == SmplType.CHARACTER)
			return make(val | arg.charValue());
		else
			throw new TypeSmplException(SmplType.CHARACTER, arg.getType());
	}

	public SmplValue<?> bitand(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.INTEGER ||
			arg.getType() == SmplType.CHARACTER)
			return make(val & arg.charValue());
		else
			throw new TypeSmplException(SmplType.CHARACTER, arg.getType());
	}


	// return literal values

	public int intValue() throws TypeSmplException {
		return (int) val;
	}

	public double doubleValue() throws TypeSmplException {
		return (double) val;
	}

	public char charValue() throws TypeSmplException {
		return val;
	}

	@Override
	public String toString() {
		return ""+val;
	}
}