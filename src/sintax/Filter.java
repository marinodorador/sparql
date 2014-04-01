package sintax; 

import java.io.IOException;

import lexic.Token;

public class Filter extends Production{

	@Override
	public boolean analize() throws IOException {
		if($.current.token == Token.FILTER) {
			$.next();
			return $.analize("Constraint");
		}
		return false;
	}

}