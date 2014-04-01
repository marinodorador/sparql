package sintax; 

import java.io.IOException;

public class VarOrIRIref extends Production{

	@Override
	public boolean analize() throws IOException {
		return $.analize("Var") || $.analize("IRIref");
	}

}