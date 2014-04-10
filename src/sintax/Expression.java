package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * Expression	  ::=  	ConditionalOrExpression
 */
public class Expression extends Production{

	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("ConditionalOrExpression");
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return  new Token[]{ Token.RIGTH_PARENTH, Token.COMMA};
	}
}