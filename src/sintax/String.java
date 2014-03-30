package sintax; 

import java.io.IOException;

public class String extends Production{
	
	/**
	 * @author Romina
	 *
	 * String ::= STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2
	 * 
	 * @throws IOException
	 */
	
	public boolean analize() throws IOException{	
		
		switch($.current.token){

		case STRING_LITERAL1:
		case STRING_LITERAL2:
		case STRING_LITERAL_LONG1:
		case STRING_LITERAL_LONG2:
			$.next();
			return true;
		}
		
	return false;
	}	 
}