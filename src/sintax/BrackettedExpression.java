package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 *
 *	FIRST(BrackettedExpression) = {} 
 */
public class BrackettedExpression extends Production{
	public boolean analize() throws IOException{
		if($.current.token != Token.LEFT_PARENTH) return false;
		$.next();
		if(!$.analize("Expression")) return false;
		if($.current.token != Token.LEFT_PARENTH) return false;
		$.next();
		return true;
	}
}