package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;
/*
 * @author Esteban
 * WhereClause ::=   'WHERE'? GroupGraphPattern
 **/
public class WhereClause extends Production{

	public boolean process() throws IOException{
		if($.current.token == Token.WHERE) $.next();		
		
		if(!$.analize("GroupGraphPattern")) return false;
		
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return new Token[]{ORDER_BY};
	}
}