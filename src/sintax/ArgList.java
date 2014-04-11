package sintax;

import java.io.IOException;

import lexic.Token;

/**
 * @author esteban
 *	
 * ArgList	 ::=  	( NIL | '(' Expression ( ',' Expression )* ')' )
 */
public class ArgList  extends Production {
	
	@Override
	public boolean process() throws IOException {
		switch($.current.token){
			case NIL: 
				$.next();
				break;
			case LEFT_PARENTH:
				$.next();
				if(!$.analize("Expression")) return false;
				
				while($.current.token == Token.COMMA){
					$.next();
					if(!$.analize("Expression")) return false;
				}
				
				if($.current.token != Token.RIGTH_PARENTH) return false;
				$.next();
				break;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("IRIrefOrFunction").FOLLOWS();
	}

}
