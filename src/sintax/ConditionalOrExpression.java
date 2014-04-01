package sintax; 
import static lexic.Token.*;

import java.io.IOException;
/***
 * 
 *  ConditionalOrExpression	::= ConditionalAndExpression ( '||' ConditionalAndExpression )*
 *  
 *	FIRST(ConditionalOrExpression)  = { '-', '!', '+',INTEGER_POSITIVE,DECIMAL_POSITIVE,DOUBLE_POSITIVE,
 *										INTEGER_NEGATIVE, DECIMAL_NEGATIVE, DOUBLE_NEGATIVE, '(', 'STR',
 *										'LANG','LANGMATCHES', 'DATATYPE', 'BOUND', 'sameTerm', 'isIRI',
 *										'isURI', 'isBLANK', 'isLITERAL', 'REGEX', IRIref, STRING_LITERAL1, STRING_LITERAL2,
 *										STRING_LITERAL_LONG1,STRING_LITERAL_LONG2, INTEGER, DECIMAL, DOUBLE, TRUE, FALSE, 
 * 										VAR1, VAR2
 *									  }
 *  FIRST(  ( '||' ConditionalAndExpression )* ) = {'||'}
 *  
 *  FOLLOW(  ( '||' ConditionalAndExpression )* ) = { ')', ','} 
 *
 **/
public class ConditionalOrExpression extends Production{
	public boolean analize() throws IOException{
		if($.current.token == SUB || $.current.token == NOT || $.current.token == PLUS 
		||	$.current.token == INTEGER_POSITIVE || $.current.token == DECIMAL_POSITIVE 
		||  $.current.token == DOUBLE_POSITIVE || $.current.token == INTEGER_NEGATIVE
		||  $.current.token == DECIMAL_NEGATIVE || $.current.token == DOUBLE_NEGATIVE
		||  $.current.token  == LEFT_PARENTH || $.current.token == STR
		||  $.current.token == LANG  || $.current.token == LANGMATCHES
		||  $.current.token == DATATYPE || $.current.token == BOUND
		||  $.current.token == SAMETERM || $.current.token == ISIRI
		||  $.current.token == ISURI ||  $.current.token == ISBLANK
		||  $.current.token == ISLITERAL ||  $.current.token == REGEX || $.current.token == IRI_REF
		||  $.current.token ==  STRING_LITERAL1 || $.current.token ==  STRING_LITERAL2 
		||  $.current.token ==  STRING_LITERAL_LONG1 || $.current.token ==  STRING_LITERAL_LONG2
		||  $.current.token ==  INTEGER || $.current.token ==  DECIMAL || $.current.token ==  DOUBLE
		||  $.current.token ==  DOUBLE || $.current.token ==  TRUE || $.current.token ==  FALSE 
		||  $.current.token ==  VAR1 || $.current.token ==  VAR2){
			while($.current.token == OR){
				$.next();
				if(!$.analize("ConditionalAndExpression")) return false;
			}
			if($.current.token != RIGTH_PARENTH && $.current.token != COMMA) return false;
		}else return false;
		return true;
	}
}