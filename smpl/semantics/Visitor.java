package smpl.semantics;

import smpl.syntax.*;
import smpl.sys.SmplException;

public interface Visitor<S, T> {

	// program
	public T visitSmplProgram(SmplProgram p, S arg) throws SmplException;

	// statements
	public T visitStmtSequence(StmtSequence exp, S arg) throws SmplException;
	public T visitStmtDefinition(StmtDefinition exp, S arg) throws SmplException;
	public T visitStmtLet(StmtLet exp, S arg) throws SmplException;
	public T visitPrintStmt(PrintStmt exp, S arg) throws SmplException;
	public T visitExp(Exp exp, S arg) throws SmplException;
	public T visitExpAdd(ExpAdd exp, S arg) throws SmplException;
	public T visitExpSub(ExpSub exp, S arg) throws SmplException;
	public T visitExpMul(ExpMul exp, S arg) throws SmplException;
	public T visitExpDiv(ExpDiv exp, S arg) throws SmplException;
	public T visitExpMod(ExpMod exp, S arg) throws SmplException;
	public T visitExpPow(ExpPow exp, S arg) throws SmplException;
	//public T visitExpLit(ExpLit exp, S arg) throws SmplException;
	//public T visitExpVar(ExpVar exp, S arg) throws SmplException;
	//public T visitExpProcedure(ExpProcedure exp, S arg) throws SmplException;
	//public T visitExpProcedureCall(ExpProcedureCall exp, S arg) throws SmplException;
	public T visitmakePair(ExpPair exp, S arg) throws SmplException;
	public T visitExpList(ExpList exp, S arg) throws SmplException;
	public T visitExpVector(ExpVector exp, S arg) throws SmplException;
	public T visitExpVectorRef(ExpVectorRef exp, S arg) throws SmplException;
	public T visitExpSize(ExpSize exp, S arg) throws SmplException;
	public T visitExpSubVector(ExpSubVector exp, S arg) throws SmplException;
	public T visitExpPairCheck(ExpPairCheck exp, S arg) throws SmplException;
	public T visitExpCar(ExpCar exp, S arg) throws SmplException;
	public T visitExpCdr(ExpCdr exp, S arg) throws SmplException;
	public T visitExpEq(ExpEq exp, S arg) throws SmplException;
	public T visitExpGt(ExpGt exp, S arg) throws SmplException;
	public T visitExpLt(ExpLt exp, S arg) throws SmplException;
	public T visitExpLe(ExpLe exp, S arg) throws SmplException;
	public T visitExpGe(ExpGe exp, S arg) throws SmplException;
	public T visitExpNeq(ExpNeq exp, S arg) throws SmplException;
	public T visitExpLogicNot(ExpLogicNot exp, S arg) throws SmplException;
	public T visitExpLogicAnd(ExpLogicAnd exp, S arg) throws SmplException;
	public T visitExpLogicOr(ExpLogicOr exp, S arg) throws SmplException;
	public T visitExpBitWiseNot(ExpBitWiseNot exp, S arg) throws SmplException;
	public T visitExpBitWiseAnd(ExpBitWiseAnd exp, S arg) throws SmplException;
	public T visitExpBitWiseOr(ExpBitWiseOr exp, S arg) throws SmplException;
	public T visitExpSubStr(ExpSubStr exp, S arg) throws SmplException;
	public T visitExpEqv(ExpEqv exp, S arg) throws SmplException;
	public T visitExpEqual(ExpEqual exp, S arg) throws SmplException;
	public T visitExpCall(ExpCall exp, S arg) throws SmplException;
	public T visitExpLazy(ExpLazy exp, S arg) throws SmplException;
	public T visitExpDef(ExpDef exp, S arg) throws SmplException;
	public T visitExpConcatLst(ExpConcatLst exp, S arg) throws SmplException;
	public T visitExpRead(ExpRead exp, S arg) throws SmplException;
	public T visitExpReadInt(ExpReadInt exp, S arg) throws SmplException;
	public T visitExpIf(ExpIf exp, S arg) throws SmplException;
	public T visitExpCase(ExpCase exp, S arg) throws SmplException;



}