package sintax; 

import java.io.IOException;

import lexic.Token;

public class OptionalGraphPattern extends Production{
	/**
	 * @author Romina
	 *
	 * OptionalGraphPattern ::= 'OPTIONAL' GroupGraphPattern
	 * 
	 * FIRST = 'OPTIONAL'
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		if ( $.current.token == Token.OPTIONAL )
		{
			$.next();
			return $.analize("GroupGraphPattern");
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("GraphPatternNotTriples").FOLLOWS();
	}
}