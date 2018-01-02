package smpl.values;

import smpl.sys.SmplException;
import static smpl.values.SmplValue.make;
import static smpl.values.SmplValue.makeStr;

public class SmplString extends SmplValue<SmplString> {

	String val;

	public SmplString(String v){
		val = v;
	}

	public static String escape(String str){
		String newStr = "";

		for (int i=0; i<str.length(); i++){
			// get current char in string
			char c = str.charAt(i);
			// add c to new string unless c is a backslash or the last character in the string
			if(c != '\\' || i == str.length()) {
				newStr += c;
			} else {
				char nxt = str.charAt(i+1);
				if(nxt == 'n'){
					newStr += '\n';
				} else if(nxt == 't'){
					newStr += '\t';
				} else if(nxt == '\\'){
					newStr += '\\';
				} else if (nxt == '\"'){
					newStr += '\"';
				} else {
					newStr += c;
					continue;
				}

				i++;
			}
		}

		return newStr;
	}

	public SmplType getType(){
		return SmplType.STRING;
	}

	@Override
	public SmplValue<?> add(SmplValue<?> arg) throws SmplException {
		return makeStr(val + arg.stringValue());
	}

	public SmplValue<?> eq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.STRING)
			return make(val.compareTo(arg.stringValue()) == 0);
		else
			return make(false);
	}

	public SmplValue<?> gt(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.STRING)
			return make(val.compareTo(arg.stringValue()) > 0);
		else
			throw new TypeSmplException(SmplType.STRING, arg.getType());
	}

	public SmplValue<?> lt(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.STRING)
			return make(val.compareTo(arg.stringValue()) < 0);
		else
			throw new TypeSmplException(SmplType.STRING, arg.getType());
	}

	public SmplValue<?> le(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.STRING)
			return make(val.compareTo(arg.stringValue()) <= 0);
		else
			throw new TypeSmplException(SmplType.STRING, arg.getType());
	}

	public SmplValue<?> ge(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.STRING)
			return make(val.compareTo(arg.stringValue()) >= 0);
		else
			throw new TypeSmplException(SmplType.STRING, arg.getType());
	}

	public SmplValue<?> neq(SmplValue<?> arg) throws SmplException {
		if(arg.getType() == SmplType.STRING)
			return make(val.compareTo(arg.stringValue()) != 0);
		else
			return make(true);
	}

	// return literal values

	public int intValue() throws TypeSmplException {
		return Integer.parseInt(val);
	}

	public double doubleValue() throws TypeSmplException {
		return Double.valueOf(val);
	}

	@Override
	public String toString() {
		return val;
	}
}