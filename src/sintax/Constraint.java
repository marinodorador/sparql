package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;

/*
 *  Constraint ::= BrackettedExpression
*	FIRST(Constraint) = {'('}
*/
public class Constraint extends Production{
	public boolean process() throws IOException{
		if($.current.token == Token.LEFT_PARENTH){
			if(!$.analize("BrackettedExpression")) return false;
		}else{
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("Filter").FOLLOWS();
	}
}