package sintax; 

import java.io.IOException;
/*
 *  VarOrIRIref ::=  Var 
 *				|   IRIref
 **/
public class VarOrIRIref extends Production{

	@Override
	public boolean analize() throws IOException {
		return $.analize("Var") || $.analize("IRIref");
	}

}