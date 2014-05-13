package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;

public class NamedGraphClause extends Production{
	public com.hp.hpl.jena.query.Query  query;
	public String uri = null;
	/**
	 * @author Romina
	 *
	 * NamedGraphClause ::= 'NAMED' SourceSelector
	 * 
	 * FIRST = 'NAMED'
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		if ( $.current.token == Token.NAMED )
		{
			$.next();
			SourceSelector ss =(SourceSelector) $.get("SourceSelector");
			boolean result = ss.analize1();
			uri = ss.uri;
			return result;
		}
		MistakeLog.spected.add(Token.NAMED);
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.NAMED);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("DatasetClause").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}