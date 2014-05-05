package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;

public class _String extends Production{
	public String val = null;
	/**
	 * @author Romina
	 *
	 * String ::= STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2
	 * 
	 * @throws IOException
	 */
	
	public boolean process() throws IOException{	
		
		switch($.current.token){
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:{
				val = $.current.lexeme;
				$.next();
				return true;
			}
		}
		
	return false;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.STRING_LITERAL1 );
		ans.add( Token.STRING_LITERAL2 );
		ans.add( Token.STRING_LITERAL_LONG1 );
		ans.add( Token.STRING_LITERAL_LONG2 );
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.LANGTAG );
		ans.add( Token.TYPE );
		
		for ( Token t : get("RDFLiteral").FOLLOWS() )
			ans.add(t);
		return ans;
	}
}