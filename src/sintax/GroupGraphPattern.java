package sintax; 

import java.io.IOException;

import lexic.Token;

public class GroupGraphPattern extends Production{
	/**
	 * @author Romina
	 *
	 * GroupGraphPattern ::= '{' TriplesBlock? ( ( GraphPatternNotTriples | Filter ) '.'? TriplesBlock? )* '}'
	 * FIRST = '{'
	 * 
	 TriplesBlock FIRST = VAR1 | VAR2 | IRI_REF |PNAME_LN | PNAME_NS | STRING_LITERAL1 | STRING_LITERAL2 
	   | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | INTEGER | DECIMAL |	DOUBLE | INTEGER_POSITIVE 
	   | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE
	   | 'true' | 'false' |	NIL
	   
	  GraphPatternNotTriples FIRST = 'OPTIONAL'
	   
	  Filter  FIRST = 'FILTER'
	   
	   
	 * 
	 * @throws IOException 
	 */
	public boolean analize() throws IOException{
		
		// '{'
		if ( $.current.token != Token.LEFT_BRACE )
			return false;
		$.next();
		
		// TriplesBlock?
		if (
				$.current.token != Token.RIGHT_BRACE &&
				$.current.token != Token.OPTIONAL && 
				$.current.token != Token.FILTER && 
				$.current.token != Token.PERIOD )
		{
			if ( ! $.analize("TriplesBlock") )
				return false;
		}
		
		// ( ( GraphPatternNotTriples | Filter ) '.'? TriplesBlock? )* '}'
		while (true)
		{
			// '}'
			if ( $.current.token== Token.RIGHT_BRACE )
			{
				$.next();
				return true;
			}

			// ( GraphPatternNotTriples | Filter )
			switch($.current.token){
				case OPTIONAL:
				{
					if (! $.analize("GraphPatternNotTriples"))
						return false;
					break;
				}
				case FILTER:
				{
					if (! $.analize("Filter"))
						return false;
					break;
				}
				default:
					return false;
			}

			// '.'?
			if ( $.current.token != Token.PERIOD )
				$.next();

			// TriplesBlock?
			if (
					$.current.token != Token.RIGHT_BRACE &&
					$.current.token != Token.OPTIONAL && 
					$.current.token != Token.FILTER && 
					$.current.token != Token.PERIOD )
			{
				if ( ! $.analize("TriplesBlock") )
					return false;
			}
			
			
		}
	}
}