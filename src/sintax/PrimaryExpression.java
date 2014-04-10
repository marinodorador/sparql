package sintax; 

import java.io.IOException;

import lexic.Token;
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
	public boolean process() throws IOException{
		return $.analize("BrackettedExpression") || $.analize("BuiltInCall") || $.analize("IRIrefOrFunction") 
	 || $.analize("RDFLiteral") || $.analize("NumericLiteral") || $.analize("BooleanLiteral") || $.analize("Var");
		
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("UnaryExpression").FOLLOWS()
				});
	}
}