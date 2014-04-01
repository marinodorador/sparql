package sintax; 

import java.io.IOException;

/**
 * 
 * @author esteban
 *
 * BooleanLiteral	::= 'true' 				
 *					  | 'false'
 */
public class BooleanLiteral extends Production{
	
	
	public boolean analize() throws IOException{
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
}