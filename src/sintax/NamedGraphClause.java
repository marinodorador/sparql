package sintax; 

import java.io.IOException;

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
			boolean result = ss.analize();
			uri = ss.uri;
			return result;
		}
		
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		return new Token[]{ Token.WHERE , Token.LEFT_BRACE };
	}
}