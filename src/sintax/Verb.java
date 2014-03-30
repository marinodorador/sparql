package sintax; 

import java.io.IOException;

/*
 * FIRST(Verb) = {VAR1, VAR2, IRIref, 'a'}
 **/

public class Verb extends Production{

	public boolean analize() throws IOException{
		switch($.current.token){
			case VAR1: case VAR2: case IRI_REF:
				if(!$.analize("VarOrIRIref")) return false;
				break;
			case A:
				$.next();
				break;
			default:
				return false;
		}
		return true;
	}
}