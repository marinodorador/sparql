package sintax; 

import java.io.IOException;

import lexic.Token;

public class GraphTerm extends Production{
	/**
	 * @author Romina
	 *
	 * GraphTerm = IRIref |	RDFLiteral | NumericLiteral  | BooleanLiteral |	NIL
	 * 
	 * FIRSTS: NIL | (IRIref |	RDFLiteral | NumericLiteral  | BooleanLiteral ).FIRSTS
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		
		if ( $.current.token == Token.NIL )
			return true;
		
		return ( $.analize("IRIref") || 
				$.analize("RDFLiteral") || 
				$.analize("NumericLiteral") || 
				$.analize("BooleanLiteral")  );
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}