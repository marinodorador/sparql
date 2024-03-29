package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;

/**
 * 
 * @author esteban
 *	BrackettedExpression ::= '(' Expression ')'
 *	FIRST(BrackettedExpression) = {} 
 */
public class BrackettedExpression extends Production{
	Expr expr = null;
	
	public boolean process() throws IOException{
		
		if($.current.token != Token.LEFT_PARENTH){
			MistakeLog.spected.add(Token.LEFT_PARENTH);
			return false;
		}
		
		$.next();
		Expression e = (Expression)$.get("Expression");
		
		if(!e.analize1())
			return false;
		
		expr = e.expr;
		if($.current.token != Token.RIGTH_PARENTH){
			MistakeLog.spected.add(Token.RIGTH_PARENTH);
			return false;
		}
		$.next();
		
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.LEFT_PARENTH);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("PrimaryExpression").FOLLOWS();
	}
}