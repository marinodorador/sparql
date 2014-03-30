package sintax; 

import java.io.IOException;

public class TriplesNode extends Production{
	/**
	 * @author Romina
	 *
	 * TriplesNode ::= Collection
	 * 
	 * @throws IOException
	 */
	public boolean analize() throws IOException{
		return ( $.analize("Collection") );
	}
}