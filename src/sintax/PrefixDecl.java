package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;

/*
 * PrefixDecl ::=  'PREFIX' PNAME_NS IRI_REF
 **/
public class PrefixDecl extends Production{
	public String prefix;
	public String uri;
	
	public boolean process() throws IOException{
		if($.current.token != Token.PREFIX ) return false;
		$.next();
		if($.current.token != Token.PNAME_NS ) return false;
		prefix = $.current.lexeme.substring(0, $.current.lexeme.length()-1);
		$.next();
		if($.current.token != Token.IRI_REF ) return false;
		uri = $.current.lexeme;
		/*uri = uri.substring(1, uri.length()-1);
		if(uri.charAt(uri.length()-1) == '#'){
			uri = uri.substring(1, uri.length()-1);
		}*/
		$.next();
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return new Token[]{PREFIX};
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return new Token[]{SELECT};
	}
}