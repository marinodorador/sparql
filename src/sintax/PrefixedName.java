package sintax; 

import java.io.IOException;

/**
 * 
 * @author esteban
 *
 *	PrefixedName ::=  PNAME_LN 
 *					| PNAME_NS
 */
public class PrefixedName extends Production{

	public boolean analize() throws IOException{
		switch($.current.token){
		case PNAME_LN:
			$.next();
			break;
		case PNAME_NS:
			$.next();
			break;
		default: return false;
		}
		return true;
	}
}