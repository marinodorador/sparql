package sintax; 

import java.io.IOException;

import com.hp.hpl.jena.sparql.syntax.Element;

import lexic.Token;
import static lexic.Token.*;
/*
 * @author Esteban
 * WhereClause ::=   'WHERE'? GroupGraphPattern
 **/
public class WhereClause extends Production{
	public Element element = null;
	public boolean process() throws IOException{
		if($.current.token == Token.WHERE) $.next();		
		GroupGraphPattern ggp = (GroupGraphPattern)$.get("GroupGraphPattern");
		if(!ggp.analize()) return false;
		element = ggp.element;
		return true;
	}

	@Override
	public Token[] initFIRSTS() throws IOException {
		return null;
	}
	
	@Override
	public Token[] initFOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return new Token[]{ORDER_BY, LIMIT, OFFSET, END};
	}
}