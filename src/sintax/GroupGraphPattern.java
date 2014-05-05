package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.syntax.ElementGroup;

import lexic.Token;

public class GroupGraphPattern extends Production{
	public ElementGroup element = new ElementGroup();
	/**
	 * @author Romina
	 *
	 * GroupGraphPattern ::= '{' TriplesBlock? ( ( GraphPatternNotTriples | Filter ) '.'? TriplesBlock? )* '}'
	 * FIRST = '{'
	 * 
	 *TriplesBlock FIRST = VAR1 | VAR2 | IRI_REF |PNAME_LN | PNAME_NS | STRING_LITERAL1 | STRING_LITERAL2 
	 *  | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | INTEGER | DECIMAL |	DOUBLE | INTEGER_POSITIVE 
	 *  | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE
	 *  | 'true' | 'false' |	NIL
	 *  
	 * GraphPatternNotTriples FIRST = 'OPTIONAL'
	 *  
	 * Filter  FIRST = 'FILTER'
	 *  
	 *  
	 * 
	 * @throws IOException 
	 */
	public boolean process() throws IOException{
		
		// '{'
		if ( $.current.token != Token.LEFT_BRACE )
			return false;
		$.next();
		
		// TriplesBlock?
		if (
				$.current.token == Token.VAR1 ||
				$.current.token == Token.VAR2 ||
				$.current.token == Token.IRI_REF || 
				$.current.token == Token.PNAME_LN || 
				$.current.token == Token.PNAME_NS || 
				$.current.token == Token.STRING_LITERAL1 || 
				$.current.token == Token.STRING_LITERAL2 || 
				$.current.token == Token.STRING_LITERAL_LONG1 || 
				$.current.token == Token.STRING_LITERAL_LONG2 || 
				$.current.token == Token.INTEGER || 
				$.current.token == Token.DECIMAL || 
				$.current.token == Token.DOUBLE || 
				$.current.token == Token.INTEGER_POSITIVE|| 
				$.current.token == Token.DECIMAL_POSITIVE|| 
				$.current.token == Token.DOUBLE_POSITIVE|| 
				$.current.token == Token.INTEGER_NEGATIVE|| 
				$.current.token == Token.DECIMAL_NEGATIVE|| 
				$.current.token == Token.DOUBLE_NEGATIVE|| 
				$.current.token == Token.TRUE|| 
				$.current.token == Token.FALSE || 
				$.current.token == Token.NIL)
		{
			TriplesBlock tb = (TriplesBlock)$.get("TriplesBlock"); 
			if (!tb.analize()) return false;
			element.addElement(tb.element);
		}
		
		// ( ( GraphPatternNotTriples | Filter ) '.'? TriplesBlock? )* '}'
		while (true)
		{
			
			// '}'
			if ( $.current.token== Token.RIGHT_BRACE ){
				$.next();
				return true;
			}else if ( $.current.token == Token.END )
				return (MistakeLog.spected(" } "));

			// ( GraphPatternNotTriples | Filter )
			switch($.current.token){
				case LEFT_BRACE:
				case OPTIONAL:{
					GraphPatternNotTriples gpnt  =(GraphPatternNotTriples) $.get("GraphPatternNotTriples");
						if (!gpnt.analize() ) return false;
						element.addElement(gpnt.element);
						break;
				}
				
					
				case FILTER:
				{
					Filter f = (Filter) $.get("Filter");
					if (! f.analize()) return false;
					element.addElement(f.node);
					break;
				}
				default:
					return false;
			}

			// '.'?
			if ( $.current.token == Token.PERIOD )
				$.next();

			// TriplesBlock?
			if (
					$.current.token == Token.VAR1 ||
					$.current.token == Token.VAR2 ||
					$.current.token == Token.IRI_REF || 
					$.current.token == Token.PNAME_LN || 
					$.current.token == Token.PNAME_NS || 
					$.current.token == Token.STRING_LITERAL1 || 
					$.current.token == Token.STRING_LITERAL2 || 
					$.current.token == Token.STRING_LITERAL_LONG1 || 
					$.current.token == Token.STRING_LITERAL_LONG2 || 
					$.current.token == Token.INTEGER || 
					$.current.token == Token.DECIMAL || 
					$.current.token == Token.DOUBLE || 
					$.current.token == Token.INTEGER_POSITIVE|| 
					$.current.token == Token.DECIMAL_POSITIVE|| 
					$.current.token == Token.DOUBLE_POSITIVE|| 
					$.current.token == Token.INTEGER_NEGATIVE|| 
					$.current.token == Token.DECIMAL_NEGATIVE|| 
					$.current.token == Token.DOUBLE_NEGATIVE|| 
					$.current.token == Token.TRUE|| 
					$.current.token == Token.FALSE || 
					$.current.token == Token.NIL){
					TriplesBlock tb2 = (TriplesBlock) $.get("TriplesBlock");
						if ( ! tb2.analize()) return false;
						element.addElement(tb2.element);
			}
			
		}
		
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.LEFT_BRACE);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("WhereClause").FOLLOWS() )
			ans.add(t);
		for ( Token t : get("OptionalGraphPattern").FOLLOWS() )
			ans.add(t);
		
		return ans;
	}
}