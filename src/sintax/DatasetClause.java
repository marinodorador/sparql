package sintax; 

import java.io.IOException;


import lexic.Token;
/*
 * ArgList ::= ( NIL | '(' Expression ( ',' Expression )* ')' )
 */
public class DatasetClause extends Production{
	public boolean process() throws IOException{
		if($.current.token == Token.FROM)
		{
			$.next();
			if ( $.current.token == Token.NAMED )
				return $.analize("NamedGraphClause");
			else
				return $.analize("DefaultGraphClause");
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}