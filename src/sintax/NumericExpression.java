package sintax; 

import java.io.IOException;

import lexic.Token;

public class NumericExpression extends Production{
	/**
	 * @author Romina
	 *
	 * NumericExpression = AdditiveExpression
	 * FIRSTS: AdditiveExpression.FIRSTS
	 * 
	 * @throws IOException
	 */
	
	public boolean process() throws IOException{
		return $.analize("AdditiveExpression");
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				$.get("RelationalExpression").FOLLOWS(), 
				new Token[]{Token.EQUAL,Token.NOT_EQUAL, Token.GREATER, Token.LESS, Token.GET, Token.LET}
				});
	}
}