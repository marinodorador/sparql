package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.syntax.ElementOptional;

import lexic.Token;

public class OptionalGraphPattern extends Production{
	public ElementOptional element;
	/**
	 * @author Romina
	 *
	 * OptionalGraphPattern ::= 'OPTIONAL' GroupGraphPattern
	 * 
	 * FIRST = 'OPTIONAL'
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		if ( $.current.token == Token.OPTIONAL )
		{
			$.next();
			
			if ( $.current.token == Token.LEFT_BRACE ){
				GroupGraphPattern ggp = (GroupGraphPattern) $.get("GroupGraphPattern");
				boolean result = ggp.analize();
				element = new ElementOptional(ggp.element);
				return result;
			}else
				return MistakeLog.spected(" { ");
		}
		
		return false;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return new Token[]{ Token.OPTIONAL };
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		return get("GraphPatternNotTriples").FOLLOWS();
	}
}