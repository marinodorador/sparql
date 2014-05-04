package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;

/*
 *  Constraint ::= BrackettedExpression
*	FIRST(Constraint) = {'('}
*/
public class Constraint extends Production{
	Expr expr = null;
	public boolean process() throws IOException{
		if($.current.token == Token.LEFT_PARENTH){
			BrackettedExpression be = (BrackettedExpression)$.get("BrackettedExpression");
			if(!be.analize()) return false;
			expr = be.expr;
		}else{
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("Filter").FOLLOWS();
	}
}