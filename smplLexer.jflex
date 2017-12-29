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

comment = "//".*[\n\r]

block = "/* [{nl}|.]*   */"

num = [0-9]

sign = ("+"| "-")

binary = #b[01]+

hex = [0-9a-fA-F]

char = [^\\\n\"] | "\\". | {hex}{4}

floatnum =  \d+\.|\d+\.\d+ | \.[0-9][0-9][0-9]+

alpha = [a-zA-Z0-9?\\-+*!?#.]* | [_a-zA-Z?] 

alphnum = {floatnum}|{alpha}

%%

<YYINITIAL>	{nl}	{
			 //skip newline
             yychar = 0;
			}

<YYINITIAL> {comment}  {
             // skip line comments
            }

<YYINITIAL> {block}  {
             // skip block comments
            }


<YYINITIAL>	{ws}	{
			 // skip whitespace
			}



<YYINITIAL> {

    "+"         {return mkSymbol(sym.PLUS);}
    "-"         {return mkSymbol(sym.MINUS);}
    "*"         {return mkSymbol(sym.MUL);}
    "/"         {return mkSymbol(sym.DIV);}
    "%"         {return mkSymbol(sym.MOD);}
    "^"         {return mkSymbol(sym.POW);}

    "="         {return mkSymbol(sym.EQUAL);}
    ">"         {return mkSymbol(sym.GREATERTHAN);}
    "<"         {return mkSymbol(sym.LESSTHAN);}
    "<="        {return mkSymbol(sym.LESSEQUAL);}
    ">="        {return mkSymbol(sym.GREATEQUAL);}
    "!="        {return mkSymbol(sym.NOTEQUAL);}

    "not"       {return mkSymbol(sym.NOT);}
    "and"       {return mkSymbol(sym.AND);}
    "or"        {return mkSymbol(sym.OR);}

    "@"        {return mkSymbol(sym.CONCAT);}
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
    "#t"        {return mkSymbol(sym.TRUE,yytext());}
    "#f"        {return mkSymbol(sym.FALSE,yytext());}
    "#e"        {return mkSymbol(sym.NIL);}

    "def|DEF"       {return mkSymbol(sym.DEF);}
    ":="       {return mkSymbol(sym.ASSIGN);}
    "proc|PROC"      {return mkSymbol(sym.PROC);}
    "call|CALL"      {return mkSymbol(sym.CALL);}
    "lazy|LAZY"      {return mkSymbol(sym.LAZY);}
    "let|LET"       {return mkSymbol(sym.LET);}

    "if|IF"        {return mkSymbol(sym.IF);}
    "then|THEN"      {return mkSymbol(sym.THEN);}
    "case|CASE"      {return mkSymbol(sym.CASE);}
    "print|PRINT"     {return mkSymbol(sym.PRINT);}
    "println|PRINTLN"   {return mkSymbol(sym.PRINTLN);}

    "read|READ"      {return mkSymbol(sym.READ);}
    "readint|READINT"   {return mkSymbol(sym.READINT);}
    "/*....*/"  {return mkSymbol(sym.BLOCKCOMMENT);}

    "pair"   {return mkSymbol(sym.PAIR);}
    "car"   {return mkSymbol(sym.CAR);}
    "cdr"   {return mkSymbol(sym.CDR);}
    "pair?" {return mkSymbol(sym.ISPAIR);}
    "list"   {return mkSymbol(sym.LIST);}
    "[:"   {return mkSymbol(sym.LVECTOR);}
    ":]"   {return mkSymbol(sym.RVECTOR);}
    "size"   {return mkSymbol(sym.SIZE);}
    "eqv?"   {return mkSymbol(sym.ISEQUIVALENT);}
    "equal?"   {return mkSymbol(sym.ISEQUAL);}
    "substr"   {return mkSymbol(sym.SUBSTR);}


     {sign}?{num}+         {
             // INTEGER
                 return mkSymbol(sym.INTEGER, 
                         new INTEGER(yytext()));
                }



    {sign}?{floatnum}+ 		{
			 // REAL
	       		 return mkSymbol(sym.REAL, 
			 	         new Double(yytext()));
	       		}

    {sign}?{binary}+ {
        //BINARY
        return mkSymbol(sym.INTEGER,Integer.parseInt(yytext().replace("#b", " "), 2));

    }

    {sign}?"x"{hex}+ {
        //HEXADECIMAL
        return mkSymbol(sym.INTEGER,Integer.parseInt(yytext().replace("#x", " "), 16));

    }

    "#c"{char}  {
        //CHARACTER
        return mkSymbol(sym.CHARACTER, yytext().replace("#c", " "));

    }

    "{char}*" {
        //STRING
        return mkSymbol(sym.STRING, yytext().substring(1,yytext().length()-1);

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
