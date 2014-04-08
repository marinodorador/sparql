package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 *  NumericLiteralUnsigned	::=  INTEGER 
 *							|	DECIMAL 
 *							|	DOUBLE
 **/
public class NumericLiteral extends Production{
	public boolean process() throws IOException{
		return $.analize("NumericLiteralUnsigned");
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}