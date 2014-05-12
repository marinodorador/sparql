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
			System.out.println($.current.token.name());
			if($.current.token == Token.INTEGER) {
				System.out.println("HERE");
				System.out.println($.current.lexeme);
				Long limit = Long.parseLong($.current.lexeme);
				Query.query.setLimit(limit);
				$.next();
				
				return true;
			}
			
		}
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