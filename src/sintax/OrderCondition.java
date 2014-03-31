package sintax; 

import java.io.IOException;

public class OrderCondition extends Production{
	/**
	 * @author Romina
	 *
	 * OrderCondition ::= ( ( 'ASC' | 'DESC' ) BrackettedExpression )| ( Constraint | Var )
	 * 
	 * FIRSTS = 'ASC' | 'DESC' | Constraint=> '(' | Var
	 * 
	 * @throws IOException 
	 */
	public boolean analize() throws IOException{
		
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
}