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

//	@Override
//	public Token[] FOLLOWS(){
//		return new Token[]{ Token.OPTIONAL, Token.FILTER, Token.RIGHT_BRACE};
//	}
	public Token[] FOLLOWS() throws IOException {
		return new Token[]{ 
				Token.OPTIONAL, 
				Token.FILTER, 
				Token.RIGHT_BRACE, 
				Token.PERIOD};
	}
}