package smpl.semantics;

import smpl.syntax.*;
import smpl.sys.SmplException;
import smpl.values.*;
import java.util.*;
import java.lang.Math;
//import java.awt.geom.Point2D;


public class SmplEvaluator implements Visitor<Environment , SmplValue > {

	protected SmplValue  result;

	@Override
	public SmplValue  visitSmplProgram(SmplProgram p, Environment  env) throws SmplException {
		result = p.getSeq().visit(this, env);
		return result;
	}


	@Override
	public SmplValue  visitStmtSequence(StmtSequence sseq, Environment  env) throws SmplException{
		ArrayList<Statement> seq = sseq.getSeq();
		result = SmplValue.make(0); 
		for (Statement s : seq){
			result = s.visit(this, env);
		}
		
		return result;
	}

	@Override
	public SmplValue  visitStmtDefinition(StmtDefinition sd, Environment  env) throws SmplException{

		if(sd.getVectorReference() == null){
		
			ArrayList<Exp> args = sd.getExps();
			ArrayList<String> vars = sd.getVars();

			int a_size = args.size();
			int v_size = vars.size();

			if(a_size == 1 && v_size != 1){
				Exp e = args.get(0);
				result = e.visit(this,env);
				if(result.getType() == SmplTypes.LIST){
					SmplList l = result.listValue();
					for(int i=0; i<v_size; i++){
						if(l.getNextValue() != null){
							env.put(vars.get(i), l.getCurrentValue());
							l = l.getNextValue();
						}
					}
				}
			} else if(a_size != v_size){
				throw new SmplException("Must assign same number of expressions as variables");
			} else {
				for(int i=0; i<a_size; i++)
					env.put(vars.get(i), args.get(i).visit(this, env));
			}
		} else {
			
			ExpVectorRef vr = sd.getVectorReference();
			
			Exp val = sd.getExp();
			
			String vecVar = vr.getVar();
			Exp ref = vr.getRef();
			result = ref.visit(this, env);
			
			if(result.getType() != SmplTypes.INTEGER && result.getType() != SmplTypes.REAL)
				throw new  SmplTypeException(SmplTypes.INTEGER, result.getType());
			
			int _ref = result.intValue();
			
			SmplValue  vec = env.get(vecVar);
			
			if(vec.getType() != SmplTypes.VECTOR)
				throw new  SmplTypeException(SmplTypes.VECTOR, vec.getType());
			
			ArrayList  lst = ((SmplVector)vec).getList();
			if(_ref < 0 || _ref >= lst.size())
				throw new SmplException("Reference to index [" + _ref + "] outside of bounds of " + vecVar + "[" + lst.size() + "]");
			lst.set(_ref, val.visit(this, env));

		}

		return SmplValue.make(true);
	}

	@Override
	public SmplValue  visitStmtLet(StmtLet let, Environment  env) throws SmplException{
		ArrayList<Binding> bindings = let.getBindings();
		Exp body = let.getBody();

		int size = bindings.size();
		String[] vars = new String[size];
		SmplValue [] vals = new SmplValue [size];
		Binding b;

		for (int i = 0; i < size; i++) {
		    b = bindings.get(i);
		    vars[i] = b.getVar();
		    
		    result = b.getValExp().visit(this, env);
		    vals[i] = result;
		}
		
		Environment  newEnv = new Environment<> (vars, vals, env);
		return body.visit(this, newEnv);
	}

	@Override
	public SmplValue  visitStmtPrint(StmtPrint sp, Environment  env) throws SmplException{
		result = sp.getExp().visit(this, env);
		System.out.print(result.toString() + sp.getTerminatingCharacter());
		return result;
	}

	@Override
	public SmplValue  visitExpAdd(ExpAdd exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.add(rval);
	}

	@Override
	public SmplValue  visitExpSub(ExpSub exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.sub(rval);
	}

	@Override
	public SmplValue  visitExpMul(ExpMul exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.mul(rval);
	}

	@Override
	public SmplValue  visitExpDiv(ExpDiv exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.div(rval);
	}

	@Override
	public SmplValue  visitExpMod(ExpMod exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.mod(rval);
	}

	@Override
	public SmplValue  visitExpPow(ExpPow exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.pow(rval);
	}

