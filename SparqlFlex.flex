package lexic;
import static lexic.Token.*;
%%
/*End of usercode*/
%class Alex
%type Symbol
%ignorecase


/*Macros*/

IRI_REF	 				= (<([^\<\>\"\{\}\|\^\`\\\u0000-\u0020])*>)
PNAME_NS 				= {PN_PREFIX}?\:
PNAME_LN	 			= {PNAME_NS}{PN_LOCAL}
VAR1					= \?{VARNAME}
VAR2					= \${VARNAME}
LANGTAG	 				= \@[a-zA-Z]+(\-[a-zA-Z0-9]+)*
INTEGER  				= [0-9]+
DECIMAL 				= [0-9]+.[0-9]*|.[0-9]+
DOUBLE					= [0-9]+\.[0-9]*{EXPONENT}|\.([0-9])+{EXPONENT}|([0-9])+{EXPONENT}
INTEGER_POSITIVE		= \+{INTEGER}
DECIMAL_POSITIVE		= \+{DECIMAL}
DOUBLE_POSITIVE			= \+{DOUBLE}
INTEGER_NEGATIVE		= \-{INTEGER}
DECIMAL_NEGATIVE		= \-{DECIMAL}
DOUBLE_NEGATIVE	    	= \-{DOUBLE}
EXPONENT 				= [eE][+-]?[0-9]+
STRING_LITERAL1	 		= \'(([^\u0027\u005C\u000A\u000D])|{ECHAR})*\'
STRING_LITERAL2	  		= \"(([^\u0027\u005C\u000A\u000D])|{ECHAR})*\"
STRING_LITERAL_LONG1	= \'\'\'((\'|(\'\'))?([^\'\\]|{ECHAR}))*\'\'\'
STRING_LITERAL_LONG2	= \"\"\"((\"|(\"\"))?([^\"\\]|{ECHAR}))*\"\"\"
ECHAR   			    = (\\[tbnrf\"\'])	 
NIL	     				= \({WS}*\)
WS			  			= (\u0020|\u0009|\u000D|\u000A)
ANON	  				= \[{WS}*\]
PN_CHARS_BASE 			= ([A-Z]|[a-z]|[\u00C0-\u00D6]|[\u00D8-\u00F6]|[\u00F8-\u02FF]|[\u0370-\u037D]|[\u037F-\u1FFF]|[\u200C-\u200D]|[\u2070-\u218F]|[\u2C00-\u2FEF]|[\u3001-\uD7FF]|[\uF900-\uFDCF]|[\uFDF0-\uFFFD]|[\u10000-\uEFFFF])
PN_CHARS_U	 			= {PN_CHARS_BASE}|\_
VARNAME		  			= ({PN_CHARS_U}|[0-9])({PN_CHARS_U}|[0-9]|\u00B7|[\u0300-\u036F]|[\u203F-\u2040])*
PN_CHARS	  			= {PN_CHARS_U}|\-|[0-9]|\u00B7|[\u0300-\u036F]|[\u203F-\u2040]
PN_PREFIX     			= {PN_CHARS_BASE}(({PN_CHARS}|\.)*{PN_CHARS})?
PN_LOCAL      			= (({PN_CHARS_U}|[0-9])(({PN_CHARS}|\.)*{PN_CHARS})?)
WHITE 					= [ \t\r\n]+
TYPE  					= "^^"

%{
	public StringBuffer lexeme = new StringBuffer();;
%}

%states  TYPE_SYM, IRI_STATE

%%

/*End of options and declarations*/
	
	
	
	
 	/*OPERATORS*/

	 "&&"			{return new Symbol(Token.AND);}
	 "||"			{return new Symbol(Token.OR);}
	 "="				{return new Symbol(Token.EQUAL);}
	 "!="			{return new Symbol(Token.NOT_EQUAL);}
	 "<"				{return new Symbol(Token.LESS);}
	 ">"				{return new Symbol(Token.GREATER);}
	 "<="			{return new Symbol(Token.LT);}
	 ">="			{return new Symbol(Token.GT);}
	 "+"				{return new Symbol(Token.PLUS);}
	 "-"				{return new Symbol(Token.SUB);}
	 "*"				{return new Symbol(Token.MULT);}
	 "/"				{return new Symbol(Token.DIV);}
	 "!"				{return new Symbol(Token.NOT);}
	
	/*PUNCTIATION*/
	 "{"  {return new Symbol(Token.LEFT_BRACE); }
	 "}" {return new Symbol(Token.RIGHT_BRACE); }
	 "," {return new Symbol(Token.COMMA); }
	 "." {return new Symbol(Token.PERIOD); }
	 "("  {return new Symbol(Token.LEFT_PARENTH); }
	 ")" {return new Symbol(Token.RIGTH_PARENTH); }
	 "[" {return new Symbol(Token.LEFT_CLASP); }
	 "]" {return new Symbol(Token.RIGHT_CLASP); }
	 
 	/**
	 * Reserve words
	 */
	"LANGMATCHES"   {return new Symbol(Token.LANGMATCHES);}
	"FROM NAMED"    {return new Symbol(Token.FROM_NAMED); }
	"CONSTRUCT"     {return new Symbol(Token.CONSTRUCT);}
	"isLITERAL"     {return new Symbol(Token.ISLITERAL);}
	"DATATYPE"	    {return new Symbol(Token.DATATYPE);}
	"ORDER BY"      {return new Symbol(Token.ORDER_BY);}
	"DESCRIBE"      {return new Symbol(Token.DESCRIBE);}
	"OPTIONAL"	    {return new Symbol(Token.OPTIONAL);}
	"DISTINCT"      {return new Symbol(Token.DISTINCT);}
	"sameTERM"	    {return new Symbol(Token.SAMETERM);}
	"REDUCED"       {return new Symbol(Token.REDUCED);}
	"OFFSET"        {return new Symbol(Token.OFFSET);}
	"PREFIX"        {return new Symbol(Token.PREFIX);}
	"SELECT"        {return new Symbol(Token.SELECT);}
	"FILTER"	    {return new Symbol(Token.FILTER);}
	"WHERE"         {return new Symbol(Token.WHERE);}
	"LIMIT"         {return new Symbol(Token.LIMIT);}
	"GRAPH"         {return new Symbol(Token.GRAPH);}
	"isURI"		    {return new Symbol(Token.ISURI);}
	"isIRI"		    {return new Symbol(Token.ISIRI);}
	"UNION"         {return new Symbol(Token.UNION);}
	"BOUND"		    {return new Symbol(Token.BOUND);}
	"REGEX"         {return new Symbol(Token.REGEX);}
	"false"			{return new Symbol(Token.FALSE);}
	"LANG"			{return new Symbol(Token.LANG);}
	"BASE" 		    {return new Symbol(Token.BASE);}
	"FROM" 		    {return new Symbol(Token.FROM);}
	"true"		    {return new Symbol(Token.TRUE);}
	"STR"		    {return new Symbol(Token.STR);}
	"ASK" 		    {return new Symbol(Token.ASK);}
	"a"				{return new Symbol(Token.A);}
	
     /*COMPOSED*/
     
     <TYPE_SYM>{
     	 {TYPE} {yybegin(IRI_STATE);return new Symbol(Token.TYPE, yytext());}
     	 .      {yybegin(YYINITIAL);}
     }

	 <IRI_STATE,YYINITIAL> {IRI_REF}  {yybegin(YYINITIAL);return new Symbol(Token.IRI_REF, yytext());}
	 
	 <YYINITIAL>{
	 	 {INTEGER_POSITIVE} {yybegin(TYPE_SYM);return new Symbol(Token.INTEGER_POSITIVE, yytext());}
	 	 {INTEGER_NEGATIVE} {yybegin(TYPE_SYM);return new Symbol(Token.INTEGER_NEGATIVE, yytext());}
		 {INTEGER} {yybegin(TYPE_SYM);return new Symbol(Token.INTEGER, yytext());}
		 {DECIMAL} {yybegin(TYPE_SYM);return new Symbol(Token.DECIMAL, yytext());}
		 {DOUBLE} {yybegin(TYPE_SYM);return new Symbol(Token.DOUBLE, yytext());}
		 {DECIMAL_POSITIVE} {yybegin(TYPE_SYM);return new Symbol(Token.DECIMAL_POSITIVE, yytext());}
		 {DOUBLE_POSITIVE} {yybegin(TYPE_SYM);return new Symbol(Token.DOUBLE_POSITIVE, yytext());}
		 {DECIMAL_NEGATIVE} {yybegin(TYPE_SYM);return new Symbol(Token.DECIMAL_NEGATIVE, yytext());}
		 {DOUBLE_NEGATIVE} {yybegin(TYPE_SYM);return new Symbol(Token.DOUBLE_NEGATIVE, yytext());}
		 {STRING_LITERAL1} {yybegin(TYPE_SYM);return new Symbol(Token.STRING_LITERAL1, yytext());}
		 {STRING_LITERAL2} {yybegin(TYPE_SYM);return new Symbol(Token.STRING_LITERAL2, yytext());}
		 {STRING_LITERAL_LONG1} {yybegin(TYPE_SYM);return new Symbol(Token.STRING_LITERAL_LONG1, yytext());}
		 {STRING_LITERAL_LONG2} {yybegin(TYPE_SYM);return new Symbol(Token.STRING_LITERAL_LONG2, yytext());}
		 
		 
		 {PNAME_NS} {return new Symbol(Token.PNAME_NS, yytext());}
		 {PNAME_LN} {return new Symbol(Token.PNAME_LN, yytext());}
		 {VAR1} {return new Symbol(Token.VAR1, yytext());}
		 {VAR2} {return new Symbol(Token.VAR2, yytext());}
		 {LANGTAG} {return new Symbol(Token.LANGTAG, yytext());}
		 {NIL}  {return new Symbol(Token.NIL, yytext());}
		 {ANON} {return new Symbol(Token.ANON, yytext());}	 
	 }
	
	
	
     {WHITE} {}

	 . {return new Symbol(Token.ERROR, yytext());}