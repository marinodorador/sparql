package sintax; 
import static lexic.Token.*;

import java.io.IOException;

import lexic.Token;
/*
 * RelationalExpression	         ::=  	NumericExpression ( '=' NumericExpression 
 *										|   '!=' NumericExpression 
 *										|   '<' NumericExpression 
 *										|   '>' NumericExpression 
 *										|   '<=' NumericExpression 
 *										|   '>=' NumericExpression )?
 * 
 * FIRST(RelationalExpression) = {'-', '!',  '+', '(', 'STR', 'LANG', 'LANGMATCHES',    
 * 								  'DATATYPE', 'BOUND', 'sameTerm', 'isIRI', 'isURI', 
 * 								   'isBLANK', 'isLITERAL', 'REGEX', IRIref, STRING_LITERAL1,
 * 								    STRING_LITERAL2, STRING_LITERAL_LONG1, STRING_LITERAL_LONG2,
 * 									INTEGER, DECIMAL, DOUBLE, INTEGER_POSITIVE, DECIMAL_POSITIVE, 
 * 									DOUBLE_POSITIVE, INTEGER_NEGATIVE, DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, 
 * 									TRUE, FALSE, VAR1, VAR2
 * 								  }
 * 
 * FOLLOW( ( '=' NumericExpression 
 *										|   '!=' NumericExpression 
 *										|   '<' NumericExpression 
 *										|   '>' NumericExpression 
 *										|   '<=' NumericExpression 
 *										|   '>=' NumericExpression )? ) = { OR, AND, COMMA, ')' }
 * 
 **/
public class RelationalExpression extends Production{

	public boolean process() throws IOException{
		if($.current.token  == SUB  || $.current.token  == NOT || $.current.token  == PLUS
				|| $.current.token  == LEFT_PARENTH|| $.current.token  == STR|| $.current.token  == LANG
				|| $.current.token  == LANGMATCHES|| $.current.token  == DATATYPE|| $.current.token  == BOUND
				|| $.current.token  == SAMETERM|| $.current.token  == ISIRI|| $.current.token  == ISURI
				|| $.current.token  == STRING_LITERAL1|| $.current.token  == STRING_LITERAL2 || $.current.token  == STRING_LITERAL_LONG1
				|| $.current.token  == STRING_LITERAL_LONG2|| $.current.token  == INTEGER|| $.current.token  == DECIMAL
				|| $.current.token  == DOUBLE || $.current.token  == INTEGER_POSITIVE || $.current.token  == DECIMAL_POSITIVE
				|| $.current.token  == DOUBLE_POSITIVE || $.current.token  == INTEGER_NEGATIVE || $.current.token  == DECIMAL_NEGATIVE
				|| $.current.token  == DOUBLE_NEGATIVE || $.current.token  == TRUE || $.current.token  == FALSE
				|| $.current.token  == VAR1 || $.current.token  ==VAR2
				){
			if(!$.analize("NumericExpression")) return false;
			
			switch($.current.token){
				case EQUAL:
					$.next();
					if(!$.analize("NumericExpression")) return false;
					break;
				case NOT_EQUAL:
					$.next();
					if(!$.analize("NumericExpression")) return false;
					break;
				case LESS:
					$.next();
					if(!$.analize("NumericExpression")) return false;
					break;
				case GREATER:
					$.next();
					if(!$.analize("NumericExpression")) return false;
					break;
				case LET:
					$.next();
					if(!$.analize("NumericExpression")) return false;
					break;
				case GET:
					$.next();
					if(!$.analize("NumericExpression")) return false;
					break;
					
			}
			
			if($.current.token != OR && $.current.token != AND  && $.current.token != COMMA && $.current.token != RIGTH_PARENTH  )
				return false;
			
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("ValueLogical").FOLLOWS();
	}
}