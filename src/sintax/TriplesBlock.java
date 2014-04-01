package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * TriplesBlock	 ::=  	TriplesSameSubject ( '.' TriplesBlock? )?
 **/
public class TriplesBlock extends Production{

	@Override
	public boolean analize() throws IOException {
		if($.analize("TriplesSameSubject")){
			if($.current.token == Token.PERIOD){
				$.next();
				if($.analize("TriplesBlock")){
					return true;
				}
			}
			return true;
		}
		return false;
	}

}