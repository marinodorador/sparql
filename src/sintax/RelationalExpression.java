package sintax; 
import static lexic.Token.*;

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.E_Equals;
import com.hp.hpl.jena.sparql.expr.E_GreaterThan;
import com.hp.hpl.jena.sparql.expr.E_GreaterThanOrEqual;
import com.hp.hpl.jena.sparql.expr.E_LessThan;
import com.hp.hpl.jena.sparql.expr.E_LessThanOrEqual;
import com.hp.hpl.jena.sparql.expr.E_NotEquals;
import com.hp.hpl.jena.sparql.expr.Expr;

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
	Expr expr = null;
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
			
			NumericExpression ne = (NumericExpression) $.get("NumericExpression");
			NumericExpression ne2 = (NumericExpression) $.get("NumericExpression");
			if(!ne.analize()) return false;
			this.expr = ne.expr;
			
			switch($.current.token){
				case EQUAL:
					$.next();
					if(!ne2.analize()) return false;
					this.expr = new E_Equals(ne.expr, ne2.expr);
					break;
				case NOT_EQUAL:
					$.next();
					if(!ne2.analize()) return false;
					this.expr = new E_NotEquals(ne.expr, ne2.expr);
					break;
				case LESS:
					$.next();
					if(!ne2.analize()) return false;
					this.expr = new E_LessThan(ne.expr, ne2.expr);
					break;
				case GREATER:
					$.next();
					if(!ne2.analize()) return false;
					this.expr = new E_GreaterThan(ne.expr, ne2.expr);
					break;
				case LET:
					$.next();
					if(!ne2.analize()) return false;
					this.expr = new E_LessThanOrEqual(ne.expr, ne2.expr);
					break;
				case GET:
					$.next();
					if(!ne2.analize()) return false;
					this.expr = new E_GreaterThanOrEqual(ne.expr, ne2.expr);
					break;
					
			}
			
			if($.current.token != OR && $.current.token != AND  && $.current.token != COMMA && $.current.token != RIGTH_PARENTH  )
				return false;
			
		}
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return get("NumericExpression").FIRSTS();
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("ValueLogical").FOLLOWS();
	}
}