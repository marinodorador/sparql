package sintax; 

import java.io.IOException;

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
	public Token[] initFIRSTS() throws IOException {
		return new Token[]{
				Token.STRING_LITERAL1,
				Token.STRING_LITERAL2,
				Token.STRING_LITERAL_LONG1,
				Token.STRING_LITERAL_LONG2
		};
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return construct(new Token[][]{
				new Token[]{ Token.LANGTAG, Token.TYPE },
				get("RDFLiteral").FOLLOWS(),
				});
	}
}