package sintax; 

import java.io.IOException;

public class NumericExpression extends Production{
	/**
	 * @author Romina
	 *
	 * NumericExpression = AdditiveExpression
	 * FIRSTS: AdditiveExpression.FIRSTS
	 * 
	 * @throws IOException
	 */
	
	public boolean analize() throws IOException{
		return $.analize("AdditiveExpression");
	}
}