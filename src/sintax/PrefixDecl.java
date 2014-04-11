package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;

/*
 * PrefixDecl ::=  'PREFIX' PNAME_NS IRI_REF
 **/
public class PrefixDecl extends Production{
	public boolean process() throws IOException{
		if($.current.token != Token.PREFIX ) return false;
		$.next();
		if($.current.token != Token.PNAME_NS ) return false;
		$.next();
		if($.current.token != Token.IRI_REF ) return false;
		$.next();
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return new Token[]{SELECT};
	}
}