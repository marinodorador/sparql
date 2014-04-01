package sintax; 

import java.io.IOException;

import lexic.Token;

public class IRIref extends Production{

	@Override
	public boolean analize() throws IOException {
		if($.current.token == Token.IRI_REF){
			$.next();
			return true;
		}
		return false;
	}

}