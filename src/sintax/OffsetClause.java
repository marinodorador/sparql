package sintax; 

import java.io.IOException;

import lexic.Token;

public class OffsetClause extends Production{

	public boolean analize() throws IOException{
		if($.current.token != Token.OFFSET) return false;
			$.next();
		if($.current.token == Token.INTEGER) return false;
			$.next();
		return true;
	}
}