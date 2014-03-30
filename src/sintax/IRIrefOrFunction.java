package sintax; 

import java.io.IOException;

import lexic.Token;

/**
 * 
 * @author esteban
 * FIRST(IRIrefOrFunction) = {IRI_REF, PNAME_LN, PNAME_NS}
 * FIRST(ArgList?) = {NIL, '(' }
 * FOLLOW(ArgList?) = {*, /,+,-}
 */
public class IRIrefOrFunction extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.IRI_REF || $.current.token == Token.PNAME_LN || $.current.token == Token.PNAME_NS){
			if(!$.analize("IRIref")) return false;
			$.next();
		
		}else{
			return false;
		}
		return true;
	}
}