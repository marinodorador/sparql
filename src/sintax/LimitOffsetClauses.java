package sintax; 

import java.io.IOException;
import java.util.ArrayList;

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
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("LimitClause").FIRSTS() )
			ans.add(t);
		for ( Token t : get("OffsetClause").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("SolutionModifier").FOLLOWS();
	}
}