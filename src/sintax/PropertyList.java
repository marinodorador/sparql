package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("PropertyListNotEmpty").FIRSTS() )
			ans.add(t);
		for ( Token t : this.FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return $.get("TriplesSameSubject").FOLLOWS();
	}
}