package sintax; 

import java.io.IOException;

import lexic.Token;

public class RegexExpression extends Production{

	@Override
	public boolean analize() throws IOException {
		if($.current.token != Token.REGEX) return false;
	    $.next();
	    if($.current.token != Token.LEFT_PARENTH) return false;
	    $.next();
		if(!$.analize("Expression")) return false;
		if($.current.token != Token.COMMA) return false;
		$.next();
		if(!$.analize("Expression")) return false;
		
		if($.current.token == Token.COMMA){
			$.next();
			if(!$.analize("Expression")){
				return false;
			}
		}
		if($.current.token != Token.RIGTH_PARENTH)return false;
		$.next();
		return true;
	}

}