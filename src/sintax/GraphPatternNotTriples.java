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

//	@Override
//	public Token[] FOLLOWS() {
//		// TODO Auto-generated method stub
//		return construct(new Token[][]{new Token[]{PERIOD,
//		VAR1,
//		VAR2,
//		IRI_REF,
//		PNAME_LN,
//		PNAME_NS,STRING_LITERAL1,
//		STRING_LITERAL2,
//		STRING_LITERAL_LONG1,
//		STRING_LITERAL_LONG2,
//		INTEGER,
//		DECIMAL,
//		DOUBLE,
//		INTEGER_POSITIVE,
//		DECIMAL_POSITIVE,
//		DOUBLE_POSITIVE,
//		INTEGER_NEGATIVE,
//		DECIMAL_NEGATIVE,
//		DOUBLE_NEGATIVE,
//		TRUE,
//		FALSE,
//		RIGHT_BRACE
//	}});
//	}

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