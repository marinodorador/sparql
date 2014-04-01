package sintax; 

import java.io.IOException;

import lexic.Token;

public class NumericLiteralNegative extends Production{
	public boolean analize() throws IOException{
	if($.current.token == Token.INTEGER_NEGATIVE || $.current.token == Token.DECIMAL_NEGATIVE
	|| $.current.token == Token.DOUBLE_NEGATIVE){
		$.next();
		return true;
	}
	return false;
	}
}