package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 *
 * BooleanLiteral	::= 'true' 				
 *					  | 'false'
 */
public class BooleanLiteral extends Production{
	
	
	public boolean process() throws IOException{
		switch($.current.token){
			case TRUE:
				$.next();
				break;
			case FALSE:
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