	@Override
	public SmplValue  visitExpLit(ExpLit exp, Environment  env) throws SmplException {
		return exp.getVal();
	}

	@Override
	public SmplValue visitExpVar(ExpVar exp, Environment  env) throws SmplException {
		return env.get(exp.getVar());
	}

	@Override
	public SmplValue visitExpProc(ExpProc proc, Environment  env) throws SmplException {
		return new ExpProc(proc, env);
	}

	@Override
	public SmplValue  visitExpProcedureCall(ExpProcedureCall exp, Environment  env) throws SmplException {

		
		ArrayList<Exp> args = new ArrayList(exp.getArguments());

		
		ExpProc proc;
		
		if(exp.getVar() != null){
			String id = exp.getVar();
			proc = (ExpProc) env.get(id);
		} else {
			Exp toEval = exp.getProcExp();
			result = toEval.visit(this, env);
			if(result.getType() != SmplTypes.PROCEDURE)
				throw new  SmplTypeException(SmplTypes.PROCEDURE, result.getType());
			else
				proc = (ExpProc) result;
		}

		ExpProc procExp = proc.getProcExp();
		Environment _env = proc.getClosingEnv();

		
		ArrayList<String> params = new ArrayList(procExp.getParameters());
		Exp body = procExp.getBody();

		int a_size = args.size();
		int p_size = params.size();
		ArrayList<String> vars = new ArrayList();
		ArrayList  vals = new ArrayList();

		String extra = procExp.getListVar();
		ArrayList  extras = new ArrayList();

		
		for(int i=0; i<a_size; i++){
			if(i<p_size){
				vars.add(params.remove(0));
				vals.add(args.remove(0).visit(this,env));
			} else {
				if(extra != null){
					vars.add(extra);
				}

				break;
			}

		}

		if(!args.isEmpty()){
			for(Exp e : args){
				extras.add(e.visit(this, env));
			}
			vals.add(SmplValue.makeList(extras));
		}

		Environment  newEnv = new Environment (vars, vals, _env);

		if(body == null){
			ArrayList<Exp> expList = procExp.getExpressions();
			ArrayList  vlist = new ArrayList();
			if(expList == null)
				throw new SmplException("Proc body not found.");
			for(Exp e : expList)
				vlist.add(e.visit(this, newEnv));
			return SmplValue.makeList(vlist);
		} else {
			return body.visit(this, newEnv);
		}

	}

	@Override
	public SmplValue  visitExpPair(ExpPair exp, Environment  env) throws SmplException {
		SmplValue  v1 = exp.getExpL().visit(this, env);
		SmplValue  v2 = exp.getExpR().visit(this, env);
		return SmplValue.makePair(v1, v2);
	}

	@Override
	public SmplValue  visitExpList(ExpList exp, Environment  env) throws SmplException {
		ArrayList  vals = new ArrayList();
		ArrayList<Exp> list = exp.getList();

		for(Exp lexp : list)
			vals.add(lexp.visit(this, env));

		return SmplValue.makeList(vals);
	}

	@Override
	public SmplValue  visitExpVector(ExpVector exp, Environment  env) throws SmplException {

		ArrayList<Exp> lst = exp.getList();
		ArrayList  vals = new ArrayList();

		for(Exp e : lst){
			result = e.visit(this, env);
				ExpProc procExp = proc.getProcExp();
				ArrayList<String> params = new ArrayList(procExp.getParameters());
				if(params.size() > 1 || procExp.getListVar() != null)
					throw new SmplException("Proc must have 1 or no parameters.");
				Exp body = procExp.getBody();
				
				for(int i=0; i<size; i++){
					ArrayList<SmplInt> args = new ArrayList();
					args.add(SmplValue.make(i));
					Environment  newEnv = new Environment(params, args, env);
					vals.add(body.visit(this,newEnv));
				}
		}

		return SmplValue.makeVector(vals);
	}

