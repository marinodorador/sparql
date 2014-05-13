package sintax; 

import java.io.IOException;
import java.util.ArrayList;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;

import lexic.Token;

/*
 * Verb	::= VarOrIRIref | 'a'
 * 
 * FIRST(Verb) = {VAR1, VAR2, IRIref, 'a',PNAME_LN,PNAME_NS}
 **/

public class Verb extends Production{
	public Node node = null;
	
	public boolean process() throws IOException{
		switch($.current.token){
			case VAR1: 
			case VAR2:
			case IRI_REF: 
			case PNAME_LN:
			case PNAME_NS:
				VarOrIRIref varOrIriRef = (VarOrIRIref)$.get("VarOrIRIref");
				if(!varOrIriRef.analize1()) return false;
					node = varOrIriRef.node;
				break;
			case A:
				node =  NodeFactory.createURI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
				$.next();
				break;
			default:
				return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<Token> FIRSTS() throws IOException {
		ArrayList<Token> ans = new ArrayList<Token>();
		
		ans.add( Token.A );
		
		for ( Token t : get("VarOrIRIref").FIRSTS() )
			ans.add(t);
		
		return ans;
	}
	
	@Override	
	public ArrayList<Token> FOLLOWS() throws IOException {
		return $.get("ObjectList").FIRSTS();
	}
}