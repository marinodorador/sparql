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
		return get("AdditiveExpression").FOLLOWS();
	}
}