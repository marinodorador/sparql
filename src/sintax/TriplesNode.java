package sintax; 

import java.io.IOException;

import lexic.Token;

public class TriplesNode extends Production{
	/**
	 * @author Romina
	 *
	 * TriplesNode ::= Collection
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		return ( $.analize("Collection") );
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("Collection").FOLLOWS();
	}
}