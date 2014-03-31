package sintax; 

import java.io.IOException;

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
	public boolean analize() throws IOException{
		
		switch($.current.token){

		case ORDER_BY:
			return $.analize("OrderClause");
		case LIMIT:
		case OFFSET:
			return $.analize("LimitOffsetClauses");
		}
		
		return true;
	}
}