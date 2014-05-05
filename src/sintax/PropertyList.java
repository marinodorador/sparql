package sintax; 

import java.io.IOException;

import lexic.Token;

/*
 * PropertyList	  				::=  	PropertyListNotEmpty?
 * 
 * FIRST(PropertyList) = {VAR1, VAR2, IRIRef}
 * 
 * Follow(PropertyListNotEmpty?) = {'}',OPTIONAL, FILTER, '.'}
 * */
public class PropertyList extends Production{
	public boolean process() throws IOException{
		
		if($.current.token == Token.VAR1 || $.current.token == Token.VAR2 || $.current.token == Token.IRI_REF){
			if(!$.analize("PropertyListNotEmpty")) return false;
		}
		
		if($.current.token != Token.RIGHT_BRACE 
				&& $.current.token != Token.OPTIONAL 
				&& $.current.token != Token.FILTER 
				&& $.current.token != Token.PERIOD) return false;
		
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("TriplesSameSubject").FOLLOWS();
	}
}