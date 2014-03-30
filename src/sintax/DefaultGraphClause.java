package sintax; 

import java.io.IOException;

import lexic.Token;

public class DefaultGraphClause extends Production{
	public boolean analize() throws IOException{
		if($.current.token == Token.IRI_REF){
			if(!$.analize("SourceSelector")) return false;
		} else {
			return false;
		}
		return true;
	}
}