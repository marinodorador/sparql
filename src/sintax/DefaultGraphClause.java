package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import lexic.Token;
/*
 * DefaultGraphClause ::= SourceSelector
 */
public class DefaultGraphClause extends Production{
	public String uri = null;
	public boolean process() throws IOException{
		if($.current.token == Token.IRI_REF){
			SourceSelector ss = (SourceSelector)$.get("SourceSelector");
			if(!ss.analize()) return false;
			uri = ss.uri;
		} else {
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("SourceSelector").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("DatasetClause").FOLLOWS();
	}
}