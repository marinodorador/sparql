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
	public boolean analize() throws IOException{
		
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
}