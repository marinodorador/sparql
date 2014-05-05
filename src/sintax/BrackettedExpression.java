package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;

/**
 * 
 * @author esteban
 *	BrackettedExpression ::= '(' Expression ')'
 *	FIRST(BrackettedExpression) = {'('} 
 */
public class BrackettedExpression extends Production{
	Expr expr = null;
	public boolean process() throws IOException{
		if($.current.token != Token.LEFT_PARENTH) return false;
		$.next();
		Expression e = (Expression)$.get("Expression");
		
		if(!e.analize()) return false;
		expr = e.expr;
		
		if($.current.token != Token.LEFT_PARENTH) return false;
		$.next();
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException{
		// TODO Auto-generated method stub
		return $.get("PrimaryExpression").FOLLOWS();
	}
}