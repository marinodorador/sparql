package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 *  VarOrIRIref ::=  Var 
 *				|   IRIref
 **/
public class VarOrIRIref extends Production{

	@Override
	public boolean process() throws IOException {
		return $.analize("Var") || $.analize("IRIref");
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}