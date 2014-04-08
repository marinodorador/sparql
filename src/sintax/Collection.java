package sintax; 

import java.io.IOException;

import lexic.Token;
/**
 * 
 * Collection ::= '(' GraphNode+ ')'
 *
 */
public class Collection extends Production{

	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.LEFT_PARENTH){
			$.next();
			if($.analize("GraphNode")){
				while($.analize("GraphNode"));
				if($.current.token == Token.RIGTH_PARENTH){
					$.next();
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}