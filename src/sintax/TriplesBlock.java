package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.syntax.ElementPathBlock;	

import lexic.Token;
import static lexic.Token.*;
/*
 * TriplesBlock	 ::=  	TriplesSameSubject ( '.' TriplesBlock? )?
 **/
public class TriplesBlock extends Production{
	public ElementPathBlock element = new ElementPathBlock();
	public boolean firsts = $.current.token == VAR1 || 
			 $.current.token == VAR2 ||
			 $.current.token == IRI_REF ||
			 $.current.token == PNAME_LN ||
			 $.current.token ==PNAME_NS ||
			 $.current.token ==STRING_LITERAL1 ||
			$.current.token ==STRING_LITERAL2 ||
			$.current.token ==STRING_LITERAL_LONG1 ||
			$.current.token ==STRING_LITERAL_LONG2 ||
			$.current.token ==INTEGER ||
			$.current.token ==DECIMAL ||
			$.current.token ==DOUBLE ||
			$.current.token ==INTEGER_POSITIVE ||
			$.current.token == DECIMAL_POSITIVE ||
			$.current.token == DOUBLE_POSITIVE ||
			$.current.token == INTEGER_NEGATIVE ||
			$.current.token == DECIMAL_NEGATIVE ||
			$.current.token == DOUBLE_NEGATIVE ||
			$.current.token == TRUE ||
			$.current.token == FALSE ||
			$.current.token == NIL;
	@Override
	public boolean process() throws IOException {
		TriplesSameSubject tss = (TriplesSameSubject)$.get("TriplesSameSubject");
		
		if(!tss.analize()) return false;
		
		for(int i=0;i<tss.triples.size();i++)
			element.addTriple(tss.triples.get(i));
		
		if($.current.token == Token.PERIOD){
			$.next();
		
			if(firsts){
				if(!this.analize()) return false;
			}
		}
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("TriplesSameSubject").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("GraphPatternNotTriples").FIRSTS() )
			ans.add(t);
		for ( Token t : get("Filter").FIRSTS() )
			ans.add(t);
		
		ans.add( Token.RIGHT_BRACE );
		
		return ans;
	}
}