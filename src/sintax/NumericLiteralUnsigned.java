package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 *
 *NumericLiteralUnsigned	  		::=  	INTEGER 
										|	DECIMAL 
										|	DOUBLE
 */
public class NumericLiteralUnsigned extends Production{
	public boolean process() throws IOException{
		switch($.current.token){
			case INTEGER:
				$.next();
			break;
			case DECIMAL:
				$.next();
			break;
			case DOUBLE:
				$.next();
			break;
			default: return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}