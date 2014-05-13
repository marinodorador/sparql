package sintax; 

import static lexic.Token.AND;
import static lexic.Token.BOUND;
import static lexic.Token.COMMA;
import static lexic.Token.DATATYPE;
import static lexic.Token.DECIMAL;
import static lexic.Token.DECIMAL_NEGATIVE;
import static lexic.Token.DECIMAL_POSITIVE;
import static lexic.Token.DOUBLE;
import static lexic.Token.DOUBLE_NEGATIVE;
import static lexic.Token.DOUBLE_POSITIVE;
import static lexic.Token.FALSE;
import static lexic.Token.INTEGER;
import static lexic.Token.INTEGER_NEGATIVE;
import static lexic.Token.INTEGER_POSITIVE;
import static lexic.Token.ISIRI;
import static lexic.Token.ISURI;
import static lexic.Token.LANG;
import static lexic.Token.LANGMATCHES;
import static lexic.Token.LEFT_PARENTH;
import static lexic.Token.NOT;
import static lexic.Token.OR;
import static lexic.Token.PLUS;
import static lexic.Token.RIGTH_PARENTH;
import static lexic.Token.SAMETERM;
import static lexic.Token.STR;
import static lexic.Token.STRING_LITERAL1;
import static lexic.Token.STRING_LITERAL2;
import static lexic.Token.STRING_LITERAL_LONG1;
import static lexic.Token.STRING_LITERAL_LONG2;
import static lexic.Token.SUB;
import static lexic.Token.TRUE;
import static lexic.Token.VAR1;
import static lexic.Token.VAR2;

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_Equals;
import com.hp.hpl.jena.sparql.expr.E_GreaterThan;
import com.hp.hpl.jena.sparql.expr.E_GreaterThanOrEqual;
import com.hp.hpl.jena.sparql.expr.E_LessThan;
import com.hp.hpl.jena.sparql.expr.E_LessThanOrEqual;
import com.hp.hpl.jena.sparql.expr.E_NotEquals;
import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * ValueLogical	::= RelationalExpression         ::=  	NumericExpression ( '=' NumericExpression 
 *										|   '!=' NumericExpression 
 *										|   '<' NumericExpression 
 *										|   '>' NumericExpression 
 *										|   '<=' NumericExpression 
 *										|   '>=' NumericExpression )?
 * 
 **/

public class ValueLogical extends Production{
	Expr expr = null;
	@Override
	public boolean process() throws IOException{
			
			NumericExpression ne = (NumericExpression) $.get("NumericExpression");
			NumericExpression ne2 = (NumericExpression) $.get("NumericExpression");
			if(!ne.analize()) return false;
			this.expr = ne.expr;
			switch($.current.token){
				case EQUAL:
					$.next();
					if(!ne2.analize1()) return false;
					this.expr = new E_Equals(ne.expr, ne2.expr);
					break;
				case NOT_EQUAL:
					$.next();
					if(!ne2.analize1()) return false;
					this.expr = new E_NotEquals(ne.expr, ne2.expr);
					break;
				case LESS:
					$.next();
					if(!ne2.analize1()) return false;
					this.expr = new E_LessThan(ne.expr, ne2.expr);
					break;
				case GREATER:
					$.next();
					if(!ne2.analize1()) return false;
					this.expr = new E_GreaterThan(ne.expr, ne2.expr);
					break;
				case LET:
					$.next();
					if(!ne2.analize1()) return false;
					this.expr = new E_LessThanOrEqual(ne.expr, ne2.expr);
					break;
				case GET:
					$.next();
					if(!ne2.analize1()) return false;
					this.expr = new E_GreaterThanOrEqual(ne.expr, ne2.expr);
					break;
					
			}
			
			if($.current.token != OR && $.current.token != AND  && $.current.token != COMMA && $.current.token != RIGTH_PARENTH  )
				return false;
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("NumericExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.AND );
		
		for ( Token t : get("ConditionalAndExpression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}

}