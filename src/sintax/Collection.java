package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add(Token.LEFT_PARENTH);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("TriplesNode").FOLLOWS();
	}

}