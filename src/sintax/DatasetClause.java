package sintax; 

import java.io.IOException;


import lexic.Token;
/*
 * ArgList ::= ( NIL | '(' Expression ( ',' Expression )* ')' )
 */
public class DatasetClause extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.FROM){
			$.next();
			if($.analize("DefaultGraphClause")){
				return true;
			}else if ($.analize("NamedGraphClause")){
				return true;
			}
		}
		return false;
	}
}