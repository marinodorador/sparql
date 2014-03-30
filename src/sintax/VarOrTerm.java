package sintax; 

import java.io.IOException;

public class VarOrTerm extends Production{
	/**
	 * @author Romina
	 *
	 * VarOrTerm = { Var | GraphTerm }
	 * 
	 * @throws IOException
	 */
	public boolean analize() throws IOException{
		return ( $.analize("Var") || $.analize("GraphTerm") );
	}
}