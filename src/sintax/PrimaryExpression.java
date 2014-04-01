package sintax; 

import java.io.IOException;

public class PrimaryExpression extends Production{
	public boolean analize() throws IOException{
		return $.analize("BrackettedExpression") || $.analize("BuiltInCall") || $.analize("IRIrefOrFunction") 
	 || $.analize("RDFLiteral") || $.analize("NumericLiteral") || $.analize("BooleanLiteral") || $.analize("Var");
		
	}
}