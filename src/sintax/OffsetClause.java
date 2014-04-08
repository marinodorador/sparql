package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * OffsetClause	::= 'OFFSET' INTEGER
 */
public class OffsetClause extends Production{

	public boolean process() throws IOException{
		if($.current.token != Token.OFFSET) return false;
			$.next();
		if($.current.token == Token.INTEGER) return false;
			$.next();
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}