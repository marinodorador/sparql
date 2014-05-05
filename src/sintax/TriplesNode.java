package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
		throw new SemanticException("Acci�n no soportada");
		//return ( $.analize("Collection") );
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("Collection").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("PropertyList").FIRSTS() )
			ans.add(t);
		
		for ( Token t : get("GraphNode").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}