package sintax; 

import java.io.IOException;

import lexic.Token;

public class NumericLiteralPositive extends Production{
	/**
	 * @author Romina
	 *
	 * NumericLiteralPositive = INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		switch($.current.token){

		case INTEGER_POSITIVE:
		case DECIMAL_POSITIVE:
		case DOUBLE_POSITIVE:
			$.next();
			return true;
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("NumericLiteral").FOLLOWS();
	}
}