package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 *	BrackettedExpression ::= '(' Expression ')'
 *	FIRST(BrackettedExpression) = {} 
 */
public class BrackettedExpression extends Production{
	public boolean process() throws IOException{
		if($.current.token != Token.LEFT_PARENTH) return false;
		$.next();
		if(!$.analize("Expression")) return false;
		if($.current.token != Token.LEFT_PARENTH) return false;
		$.next();
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}