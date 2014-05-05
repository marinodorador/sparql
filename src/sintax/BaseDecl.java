package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.BASE);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("PrefixDecl").FIRSTS() )
			ans.add(t);
		for ( Token t : get("Prologue").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}