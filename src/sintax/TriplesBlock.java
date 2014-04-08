package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * TriplesBlock	 ::=  	TriplesSameSubject ( '.' TriplesBlock? )?
 **/
public class TriplesBlock extends Production{

	@Override
	public boolean process() throws IOException {
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

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}