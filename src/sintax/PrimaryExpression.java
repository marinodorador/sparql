package sintax; 

import java.io.IOException;
/*
 * PrimaryExpression	::=  	BrackettedExpression 
 *							| 	BuiltInCall 
 *							| 	IRIrefOrFunction 
 *							| 	RDFLiteral 
 *							| 	NumericLiteral 
 *							| 	BooleanLiteral 
 *							| 	Var
 */
public class PrimaryExpression extends Production{
	public boolean analize() throws IOException{
		return $.analize("BrackettedExpression") || $.analize("BuiltInCall") || $.analize("IRIrefOrFunction") 
	 || $.analize("RDFLiteral") || $.analize("NumericLiteral") || $.analize("BooleanLiteral") || $.analize("Var");
		
	}
}