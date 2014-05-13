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
 */

public class MultiplicativeExpression extends Production{
	public Expr expr = null;
	
	public boolean process() throws IOException{
		
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
		
//		if($.current.token != PLUS && $.current.token != SUB && $.current.token != EQUAL && $.current.token != NOT_EQUAL && $.current.token != LESS
//				&& $.current.token != GREATER && $.current.token != LET && $.current.token != GET && $.current.token != AND && $.current.token != INTEGER_POSITIVE
//				&& $.current.token != DECIMAL_POSITIVE && $.current.token != DOUBLE_POSITIVE && $.current.token != INTEGER_NEGATIVE && $.current.token != DECIMAL_NEGATIVE
//				&& $.current.token != DOUBLE_NEGATIVE && $.current.token != OR && $.current.token != LEFT_PARENTH && $.current.token != COMMA
//				) return false; WTF?
			
		
		
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
		for ( Token t : get("NumericExpression").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralPositive").FIRSTS() )
			ans.add(t);
		for ( Token t : get("NumericLiteralNegative").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}