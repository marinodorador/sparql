package sintax; 

import java.io.IOException;

/*
 * Verb	::= VarOrIRIref | 'a'
 * 
 * FIRST(Verb) = {VAR1, VAR2, IRIref, 'a',PNAME_LN,PNAME_NS}
 **/

public class Verb extends Production{

	public boolean analize() throws IOException{
		switch($.current.token){
			case VAR1: case VAR2: case IRI_REF: case PNAME_LN: case PNAME_NS:
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