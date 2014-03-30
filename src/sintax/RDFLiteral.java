package sintax; 

import java.io.IOException;

public class RDFLiteral extends Production{
	/**
	 * @author Romina
	 *
	 * RDFLiteral = String ( LANGTAG | ( '^^' IRIref ) )?
	 * FIRSTS: STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2
	 *  
	 * @throws IOException 
	 */
	
	public boolean analize() throws IOException{
		
		if(!$.analize("String"))
			return false;
		
		switch($.current.token){
			
		case TYPE:
		{
			$.next();
			if(!$.analize("IRIref"))
				return false;
		}
			
		case LANGTAG:
			$.next();
		}
		
	return true;
	}
}