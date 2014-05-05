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
		throw new SemanticException("Acci�n no soportada");
		//return ( $.analize("Collection") );
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return get("Collection").FIRSTS();
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct( new Token[][]{
				get("GraphNode").FOLLOWS(),
				new Token[]{ Token.VAR1, Token.VAR2, Token.IRI_REF, Token.PNAME_LN, Token.PNAME_NS, Token.A}
		});
	}
}