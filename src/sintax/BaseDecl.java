package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * BaseDecl ::=  'BASE' IRI_REF
 */
public class BaseDecl extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.BASE){
			$.next();
			if($.analize("IRIref")) return true;
		}
		return false;
	}
}