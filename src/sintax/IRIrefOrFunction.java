package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 *  IRIrefOrFunction ::=  IRIref ArgList?
 * 	FIRST(IRIrefOrFunction) = {IRI_REF, PNAME_LN, PNAME_NS}
 * 	FIRST(ArgList?) = {NIL, '(' }
 * 	FOLLOW(ArgList?) = {*, /,+,-INTEGER_POSITIVE,DECIMAL_POSITIVE,DOUBLE_POSITIVE,INTEGER_NEGATIVE
						,DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, =, !=, <, > , <=, >=,
						&&, ||, COMMA,')'}
 */
public class IRIrefOrFunction extends Production{
	public boolean process() throws IOException{
		if($.current.token == Token.IRI_REF || $.current.token == Token.PNAME_LN || $.current.token == Token.PNAME_NS){
			if(!$.analize("IRIref")) return false;
			
			if($.current.token == Token.NIL || $.current.token == Token.LEFT_PARENTH){
				if(!$.analize("ArgList")) return false;
			}
			
			if($.current.token != Token.MULT && $.current.token != Token.DIV && $.current.token != Token.PLUS
					 && $.current.token != Token.SUB && $.current.token != Token.INTEGER_POSITIVE && $.current.token != Token.DECIMAL_POSITIVE
					 && $.current.token != Token.DOUBLE_POSITIVE && $.current.token != Token.INTEGER_NEGATIVE && $.current.token != Token.DECIMAL_NEGATIVE
					 && $.current.token != Token.LESS && $.current.token != Token.GREATER && $.current.token != Token.LET
					 && $.current.token != Token.GET && $.current.token != Token.AND && $.current.token != Token.OR
					 && $.current.token != Token.COMMA && $.current.token != Token.RIGTH_PARENTH
					) return false;
		
		}else{
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}