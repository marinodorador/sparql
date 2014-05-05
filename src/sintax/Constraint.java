package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("BrackettedExpression").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("Filter").FOLLOWS();
	}
}