package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 *
 *	PrefixedName ::=  PNAME_LN 
 *					| PNAME_NS
 */
public class PrefixedName extends Production{

	public boolean process() throws IOException{
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

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("PrefixedName").FOLLOWS();
	}
}