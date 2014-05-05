package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.OFFSET);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("LimitClause").FIRSTS() )
			ans.add(t);
		for ( Token t : get("LimitOffsetClauses").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}