/* Specification for smpl tokens */

// user customisations

package smpl.syntax;
import java_cup.runtime.*;
import smpl.sys.SmplException;

// JFlex directives
    
%%

%cup
%public

%class SmplLexer
%throws SmplException

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

    public String getText(String str) {
    boolean Blockcomment = false;
    boolean linecomment = false;
    
    for (int index=0; index<str.length(); index++){
        char c = str.charAt(index);
        char c2 = str.charAt(index+1);

        char lc = str.charAt(index);
        char lc2 = str.charAt(index+1);

        if(c == '/' && c2 == '*'){
                char nexxtchar = str.charAt(index+2);
                pattern = [.]*;
                if (nexxtchar == pattern){
                    Blockcomment = true;
                    continue; /*ignores block  comment*/
                }
        }
        else if(c == '*' && c2 == '/'){
            Blockcomment = false;
            c = str.charAt(index+3);
            continue; /*end of the block comment*/
        }
        else if (lc == '/' && lc2 == '/'){
            linecomment = true;
            lc = str.charAt(index+1);
            continue; //ignores line comment
        }
        //line comment terminates at the new line marker.
        if (linecomment){
            if (lc2 == '\n'){
                linecomment = false;
                lc = str.charAt(index+2);
                continue;
            } else if(lc == '\n'){
                linecomment = false;
                lc = lc2;
                continue;
            } else {
                lc = str.charAt(index+3);
                continue;
            }

        }        
    }
	return yytext();
    }
%}

nl = [\n\r]

cc = ([\b\f]|{nl})

ws = {cc}|[\t ]

num = [0-9]

comment = getText(String str);

block = getText(String str);

sign = ("+"| "-")

binary = #b[01]+

hex = [0-9a-fA-F]

char = [^\\\n\"] | "\\". | \u {hex}{4}

symbols = ["?" "\\" "-" "+" "*" "!" "#" "."]

floatnum =  \d+\.|\d+\.\d+ | \.[0-9][0-9][0-9]+

alpha = [a-zA-Z] 

alphnum = {alpha}|{num}

var = {alphnum}| {sign}

%%

<YYINITIAL>	{nl}	{
			 //skip newline
             yychar = 0;
			}

<YYINITIAL> \" {
        yybegin(YYSTRING);
        }

<YYSTRING>  \" {
            yybegin(YYINITIAL);
        }

<YYSTRING>    [^\"]* {
            // constant string
            // System.out.println(yytext());
            return new Symbol(sym.STRING, yytext());
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
    "."         {return mkSymbol(sym.PERIOD);}

    "="         {return mkSymbol(sym.EQUAL);}
    ">"         {return mkSymbol(sym.GREATERTHAN);}
    "<"         {return mkSymbol(sym.LESSTHAN);}
    "<="        {return mkSymbol(sym.LESSEQUAL);}
    ">="        {return mkSymbol(sym.GREATERQUAL);}
    "!="        {return mkSymbol(sym.NOTEQUAL);}

    "not"       {return mkSymbol(sym.LNOT);}
    "and"       {return mkSymbol(sym.LAND);}
    "or"        {return mkSymbol(sym.LOR);}

    "@"        {return mkSymbol(sym.CONCAT);}
    "~"        {return mkSymbol(sym.BNOT);}
    "|"        {return mkSymbol(sym.BOR);}
    "&"       {return mkSymbol(sym.BAND);}

    

    "("			{return mkSymbol(sym.LPAREN);}
    ")"			{return mkSymbol(sym.RPAREN);}
    "["         {return mkSymbol(sym.LBRACE);}
    "]"         {return mkSymbol(sym.RBRACE);}
    ","         {return mkSymbol(sym.COMMA);}
    ":"         {return mkSymbol(sym.COLON);}
    ";"         {return mkSymbol(sym.SEMI);}
    "{"         {return mkSymbol(sym.LCBRACE);}
    "}"         {return mkSymbol(sym.RCBRACE);}
   

    \"\"        {return mkSymbol(sym.DQUOTES);}
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
    "else|ELSE"      {return mkSymbol(sym.ELSE);}
    "case|CASE"      {return mkSymbol(sym.CASE);}
    "print|PRINT"     {return mkSymbol(sym.PRINT);}
    "println|PRINTLN"   {return mkSymbol(sym.PRINTLN);}

    "read|READ"      {return mkSymbol(sym.READ);}
    "readint|READINT"   {return mkSymbol(sym.READINT);}

    "pair"   {return mkSymbol(sym.PAIR);}
    "car"   {return mkSymbol(sym.CAR);}
    "cdr"   {return mkSymbol(sym.CDR);}
    "pair?" {return mkSymbol(sym.PAIRCHECK);}
    "list"   {return mkSymbol(sym.LIST);}
    "[:"   {return mkSymbol(sym.LVECTOR);}
    ":]"   {return mkSymbol(sym.RVECTOR);}
    "size"   {return mkSymbol(sym.SIZE);}
    "eqv?"   {return mkSymbol(sym.ISEQUIVALENT);}
    "equal?"   {return mkSymbol(sym.ISEQUAL);}
    "substr"   {return mkSymbol(sym.SUBSTR);}



    ([^#][0-9]+[A-Za-z_][A-Za-z_0-9#+.-*?]*)|([A-Za-z_][A-Za-z_0-9#+.-*?]*) {
            // IDENTIFIER
            return new Symbol(sym.VAR, yytext());
        }




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

    {char}* {
        //STRING
        return mkSymbol(sym.STRING, yytext().substring(1,yytext().length()-1));

    }

    {var}* {
            // VARIABLE
            return mkSymbol(sym.VARIABLE, yytext());
        }

    .			{ // Unknown token (leave this in the last position)
    			  throw new SmplException(yytext(), getLine(),
							  getColumn());
    			}
}
