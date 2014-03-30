package sintax; 

import java.io.IOException;

import lexic.Token;

public class Var extends Production{
	
	public boolean analize() throws IOException{
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
}