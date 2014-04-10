package sintax; 

import java.io.IOException;

import lexic.Token;

public class NumericLiteralNegative extends Production{
	public boolean process() throws IOException{
	if($.current.token == Token.INTEGER_NEGATIVE || $.current.token == Token.DECIMAL_NEGATIVE
	|| $.current.token == Token.DOUBLE_NEGATIVE){
		$.next();
		return true;
	}
	return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("NumericLiteral").FOLLOWS(),
				get("AdditiveExpression").FOLLOWS(),
				new Token[]{ Token.PLUS, Token.LESS },
				});
	}
}