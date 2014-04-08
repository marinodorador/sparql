package sintax; 

import java.io.IOException;

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
	public Token[] FOLLOWS() {
		return new Token[]{};
	}
}