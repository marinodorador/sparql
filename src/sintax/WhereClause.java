package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.syntax.Element;

import lexic.Token;
/*
 * @author Esteban
 * WhereClause ::=   'WHERE'? GroupGraphPattern
 **/
public class WhereClause extends Production{
	public Element element = null;
	public boolean process() throws IOException{
		if($.current.token == Token.WHERE) $.next();		
		GroupGraphPattern ggp = (GroupGraphPattern)$.get("GroupGraphPattern");
		if(!ggp.analize1()) return false;
		element = ggp.element;
		return true;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		for ( Token t : get("GroupGraphPattern").FIRSTS() )
			ans.add(t);
		ans.add(Token.WHERE);
		for( Token t : get("GroupGraphPattern").FIRSTS() )
			ans.add(t);
		return ans;
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		for ( Token t : get("SolutionModifier").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
}