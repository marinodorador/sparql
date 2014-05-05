package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.FILTER);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.PERIOD );
		ans.add( Token.RIGHT_BRACE );
		
		for ( Token t : get("TriplesBlock").FIRSTS() )
			ans.add(t);
		
		return ans;
	}

}