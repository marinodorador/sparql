package sintax;

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.sparql.syntax.ElementUnion;

import lexic.Token;
/*
 * GroupOrUnionGraphPattern	  ::=  	GroupGraphPattern ( 'UNION' GroupGraphPattern )*
 * */
public class GroupOrUnionGraphPattern extends Production{
	ElementUnion element = new ElementUnion();
	
	@Override
	public boolean process() throws IOException {
		GroupGraphPattern ggp = (GroupGraphPattern)$.get("GroupGraphPattern");
		if(ggp.analize()){
			element.addElement(ggp.element);
			 
			while($.current.token == Token.UNION){
				$.next();
				ggp = (GroupGraphPattern)$.get("GroupGraphPattern");
				if(!ggp.analize1()) return false;
				element.addElement(ggp.element);
			}
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		return get("GroupGraphPattern").FIRSTS();
	}
	
	@Override
	public ArrayList<Token> FOLLOWS() throws IOException {
		return get("GroupGraphPattern").FOLLOWS();
	}
}
