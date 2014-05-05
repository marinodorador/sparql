package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.syntax.Element;

import lexic.Token;

/*
 * GraphPatternNotTriples ::= OptionalGraphPattern | GroupOrUnionGraphPattern
 * FIRST(GraphPatternNotTriples) = {'OPTIONAL'}
 * FIRST(GroupOrUnionGraphPattern) = '{' 
 * 
 * */
public class GraphPatternNotTriples extends Production{
	public Element element = null;
	public boolean process() throws IOException{
		if($.current.token == Token.OPTIONAL){
			OptionalGraphPattern ogp = (OptionalGraphPattern)$.get("OptionalGraphPattern");
			if(!ogp.analize()) return false;
			element = ogp.element;
		}else if($.current.token == Token.LEFT_BRACE){
			GroupOrUnionGraphPattern gugp = (GroupOrUnionGraphPattern)$.get("GroupOrUnionGraphPattern");
			if(!gugp.analize()) return false;
			element = gugp.element;
			
		}else{
			return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("OptionalGraphPattern").FIRSTS() )
			ans.add(t);
		
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