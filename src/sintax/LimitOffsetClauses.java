package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * LimitOffsetClauses ::= ( LimitClause OffsetClause? | OffsetClause LimitClause? )
 * FIRST: 'LIMIT', 'OFFSET'
 **/
public class LimitOffsetClauses extends Production{

	@Override
	public boolean process() throws IOException {
		if($.current.token == Token.LIMIT){ //SE REVISA EL FIRST 
			if($.analize("LimitClause")){
				if ($.current.token == Token.OFFSET){
					return $.analize("OffsetClause");
				}
				return true;
			}else{
				return false;
			}
		}else if($.current.token == Token.OFFSET){ //SE REVISA EL FIRST
			if($.analize("OffsetClause")){
				if ($.current.token == Token.LIMIT){
					return $.analize("LimitClause");
				}
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		return construct(new Token[][]{
				get("SolutionModifier").FOLLOWS()});
	}
}