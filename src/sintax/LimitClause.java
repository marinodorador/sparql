package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * LimitClause ::= 'LIMIT' INTEGER
 **/
public class LimitClause extends Production{

	@Override
	public boolean process() throws IOException {
		
		if($.current.token == Token.LIMIT){
			$.next();
			if($.current.token == Token.INTEGER) $.next();return true;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("LimitOffsetClauses").FOLLOWS(), new Token[]{ Token.OFFSET}
				});
	}

}