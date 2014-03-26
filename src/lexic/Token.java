package lexic;

public enum Token {
	/*Reserve words*/
   LANGMATCHES,FROM_NAMED,CONSTRUCT,ISLITERAL,DATATYPE,ORDER_BY,DESCRIBE,OPTIONAL,DISTINCT,SAMETERM,	    
   REDUCED,OFFSET,PREFIX,SELECT,FILTER,WHERE,LIMIT, GRAPH, ISURI, ISIRI, UNION, BOUND, REGEX, FALSE, 
   LANG, BASE,FROM, TRUE, STR, ASK, A,
	
   /*NON Terminal*/
   IRI_REF,PNAME_NS,PNAME_LN,BLANK_NODE_LABEL,VAR1,VAR2,LANGTAG,INTEGER,DECIMAL,DOUBLE, INTEGER_POSITIVE,
   DECIMAL_POSITIVE,DOUBLE_POSITIVE,INTEGER_NEGATIVE,DECIMAL_NEGATIVE,DOUBLE_NEGATIVE,EXPONENT,STRING_LITERAL1,
   STRING_LITERAL2,STRING_LITERAL_LONG1,STRING_LITERAL_LONG2,ECHAR ,NIL,WS,ANON,PN_CHARS_BASE,PN_CHARS_U,
   VARNAME,PN_CHARS,PN_PREFIX,PN_LOCAL, TYPE,
   
   /*PUNCTUATION*/
   LEFT_BRACE, RIGHT_BRACE,COMMA,PERIOD, LEFT_PARENTH,RIGTH_PARENTH,LEFT_CLASP,RIGHT_CLASP,
   
   /*OPERATORS*/
   AND, OR, EQUAL, NOT_EQUAL, LESS, GREATER, LT, GT, PLUS, SUB, MULT, DIV, NOT,
   /*Error*/
   ERROR
}
