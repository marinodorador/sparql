package sintax; 

import java.io.IOException;
import java.util.ArrayList;


import lexic.Token;
/*
 * ArgList ::= ( NIL | '(' Expression ( ',' Expression )* ')' )
 */
public class DatasetClause extends Production{
	public com.hp.hpl.jena.query.Query  query;
	boolean isNamed = false;
	String uri = null;
	public boolean process() throws IOException{
		if($.current.token == Token.FROM)
		{
			$.next();
			if ( $.current.token == Token.NAMED ){
				isNamed = true;
				NamedGraphClause ngc = (NamedGraphClause)$.get("NamedGraphClause");
				ngc.query = query;
				boolean result = ngc.analize();
				uri = ngc.uri;
				return result;
			}else{
				DefaultGraphClause dgc = (DefaultGraphClause)$.get("DefaultGraphClause");
				isNamed = false;
				boolean result = dgc.analize();
				uri = dgc.uri;
				return result;
			}
				
		}
		
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.FROM);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("WhereClause").FIRSTS() )
			ans.add(t);
		for ( Token t : get("DatasetClause").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}