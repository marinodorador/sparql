package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.syntax.Element;

import lexic.Token;
import static lexic.Token.*;

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
	public Token[] initFIRSTS() throws IOException {
		return get("OptionalGraphPattern").FIRSTS();
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