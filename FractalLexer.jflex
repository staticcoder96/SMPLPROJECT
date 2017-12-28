/* Specification for Fractal tokens */

// user customisations

package fractal.syntax;
import java_cup.runtime.*;
import fractal.sys.FractalException;
import fractal.sys.FractalLexerException;

// JFlex directives
    
%%

%cup
%public

%class FractalLexer
%throws FractalException

%type java_cup.runtime.Symbol

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%eofclose false

%char
%column
%line

%{
    private Symbol mkSymbol(int id) {
        return new Symbol(id, yyline, yycolumn);
    }

    private Symbol mkSymbol(int id, Object val) {
        return new Symbol(id, yyline, yycolumn, val);
    }

    public int getChar() {
	return yychar + 1;
    }

    public int getColumn() {
    	return yycolumn + 1;
    }

    public int getLine() {
	return yyline + 1;
    }

    public String getText() {
	return yytext();
    }
%}

nl = [\n\r]

cc = ([\b\f]|{nl})

ws = {cc}|[\t ]

bkslash = [\\\\]

tab = [\t]

floatnum =  \d+\.|\d+\.\d+ | \.[0-9][0-9][0-9]+

alpha = [a-zA-Z0-9?\\-+*!?#.]* | [_a-zA-Z?] 

alphnum = {floatnum}|{alpha}

%%

<YYINITIAL>	{nl}	{
			 //skip newline
             yychar = 0;
			}

<YYINITIAL> {tab}    {
             //skip tab
            }

<YYINITIAL>	{ws}	{
			 // skip whitespace
			}


<YYINITIAL>	"//".*	{
			 // skip line comments
			}


<YYINITIAL> {bkslash}    {
             //skip backslash
            }


<YYINITIAL> {

    "+"         {return mkSymbol(sym.PLUS);}
    "-"         {return mkSymbol(sym.MINUS);}
    "*"         {return mkSymbol(sym.MUL);}
    "/"         {return mkSymbol(sym.DIV);}
    "%"         {return mkSymbol(sym.MOD);}
    "^"         {return mkSymbol(sym.POW);}

    "="         {return mkSymbol(sym.ASSIGN);}
    ">"         {return mkSymbol(sym.GREATERTHAN);}
    "<"         {return mkSymbol(sym.LESSTHAN);}
    "<="        {return mkSymbol(sym.LESSEQUAL);}
    ">="        {return mkSymbol(sym.GREATEQUAL);}
    "!="        {return mkSymbol(sym.NOTEQUAL);}

    "not"       {return mkSymbol(sym.NOT);}
    "and"       {return mkSymbol(sym.AND);}
    "or"        {return mkSymbol(sym.OR);}

    "@"        {return mkSymbol(sym.LIST);}
    "~"        {return mkSymbol(sym.TILDE);}
    "|"        {return mkSymbol(sym.BAR);}
    "&"       {return mkSymbol(sym.AMPERSAND);}

    

    "("			{return mkSymbol(sym.LPAREN);}
    ")"			{return mkSymbol(sym.RPAREN);}
    "["         {return mkSymbol(sym.LBRACE);}
    "]"         {return mkSymbol(sym.RBRACE);}
    ","         {return mkSymbol(sym.COMMA);}
    ":"         {return mkSymbol(sym.COLON);}
    ";"         {return mkSymbol(sym.SEMICOLON);}
    "{"         {return mkSymbol(sym.LCBRACE);}
    "}"         {return mkSymbol(sym.RCBRACE);}
   

    """"        {return mkSymbol(sym.DQUOTES);}
    "''"        {return mkSymbol(sym.SQUOTES);}
    "#t"        {return mkSymbol(sym.TRUE);}
    "#f"        {return mkSymbol(sym.FALSE);}
    "#e"        {return mkSymbol(sym.NIL);}

    "def"       {return mkSymbol(sym.DEF);}
    "proc"      {return mkSymbol(sym.PROC);}
    "call"      {return mkSymbol(sym.CALL);}
    "lazy"      {return mkSymbol(sym.LAZY);}
    "let"       {return mkSymbol(sym.LET);}

    "if"        {return mkSymbol(sym.IF);}
    "then"      {return mkSymbol(sym.THEN);}
    "case"      {return mkSymbol(sym.CASE);}
    "print"     {return mkSymbol(sym.PRINT);}
    "println"   {return mkSymbol(sym.PRINTLN);}

    "read"      {return mkSymbol(sym.READ);}
    "readint"   {return mkSymbol(sym.READINT);}
    "/*....*/"  {return mkSymbol(sym.BLOCKCOMMENT);}




    {floatnum}+ 		{
			 // REAL
	       		 return mkSymbol(sym.REAL, 
			 	         new REAL(yytext()));
	       		}

    {alpha}{alphnum}*   {
    		      	 // IDENTIFIERS
	       		 return mkSymbol(sym.ID, yytext());
	       		}

    .			{ // Unknown token (leave this in the last position)
    			  throw new FractalLexerException(yytext(), getLine(),
							  getColumn());
    			}
}
