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
	public boolean analize() throws IOException{
		
		if ( $.current.token == Token.NOT || $.current.token == Token.PLUS ||$.current.token == Token.SUB )
			$.next();
		
		return $.analize("PrimaryExpression");
	}
}