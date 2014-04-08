package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * SourceSelector ::=   IRIref
 **/
public class SourceSelector extends Production{

	@Override
	public boolean process() throws IOException {
		// TODO Auto-generated method stub
		return $.analize("IRIref");
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}
}