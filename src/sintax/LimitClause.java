package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * LimitClause ::= 'LIMIT' INTEGER
 **/
public class LimitClause extends Production{

	@Override
	public boolean analize() throws IOException {
		
		if($.current.token == Token.LIMIT){
			$.next();
			if($.current.token == Token.INTEGER) $.next();return true;
		}
		return false;
	}

}