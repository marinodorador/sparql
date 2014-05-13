package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;

/*
 * PrefixDecl ::=  'PREFIX' PNAME_NS IRI_REF
 **/
public class PrefixDecl extends Production{
	public String prefix;
	public String uri;
	
	public boolean process() throws IOException{
		if($.current.token != Token.PREFIX ){
			MistakeLog.spected.add(Token.PREFIX);
			return false;
		}
		$.next();
		if($.current.token != Token.PNAME_NS ){
			MistakeLog.spected.add(Token.PNAME_NS);
			return false;
		}
		prefix = $.current.lexeme.substring(0, $.current.lexeme.length()-1);
		$.next();
		if($.current.token != Token.IRI_REF ){
			MistakeLog.spected.add(Token.IRI_REF);
			return false;
		}
		uri = $.current.lexeme;
		/*uri = uri.substring(1, uri.length()-1);
		if(uri.charAt(uri.length()-1) == '#'){
			uri = uri.substring(1, uri.length()-1);
		}*/
		$.next();
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.PREFIX);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("Prologue").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("PrefixDecl").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}