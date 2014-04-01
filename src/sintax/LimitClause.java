package sintax; 

import java.io.IOException;

import lexic.Token;

public class LimitClause extends Production{

	@Override
	public boolean analize() throws IOException {
		
		if($.current.token == Token.LIMIT){
			$.next();
			if($.analize("INTEGER"))return true;
		}
		return false;
	}

}