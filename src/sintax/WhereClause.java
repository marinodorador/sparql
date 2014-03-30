package sintax; 

import java.io.IOException;

import lexic.Token;

public class WhereClause extends Production{

	public boolean analize() throws IOException{
		if($.current.token == Token.WHERE) $.next();
		
		if($.current.token != Token.LEFT_BRACE) return false;
		
		if(!$.analize("GroupGraphPattern")) return false;
		
		return true;
	}
}