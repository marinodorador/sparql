package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;

/*
 * GraphPatternNotTriples ::= OptionalGraphPattern | GroupOrUnionGraphPattern
 * FIRST(GraphPatternNotTriples) = {'OPTIONAL'}
 * FIRST(GroupOrUnionGraphPattern) = '{' 
 * 
 * */
public class GraphPatternNotTriples extends Production{
	public boolean process() throws IOException{
		if($.current.token == Token.OPTIONAL){
			if(!$.analize("OptionalGraphPattern")) return false;
		}else if($.current.token == Token.LEFT_BRACE){
			if(!$.analize("GroupOrUnionGraphPattern")) return false;
		}else{
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return construct(new Token[][]{new Token[]{PERIOD,
		VAR1,
		VAR2,
		IRI_REF,
		PNAME_LN,
		PNAME_NS,STRING_LITERAL1,
		STRING_LITERAL2,
		STRING_LITERAL_LONG1,
		STRING_LITERAL_LONG2,
		INTEGER,
		DECIMAL,
		DOUBLE,
		INTEGER_POSITIVE,
		DECIMAL_POSITIVE,
		DOUBLE_POSITIVE,
		INTEGER_NEGATIVE,
		DECIMAL_NEGATIVE,
		DOUBLE_NEGATIVE,
		TRUE,
		FALSE,
		RIGHT_BRACE
	}});
	}
}