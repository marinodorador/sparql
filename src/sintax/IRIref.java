package sintax; 

import java.io.IOException;

import lexic.Token;
/**
 * 
 * IRIref ::= IRI_REF 
 *			 |PrefixedName
 *
 */
public class IRIref extends Production{

	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.IRI_REF){
			$.next();
			return true;
		}else if($.current.token == Token.PNAME_LN || $.current.token == Token.PNAME_NS){
			$.next();
			return true;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}