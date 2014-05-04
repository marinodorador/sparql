package sintax; 

import java.io.IOException;

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
				if(!varOrIriRef.analize()) return false;
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
	public Token[] FOLLOWS() throws IOException {
		// TODO Auto-generated method stub
		return $.get("ObjectList").FOLLOWS();
	}
}