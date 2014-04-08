package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * ValueLogical	::= RelationalExpression
 */
public class ValueLogical extends Production{

	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("RelationalExpression");
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}