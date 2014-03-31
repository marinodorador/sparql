package sintax; 

import java.io.IOException;

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
	public boolean analize() throws IOException{
		
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
}