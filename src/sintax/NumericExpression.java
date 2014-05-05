package sintax; 

import java.io.IOException;

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
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				$.get("RelationalExpression").FOLLOWS(), 
				new Token[]{Token.EQUAL,Token.NOT_EQUAL, Token.GREATER, Token.LESS, Token.GET, Token.LET}
				});
	}
}