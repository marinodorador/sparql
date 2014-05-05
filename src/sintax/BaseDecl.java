package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * BaseDecl ::=  'BASE' IRI_REF
 */
public class BaseDecl extends Production{
	public String baseUri;
	public boolean process() throws IOException{
		if($.current.token == Token.BASE){
			$.next();
			IRIref iriRef = (IRIref)$.get("IRIref");
			if(!iriRef.analize()) return false;
			baseUri  = iriRef.val;
		}
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return new Token[]{ Token.BASE};
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return new Token[]{ Token.PREFIX, Token.SELECT};
	}
}