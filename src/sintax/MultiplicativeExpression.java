package sintax; 

import lexic.Token;
import java.io.IOException;

import static lexic.Token.*;
/**
 * 
 * @author esteban
 * 
 * MultiplicativeExpression	::=  UnaryExpression ( '*' UnaryExpression | '/' UnaryExpression )* 
 * 
 * FIRST(MultiplicativeExpression) = {'!', '+', '-', '(', STR, LANG, LANGMATCHES , DATATYPE, BOUND, sameTerm, 
 * 										isIRI, isURI, isBLANK, isLITERAL, REGEX, STRING_LITERAL1, STRING_LITERAL2,  
 * 										STRING_LITERAL_LONG1,	STRING_LITERAL_LONG2,INTEGER,DECIMAL,DOUBLE,
 * 										INTEGER_POSITIVE,DECIMAL_POSITIVE,DOUBLE_POSITIVE,INTEGER_NEGATIVE,
 * 										,DECIMAL_NEGATIVE,DOUBLE_NEGATIVE,TRUE,FALSE,VAR1, VAR2}
 * 
 * FOLLOW( ( '*' UnaryExpression | '/' UnaryExpression )* ) = {
 * 					'+','-', '=', '!=', '<', '>', '<=',  '>=', &&, INTEGER_POSITIVE, DECIMAL_POSITIVE,DOUBLE_POSITIVE,
 * 					INTEGER_NEGATIVE, DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, ||, '(', ','  
 * 		 }
 */

public class MultiplicativeExpression extends Production{
	public boolean process() throws IOException{
		if($.current.token == NOT || $.current.token == PLUS || $.current.token == SUB || $.current.token == LEFT_PARENTH 
				|| $.current.token == STR || $.current.token == LANG || $.current.token == LANGMATCHES 
				|| $.current.token == DATATYPE || $.current.token == BOUND || $.current.token == SAMETERM 
				|| $.current.token == ISIRI || $.current.token == ISURI || $.current.token == ISBLANK 
				|| $.current.token == ISLITERAL || $.current.token == REGEX || $.current.token == STRING_LITERAL1 
				|| $.current.token == STRING_LITERAL2 || $.current.token == STRING_LITERAL_LONG1
				 || $.current.token == INTEGER || $.current.token == DECIMAL || $.current.token == DOUBLE
				 || $.current.token == INTEGER_POSITIVE || $.current.token == DECIMAL_POSITIVE || $.current.token == DOUBLE_POSITIVE
				 || $.current.token == INTEGER_NEGATIVE || $.current.token == DECIMAL_NEGATIVE || $.current.token == DOUBLE_NEGATIVE
				 || $.current.token == TRUE || $.current.token == FALSE|| $.current.token == VAR1 || $.current.token == VAR2){
			if(!$.analize("UnaryExpression")) return false;
			while($.current.token  == MULT || $.current.token  == DIV){
				switch($.current.token ){
					case MULT:
						$.next();
						if(!$.analize("UnaryExpression")) return false;
						break;
					case DIV:
						$.next();
						if(!$.analize("UnaryExpression")) return false;
						break;
				}
				
			}
			if($.current.token != PLUS && $.current.token != SUB && $.current.token != EQUAL && $.current.token != NOT_EQUAL && $.current.token != LESS
					&& $.current.token != GREATER && $.current.token != LET && $.current.token != GET && $.current.token != AND && $.current.token != INTEGER_POSITIVE
					&& $.current.token != DECIMAL_POSITIVE && $.current.token != DOUBLE_POSITIVE && $.current.token != INTEGER_NEGATIVE && $.current.token != DECIMAL_NEGATIVE
					&& $.current.token != DOUBLE_NEGATIVE && $.current.token != OR && $.current.token != LEFT_PARENTH && $.current.token != COMMA
					) return false;
			
		}
		
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return construct(new Token[][]{
			new Token[]{
				PLUS,
				SUB,
				INTEGER_POSITIVE,
				DECIMAL_POSITIVE,
				DOUBLE_POSITIVE,
				INTEGER_NEGATIVE,
				DECIMAL_NEGATIVE,
				DOUBLE_NEGATIVE
			},
			$.get("AdditiveExpression").FOLLOWS()
		});
	}
}