package sintax; 

import java.io.IOException;

import lexic.Token;

public class UnaryExpression extends Production{
	/**
	 * @author Romina
	 *
	 * UnaryExpression = ('!' | '+' | '-')? PrimaryExpression
										
	 * FIRSTS: { '!' | '+' | '-' | PrimaryExpression }
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		
		if ( $.current.token == Token.NOT || $.current.token == Token.PLUS ||$.current.token == Token.SUB )
			$.next();
		
		return $.analize("PrimaryExpression");
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.MULT , Token.DIV },
				get("MultiplicativeExpression").FOLLOWS(),
				});
	}
}