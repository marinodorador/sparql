package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
				boolean result = ggp.analize1();
				element = new ElementOptional(ggp.element);
				return result;
			}else{
				MistakeLog.spected.add(Token.LEFT_BRACE);
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.OPTIONAL);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("GraphPatternNotTriples").FOLLOWS();
	}
}