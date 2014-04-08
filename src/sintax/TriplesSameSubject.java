package sintax; 

import java.io.IOException;

import lexic.Token;

public class TriplesSameSubject extends Production{
	/**
	 * @author Romina
	 *
	 * TriplesSameSubject ::= VarOrTerm PropertyListNotEmpty |	TriplesNode PropertyList
	 * FIRSTS = { VarOrTerm
	 *          | TriplesNode => '('}
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		
		if ( $.analize("VarOrTerm" ) )
		{
			return $.analize("PropertyListNotEmpty" );
		}
		else if ( $.analize("TriplesNode" ) )
		{
			return $.analize("PropertyList" );
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("TriplesBlock").FOLLOWS(),
				new Token[]{Token.PERIOD}
				});
	}
}