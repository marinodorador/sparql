package sintax; 

import lexic.Token;
import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.E_Divide;
import com.hp.hpl.jena.sparql.expr.E_Multiply;
import com.hp.hpl.jena.sparql.expr.Expr;

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
	public Expr expr = null;
	private static final boolean firsts = $.current.token == NOT || $.current.token == PLUS || $.current.token == SUB || $.current.token == LEFT_PARENTH 
			|| $.current.token == STR || $.current.token == LANG || $.current.token == LANGMATCHES 
			|| $.current.token == DATATYPE || $.current.token == BOUND || $.current.token == SAMETERM 
			|| $.current.token == ISIRI || $.current.token == ISURI || $.current.token == ISBLANK 
			|| $.current.token == ISLITERAL || $.current.token == REGEX || $.current.token == STRING_LITERAL1 
			|| $.current.token == STRING_LITERAL2 || $.current.token == STRING_LITERAL_LONG1
			|| $.current.token == INTEGER || $.current.token == DECIMAL || $.current.token == DOUBLE
			|| $.current.token == INTEGER_POSITIVE || $.current.token == DECIMAL_POSITIVE || $.current.token == DOUBLE_POSITIVE
			|| $.current.token == INTEGER_NEGATIVE || $.current.token == DECIMAL_NEGATIVE || $.current.token == DOUBLE_NEGATIVE
			|| $.current.token == TRUE || $.current.token == FALSE|| $.current.token == VAR1 || $.current.token == VAR2;
	
	public boolean process() throws IOException{
		if(firsts){
			UnaryExpression ue = (UnaryExpression)$.get("UnaryExpression");
			
			if(!ue.analize()) return false;
			this.expr = ue.expr;
			while($.current.token  == MULT || $.current.token  == DIV){
				switch($.current.token ){
					case MULT:{
						$.next();
						UnaryExpression ue2 = (UnaryExpression)$.get("UnaryExpression");
						if(!ue2.analize()) return false;
						this.expr = new E_Multiply(this.expr, ue2.expr);
						break;
					}case DIV:{
						$.next();
						UnaryExpression ue2 = (UnaryExpression)$.get("UnaryExpression");
						if(!ue2.analize()) return false;
						this.expr = new E_Divide(this.expr, ue2.expr);
						break;
					}
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
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("UnaryExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.PLUS );
		ans.add( Token.LESS );
		
		for ( Token t : get("NumericLiteral").FIRSTS() )
			ans.add(t);
		for ( Token t : get("AdditiveExpression").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralPositive").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralNegative").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}