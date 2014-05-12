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
		if ( $.current.token == Token.LEFT_BRACE ){
			$.next();
		}else{
			MistakeLog.spected.add(Token.RIGHT_BRACE);
			return  false;
		}

		// TriplesBlock?
		TriplesBlock tb = (TriplesBlock)$.get("TriplesBlock"); 
		if (tb.analize()){
			element.addElement(tb.element);
			element.addElement(tb.element2);
		}
		
		// ( ( GraphPatternNotTriples | Filter ) '.'? TriplesBlock? )*
		
		do{
			GraphPatternNotTriples gpnt  =(GraphPatternNotTriples) $.get("GraphPatternNotTriples");
			Filter f = (Filter) $.get("Filter");
			
			if (gpnt.analize() ) element.addElement(gpnt.element);  //gpnt
			else if (f.analize()) element.addElement(f.node);  //filter
			else break;
			
			//.?
			if ( $.current.token == Token.PERIOD ) $.next();
			//tb?
			TriplesBlock tb2 = (TriplesBlock) $.get("TriplesBlock");
			if (tb2.analize())  element.addElement(tb2.element);
			
		}while(true);
		
		// '}'
		if ( $.current.token== Token.RIGHT_BRACE ){
			$.next();
			return true;
		}else {
			MistakeLog.spected.add(Token.RIGHT_BRACE);
			return false;
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