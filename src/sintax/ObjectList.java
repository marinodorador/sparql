package sintax; 

import java.io.IOException;

import lexic.Token;

public class ObjectList extends Production{
	/**
	 * @author Romina
	 *
	 * ObjectList = Object ( ',' Object )*
	 * FIRST = Object
	 * 
	 * @throws IOException
	 */
	public boolean process() throws IOException{
		
		if ( $.analize("Object") )
		{
			while ( $.current.token == Token.COMMA )
			{
				$.next();
				if ( ! $.analize("Object") )
					return false;
			}
			return true;
		}
		
		return false;
	}
	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("PropertyListNotEmpty").FOLLOWS(),
				new Token[]{Token.SEMI}
				});
	}
}