package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.expr.Expr;

import lexic.Token;

public class NumericExpression extends Production{
	Expr expr = null;
	/**
	 * @author Romina
	 *
	 * NumericExpression = AdditiveExpression
	 * FIRSTS: AdditiveExpression.FIRSTS
	 * 
	 * @throws IOException
	 */
	
	public boolean process() throws IOException{
		AdditiveExpression ae= (AdditiveExpression)$.get("AdditiveExpression");
		boolean result = ae.analize();
		expr = ae.expr;
		return result;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("AdditiveExpression").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.EQUAL );
		ans.add( Token.NOT_EQUAL );
		ans.add( Token.GREATER );
		ans.add( Token.LESS );
		ans.add( Token.GET );
		ans.add( Token.LET );
		
		for ( Token t : get("RelationalExpression").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}