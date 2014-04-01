package sintax; 

import java.io.IOException;

import lexic.Token;

/*
 * GraphPatternNotTriples ::= OptionalGraphPattern | GroupOrUnionGraphPattern
 * FIRST(GraphPatternNotTriples) = {'OPTIONAL'}
 * FIRST(GroupOrUnionGraphPattern) = '{' 
 * 
 * */
public class GraphPatternNotTriples extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.OPTIONAL){
			if(!$.analize("OptionalGraphPattern")) return false;
		}else if($.current.token == Token.LEFT_BRACE){
			if(!$.analize("GroupOrUnionGraphPattern")) return false;
		}else{
			return false;
		}
		return true;
	}
}