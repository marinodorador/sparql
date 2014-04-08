package sintax; 

import java.io.IOException;

import lexic.Token;
/*
 * LimitOffsetClauses ::= ( LimitClause OffsetClause? | OffsetClause LimitClause? )
 **/
public class LimitOffsetClauses extends Production{

	@Override
	public boolean process() throws IOException {
		if($.analize("LimitClause")){
			if ($.analize("OffsetClause")){
				return true;
			}
			return true;
		}else if ($.analize("OffsetClause")){
			if($.analize("LimitClause")) return true;
			return true;
		}
		return false;
	}

	@Override
	public Token[] FOLLOWS() {
		// TODO Auto-generated method stub
		return null;
	}

}