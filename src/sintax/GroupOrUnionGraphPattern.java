package sintax;

import java.io.IOException;

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
		// TODO Auto-generated method stub
		if(ggp.analize()){
			element.addElement(ggp.element);
			 
			while($.current.token == Token.UNION){
				$.next();
				ggp = (GroupGraphPattern)$.get("GroupGraphPattern");
				if(!ggp.analize()) return false;
				element.addElement(ggp.element);
			}
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
