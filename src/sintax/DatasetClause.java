package sintax; 

import java.io.IOException;


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
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return new Token[]{ Token.WHERE , Token.LEFT_BRACE };
	}
}