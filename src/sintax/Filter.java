package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.syntax.ElementFilter;

import lexic.Token;

/*
 * Filter ::= 'FILTER' BrackettedExpression
 */
public class Filter extends Production{
	ElementFilter node = null;
	@Override
	public boolean process() throws IOException {
		boolean result=false;
		
		if($.current.token == Token.FILTER) { 
			$.next();
			BrackettedExpression be = (BrackettedExpression)$.get("BrackettedExpression");
			result = be.analize();
			node = new ElementFilter(be.expr);
		}else{
			MistakeLog.spected.add(Token.FILTER);
		}
		
		return result;
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