package sintax; 

import java.io.IOException;

import lexic.Token;

public class RDFLiteral extends Production{
	/**
	 * @author Romina
	 *
	 * RDFLiteral = String ( LANGTAG | ( '^^' IRIref ) )?
	 * FIRSTS: String.FIRSTS
	 *  
	 * @throws IOException 
	 */
	
	public boolean process() throws IOException{
		
		if(!$.analize("String"))
			return false;
		
		switch($.current.token){
			
		case TYPE:
		{
			$.next();
			if(!$.analize("IRIref"))
				return false;
		}
			
		case LANGTAG:
			$.next();
		}
		
	return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return get("PrimaryExpression").FOLLOWS();
	}
}