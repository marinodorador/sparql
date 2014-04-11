package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;
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
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return  construct(new Token[][]{$.get("LimitOffsetClauses").FOLLOWS(), new Token[]{LIMIT}});
	}
}