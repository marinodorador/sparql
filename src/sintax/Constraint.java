package sintax; 

import java.io.IOException;

import lexic.Token;

/*
 *  Constraint ::= BrackettedExpression
*	FIRST(Constraint) = {'('}
*/
public class Constraint extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.LEFT_PARENTH){
			if(!$.analize("BrackettedExpression")) return false;
		}else{
			return false;
		}
		return true;
	}
}