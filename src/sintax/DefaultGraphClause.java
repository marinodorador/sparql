package sintax; 

import java.io.IOException;

import lexic.Token;
import static lexic.Token.*;
/*
 * DefaultGraphClause ::= SourceSelector
 */
public class DefaultGraphClause extends Production{
	public boolean process() throws IOException{
		if($.current.token == Token.IRI_REF){
			if(!$.analize("SourceSelector")) return false;
		} else {
			return false;
		}
		return true;
	}

	@Override
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		
		return $.get("DatasetClause").FOLLOWS();
	}
}