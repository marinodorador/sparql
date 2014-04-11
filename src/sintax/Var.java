package sintax; 

import static lexic.Token.*;
import java.io.IOException;

import lexic.Token;
/*
 * Var ::= VAR1 
		|  VAR2
 **/
public class Var extends Production{
	
	public boolean process() throws IOException{
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

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return construct(new Token[][]{
				$.get("VarOrIRIref").FOLLOWS(),
				$.get("VarOrTerm").FOLLOWS(),
				$.get("OrderCondition").FOLLOWS(),
				$.get("PrimaryExpression").FOLLOWS(),
				new Token[]{FROM}
		});
		 
		
		 
		
	}
}