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
			Long offset = Long.parseLong($.current.lexeme);
			Query.query.setOffset(offset);
			$.next();
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return new Token[]{ Token.OFFSET };
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("LimitOffsetClauses").FOLLOWS(), get("OffsetClause").FOLLOWS()
				});
	}
}