	@Override
	public SmplValue  visitExpVectorRef(ExpVectorRef exp, Environment  env) throws SmplException {

		Exp ref = exp.getRef();
		result = ref.visit(this, env);

		if(result.getType() != SmplTypes.INTEGER && result.getType() != SmplTypes.REAL)
			throw new  SmplTypeException(SmplTypes.INTEGER, result.getType());

		int _ref = result.intValue();

		String var = exp.getVar();
		SmplValue  val = env.get(var);

		ArrayList  lst = ((SmplVector)val).getList();

		if(_ref < 0 || _ref >= lst.size())
				throw new SmplException("Reference to index [" + _ref + "] outside of bounds of " + var + "[" + lst.size() + "]");

		return lst.get(_ref);
	}

	@Override
	public SmplValue  visitExpPairCheck(ExpPairCheck exp, Environment  env) throws SmplException {
		Exp toCheck = exp.getExp();
		result = toCheck.visit(this, env);
		SmplTypes type = result.getType();

		return new  SmplBoolean(type == SmplTypes.PAIR || type == SmplTypes.LIST || type == SmplTypes.EMPTYLIST);
	}

	@Override
	public SmplValue  visitExpCar(ExpCar exp, Environment  env) throws SmplException {
		
		result = exp.getExp().visit(this, env);
		return ((ExpPair)result).getFirstValue();
	}

	@Override
	public SmplValue  visitExpCdr(ExpCdr exp, Environment  env) throws SmplException {
		
		result = exp.getExp().visit(this, env);
		return ((ExpPair)result).getSecondValue();
	}

	@Override
	public SmplValue visitExpEqual(ExpEqual exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.eq(rval);
	}

	@Override
	public SmplValue visitExpGreater(ExpGreater exp, Environment  env) throws SmplException {
		SmplValue lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.gt(rval);
	}

	@Override
	public SmplValue  visitExpLess(ExpLess exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.lt(rval);
	}

	@Override
	public SmplValue  visitExpLessEq(ExpLessEq exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.le(rval);
	}

	@Override
	public SmplValue  visitExpGreatEq(ExpGreatEq exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.ge(rval);
	}

	@Override
	public SmplValue  visitExpNotEqual(ExpNotEqual exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.neq(rval);
	}

	@Override
	public SmplValue  visitExpLogicNot(ExpLogicNot exp, Environment  env) throws SmplException {
		result = exp.getExp().visit(this, env);
		return result.not();
	}

	@Override
	public SmplValue  visitExpLogicAnd(ExpLogicAnd exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.and(rval);
	}

	@Override
	public SmplValue  visitExpLogicOr(ExpLogicOr exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.or(rval);
	}

	@Override
	public SmplValue  visitExpBitNot(ExpBitNot exp, Environment  env) throws SmplException {
		result = exp.getExp().visit(this, env);
		return result.bitnot();
	}

	@Override
	public SmplValue  visitExpBitAnd(ExpBitAnd exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.bitand(rval);
	}

	@Override
	public SmplValue  visitExpBitOr(ExpBitOr exp, Environment  env) throws SmplException {
		SmplValue  lval, rval;
		lval = exp.getExpL().visit(this, env);
		rval = exp.getExpR().visit(this, env);
		return lval.bitor(rval);
	}

	@Override
	public SmplValue  visitExpSubStr(ExpSubStr exp, Environment  env) throws SmplException {

		String str = exp.getExpString().visit(this, env).stringValue();
		int lo = exp.getStart().visit(this, env).intValue();
		int hi = exp.getEnd().visit(this, env).intValue();

		if(lo < 0 || lo > str.length())
			throw new SmplException("Starting index out of bounds");
		else if (hi > str.length())
			throw new SmplException("Ending index out of bounds");
		else if (hi < lo)
			return SmplValue.makeStr("");
		else
			return SmplValue.makeStr(str.substring(lo,hi));
	}

	@Override
	public SmplValue  visitExpEqv(ExpEqv exp, Environment  env) throws SmplException {

		SmplValue  exp1 = exp.getExpFirst().visit(this, env);
		SmplValue  exp2 = exp.getExpSecond().visit(this, env);

		if(exp1 == exp2)
		{
			return SmplValue.make(true);
		}else { return SmplValue.make(false); }
		
	}

	@Override
	public SmplValue  visitExpEqual(ExpEqual exp, Environment  env) throws SmplException {

		String exp1 = exp.getExpFirst().visit(this, env).toString();
		String exp2 = exp.getExpSecond().visit(this, env).toString();
		return SmplValue.make(exp1.equals(exp2));
	}

