package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * @author Esteban
 * WhereClause ::=   'WHERE'? GroupGraphPattern
 **/
public class WhereClause extends Production{

	public boolean analize() throws IOException{
		if($.current.token == Token.WHERE) $.next();		
		
		if(!$.analize("GroupGraphPattern")) return false;
		
		return true;
	}
}