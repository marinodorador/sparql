package sintax; 

import java.io.IOException;

import lexic.Token;

public class NamedGraphClause extends Production{
	/**
	 * @author Romina
	 *
	 * NamedGraphClause ::= 'NAMED' SourceSelector
	 * 
	 * FIRST = 'NAMED'
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		if ( $.current.token == Token.NAMED )
		{
			$.next();
			return $.analize("SourceSelector");
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		return new Token[]{ Token.WHERE , Token.LEFT_BRACE };
	}
}