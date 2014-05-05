package sintax; 

import static lexic.Token.PERIOD;
import static lexic.Token.RIGHT_BRACE;

import java.io.IOException;

import com.hp.hpl.jena.sparql.syntax.ElementFilter;

import lexic.Token;

/*
 * Filter ::= 'FILTER' Constraint
 */
public class Filter extends Production{
	ElementFilter node = null;
	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.FILTER) { 
			$.next();
			Constraint c = (Constraint)$.get("Constraint");
			boolean result = c.analize();
			node = new ElementFilter(c.expr);
			return result;
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
		
		return construct(new Token[][]{
				new Token[]{PERIOD, RIGHT_BRACE},
				get("TriplesBlock").FOLLOWS()
				});
	}

}