package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;
/*
 * LimitClause ::= 'LIMIT' INTEGER
 **/
public class LimitClause extends Production{

	@Override
	public boolean process() throws IOException {
		
		if($.current.token == Token.LIMIT){
			$.next();
			if($.current.token == Token.INTEGER) {
				Long limit = Long.parseLong($.current.lexeme);
				Query.query.setLimit(limit);
				$.next();
				
				return true;
			}
			
		}
		MistakeLog.spected.add(Token.INTEGER);
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.LIMIT);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("OffsetClause").FIRSTS() )
			ans.add(t);
		for ( Token t : get("LimitOffsetClauses").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}

}