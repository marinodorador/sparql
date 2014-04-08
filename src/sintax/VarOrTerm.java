package sintax; 

import java.io.IOException;

import lexic.Token;

public class VarOrTerm extends Production{
	/**
	 * @author Romina
	 *
	 * VarOrTerm = { Var | GraphTerm }
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		return ( $.analize("Var") || $.analize("GraphTerm") );
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("Var").FOLLOWS(),
				get("GraphTerm").FOLLOWS(),
				});
	}
}