package sintax; 

import java.io.IOException;

import lexic.Token;

/*
 * FIRST(GraphPatternNotTriples) = {'OPTIONAL'}
 * 
 * */
public class GraphPatternNotTriples extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.OPTIONAL){
			if(!$.analize("OptionalGraphPattern")) return false;
		}else{
			return false;
		}
		return true;
	}
}