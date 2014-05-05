package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;

public class SolutionModifier extends Production{
	/**
	 * @author Romina
	 *
	 * SolutionModifier ::= OrderClause? LimitOffsetClauses?
	 * 
	 * FIRSTS OrderClause => 'ORDER BY' | LimitOffsetClauses => ( 'LIMIT' | 'OFFSET' ) | none
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		switch($.current.token){

		case ORDER_BY:
			return $.analize("OrderClause");
		case LIMIT:
		case OFFSET:
			return $.analize("LimitOffsetClauses");
		}
		
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("OrderClause").FIRSTS() )
			ans.add(t);
		for ( Token t : get("LimitOffsetClauses").FIRSTS() )
			ans.add(t);
		for ( Token t : this.FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.END);
		
		return ans;
	}
}