	@Override
	public SmplValue  visitExpCall(ExpCall exp, Environment  env) throws SmplException {

		Exp expl = exp.getExpL();
		Exp expr = exp.getExpR();

		
		ExpProc proc = (ExpProc) expl.visit(this, env);
		ExpProc toEval = proc.getProcExp();

		
		ArrayList<String> _params = new ArrayList(toEval.getParameters());
		int p_size = _params.size();

		
		Exp body = toEval.getBody();

		
		SmplList lst = (SmplList) expr.visit(this, env);

		
		ArrayList  args = new ArrayList();
		ArrayList  extras = new ArrayList();

		
		ArrayList<String> params = new ArrayList();

		
		String e = toEval.getListVar();
		if(e != null)
			params.add(e);

		
		if(!extras.isEmpty())
			args.add(SmplValue.makeList(extras));

		
		Environment  newEnv = new Environment (params, args, env);

		//System.out.println(newEnv);
		return body.visit(this, newEnv);

	}

	@Override
	public SmplValue  visitExpLazy(ExpLazy exp, Environment  env) throws SmplException {
		
		Boolean exists;

		try 
		{
		    env.get("101lazy");
		    exists = true;
		}catch(SmplException e) { exists = false; }

		if(!exists)
		{
			Exp body = exp.getExp();
			env.put("101lazy", body.visit(this, env));
		}else { result = env.get("101lazy"); }
		
		return result;

	}

	@Override
	public SmplValue  visitExpDef(ExpDef exp, Environment  env) throws SmplException {
		Exp body = exp.getExp();
		String var = exp.getVar();

		env.put(var, body.visit(this, env));

		return result;

	}

	

	@Override
	public SmplValue  visitExpRead(ExpRead exp, Environment  env) throws SmplException {

		Scanner input = new Scanner(System.in);
		result = SmplValue.makeStr(input.nextLine());
		return result;
	}

	@Override
	public SmplValue  visitExpReadInt(ExpReadInt exp, Environment  env) throws SmplException {
		
		Scanner input = new Scanner(System.in);
		if(input.hasNextInt()){
			result = SmplValue.make(input.nextInt());
			return result;
		} else {
			throw new  SmplTypeException("Type Error: Input must be of type " + SmplTypes.INTEGER);
		}
	}

	@Override
	public SmplValue  visitExpIf(ExpIf exp, Environment  env) throws SmplException {
		
		Boolean conBool;
		if(exp.getElse())
		{
			Exp conExp = exp.getCondition();
			SmplValue conValue = conExp.visit(this, env);

			try{
				conBool = conValue.boolValue();
			}catch (Exception e){ throw new SmplException("Condition must evaluate to a boolean."); }
		
			if(conBool)
			{
				Exp ifArgBody = exp.getIfArg();
				result = ifArgBody.visit(this, env);
			}
			else
			{ 
				Exp elseArgBody = exp.getElseArg();
				result = elseArgBody.visit(this, env); 
			}
		}
		else
		{
			Exp conExp = exp.getCondition();
			SmplValue conValue = conExp.visit(this, env);

			try{
				conBool = conValue.boolValue();
			}catch (Exception e){ throw new SmplException("Condition must evaluate to a boolean."); }
			
			if(conBool)
			{
				Exp ifArgBody = exp.getIfArg();
				result = ifArgBody.visit(this, env);
			}
		}

		return result;

	}

	@Override
	public SmplValue  visitExpCase(ExpCase exp, Environment  env) throws SmplException {

		
		Exp elseCond = null;
		
		for(ExpPair _case : lst){
			Exp cond = _case.getExpL();
			SmplValue  check = cond.visit(this, env);
			
			if(check.getType() == SmplTypes.STRING){
				if(check.stringValue().equals("else")){
					elseCond = _case.getExpR();
					break;
				}
			}
			
			if(check.getType() != SmplTypes.BOOLEAN)
				throw new  SmplTypeException(SmplTypes.BOOLEAN, check.getType());

			if(check.boolValue()){
				Exp body = _case.getExpR();
				result = body.visit(this, env);
				return result;
			}
		}
		
		if(elseCond != null)
			result = elseCond.visit(this, env);
		
		return result;
	}
}
