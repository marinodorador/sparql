package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;
/*
 * Expression	  ::=  	ConditionalOrExpression
 */
public class Expression extends Production{
	Expr expr = null;
	@Override
	public boolean process() throws IOException {
		ConditionalOrExpression coe= (ConditionalOrExpression)$.get("ConditionalOrExpression");
		boolean result = coe.analize();
		expr = coe.expr;
		return result;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("ConditionalOrExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.RIGTH_PARENTH );
		ans.add( Token.COMMA );
		
		return ans;
	}
}