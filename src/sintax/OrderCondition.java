package sintax; 

import java.io.IOException;

import lexic.Token;

public class OrderCondition extends Production{
	/**
	 * @author Romina
	 *
	 * OrderCondition ::= ( ( 'ASC' | 'DESC' ) BrackettedExpression )| ( Constraint | Var )
	 * 
	 * FIRSTS = 'ASC' | 'DESC' | Constraint=> '(' | Var=> VAR1 | VAR2
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		switch($.current.token){

		case ASC:
		case DESC:
		{
			$.next();
			return $.analize("BrackettedExpression");
		}
		case LEFT_PARENTH:
			return $.analize("Constraint");
			
		default:
			return $.analize("Var");
		}
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("OrderClause").FOLLOWS();
	}
}