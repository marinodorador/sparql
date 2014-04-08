package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * Var ::= VAR1 
		|  VAR2
 **/
public class Var extends Production{
	
	public boolean process() throws IOException{
		switch($.current.token){
			case VAR1:
				$.next();
				break;
			case VAR2:
				$.next();
				break;
			default:
				return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}