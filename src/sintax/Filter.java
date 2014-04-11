package sintax; 

import static lexic.Token.PERIOD;
import static lexic.Token.RIGHT_BRACE;

import java.io.IOException;

import lexic.Token;

/*
 * Filter ::= 'FILTER' Constraint
 */
public class Filter extends Production{

	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.FILTER) {
			$.next();
			return $.analize("Constraint");
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		
		return construct(new Token[][]{
				new Token[]{PERIOD, RIGHT_BRACE},
				get("TriplesBlock").FOLLOWS()
				});
	}

}