package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.PNAME_LN );
		ans.add( Token.PNAME_NS );
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("IRIref").FOLLOWS();
	}
}